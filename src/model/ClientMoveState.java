package model;

import view.GameView;

public class ClientMoveState implements I_GameState {
    private StateHandler stateHandler;
    public ClientMoveState(StateHandler stateHandler) {
        this.stateHandler = stateHandler;
    }

    @Override
    public void endGameDraw() {
        //stateHandler.setGameState(stateHandler.getGameEndedDraw());
    }

    @Override
    public void endGameLoss() {
        //stateHandler.setGameState(stateHandler.getGameEndedLoss());
    }

    @Override
    public void endGameWin() {
        //stateHandler.setGameState(stateHandler.getGameEndedWin());
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
        stateHandler.setGameState(stateHandler.getServerMove());
    }

    @Override
    public void moveClient() {
        System.out.print("Already your turn");
    }

    @Override
    public void establishConnection() {
        System.out.print("Already connected");
    }
}
