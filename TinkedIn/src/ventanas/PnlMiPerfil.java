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
import java.awt.Font;


public class PnlMiPerfil  extends JPanel {
	public PnlMiPerfil() {
		setLayout(null);
		setSize(750,650);
		
		JPanel panelFotoPerfil = new JPanel();
		panelFotoPerfil.setBounds(70, 29, 680, 305);
		add(panelFotoPerfil);
		panelFotoPerfil.setLayout(null);
		
		JLabel lblNewLabel_9 = new JLabel("Apellidos");
		lblNewLabel_9.setBounds(400, 250, 98, 14);
		panelFotoPerfil.add(lblNewLabel_9);
		
		JLabel lblNewLabel_8 = new JLabel("Nombre");
		lblNewLabel_8.setBounds(230, 250, 50, 14);
		panelFotoPerfil.add(lblNewLabel_8);
		
		
		JPanel pnlDatos = new JPanel();
		pnlDatos.setBounds(70, 358, 680, 215);
		add(pnlDatos);
		pnlDatos.setLayout(null);
		
		
		
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
	        
			//Crea in JLabel y asigna la imagen
	        JLabel lblGraphics = new JLabel(new ImageIcon(resizedImage));
	        lblGraphics.setBackground(new Color(240, 240, 240));
    		lblGraphics.setBounds(290, 91, 100, 100);
	        panelFotoPerfil.add(lblGraphics);
			
			
	  
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
		
		
		JLabel lblNewLabel = new JLabel("Correo Electrónico");
		lblNewLabel.setBounds(40, 52, 94, 16);
		pnlDatos.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("NºTeléfono");
		lblNewLabel_1.setBounds(40, 91, 94, 14);
		pnlDatos.add(lblNewLabel_1);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setBounds(266, 108, 48, 14);
		pnlDatos.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Destreza:");
		lblNewLabel_4.setBounds(369, 53, 70, 14);
		pnlDatos.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Experiencia:");
		lblNewLabel_5.setBounds(369, 91, 96, 14);
		pnlDatos.add(lblNewLabel_5);
		
		JComboBox comboBoxExperiencia = new JComboBox<String>();
		comboBoxExperiencia.setBounds(489, 87, 191, 22);
		pnlDatos.add(comboBoxExperiencia);
		
		JLabel lblNewLabel_6 = new JLabel("Recursos Adicionales: ");
		lblNewLabel_6.setBounds(369, 135, 150, 14);
		pnlDatos.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("(Linkedln, Github,...)");
		lblNewLabel_7.setBounds(368, 149, 140, 14);
		pnlDatos.add(lblNewLabel_7);
		
		JButton btnNewButton = new JButton("Editar");
		btnNewButton.setBounds(581, 185, 89, 23);
		pnlDatos.add(btnNewButton);
		
		JLabel lblFuenteContacto = new JLabel("Contacto:");
		lblFuenteContacto.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblFuenteContacto.setBounds(101, 15, 124, 14);
		pnlDatos.add(lblFuenteContacto);
		
		JLabel lblFuenteCurriculum = new JLabel("Curriculum:");
		lblFuenteCurriculum.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblFuenteCurriculum.setBounds(438, 11, 201, 22);
		pnlDatos.add(lblFuenteCurriculum);
		
		JLabel lblCorreoElectronico = new JLabel("*****@****");
		lblCorreoElectronico.setBounds(173, 53, 141, 14);
		pnlDatos.add(lblCorreoElectronico);
		
		JLabel lblTelefono = new JLabel("000000000");
		lblTelefono.setBounds(173, 91, 94, 14);
		pnlDatos.add(lblTelefono);
		
		JLabel lblRecursos = new JLabel(".................");
		lblRecursos.setBounds(489, 135, 48, 14);
		pnlDatos.add(lblRecursos);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(489, 49, 191, 22);
		pnlDatos.add(comboBox);
	
		
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
	

