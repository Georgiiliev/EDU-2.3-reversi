package controller;

import connection.ServerConnection;
import model.StateHandler;

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
        connect = new ServerConnection(host,stateHandler); //zet connectie op
        CommandHandler commandHandler = new CommandHandler(connect,stateHandler);
        Thread thread = new Thread(commandHandler);
        thread.start();
    }
}