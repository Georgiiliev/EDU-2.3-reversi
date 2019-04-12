package AI.controller;

import controller.MoveController;
import model.StateHandler;

import java.awt.*;
import java.util.List;
import java.util.Random;

public class ReversiAI implements Runnable{
    private MoveController moveController;
    private StateHandler stateHandler;
    private boolean gameStatus;

    public ReversiAI(MoveController moveController, StateHandler stateHandler) {
        this.moveController = moveController;
        this.stateHandler = stateHandler;
        this.gameStatus = true;
    }

    private synchronized void doRandomMove(){
        char[][] board = moveController.getBoard();
        char clientSymbol = moveController.getClientSymbol();
        List<Point> possibleMoves = moveController.getValidMoves(board, clientSymbol);
//        int i = 0;
//        while (possibleMoves.size() == 0){
//            possibleMoves = moveController.getValidMoves2(board, clientSymbol);
//            i++;
//            System.out.println(i);
//            try {
//                Thread.sleep(10000);
//            } catch(InterruptedException e){
//                e.printStackTrace();
//            }
//        }
//        System.out.printf("%d legal moves for %c%n", possibleMoves.size(), clientSymbol);

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

    @Override
    public void run() {
        try {
            Thread.sleep(200);
        } catch(InterruptedException e){
            e.printStackTrace();
        }
        while (gameStatus ){ // thread moet altijd blijven leven wanneer er mogelijkheden zijn
            try {
                Thread.sleep(50);
            } catch(InterruptedException e){
                e.printStackTrace();
            }
            if (moveController.canWeMove()){
                try {
                    Thread.sleep(10);
                } catch(InterruptedException e){
                    e.printStackTrace();
                }
                if (moveController.getAIStatus()){ // als de AI enabled is dan mag hij dingen doen.
//                try {
//                    Thread.sleep(100);
//                } catch(InterruptedException e){
//                    e.printStackTrace();
//                }
                    if (stateHandler.getGameState() == stateHandler.getClientMove()){
                        doRandomMove();
                    }
                }

            }
        }
    }
}
