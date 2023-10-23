package ventanas;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import clases.Main;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.Font;

public class PnlPrincipal extends JPanel {

	/**
	 * Create the panel.
	 */
	public PnlPrincipal() {
		setLayout(null);
		
		JPanel PnlBotones = new JPanel();
		PnlBotones.setBounds(0, 0, 150, 650);
		PnlBotones.setBackground(new Color(208, 235, 242));
		add(PnlBotones);
		PnlBotones.setLayout(null);
		
		JPanel pnlPerfil = new JPanel();
		pnlPerfil.setBackground(new Color(208, 235, 242));
		pnlPerfil.setBounds(0, 161, 150, 38);
		PnlBotones.add(pnlPerfil);
		pnlPerfil.setLayout(null);
		
		JLabel lblPerfil = new JLabel("Mi Perfil");
		lblPerfil.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblPerfil.setHorizontalAlignment(SwingConstants.CENTER);
		lblPerfil.setBounds(38, 0, 112, 38);
		pnlPerfil.add(lblPerfil);
		
		try {
            // Carga la imagen original desde el archivo en el paquete "imagenes"
            InputStream imageStream = PnlPrincipal.class.getResourceAsStream("perfil.png");
            BufferedImage originalImage = ImageIO.read(imageStream);

            // Redimensiona la imagen a un tamaño más pequeño (50x50 pixeles)
            int width = 30;
            int height = 30;
            Image scaledImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);

            // Convierte la imagen escalada en un BufferedImage
            BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = resizedImage.createGraphics();
            g2d.drawImage(scaledImage, 0, 0, null);
            g2d.dispose();

            // Crea un JLabel y asigna la imagen escalada como ícono
            JLabel lblGraphics = new JLabel(new ImageIcon(resizedImage));
            lblGraphics.setBackground(new Color(240, 240, 240));
    		lblGraphics.setBounds(20, 0, 38, 38);
    		pnlPerfil.add(lblGraphics);
      
        } catch (IOException e) {
            e.printStackTrace();
        }
		
//		Cambio de colores al pasar el ratón por encima
		pnlPerfil.addMouseListener( new MouseAdapter() {			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				pnlPerfil.setBackground(new Color(208, 235, 242));
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				pnlPerfil.setBackground(new Color(122, 199, 218));
			}
		});
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 198, 150, 38);
		PnlBotones.add(panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 235, 150, 38);
		PnlBotones.add(panel_2);
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setBounds(0, 272, 150, 38);
		PnlBotones.add(panel_2_1);
		
		JPanel pnlFuncional = new JPanel();
		pnlFuncional.setBackground(Color.BLACK);
		pnlFuncional.setBounds(150, 0, 750, 650);
		setSize(900, 650);
		add(pnlFuncional);

	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(900, 650);
		frame.getContentPane().add(new PnlPrincipal());
		frame.setVisible(true);
		
	}
}
