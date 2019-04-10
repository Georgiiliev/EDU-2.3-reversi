package view;

import javax.swing.*;
import java.awt.*;

public class CrossObject implements Icon {
    private Color circleColor;


    public CrossObject(Color circleColor) {
        this.circleColor = circleColor;
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        Graphics2D g1 = (Graphics2D) g.create();
        g1.setPaint(circleColor);
        g1.drawLine(1+5,1+5,64-10,64-10);
        g1.setBackground(circleColor);
        g1.dispose();
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setPaint(circleColor);
        g2.drawLine(64-10, 1+5, 1+5,64-10);
        g2.setBackground(circleColor);
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
