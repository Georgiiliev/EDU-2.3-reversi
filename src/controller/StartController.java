package controller;

import com.sun.security.ntlm.Server;
import connection.ServerConnection;
import model.StateHandler;

import static java.lang.Thread.sleep;

public class StartController {
    private ServerConnection connect;
    private String host;
    private StateHandler stateHandler;

    public StartController(String host, StateHandler stateHandler) {
        this.host = host;
        this.stateHandler = stateHandler;
        startGame();
    }

    public void startGame(){
        connect = new ServerConnection(host,stateHandler); //zet connectie op

        CommandHandler commandHandler = new CommandHandler(connect, stateHandler);

        Thread thread = new Thread(commandHandler);
        thread.start();
    }
}