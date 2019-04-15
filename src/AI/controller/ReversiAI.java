package AI.controller;

import controller.MoveController;
import model.StateHandler;

import java.awt.*;
import java.util.List;
import java.util.Random;

public class ReversiAI implements Runnable{
    private static MoveController moveController;
    private StateHandler stateHandler;
    private static boolean gameStatus;

    public ReversiAI(MoveController moveController, StateHandler stateHandler) {
        this.moveController = moveController;
        this.stateHandler = stateHandler;
        this.gameStatus = true;
    }

    public synchronized static void doRandomMove(){
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
            gameStatus = false; // game is gestopt
        }
    }
//    public void pickMostCommenMove(){
//        char[][] board = moveController.getBoard();
//        char clientSymbol = moveController.getClientSymbol();
//        List<Point> possibleMoves = moveController.getValidMoves(board, clientSymbol);
//
//        int[][] countMoves = new int[64][3];
//
//        for (int i = 0; i < possibleMoves.size(); i++){
//            if (possibleMoves.get(i).x == 5 && possibleMoves.get(i).y == 3){
//                //we hebben een match
//            }
//            for(int j = 0; j < countMoves.length; j++){
//
//            }
//        }
//    }

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
