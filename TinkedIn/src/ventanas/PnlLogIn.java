package ventanas;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import clases.DatosFicheros;
import clases.Empresa;
import clases.Persona;
import clases.Usuario;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.BoxLayout;

public class PnlLogIn extends JPanel {
	private JTextField tfCorreo;
	private PnlRegistroPersona pnlRegistroPersona;
	private PnlRegistroEmpresa pnlRegistroEmpresa;
	private JPasswordField pfContrasnya;
	
	
	

	public PnlLogIn(JPanel pnlContenido, CardLayout layoutVentana) {
		setBackground(new Color(202, 232, 232));
		setSize(900, 650);
		setLayout(new BorderLayout(0, 0));
		
		JPanel pnlLogo = new JPanel(new BorderLayout());
		pnlLogo.setBackground(new Color(202, 232, 232));
		
		ImageIcon icono = new ImageIcon("TinkedinPNG.png");
		ImageIcon iconoRedimensionado = new ImageIcon(getScaledImage(icono.getImage(), 350, 300));
		
		JLabel lblLogo = new JLabel(iconoRedimensionado);
		lblLogo.setHorizontalAlignment(JLabel.CENTER);
        lblLogo.setVerticalAlignment(JLabel.CENTER);
        pnlLogo.add(lblLogo,BorderLayout.CENTER);
		
		add(pnlLogo);
		
		
		

		JPanel pnlCont = new JPanel(new BorderLayout());
		pnlCont.setPreferredSize(new Dimension(400, 10));
		pnlCont.setBackground(Color.WHITE);
		add(pnlCont, BorderLayout.WEST);
		
//		Margen Superior
		JPanel p = new JPanel();
		p.setBackground(Color.white);
		p.setPreferredSize(new Dimension(100,100));
		pnlCont.add(p, BorderLayout.NORTH);
		
//		Margen Inf
		p = new JPanel();
		p.setBackground(Color.white);
		p.setPreferredSize(new Dimension(50,50));
		pnlCont.add(p, BorderLayout.SOUTH);
		
//		Margen WEST
		p = new JPanel();
		p.setPreferredSize(new Dimension(50,50));
		p.setBackground(Color.white);
		pnlCont.add(p, BorderLayout.WEST);
		
//		Margen EAST
		p = new JPanel();
		p.setPreferredSize(new Dimension(50,50));
		p.setBackground(Color.white);
		pnlCont.add(p, BorderLayout.EAST);
		
		JPanel pnlFunc = new JPanel();
		pnlFunc.setBackground(Color.white);
		pnlRegistroPersona = new PnlRegistroPersona(pnlContenido, layoutVentana);
		pnlContenido.add(pnlRegistroPersona,"pnlRegistroPersona");
		
		pnlRegistroEmpresa = new PnlRegistroEmpresa(pnlContenido, layoutVentana);
		pnlContenido.add(pnlRegistroEmpresa,"pnlRegistroEmpresa");
		pnlFunc.setLayout(new BoxLayout(pnlFunc, BoxLayout.Y_AXIS));
		
		JPanel pc = new JPanel();
		pc.setLayout(new BorderLayout());
		pc.setBackground(Color.white);
		pc.setMaximumSize(new Dimension(20000, 80));
		pc.setPreferredSize(new Dimension(100, 16));
		pc.setMinimumSize(new Dimension(100, 20));
		
		JLabel lblBienvenida = new JLabel("Bienvenido de nuevo");
//		lblBienvenida.setMaximumSize(new Dimension(20000, 80));
		lblBienvenida.setHorizontalTextPosition(SwingConstants.CENTER);
//		lblBienvenida.setPreferredSize(new Dimension(100, 16));
//		lblBienvenida.setMinimumSize(new Dimension(100, 20));
		lblBienvenida.setHorizontalAlignment(SwingConstants.CENTER);
		lblBienvenida.setFont(new Font("Trebuchet MS", Font.BOLD, 26));
//		pnlFunc.add(lblBienvenida);
		pc.add(lblBienvenida);
		pnlFunc.add(pc);
		
		pc = new JPanel();
		pc.setLayout(new BorderLayout());
//		pc.setLayout(new FlowLayout());
		pc.setMaximumSize(new Dimension(2000, 30));
		pc.setMinimumSize(new Dimension(200, 30));
		pc.setBackground(Color.white);
		JLabel lblCorreo = new JLabel("Correo electrónico");
		lblCorreo.setHorizontalAlignment(SwingConstants.LEFT);
//		lblCorreo.setMaximumSize(new Dimension(200, 30));
//		lblCorreo.setMinimumSize(new Dimension(200, 30));
		pc.add(lblCorreo);
		pnlFunc.add(pc);
		
		tfCorreo = new JTextField();
		tfCorreo.setMaximumSize(new Dimension(2147483647, 25));
		pnlFunc.add(tfCorreo);
		tfCorreo.setColumns(10);
		
		pc = new JPanel();
		pc.setLayout(new BorderLayout());
		pc.setMaximumSize(new Dimension(2000, 30));
		pc.setMinimumSize(new Dimension(80, 30));
		pc.setBackground(Color.white);
		JLabel lblContrasena = new JLabel("Contraseña");
		lblContrasena.setHorizontalAlignment(SwingConstants.LEFT);
		pc.add(lblContrasena);
		pnlFunc.add(pc);
		
		
		
		pfContrasnya = new JPasswordField();
		pfContrasnya.setMaximumSize(new Dimension(2147483647, 25));
		pnlFunc.add(pfContrasnya);
		
		pc = new JPanel();
		pc.setLayout(new BorderLayout());
		pc.setMaximumSize(new Dimension(2000, 30));
		pc.setMinimumSize(new Dimension(200, 20));
		pc.setBackground(Color.white);
		JLabel lblCredIncorrectas = new JLabel("E-mail o contraseña incorrectos.");
		lblCredIncorrectas.setHorizontalAlignment(SwingConstants.CENTER);
		lblCredIncorrectas.setForeground(new Color(255, 0, 0));
		lblCredIncorrectas.setVisible(false);
		pc.add(lblCredIncorrectas);
		
		pnlFunc.add(pc);
		
		pc = new JPanel();
		pc.setLayout(new BorderLayout());
		pc.setMaximumSize(new Dimension(2000, 26));
		pc.setBackground(Color.white);
		JButton btnIniciarSesion = new JButton("Iniciar sesión");
		btnIniciarSesion.setMaximumSize(new Dimension(20000, 23));
		pc.add(btnIniciarSesion);
		pnlFunc.add(pc);
		
		
		pc = new JPanel();
		pc.setBackground(Color.white);
		pc.setLayout(new BorderLayout());
		pc.setMaximumSize(new Dimension(20000, 30));
		JLabel lblRegistro = new JLabel("¿No tienes cuenta todavía? Regístrate");
//		lblRegistro.setMaximumSize(new Dimension(20000, 28));
		lblRegistro.setHorizontalAlignment(SwingConstants.CENTER);
		pc.add(lblRegistro);
		pnlFunc.add(pc);
		
		pnlCont.add(pnlFunc,BorderLayout.CENTER);
		
		
//		try {
//		    InputStream imageStream = PnlBotonera.class.getResourceAsStream("TinkedinPNG.png");
//		    if (imageStream != null) {
//		        BufferedImage originalImage = ImageIO.read(imageStream);
//		        ImageIcon icono = new ImageIcon(originalImage.getScaledInstance(350, 300, Image.SCALE_SMOOTH));
//		        lblImagen.setIcon(icono);
//		    }
//		} catch (IOException e) {
//		    e.printStackTrace(); // Manejo de la excepción, imprime el seguimiento de la pila en la consola
//		}
		
		
//		Listener para abrir la ventana de registrarse
		lblRegistro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblRegistro.setText("<html><u>¿No tienes cuenta todavía? Regístrate</u></html>");
				lblRegistro.setForeground(new Color(122, 199, 218));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				lblRegistro.setText("¿No tienes cuenta todavía? Regístrate");
				lblRegistro.setForeground(new Color(0, 0, 0));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// Array de opciones
		        Object[] opciones = {"Empresa", "Persona"};
		        // Muestra el JOptionPane
		        int seleccion = JOptionPane.showOptionDialog(
		                null,
		                "¿Qué tipo de cuenta quieres crear?",
		                "Selecciona un tipo de cuenta",
		                JOptionPane.YES_NO_OPTION,
		                JOptionPane.QUESTION_MESSAGE,
		                null,
		                opciones,
		                opciones[0]
		        );

		        // Comprueba la opción seleccionada
		        if (seleccion == JOptionPane.YES_OPTION) {
		        	layoutVentana.show(pnlContenido, "pnlRegistroEmpresa");
		        } else if (seleccion == JOptionPane.NO_OPTION) {
		            // Opción "Persona" seleccionada
		        	layoutVentana.show(pnlContenido, "pnlRegistroPersona");
		        } else {}
				
			}
		});
		
		
		btnIniciarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(VentanaPrincipal.getDatos().autenticarUsuario(tfCorreo.getText(), new String(pfContrasnya.getPassword()))) {
					
					Usuario usuarioAutenticado = DatosFicheros.getMapaEmailUsuario().get(tfCorreo.getText());
					PnlBotonera pnlBotones = new PnlBotonera( usuarioAutenticado );
					pnlContenido.add(pnlBotones,"pnlBotones");
					layoutVentana.show(pnlContenido, "pnlBotones");
				}else {
					lblCredIncorrectas.setVisible(true);
				}
				
				
			}
		});
		
		

	}
	
	private Image getScaledImage(Image srcImg, int width, int height) {
        BufferedImage resizedImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resizedImg.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.drawImage(srcImg, 0, 0, width, height, null);
        g2d.dispose();
        return resizedImg;
    }
}
