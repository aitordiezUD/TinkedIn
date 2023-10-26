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
import javax.swing.Icon;

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
            JLabel lblGrafExp = new JLabel(new ImageIcon(resizedImage));
            lblGrafExp.setBackground(new Color(240, 240, 240));
    		lblGrafExp.setBounds(20, 0, 38, 38);
    		pnlPerfil.add(lblGrafExp);
      
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
		
		JPanel pnlExplorar = new JPanel();
		pnlExplorar.setLayout(null);
		pnlExplorar.setBackground(new Color(208, 235, 242));
		pnlExplorar.setBounds(0, 199, 150, 38);
		PnlBotones.add(pnlExplorar);
		
		JLabel lblExplorar = new JLabel("Explorar");
		lblExplorar.setHorizontalAlignment(SwingConstants.CENTER);
		lblExplorar.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblExplorar.setBounds(38, 0, 112, 38);
		pnlExplorar.add(lblExplorar);
		
		try {
            // Carga la imagen original desde el archivo en el paquete "imagenes"
            InputStream imageStream = PnlPrincipal.class.getResourceAsStream("Explorar.png");
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
            JLabel lblGrafExp = new JLabel(new ImageIcon(resizedImage));
            lblGrafExp.setBackground(new Color(240, 240, 240));
    		lblGrafExp.setBounds(20, 0, 38, 38);
    		pnlExplorar.add(lblGrafExp);
      
        } catch (IOException e) {
            e.printStackTrace();
        }
		
//		Cambio de colores al pasar el ratón por encima
		pnlExplorar.addMouseListener( new MouseAdapter() {			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				pnlExplorar.setBackground(new Color(208, 235, 242));
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				pnlExplorar.setBackground(new Color(122, 199, 218));
			}
		});
		
		JPanel pnlMensajes = new JPanel();
		pnlMensajes.setLayout(null);
		pnlMensajes.setBackground(new Color(208, 235, 242));
		pnlMensajes.setBounds(0, 237, 150, 38);
		PnlBotones.add(pnlMensajes);
		
		JLabel lblMensajes = new JLabel("Mensajes");
		lblMensajes.setHorizontalAlignment(SwingConstants.CENTER);
		lblMensajes.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblMensajes.setBounds(38, 0, 112, 38);
		pnlMensajes.add(lblMensajes);
		
		
		try {
            // Carga la imagen original desde el archivo en el paquete "imagenes"
            InputStream imageStream = PnlPrincipal.class.getResourceAsStream("Mensajes.png");
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
            JLabel lblGrafMens = new JLabel(new ImageIcon(resizedImage));
            lblGrafMens.setBackground(new Color(240, 240, 240));
    		lblGrafMens.setBounds(20, 0, 38, 38);
    		pnlMensajes.add(lblGrafMens);
      
        } catch (IOException e) {
            e.printStackTrace();
        }
		
//		Cambio de colores al pasar el ratón por encima
		pnlMensajes.addMouseListener( new MouseAdapter() {			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				pnlMensajes.setBackground(new Color(208, 235, 242));
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				pnlMensajes.setBackground(new Color(122, 199, 218));
			}
		});
		
		JPanel pnlEstadisticas = new JPanel();
		pnlEstadisticas.setLayout(null);
		pnlEstadisticas.setBackground(new Color(208, 235, 242));
		pnlEstadisticas.setBounds(0, 275, 150, 38);
		PnlBotones.add(pnlEstadisticas);
		
		JLabel lblEstadisticas = new JLabel("Estadísticas");
		lblEstadisticas.setHorizontalAlignment(SwingConstants.CENTER);
		lblEstadisticas.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblEstadisticas.setBounds(38, 0, 112, 38);
		pnlEstadisticas.add(lblEstadisticas);
		
		
		try {
            // Carga la imagen original desde el archivo en el paquete "imagenes"
            InputStream imageStream = PnlPrincipal.class.getResourceAsStream("Estadisticas.png");
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
            JLabel lblGrafEstadisticas = new JLabel(new ImageIcon(resizedImage));
            lblGrafEstadisticas.setBackground(new Color(240, 240, 240));
    		lblGrafEstadisticas.setBounds(16, 0, 38, 38);
    		pnlEstadisticas.add(lblGrafEstadisticas);
      
        } catch (IOException e) {
            e.printStackTrace();
        }
		
//		Cambio de colores al pasar el ratón por encima
		pnlEstadisticas.addMouseListener( new MouseAdapter() {			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				pnlEstadisticas.setBackground(new Color(208, 235, 242));
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				pnlEstadisticas.setBackground(new Color(122, 199, 218));
			}
		});
		
		JPanel pnlAjustes = new JPanel();
		pnlAjustes.setLayout(null);
		pnlAjustes.setBackground(new Color(208, 235, 242));
		pnlAjustes.setBounds(0, 313, 150, 38);
		PnlBotones.add(pnlAjustes);
		
		JLabel lblAjustes = new JLabel("Ajustes");
		lblAjustes.setHorizontalAlignment(SwingConstants.CENTER);
		lblAjustes.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblAjustes.setBounds(38, 0, 112, 38);
		pnlAjustes.add(lblAjustes);
		
		
		try {
            // Carga la imagen original desde el archivo en el paquete "imagenes"
            InputStream imageStream = PnlPrincipal.class.getResourceAsStream("Ajustes.png");
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
            JLabel lblGrafAjustes = new JLabel(new ImageIcon(resizedImage));
            lblGrafAjustes.setBackground(new Color(240, 240, 240));
    		lblGrafAjustes.setBounds(20, 0, 38, 38);
    		pnlAjustes.add(lblGrafAjustes);
      
        } catch (IOException e) {
            e.printStackTrace();
        }
		
//		Cambio de colores al pasar el ratón por encima
		pnlAjustes.addMouseListener( new MouseAdapter() {			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				pnlAjustes.setBackground(new Color(208, 235, 242));
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				pnlAjustes.setBackground(new Color(122, 199, 218));
			}
		});
		
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