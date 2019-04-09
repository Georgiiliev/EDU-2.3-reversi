package model;

import connection.ServerConnection;
import controller.CommandHandler;
import view.GameView;

public class ConnectingToServerState implements I_GameState {
    private StateHandler stateHandler;

    public ConnectingToServerState(StateHandler stateHandler) {
        this.stateHandler = stateHandler;
    }

    @Override
    public void endGameDraw() {
        System.out.print("Not connected");
    }

    @Override
    public void endGameLoss() {
        System.out.print("Not connected");
    }

    @Override
    public void endGameWin() {
        System.out.print("Not connected");
    }

    @Override
    public void gameStart() {
        stateHandler.setGameState(stateHandler.getGameStarted());
    }

    @Override
    public void gameIdle() {
        System.out.print("Not connected");
    }

    @Override
    public void moveServer() {
        System.out.print("Not connected");
    }

    @Override
    public void moveClient() {
        System.out.print("Not connected");
    }

    @Override
    public void establishConnection() {
        System.out.println("Connecting to the server...");
        ServerConnection connect = new ServerConnection(stateHandler);

        if (connect.connectionSucceed()) {
            GameView gameView = new GameView(stateHandler,connect);
            CommandHandler commandHandler = new CommandHandler(connect, stateHandler, gameView);

            Thread thread = new Thread(commandHandler);
            thread.start();
        } else {
            System.exit(0);
        }
    }
}
