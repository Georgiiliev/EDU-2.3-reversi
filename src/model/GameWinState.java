package model;

import AI.controller.ReversiAI;
import view.GameView;

public class GameWinState implements I_GameState {
    private StateHandler stateHandler;
    private GameView gameView;

    public GameWinState(StateHandler stateHandler) {
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
        ReversiAI.disableThread();
    }

    @Override
    public void gameStart(GameView gameView, String gameType) {
        System.out.print("Game is starting...");
    }

    @Override
    public void gameIdle() {
        stateHandler.setGameState(stateHandler.getIdle()); //already idle
    }

    @Override
    public void moveServer() {
        System.out.print("Game hasn't started");
    }

    @Override
    public void moveClient() {
        System.out.print("Game hasn't started");
    }

    @Override
    public void establishConnection(String host, int port) {
        System.out.print("Already connected");
    }

    public void setGameView(GameView gameView){
        this.gameView = gameView;
    }
}
