package controller;

import connection.ServerConnection;
import model.StateHandler;
import view.GameView;

import java.util.Arrays;
import java.util.HashMap;

public class CommandController implements Runnable{
    private ServerConnection connect;
    private StateHandler stateHandler;

    public CommandController(ServerConnection connect) {
        this.connect = connect;
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
//                                stateHandler.setGameState();
                            }
                            if (hashMap.get("GAMETYPE") == "Tic-tac-toe"){
//                                stateHandler.setGameState();
                            }
                            // State is gamestarted
                        }

                        else if(receive.startsWith("MOVE")){ // move is gezet door 1 van bijde spelers.
                            receive = receive.substring(5);
                            HashMap hashMap = stringToHashMap(receive);

                            String name = "player"; //TODO
                            if (!hashMap.get("PLAYER").equals(name)){
                                stateHandler.setGameState(stateHandler.getServerMove());
                            }
                            else{
                                stateHandler.setGameState(stateHandler.getClientMove());
                            }

                            Integer.parseInt((String)hashMap.get("MOVE")); // De speler heeft op X gespeeld

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
    public HashMap stringToHashMap(String hashMap){
        String cleanString = hashMap.replaceAll("(\\{|}|\")",""); // verwijdert rare items
        String[] nieuwMap = cleanString.split("[,|:]");
        Arrays.asList(nieuwMap);
        HashMap<String, String> serverGegevens = new HashMap<String, String>();
        for (int i = 0; i < nieuwMap.length; i++){
            serverGegevens.put(nieuwMap[i].replaceAll(" ",""), nieuwMap[++i].substring(1));
        }
        return serverGegevens;
    }
    public String[] stringToArray(String array){ // void moet array worden
        String ok = array.replaceAll("(\\[|]|\")","");
        String[] nieuweArray = ok.split("[,]");
        for (int i = 1; i < nieuweArray.length; i++){
            nieuweArray[i] = nieuweArray[i].substring(1);
        }
        return nieuweArray;
    }

    @Override
    public void run() {
        start();
    }
}
