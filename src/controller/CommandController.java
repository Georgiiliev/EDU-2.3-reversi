package controller;

import connection.ServerConnection;
import model.StateHandler;
import view.GameView;

import java.util.Arrays;
import java.util.HashMap;

public class CommandController implements Runnable{
    private String gameType;
    private ServerConnection connect;
    private CommandController commandController;
    private StateHandler stateHandler;

    public CommandController(ServerConnection connect) {
        this.connect = connect;
        this.commandController = this;
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
                            HashMap hashMap = stringToHashMap(receive);

                            hashMap.get("GAMETYPE"); // reversi of tic-tac-toe
                            if (hashMap.get("GAMETYPE") == "Reversi"){
                                gameType = "Reversie";
//                                stateHandler.setGameState();
                            }
                            if (hashMap.get("GAMETYPE") == "Tic-tac-toe"){
                                gameType = "Tic-tac-toe";
//                                stateHandler.setGameState();
                            }
                            // State is gamestarted
                        }

                        else if(receive.startsWith("MOVE")){ // move is gezet door 1 van bijde spelers.
                            receive = receive.substring(5);
                            HashMap hashMap = stringToHashMap(receive);

                            String name = "player"; //TODO
                            if (!hashMap.get("PLAYER").equals(name)){ // als speler tegenstander is dan:
                                stateHandler.setGameState(stateHandler.getServerMove());
                                int move = Integer.parseInt((String)hashMap.get("MOVE")); // De speler heeft op X gespeeld

                                int[] a = serverIntToLocal(move);
                                // TODO geef het volgende aan een functie
                                System.out.println(a[0]);
                                System.out.println(a[1]);
//                                printIcon(a[0], a[1]), "X");
                            }
                        }

                        else if(receive.startsWith("YOURTURN")){
                            // state = doe een zet.
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
                        GameView gameView;
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
        HashMap<String, String> serverGegevens = new HashMap<String, String>();
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

        int gameSize = 3;
        newInt[0] = oldInt / gameSize;
        newInt[1] = oldInt % gameSize;
        return newInt;
    }
    public String getGameType(){
        return gameType;
    }
    public CommandController commandController(){
        return commandController;
    }

    @Override
    public void run() {
        start();
    }
}
