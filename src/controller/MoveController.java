package controller;

public class MoveController {

    private char[][] board;

    public MoveController(int size){
        board = new char[size][size];
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                board[i][j] = '_';
            }
        }
    }

    public boolean isMoveLegit(int row, int column){
        if(board[column][row] != '_'){ // check if vakje is leeg
            return false;
        }
        // TODO check if state = yourturn
        fillCharBoard(row, column);
        // TODO state = new state(ServerMove);
        return true;
    }

    public void fillCharBoard(int row, int column){
        board[column][row] = 'O';
    }
}
