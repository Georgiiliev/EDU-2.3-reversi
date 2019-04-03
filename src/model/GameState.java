package model;

public interface GameState {
    boolean action();

    void endGameDraw();
    void endGameLoss();
    void endGameWin();

    void gameStart();
    void gameIdle();
    void waitForMoveServer();
    void waitForMoveClient();

    void establishConnection();
}

class GameEndedDraw implements GameState{

    @Override
    public boolean action() {
        return false;
    }

    @Override
    public void endGameDraw() {

    }

    @Override
    public void endGameLoss() {

    }

    @Override
    public void endGameWin() {

    }

    @Override
    public void gameStart() {

    }

    @Override
    public void gameIdle() {

    }

    @Override
    public void waitForMoveServer() {

    }

    @Override
    public void waitForMoveClient() {

    }

    @Override
    public void establishConnection() {

    }
}
class GameEndedWin implements GameState{

    @Override
    public boolean action() {
        return false;
    }

    @Override
    public void endGameDraw() {

    }

    @Override
    public void endGameLoss() {

    }

    @Override
    public void endGameWin() {

    }

    @Override
    public void gameStart() {

    }

    @Override
    public void gameIdle() {

    }

    @Override
    public void waitForMoveServer() {

    }

    @Override
    public void waitForMoveClient() {

    }

    @Override
    public void establishConnection() {

    }
}
class GameEndedLoss implements GameState{

    @Override
    public boolean action() {
        return false;
    }

    @Override
    public void endGameDraw() {

    }

    @Override
    public void endGameLoss() {

    }

    @Override
    public void endGameWin() {

    }

    @Override
    public void gameStart() {

    }

    @Override
    public void gameIdle() {

    }

    @Override
    public void waitForMoveServer() {

    }

    @Override
    public void waitForMoveClient() {

    }

    @Override
    public void establishConnection() {

    }
}
class GameStarted implements GameState{

    @Override
    public boolean action() {
        return false;
    }

    @Override
    public void endGameDraw() {

    }

    @Override
    public void endGameLoss() {

    }

    @Override
    public void endGameWin() {

    }

    @Override
    public void gameStart() {

    }

    @Override
    public void gameIdle() {

    }

    @Override
    public void waitForMoveServer() {

    }

    @Override
    public void waitForMoveClient() {

    }

    @Override
    public void establishConnection() {

    }
}
class ClientMove implements GameState{

    @Override
    public boolean action() {
        return true;
    }

    @Override
    public void endGameDraw() {

    }

    @Override
    public void endGameLoss() {

    }

    @Override
    public void endGameWin() {

    }

    @Override
    public void gameStart() {

    }

    @Override
    public void gameIdle() {

    }

    @Override
    public void waitForMoveServer() {

    }

    @Override
    public void waitForMoveClient() {

    }

    @Override
    public void establishConnection() {

    }
}
class ServerMove implements GameState{

    @Override
    public boolean action() {
        return false;
    }

    @Override
    public void endGameDraw() {

    }

    @Override
    public void endGameLoss() {

    }

    @Override
    public void endGameWin() {

    }

    @Override
    public void gameStart() {

    }

    @Override
    public void gameIdle() {

    }

    @Override
    public void waitForMoveServer() {

    }

    @Override
    public void waitForMoveClient() {

    }

    @Override
    public void establishConnection() {

    }
}
class Idle implements GameState{

    @Override
    public boolean action() {
        return false;
    }

    @Override
    public void endGameDraw() {

    }

    @Override
    public void endGameLoss() {

    }

    @Override
    public void endGameWin() {

    }

    @Override
    public void gameStart() {

    }

    @Override
    public void gameIdle() {

    }

    @Override
    public void waitForMoveServer() {

    }

    @Override
    public void waitForMoveClient() {

    }

    @Override
    public void establishConnection() {

    }
}

class ConnectingToServer implements GameState{

    @Override
    public boolean action() {
        return false;
    }

    @Override
    public void endGameDraw() {

    }

    @Override
    public void endGameLoss() {

    }

    @Override
    public void endGameWin() {

    }

    @Override
    public void gameStart() {

    }

    @Override
    public void gameIdle() {

    }

    @Override
    public void waitForMoveServer() {

    }

    @Override
    public void waitForMoveClient() {

    }

    @Override
    public void establishConnection() {

    }
}