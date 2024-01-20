package ventanas;

import javax.swing.JPanel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Vector;

import javax.swing.Icon;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.StrokeBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import clases.Habilidad;
import clases.PuestoTrabajo;
import componentes.botonAnEl;
import datos.DatosFicheros;
import nube.ImagenesAzure;
import servidor.ServicioPersistencia;
import servidor.ServicioPersistenciaFicheros;
import sistemaExplorar.Like;
import usuarios.Empresa;
import usuarios.Persona;
import usuarios.Usuario;
import componentes.botonCorazon;
import componentes.botonLike;
import componentes.botonX;

import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.DropMode;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;

public class PnlExplorarPersona extends JPanel {

	protected static DefaultListModel modeloListaPt;

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */

	private JLabel lblGrafExpCor;
	private JLabel lblGrafExpX;
	private JPanel pnlLike;
	private JPanel pnlPass;
	private JPanel pnlInfoUsu;
	private JPanel pnlAbajo;
	private JPanel pnlDatos;
	private JPanel pnlHabilidades;
	private PuestoTrabajo puestoElegido;
	protected JLabel lblNombreUsu;
	protected JLabel lblNomEInfo;
	protected JLabel lblDescrPInfo;
	protected JLabel lblPuesto;
	protected JTextPane DescripcionPuesto;
	protected static Usuario usuarioAutenticado;
	protected static HashMap<PuestoTrabajo, TreeSet<Persona>> mapaPersonasPorPuesto;
	protected static TreeSet<PuestoTrabajo> puestosCandidatos;
	protected static Iterator<PuestoTrabajo> iteradorPuestos;
	protected ServicioPersistencia servicio;
	protected Empresa empresaActual = null; //La empresa que se esta mostrando en el panel

	public PnlExplorarPersona(Persona pers, ServicioPersistencia servicio) {
		setLayout(new BorderLayout());
		setBackground(Color.WHITE);

		setLayout(new BorderLayout(0, 0));

		this.usuarioAutenticado = pers;
		this.servicio = servicio;

		// PuestoTrabajo primerPuesto = puestosCandidatos.first();

		JPanel pnlContenido = new JPanel();
		pnlContenido.setLayout(new BorderLayout());
		pnlContenido.setBackground(Color.WHITE);
		pnlContenido.setPreferredSize(new Dimension(getWidth() - 250, getHeight()));
		add(pnlContenido, BorderLayout.CENTER);

		JPanel pnlInfo = new JPanel();
		pnlInfo.setLayout(new BorderLayout());
		pnlInfo.setBackground(new Color(129, 186, 207));
		pnlContenido.add(pnlInfo, BorderLayout.CENTER);

		pnlInfoUsu = new JPanel();
		pnlInfoUsu.setBackground(new Color(129, 186, 207));
		pnlInfoUsu.setLayout(new BorderLayout());
		pnlAbajo = new JPanel();
		pnlAbajo.setBackground(pnlInfoUsu.getBackground());
		pnlAbajo.setPreferredSize(new Dimension(pnlInfoUsu.getWidth(), 100));
		pnlInfoUsu.add(pnlAbajo, BorderLayout.SOUTH);

		pnlInfo.add(pnlInfoUsu, BorderLayout.CENTER);

		pnlDatos = new JPanel();
		pnlDatos.setBackground(new Color(208, 235, 242));
		pnlDatos.setLayout(new BorderLayout());
//	        pnlDatos.setBackground(Color.GREEN);
		pnlDatos.setPreferredSize(new Dimension(getWidth() - 250, 125));
		pnlContenido.add(pnlDatos, BorderLayout.NORTH);

		lblPuesto = new JLabel("");
		lblPuesto.setForeground(new Color(192, 192, 192));
		lblPuesto.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblPuesto.setHorizontalAlignment(SwingConstants.CENTER);
		pnlDatos.add(lblPuesto, BorderLayout.CENTER);

		JPanel pnlBotonera = new JPanel();
		pnlBotonera.setLayout(new GridLayout(0, 3));
//	        pnlBotonera.setBackground( Color.BLUE);
		pnlBotonera.setPreferredSize(new Dimension(getWidth() - 250, 120));
		pnlContenido.add(pnlBotonera, BorderLayout.SOUTH);
//	        System.out.println( tipoUsuario.getClass() );

		pnlLike = new JPanel();
		pnlLike.setLayout(new BorderLayout());
		pnlLike.setPreferredSize( new Dimension(70,70));
		botonLike btnLike = new botonLike();
		btnLike.setPreferredSize( new Dimension(55,55));
		pnlLike.add(btnLike, BorderLayout.CENTER);
		pnlBotonera.add(pnlLike);
		
		btnLike.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Like like = new Like(pers, empresaActual);
				servicio.anadirLike(like);
				mostrarSiguientePuesto();
			}
		});
		
		JPanel pnlVacio = new JPanel();
		pnlVacio.setPreferredSize(new Dimension(10, 10));
		pnlBotonera.add(pnlVacio);

		pnlPass = new JPanel();
		pnlPass.setLayout(new BorderLayout());
		botonX btnX = new botonX();

		pnlPass.add(btnX, BorderLayout.CENTER);
		pnlBotonera.add(pnlPass);
		try {
			// Carga la imagen original desde el archivo en el paquete "imagenes"
			InputStream imageStream = PnlBotonera.class.getResourceAsStream("fotoPerfilEjemplo.jpg");
			BufferedImage originalImage = ImageIO.read(imageStream);

			// Redimensiona la imagen a un tamaño más pequeño (50x50 pixeles)
			int width = 70;
			int height = 70;
			Image scaledImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);

			// Convierte la imagen escalada en un BufferedImage
			BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g2d = resizedImage.createGraphics();
			Shape circle = new Ellipse2D.Float(0, 0, width, height);
			g2d.setClip(circle);
			g2d.drawImage(scaledImage, 0, 0, null);
			g2d.dispose();

			// Crea un JLabel y asigna la imagen escalada como ícono
			JLabel lblGrafExp = new JLabel(new ImageIcon(resizedImage));
			lblGrafExp.setBackground(new Color(240, 240, 240));
			lblGrafExp.setBounds(20, 0, 38, 38);
			// pnlDatos.add(lblGrafExp);

		} catch (IOException e) {
			e.printStackTrace();
		}

		// botonCorazon btnCorazon = new botonCorazon();
		// pnlLike.add(btnCorazon, BorderLayout.EAST);
		lblNombreUsu = new JLabel("      Nombre de Usuario");
		lblNombreUsu.setFont(new Font("Segoe UI Black", Font.BOLD, 18));
		// pnlDatos.add(lblNombreUsu);

		JPanel pnlInfoPuesto = new JPanel();
		pnlInfoPuesto.setLayout(new BorderLayout());
		pnlInfoPuesto.setBackground(Color.WHITE);
		pnlInfoPuesto.setForeground(Color.BLUE);
		pnlInfoPuesto.setPreferredSize(new Dimension(400, 200));

		JLabel nomEmpresa = new JLabel("Nombre de la empresa: ");
		JLabel lblDescripcionPuesto = new JLabel("   Descripcion del puesto: ");
		lblDescripcionPuesto.setPreferredSize(new Dimension(pnlInfoPuesto.getWidth(), 60));
		lblDescripcionPuesto.setFont(new Font("Tahoma", Font.BOLD, 16));

		DescripcionPuesto = new JTextPane();
		DescripcionPuesto.setBorder(new LineBorder(new Color(129, 186, 207), 3));
		DescripcionPuesto.setEditable(false);
		pnlInfoPuesto.add(DescripcionPuesto, BorderLayout.CENTER);

		pnlHabilidades = new JPanel();
		pnlHabilidades.setBackground(Color.WHITE);
		pnlInfoPuesto.add(pnlHabilidades, BorderLayout.SOUTH);

		JPanel panelIzqv = new JPanel();
		panelIzqv.setPreferredSize(new Dimension(60, 0));
		panelIzqv.setBackground(Color.WHITE);
		pnlInfoPuesto.add(panelIzqv, BorderLayout.WEST);

		JPanel panelDerv = new JPanel();
		panelDerv.setPreferredSize(new Dimension(60, 0));
		panelDerv.setBackground(Color.WHITE);
		pnlInfoPuesto.add(panelDerv, BorderLayout.EAST);
		pnlInfo.add(pnlInfoPuesto, BorderLayout.WEST);
		lblNomEInfo = new JLabel("");
		lblDescrPInfo = new JLabel("");

		// pnlInfoPuesto.add(nomEmpresa);
		pnlInfoPuesto.add(lblDescripcionPuesto, BorderLayout.NORTH);
		JLabel lblExplorarPuestos = new JLabel("Explora Puestos De Trabajo");
		lblExplorarPuestos.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblExplorarPuestos.setHorizontalAlignment(SwingConstants.CENTER);
		pnlDatos.add(lblExplorarPuestos, BorderLayout.NORTH);

		btnX.addActionListener((ActionListener) new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mostrarSiguientePuesto();
			}
		});

		puestosCandidatos = CrearTreeSetPuestos();
		iteradorPuestos = puestosCandidatos.iterator();
//	    System.out.println(puestosCandidatos);
		Empresa primeraEmpresa = servicio.getEmpresaFromPuesto(puestosCandidatos.first());
		lblNombreUsu.setText(primeraEmpresa.getNombre());
		DescripcionPuesto.setText(puestosCandidatos.first().getDescripcion());
		JLabel Imagen = ImagenesAzure.crearImagen(primeraEmpresa, 150, 150);
		pnlInfoUsu.add(Imagen, BorderLayout.CENTER);
		lblNombreUsu.setFont(new Font("Tahoma", Font.BOLD, 31));
		lblNombreUsu.setForeground(new Color(4, 32, 63));
		pnlAbajo.add(lblNombreUsu, BorderLayout.NORTH);
		lblPuesto.removeAll();
		lblPuesto.setText(puestosCandidatos.first().getNombre());

		pnlHabilidades.removeAll();

		for (Habilidad hab : puestosCandidatos.first().getHabilidadesReq()) {
			JButton btnHab = new JButton(hab.getNombre());
			pnlHabilidades.add(btnHab);
			btnHab.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(null, "Nivel requerido: " + hab.getDestreza(), "Nivel de destreza",
							JOptionPane.INFORMATION_MESSAGE);
				}
			});
		}

	}

	

	public void xArojo() {
		try {
			// Carga la imagen original desde el archivo en el paquete "imagenes"
			InputStream imageStream = PnlBotonera.class.getResourceAsStream("xRoja.png");
			BufferedImage originalImage = ImageIO.read(imageStream);

			// Redimensiona la imagen a un tamaño más pequeño (50x50 pixeles)
			int width = 40;
			int height = 40;
			Image scaledImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);

			// Convierte la imagen escalada en un BufferedImage
			BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g2d = resizedImage.createGraphics();
			g2d.drawImage(scaledImage, 0, 0, null);
			g2d.dispose();

			// Crea un JLabel y asigna la imagen escalada como ícono
			lblGrafExpX = new JLabel(new ImageIcon(resizedImage));
			lblGrafExpX.setBackground(new Color(240, 240, 240));
			lblGrafExpX.setBounds(20, 0, 38, 38);
			pnlPass.add(lblGrafExpX, BorderLayout.WEST);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public TreeSet<PuestoTrabajo> CrearTreeSetPuestos() {
		Persona usuarioP = (Persona) usuarioAutenticado;
		puestosCandidatos = new TreeSet<PuestoTrabajo>();
		TreeSet<PuestoTrabajo> puestosCandidatos = new TreeSet<PuestoTrabajo>(new Comparator<PuestoTrabajo>() {

			@Override
			public int compare(PuestoTrabajo o1, PuestoTrabajo o2) {
				int contador1 = 0;
				int contador2 = 0;
				for (int i = 0; i < usuarioP.getCurriculum().size(); i++) {
					for (int j = 0; j < o1.getHabilidadesReq().size(); j++) {
						if (usuarioP.getCurriculum().get(i).equals(o1.getHabilidadesReq().get(j))) {
							contador1++;
						}
					}
				}
				for (int i = 0; i < usuarioP.getCurriculum().size(); i++) {
					for (int j = 0; j < o2.getHabilidadesReq().size(); j++) {
						if (usuarioP.getCurriculum().get(i).equals(o2.getHabilidadesReq().get(j))) {
							contador2++;
						}
					}
				}
				return contador2 - contador1 + 1;
			}
		});
		for (Empresa e : servicio.getEmpresas()) {
			for (PuestoTrabajo p : e.getPuestos()) {
//				System.out.println(p);
				puestosCandidatos.add(p);
			}
		}
		return puestosCandidatos;
	}

	public TreeSet<Persona> crearTreeSetPersonas(PuestoTrabajo pt) {
		Empresa usuarioE = (Empresa) usuarioAutenticado;
		TreeSet<Persona> personasCandidatas = new TreeSet<Persona>(new Comparator<Persona>() {

			@Override
			public int compare(Persona o1, Persona o2) {
				int contador1 = 0;
				int contador2 = 0;

				for (Habilidad h : pt.getHabilidadesReq()) {
					for (Habilidad h1 : o1.getCurriculum()) {
						if (h.equals(h1)) {
							contador1++;
						}
					}
				}

				for (PuestoTrabajo pt : usuarioE.getPuestos()) {
					for (Habilidad h : pt.getHabilidadesReq()) {
						for (Habilidad h2 : o2.getCurriculum()) {
							if (h.equals(h2)) {
								contador2++;
							}
						}
					}
				}

				return contador2 - contador1 + 1;
			}

		});
		for (Persona p : VentanaPrincipal.servicio.getPersonas()) {
			personasCandidatas.add(p);
		}
		return personasCandidatas;
	}

	public HashMap<PuestoTrabajo, TreeSet<Persona>> crearMapaPersonasPorPuesto() {
		HashMap<PuestoTrabajo, TreeSet<Persona>> mapaPersonasPorPuesto = new HashMap<PuestoTrabajo, TreeSet<Persona>>();
		Empresa usuarioE = (Empresa) usuarioAutenticado;
		for (PuestoTrabajo pt : usuarioE.getPuestos()) {
			mapaPersonasPorPuesto.put(pt, crearTreeSetPersonas(pt));
		}
		System.err.println("Mapa Personas Puesto: " + mapaPersonasPorPuesto);
		return mapaPersonasPorPuesto;
	}

	private void mostrarSiguientePuesto() {
		if (iteradorPuestos.hasNext()) {
			PuestoTrabajo puestoActual = iteradorPuestos.next();
			empresaActual = servicio.getEmpresaFromPuesto(puestoActual);
//			System.out.println(empresaPertenece);
//			System.out.println(empresaPertenece.getNombre());
//			System.out.println(empresaPertenece.getDescripcion());
//			System.out.println(puestoActual.getDescripcion());

			lblNombreUsu.setText(empresaActual.getNombre());
			lblNomEInfo.setText(empresaActual.getNombre());
			DescripcionPuesto.setText(puestoActual.getDescripcion());
			// Cambiar
			JLabel Imagen = ImagenesAzure.crearImagen(empresaActual, 150, 150);
			pnlInfoUsu.add(Imagen, BorderLayout.CENTER);
			lblNombreUsu.setFont(new Font("Tahoma", Font.BOLD, 31));
			lblNombreUsu.setForeground(new Color(4, 32, 63));
			pnlAbajo.add(lblNombreUsu, BorderLayout.NORTH);

			lblPuesto.removeAll();
			lblPuesto.setText(puestoActual.getNombre());

			pnlHabilidades.removeAll();

			for (Habilidad hab : puestoActual.getHabilidadesReq()) {
				JButton btnHab = new JButton(hab.getNombre());
				pnlHabilidades.add(btnHab);
				btnHab.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						JOptionPane.showMessageDialog(null, "Nivel requerido: " + hab.getDestreza(),
								"Nivel de destreza", JOptionPane.INFORMATION_MESSAGE);

					}
				});
			}

			pnlHabilidades.repaint();

		} else {
			System.out.println("No quedan puestos. ");
			;
		}
	}


	public static void main(String[] args) {
		ServicioPersistencia servicio = new ServicioPersistenciaFicheros();
		servicio.init();
// 		System.out.println(servicio.getPersonas().size());
		JFrame frame = new JFrame();
		frame.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				servicio.close();
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		frame.getContentPane().add(new PnlExplorarPersona(servicio.getPersonas().get(3), servicio));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setSize(750, 650);
		// frame.getContentPane().add(new
		// PnlExplorar(DatosFicheros.getEmpresas().get(0)));
		frame.setVisible(true);
	}
}
