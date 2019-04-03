package controller;

import model.GameState;

public class StateHandler implements GameState {

    final static int ClientMove = 0;
    final static int ServerMove = 1;

    final static int GameStarted = 3;
    final static int Idle = 4;

    final static int GameEndedDraw = 5;
    final static int GameEndedLoss = 6;
    final static int GameEndedWin = 7;

    int state;

    public StateHandler(int state){
        this.state = state;
    }

    @Override
    public boolean action() {
        return false;
    }

    @Override
    public void endGameDraw() {
        switch (state){
            case 0:
                System.out.println("");
                break;
            case 1:
                System.out.println();
                break;
            case 2:
                System.out.println();
                break;
            case 3:
                System.out.println();
                break;
            case 4:
                System.out.println();
                break;
            case 5:
                System.out.println();
                break;
            case 6:
                System.out.println();
                break;
            case 7:
                System.out.println();
                break;
        }
    }

    @Override
    public void endGameLoss() {
        switch (state){
            case 0:
                System.out.println();
                break;
            case 1:
                System.out.println();
                break;
            case 2:
                System.out.println();
                break;
            case 3:
                System.out.println();
                break;
            case 4:
                System.out.println();
                break;
            case 5:
                System.out.println();
                break;
            case 6:
                System.out.println();
                break;
            case 7:
                System.out.println();
                break;
        }
    }

    @Override
    public void endGameWin() {
        switch (state){
            case 0:
                System.out.println();
                break;
            case 1:
                System.out.println();
                break;
            case 2:
                System.out.println();
                break;
            case 3:
                System.out.println();
                break;
            case 4:
                System.out.println();
                break;
            case 5:
                System.out.println();
                break;
            case 6:
                System.out.println();
                break;
            case 7:
                System.out.println();
                break;
        }
    }

    @Override
    public void gameStart() {
        switch (state){
            case 0:
                System.out.println();
                break;
            case 1:
                System.out.println();
                break;
            case 2:
                System.out.println();
                break;
            case 3:
                System.out.println();
                break;
            case 4:
                System.out.println();
                break;
            case 5:
                System.out.println();
                break;
            case 6:
                System.out.println();
                break;
            case 7:
                System.out.println();
                break;
        }
    }

    @Override
    public void gameIdle() {
        switch (state){
            case 0:
                System.out.println();
                break;
            case 1:
                System.out.println();
                break;
            case 2:
                System.out.println();
                break;
            case 3:
                System.out.println();
                break;
            case 4:
                System.out.println();
                break;
            case 5:
                System.out.println();
                break;
            case 6:
                System.out.println();
                break;
            case 7:
                System.out.println();
                break;
        }
    }

    @Override
    public void waitForMoveServer() {
        switch (state){
            case 0:
                System.out.println();
                break;
            case 1:
                System.out.println();
                break;
            case 2:
                System.out.println();
                break;
            case 3:
                System.out.println();
                break;
            case 4:
                System.out.println();
                break;
            case 5:
                System.out.println();
                break;
            case 6:
                System.out.println();
                break;
            case 7:
                System.out.println();
                break;
        }
    }

    @Override
    public void waitForMoveClient() {
        switch (state){
            case 0:
                System.out.println();
                break;
            case 1:
                System.out.println();
                break;
            case 2:
                System.out.println();
                break;
            case 3:
                System.out.println();
                break;
            case 4:
                System.out.println();
                break;
            case 5:
                System.out.println();
                break;
            case 6:
                System.out.println();
                break;
            case 7:
                System.out.println();
                break;
        }
    }

    @Override
    public void establishConnection() {
        switch (state){
            case 0:
                System.out.println();
                break;
            case 1:
                System.out.println();
                break;
            case 2:
                System.out.println();
                break;
            case 3:
                System.out.println();
                break;
            case 4:
                System.out.println();
                break;
            case 5:
                System.out.println();
                break;
            case 6:
                System.out.println();
                break;
            case 7:
                System.out.println();
                break;
        }
    }
}
