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

    }

    public void endGameLoss() {

    }

    public void endGameWin() {

    }

    public void gameStart() {

    }

    public void gameIdle() {

    }

    public void waitForMoveServer() {

    }

    public void waitForMoveClient() {

    }

    public void establishConnection() {

    }

    public void setGameState(GameState gameState){
        this.gameState = gameState;
    }

    public GameState getState(){
        return gameState;
    }
}
