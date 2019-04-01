package controller;

import connection.ServerConnection;

import java.util.*;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class MovementController {
    ServerConnection connect;
    String host;

    public MovementController(String host) {
        this.host = host;
        startGame();
    }

    public void startGame(){
        System.out.println("Voer je naam in: ");
        Scanner reader = new Scanner(System.in);
        String name = reader.next();

        System.out.println("Kies een spel dat je wilt spelen: ");
        System.out.println("    - Reversi (1)");
        System.out.println("    - Tic-Tac-Toe (2)");

        int game = 0;
        int error = 0;
        while (game != 1 || game != 2 ){
            if (error == 1){
                System.out.println("Voer getal 1 of 2 in.");
            }
            game = reader.nextInt();
            error = 1;
        }

        reader.close();

        connect = new ServerConnection(host); //zet connectie op

//        System.out.println("hallo " + name + ". Welkom bij het spel: " + game +".");
        connect.send("login", name);
        if (game == 1){
            connect.send("subscribe", "Reversi");
        }
        if (game == 2){
            connect.send("subscribe", "Tic-tac-toe");
        }
//        connect.receive();
    }

    public static void main(String[] args){
    }
}