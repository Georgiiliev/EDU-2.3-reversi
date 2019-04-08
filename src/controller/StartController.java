package controller;

import connection.ServerConnection;
import model.StateHandler;

public class StartController {
    private ServerConnection connect;
    private String host;
    private StateHandler stateHandler;

    public StartController(String host, StateHandler stateHandler) {
        this.host = host;
        this.stateHandler = stateHandler;
        startGame();
    }

    public void startGame() {
        connect = new ServerConnection(host, stateHandler); //zet connectie op

        if (connect.connectionSucceed()) {
            CommandHandler commandHandler = new CommandHandler(connect, stateHandler);

            Thread thread = new Thread(commandHandler);
            thread.start();
        }
    }
}