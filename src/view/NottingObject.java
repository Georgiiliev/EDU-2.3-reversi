package view;

import javax.swing.*;
import java.awt.*;

public class NottingObject implements Icon {
    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setStroke(new BasicStroke(0));
        g2.drawRect(0,0,0,0);
        g2.dispose();
    }

    @Override
    public int getIconWidth() {
        return 0;
    }

    @Override
    public int getIconHeight() {
        return 0;
    }
}
