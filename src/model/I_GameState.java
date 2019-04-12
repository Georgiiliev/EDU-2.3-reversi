package model;

import view.GameView;

public interface I_GameState {
    void endGameDraw();
    void endGameLoss();
    void endGameWin();

    void gameStart(GameView gameView, String gameType);
    void gameIdle();
    void moveServer();
    void moveClient();

    void establishConnection();

}
