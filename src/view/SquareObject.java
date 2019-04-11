package view;

import javax.swing.*;
import java.awt.*;

public class SquareObject implements Icon {
    private String type;
    public Color circleColor;

    public SquareObject(Color circleColor, String type) {
        this.circleColor = circleColor;
        this.type = type;
    }
    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setStroke(new BasicStroke(3));
        g2.setPaint(circleColor);
        g2.drawRect(x, y, getIconWidth()-1, getIconHeight()-1);

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
