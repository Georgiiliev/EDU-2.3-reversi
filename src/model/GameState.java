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
    StateHandler stateHandler;
    public GameEndedDraw(StateHandler stateHandler) {
        this.stateHandler = stateHandler;
    }
    @Override
    public boolean action() {
        return false;
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
    public void waitForMoveServer() {
        System.out.print("Game already finished");
    }

    @Override
    public void waitForMoveClient() {
        System.out.print("Game already finished");
    }

    @Override
    public void establishConnection() {
        System.out.print("Already connected");
    }
}
class GameEndedWin implements GameState{
    StateHandler stateHandler;

    public GameEndedWin(StateHandler stateHandler) {
        this.stateHandler = stateHandler;
    }

    @Override
    public boolean action() {
        return false;
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
    public void waitForMoveServer() {
        System.out.print("Game already finished");
    }

    @Override
    public void waitForMoveClient() {
        System.out.print("Game already finished");
    }

    @Override
    public void establishConnection() {
        System.out.print("Already connected");
    }
}
class GameEndedLoss implements GameState{
    StateHandler stateHandler;

    public GameEndedLoss(StateHandler stateHandler) {
        this.stateHandler = stateHandler;
    }

    @Override
    public boolean action() {
        return false;
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
    public void waitForMoveServer() {
        System.out.print("Game already finished");
    }

    @Override
    public void waitForMoveClient() {
        System.out.print("Game already finished");
    }

    @Override
    public void establishConnection() {
        System.out.print("Already connected");
    }
}

class GameStarted implements GameState{
    StateHandler stateHandler;

    public GameStarted(StateHandler stateHandler) {
        this.stateHandler = stateHandler;
    }
    @Override
    public boolean action() {
        return false;
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
        System.out.print("Game is already starting");
    }

    @Override
    public void gameIdle() {
        stateHandler.setGameState(stateHandler.getIdle());
    }

    @Override
    public void waitForMoveServer() {
        System.out.print("Game hasn't started");
    }

    @Override
    public void waitForMoveClient() {
        System.out.print("Game hasn't started");
    }

    @Override
    public void establishConnection() {
        System.out.print("Already connected");
    }
}

class ClientMove implements GameState{
    StateHandler stateHandler;

    public ClientMove(StateHandler stateHandler) {
        this.stateHandler = stateHandler;
    }

    @Override
    public boolean action() {
        return true;
    }

    @Override
    public void endGameDraw() {
        stateHandler.setGameState(stateHandler.getGameEndedDraw());
    }

    @Override
    public void endGameLoss() {
        stateHandler.setGameState(stateHandler.getGameEndedLoss());
    }

    @Override
    public void endGameWin() {
        stateHandler.setGameState(stateHandler.getGameEndedWin());
    }

    @Override
    public void gameStart() {
        System.out.print("Game already started");
    }

    @Override
    public void gameIdle() {
        System.out.print("Game in progress");
    }

    @Override
    public void waitForMoveServer() {
        stateHandler.setGameState(stateHandler.getServerMove());
    }

    @Override
    public void waitForMoveClient() {
        System.out.print("Already your turn");
    }

    @Override
    public void establishConnection() {
        System.out.print("Already connected");
    }
}

class ServerMove implements GameState{
    StateHandler stateHandler;

    public ServerMove(StateHandler stateHandler) {
        this.stateHandler = stateHandler;
    }
    @Override
    public boolean action() {
        return false;
    }

    @Override
    public void endGameDraw() {
        stateHandler.setGameState(stateHandler.getGameEndedDraw());
    }

    @Override
    public void endGameLoss() {
        stateHandler.setGameState(stateHandler.getGameEndedLoss());
    }

    @Override
    public void endGameWin() {
        stateHandler.setGameState(stateHandler.getGameEndedWin());
    }

    @Override
    public void gameStart() {
        System.out.print("Game already started");
    }

    @Override
    public void gameIdle() {
        System.out.print("Game in progress");
    }

    @Override
    public void waitForMoveServer() {
        System.out.print("Already your turn");
    }

    @Override
    public void waitForMoveClient() {
        stateHandler.setGameState(stateHandler.getClientMove());
    }

    @Override
    public void establishConnection() {
        System.out.print("Already connected");
    }
}

class Idle implements GameState{
    StateHandler stateHandler;

    public Idle(StateHandler stateHandler) {
        this.stateHandler = stateHandler;
    }
    @Override
    public boolean action() {

        return false;
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
        System.out.print("Game already started");
    }

    @Override
    public void gameIdle() {
        System.out.print("Already idle");
    }

    @Override
    public void waitForMoveServer() {
        stateHandler.setGameState(stateHandler.getServerMove());
    }

    @Override
    public void waitForMoveClient() {
        stateHandler.setGameState(stateHandler.getClientMove());
    }

    @Override
    public void establishConnection() {
        System.out.print("Already connected");
    }
}

class ConnectingToServer implements GameState{
    StateHandler stateHandler;

    public ConnectingToServer(StateHandler stateHandler) {
        this.stateHandler = stateHandler;
    }
    @Override
    public boolean action() {
        return false;
    }

    @Override
    public void endGameDraw() {
        System.out.print("Not connected");
    }

    @Override
    public void endGameLoss() {
        System.out.print("Not connected");
    }

    @Override
    public void endGameWin() {
        System.out.print("Not connected");
    }

    @Override
    public void gameStart() {
        stateHandler.setGameState(stateHandler.getGameStarted());
    }

    @Override
    public void gameIdle() {
        System.out.print("Not connected");
    }

    @Override
    public void waitForMoveServer() {
        System.out.print("Not connected");
    }

    @Override
    public void waitForMoveClient() {
        System.out.print("Not connected");
    }

    @Override
    public void establishConnection() {
        System.out.print("Already connecting");
    }
}