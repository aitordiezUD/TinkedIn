package ventanas;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.*;


public class PnlMiPerfil  extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	public PnlMiPerfil() {
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(6, 233, 615, 211);
		add(panel);
		
		JPanel pnlFotoPerfil = new JPanel();
		pnlFotoPerfil.setBounds(6, 6, 615, 215);
		add(pnlFotoPerfil);
		pnlFotoPerfil.setLayout(null);
		
		
		
		try {
	        // Carga la imagen original desde el archivo en el paquete "imagenes"
	        InputStream imageStream = PnlPrincipal.class.getResourceAsStream("FotoPerfil.png");
	        BufferedImage originalImage = ImageIO.read(imageStream);

	        // Redimensiona la imagen a un tamaño más pequeño (50x50 pixeles)
	        int width = 70;
	        int height = 70;
	        Image scaledImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);

	        // Convierte la imagen escalada en un BufferedImage
	        BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	        Graphics2D g2d = resizedImage.createGraphics();
	        g2d.drawImage(scaledImage, 0, 0, null);
	        g2d.dispose();
			
			
			
	  
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
		
		JLabel lblNewLabel = new JLabel("Correo Electrónico");
		lblNewLabel.setBounds(50, 88, 94, 16);
		pnlFotoPerfil.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(48, 105, 96, 20);
		pnlFotoPerfil.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("NºTeléfono");
		lblNewLabel_1.setBounds(50, 136, 94, 14);
		pnlFotoPerfil.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(48, 157, 96, 20);
		pnlFotoPerfil.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Curriculum");
		lblNewLabel_2.setBounds(287, 88, 61, 16);
		pnlFotoPerfil.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setBounds(266, 108, 48, 14);
		pnlFotoPerfil.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Destreza:");
		lblNewLabel_4.setBounds(191, 108, 48, 14);
		pnlFotoPerfil.add(lblNewLabel_4);
		
		textField_2 = new JTextField();
		textField_2.setBounds(311, 105, 96, 20);
		pnlFotoPerfil.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Experiencia:");
		lblNewLabel_5.setBounds(191, 136, 61, 14);
		pnlFotoPerfil.add(lblNewLabel_5);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(311, 132, 96, 22);
		pnlFotoPerfil.add(comboBox);
		
		JLabel lblNewLabel_6 = new JLabel("Recursos Adicionales: ");
		lblNewLabel_6.setBounds(191, 171, 123, 16);
		pnlFotoPerfil.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("(Linkedln, Github,...)");
		lblNewLabel_7.setBounds(191, 189, 109, 14);
		pnlFotoPerfil.add(lblNewLabel_7);
		
		textField_3 = new JTextField();
		textField_3.setBounds(311, 165, 96, 20);
		pnlFotoPerfil.add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnNewButton = new JButton("Editar");
		btnNewButton.setBounds(356, 192, 89, 23);
		pnlFotoPerfil.add(btnNewButton);
		
		JLabel lblNewLabel_8 = new JLabel("Nombre");
		lblNewLabel_8.setBounds(127, 63, 48, 14);
		pnlFotoPerfil.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("Apellidos");
		lblNewLabel_9.setBounds(252, 63, 48, 14);
		pnlFotoPerfil.add(lblNewLabel_9);
	
		
		//TODO: Arreglar
	
//		lblEditarFoto.addMouseListener( new MouseAdapter() {			
//		@Override
//		public void mouseClicked(MouseEvent e) {
//			// TODO Auto-generated method stub
//			lblEditarFoto.setBackground(new Color(208, 235, 242));
//		}
//		@Override
//		public void mouseEntered(MouseEvent e) {
//			// TODO Auto-generated method stub
//			lblEditarFoto.setBackground(new Color(122, 199, 218));
//		}
//		
//	});
//		
}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(900, 650);
		frame.getContentPane().add(new PnlMiPerfil());
		frame.setVisible(true);
		
	}
}
	

