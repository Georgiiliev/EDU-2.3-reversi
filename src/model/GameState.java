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

    public GameEndedDraw(StateHandler stateHandler) {

    }
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
    public GameEndedWin(StateHandler stateHandler) {

    }
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
    public GameEndedLoss(StateHandler stateHandler) {

    }
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

    public GameStarted(StateHandler stateHandler) {

    }
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

    public ClientMove(StateHandler stateHandler) {

    }

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

    public ServerMove(StateHandler stateHandler) {

    }
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

    public Idle(StateHandler stateHandler) {

    }
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

    public ConnectingToServer(StateHandler stateHandler) {

    }
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