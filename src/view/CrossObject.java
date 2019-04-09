package view;

import javax.swing.*;
import java.awt.*;

public class CrossObject implements Icon {
    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        Graphics2D g1 = (Graphics2D) g.create();
        g1.setPaint(Color.BLACK);
        g1.drawLine(1,1,64,64);
        g1.setBackground(Color.black);
        g1.dispose();
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setPaint(Color.BLACK);
        g2.drawLine(64, 1, 1,64);
        g2.setBackground(Color.black);
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
