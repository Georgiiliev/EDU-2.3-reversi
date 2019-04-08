package view;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import connection.ServerConnection;
import controller.CommandController;
import model.StateHandler;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class GameView extends JFrame{

    private final JPanel GUI = new JPanel();
    private JButton submit = new JButton("Submit");
    private JButton restart = new JButton("Go back to lobby");
    private JRadioButton gameOne = new JRadioButton("Tic-Tac-Toe");
    private JRadioButton gameTwo = new JRadioButton("Reversi");
    private JTextField nameInput = new JTextField("name",1);
    private JLabel consoleName = new JLabel();
    private DefaultListModel playerList = new DefaultListModel();
    private DefaultListModel modelConsole = new DefaultListModel();
    private BoardView boardView;
    private String gameValue;
    private String userName = "";

    private ServerConnection serverConnection;
    private StateHandler stateHandler;
    private CommandController commandController;
    private static GameView gameView;
    private String[] players;

    public GameView(StateHandler stateHandler){

        this.gameView = this;
        serverConnection = ServerConnection.getServerConnection();
        commandController = new CommandController(serverConnection);
        this.stateHandler = stateHandler;
        drawGui();

        this.stateHandler.setGameState(this.stateHandler.getIdle());
        this.stateHandler.gameIdle();
        System.out.println(this.stateHandler.getState());
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
        this.setLocationRelativeTo(null);

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
        JPanel setListPanel = new JPanel();
        JList list = new JList(playerList);
        JScrollPane scrollableList = new JScrollPane(list);
        setListPanel.add(scrollableList);
        list.setFixedCellHeight(47);
        list.setFixedCellWidth(190);
        addComp(GUI, setListPanel, 0, 0, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);
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
            sendCommand("get", "playerlist");
            if(gameOne.isSelected()){
                if(boardView != null){
                    GUI.remove(boardView);
                }
                drawTicTacToe(boardView = new BoardView(3, stateHandler, commandController, this));
                GUI.revalidate();
                GUI.repaint();
            }else if(gameTwo.isSelected()){
                if(boardView != null){
                    GUI.remove(boardView);
                }
                if(boardView != null){
                    GUI.remove(boardView);
                }
                drawReversi(boardView = new BoardView(8, stateHandler, commandController, this));
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
            gameValue = "Tic-tac-toe";

        } else if(gameTwo.isSelected()) {
            gameValue = "Reversi";
        }

        // You can do some validation here before assign the text to the variable
        String name = nameInput.getText();
        System.out.println(name);
        if (userName == null || userName.equals("")){
            userName = name;
        }
        modelConsole.clear();
        modelConsole.insertElementAt("Er is ingelogt met de naam: "  + userName, 0);
        modelConsole.insertElementAt("Jou gekozen spel is: " + gameValue, 1);

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

    public void sendCommand(String action, String value){
        serverConnection.send(action,value);
    }

    public String getGameValue(){
        return gameValue;
    }
    public String getUserName(){
        return userName;
    }
    public static GameView getGameView(){
        return gameView;
    }

    public void endGamePopUp(String text){
        JFrame frame = new JFrame(text);
        frame.setSize(300,100);
        frame.getContentPane().add(restart);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    public void setPlayerListFromServer(String[] players){
        playerList.clear();
        this.players = players;
        System.out.println(Arrays.deepToString(this.players));

        for(int i = 0; i < this.players.length; i++){
            playerList.addElement(this.players[i]);
        }
    }

    public void restartGame(){
        restart.addActionListener( (e)-> {
            System.out.println("Restarting game");
        });
    }
}
