package controller;

import AI.controller.ReversiAI;
import connection.ServerConnection;
import model.StateHandler;
import view.GameView;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class CommandHandler implements Runnable{
    private String gameType;
    private ServerConnection connect;
    private StateHandler stateHandler;
    private GameView gameView;
    private MoveController moveController;
    private String receive;

    private int interval;
    private Timer timer;

    private String opponentName;
    private String playerName;
    private boolean start;

    public CommandHandler(ServerConnection connect, StateHandler stateHandler, GameView gameView) {
        opponentName = "";
        playerName = "";
        this.gameView = gameView;
        this.connect = connect;
        this.stateHandler = stateHandler;

        connect.send("get","playerlist");
        setTimer(5);
    }

    public void start(){
        while (true){

            while (connect.hasNext()){
                receive = connect.receive();
                if (receive.startsWith("OK")){ // Ingevoerde commando is goed gegaan.
                    //Doe niets. Goed!
                }
                else if (receive.startsWith("SVR")){
                    removeFirstSpace(receive);

                    if (receive.startsWith("GAME")){
                        removeFirstSpace(receive);

                        if (receive.startsWith("MATCH")){ // Er is een match gestart
                            removeFirstSpace(receive);

                            gameType = (String) stringToHashMap(receive).get("GAMETYPE");
                            opponentName = (String) stringToHashMap(receive).get("OPPONENT");
                            String firstToStart = (String) stringToHashMap(receive).get("PLAYERTOMOVE");

                            start = false;
                            playerName = gameView.getUserName();
                            gameView.addToConsole("");
                            gameView.addToConsole("Your opponent is " + opponentName);

                            if (playerName.equals(firstToStart)){
                                start = true;
                                gameView.setPLayerNames(playerName, opponentName, gameType);
                            } else
                                gameView.setPLayerNames(opponentName, playerName, gameType);

                            System.out.println(" A match has been found!");
                            if (gameType.equals("Reversi")){
                                stateHandler.setGameState(stateHandler.getGameStarted());
                                stateHandler.gameStart(gameView, gameType);
                                moveController = new MoveController(8, stateHandler, start, gameView);
                            }
                            else if ( gameType.equals("Tic-tac-toe")){
                                stateHandler.setGameState(stateHandler.getGameStarted());
                                stateHandler.gameStart(gameView, gameType);
                                moveController = new MoveController(3, stateHandler, start, gameView);
                            }

                            stateHandler.setGameState(stateHandler.getServerMove());
                        }

                        else if(receive.startsWith("MOVE")){ // move is gezet door 1 van bijde spelers.
                            removeFirstSpace(receive);
                            HashMap hashMap = stringToHashMap(receive);

                            String name = gameView.getUserName();
                            if (!hashMap.get("PLAYER").equals(name)){ // als speler tegenstander is dan:
                                int move = Integer.parseInt((String)hashMap.get("MOVE")); // De speler heeft op X gespeeld

                                int[] a = serverIntToLocal(move); // zet move naar onze spel

                                moveController.serverMove(a[0], a[1]);
                            }
                        }

                        else if(receive.startsWith("YOURTURN")){
                            // sleep om laatst binnen gekomen move eerst te verwerken.
                            try {
                                Thread.sleep(100);
                            } catch(InterruptedException e){
                                e.printStackTrace();
                            }
                            gameView.stopCounter();
                            gameView.setTimer(9);
                            stateHandler.setGameState(stateHandler.getClientMove());
                        }

                        else if(receive.startsWith("LOSS")){
                            // state = game ended loss
                            fetchScore("LOST");
                        }

                        else if(receive.startsWith("WIN")){
                            fetchScore("WON");
                        }

                        else if(receive.startsWith("DRAW")){
                            fetchScore("DRAW");
                        }

                        else if(receive.startsWith("CHALLENGE")){
                            removeFirstSpace(receive);
                            String challenger = (String) stringToHashMap(receive).get("CHALLENGER");
                            String challengeNumber = (String) stringToHashMap(receive).get("CHALLENGENUMBER");
                            String game = (String) stringToHashMap(receive).get("GAMETYPE");
                            gameView.challengePopUp(challenger, challengeNumber, game);
                        }
                    }

                    else if (receive.startsWith("PLAYERLIST")){
                        removeFirstSpace(receive);
                        String[] players = stringToArray(receive);

                        gameView.setPlayerListFromServer(players);
                    }
                }

                else if (receive.startsWith("ERR")){ // heeft GEEN gevolgen op het spel
                    System.out.println("Error found: " + receive);
                }

                else{
                    System.out.println("This is a new command: " + receive);
                }
            }
        }
    }
    private void fetchScore(String status){
        removeFirstSpace(receive);
        String playerScore;
        String opponentScore;

        String playerOne = (String) stringToHashMap(receive).get("PLAYERONESCORE");
        String playerTwo = (String) stringToHashMap(receive).get("PLAYERTWOSCORE");
        String comment = (String) stringToHashMap(receive).get("COMMENT");

        if(start){
            playerScore = playerOne;
            opponentScore = playerTwo;
        } else {
            playerScore = playerTwo;
            opponentScore = playerOne;
        }

        if (!comment.equals("")){
            gameView.addToConsole("Comment: "+ comment);
        }
        gameView.addToConsole("Your score: " + playerScore +" | "+" Opponent score: "+opponentScore);
        gameView.addToConsole("You have " + status + " the game!");

        gameView.removeGameBoard();
        stateHandler.setGameState(stateHandler.getGameEndedLoss());
        ReversiAI.disableThread();
    }

    private void removeFirstSpace(String string){
        int spacePos = string.indexOf(" ");
        receive = string.substring(spacePos + 1);
    }

    private HashMap stringToHashMap(String hashMap){
        String cleanString = hashMap.replaceAll("(\\{|}|\")",""); // verwijdert rare items
        String[] nieuwMap = cleanString.split("[,|:]");
        Arrays.asList(nieuwMap);
        HashMap<String, String> serverGegevens = new HashMap<>();
        for (int i = 0; i < nieuwMap.length; i++){
            serverGegevens.put(nieuwMap[i].replaceAll(" ",""), nieuwMap[++i].substring(1));
        }
        return serverGegevens;
    }
    private String[] stringToArray(String array){ // void moet array worden
        String ok = array.replaceAll("(\\[|]|\")","");
        String[] nieuweArray = ok.split("[,]");
        for (int i = 1; i < nieuweArray.length; i++){
            nieuweArray[i] = nieuweArray[i].substring(1);
        }
        return nieuweArray;
    }
    private int[] serverIntToLocal(int oldInt){
        int[] newInt = new int[2];
        int gameSize = 0;

        if(getGameType().equals("Reversi")){
            gameSize = 8;
        } else if (getGameType().equals("Tic-tac-toe")){
            gameSize = 3;
        }
        newInt[0] = oldInt / gameSize; // = row
        newInt[1] = oldInt % gameSize;// = column
        return newInt;
    }


    private void setTimer(int time) {
        int delay = 1000;
        int period = 1000;
        timer = new Timer();
        interval = time;
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                setInterval(time);
            }
        }, delay, period);
    }
    private final int setInterval(int time) {
        if (interval == 0) {
            timer.cancel();
            setTimer(time);
            connect.send("get","playerlist");
        }
        return --interval;
    }

    public String getGameType(){
        return gameType;
    }

    public StateHandler getStateHandler(){
        return stateHandler;
    }
    @Override
    public void run() {
        start();
    }
}
