package model;

public class GameStartedState implements I_GameState {
    private StateHandler stateHandler;

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
    public void gameStart() {
        System.out.print("Game already started");
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
}
