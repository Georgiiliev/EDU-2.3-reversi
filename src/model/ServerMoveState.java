package model;

import view.GameView;

public class ServerMoveState implements I_GameState {
    private StateHandler stateHandler;

    public ServerMoveState(StateHandler stateHandler) {
        this.stateHandler = stateHandler;
    }

    @Override
    public void endGameDraw() {
        stateHandler.setGameState(stateHandler.getGameEndedDraw());
    }

    @Override
    public void endGameLoss() {
        stateHandler.setGameState(stateHandler.getGameEndedLoss());
    }

    @Override
    public void endGameWin() {
        stateHandler.setGameState(stateHandler.getGameEndedWin());
    }

    @Override
    public void gameStart(GameView gameView, String gameType) {
        System.out.print("Game already started");
    }

    @Override
    public void gameIdle() {
        System.out.print("Game in progress");
    }

    @Override
    public void moveServer() {
        System.out.print("Already your turn");
    }

    @Override
    public void moveClient() {
        stateHandler.setGameState(stateHandler.getClientMove());
    }

    @Override
    public void establishConnection(String host, int port) {
        System.out.print("Already connected");
    }
}
