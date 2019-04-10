package view;

import javax.swing.*;
import java.awt.*;

public class CircleObject implements Icon {
    private String type;
    public Color circleColor;

    public CircleObject(Color circleColor, String type) {
        this.circleColor = circleColor;
        this.type = type;
    }
    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setPaint(circleColor);
        g2.drawOval(x+5, y+5, getIconWidth() - 10, getIconHeight() - 10);
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
