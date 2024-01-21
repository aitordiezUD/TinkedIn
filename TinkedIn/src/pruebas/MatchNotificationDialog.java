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
import javax.swing.SwingUtilities;

import nube.ImagenesAzure;
import servidor.ServicioPersistencia;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;

public class MatchNotificationDialog extends JDialog {

    public MatchNotificationDialog(String nombreUsuario,String urlImagen) {
    	setTitle("¡Nuevo Match!");
        setSize(300, 180);
        setResizable(false);
        setLocationRelativeTo(null); // Centra el diálogo en la pantalla
        ImageIcon icon = new ImageIcon("TinkedinPNG.png");
        Image iconImage = icon.getImage();
        setIconImage(iconImage);
        
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.white);
        String mensaje = "<html>¡Felicidades! Tienes un nuevo match con:<br>";
        JLabel lblMensaje = new JLabel(mensaje);
        lblMensaje.setHorizontalAlignment(SwingConstants.CENTER);
        lblMensaje.setPreferredSize(new Dimension(300,40));
        panel.add(lblMensaje, BorderLayout.NORTH);
        panel.add(new pnlFotoNombre(nombreUsuario,urlImagen), BorderLayout.CENTER);
        getContentPane().add(panel);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
    private static class pnlFotoNombre extends JPanel{
		private static final long serialVersionUID = 1L;
		public pnlFotoNombre(String nombre, String urlImagen) {
			setLayout(new BorderLayout());
			setBackground(Color.white);
			JPanel pnlFoto = new JPanel();
			pnlFoto.setBackground(Color.WHITE);
			JLabel fotoPerfil = ImagenesAzure.crearImagen(urlImagen, 90, 90);
			pnlFoto.add(fotoPerfil);
			JPanel pnlNombre = new JPanel(new BorderLayout());
			pnlNombre.setBackground(Color.white);
			pnlNombre.setAlignmentY(CENTER_ALIGNMENT);
			JLabel lblNombre = new JLabel(nombre);
			lblNombre.setFont(new Font("Arial", Font.BOLD, 16));
			lblNombre.setVerticalAlignment(SwingConstants.CENTER);
			lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
			pnlNombre.add(lblNombre);
			add(pnlFoto,BorderLayout.WEST);
			add(pnlNombre,BorderLayout.CENTER);
		}
	}
    
    public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			new MatchNotificationDialog("Aitor Diez Mateo", "https://tinkedin.blob.core.windows.net/tinkedinbd/0.jpg");
		});
	}

}
