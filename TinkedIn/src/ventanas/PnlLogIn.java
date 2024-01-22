package ventanas;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import componentes.botonAnEl;
import servidor.ServicioPersistencia;
import usuarios.Empresa;
import usuarios.Persona;
import usuarios.Usuario;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
	protected static PnlRegistroPersona pnlRegistroPersona;
	protected static PnlRegistroEmpresa pnlRegistroEmpresa;
	private JPasswordField pfContrasnya;
	private ServicioPersistencia servicio;
    private int angle = 0;
    private SpinnerPanel spinnerPanel;
    protected static JPanel pnlContenido;
    protected static CardLayout layoutVentana;

	public PnlLogIn(JPanel pnlContenido, CardLayout layoutVentana, VentanaPrincipal vp) {
		this.servicio = VentanaPrincipal.servicio;
		PnlLogIn.pnlContenido = pnlContenido;
		PnlLogIn.layoutVentana = layoutVentana;

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
		lblRegistro.setHorizontalAlignment(SwingConstants.CENTER);
		pc.add(lblRegistro);
		pnlFunc.add(pc);
		
		spinnerPanel = new SpinnerPanel();
		Timer timer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                angle = (angle + 5) % 360;
                spinnerPanel.repaint();
            }
        });
        timer.start();
        spinnerPanel.setMaximumSize(new Dimension(100,100));
        pnlFunc.add(spinnerPanel);
		
		pnlCont.add(pnlFunc,BorderLayout.CENTER);
		
		
		
		
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
//				// Array de opciones
//		        Object[] opciones = {"Empresa", "Persona"};
//		        // Muestra el JOptionPane
//		        int seleccion = JOptionPane.showOptionDialog(
//		                vp,
//		                "¿Qué tipo de cuenta quieres crear?",
//		                "Selecciona un tipo de cuenta",
//		                JOptionPane.YES_NO_OPTION,
//		                JOptionPane.QUESTION_MESSAGE,
//		                null,
//		                opciones,
//		                opciones[0]
//		        );
//
//		        // Comprueba la opción seleccionada
//		        if (seleccion == JOptionPane.YES_OPTION) {
//		        	layoutVentana.show(pnlContenido, "pnlRegistroEmpresa");
//		        } else if (seleccion == JOptionPane.NO_OPTION) {
//		            // Opción "Persona" seleccionada
//		        	pnlRegistroPersona.limpiarCampos();;
//		        	layoutVentana.show(pnlContenido, "pnlRegistroPersona");
//		        } else {}
				new DialogoCreacionUsuario(vp);
			}
		});
		
		
//		btnIniciarSesion.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				System.out.println(VentanaPrincipal.getDatos().autenticarUsuario(tfCorreo.getText(), new String(pfContrasnya.getPassword())));
//				if(VentanaPrincipal.getDatos().autenticarUsuario(tfCorreo.getText(), new String(pfContrasnya.getPassword()))) {
//					Usuario usuarioAutenticado = DatosFicheros.getMapaEmailUsuario().get(tfCorreo.getText());
//					usuarioAutenticado = DatosFicheros.getMapaEmailUsuario().get(tfCorreo.getText());
//					PnlBotonera pnlBotones = new PnlBotonera( usuarioAutenticado );
//					pnlContenido.add(pnlBotones,"pnlBotones");
//					layoutVentana.show(pnlContenido, "pnlBotones");
//				}else {
//					lblCredIncorrectas.setVisible(true);
//				}
//			}
//		});
		btnIniciarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(servicio.logIn(tfCorreo.getText(), new String(pfContrasnya.getPassword()))) {
					Usuario u = servicio.getUsuarioFromCorreo(tfCorreo.getText());
					spinnerPanel.hacerVisible();
					tfCorreo.setEditable(false);
					pfContrasnya.setEditable(false);
					btnIniciarSesion.setEnabled(false);
					Thread t1 = new Thread(()->{
						PnlBotonera pnlBotones = new PnlBotonera( u,vp );
						pnlContenido.add(pnlBotones,"pnlBotones");
						layoutVentana.show(pnlContenido, "pnlBotones");
					});
					t1.start();
				}else {
					lblCredIncorrectas.setVisible(true);
				}
			}
		});
		
		pfContrasnya.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if(servicio.logIn(tfCorreo.getText(), new String(pfContrasnya.getPassword()))) {
						Usuario u = servicio.getUsuarioFromCorreo(tfCorreo.getText());
						PnlBotonera pnlBotones = new PnlBotonera( u,vp );
						pnlContenido.add(pnlBotones,"pnlBotones");
						layoutVentana.show(pnlContenido, "pnlBotones");
					}else {
						lblCredIncorrectas.setVisible(true);
					}
				}
				
			}
		});
		
		

	}
	
	private static Image getScaledImage(Image srcImg, int width, int height) {
        BufferedImage resizedImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resizedImg.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.drawImage(srcImg, 0, 0, width, height, null);
        g2d.dispose();
        return resizedImg;
    }
	private static class DialogoCreacionUsuario extends JDialog {

	    public DialogoCreacionUsuario(VentanaPrincipal vp) {
	    	super(vp);
	    	setTitle("Bienvenido!");
	        setSize(450, 250);
	        setResizable(false);
	        setLocationRelativeTo(vp); // Centra el diálogo en la pantalla
	        ImageIcon icon = new ImageIcon("TinkedinPNG.png");
	        Image iconImage = icon.getImage();
	        setIconImage(iconImage);
	        
	        JPanel panel = new JPanel(new BorderLayout());
	        panel.setBackground(Color.white);
	        String mensaje = "<html>¡Bienvenido a TinkedIn! ¡Gracias por elegirnos!<br>";
	        JLabel lblMensaje = new JLabel(mensaje);
	        lblMensaje.setHorizontalAlignment(SwingConstants.CENTER);
	        lblMensaje.setPreferredSize(new Dimension(300,40));
	        panel.add(lblMensaje, BorderLayout.NORTH);
	        getContentPane().add(panel);
	        
			JPanel pnlLogo = new JPanel(new BorderLayout());
			pnlLogo.setBackground(Color.white);
			pnlLogo.setPreferredSize(new Dimension(190,190));
			
			ImageIcon icono = new ImageIcon("TinkedinPNG.png");
			ImageIcon iconoRedimensionado = new ImageIcon(getScaledImage(icono.getImage(), 150, 150));
			
			JLabel lblLogo = new JLabel(iconoRedimensionado);
			lblLogo.setHorizontalAlignment(JLabel.CENTER);
	        lblLogo.setVerticalAlignment(JLabel.TOP);
	        pnlLogo.add(lblLogo,BorderLayout.CENTER);
	        JPanel p = new JPanel();
	        p.setPreferredSize(new Dimension(20,20));
	        p.setBackground(Color.WHITE);
	        pnlLogo.add(p,BorderLayout.WEST);
	        panel.add(pnlLogo,BorderLayout.WEST);
	        
	        panel.add(new pnlBotones(this),BorderLayout.CENTER);
	        
	        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	        setVisible(true);
	    }
	    
	    private void cerrar(){
	    	dispose();
	    }
	    
	    private static class pnlBotones extends JPanel{
			private static final long serialVersionUID = 1L;
			public pnlBotones(DialogoCreacionUsuario dialogo) {
				setLayout(new BorderLayout());
				setBackground(Color.WHITE);
		        String mensaje = "<html>¿Qué tipo de usuario quieres crear?<br>";
		        JLabel lblMensaje = new JLabel(mensaje);
		        lblMensaje.setHorizontalAlignment(JLabel.CENTER);
		        lblMensaje.setPreferredSize(new Dimension(50,50));
		        add(lblMensaje, BorderLayout.NORTH);
		        JPanel pnlBtns = new JPanel(new FlowLayout(FlowLayout.CENTER));
		        pnlBtns.setBackground(Color.white);
		        botonAnEl btnEmpresa = new botonAnEl("Empresa");
		        botonAnEl btnPersona = new botonAnEl("Persona");
		        pnlBtns.add(btnEmpresa);
		        btnEmpresa.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						dialogo.cerrar();
						pnlRegistroEmpresa.limpiarCampos();
			        	layoutVentana.show(pnlContenido, "pnlRegistroEmpresa");
					}
				});
		        btnPersona.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						dialogo.cerrar();
						pnlRegistroPersona.limpiarCampos();
			        	layoutVentana.show(pnlContenido, "pnlRegistroPersona");
					}
				});
		        pnlBtns.add(btnPersona);
		        add(pnlBtns,BorderLayout.CENTER);
			}
		}

	}
	
    private class SpinnerPanel extends JPanel {
    	public SpinnerPanel() {
    		setBackground(Color.WHITE);
    		setVisible(false);
    	}
    	
    	public void hacerVisible() {
    		setVisible(true);
    	}
    	
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            int centerX = getWidth() / 2;
            int centerY = (getHeight() / 2) - 15;

            // Dibuja la ruleta giratoria
            int radius = 30;
            int x = centerX - radius;
            int y = centerY - radius;

            g.setColor(VentanaPrincipal.ColorBase);
            g.fillArc(x, y, 2 * radius, 2 * radius, angle, 30);

            g.setColor(VentanaPrincipal.ColorBase);
            g.fillArc(x, y, 2 * radius, 2 * radius, angle + 120, 30);

            g.setColor(VentanaPrincipal.ColorBase);
            g.fillArc(x, y, 2 * radius, 2 * radius, angle + 240, 30);
        }
    }
}
