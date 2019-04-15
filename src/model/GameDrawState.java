package model;

import AI.controller.ReversiAI;
import view.GameView;

public class GameDrawState implements I_GameState {
    private StateHandler stateHandler;

    public GameDrawState(StateHandler stateHandler) {
        this.stateHandler = stateHandler;
    }

    @Override
    public void endGameDraw() {
        ReversiAI.disableThread();
    }

    @Override
    public void endGameLoss() {
        System.out.print("Game already finished");
    }

    @Override
    public void endGameWin() {
        System.out.print("Game already finished");
    }

    @Override
    public void gameStart(GameView gameView, String gameType) {

    }

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
    public void establishConnection(String host, int port) {
        System.out.print("Already connected");
    }
}
