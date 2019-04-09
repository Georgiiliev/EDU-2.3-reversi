package controller;
import model.StateHandler;
import view.BoardView;
import view.GameView;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MoveController {
    public static MoveController moveController;
    private StateHandler stateHandler;
    private char[][] board;
    private int[][] directions;
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
        setupBoard(size);
        setSymbol(firstToStart);

        if (size == 8) // Als het spel reversi is dan:
            drawMiddle();


    }

    public boolean clientMove(int row, int column){
        this.boardView = gameView.getBoardView();
        if(board[column][row] != '-') // check if vakje is leeg
            return false;
        if (stateHandler.getGameState() != stateHandler.getClientMove())
            return false;
        if (size == 8)
            reversiDoMove(column, row, clientSymbol);
        updateBoard(row, column, clientSymbol);
        printBoard(board);
        return true;
    }

    public boolean serverMove(int row, int column){
        this.boardView = gameView.getBoardView();
        if (stateHandler.getGameState() == stateHandler.getServerMove()){
            boardView.printIcon(row, column, "X");
            updateBoard(column, row, serverSymbol);
            return true;
        }
        return false;
    }

    // Bron: https://www.reddit.com/r/dailyprogrammer/comments/468pvf/20160217_challenge_254_intermediate_finding_legal/
    public void reversiDoMove(int row, int column, char player){
        List<Point> possibleMoves = checkBoard(board, clientSymbol);
        for (int i = 0; i < possibleMoves.size(); i++){
            int r = possibleMoves.get(i).x;
            int c = possibleMoves.get(i).y;
            if (r == row && c == column){ // als geldige move is dan board updaten.
                reversiUpdate(row, column, player, directions[i][0], directions[i][1]);
            }
//            board[r][c] = '*';
//            System.out.println("X=" + possibleMoves.get(i).x + " Y=" + possibleMoves.get(i).y);
        }
    }
    private List<Point> checkBoard(char[][] board, char player) {
        int i = 0;
        directions = new int[32][2];

        List<Point> points = new ArrayList<>();
        int[][] dirs = new int[][] {
                new int[] {0, 1}, new int[] {1, 1}, new int[] {1, 0}, new int[] {1, -1},
                new int[] {0, -1}, new int[] {-1, -1}, new int[] {-1, 0}, new int[] {-1, 1}
        };
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                if (board[r][c] != player)
                    continue;
                for (int[] dir : dirs) {
                    Point p = checkDir(board, player, r, c, dir[0], dir[1]);
                    if (p != null) {
                        points.add(p);
                        directions[i][0] = dir[0];
                        directions[i][1] = dir[1];
                    }
                }
            }
        }
        return points;
    }

    public Point checkDir(char[][] board, char player, int r, int c, int dr, int dc) {
        boolean inProgress = false;
        while (true) {
            r += dr;
            c += dc;
            if (!inBounds(r, c))
                return null;
            if (!inProgress) {
                if (board[r][c] == '-' || board[r][c] == player)
                    return null;
                inProgress = true;
            }
            else {
                if (board[r][c] == player)
                    return null;
                else if (board[r][c] == '-')
                    return new Point(r, c);
            }
        }
    }

    private void reversiUpdate(int row, int column, char player, int rowDir, int colDir){
        int currentRow= row + rowDir;
        int currentCol = column + colDir;

        if (currentRow==8 || currentRow<0 || currentCol==8 || currentCol<0)
        {
            return;
        }
        while (board[currentRow][currentCol]==clientSymbol || board[currentRow][currentCol]==serverSymbol)
        {
            if (board[currentRow][currentCol] == player)
            {
                while(!(row==currentRow && column==currentCol))
                {
                    updateBoard(currentRow, currentCol, player);
                    currentRow=currentRow-rowDir;
                    currentCol=currentCol-colDir;
                }
                break;
            }else
            {
                currentRow=currentRow + rowDir;
                currentCol=currentCol + colDir;
            }
            if (currentRow<0 || currentCol<0 || currentRow==8 || currentCol==8)
            {
                break;
            }
        }
    }

    public boolean inBounds(int row, int col) {
        return row < size && col < size && row >= 0 && col >= 0;
    }

    public void updateBoard(int row, int column, char type){
        board[column][row] = type;                              // update local board
        this.boardView = gameView.getBoardView();               // get current board
        boardView.printIcon(column, row, String.valueOf(type)); // update gameView board
    }

    private void setSymbol (boolean firstToStart){
        if (firstToStart){
            clientSymbol = 'O'; serverSymbol = 'X';
        }  else {
            clientSymbol = 'X';
            serverSymbol = 'O';
        }
    }

    private void drawMiddle(){
        try {
            Thread.sleep(100);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
        updateBoard(3, 3, 'X');
        updateBoard(4, 4, 'X');
        updateBoard(3, 4, 'O');
        updateBoard(4, 3, 'O');
    }

    public void setupBoard(int size){
        board = new char[size][size];
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                board[i][j] = '-';
            }
        }
    }

    public static void printBoard(char[][] board) {
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++)
                System.out.print(board[r][c]);
            System.out.println();
        }
    }

    public static MoveController getMoveController (){
        return moveController;
    }
}
