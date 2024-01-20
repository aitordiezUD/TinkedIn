package pruebas;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

public class MatchNotificationDialog extends JDialog {

    public MatchNotificationDialog(String nombreUsuario) {
        setTitle("¡Nuevo Match!");
        setSize(300, 150);
        setLocationRelativeTo(null); // Centra el diálogo en la pantalla
        ImageIcon icon = new ImageIcon("TinkedinPNG.png");
        Image iconImage = icon.getImage();
        setIconImage(iconImage);
        
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.white);
        String mensaje = "<html>¡Felicidades! Tienes un nuevo match con:<br>" + nombreUsuario + "</html>";
        JLabel lblMensaje = new JLabel(mensaje);
        lblMensaje.setHorizontalAlignment(SwingConstants.CENTER);

        panel.add(lblMensaje, BorderLayout.CENTER);
        getContentPane().add(panel);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }

}
