package controller;

import connection.ServerConnection;

public class CommandController {
    private ServerConnection serverConnection;

    public CommandController(ServerConnection serverConnection){
        this.serverConnection = serverConnection;
    }

    public void sendCommand(String action, String value){
        serverConnection.send(action, value);
    }

    public void positieOmzetten(String type, int row, int column) {
        int size = 0;
        if(type == "Reversi"){
            size = 8;
        } else if(type == "Tic-tac-toe"){
            size = 3;
        }

        int positie;

        int resultaat = size * column;
        positie = resultaat + row;

        System.out.println("Sending move to server move: " + Integer.toString(positie));
        sendCommand("move", Integer.toString(positie));
    }
}
