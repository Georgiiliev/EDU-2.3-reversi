package controller;

import connection.ServerConnection;

import java.util.Arrays;
import java.util.HashMap;

public class GameController implements Runnable{
    ServerConnection connect;

    public GameController(ServerConnection connect) {
        this.connect = connect;
    }

    public void start() {
        while (true){
            while (connect.hasNext()){
                String receive = connect.receive();
                if (receive.startsWith("OK")){ // Ingevoerde commando is goed gegaan.
                    //Doe niets. Goed!
                }
//            else if (receive.startsWith("SVR GAMELIST")){ // Is niet nodig omdat we dat zelf regelen
//                System.out.println(receive);
//            }
                else if (receive.startsWith("SVR GAME")){
                    receive = receive.substring(9);
                    if (receive.startsWith("MATCH")){ // Er is een match gestart
                        receive = receive.substring(6);
                        HashMap hashMap = convertToHashMap(receive);
                        hashMap.get("GAMETYPE"); // reversi of tic-tac-toe
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
                else if (receive.startsWith("SVR PLAYERLIST")){

                }
                else if (receive.startsWith("ERR")){ // heeft GEEN gevolgen
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
