package controller;

import connection.ServerConnection;
import model.StateHandler;

import java.util.*;

import static java.lang.Thread.sleep;

public class StartController {
    ServerConnection connect;
    String host;
    StateHandler stateHandler;

    public StartController(String host, StateHandler stateHandler) {
        this.host = host;
        this.stateHandler = stateHandler;
        startGame();
    }

    public void startGame(){
//        System.out.println("Voer je naam in: ");
//        Scanner reader = new Scanner(System.in);
//        String name = reader.next();

//        System.out.println("Kies een spel dat je wilt spelen: ");
//        System.out.println("    - Reversi (1)");
//        System.out.println("    - Tic-Tac-Toe (2)");

//        int game = 0;
//        int error = 0;
//        while (game != 1 && game != 2 ){
//            if (error == 1){
//                System.out.println("Voer getal 1 of 2 in.");
//            }
//            game = reader.nextInt();
//            error = 1;
//        }

//        reader.close();

        connect = new ServerConnection(host,stateHandler); //zet connectie op

//        connect.send("login", name); //login op de server
//        if (game == 1){
//            connect.send("subscribe", "Reversi");
//        }
//        else if (game == 2){
//            connect.send("subscribe", "Tic-tac-toe");
//        }

        CommandController commandController = new CommandController(connect);
        Thread thread = new Thread(commandController);
        thread.start();
    }
}