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
        if (possibleMoves.size() != 0){
            int randomInt = randomGenerator.nextInt(possibleMoves.size());

            int row = possibleMoves.get(randomInt).x;
            int column = possibleMoves.get(randomInt).y;

            moveController.clientMove(row, column);
        } else {
            statusAI = false;
        }
    }

    public void disableAI(){
        statusAI = false;
    }
    public void enableAI(){
        statusAI = true;
    }

    @Override
    public void run() {
        statusAI = true;
        try {
            Thread.sleep(200);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
        while (true){ // thread moet altijd blijven leven
            while (statusAI){ // als de AI enabled is dan mag hij dingen doen.
                try {
                    Thread.sleep(100);
                }
                catch(InterruptedException e){
                    e.printStackTrace();
                }
                if (stateHandler.getGameState() == stateHandler.getClientMove()){

                    doRandomMove();
                }
            }
        }
    }
}
