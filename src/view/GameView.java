package view;

import connection.ServerConnection;
import model.StateHandler;
import view.GUI.GhostText;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class GameView  extends JFrame {

    private final JPanel GUI = new JPanel();
    private JButton submit = new JButton("Submit");
    private JButton challengeReceived = new JButton();
    private JRadioButton gameOne = new JRadioButton("Tic-Tac-Toe");
    private JRadioButton gameTwo = new JRadioButton("Reversi");
    private JRadioButton gameTypeOne = new JRadioButton("Human");
    private JRadioButton gameTypeTwo = new JRadioButton("AI");
    private JTextField nameInput = new JTextField();
    private JLabel nameGame = new JLabel("GameType");
    private JLabel nameGameType = new JLabel("GameMode");
    private JLabel playerOne = new JLabel("PLAYERNAME1");
    private JLabel playerTwo = new JLabel("PLAYERNAME2");
    private JLabel counterCountDown = new JLabel("10");
    private JFrame popUp;

    private DefaultListModel playerList = new DefaultListModel();
    private DefaultListModel modelConsole = new DefaultListModel();
    private BoardView boardView;
    private String gameValue;
    private String userName = "";

    private int interval;
    private Timer counterTimer;

    private ServerConnection serverConnection;
    private StateHandler stateHandler;
    private GameView gameView;
    private String[] players;
    private boolean ai;


    public GameView(StateHandler stateHandler, ServerConnection serverConnection){
        this.ai = false;
        this.gameView = this;
        this.serverConnection = serverConnection;
        this.stateHandler = stateHandler;
        this.stateHandler.setGameState(this.stateHandler.getIdle());
        this.stateHandler.gameIdle();
        drawGui();
    }

    public void drawGui(){
        this.setSize(1000, 750);
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
        drawCounter();
        boardView = new BoardView(3, stateHandler, this);
        addComp(GUI, boardView, 0, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE, 5);
    }

    public void drawReversi() {
        drawCounter();
        boardView = new BoardView(8, stateHandler, this);
        addComp(GUI, boardView, 0, 0, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.NONE, 50);
    }


    private void drawConsole() {
        JPanel console = new JPanel();
        JList consoleList = new JList(modelConsole);
        JScrollPane scrollableConsoleList = new JScrollPane(consoleList);
        console.add(scrollableConsoleList);
        console.add(Box.createRigidArea(new Dimension(15, 0)));
        consoleList.setFixedCellWidth(740);
        consoleList.setFixedCellHeight(20);
        addComp(GUI, console, 0, 0, 1, 1, GridBagConstraints.SOUTHEAST, GridBagConstraints.NONE, 5);
    }
    private void drawCounter(){
        Box counterBox = Box.createVerticalBox();
        counterCountDown.setFont(new Font("Verdana", Font.BOLD, 60));
        counterCountDown.setForeground(Color.red);
        counterBox.add(counterCountDown);
        counterBox.add(Box.createRigidArea(new Dimension(100, 60)));
        addComp(GUI, counterBox, 0, 0, 1, 1, GridBagConstraints.SOUTHWEST, GridBagConstraints.NONE, 5);
    }
    public void stopCounter(){
        if (counterTimer != null){
            counterTimer.cancel();
        }
    }

    public void setTimer(int time) {
        counterCountDown.setText(Integer.toString(10));
        int delay = 900;
        int period = 900;
        counterTimer = new Timer();
        interval = time;
        counterTimer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                counterCountDown.setText(Integer.toString(interval));
                setInterval(time);
            }
        }, delay, period);
    }
    private final int setInterval(int time) {
        if (interval == 0) {
            counterTimer.cancel();
            // TODO hij is 0 dus doe een random move!
        }
        return --interval;
    }

    private void drawPlayerList() {
        JPanel setListPanel = new JPanel();
        JList list = new JList(playerList);
        JScrollPane scrollableList = new JScrollPane(list);
        setListPanel.add(scrollableList);

        Box gameBox = Box.createVerticalBox();
        ButtonGroup gameGroup = new ButtonGroup();
        gameOne.setFont(new Font("Verdana", Font.BOLD, 14));
        gameGroup.add(gameOne);
        gameTwo.setFont(new Font("Verdana", Font.BOLD, 14));
        gameGroup.add(gameTwo);

        nameGame.setFont(new Font("Verdana", Font.BOLD, 25));
        nameGame.setForeground(Color.darkGray);
        gameBox.add(Box.createRigidArea(new Dimension(0, 20)));
        gameBox.add(nameGame);

        gameBox.add(Box.createRigidArea(new Dimension(0, 20)));
        gameBox.add(gameOne);
        gameBox.add(gameTwo);

        gameBox.add(Box.createRigidArea(new Dimension(0, 20)));
        gameBox.add(setListPanel);

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
                        if(gameOne.isSelected()) {
                            gameValue = "Tic-tac-toe";
                            challengePlayer(selectionValues[i], gameValue);
                        } else if(gameTwo.isSelected()) {
                            gameValue = "Reversi";
                            challengePlayer(selectionValues[i], gameValue);
                        } else{
                            addToConsole("Select a game to challenge an opponent.");
                        }
                    }
                }
            }
        };
        list.addListSelectionListener(listSelectionListener);
        addComp(GUI, gameBox, 0, 0, 1, 1, GridBagConstraints.NORTHEAST, GridBagConstraints.NONE, 5);
    }

    private void drawBox(){
        Box box = Box.createVerticalBox();
        nameInput.setPreferredSize(new Dimension(0, 20));
        new GhostText(nameInput, "Enter your name..");

        ButtonGroup typeGroup = new ButtonGroup();
        typeGroup.add(gameTypeOne);
        typeGroup.add(gameTypeTwo);
        gameTypeOne.setSelected(true);

        submit.setBackground(new Color(59, 89, 182));
        submit.setForeground(Color.WHITE);
        submit.setFocusPainted(false);
        submit.setFont(new Font("Verdana", Font.BOLD, 16));

        box.add(Box.createRigidArea(new Dimension(20, 20)));

        nameGameType.setFont(new Font("Verdana", Font.BOLD, 25));
        nameGameType.setForeground(Color.darkGray);
        box.add(nameGameType);

        box.add(Box.createRigidArea(new Dimension(20, 20)));

        gameTypeOne.setFont(new Font("Verdana", Font.BOLD, 14));
        box.add(gameTypeOne);

        gameTypeTwo.setFont(new Font("Verdana", Font.BOLD, 14));
        box.add(gameTypeTwo);

        box.add(Box.createRigidArea(new Dimension(0, 20)));
        box.add(nameInput);
        box.add(Box.createRigidArea(new Dimension(0, 20)));
        box.add(submit);

        //TODO Game status.
        box.add(Box.createRigidArea(new Dimension(20, 40)));
        playerOne.setFont(new Font("Verdana", Font.BOLD, 15));
        box.add(playerOne);
        playerTwo.setFont(new Font("Verdana", Font.BOLD, 15));
        box.add(playerTwo);

        addComp(GUI, box, 0,0,1,1, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, 5);
    }


    private void drawTextInput() {
        addToConsole("Enter your name in the text box on the left.");
        addToConsole("Then click on the Submit button.");

        submit.addActionListener( (e)-> {
            submitAction();
            sendCommand("get", "playerlist");

            if(gameTypeOne.isSelected()){
                this.ai = false;
            } else if(gameTypeTwo.isSelected()){
                this.ai = true;
            }
        });
        gameTypeOne.addActionListener( (e)-> this.ai = false );
        gameTypeTwo.addActionListener( (e)-> this.ai = true );
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
        addToConsole("Logged in with the name: "  + userName);
        addToConsole("To challenge an opponent, choose a game on the right and click on your opponent's name.");

        sendCommand("login", name);
    }

    // Sets the rules for a component destined for a GridBagLayout
    // and then adds it

    private void addComp(JPanel thePanel, JComponent comp, int xPos, int yPos, int compWidth, int compHeight,
                         int place, int stretch, int x){
        GridBagConstraints gridConstraints = new GridBagConstraints();

        gridConstraints.gridx = xPos;
        gridConstraints.gridy = yPos;
        gridConstraints.gridwidth = compWidth;
        gridConstraints.gridheight = compHeight;
        gridConstraints.weightx = 100;
        gridConstraints.weighty = 100;
        gridConstraints.insets = new Insets(5, 5,5,x);
        gridConstraints.anchor = place;
        gridConstraints.fill = stretch;

        thePanel.add(comp, gridConstraints);
    }

    public void sendCommand(String action, String value){
        serverConnection.send(action,value);
    }


    public void setPlayerListFromServer(String[] players){
        playerList.clear();
        this.players = players;



        for(int i = 0; i < this.players.length; i++){
            playerList.addElement(this.players[i]);
        }
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
        if(userName.isEmpty()){
            addToConsole("Log in before you challenge an opponent");
        }
        else{
            if (userName.equals(playerName)) {
                addToConsole("You cannot challenge yourself...");
            } else {
                sendCommand("challenge", "\"" + playerName + "\" \"" + game + "\"");
                addToConsole("You challenged " + playerName);
            }
        }
    }

    public void addToConsole(String value){
        modelConsole.insertElementAt(value, 0);
        setForeground( Color.red );
    }

    public void removeGameBoard(){
        GUI.remove(boardView);
        GUI.revalidate();
        GUI.repaint();
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
