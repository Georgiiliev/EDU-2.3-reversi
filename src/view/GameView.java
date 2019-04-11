package view;

import connection.ServerConnection;
import model.StateHandler;
import view.GUI.GhostText;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

public class GameView  extends JFrame {

    private final JPanel GUI = new JPanel();
    private JButton submit = new JButton("Submit");
    private JButton restart = new JButton("Go back to lobby");
    private JButton accept = new JButton("Accept match");
    private JButton challengeReceived = new JButton();
    private JRadioButton gameOne = new JRadioButton("Tic-Tac-Toe");
    private JRadioButton gameTwo = new JRadioButton("Reversi");
    private JRadioButton gameTypeOne = new JRadioButton("Human");
    private JRadioButton gameTypeTwo = new JRadioButton("AI");
    private JTextField nameInput = new JTextField(1);
    private JLabel nameGame = new JLabel("Game");
    private JLabel nameGameType = new JLabel("Gametype");
    private GhostText ghostText = new GhostText(nameInput, "Enter your name..");
    private JFrame popUp;

    private DefaultListModel playerList = new DefaultListModel();
    private DefaultListModel modelConsole = new DefaultListModel();
    private BoardView boardView;
    private String gameValue;
    private String userName = "";

    private ServerConnection serverConnection;
    private StateHandler stateHandler;
    private GameView gameView;
    private String[] players;
    private boolean ai;

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();


    public GameView(StateHandler stateHandler, ServerConnection serverConnection){
        this.gameView = this;
        this.serverConnection = serverConnection;
        this.stateHandler = stateHandler;

        this.stateHandler.setGameState(this.stateHandler.getIdle());
        this.stateHandler.gameIdle();

        drawGui();
    }

    public void drawGui(){
        this.setSize(screenSize.width, screenSize.height);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Game Engine - Groep 4");

        GUI.setLayout(new GridBagLayout());

        drawConsole();
        drawPlayerList();
        drawBox();
        drawTextInput();

        this.add(GUI);
        this.setVisible(true);
        this.setResizable(true);
        this.setLocationRelativeTo(null);

    }

    public void drawTicTacToe(){
        boardView = new BoardView(3, stateHandler, this);
        addComp(GUI, boardView, 0, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);
    }

    public void drawReversi() {
        boardView = new BoardView(8, stateHandler, this);
        addComp(GUI, boardView, 0, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);
    }


    private void drawConsole() {
        JPanel console = new JPanel();
        JList consoleList = new JList(modelConsole);
        JScrollPane scrollableConsoleList = new JScrollPane(consoleList);
        console.add(scrollableConsoleList);
        consoleList.setFixedCellWidth(900);
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
        ListSelectionListener listSelectionListener = new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent listSelectionEvent) {
                boolean adjust = listSelectionEvent.getValueIsAdjusting();
                if (!adjust) {
                    JList list = (JList) listSelectionEvent.getSource();
                    int selections[] = list.getSelectedIndices();
                    Object selectionValues[] = list.getSelectedValues();
                    for (int i = 0, n = selections.length; i < n; i++) {
                        if (i == 0) {
                            System.out.println(" Selections: ");
                        }
                        if(gameOne.isSelected()) {
                            gameValue = "Tic-tac-toe";

                        } else if(gameTwo.isSelected()) {
                            gameValue = "Reversi";
                        }
                        challengePlayer(selectionValues[i], gameValue);
                        modelConsole.addElement(selectionValues[i]);
                    }
                }
            }
        };
        list.addListSelectionListener(listSelectionListener);
        addComp(GUI, setListPanel, 0, 0, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);
    }

    private void drawBox(){
        Box box = Box.createVerticalBox();
        nameInput.setPreferredSize(new Dimension(50, 20));

        ButtonGroup typeGroup = new ButtonGroup();
        typeGroup.add(gameTypeOne);
        typeGroup.add(gameTypeTwo);

        ButtonGroup gameGroup = new ButtonGroup();
        gameGroup.add(gameOne);
        gameGroup.add(gameTwo);

        box.add(nameGame);
        box.add(Box.createRigidArea(new Dimension(20, 5)));
        box.add(gameOne);
        box.add(gameTwo);

        box.add(Box.createRigidArea(new Dimension(20, 20)));
        box.add(nameGameType);
        box.add(Box.createRigidArea(new Dimension(20, 5)));
        box.add(gameTypeOne);
        box.add(gameTypeTwo);

        box.add(Box.createRigidArea(new Dimension(20, 20)));
        box.add(nameInput);
        box.add(Box.createRigidArea(new Dimension(20, 20)));
        box.add(submit);
        addComp(GUI, box, 0,0,3,1,GridBagConstraints.WEST,GridBagConstraints.NONE);

    }


    private void drawTextInput() {
        modelConsole.addElement("Geef je naam op in het text vak hierboven.");
        modelConsole.addElement("Selecteer het spel aan dat je wilt spelen.");
        modelConsole.addElement("Klik vervolgens op de knop Submit.");

        submit.addActionListener( (e)-> {
            submitAction();
            sendCommand("get", "playerlist");
//            if(gameOne.isSelected()){
//                if(boardView != null){
//                    GUI.remove(boardView);
//                }
//                drawTicTacToe();
//                GUI.revalidate();
//                GUI.repaint();
//            }else if(gameTwo.isSelected()){
//                if(boardView != null){
//                    GUI.remove(boardView);
//                }
//                drawReversi();
//                GUI.revalidate();
//                GUI.repaint();
//
//            }
            if(gameTypeOne.isSelected()){
                this.ai = true;
            } else if(gameTypeTwo.isSelected()){
                this.ai = false;
            }
        });
    }
    public boolean getAI(){
        return ai;
    }

    private void submitAction() {

        // You can do some validation here before assign the text to the variable
        String name = nameInput.getText();

        if (userName == null || userName.equals("")){
            userName = name;
        }
        modelConsole.clear();
        modelConsole.insertElementAt("Er is ingelogt met de naam: "  + userName, 0);
        modelConsole.insertElementAt("Jou gekozen spel is: " + gameValue, 1);

        sendCommand("login", name);
//        sendCommand("subscribe", gameValue);
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

    public void endGamePopUp(String text){
        popUp = new JFrame(text);
        popUp.setSize(300,100);
        popUp.getContentPane().add(restart);
        popUp.setVisible(true);
        popUp.setLocationRelativeTo(null);
        restartGame();
    }

    public void setPlayerListFromServer(String[] players){
        playerList.clear();
        this.players = players;

        for(int i = 0; i < this.players.length; i++){
            playerList.addElement(this.players[i]);
        }
    }

    private void restartGame(){
        restart.addActionListener( (e)-> {
            System.out.println("Go back to lobby");
            popUp.dispose();
            boardView.setVisible(false);
        });
    }
    public void challengePopUp(String challenger, String challengeNumber, String game){
        String text = "You have been challenged for " + game + " by " + challenger;
        popUp = new JFrame("Challenged!");
        popUp.setSize(500,100);
        challengeReceived.setText(text);
        popUp.getContentPane().add(challengeReceived);
        popUp.setVisible(true);
        popUp.setLocationRelativeTo(null);
        acceptChallenge(challengeNumber, game);
    }

    public void acceptChallenge(String challengeNumber, String game){
        challengeReceived.addActionListener( (e)-> {
            sendCommand("challenge accept", challengeNumber);
            popUp.dispose();
        });
    }

    private void challengePlayer(Object playerName, String game){
        sendCommand("challenge", "\"" + playerName + "\" \"" + game + "\"");
    }


    public String getGameValue(){
        return gameValue;
    }

    public String getUserName(){
        return userName;
    }
    public GameView getGameView(){
        return gameView;
    }
    public BoardView getBoardView(){return boardView;}
}
