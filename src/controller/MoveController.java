package controller;

import model.GameState;
import model.StateHandler;

import javax.swing.*;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Scanner;
import java.util.TimerTask;

public class MoveController {
    static int interval;
    static Timer timer;
    public static MoveController moveController;
    private StateHandler stateHandler;
    private char[][] board;
    private int size;
    public MoveController(int size, StateHandler stateHandler){
        moveController = this;
        this.size = size;
        this.stateHandler = stateHandler;
        drawBoard();
    }

   public void drawBoard(){
        board = new char[size][size];
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                board[i][j] = '_';
            }
        }
    }

    public static void getTimer() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Input seconds => : ");
        String secs = sc.nextLine();
        int delay = 1000;
        int period = 1000;
        timer = new Timer();
        interval = Integer.parseInt(secs);
        System.out.println(secs);
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                System.out.println(setInterval());
            }
        }, delay, period);
    }

    private static final int setInterval() {
        if (interval == 1)
            timer.cancel();
        return --interval;
    }

    public boolean clientmove(int row, int column){
        if(board[column][row] != '_'){ // check if vakje is leeg
            System.out.println("You can't do this move.");
            return false;
        }

        if (stateHandler.getState() != stateHandler.getClientMove()){
            return false;
        }

        fillCharBoard(row, column);
        return true;
    }

    public boolean serverMove(int row, int column){
        if (stateHandler.getState() == stateHandler.getServerMove()){
            fillCharBoard(row, column);
            return true;
        }
        return false;
    }

    public void reversieCheck(int row, int column){
        for (int i = 0; i < row; i++){
             int newRow = row - 1;
             for (int j = 0; j < column; i++){
                 int newColumn = column -1;

             }
        }
        // check die geldige zetten uitrekend
            // zijn er stenen van tegen partij om me heen? Opslaan nieuwe array.
            // ja? zijn er stenen die
        // functie die de stenen aanpast.
    }

    public void fillCharBoard(int row, int column){
        board[column][row] = 'O';
    }
    public static MoveController getMoveController (){
        return moveController;
    }
}
