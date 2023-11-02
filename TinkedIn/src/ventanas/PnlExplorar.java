package ventanas;

import javax.swing.JPanel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.Icon;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.DropMode;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class PnlExplorar extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public PnlExplorar() {
		setBackground(new Color(129, 186, 207));
		setLayout(null);
		
		try {
	        // Carga la imagen original desde el archivo en el paquete "imagenes"
	        InputStream imageStream = PnlBotonera.class.getResourceAsStream("fotoPerfilEjemplo.JPG");
	        BufferedImage originalImage = ImageIO.read(imageStream);

	        // Redimensiona la imagen a un tamaño más pequeño (50x50 pixeles)
	        int width = 100;
	        int height = 100;
	        Image scaledImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);

	        // Convierte la imagen escalada en un BufferedImage
	        BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	        Graphics2D g2d = resizedImage.createGraphics();
	        Shape circle = new Ellipse2D.Float(0, 0, width, height);
	        g2d.setClip(circle);
	        g2d.drawImage(scaledImage, 0, 0, null);
	        g2d.dispose();
	        
	        JPanel pnlDatos = new JPanel();
	        pnlDatos.setBounds(29, 11, 656, 196);
	        add(pnlDatos);
	        pnlDatos.setLayout(null);
	        
			//Crea in JLabel y asigna la imagen
	        JLabel lblFotoUsuario = new JLabel(new ImageIcon(resizedImage));
	        lblFotoUsuario.setBounds(281, 11, 100, 100);
	        pnlDatos.add(lblFotoUsuario);
	        lblFotoUsuario.setBackground(new Color(240, 240, 240));
	        
	        JLabel lblNombreUsuario = new JLabel("Nombre Usuario");
	        lblNombreUsuario.setBounds(256, 122, 142, 36);
	        pnlDatos.add(lblNombreUsuario);
	        lblNombreUsuario.setFont(new Font("Tahoma", Font.PLAIN, 18));
	        lblNombreUsuario.setHorizontalAlignment(SwingConstants.CENTER);
			
			
	  
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
		
	}
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(750, 650);
		frame.getContentPane().add(new PnlExplorar());
		frame.setVisible(true);
		
	}
}
