package view;

import connection.ServerConnection;
import model.StateHandler;

import javax.swing.*;
import java.awt.*;

public class GameView extends JFrame{

    private final JPanel GUI = new JPanel();
    private JButton submit = new JButton("Submit");
    private JRadioButton gameOne = new JRadioButton("Tic-Tac-Toe");
    private JRadioButton gameTwo = new JRadioButton("Reversi");
    private JTextField nameInput = new JTextField("name",1);
    private JLabel consoleName = new JLabel();
    private JLabel playerListName = new JLabel();
    private DefaultListModel modelConsole = new DefaultListModel();
    private BoardView boardView;
    private String gameValue;

    private ServerConnection serverConnection;

    private StateHandler stateHandler;
    public GameView(StateHandler stateHandler){
        this.stateHandler = stateHandler;
        serverConnection = ServerConnection.getServerConnection();
        drawGui();
        stateHandler.setGameState(stateHandler.getIdle());
        stateHandler.gameIdle();
    }

    public void drawGui(){
        this.setSize(1000, 800);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Board");


        GUI.setLayout(new GridBagLayout());

        drawConsole();
        drawPlayerList();
        drawBox();
        drawTextInput();
//        drawTicTacToe(boardView);

        this.add(GUI);
        this.setVisible(true);
        this.setResizable(false);
    }

    private void drawTicTacToe(BoardView b){
        addComp(GUI, b, 0, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);
    }

    private void drawReversi(BoardView b) {
        addComp(GUI, b, 0, 0, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.NONE);
    }

    private void drawConsole() {
        JPanel console = new JPanel();
        JList consoleList = new JList(modelConsole);
        JScrollPane scrollableConsoleList = new JScrollPane(consoleList);
        console.add(scrollableConsoleList);
        consoleList.setFixedCellWidth(600);
        consoleList.setFixedCellHeight(20);
        addComp(GUI, console, 0, 0, 1, 1, GridBagConstraints.SOUTH, GridBagConstraints.NONE);
    }

    private void drawPlayerList() {
        Box playerBox = Box.createVerticalBox();
        playerBox.add(playerListName);
        JList list = new JList();
        JScrollPane scrollableList = new JScrollPane(list);
        list.setFixedCellHeight(47);
        list.setFixedCellWidth(190);
        playerBox.add(scrollableList);

        addComp(GUI, playerBox, 0, 0, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);
    }

    private void drawBox(){
        Box box = Box.createVerticalBox();
        nameInput.setPreferredSize(new Dimension(100, 20));

        ButtonGroup group = new ButtonGroup();
        group.add(gameOne);
        group.add(gameTwo);

        box.add(nameInput);
        box.add(Box.createRigidArea(new Dimension(20, 20)));
        box.add(submit);
        box.add(Box.createRigidArea(new Dimension(20, 20)));
        box.add(gameOne);
        box.add(Box.createRigidArea(new Dimension(20, 20)));
        box.add(gameTwo);
        addComp(GUI, box, 0,0,3,1,GridBagConstraints.WEST,GridBagConstraints.NONE);

    }


    private void drawTextInput() {
        modelConsole.addElement("Geef je naam op in het text vak hierboven.");
        modelConsole.addElement("Selecteer het spel aan dat je wilt spelen.");
        modelConsole.addElement("Klik vervolgens op de knop Submit.");

        submit.addActionListener( (e)-> {
            submitAction();
            if(gameOne.isSelected()){
                drawTicTacToe(boardView = new BoardView(3));
                GUI.revalidate();
                GUI.repaint();
            }else if(gameTwo.isSelected()){
                drawReversi(boardView = new BoardView(8));
                GUI.revalidate();
                GUI.repaint();
            } else{
                String error = "Select a game";
                modelConsole.addElement(error);

            }
        });

    }

    private void submitAction() {
        if(gameOne.isSelected()) {
            System.out.println("Tic-Tac-Toe");
            gameValue = "Tic-tac-toe";

        } else if(gameTwo.isSelected()) {
            System.out.println("Reversi");
            gameValue = "Reversi";
        }

        // You can do some validation here before assign the text to the variable
        String name = nameInput.getText();
        System.out.println(name);
        modelConsole.clear();
        modelConsole.insertElementAt("Er wordt ingelogt met de naam: "  + name, 0);
        modelConsole.insertElementAt("Jou gekozen spel is: <game>", 1);
        modelConsole.insertElementAt("", 2);


        sendCommand("login", name);
        sendCommand("subscribe", gameValue);
    }

    // Sets the rules for a component destined for a GridBagLayout
    // and then adds it

    private void addComp(JPanel thePanel, JComponent comp, int xPos, int yPos, int compWidth, int compHeight, int place, int stretch){

        GridBagConstraints gridConstraints = new GridBagConstraints();

        gridConstraints.gridx = xPos;
        gridConstraints.gridy = yPos;
        gridConstraints.gridwidth = compWidth;
        gridConstraints.gridheight = compHeight;
        gridConstraints.weightx = 100;
        gridConstraints.weighty = 100;
        gridConstraints.insets = new Insets(5,5,5,5);
        gridConstraints.anchor = place;
        gridConstraints.fill = stretch;

        thePanel.add(comp, gridConstraints);
    }

    private void sendCommand(String action, String value){
        serverConnection.send(action,value);
    }


}
