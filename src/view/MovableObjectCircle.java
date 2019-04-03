package view;

import javax.swing.*;
import java.awt.*;

public class MovableObjectCircle implements Icon {
        @Override
        public void paintIcon(Component c, Graphics g, int x, int y) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setPaint(Color.BLACK);
            g2.drawOval(x, y, getIconWidth() - 1, getIconHeight() - 1);
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
