package controller;
import model.StateHandler;
import view.BoardView;
import view.GameView;

import java.util.Timer;
import java.util.TimerTask;

public class MoveController {
    static int interval;
    static Timer timer;
    public static MoveController moveController;
    private StateHandler stateHandler;
    private char[][] board;
    private int size;
    private BoardView boardView;
    private char clientSymbol;
    private char serverSymbol;
    private static GameView gameView;

    public MoveController(int size, StateHandler stateHandler, boolean firstToStart, GameView gameView){
        this.moveController = this;
        this.gameView = gameView;
        this.size = size;
        this.stateHandler = stateHandler;
        drawBoard();
        setSymbol(firstToStart);

        if (size == 8){ // Als het spel reversi is dan:
            drawMiddle();
        }
    }
    private void setSymbol (boolean firstToStart){
        if (firstToStart){
            clientSymbol = 'O';
            serverSymbol = 'X';
        }
        else {
            clientSymbol = 'X';
            serverSymbol = 'O';
        }
    }

    private void drawMiddle(){
//        try {
//            Thread.sleep(100);
//        }
//        catch(InterruptedException e){
//            e.printStackTrace();
//        }
        fillCharBoard(3, 3, 'X');
        fillCharBoard(4, 4, 'X');
        fillCharBoard(3, 4, 'O');
        fillCharBoard(4, 3, 'O');

    }

    public void drawBoard(){
        board = new char[size][size];
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                board[i][j] = '_';
            }
        }
    }

    public static void setTimer(int time) {
        int delay = 1000;
        int period = 1000;
        timer = new Timer();
        interval = time;
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                System.out.println(setInterval());
            }
        }, delay, period);
    }

    private static final int setInterval() {
        if (interval == 1) {
            timer.cancel();
            MoveController.setTimer(6);
            if (moveController != null){
                gameView.sendCommand("get","playerlist");
            }

        }
        return --interval;
    }

    public boolean clientmove(int row, int column){
        this.boardView = gameView.getBoardView();
        if(board[column][row] != '_'){ // check if vakje is leeg
            return false;
        }

        if (stateHandler.getGameState() != stateHandler.getClientMove()){
            return false;
        }
        fillCharBoard(row, column, clientSymbol);
        return true;
    }

    public boolean serverMove(int row, int column){
        this.boardView = gameView.getBoardView();
        if (stateHandler.getGameState() == stateHandler.getServerMove()){
            boardView.printIcon(row, column, "X");
            fillCharBoard(column, row, serverSymbol);
            return true;
        }
        return false;
    }

    public void reversieCheck(int row, int column){

    }

    public void fillCharBoard(int row, int column, char type){
        this.boardView = gameView.getBoardView();
        board[column][row] = type;
        boardView.printIcon(column, row, String.valueOf(type));
    }

    public static MoveController getMoveController (){
        return moveController;
    }
}
