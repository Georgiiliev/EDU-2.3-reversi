package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.*;

public class Board extends JPanel {

    private final JPanel[][] tiles = new JPanel[8][8];

    public Board() {
        Dimension dims = new Dimension(64, 64);
        setLayout(new GridLayout(8, 8));
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                JPanel b = new JPanel();
                b.setPreferredSize(dims);
                b.setMinimumSize(dims);
                b.setBorder(BorderFactory.createLineBorder(Color.black));
//                if ((i + j + 1) % 2 == 0) {
//                    b.setBackground(Color.WHITE);
//                } else {
//                    b.setBackground(Color.RED);
//                }
                b.setBackground(Color.GREEN);
                add(b);
                tiles[i][j] = b;
            }
        }
    }

    public void paint(Board b){
        JFrame frame = new JFrame("Board");
        frame.add(b);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}