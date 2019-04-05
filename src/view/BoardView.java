package view;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Color;

import javax.swing.JPanel;

import controller.CommandController;
import controller.MoveController;
import model.StateHandler;

public class BoardView extends JPanel {

    private final JPanel tiles = new JPanel();
    private MoveController moveController;
    private StateHandler stateHandler;
    private CommandController commandController;
    private GameView gameView;
    private static BoardView boardView;


    public int boardSize; // 8*8
    public JButton[][] button;


    public BoardView(int newBoardSize, StateHandler stateHandler, CommandController commandController, GameView gameView) {
        boardView = this;
        System.out.println("De boardview: " + boardView);
        this.commandController = commandController;
        boardSize = newBoardSize;
        this.stateHandler = stateHandler;
        this.gameView = gameView;

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

//    public int setBoardSizeX() {
//
//        int s = frame.getWidth();
//        int width = s / boardSize;
//        return width;
//    }

    public class MyActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            JButton btn = (JButton) e.getSource();
            int column = (int)btn.getClientProperty("column");
            int row = (int)btn.getClientProperty("row");

            moveController = MoveController.getMoveController();

            System.out.println(moveController);
            if(moveController != null){
                if (moveController.clientmove(row, column)){
                    MovableObjectCircle c = new MovableObjectCircle();
                    btn.setOpaque(false);
                    btn.setBackground(Color.black);
                    btn.setIcon(c);
                    System.out.println("column: " + row + ", row: " + column);

                    commandController.positieOmzetten(gameView.getGameValue(), row, column);
                }
            }
        }
    }
    public static BoardView getBoardView(){
        System.out.println(boardView);
        return boardView;
    }

    public void printIcon(int x, int y, String i) {
        JButton button = this.button[x][y];
        if (i.equals("O")) {
            MovableObjectCircle o = new MovableObjectCircle();
            button.setIcon(o);
        } else if (i.equals("X")) {
            MovableObjectCross n = new MovableObjectCross();
            button.setIcon(n);
        }
    }

}


