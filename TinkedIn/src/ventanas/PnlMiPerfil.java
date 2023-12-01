package ventanas;


import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.Font;
import java.awt.Graphics;


public class PnlMiPerfil  extends JPanel {
	
	private PnlEditarPerfil PnlEditarPerfil;
	
	public PnlMiPerfil() {
		
		setBackground(Color.WHITE);
		setLayout(null);
		setSize(750,650);
		
		PnlEditarPerfil = new PnlEditarPerfil();
		
		JPanel pnlDatos = new JPanel(  ) {

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
		    	
		    	g.setColor(Color.BLACK);
		    	g.drawLine(  355,10,355,420 );
			}
			
		};
		pnlDatos.setBackground(new Color(202, 232, 232));
		pnlDatos.setBounds(10, 177, 711, 435);
		add(pnlDatos);
		pnlDatos.setLayout(null);
		
		
		JPanel pnlFotoPerfil = new JPanel();

		pnlFotoPerfil.setBackground(new Color(98, 188, 255));
		pnlFotoPerfil.setBounds(10, 11, 711, 165);
		add(pnlFotoPerfil);
		pnlFotoPerfil.setLayout(null);
		
		
		try {
	        InputStream imageStream = PnlBotonera.class.getResourceAsStream("fotoPerfilEjemplo.jpg");
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
			
	        JLabel lblFoto =new JLabel(new ImageIcon(resizedImage));
	        lblFoto.setBounds(35, 7, 150, 150);
	        lblFoto.setBackground(new Color(240, 240, 240));
	  

	        pnlFotoPerfil.add(lblFoto);
	        
	        JLabel lblUsername = new JLabel("@username");
	        lblUsername.setForeground(new Color(55, 55, 55));
	        lblUsername.setBounds(190, 125, 94, 23);
	        pnlFotoPerfil.add(lblUsername);
	        lblUsername.setFont(new Font("Trebuchet MS", Font.PLAIN, 17));
		
			
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
		
		
		JLabel lblCorreoE = new JLabel("Correo Electrónico:");
		lblCorreoE.setFont(new Font("SansSerif", Font.PLAIN, 17));
		lblCorreoE.setBounds(20, 141, 174, 16);
		pnlDatos.add(lblCorreoE);
		
		JLabel lblNTelefono = new JLabel("NºTeléfono:");
		lblNTelefono.setFont(new Font("Trebuchet MS", Font.PLAIN, 17));
		lblNTelefono.setBounds(20, 168, 105, 23);
		pnlDatos.add(lblNTelefono);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setBounds(266, 108, 48, 14);
		pnlDatos.add(lblNewLabel_3);
		
		ImageIcon lapiz = new ImageIcon("EditarPerfil.png");
		
		try {
	        InputStream imageStream = PnlBotonera.class.getResourceAsStream("EditarPerfil.png");
	        BufferedImage originalImage = ImageIO.read(imageStream);

	        // Redimensiona la imagen a un tamaño más pequeño (50x50 pixeles)
	        int width = 25;
	        int height = 25;
	        Image scaledImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);

	        // Convierte la imagen escalada en un BufferedImage
	        BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	        Graphics2D g2d = resizedImage.createGraphics();
	        g2d.drawImage(scaledImage, 0, 0, null);
	        g2d.dispose();
			
	        JButton btnEditar =new JButton(new ImageIcon(resizedImage));
	        btnEditar.setBounds(20, 80, 25, 25);
	  

	        pnlDatos.add(btnEditar);
	        
	        btnEditar.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					PnlBotonera.pnlFuncional.add(PnlEditarPerfil,"pnlEditarPerfil");
					PnlBotonera.CardLayout.show(PnlBotonera.pnlFuncional, "pnlEditarPerfil");
					
				}
			});
		
			
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
		
		
		JLabel lblDatosPerfil = new JLabel("Datos:");
		lblDatosPerfil.setHorizontalAlignment(SwingConstants.LEFT);
		lblDatosPerfil.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDatosPerfil.setBounds(20, 116, 124, 14);
		pnlDatos.add(lblDatosPerfil);
		
		JLabel lblFuenteCurriculum = new JLabel("Curriculum:");
		lblFuenteCurriculum.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblFuenteCurriculum.setBounds(488, 11, 105, 22);
		pnlDatos.add(lblFuenteCurriculum);
		
		JList list = new JList();
		list.setBounds(399, 44, 286, 332);
		pnlDatos.add(list);
		
		JLabel lblTitPrincipal = new JLabel("Titulacion principal (ejemplo)");
		lblTitPrincipal.setForeground(new Color(128, 128, 128));
		lblTitPrincipal.setFont(new Font("Trebuchet MS", Font.PLAIN, 17));
		lblTitPrincipal.setBounds(20, 50, 261, 23);
		pnlDatos.add(lblTitPrincipal);
		
		JLabel lblNombre = new JLabel("Nombre de la persona (ejemplo)");
		lblNombre.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		lblNombre.setBounds(20, 23, 332, 31);
		pnlDatos.add(lblNombre);
		
		JLabel lblProvincia = new JLabel("Provincia:");
		lblProvincia.setFont(new Font("Trebuchet MS", Font.PLAIN, 17));
		lblProvincia.setBounds(20, 202, 80, 23);
		pnlDatos.add(lblProvincia);
		
		JButton btnCrearPt = new JButton("Crear Puestos");
		btnCrearPt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PnlBotonera.CardLayout.show( PnlBotonera.pnlFuncional, "pnlPuestoTrabajo");
			}
		});
		btnCrearPt.setBounds(20, 347, 133, 23);
		pnlDatos.add(btnCrearPt);
		

	        

	

		
		
		
		
		
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
	

