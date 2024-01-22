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

import componentes.botonAceptar;
import componentes.botonAnEl;
import nube.ImagenesAzure;
import servidor.ServicioPersistencia;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

public class MatchNotificationDialog extends JDialog {

    public MatchNotificationDialog() {
    	setTitle("Bienvenido!");
        setSize(450, 250);
        setResizable(false);
        setLocationRelativeTo(null); // Centra el diálogo en la pantalla
        ImageIcon icon = new ImageIcon("TinkedinPNG.png");
        Image iconImage = icon.getImage();
        setIconImage(iconImage);
        
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.white);
        String mensaje = "<html>¡Bienvenido a TinkedIn! ¡Gracias por elegirnos!<br>";
        JLabel lblMensaje = new JLabel(mensaje);
        lblMensaje.setHorizontalAlignment(SwingConstants.CENTER);
        lblMensaje.setPreferredSize(new Dimension(300,40));
        panel.add(lblMensaje, BorderLayout.NORTH);
        getContentPane().add(panel);
        
		JPanel pnlLogo = new JPanel(new BorderLayout());
		pnlLogo.setBackground(Color.white);
		pnlLogo.setPreferredSize(new Dimension(190,190));
		
		ImageIcon icono = new ImageIcon("TinkedinPNG.png");
		ImageIcon iconoRedimensionado = new ImageIcon(getScaledImage(icono.getImage(), 150, 150));
		
		JLabel lblLogo = new JLabel(iconoRedimensionado);
		lblLogo.setHorizontalAlignment(JLabel.CENTER);
        lblLogo.setVerticalAlignment(JLabel.TOP);
        pnlLogo.add(lblLogo,BorderLayout.CENTER);
        JPanel p = new JPanel();
        p.setPreferredSize(new Dimension(20,20));
        p.setBackground(Color.WHITE);
        pnlLogo.add(p,BorderLayout.WEST);
        panel.add(pnlLogo,BorderLayout.WEST);
        
        panel.add(new pnlBotones(),BorderLayout.CENTER);
        
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
    
	private Image getScaledImage(Image srcImg, int width, int height) {
        BufferedImage resizedImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resizedImg.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.drawImage(srcImg, 0, 0, width, height, null);
        g2d.dispose();
        return resizedImg;
    }
    
    private static class pnlBotones extends JPanel{
		private static final long serialVersionUID = 1L;
		public pnlBotones() {
			setLayout(new BorderLayout());
			setBackground(Color.WHITE);
	        String mensaje = "<html>¿Qué tipo de usuario quieres crear?<br>";
	        JLabel lblMensaje = new JLabel(mensaje);
	        lblMensaje.setHorizontalAlignment(JLabel.CENTER);
	        lblMensaje.setPreferredSize(new Dimension(50,50));
	        add(lblMensaje, BorderLayout.NORTH);
	        JPanel pnlBtns = new JPanel(new FlowLayout(FlowLayout.CENTER));
	        pnlBtns.setBackground(Color.white);
	        botonAnEl btnEmpresa = new botonAnEl("Empresa");
	        botonAnEl btnPersona = new botonAnEl("Persona");
	        pnlBtns.add(btnEmpresa);
	        pnlBtns.add(btnPersona);
	        add(pnlBtns,BorderLayout.CENTER);
		}
	}
    
    public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			new MatchNotificationDialog();
		});
	}

}
