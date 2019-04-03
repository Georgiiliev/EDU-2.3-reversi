package view;

import controller.MoveController;

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


    public GameView() {
        BoardView boardView = new BoardView(3);
        paint(boardView);
    }

    public void paint(BoardView b){

        //console
        drawConsole();
        drawRadioButtons();
        drawPlayerList();
        drawTextInput();
        frameProperties();
        //buttons


        frame.add(b);
    }

    private void drawConsole() {
        JPanel console = new JPanel();
        JList consoleList = new JList(modelConsole);
        JScrollPane scrollableConsoleList = new JScrollPane(consoleList);
        console.add(scrollableConsoleList);

        frame.add(console, BorderLayout.SOUTH);
    }

    private void drawPlayerList() {
        JList list = new JList();
        JScrollPane scrollableList = new JScrollPane(list);
        UI.add(scrollableList);
    }

    private void drawTextInput() {
        UI.add(start);
        UI.add(stop);
        UI.add(input);

        start.addActionListener( (e)-> {
            submitAction();
        });

        input.setPreferredSize(new Dimension(100, 20));
    }

    private void drawRadioButtons() {
        UI.add(gameOne);
        UI.add(gameTwo);
    }

    private void frameProperties() {

        UI.setMaximumSize(new Dimension(300,600));
        UI.setLayout(new GridLayout(3,2));
        UI.setVisible(true);

        frame.add(UI, BorderLayout.WEST);
        frame.pack();

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void submitAction() {
        // You can do some validation here before assign the text to the variable
        String text = input.getText();
        System.out.println(text);
        modelConsole.addElement(text);

    }

}


