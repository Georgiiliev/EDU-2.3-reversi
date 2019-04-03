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
        // TODO check if game = reversi
        // TODO check if state = yourturn
        fillCharBoard(row, column);
        // TODO state = new state(ServerMove);
        return true;
    }

    public void reversieCheck (){
        // check die geldige zetten uitrekend
            // zijn er stenen van tegen partij om me heen? Opslaan nieuwe array.
            // ja? zijn er stenen die
        // functie die de stenen aanpast.
    }

    public void fillCharBoard(int row, int column){
        board[column][row] = 'O';
    }
}
