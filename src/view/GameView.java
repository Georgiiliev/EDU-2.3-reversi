package view;

import controller.MoveController;
import model.Board;

import javax.swing.*;
import java.awt.*;

public class GameView {
    public JFrame frame = new JFrame("Board");


    private final JPanel tiles = new JPanel();
    private final JPanel UI = new JPanel();
    private final JPanel console = new JPanel();
    //    public String[] playerList = {"Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 2", "Item 3", "Item 4", "Item 5", "Item 2", "Item 3", "Item 4", "Item 5", "Item 2", "Item 3", "Item 4", "Item 5", "Item 2", "Item 3", "Item 4", "Item 5", "Item 2", "Item 3", "Item 4", "Item 5", "Item 2", "Item 3", "Item 4", "Item 5", "Item 2", "Item 3", "Item 4", "Item 5", "Item 2", "Item 3", "Item 4", "Item 5", "Item 2", "Item 3", "Item 4", "Item 5", "Item 2", "Item 3", "Item 4", "Item 5", "Item 2", "Item 3", "Item 4", "Item 5", "Item 2", "Item 3", "Item 4", "Item 5", "Item 2", "Item 3", "Item 4", "Item 5", "Item 2", "Item 3", "Item 4", "Item 5", "Item 2", "Item 3", "Item 4", "Item 5", "Item 2", "Item 3", "Item 4", "Item 5", "Item 2", "Item 3", "Item 4", "Item 5", "Item 2", "Item 3", "Item 4", "Item 5", "Item 2", "Item 3", "Item 4", "Item 5", "Item 2", "Item 3", "Item 4", "Item 5"};
//    public String[] consoleListData = {"Item 1", "Item 2", "ItemItemItemItemItemItemItemItemItemItemItemItemItemItemItemItemItemItemItemItemItemItemItemItemItemItemItemItemItemItemItemItemItemItemItemItemItem 3", "Item 4", "Item 5", "Item 2", "Item 3", "Item 4", "Item 5", "Item 2", "Item 3", "Item 4", "Item 5", "Item 2", "Item 3", "Item 4", "Item 5", "Item 2", "Item 3", "Item 4", "Item 5", "Item 2", "Item 3", "Item 4", "Item 5", "Item 2", "Item 3", "Item 4", "Item 5", "Item 2", "Item 3", "Item 4", "Item 5", "Item 2", "Item 3", "Item 4", "Item 5", "Item 2", "Item 3", "Item 4", "Item 5", "Item 2", "Item 3", "Item 4", "Item 5", "Item 2", "Item 3", "Item 4", "Item 5", "Item 2", "Item 3", "Item 4", "Item 5", "Item 2", "Item 3", "Item 4", "Item 5", "Item 2", "Item 3", "Item 4", "Item 5", "Item 2", "Item 3", "Item 4", "Item 5", "Item 2", "Item 3", "Item 4", "Item 5", "Item 2", "Item 3", "Item 4", "Item 5", "Item 2", "Item 3", "Item 4", "Item 5", "Item 2", "Item 3", "Item 4", "Item 5", "Item 2", "Item 3", "Item 4", "Item 5"};
    public JButton start = new JButton("Start");
    public JButton stop = new JButton("Stop");
    public JTextField input = new JTextField("input field");
    public DefaultListModel modelConsole = new DefaultListModel();

    public JRadioButton gameOne = new JRadioButton("Tic-Tac-Toe");
    public JRadioButton gameTwo = new JRadioButton("Reversi");

    private MoveController moveController;

    public GameView(){
        BoardView boardView = new BoardView(3);
        paint(boardView);
    }

    public void paint(BoardView b){
        drawConsole();
        drawButtons();
        drawPlayerList();



        UI.setVisible(true);
        UI.setLayout(new GridLayout(3,2));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.add(b);
    }

    public void drawConsole() {
        //console
        JPanel console = new JPanel();
        JList consoleList = new JList(modelConsole);
        JScrollPane scrollableConsoleList = new JScrollPane(consoleList);
        console.add(scrollableConsoleList);
        frame.add(console, BorderLayout.SOUTH);
    }

    public void drawButtons() {
        //buttons
        input.setPreferredSize(new Dimension(100, 20));
        start.addActionListener( (e)-> {
            submitAction();
        });
        UI.setMaximumSize(new Dimension(300,600));
        frame.add(UI, BorderLayout.WEST);
        UI.add(start);
        UI.add(stop);
        UI.add(gameOne);
        UI.add(gameTwo);
        UI.add(input);
    }

    public void drawPlayerList() {
        //lists
        JList list = new JList();
        JScrollPane scrollableList = new JScrollPane(list);
        UI.add(scrollableList);
    }


    private void submitAction() {
        // You can do some validation here before assign the text to the variable
        String text = input.getText();
        System.out.println(text);
        modelConsole.addElement(text);

    }
}
