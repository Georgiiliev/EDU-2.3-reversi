package model;

import view.GameView;

public class GameStartedState implements I_GameState {
    private StateHandler stateHandler;
    private GameView gameView;
    private String gameType;

    public GameStartedState(StateHandler stateHandler) {
        this.stateHandler = stateHandler;
    }

    @Override
    public void endGameDraw() {
        System.out.print("Game already finished");
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
        if(gameType.equals("Reversi")){
            gameView.drawReversi();
        } else if(gameType.equals("Tic-tac-Toe")){
            gameView.drawTicTacToe();
        }
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
    public void establishConnection() {
        System.out.print("Already connected");
    }

    public void setGameView(GameView gameView){
        this.gameView = gameView;
    }

    public void setGameType(String gameType){
        this.gameType = gameType;
    }
}
