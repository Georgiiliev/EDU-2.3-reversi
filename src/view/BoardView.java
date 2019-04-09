package view;

import controller.MoveController;
import model.StateHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoardView extends JPanel {

    private final JPanel tiles = new JPanel();
    private MoveController moveController;
    private StateHandler stateHandler;
    private GameView gameView;
    private BoardView boardView;


    public int boardSize; // 8*8
    public JButton[][] button;


    public BoardView(int newBoardSize, StateHandler stateHandler, GameView gameView) {
        this.boardView = this;
        this.stateHandler = stateHandler;
        this.gameView = gameView;

        boardSize = newBoardSize;
        drawBoardView();
    }

    public void drawBoardView(){
        this.button = new JButton[boardSize][boardSize];
        Dimension dims = new Dimension(64, 64);
        tiles.setLayout(new GridLayout(boardSize, boardSize));

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                JButton b = new JButton();
                b.setPreferredSize(dims);
                b.setMinimumSize(dims);
                b.setBorder(BorderFactory.createLineBorder(Color.black));
                b.setBackground(Color.green);
                b.setOpaque(true);
                button[i][j] = b;
                button[i][j].putClientProperty("column", i);
                button[i][j].putClientProperty("row", j);
                button[i][j].addActionListener(new MyActionListener());
                tiles.add(button[i][j]);
            }
        }
        add(tiles);
    }


    public int getBoardSize(){
        return boardSize;
    }

    public void setBoardSize(int size){
        boardSize = size;
    }

    public class MyActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            JButton btn = (JButton) e.getSource();
            int column = (int)btn.getClientProperty("column");
            int row = (int)btn.getClientProperty("row");

            moveController = MoveController.getMoveController();

            if(moveController != null){
                if (moveController.clientMove(row, column)){
//                    MovableObjectCircle c = new MovableObjectCircle();
//                    btn.setOpaque(false);
//                    btn.setBackground(Color.black);
//                    btn.setIcon(c);
//                    System.out.println("column: " + row + ", row: " + column);

                    positieOmzetten(gameView.getGameValue(), row, column);
                }
            }
        }
    }

    public void positieOmzetten(String type, int row, int column) {
        int size = 0;
        if(type == "Reversi"){
            size = 8;
        } else if(type == "Tic-tac-toe"){
            size = 3;
        }

        int positie;

        int resultaat = size * column;
        positie = resultaat + row;

        gameView.sendCommand("move", Integer.toString(positie));
    }
    public BoardView getBoardView(){
        return boardView;
    }

    public void printIcon(int x, int y, String i) {
        JButton button = this.button[x][y];
        if (i.equals("O")) {
            button.setIcon(new CircleObject());
        } else if (i.equals("X")) {
            button.setIcon(new CrossObject());
            //TODO geeft soms null pointer exeption?
        }
    }
}
