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
        serverMove = new ServerMove(this);
        connectingToServer = new ConnectingToServer(this);
        gameEndedDraw = new GameEndedDraw(this);
        gameEndedLoss = new GameEndedLoss(this);
        gameEndedWin = new GameEndedWin(this);
        gameStarted = new GameStarted(this);
        idle = new Idle(this);
    }

    public void endGameDraw() { gameState.endGameDraw(); }
    public void endGameLoss() { gameState.endGameLoss(); }
    public void endGameWin() { gameState.endGameWin(); }
    public void gameStart() { gameState.gameStart(); }
    public void gameIdle() { gameState.gameIdle(); }
    public void waitForMoveServer() { gameState.waitForMoveServer(); }
    public void waitForMoveClient() { gameState.waitForMoveClient(); }
    public void establishConnection() { gameState.establishConnection(); }

    public GameState getGameEndedDraw() { return gameEndedDraw; }
    public GameState getGameEndedLoss() { return gameEndedLoss; }
    public GameState getGameEndedWin() { return gameEndedWin; }
    public GameState getClientMove() { return clientMove; }
    public GameState getServerMove() { return serverMove;  }
    public GameState getConnectingToServer(){ return connectingToServer; }
    public GameState getGameStarted(){ return gameStarted; }
    public GameState getIdle(){ return idle; }

    public void setGameState(GameState gameState){ this.gameState = gameState; }
    public GameState getState(){ return gameState; }
}
