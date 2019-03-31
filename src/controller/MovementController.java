package controller;

import connection.ServerConnection;

import java.util.Arrays;
import java.util.HashMap;

public class MovementController {
    ServerConnection connect;

    public MovementController(ServerConnection connect) {
        this.connect = connect;
    }

    public void start() {
        connect.receive();
        while (true){
            String receive = connect.receive();
            if (receive.startsWith("OK")){ // Ingevoerde commando is goed gegaan.

            }
            else if (receive.startsWith("SVR GAMELIST")){

            }
            else if (receive.startsWith("SVR GAME")){
                receive = receive.substring(9);
                if (receive.startsWith("MATCH")){ // Er is een match gestart
                    receive = receive.substring(15);
                }
                else if(receive.startsWith("MOVE")){ // move is gezet door 1 van bijde spelers.
                    System.out.println(receive.substring(14));
                }
                else if(receive.startsWith("YOURTURN")){

                }
                else if(receive.startsWith("LOSS")){

                }
                else if(receive.startsWith("WIN")){

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
    public HashMap convertToHashMap(String hashMap){
        hashMap.replaceAll("(\\{|}|\")",""); // verwijdert rare items
        String[] nieuwMap = hashMap.split("[,|:]");
        Arrays.asList(nieuwMap);
        HashMap serverGegevens = new HashMap();
        for (int i = 0; i < nieuwMap.length; i++){
            serverGegevens.put(nieuwMap[i].replaceAll(" ",""), nieuwMap[++i].substring(1));
        }
        return serverGegevens;
    }
    public static void main(String[] args){
    }
}
