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
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
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
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.DropMode;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.CardLayout;

public class PnlExplorarEmpresa extends JPanel {

	protected static DefaultListModel<PuestoTrabajo> modeloListaPt;

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */

	private JLabel lblGrafExpX;
	private JPanel pnlLike;
	private JPanel pnlPass;
	private JPanel pnlInfoUsu;
	private JPanel pnlDatos;
	private PuestoTrabajo puestoElegido;
	protected JLabel lblNombreUsu;
	protected JLabel lblNomEInfo;
	protected JLabel lblDescrPInfo;
	protected JLabel lblPuesto;
	protected JLabel lblNombreDatosPer;
	protected JLabel lblApellidosDatosPer;
	protected JLabel lblFechaDatosPer;
	protected JLabel lblUbicacionDatosPer;
	protected DefaultListModel<Habilidad> modeloHP;
	protected static Usuario usuarioAutenticado;
	protected static HashMap<PuestoTrabajo, TreeSet<Persona>> mapaPersonasPorPuesto;
	protected static HashMap<PuestoTrabajo, Iterator<Persona>> mapaIteradorPersonas;
	protected ServicioPersistencia servicio;
	protected SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	protected CardLayout clPaneles;
	protected Persona personaActual
	
;

	public PnlExplorarEmpresa(Empresa empr, ServicioPersistencia servicio) {
		setLayout(new BorderLayout());
		setBackground(Color.WHITE);

		setLayout(new BorderLayout(0, 0));
		
		mapaIteradorPersonas = new HashMap<>();
		
		this.usuarioAutenticado = empr;
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
//	 	        pnlInfo.setBackground( Color.GREEN );
		// pnlInfo.setBackground( Color.GREEN);
		pnlContenido.add(pnlInfo, BorderLayout.CENTER);

		pnlInfoUsu = new JPanel();
		pnlInfoUsu.setBackground(new Color(129, 186, 207));
		pnlInfoUsu.setPreferredSize( new Dimension(186,207));
		pnlInfoUsu.setLayout(new BorderLayout());


		pnlInfo.add(pnlInfoUsu, BorderLayout.CENTER);


		JPanel pnlBotonera = new JPanel();
		pnlBotonera.setLayout(new GridLayout(0, 3));
//	 	        pnlBotonera.setBackground( Color.BLUE);
		pnlBotonera.setPreferredSize(new Dimension(getWidth() - 250, 120));
		pnlContenido.add(pnlBotonera, BorderLayout.SOUTH);
//	 	        System.out.println( tipoUsuario.getClass() );

		pnlLike = new JPanel();
		pnlLike.setLayout(new BorderLayout());
		pnlLike.setLayout(new FlowLayout());
		pnlLike.setPreferredSize( new Dimension(150,70));
		botonLike btnLike = new botonLike();
//		btnLike.setPreferredSize( new Dimension(70,70));
		pnlLike.add(btnLike, BorderLayout.CENTER);
		pnlBotonera.add(pnlLike);
		
		btnLike.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Like like = new Like(empr, personaActual);
				servicio.anadirLike(like);
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

		} catch (IOException e1) {
			e1.printStackTrace();
		}

		

		// botonCorazon btnCorazon = new botonCorazon();
		// pnlLike.add(btnCorazon, BorderLayout.EAST);
		lblNombreUsu = new JLabel("      Nombre de Usuario");
		lblNombreUsu.setFont(new Font("Segoe UI Black", Font.BOLD, 18));
		// pnlDatos.add(lblNombreUsu);

//	         if( usuarioAutenticado instanceof Empresa ) {
		JLabel lblExplorarPersonas = new JLabel("Explora Trabajadores");
		lblExplorarPersonas.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblExplorarPersonas.setHorizontalAlignment(SwingConstants.CENTER);
		

		pnlInfoUsu.setPreferredSize(new Dimension(100, pnlInfo.getHeight()));
		pnlInfo.repaint();

//       	Empresa e = (Empresa) usuarioAutenticado;
		mapaPersonasPorPuesto = crearMapaPersonasPorPuesto();
		

		JPanel pnlLista = new JPanel();
		pnlLista.setLayout(new BorderLayout());
		pnlLista.setBackground(new Color(129, 186, 207));

		JList<PuestoTrabajo> listaPuestos = new JList<PuestoTrabajo>();
		modeloListaPt = new DefaultListModel<PuestoTrabajo>();

		for (PuestoTrabajo pt : empr.getPuestos()) {
			modeloListaPt.addElement(pt);
		}

		listaPuestos.setModel(modeloListaPt);
		listaPuestos.setBackground(new Color(202, 232, 232));
		

		

//		listaPuestos.addListSelectionListener((ListSelectionListener) new ListSelectionListener() {
//
//			@Override
//			public void valueChanged(ListSelectionEvent e) {
//				puestoElegido = listaPuestos.getSelectedValue();
//				System.err.println("Puesto Elegido: " + puestoElegido);
//
//			}
//		});
		
		
		listaPuestos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                listaPuestos.clearSelection();
            }

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				int index = listaPuestos.locationToIndex(e.getPoint());
				if (index != -1) {
					puestoElegido  = modeloListaPt.get(index);
					System.err.println("Puesto Elegido: " + puestoElegido);
					clPaneles.show(pnlInfoUsu, "pnlInfoDatos");

				}
			}
        });
		
		listaPuestos.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int index = listaPuestos.locationToIndex(e.getPoint());
                if (index != -1) {
                    listaPuestos.setSelectedIndex(index);
                }
            }
        });
		
		btnX.addActionListener((ActionListener) new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mostrarSiguientePersona(puestoElegido);
			}
		});

		JScrollPane spLista = new JScrollPane(listaPuestos);
		spLista.setPreferredSize(new Dimension(250, getHeight()));
		spLista.setMaximumSize(new Dimension(250, getHeight()));
		spLista.setBackground(getBackground());
		pnlLista.add(spLista);

		add(pnlLista, BorderLayout.WEST);
		JPanel pnlBotoneraIzq = new JPanel();
		pnlBotoneraIzq.setBorder(BorderFactory.createBevelBorder(0));
		pnlBotoneraIzq.setLayout(new FlowLayout());
		pnlLista.add(pnlBotoneraIzq, BorderLayout.SOUTH);
		pnlBotoneraIzq.setPreferredSize(new Dimension(pnlLista.getWidth(), 75));
		botonAnEl btnAnadir = new botonAnEl("Añadir");
		botonAnEl btnEliminar = new botonAnEl("Eliminar");
		pnlBotoneraIzq.add(btnAnadir);
		pnlBotoneraIzq.add(btnEliminar);
		
		btnAnadir.addActionListener( (ActionListener) new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFrame vent = new JFrame();
				vent.setSize(750,600);
				vent.setLocationRelativeTo(null);
				vent.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				
				vent.add(new pnlPuestoDeTrabajo());
				vent.setVisible(true);
			}});

		listaPuestos.setCellRenderer(new DefaultListCellRenderer() {
			private static final long serialVersionUID = 1L;
			JPanel pnl;
			JLabel lbl1;
			JLabel lbl2;

			@Override
			public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				// TODO Auto-generated method stub

				pnl = new JPanel();
				pnl.setPreferredSize(new Dimension(pnlLista.getWidth() - 5, 70));
				pnl.setSize(200, 50);
				pnl.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, new Color(0, 64, 128)));
				if (isSelected) {
					pnl.setBackground(new Color(122, 199, 218));
				} else {
					pnl.setBackground(new Color(202, 232, 232));
					pnl.setForeground(list.getForeground());
				}
				lbl1 = new JLabel(value.toString());
				lbl2 = new JLabel("Numero de plazas");
				lbl2.setForeground(new Color(128, 128, 128));
				pnl.add(lbl1);
				pnl.add(lbl2);
				return pnl;
			}
		});

		listaPuestos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				listaPuestos.clearSelection();
			}
		});

		listaPuestos.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				int index = listaPuestos.locationToIndex(e.getPoint());
				if (index != -1) {
					listaPuestos.setSelectedIndex(index);
				}
			}
		});

		clPaneles = new CardLayout();
		pnlInfoUsu.setLayout(clPaneles);

		
		JPanel pnlLogo = new JPanel(new BorderLayout());
		pnlLogo.setBackground(new Color(202, 232, 232));
		
		ImageIcon icono = new ImageIcon("TinkedinPNG.png");
		ImageIcon iconoRedimensionado = new ImageIcon(getScaledImage(icono.getImage(), 350, 300));
		
		JLabel lblLogo = new JLabel(iconoRedimensionado);
		lblLogo.setHorizontalAlignment(JLabel.CENTER);
        lblLogo.setVerticalAlignment(JLabel.CENTER);
        pnlLogo.add(lblLogo,BorderLayout.CENTER);
        
        pnlInfoUsu.add( pnlLogo,"pnlLogo" );
        
        JPanel pnlInfoDatos = new JPanel();
        pnlInfoDatos.setLayout(new BorderLayout());
        pnlInfoUsu.add(pnlInfoDatos, "pnlInfoDatos");
        
		JPanel pnlInfoHabi = new JPanel();
		pnlInfoHabi.setPreferredSize( new Dimension(258, 0));
		JList<Habilidad> habilidadesPersona = new JList<>();
		modeloHP = new DefaultListModel<>();
		habilidadesPersona.setModel(modeloHP);
		JScrollPane spListaHP = new JScrollPane(habilidadesPersona);
		spListaHP.setPreferredSize( new Dimension(250,300));
		pnlInfoHabi.add(spListaHP);
		
		pnlInfoDatos.add(pnlInfoHabi, BorderLayout.EAST);
        
		JPanel pnlInfoPersonal = new JPanel();
		pnlInfoPersonal.setPreferredSize( new Dimension(240, 103));
		
		JLabel lblTitInfP = new JLabel("INFORMACIÓN PERSONAL");
		pnlInfoPersonal.add(lblTitInfP, BorderLayout.NORTH);
		
		JPanel pnlDatosPers = new JPanel();
		pnlDatosPers.setPreferredSize( new Dimension(200, 280));
		pnlDatosPers.setLayout( new BoxLayout(pnlDatosPers, BoxLayout.Y_AXIS));
		pnlInfoPersonal.add(pnlDatosPers, BorderLayout.CENTER);
		lblNombreDatosPer =  new JLabel("Prueba");
		lblApellidosDatosPer = new JLabel("Prueba apellidos");
		lblFechaDatosPer = new JLabel("Prueba Fecha");
		lblUbicacionDatosPer = new JLabel("Prueba ubicacion");
		
		
		pnlDatosPers.add(Box.createVerticalStrut(40));
		pnlDatosPers.add(lblNombreDatosPer);
		pnlDatosPers.add(Box.createVerticalStrut(40));
		pnlDatosPers.add(lblApellidosDatosPer);
		pnlDatosPers.add(Box.createVerticalStrut(40));
		pnlDatosPers.add( lblFechaDatosPer);
		pnlDatosPers.add(Box.createVerticalStrut(40));
		pnlDatosPers.add( lblUbicacionDatosPer );
		

		
		pnlDatos = new JPanel();
		pnlDatos.setBackground(new Color(208, 235, 242));
		pnlDatos.setLayout(new BorderLayout());
//	 	        pnlDatos.setBackground(Color.GREEN);
		pnlDatos.setPreferredSize(new Dimension(getWidth() - 250, 125));
		pnlContenido.add(pnlDatos, BorderLayout.NORTH);

		lblPuesto = new JLabel("");
		lblPuesto.setForeground(new Color(192, 192, 192));
		lblPuesto.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblPuesto.setHorizontalAlignment(SwingConstants.CENTER);
		pnlDatos.add(lblPuesto, BorderLayout.CENTER);
		pnlDatos.add(lblExplorarPersonas, BorderLayout.NORTH);
		
		pnlInfoDatos.add(pnlInfoPersonal, BorderLayout.WEST);
		
		clPaneles.show(pnlInfoUsu, "pnlLogo");
		

			
		habilidadesPersona.addListSelectionListener((ListSelectionListener) new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    // Obtener el índice seleccionado
                    int indiceSeleccionado = habilidadesPersona.getSelectedIndex();
                        // Obtener la habilidad seleccionada
                        Habilidad habilidadSeleccionada = modeloHP.getElementAt(indiceSeleccionado);
                        int destreza = habilidadSeleccionada.getDestreza();

                        JOptionPane.showMessageDialog(null, "Nivel de destreza: " + destreza, "Nivel de destreza", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
		
	

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
		for (Persona p : servicio.getPersonas()) {
			personasCandidatas.add(p);
		}
		return personasCandidatas;
	}

	public HashMap<PuestoTrabajo, TreeSet<Persona>> crearMapaPersonasPorPuesto() {
		HashMap<PuestoTrabajo, TreeSet<Persona>> mapaPersonasPorPuesto = new HashMap<PuestoTrabajo, TreeSet<Persona>>();
		Empresa usuarioE = (Empresa) usuarioAutenticado;
		for (PuestoTrabajo pt : usuarioE.getPuestos()) {
			TreeSet<Persona> tsPersona = crearTreeSetPersonas(pt);
			mapaPersonasPorPuesto.put(pt, tsPersona);
			mapaIteradorPersonas.put(pt, tsPersona.iterator());
		}
		System.err.println("Mapa Personas Puesto: " + mapaPersonasPorPuesto);
		System.err.println("Mapa Iterador: " + mapaIteradorPersonas);
		return mapaPersonasPorPuesto;
	}

	private void mostrarSiguientePersona(PuestoTrabajo puestoElegido) {
		if (modeloListaPt.getSize() > 0) {
			modeloHP.clear();
			Iterator<Persona> iterador = mapaIteradorPersonas.get(puestoElegido);
			System.out.println(mapaIteradorPersonas.get(puestoElegido));
			System.out.println(mapaIteradorPersonas.keySet());
			System.out.println(puestoElegido);
			System.out.println(mapaIteradorPersonas);
			if (iterador != null && iterador.hasNext()) {
				personaActual = iterador.next();
				System.err.println(personaActual);
				lblNombreUsu.setText(personaActual.getNombre());
				lblNombreDatosPer.setText(personaActual.getNombre());
				lblApellidosDatosPer.setText(personaActual.getApellidos());
				lblFechaDatosPer.setText(sdf.format(personaActual.getEdad()));
				lblUbicacionDatosPer.setText(personaActual.getUbicacion());
				llenarListaHabilidades(personaActual);
			} else {
				System.out.println("No quedan puestos. ");
				;
			}

		}
	}

	private void llenarListaHabilidades(Persona p) {
		ArrayList<Habilidad> habilidades = p.getCurriculum();
		System.out.println("Habilidades: " + habilidades);
		for (Habilidad h : habilidades) {
			modeloHP.addElement(h);
		}
	}

	private Image getScaledImage(Image srcImg, int width, int height) {
        BufferedImage resizedImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resizedImg.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.drawImage(srcImg, 0, 0, width, height, null);
        g2d.dispose();
        return resizedImg;
    }
	
	public static void main(String[] args) {
		System.out.println("Creando pnlExplorar");
		ServicioPersistencia servicio = new ServicioPersistenciaFicheros();
		servicio.init();
// 		System.out.println(servicio.getPersonas().size());
		JFrame frame = new JFrame();
		Vector<Empresa> empresasVc = servicio.getEmpresas();
		Empresa empr = empresasVc.get(3);
		System.out.println("Correo: " + empr.getCorreo());
		System.out.println("Contraseña: " + empr.getPassword());
		frame.getContentPane().add(new PnlExplorarEmpresa(empr, servicio));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(750, 650);
		// frame.getContentPane().add(new
		// PnlExplorar(DatosFicheros.getEmpresas().get(0)));
		frame.setVisible(true);

	}
}
