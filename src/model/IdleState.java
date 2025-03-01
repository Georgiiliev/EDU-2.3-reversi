package model;

import view.GameView;

public class IdleState implements I_GameState {
    private StateHandler stateHandler;

    public IdleState(StateHandler stateHandler) {
        this.stateHandler = stateHandler;
    }

    @Override
    public void endGameDraw() {
        System.out.print("Game hasn't started");
    }

    @Override
    public void endGameLoss() {
        System.out.print("Game hasn't started");
    }

    @Override
    public void endGameWin() {
        System.out.print("Game hasn't started");
    }

    @Override
    public void gameStart(GameView gameView, String gameType) {
        System.out.print("Game already started");
    }

    @Override
    public void gameIdle() {
        System.out.print("Waiting for a match.");

    }

    @Override
    public void moveServer() {
        stateHandler.setGameState(stateHandler.getServerMove());
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
