package view;

import javax.swing.*;
import java.awt.*;

public class GameView extends JFrame{


    private final JPanel GUI = new JPanel();
    private JButton start = new JButton("Start");
    private JButton stop = new JButton("Stop");
    private JButton gameOne = new JButton("Tic-Tac-Toe");
    private JButton gameTwo = new JButton("Reversi");
    private JTextField input = new JTextField("name",1);
    private DefaultListModel modelConsole = new DefaultListModel();

    public GameView(){
        BoardView boardView = new BoardView(3);
        drawGui(boardView);
    }

    public void drawGui(BoardView b){
        this.setSize(1000, 800);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Board");


        GUI.setLayout(new GridBagLayout());

        drawConsole();
        drawPlayerList();
        drawBox();
        drawTextInput();
        drawTicTacToe(b);

        this.add(GUI);
        this.setVisible(true);
        this.setResizable(false);
    }

    private void drawTicTacToe(BoardView b){
        b.setBoardSize(3);
        addComp(GUI, b, 0, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);
    }

    private void drawReversie(BoardView b) {
        b.setBoardSize(8);
        addComp(GUI, b, 0, 0, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.NONE);
    }

    private void drawConsole() {
        JPanel console = new JPanel();
        JList consoleList = new JList(modelConsole);
        JScrollPane scrollableConsoleList = new JScrollPane(consoleList);
        console.add(scrollableConsoleList);
        consoleList.setFixedCellWidth(600);
        consoleList.setFixedCellHeight(20);
        addComp(GUI, console, 0, 0, 1, 1, GridBagConstraints.SOUTH, GridBagConstraints.NONE);
    }

    private void drawPlayerList() {
        JList list = new JList();
        JScrollPane scrollableList = new JScrollPane(list);
        list.setFixedCellHeight(50);
        list.setFixedCellWidth(190);
        addComp(GUI, scrollableList, 0, 0, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);
    }

    private void drawBox(){
        Box box = Box.createVerticalBox();
        input.setPreferredSize(new Dimension(100, 20));
        box.add(input);
        box.add(Box.createRigidArea(new Dimension(20, 20)));
        box.add(start);
        box.add(Box.createRigidArea(new Dimension(20, 20)));
        box.add(stop);
        box.add(Box.createRigidArea(new Dimension(20, 20)));
        box.add(gameOne);
        box.add(Box.createRigidArea(new Dimension(20, 20)));
        box.add(gameTwo);
        addComp(GUI, box, 0,0,3,1,GridBagConstraints.WEST,GridBagConstraints.NONE);

    }

    private void drawTextInput() {

//        addComp(GUI, input, 0,0,10,1,GridBagConstraints.NORTHWEST,GridBagConstraints.NONE);

//        start.addActionListener( (e)-> {
//            submitAction();
//        });

        buttonActionListener();

    }

    private void buttonActionListener() {
        start.addActionListener( (e)-> {
            System.out.println("Start");
        });

        stop.addActionListener( (e)-> {
            System.out.println("Stop");
        });

        gameOne.addActionListener( (e)-> {
            System.out.println("Tic-Tac-Toe");
        });

        gameTwo.addActionListener( (e)-> {
            System.out.println("Reversi");
        });
    }

//    private void submitAction() {
//        // You can do some validation here before assign the text to the variable
//        String text = input.getText();
//        System.out.println(text);
//        modelConsole.addElement(text);
//
//    }

    // Sets the rules for a component destined for a GridBagLayout
    // and then adds it

    private void addComp(JPanel thePanel, JComponent comp, int xPos, int yPos, int compWidth, int compHeight, int place, int stretch){

        GridBagConstraints gridConstraints = new GridBagConstraints();

        gridConstraints.gridx = xPos;
        gridConstraints.gridy = yPos;
        gridConstraints.gridwidth = compWidth;
        gridConstraints.gridheight = compHeight;
        gridConstraints.weightx = 100;
        gridConstraints.weighty = 100;
        gridConstraints.insets = new Insets(5,5,5,5);
        gridConstraints.anchor = place;
        gridConstraints.fill = stretch;

        thePanel.add(comp, gridConstraints);

    }
}
