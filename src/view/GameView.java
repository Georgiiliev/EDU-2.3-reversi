package view;

import javax.swing.*;
import java.awt.*;

public class GameView extends JFrame{


    private final JPanel GUI = new JPanel();
    private JButton submit = new JButton("Submit");
    private JButton gameOne = new JButton("Tic-Tac-Toe");
    private JButton gameTwo = new JButton("Reversi");
    private JTextField input = new JTextField("name",1);
    private DefaultListModel modelConsole = new DefaultListModel();
    private BoardView boardView = new BoardView(1);

    public GameView(){
        drawGui();
    }

    public void drawGui(){
        this.setSize(1000, 800);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Board");


        GUI.setLayout(new GridBagLayout());

        drawConsole();
        drawPlayerList();
        drawBox();
        drawTextInput();
//        drawTicTacToe(boardView);

        this.add(GUI);
        this.setVisible(true);
        this.setResizable(false);
    }

    private void drawTicTacToe(BoardView b){
        addComp(GUI, b, 0, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);
    }

    private void drawReversi(BoardView b) {
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
        box.add(submit);
        box.add(Box.createRigidArea(new Dimension(20, 20)));
        box.add(gameOne);
        box.add(Box.createRigidArea(new Dimension(20, 20)));
        box.add(gameTwo);
        addComp(GUI, box, 0,0,3,1,GridBagConstraints.WEST,GridBagConstraints.NONE);

    }

    private void buttonActionListener() {
        submit.addActionListener( (e)-> {
            System.out.println("submit");
        });


        gameOne.addActionListener( (e)-> {
            System.out.println("Tic-Tac-Toe");
            GUI.remove(boardView);
            drawTicTacToe(boardView = new BoardView(3));
            GUI.revalidate();
            GUI.repaint();

        });

        gameTwo.addActionListener( (e)-> {
            System.out.println("Reversi");
            GUI.remove(boardView);
            drawReversi(boardView = new BoardView(8));
            GUI.revalidate();
            GUI.repaint();
        });
    }


    private void drawTextInput() {

        submit.addActionListener( (e)-> {
            submitAction();
        });

        buttonActionListener();

    }

    private void submitAction() {
        // You can do some validation here before assign the text to the variable
        String text = input.getText();
        System.out.println(text);
        modelConsole.addElement(text);

    }

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
