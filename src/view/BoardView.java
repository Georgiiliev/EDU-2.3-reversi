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
    private int boardSize;


    public JButton[][] button;


    public BoardView(int newBoardSize, StateHandler stateHandler, GameView gameView) {
        this.boardView = this;
        this.stateHandler = stateHandler;
        this.gameView = gameView;
        this.boardSize = newBoardSize;

        drawBoardView(newBoardSize);
    }

    public void drawBoardView(int boardSize){
        this.button = new JButton[boardSize][boardSize];
        Dimension dimension = new Dimension(64, 64);
        tiles.setLayout(new GridLayout(boardSize, boardSize));

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                JButton b = new JButton();
                b.setPreferredSize(dimension);
                b.setMinimumSize(dimension);
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

    public class MyActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            JButton btn = (JButton) e.getSource();
            int row = (int)btn.getClientProperty("column");
            int column = (int)btn.getClientProperty("row");

            moveController = MoveController.getMoveController();

            if(moveController != null){
                moveController.clientMove(row, column);
            }
        }
    }

    public void clearIcon(){
        for (int row = 0; row < 8; row++){
            for (int column = 0; column < 8; column++){
                JButton button = this.button[row][column];
                Icon iconType = button.getIcon();
                if (iconType instanceof SquareObject)
                    button.setIcon(null);
            }
        }
    }
    public void printIcon(int x, int y, String i) {

        JButton button = this.button[x][y];
        if (i.equals("O")) {
            button.setIcon(new CircleObject(Color.black, "empty"));
        } else if (i.equals("X")) {
            button.setIcon(new CrossObject(Color.red));
        } else if (i.equals("OB")) {
            button.setIcon(new CircleObject(Color.black, "fill"));
        } else if (i.equals("OW")) {
            button.setIcon(new CircleObject(Color.white, "fill"));
        } else if (i.equals("PM")) {
            button.setIcon(new SquareObject(Color.red, ""));
        } else if (i.equals("")) {
            button.setIcon(null);}
    }
}
