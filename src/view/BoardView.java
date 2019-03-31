package view;

import javax.swing.*;
import java.awt.*;

public class BoardView extends JPanel {

    private final JPanel tiles = new JPanel();
    public int boardSize = 8; // 8*8

    public BoardView() {
        Dimension dims = new Dimension(64, 64);
        tiles.setLayout(new GridLayout(boardSize, boardSize));
        JButton[][] button = new JButton[boardSize][boardSize];
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                JButton b = new JButton();
                b.setPreferredSize(dims);
                b.setMinimumSize(dims);
                b.setBorder(BorderFactory.createLineBorder(Color.black));
                b.setBackground(Color.GREEN);
                button[i][j] = b;
                tiles.add(button[i][j]);
            }
        }
        add(tiles);
    }

    public void paint(BoardView b){
        JFrame frame = new JFrame("Board");
        frame.add(b);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}

