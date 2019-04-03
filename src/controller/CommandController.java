package controller;

import connection.ServerConnection;
import model.StateHandler;

import java.util.Arrays;
import java.util.HashMap;

public class CommandController implements Runnable{
    ServerConnection connect;
    StateHandler stateHandler;

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
                            HashMap hashMap = convertToHashMap(receive);

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
                            HashMap hashMap = convertToHashMap(receive);

                            hashMap.get("PLAYER"); // Wij zelf, of tegen partij
                            Integer.parseInt((String)hashMap.get("MOVE")); // De speler heeft op X gespeeld

                        }

                        else if(receive.startsWith("YOURTURN")){
                            // state = doe een zet.

                        }

                        else if(receive.startsWith("LOSS")){
                            // state = game ended loss

                        }

                        else if(receive.startsWith("WIN")){
                            // state = game ended win

                        }
                        else if(receive.startsWith("CHALLENGE")){

                        }

                    }

                    else if (receive.startsWith("PLAYERLIST")){
                        receive = receive.substring(11);
                        HashMap hashMap = convertToHashMap(receive); // werkt wss niet.

                        // stuur naar gui.
                    }

                    else if (receive.startsWith("GAMELIST")){ // Is niet nodig omdat we dat zelf regelen
                        receive = receive.substring(9);
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
    public HashMap convertToHashMap(String hashMap){
        hashMap.replaceAll("(\\{|}|\")",""); // verwijdert rare items
        String[] nieuwMap = hashMap.split("[,|:]");
        Arrays.asList(nieuwMap);
        HashMap<String, String> serverGegevens = new HashMap<String, String>();
        for (int i = 0; i < nieuwMap.length; i++){
            serverGegevens.put(nieuwMap[i].replaceAll(" ",""), nieuwMap[++i].substring(1));
        }
        return serverGegevens;
    }

    @Override
    public void run() {
        start();
    }
}
