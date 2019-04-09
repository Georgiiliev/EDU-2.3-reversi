package model;

public class GameWinState implements I_GameState {
    private StateHandler stateHandler;
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
        System.out.print("Game hasn't started");
    }

    @Override
    public void gameStart() {
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
    public void establishConnection() {
        System.out.print("Already connected");
    }
}
