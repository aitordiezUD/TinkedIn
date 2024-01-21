package pruebas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SpinnerAnimation extends JFrame {

    private int angle = 0;

    public SpinnerAnimation() {
        setTitle("Spinner Animation");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        SpinnerPanel spinnerPanel = new SpinnerPanel();
        add(spinnerPanel);

        Timer timer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Incrementa el ángulo para simular el giro
                angle = (angle + 5) % 360;
                spinnerPanel.repaint();
            }
        });
        timer.start();
    }

    private class SpinnerPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            int centerX = getWidth() / 2;
            int centerY = getHeight() / 2;

            // Dibuja la ruleta giratoria
            int radius = 50;
            int x = centerX - radius;
            int y = centerY - radius;

            g.setColor(Color.BLUE);
            g.fillArc(x, y, 2 * radius, 2 * radius, angle, 30);

            g.setColor(Color.RED);
            g.fillArc(x, y, 2 * radius, 2 * radius, angle + 120, 30);

            g.setColor(Color.GREEN);
            g.fillArc(x, y, 2 * radius, 2 * radius, angle + 240, 30);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new SpinnerAnimation().setVisible(true);
        });
    }
}
