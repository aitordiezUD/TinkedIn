package pruebas;

import java.awt.*;
import javax.swing.*;

public class MenuIzquierdo {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Menu Izquierdo");
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);

        JPanel cp = new JPanel(new FlowLayout());
        frame.setContentPane(cp);
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.YELLOW);
        panel.setPreferredSize(new Dimension(100, 100));
        panel.setMaximumSize(new Dimension(100,100));

        JLabel label1 = new JLabel("Tinkedin");
        label1.setAlignmentY(JLabel.CENTER);
        panel.add(label1);

        JLabel label2 = new JLabel("Mi Perfil");
        label2.setAlignmentY(JLabel.CENTER);
        panel.add(label2);

        JLabel label3 = new JLabel("Explorar");
        label3.setAlignmentY(JLabel.CENTER);
        panel.add(label3);

        JLabel label4 = new JLabel("Mensajes");
        label4.setAlignmentY(JLabel.CENTER);
        panel.add(label4);

        JLabel label5 = new JLabel("Estadísticas");
        label5.setAlignmentY(JLabel.CENTER);
        panel.add(label5);

        JLabel label6 = new JLabel("Ajustes");
        label6.setAlignmentY(JLabel.CENTER);
        panel.add(label6);
        
        panel.setAlignmentY(JPanel.BOTTOM_ALIGNMENT);
        
        cp.add(panel);

        frame.setVisible(true);
    }
}