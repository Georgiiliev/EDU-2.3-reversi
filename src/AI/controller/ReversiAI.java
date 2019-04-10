package AI.controller;

import controller.MoveController;
import model.StateHandler;

import java.awt.*;
import java.util.List;
import java.util.Random;

public class ReversiAI implements Runnable{
    private boolean statusAI;
    private MoveController moveController;
    private StateHandler stateHandler;

    public ReversiAI(MoveController moveController, StateHandler stateHandler) {
        statusAI = false;
        this.moveController = moveController;
        this.stateHandler = stateHandler;
    }

    private void doRandomMove(){
        char[][] board = moveController.getBoard();
        char clientSymbol = moveController.getClientSymbol();

        List<Point> possibleMoves = moveController.getValidMoves(board, clientSymbol);

        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(possibleMoves.size());
        System.out.println(randomInt);

        int row = possibleMoves.get(randomInt).x;
        int column = possibleMoves.get(randomInt).y;

        moveController.clientMove(row, column);
    }

    public void disableAI(){
        statusAI = false;
    }

    @Override
    public void run() {
        statusAI = true;
        while (statusAI && (stateHandler.getGameState() == stateHandler.getClientMove() || stateHandler.getGameState() != stateHandler.getServerMove())){
            if (stateHandler.getGameState() == stateHandler.getClientMove()){
                doRandomMove();
                try {
                    Thread.sleep(1000);
                }
                catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
