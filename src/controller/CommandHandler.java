package controller;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
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

    private int interval;
    private Timer timer;

    public CommandHandler(ServerConnection connect, StateHandler stateHandler, GameView gameView) {
        this.gameView = gameView;
        this.connect = connect;
        this.stateHandler = stateHandler;

        connect.send("get","playerlist");
        setTimer(5);
    }

    public void start(){
        while (true){

            while (connect.hasNext()){
                String receive = connect.receive();
                if (receive.startsWith("OK")){ // Ingevoerde commando is goed gegaan.
                    //Doe niets. Goed!
                }
                else if (receive.startsWith("SVR")){
                    receive = receive.substring(4);

                    if (receive.startsWith("GAME")){
                        receive = receive.substring(5);

                        if (receive.startsWith("MATCH")){ // Er is een match gestart
                            receive = receive.substring(6);

                            gameType = (String) stringToHashMap(receive).get("GAMETYPE");
                            String firstToStart = (String) stringToHashMap(receive).get("PLAYERTOMOVE");


                            boolean start = false;

                            String name = gameView.getUserName();
                            if (name.equals(firstToStart)){
                                start = true;
                            }

                            System.out.println("Er is een match gevonden!");
                            if (gameType.equals("Reversi")){
                                stateHandler.setGameState(stateHandler.getGameStarted());
                                stateHandler.gameStart(gameView, gameType);

//                                gameView.drawReversi();
                                moveController = new MoveController(8, stateHandler, start, gameView);
                            }
                            else if ( gameType.equals("Tic-tac-toe")){
                                stateHandler.setGameState(stateHandler.getGameStarted());
                                stateHandler.gameStart(gameView, gameType);

//                                gameView.drawTicTacToe();
                                moveController = new MoveController(3, stateHandler, start,gameView);
                            }
                            stateHandler.setGameState(stateHandler.getServerMove());
                        }

                        else if(receive.startsWith("MOVE")){ // move is gezet door 1 van bijde spelers.
                            receive = receive.substring(5);
                            System.out.println(receive);
                            HashMap hashMap = stringToHashMap(receive);

                            String name = gameView.getUserName();
                            if (!hashMap.get("PLAYER").equals(name)){ // als speler tegenstander is dan:
                                int move = Integer.parseInt((String)hashMap.get("MOVE")); // De speler heeft op X gespeeld

                                int[] a = serverIntToLocal(move); // zet move naar onze spel

                                moveController.serverMove(a[0], a[1]);
                                stateHandler.setGameState(stateHandler.getClientMove());
                            }
                            else{
                                stateHandler.setGameState(stateHandler.getServerMove());
                            }
                        }

                        else if(receive.startsWith("YOURTURN")){
                            // state = doe een zet.
                            stateHandler.setGameState(stateHandler.getClientMove());
                        }

                        else if(receive.startsWith("LOSS")){
                            // state = game ended loss
                            stateHandler.setGameState(stateHandler.getGameEndedLoss());
                            stateHandler.endGameLoss();
                            gameView.restartGame();
                            gameView.endGamePopUp("You have lost the game!");
                            System.out.println(receive);
                        }

                        else if(receive.startsWith("WIN")){
                            // state = game ended win
                            stateHandler.setGameState(stateHandler.getGameEndedWin());
                            stateHandler.endGameWin();
                            gameView.restartGame();
                            gameView.endGamePopUp("You have won the game!");
                            System.out.println(receive);

                        }

                        else if(receive.startsWith("DRAW")){
                            // state = game ended win
                            stateHandler.setGameState(stateHandler.getGameEndedDraw());
                            stateHandler.endGameDraw();
                            gameView.restartGame();
                            gameView.endGamePopUp("You have tied the game!");
                            System.out.println(receive);
                        }

                        else if(receive.startsWith("CHALLENGE")){
                            receive = receive.substring(10);
                            String challenger = (String) stringToHashMap(receive).get("CHALLENGER");
                            String challengeNumber = (String) stringToHashMap(receive).get("CHALLENGENUMBER");
                            String game = (String) stringToHashMap(receive).get("GAMETYPE");
                            gameView.challengePopUp(challenger, challengeNumber, game);
                        }
                    }

                    else if (receive.startsWith("PLAYERLIST")){
                        receive = receive.substring(11);
                        String[] players = stringToArray(receive);

                        gameView.setPlayerListFromServer(players);
                    }
                }

                else if (receive.startsWith("ERR")){ // heeft GEEN gevolgen op het spel
                    System.out.println("Error gevonden: " + receive.substring(4));
                }

                else{
                    System.out.println("Dit is een nieuwe commando: " + receive);
                }
            }
        }
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
