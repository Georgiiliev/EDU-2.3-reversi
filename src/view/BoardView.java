package view;


import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Color;

import javax.swing.JPanel;
import java.awt.Component;
import java.util.ArrayList;
import javax.swing.Icon;

import controller.GameController;
import controller.MoveController;

public class BoardView extends JPanel {

    private final JPanel tiles = new JPanel();
    private final JPanel UI = new JPanel();
    private final JPanel console = new JPanel();
//    public String[] playerList = {"Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 2", "Item 3", "Item 4", "Item 5", "Item 2", "Item 3", "Item 4", "Item 5", "Item 2", "Item 3", "Item 4", "Item 5", "Item 2", "Item 3", "Item 4", "Item 5", "Item 2", "Item 3", "Item 4", "Item 5", "Item 2", "Item 3", "Item 4", "Item 5", "Item 2", "Item 3", "Item 4", "Item 5", "Item 2", "Item 3", "Item 4", "Item 5", "Item 2", "Item 3", "Item 4", "Item 5", "Item 2", "Item 3", "Item 4", "Item 5", "Item 2", "Item 3", "Item 4", "Item 5", "Item 2", "Item 3", "Item 4", "Item 5", "Item 2", "Item 3", "Item 4", "Item 5", "Item 2", "Item 3", "Item 4", "Item 5", "Item 2", "Item 3", "Item 4", "Item 5", "Item 2", "Item 3", "Item 4", "Item 5", "Item 2", "Item 3", "Item 4", "Item 5", "Item 2", "Item 3", "Item 4", "Item 5", "Item 2", "Item 3", "Item 4", "Item 5", "Item 2", "Item 3", "Item 4", "Item 5"};
//    public String[] consoleListData = {"Item 1", "Item 2", "ItemItemItemItemItemItemItemItemItemItemItemItemItemItemItemItemItemItemItemItemItemItemItemItemItemItemItemItemItemItemItemItemItemItemItemItemItem 3", "Item 4", "Item 5", "Item 2", "Item 3", "Item 4", "Item 5", "Item 2", "Item 3", "Item 4", "Item 5", "Item 2", "Item 3", "Item 4", "Item 5", "Item 2", "Item 3", "Item 4", "Item 5", "Item 2", "Item 3", "Item 4", "Item 5", "Item 2", "Item 3", "Item 4", "Item 5", "Item 2", "Item 3", "Item 4", "Item 5", "Item 2", "Item 3", "Item 4", "Item 5", "Item 2", "Item 3", "Item 4", "Item 5", "Item 2", "Item 3", "Item 4", "Item 5", "Item 2", "Item 3", "Item 4", "Item 5", "Item 2", "Item 3", "Item 4", "Item 5", "Item 2", "Item 3", "Item 4", "Item 5", "Item 2", "Item 3", "Item 4", "Item 5", "Item 2", "Item 3", "Item 4", "Item 5", "Item 2", "Item 3", "Item 4", "Item 5", "Item 2", "Item 3", "Item 4", "Item 5", "Item 2", "Item 3", "Item 4", "Item 5", "Item 2", "Item 3", "Item 4", "Item 5", "Item 2", "Item 3", "Item 4", "Item 5"};
    public JButton start = new JButton("Start");
    public JButton stop = new JButton("Stop");
    public JTextField input = new JTextField("input field");
    public DefaultListModel modelConsole = new DefaultListModel();
    private MoveController moveController;


    public int boardSize; // 8*8
    public JButton[][] button;

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

    public void paint(BoardView b){
        JFrame frame = new JFrame("Board");

        //console
        JPanel console = new JPanel();
        JList consoleList = new JList(modelConsole);
        JScrollPane scrollableConsoleList = new JScrollPane(consoleList);
        console.add(scrollableConsoleList);

        frame.add(console, BorderLayout.SOUTH);

        //buttons

        input.setPreferredSize(new Dimension(100, 20));
        start.addActionListener( (e)-> {
            submitAction();
        });
        UI.add(start);
        UI.add(stop);
        UI.add(input);
        UI.setMaximumSize(new Dimension(300,600));
        frame.add(UI, BorderLayout.WEST);

        JList list = new JList();
        JScrollPane scrollableList = new JScrollPane(list);
        UI.add(scrollableList);

        UI.setVisible(true);
        frame.add(b);

        frame.pack();
        UI.setLayout(new FlowLayout());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    private void submitAction() {
        // You can do some validation here before assign the text to the variable
        String text = input.getText();
        System.out.println(text);
        modelConsole.addElement(text);

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
            CircleIcon c = new CircleIcon();
            btn.setOpaque(false);
            btn.setBackground(Color.black);
            btn.setIcon(c);
            int collum = (int)btn.getClientProperty("column");
            int row = (int)btn.getClientProperty("row");

            if (moveController.isMoveLegit(row, collum)){
                // plaats putton
            }

            System.out.println(c);
            System.out.println("column: " + collum + ", row: " + row);
        }
    }

    public void printIcon(int x, int y, String i) {
        JButton button = this.button[x][y];
        if (i.equals("O")) {
            CircleIcon o = new CircleIcon();
            button.setIcon(o);
        } else if (i.equals("X")) {
            CrossIcon n = new CrossIcon();
            button.setIcon(n);
        }
    }
}


class CrossIcon implements Icon {
    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        Graphics2D g1 = (Graphics2D) g.create();
        g1.setPaint(Color.BLACK);
        g1.drawLine(1,1,64,64);
        g1.setBackground(Color.black);
        g1.dispose();
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setPaint(Color.BLACK);
        g2.drawLine(64, 1, 1,64);
        g2.setBackground(Color.black);
        g2.dispose();
    }

    @Override
    public int getIconWidth() {
        return 60;
    }

    @Override
    public int getIconHeight() {
        return 60;
    }
}

class CircleIcon implements Icon {

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setPaint(Color.BLACK);
        g2.drawOval(x, y, getIconWidth() - 1, getIconHeight() - 1);
        g2.setBackground(Color.black);
        g2.dispose();
    }

    @Override
    public int getIconWidth() {
        return 60;
    }

    @Override
    public int getIconHeight() {
        return 60;
    }

}