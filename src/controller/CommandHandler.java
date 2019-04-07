package controller;

import connection.ServerConnection;
import model.StateHandler;
import view.GameView;

import java.util.Arrays;
import java.util.HashMap;

public class CommandHandler implements Runnable{
    private String gameType;
    private ServerConnection connect;
    private CommandHandler commandHandler;
    private StateHandler stateHandler;
    private MoveController moveController;
    private GameView gameView;

    public CommandHandler(ServerConnection connect, StateHandler stateHandler) {
        this.gameView = GameView.getGameView();
        this.moveController = MoveController.getMoveController();
        this.connect = connect;
        this.commandHandler = this;
        this.stateHandler = stateHandler;
    }

    public void start() {
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
                                moveController = new MoveController(8, stateHandler, start);
                            }
                            else if ( gameType.equals("Tic-tac-toe")){
                                moveController = new MoveController(3, stateHandler, start);
                            }


                            stateHandler.setGameState(stateHandler.getServerMove());
                        }

                        else if(receive.startsWith("MOVE")){ // move is gezet door 1 van bijde spelers.
                            receive = receive.substring(5);
                            HashMap hashMap = stringToHashMap(receive);

                            String name = gameView.getUserName();
                            if (!hashMap.get("PLAYER").equals(name)){ // als speler tegenstander is dan:
                                System.out.println("TEGENSTANDER doet het volgende: ");
                                int move = Integer.parseInt((String)hashMap.get("MOVE")); // De speler heeft op X gespeeld

                                int[] a = serverIntToLocal(move); // zet move naar onze spel

                                // TODO geef het volgende aan een functie
                                System.out.println("zet die gedaan is: " +a[0] + ", " + a[1]);
                                moveController.serverMove(gameType, a[0], a[1]);
//                                printIcon(a[0], a[1]), "X");

                                stateHandler.setGameState(stateHandler.getClientMove());
                            }
                            else{
                                System.out.println("WIJZELF");
                                stateHandler.setGameState(stateHandler.getServerMove());
                            }
                        }

                        else if(receive.startsWith("YOURTURN")){
                            // state = doe een zet.
                            System.out.println(stateHandler.getState());
                            stateHandler.setGameState(stateHandler.getClientMove());
                        }

                        else if(receive.startsWith("LOSS")){
                            // state = game ended loss
                            stateHandler.setGameState(stateHandler.getGameEndedLoss());
                        }

                        else if(receive.startsWith("WIN")){
                            // state = game ended win
                            stateHandler.setGameState(stateHandler.getGameEndedWin());

                        }

                        else if(receive.startsWith("DRAW")){
                            // state = game ended win
                            stateHandler.setGameState(stateHandler.getGameEndedDraw());
                        }

                        else if(receive.startsWith("CHALLENGE")){

                        }
                    }

                    else if (receive.startsWith("PLAYERLIST")){
                        receive = receive.substring(11);
                        String[] players = stringToArray(receive);
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
        newInt[0] = oldInt / gameSize;
        newInt[1] = oldInt % gameSize;
        return newInt;
    }

    private void placeMove(){

    }

    public String getGameType(){
        return gameType;
    }
    public CommandHandler commandHandler(){
        return commandHandler;
    }

    public StateHandler getStateHandler(){
        return stateHandler;
    }
    @Override
    public void run() {
        start();
    }
}
