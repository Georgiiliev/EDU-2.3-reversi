package model;

import view.GameView;

public class StateHandler {
    private I_GameState clientMove;
    private I_GameState serverMove;
    private I_GameState connectingToServer;
    private I_GameState gameEndedDraw;
    private I_GameState gameEndedLoss;
    private I_GameState gameEndedWin;
    private I_GameState gameStarted;
    private I_GameState idle;

    I_GameState gameState;

    public StateHandler(){
        clientMove = new ClientMoveState(this);
        serverMove = new ServerMoveState(this);
        connectingToServer = new ConnectingToServerState(this);
        gameEndedDraw = new GameDrawState(this);
        gameEndedLoss = new GameLoseState(this);
        gameEndedWin = new GameWinState(this);
        gameStarted = new GameStartedState(this);
        idle = new IdleState(this);
    }

    public void endGameDraw() { gameState.endGameDraw(); }
    public void endGameLoss() { gameState.endGameLoss(); }
    public void endGameWin() { gameState.endGameWin(); }
    public void gameStart(GameView gameView, String gameType) {
        gameState.gameStart(gameView, gameType);
    }
    public void gameIdle() { gameState.gameIdle(); }
    public void waitForMoveServer() { gameState.moveServer(); }
    public void waitForMoveClient() { gameState.moveClient(); }
    public void establishConnection() { gameState.establishConnection(); }

    public I_GameState getGameEndedDraw() { return gameEndedDraw; }
    public I_GameState getGameEndedLoss() { return gameEndedLoss; }
    public I_GameState getGameEndedWin() { return gameEndedWin; }
    public I_GameState getClientMove() { return clientMove; }
    public I_GameState getServerMove() { return serverMove;  }
    public I_GameState getConnectingToServer(){ return connectingToServer; }
    public I_GameState getGameStarted(){ return gameStarted; }
    public I_GameState getIdle(){ return idle; }

    public I_GameState getGameState() {
        return gameState;
    }

    public void setGameState(I_GameState gameState) {
        this.gameState = gameState;
    }
}
