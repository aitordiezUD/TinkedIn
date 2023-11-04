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
import java.awt.Graphics;


public class PnlMiPerfil  extends JPanel {
	public PnlMiPerfil() {
		setBackground(Color.WHITE);
		setLayout(null);
		setSize(750,650);
		
		JPanel panelFotoPerfil = new JPanel();
		panelFotoPerfil.setBackground(Color.WHITE);
		panelFotoPerfil.setBounds(10, 32, 711, 193);
		add(panelFotoPerfil);
		panelFotoPerfil.setLayout(null);
		
		JLabel lblNombreApellidos = new JLabel("Nombre");
		lblNombreApellidos.setBackground(Color.WHITE);
		lblNombreApellidos.setBounds(350, 122, 50, 14);
		panelFotoPerfil.add(lblNombreApellidos);
		
		
		JPanel pnlDatos = new JPanel(  ) {

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
		    	
		    	g.setColor(Color.BLACK);
		    	g.drawLine(  355,10,355,366 );
			}
			
		};
		pnlDatos.setBackground(new Color(189, 147, 183));
		pnlDatos.setBounds(10, 236, 711, 376);
		add(pnlDatos);
		pnlDatos.setLayout(null);
		
		
		
		try {
	        // Carga la imagen original desde el archivo en el paquete "imagenes"
	        InputStream imageStream = PnlBotonera.class.getResourceAsStream("FotoPerfil.png");
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
	        
	        JPanel pnlImagen = new JPanel();
	        pnlImagen.setBackground(Color.CYAN);
	        pnlImagen.setBounds(322, 11, 100, 100);
	        panelFotoPerfil.add(pnlImagen);
	        
			//Crea in JLabel y asigna la imagen
	        JLabel lblGraphics = new JLabel(new ImageIcon(resizedImage));
	        lblGraphics.setBounds(291, 11, 100, 100);
	        panelFotoPerfil.add(lblGraphics);
	        lblGraphics.setBackground(Color.BLUE);
			
			
	  
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
		
		
		JLabel lblCorreoE = new JLabel("Correo Electrónico:");
		lblCorreoE.setFont(new Font("Trebuchet MS", Font.PLAIN, 17));
		lblCorreoE.setBounds(39, 191, 174, 16);
		pnlDatos.add(lblCorreoE);
		
		JLabel lblNewLabel_1 = new JLabel("NºTeléfono:");
		lblNewLabel_1.setFont(new Font("Trebuchet MS", Font.PLAIN, 17));
		lblNewLabel_1.setBounds(39, 218, 105, 23);
		pnlDatos.add(lblNewLabel_1);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setBounds(266, 108, 48, 14);
		pnlDatos.add(lblNewLabel_3);
		
		JButton btnNewButton = new JButton("Editar");
		btnNewButton.setBounds(39, 318, 89, 23);
		pnlDatos.add(btnNewButton);
		
		JLabel lblDatosPerfil = new JLabel("Datos:");
		lblDatosPerfil.setHorizontalAlignment(SwingConstants.CENTER);
		lblDatosPerfil.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDatosPerfil.setBounds(10, 15, 124, 14);
		pnlDatos.add(lblDatosPerfil);
		
		JLabel lblFuenteCurriculum = new JLabel("Curriculum:");
		lblFuenteCurriculum.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblFuenteCurriculum.setBounds(488, 11, 105, 22);
		pnlDatos.add(lblFuenteCurriculum);
		
		JList list = new JList();
		list.setBounds(399, 44, 286, 280);
		pnlDatos.add(list);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Trebuchet MS", Font.PLAIN, 17));
		lblNombre.setBounds(39, 86, 68, 23);
		pnlDatos.add(lblNombre);
		
		JLabel lblApellidos = new JLabel("Apellidos:");
		lblApellidos.setFont(new Font("Trebuchet MS", Font.PLAIN, 17));
		lblApellidos.setBounds(39, 123, 94, 23);
		pnlDatos.add(lblApellidos);
		
		JLabel lblUsername = new JLabel("Username\r\n:");
		lblUsername.setFont(new Font("Trebuchet MS", Font.PLAIN, 17));
		lblUsername.setBounds(39, 157, 94, 23);
		pnlDatos.add(lblUsername);
		
		JLabel lblProvincia = new JLabel("Provincia:");
		lblProvincia.setFont(new Font("Trebuchet MS", Font.PLAIN, 17));
		lblProvincia.setBounds(39, 255, 80, 23);
		pnlDatos.add(lblProvincia);
	
		
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
	
}
	
	

   
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(750, 650);
		frame.getContentPane().add(new PnlMiPerfil());
		frame.setVisible(true);
		
	}
}
	

