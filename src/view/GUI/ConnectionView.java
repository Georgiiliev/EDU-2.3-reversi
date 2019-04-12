package view.GUI;

import javax.swing.*;
import java.awt.*;

public class ConnectionView extends JFrame {

    JLabel hostName = new JLabel("Host:");
    JLabel portName = new JLabel("Port:");
    JTextField hostField = new JTextField(30);
    JTextField portField = new JTextField(30);
    JButton submit = new JButton("Submit");

    public ConnectionView() {
        drawView();
    }

    public void drawView(){
        this.setSize(400, 200);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Game Engine - Groep 4");

        JPanel jPanel = new JPanel();

        jPanel.setLayout(new GridBagLayout());

        addComp(jPanel, hostName, 0, 0, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);
        addComp(jPanel, hostField, 1, 0, 2, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
        addComp(jPanel, portName, 0, 1, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);
        addComp(jPanel, portField, 1, 1, 2, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
        addComp(jPanel, submit, 2, 3, 2, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);

        myActionListener();

        this.add(jPanel);
        this.pack();
        this.setVisible(true);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }

    private void myActionListener() {


        submit.addActionListener( (e)-> {
            System.out.println("gedrukt");

        });
    }


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
