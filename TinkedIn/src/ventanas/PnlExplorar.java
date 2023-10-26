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
	private JTextField textField_1;

	/**
	 * Create the panel.
	 */
	public PnlExplorar() {
		setLayout(null);
		
		try {
	        // Carga la imagen original desde el archivo en el paquete "imagenes"
	        InputStream imageStream = PnlPrincipal.class.getResourceAsStream("FotoPerfil.png");
	        BufferedImage originalImage = ImageIO.read(imageStream);

	        // Redimensiona la imagen a un tamaño más pequeño (50x50 pixeles)
	        int width = 100;
	        int height = 100;
	        Image scaledImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);

	        // Convierte la imagen escalada en un BufferedImage
	        BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	        Graphics2D g2d = resizedImage.createGraphics();
	        g2d.drawImage(scaledImage, 0, 0, null);
	        g2d.dispose();
	        
	        JPanel panel = new JPanel();
	        panel.setBounds(29, 16, 656, 196);
	        add(panel);
	        panel.setLayout(null);
	        
			//Crea in JLabel y asigna la imagen
	        JLabel lblGraphics = new JLabel(new ImageIcon(resizedImage));
	        lblGraphics.setBounds(278, 40, 100, 100);
	        panel.add(lblGraphics);
	        lblGraphics.setBackground(new Color(240, 240, 240));
	        
	        JLabel lblNewLabel = new JLabel("Nombre empresa");
	        lblNewLabel.setBounds(257, 150, 142, 36);
	        panel.add(lblNewLabel);
	        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
	        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
	        
	        JPanel panel_1 = new JPanel();
	        panel_1.setLayout(null);
	        panel_1.setBounds(29, 223, 656, 257);
	        add(panel_1);
	        
	        JLabel lblNewLabel_1 = new JLabel("Datos empresas:");
	        lblNewLabel_1.setBounds(65, 67, 184, 14);
	        panel_1.add(lblNewLabel_1);
	        
	        JLabel lblNewLabel_1_1 = new JLabel("Ubicacion empresas:");
	        lblNewLabel_1_1.setBounds(65, 11, 184, 14);
	        panel_1.add(lblNewLabel_1_1);
	        
	        JLabel lblNewLabel_1_2 = new JLabel("Descripcion puesto:");
	        lblNewLabel_1_2.setBounds(364, 11, 168, 14);
	        panel_1.add(lblNewLabel_1_2);
	        
	        JLabel lblNewLabel_1_2_1 = new JLabel("Habilidades imprescindibles:");
	        lblNewLabel_1_2_1.setBounds(364, 99, 253, 14);
	        panel_1.add(lblNewLabel_1_2_1);
	        
	        JComboBox comboBox = new JComboBox();
	        comboBox.setBounds(364, 123, 236, 22);
	        panel_1.add(comboBox);
	        
	        JLabel lblNewLabel_1_2_1_1 = new JLabel("Habilidades prescindibles:");
	        lblNewLabel_1_2_1_1.setBounds(364, 162, 253, 14);
	        panel_1.add(lblNewLabel_1_2_1_1);
	        
	        JComboBox comboBox_1 = new JComboBox();
	        comboBox_1.setBounds(364, 187, 236, 22);
	        panel_1.add(comboBox_1);
	        
	        textField_1 = new JTextField();
	        textField_1.setBounds(65, 36, 253, 20);
	        panel_1.add(textField_1);
	        textField_1.setColumns(10);
	        
	        JScrollPane scrollPane = new JScrollPane();
	        scrollPane.setBounds(65, 94, 253, 126);
	        panel_1.add(scrollPane);
	        
	        JTextArea textArea = new JTextArea();
	        scrollPane.setViewportView(textArea);
	        
	        JScrollPane scrollPane_1 = new JScrollPane();
	        scrollPane_1.setBounds(364, 36, 236, 52);
	        panel_1.add(scrollPane_1);
	        
	        JTextArea textArea_1 = new JTextArea();
	        scrollPane_1.setViewportView(textArea_1);
			
			
	  
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
