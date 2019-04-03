package model;

public class StateHandler{
    GameState clientMove;
    GameState serverMove;

    GameState connectingToServer;

    GameState gameEndedDraw;
    GameState gameEndedLoss;
    GameState gameEndedWin;

    GameState gameStarted;
    GameState idle;

    GameState gameState;

    public StateHandler(){
        clientMove = new ClientMove(this);
    }

    public void endGameDraw() {
        gameState.endGameDraw();
    }

    public void endGameLoss() {
        gameState.endGameLoss();
    }

    public void endGameWin() {
        gameState.endGameWin();
    }

    public void gameStart() {
        gameState.gameStart();
    }

    public void gameIdle() {
        gameState.gameIdle();
    }

    public void waitForMoveServer() {
        gameState.waitForMoveServer();
    }

    public void waitForMoveClient() {
        gameState.waitForMoveClient();
    }

    public void establishConnection() {
        gameState.establishConnection();
    }

    public void setGameState(GameState gameState){
        this.gameState = gameState;
    }

    public GameState getState(){
        return gameState;
    }
}
