package model;

import view.GameView;

public class GameLoseState implements I_GameState {
    private StateHandler stateHandler;

    public GameLoseState(StateHandler stateHandler) {
        this.stateHandler = stateHandler;
    }

    @Override
    public void endGameDraw() {
        System.out.print("Game already finished");
    }

    @Override
    public void endGameLoss() {
    }

    @Override
    public void endGameWin() {
        System.out.print("Game already finished");
    }

    @Override
    public void gameStart(GameView gameView, String gameType) { }

    @Override
    public void gameIdle() {
        stateHandler.setGameState(stateHandler.getIdle());
    }

    @Override
    public void moveServer() {
        System.out.print("Game already finished");
    }

    @Override
    public void moveClient() {
        System.out.print("Game already finished");
    }

    @Override
    public void establishConnection() {
        System.out.print("Already connected");
    }
}
