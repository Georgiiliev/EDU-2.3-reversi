package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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
                button[i][j].putClientProperty("column", i);
                button[i][j].putClientProperty("row", j);
                button[i][j].addActionListener(new MyActionListener());
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

    public class MyActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton btn = (JButton) e.getSource();
            System.out.println("column " + btn.getClientProperty("column")
                    + ", row " + btn.getClientProperty("row"));
        }
    }
}

