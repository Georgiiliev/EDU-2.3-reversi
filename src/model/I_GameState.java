package model;

public interface I_GameState {
    void endGameDraw();
    void endGameLoss();
    void endGameWin();

    void gameStart();
    void gameIdle();
    void moveServer();
    void moveClient();

    void establishConnection();

}
