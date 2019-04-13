package AI.controller;

import controller.MoveController;
import model.StateHandler;

import java.awt.*;
import java.util.List;
import java.util.Random;

public class ReversiAI implements Runnable{
    private MoveController moveController;
    private StateHandler stateHandler;
    private static boolean gameStatus;

    public ReversiAI(MoveController moveController, StateHandler stateHandler) {
        this.moveController = moveController;
        this.stateHandler = stateHandler;
        this.gameStatus = true;
    }

    private synchronized void doRandomMove(){
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
            this.gameStatus = false; // game is gestopt
        }
    }
    public static void disableThread(){
        gameStatus = false;
    }

    @Override
    public void run() {
        while (gameStatus){ // thread moet altijd blijven leven wanneer er mogelijkheden zijn
            try {
                Thread.sleep(50);
            } catch(InterruptedException e){
                e.printStackTrace();
            }
            if (moveController.getAIStatus()){ // als de AI enabled is dan mag hij dingen doen.
                try {
                    Thread.sleep(1);
                } catch(InterruptedException e){
                    e.printStackTrace();
                }
                if (stateHandler.getGameState() == stateHandler.getClientMove()){
                    doRandomMove();
                }
            }
        }
    }
}
