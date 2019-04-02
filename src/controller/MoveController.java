package controller;

public class MoveController {

    private final char[][] board;

    public MoveController(){
        board = new char[][]{
                {'_','_','_'},
                {'_','_','_'},
                {'_','_','_'},
        };
    }

    public boolean isMoveLegit(int row, int column){
        if(board[column][row] != '_'){
            return false;
        }
        return true;
    }

}
