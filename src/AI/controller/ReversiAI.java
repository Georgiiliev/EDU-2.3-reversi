package AI.controller;

import controller.MoveController;
import model.StateHandler;

import java.awt.*;
import java.util.ArrayList;
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
    private void pickMostCommenMove(){
        char[][] board = moveController.getBoard();
        char clientSymbol = moveController.getClientSymbol();
        List<Point> possibleMoves = moveController.getValidMoves(board, clientSymbol);

        int[][] newList = new int[60][3];

        int counter = 0;
        outerloop:
        for (int i = 0; i < possibleMoves.size(); i++){ // alle beschikbare moves doorlopen
            int x = possibleMoves.get(i).x;
            int y = possibleMoves.get(i).y;

            for (int j = 0; j < newList.length; j++){ // alle moves in onze list
                if (newList[j][2] != 0){ // als move niet leeg is dan:
                    if ((x == 0 && x == 0) || (y == 7 || y == 7)){
                        moveController.clientMove(x, y);
                        return;
                    }
                    else if (x == newList[j][0] && y == newList[j][1]){ // als move overeenkomt
                        newList[j][2]++;
                        continue outerloop; // ga naar de volgende possible move
                    }
                }
                else {
                    newList[counter][0] = x;
                    newList[counter][1] = y;
                    newList[counter][2] = 1;
                    counter++;
                    continue outerloop; // ga naar de volgende possible move.
                }
            }
        }
        int[] max = new int[3];
        max[2] = 0;
        for (int i = 0; i < newList.length; i++){
            if(newList[i][2] > max[2]){
                max[0] = newList[i][0];
                max[1] = newList[i][1];
                max[2] = newList[i][2];
            }
        }

        if (max[2] < 2 ){
            doRandomMove();
        }
        else {
            int[][] newList2 = new int[60][3];
            int counter2 = 0;
            for (int j = 0; j < newList.length; j++) { // alle moves in onze list
                if (newList[j][2] == max[2]) {
                    newList2[counter2][0] = newList[j][0];
                    newList2[counter2][1] = newList[j][1];
                    counter++;
                }
            }

            Random randomGenerator = new Random();
            int randomInt = randomGenerator.nextInt(newList2.length);

            moveController.clientMove(newList2[randomInt][0], newList2[randomInt][1]);
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
