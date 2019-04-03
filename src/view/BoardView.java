package view;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Color;

import javax.swing.JPanel;

import controller.MoveController;

public class BoardView extends JPanel {

    public JFrame frame = new JFrame("Board");


    private final JPanel tiles = new JPanel();
    private final JPanel UI = new JPanel();
    private final JPanel console = new JPanel();
    public JButton start = new JButton("Start");
    public JButton stop = new JButton("Stop");
    public JTextField input = new JTextField("input field");
    public DefaultListModel modelConsole = new DefaultListModel();

    public JRadioButton gameOne = new JRadioButton("Tic-Tac-Toe");
    public JRadioButton gameTwo = new JRadioButton("Reversi");

    private MoveController moveController;


    public int boardSize; // 8*8
    public JButton[][] button;

    public int setBoardSizeX() {

        int s = frame.getWidth();
        int width = s / boardSize;
        return width;
    }



    public BoardView( int newBoardSize) {
        boardSize = newBoardSize;

        this.moveController = new MoveController(boardSize);
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
//                if (button[i][j] == button[3][3] || button[i][j] == button[3][4] || button[i][j] == button[4][3] || button[i][j] == button[4][4]) {
//                    CircleIcon c = new CircleIcon();
//                    b.setBackground(Color.green);
//                    b.setIcon(c);
//                    b.setOpaque(false);
//                }
            }
        }
        add(tiles);
        add(UI);
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


            if (moveController.isMoveLegit(row, column)){
                MovableObjectCircle c = new MovableObjectCircle();
                btn.setOpaque(false);
                btn.setBackground(Color.black);
                btn.setIcon(c);
                System.out.println("column: " + column + ", row: " + row);

            }
        }
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


