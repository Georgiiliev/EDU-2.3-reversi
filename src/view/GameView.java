package view;
import javax.swing.*;
import java.awt.*;


public class GameView {
    public JFrame frame = new JFrame("Board");

    private final JPanel UI = new JPanel();
    public JButton start = new JButton("Start");
    public JButton stop = new JButton("Stop");
    public JTextField input = new JTextField("");
    public DefaultListModel modelConsole = new DefaultListModel();

    public JRadioButton gameOne = new JRadioButton("Tic-Tac-Toe");
    public JRadioButton gameTwo = new JRadioButton("Reversi");

    public GameView() {
        BoardView boardView = new BoardView(3);
        setFrameHeight();
        paint(boardView);
    }

    public void setFrameHeight() {

    }

    public void paint(BoardView b){

        //console
        drawConsole();
        drawRadioButtons();
        drawPlayerList();
        drawTextInput();
        frameProperties();

        //buttons
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        //adds boardview to frame
        frame.setSize(1000, 1000);
//        frame.add(b);
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

        buttonActionListener();

        input.setPreferredSize(new Dimension(100, 20));
    }

    private void buttonActionListener() {
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

    private void drawRadioButtons() {
        UI.add(gameOne);
        UI.add(gameTwo);
    }

    private void frameProperties() {

        UI.setMaximumSize(new Dimension(300,600));
        UI.setLayout(new FlowLayout());
        UI.setVisible(true);

        frame.add(UI);
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


