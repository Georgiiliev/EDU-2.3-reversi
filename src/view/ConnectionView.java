package view;

import model.StateHandler;
import view.GUI.GhostText;

import javax.swing.*;
import java.awt.*;

public class ConnectionView extends JFrame {

    JLabel hostName = new JLabel("Host:");
    JLabel portName = new JLabel("Port:");
    JTextField hostField = new JTextField(30);
    JTextField portField = new JTextField(30);
    JButton submit = new JButton("Submit");
    private StateHandler stateHandler;

    public ConnectionView(StateHandler stateHandler) {
        this.stateHandler = stateHandler;
        drawView();
    }

    public void drawView(){
        this.setSize(400, 200);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Game Engine - Groep 4");

        JPanel jPanel = new JPanel();

        jPanel.setLayout(new GridBagLayout());

        hostName.setFont(new Font("Verdana", Font.BOLD, 14));
        portName.setFont(new Font("Verdana", Font.BOLD, 14));

        new GhostText(hostField,"localhost");
        new GhostText(portField,"7789");

        submit.setBackground(new Color(59, 89, 182));
        submit.setForeground(Color.WHITE);
        submit.setFocusPainted(false);
        submit.setFont(new Font("Verdana", Font.BOLD, 14));

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
            String port = portField.getText();
            String host = hostField.getText();

            stateHandler.setGameState(stateHandler.getConnectingToServer());
            stateHandler.establishConnection(host, Integer.parseInt(port));
            this.dispose();
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
