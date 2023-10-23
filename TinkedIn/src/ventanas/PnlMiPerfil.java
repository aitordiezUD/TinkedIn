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

	        // Crea un JLabel y asigna la imagen escalada como ícono
	        JLabel lblGrafFoto = new JLabel(new ImageIcon(resizedImage));
			lblGrafFoto.setBounds(266, 45, 101, 102);
			pnlFotoPerfil.add(lblGrafFoto);
			
			
			
	  
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	
		
		
		
		JLabel lblEditarFoto = new JLabel("Editar");
		lblEditarFoto.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditarFoto.setBounds(289, 159, 61, 16);
		pnlFotoPerfil.add(lblEditarFoto);
	
		
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
	

