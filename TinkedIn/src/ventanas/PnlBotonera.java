package ventanas;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.LayoutManager;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.TreeSet;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import clases.Main;
import datos.DatosFicheros;
import servidor.ServicioPersistencia;
import sistemaExplorar.Match;
import usuarios.Empresa;
import usuarios.Persona;
import usuarios.Usuario;
import clases.PuestoTrabajo;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;

public class PnlBotonera extends JPanel {
	
	private ImageIcon icono;
	private JPanel btnSeleccionado = null;
	private HashMap<JPanel,JPanel> mapaPaneles;
	//public static PnlMiPerfil pnlMiPerfil = new PnlMiPerfil();
	protected static Usuario usuarioAutenticado;
	//private PnlExplorar pExplorar = new PnlExplorar( usuarioAutenticado );
	public static JPanel pnlFuncional;
	protected static CardLayout CardLayout;
	protected static VentanaPrincipal vp; //Necesario para cuando falle la conexion con el servidor cerrar la ventana
	//protected static TreeSet<PuestoTrabajo> puestosCandidatos;
	protected static PnlChat pnlChat;
	
//	Pruebas tiempo
	long tiempoInicio;
	long tiempoActual;
	long tiempoResultante;
	
	public Usuario getUsuarioAutenticado() {
		return usuarioAutenticado;
	}



	public void setUsuarioAutenticado(Usuario usuarioAutenticado) {
		this.usuarioAutenticado = usuarioAutenticado;
	}



	/**
	 * Create the panel.
	 */
	public PnlBotonera( Usuario usuarioAutenticado ) {
//		setLayout(null);
		setLayout(new BorderLayout());
		
		this.usuarioAutenticado = usuarioAutenticado;
		mapaPaneles = new HashMap<JPanel,JPanel>();

		
		tiempoInicio = System.currentTimeMillis();		
		
		
		tiempoActual = System.currentTimeMillis();
		tiempoResultante = tiempoActual - tiempoInicio;
		System.err.println("PnlExplorar: " + tiempoResultante);
		tiempoInicio = System.currentTimeMillis();
		
		pnlFuncional = new JPanel();
		pnlFuncional.setBackground(Color.PINK);
		CardLayout = new CardLayout();
		pnlFuncional.setLayout(CardLayout);
		pnlFuncional.setBounds(150, 0, 750, 610);
		setSize(900, 650);
		add(pnlFuncional,BorderLayout.CENTER);
		
		PnlMiPerfil pnlMiPerfil = new PnlMiPerfil(usuarioAutenticado);
		
		tiempoActual = System.currentTimeMillis();
		tiempoResultante = tiempoActual - tiempoInicio;
		System.err.println("PnlMiPerfil: " + tiempoResultante);
		tiempoInicio = System.currentTimeMillis();
		
		
		pnlPuestoDeTrabajo pnlPuestoTrabajo = new pnlPuestoDeTrabajo();
		
		tiempoActual = System.currentTimeMillis();
		tiempoResultante = tiempoActual - tiempoInicio;
		System.err.println("pnlPuestoTrabajo: " + tiempoResultante);
		tiempoInicio = System.currentTimeMillis();
		
		JPanel pnlInicial = new JPanel(new BorderLayout());
		icono = new ImageIcon("TinkedinPNG.png");
		
		JLabel lblInicial = new JLabel(icono);
		lblInicial.setHorizontalAlignment(JLabel.CENTER);
        lblInicial.setVerticalAlignment(JLabel.CENTER);
		pnlInicial.add(lblInicial,BorderLayout.CENTER);
        
		pnlFuncional.add(pnlInicial,"pnlInicial");
		CardLayout.show(pnlFuncional, "pnlInicial");		
		
		JPanel PnlBotones = new JPanel();
		PnlBotones.setBorder( BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));
		PnlBotones.setBounds(0, 0, 150, 650);
		PnlBotones.setPreferredSize(new Dimension(150,650));
		PnlBotones.setBackground(new Color(208, 235, 242));
		add(PnlBotones, BorderLayout.WEST);
		PnlBotones.setLayout(null);
		
		JPanel pnlPerfil = new JPanel();
		pnlPerfil.setBorder( BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));
		pnlPerfil.setBackground(new Color(208, 235, 242));
		pnlPerfil.setBounds(0, 161, 150, 38);
		PnlBotones.add(pnlPerfil);
		pnlPerfil.setLayout(null);
		
		pnlFuncional.add(pnlMiPerfil,"pnlMiPerfil");
		pnlFuncional.add(pnlPuestoTrabajo, "pnlPuestoTrabajo");
		
//		puestosCandidatos = CrearTreeSet();
		
		if (usuarioAutenticado instanceof Persona) {
			PnlHabilidad pnlHabilidad = new PnlHabilidad(pnlFuncional, CardLayout, pnlMiPerfil.getModeloListaPersona(), 1);
			pnlFuncional.add(pnlHabilidad,"pnlHabilidad");
			
			tiempoActual = System.currentTimeMillis();
			tiempoResultante = tiempoActual - tiempoInicio;
			System.err.println("PnlHabilidad: " + tiempoResultante);
			tiempoInicio = System.currentTimeMillis();
		}

		
		PnlEditarPerfil pnlEditarPerfil = new PnlEditarPerfil();
		
		tiempoActual = System.currentTimeMillis();
		tiempoResultante = tiempoActual - tiempoInicio;
		System.err.println("PnlEditarPerfil: " + tiempoResultante);
		tiempoInicio = System.currentTimeMillis();
		
		pnlFuncional.add(pnlEditarPerfil,"pnlEditarPerfil");
		
		JLabel lblPerfil = new JLabel("Mi Perfil");
		lblPerfil.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblPerfil.setHorizontalAlignment(SwingConstants.CENTER);
		lblPerfil.setBounds(38, 0, 112, 38);
		pnlPerfil.add(lblPerfil);
		
		
		try {
            // Carga la imagen original desde el archivo en el paquete "imagenes"
            InputStream imageStream = PnlBotonera.class.getResourceAsStream("perfil.png");
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
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if (btnSeleccionado != null) {
					btnSeleccionado.setBackground(new Color(208, 235, 242));
				}
				btnSeleccionado = pnlPerfil;
				CardLayout.show(pnlFuncional, "pnlMiPerfil");
				pnlPerfil.setBackground(new Color(122, 199, 218));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				if (btnSeleccionado == pnlPerfil) {
					
				}else {
					pnlPerfil.setBackground(new Color(208, 235, 242));
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				if (btnSeleccionado == pnlPerfil) {
					
				}else {
					pnlPerfil.setBackground(new Color(122, 199, 218));
				}
				
			}
		});
		
		JPanel pnlExplorar = new JPanel();
		pnlExplorar.setLayout(null);
		pnlExplorar.setBorder( BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));
		pnlExplorar.setBackground(new Color(208, 235, 242));
		pnlExplorar.setBounds(0, 199, 150, 38);
		PnlBotones.add(pnlExplorar);
		
		
		if (usuarioAutenticado instanceof Persona) {
			Persona personaAutenticada = (Persona) usuarioAutenticado;
			PnlExplorarPersona pExplorarPersona = new PnlExplorarPersona(personaAutenticada, VentanaPrincipal.servicio);
			pnlFuncional.add(pExplorarPersona,"pnlExplorar");
		}else {
			Empresa empresaAutenticada = (Empresa) usuarioAutenticado;
			PnlExplorarEmpresa pExplorarEmpresa = new PnlExplorarEmpresa(empresaAutenticada, VentanaPrincipal.servicio);
			pnlFuncional.add(pExplorarEmpresa,"pnlExplorar");

		}
		
		JLabel lblExplorar = new JLabel("Explorar");
		lblExplorar.setHorizontalAlignment(SwingConstants.CENTER);
		lblExplorar.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblExplorar.setBounds(38, 0, 112, 38);
		pnlExplorar.add(lblExplorar);
		
		try {
            // Carga la imagen original desde el archivo en el paquete "imagenes"
            InputStream imageStream = PnlBotonera.class.getResourceAsStream("Explorar.png");
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
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if (btnSeleccionado != null) {
					btnSeleccionado.setBackground(new Color(208, 235, 242));
				}
				btnSeleccionado = pnlExplorar;
				CardLayout.show(pnlFuncional, "pnlExplorar");
				pnlExplorar.setBackground(new Color(122, 199, 218));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				if (btnSeleccionado == pnlExplorar) {
					
				}else {
					pnlExplorar.setBackground(new Color(208, 235, 242));
				}
					
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				if (btnSeleccionado == pnlExplorar) {
					
				}else {
					pnlExplorar.setBackground(new Color(122, 199, 218));
				}
			}
		});
		
		JPanel pnlMensajes = new JPanel();
		pnlMensajes.setBorder( BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));
		pnlMensajes.setLayout(null);
		pnlMensajes.setBackground(new Color(208, 235, 242));
		pnlMensajes.setBounds(0, 237, 150, 38);
		PnlBotones.add(pnlMensajes);
		

		PnlChat pnlChat= new PnlChat();
		pnlChat.setBorder( BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));

		pnlChat= new PnlChat();
		pnlChat.setBackground(Color.GREEN);
		VentanaPrincipal.servicio.setPnlChat(pnlChat);
		pnlFuncional.add(pnlChat,"pnlChat");
		tiempoActual = System.currentTimeMillis();
		tiempoResultante = tiempoActual - tiempoInicio;
		System.err.println("PnlChat: " + tiempoResultante);
		tiempoInicio = System.currentTimeMillis();
		
		JLabel lblMensajes = new JLabel("Mensajes");
		lblMensajes.setHorizontalAlignment(SwingConstants.CENTER);
		lblMensajes.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblMensajes.setBounds(38, 0, 112, 38);
		pnlMensajes.add(lblMensajes);
		
		
		try {
            // Carga la imagen original desde el archivo en el paquete "imagenes"
            InputStream imageStream = PnlBotonera.class.getResourceAsStream("Mensajes.png");
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
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if (btnSeleccionado != null) {
					btnSeleccionado.setBackground(new Color(208, 235, 242));
				}
				btnSeleccionado = pnlMensajes;
				CardLayout.show(pnlFuncional, "pnlChat");
				pnlMensajes.setBackground(new Color(122, 199, 218));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				if (btnSeleccionado == pnlMensajes) {
					
				}else {
					pnlMensajes.setBackground(new Color(208, 235, 242));
				}
					
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				if (btnSeleccionado == pnlMensajes) {
					
				}else {
					pnlMensajes.setBackground(new Color(122, 199, 218));
				}
				
			}
		});
		
		JPanel pnlEstadisticas = new JPanel();
		pnlEstadisticas.setBorder( BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));
		pnlEstadisticas.setLayout(null);
		pnlEstadisticas.setBackground(new Color(208, 235, 242));
		pnlEstadisticas.setBounds(0, 275, 150, 38);
		PnlBotones.add(pnlEstadisticas);
		
		JPanel pStats = new PnlEstadisticas();
		pStats.setBackground(Color.LIGHT_GRAY);
		pnlFuncional.add(pStats,"pStats");
		
		JLabel lblEstadisticas = new JLabel("Estadísticas");
		lblEstadisticas.setHorizontalAlignment(SwingConstants.CENTER);
		lblEstadisticas.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblEstadisticas.setBounds(38, 0, 112, 38);
		pnlEstadisticas.add(lblEstadisticas);
		
		
		try {
            // Carga la imagen original desde el archivo en el paquete "imagenes"
            InputStream imageStream = PnlBotonera.class.getResourceAsStream("Estadisticas.png");
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
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if (btnSeleccionado != null) {
					btnSeleccionado.setBackground(new Color(208, 235, 242));
				}
				btnSeleccionado = pnlEstadisticas;
				CardLayout.show(pnlFuncional, "pStats");
				pnlEstadisticas.setBackground(new Color(122, 199, 218));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				if (btnSeleccionado == pnlEstadisticas) {
					
				}else {
					pnlEstadisticas.setBackground(new Color(208, 235, 242));
				}
					
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				if (btnSeleccionado == pnlEstadisticas) {
					
				}else {
					pnlEstadisticas.setBackground(new Color(122, 199, 218));
				}
				
			}
		});
		
		JPanel pnlAjustes = new JPanel();
		pnlAjustes.setBorder( BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));
		pnlAjustes.setLayout(null);
		pnlAjustes.setBackground(new Color(208, 235, 242));
		pnlAjustes.setBounds(0, 313, 150, 38);
		PnlBotones.add(pnlAjustes);
		
		JPanel pAjustes = new JPanel();
		pAjustes.setBackground(Color.GREEN);
		pnlFuncional.add(pAjustes,"pAjustes");
		
		JLabel lblAjustes = new JLabel("Ajustes");
		lblAjustes.setHorizontalAlignment(SwingConstants.CENTER);
		lblAjustes.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblAjustes.setBounds(38, 0, 112, 38);
		pnlAjustes.add(lblAjustes);
		
		
		try {
            // Carga la imagen original desde el archivo en el paquete "imagenes"
            InputStream imageStream = PnlBotonera.class.getResourceAsStream("Ajustes.png");
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
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if (btnSeleccionado != null) {
					btnSeleccionado.setBackground(new Color(208, 235, 242));
				}
				btnSeleccionado = pnlAjustes;
				CardLayout.show(pnlFuncional, "pAjustes");
				pnlAjustes.setBackground(new Color(122, 199, 218));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				if (btnSeleccionado == pnlAjustes) {
					
				}else {
					pnlAjustes.setBackground(new Color(208, 235, 242));
				}
					
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				if (btnSeleccionado == pnlAjustes) {
					
				}else {
					pnlAjustes.setBackground(new Color(122, 199, 218));
				}
				
			}
		});
		
		

	}

	public static void notificarMatch(Match match) {
		String nombre;
		Usuario usuario;
		if (usuarioAutenticado instanceof Persona) {
			if (match.getU1() != usuarioAutenticado.getId()) {
				nombre = VentanaPrincipal.servicio.getNombreEmpresaFromId(match.getU1());
				usuario = VentanaPrincipal.servicio.getUsuarioFromId(match.getU1());
				new NotificacionMatch(nombre);
			}else {
				nombre = VentanaPrincipal.servicio.getNombreEmpresaFromId(match.getU2());
				usuario = VentanaPrincipal.servicio.getUsuarioFromId(match.getU2());
				new NotificacionMatch(nombre);
			}
		}else {
			if (match.getU1() != usuarioAutenticado.getId()) {
				nombre = VentanaPrincipal.servicio.getNombrePersonaFromId(match.getU1());
				usuario = VentanaPrincipal.servicio.getUsuarioFromId(match.getU1());
				new NotificacionMatch(nombre);
			}else {
				nombre = VentanaPrincipal.servicio.getNombrePersonaFromId(match.getU2());
				usuario = VentanaPrincipal.servicio.getUsuarioFromId(match.getU2());
				new NotificacionMatch(nombre);
			}
		}
		pnlChat.anadirContacto(usuario);
	}
	
	private static class NotificacionMatch extends JDialog {
		private static final long serialVersionUID = 1L;

		public NotificacionMatch(String nombreUsuario) {
	        setTitle("¡Nuevo Match!");
	        setSize(300, 150);
	        setLocationRelativeTo(null); // Centra el diálogo en la pantalla
	        ImageIcon icon = new ImageIcon("TinkedinPNG.png");
	        Image iconImage = icon.getImage();
	        setIconImage(iconImage);
	        
	        JPanel panel = new JPanel(new BorderLayout());
	        panel.setBackground(Color.white);
	        String mensaje = "<html>¡Felicidades! Tienes un nuevo match con:<br>" + nombreUsuario + "</html>";
	        JLabel lblMensaje = new JLabel(mensaje);
	        lblMensaje.setHorizontalAlignment(SwingConstants.CENTER);

	        panel.add(lblMensaje, BorderLayout.CENTER);
	        getContentPane().add(panel);
	        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	    }

	}
	
}
