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
	private JPanel pnlAbajo;
	private JPanel pnlDatos;
	private PuestoTrabajo puestoElegido;
	protected JLabel lblNombreUsu;
	protected JLabel lblNomEInfo;
	protected JLabel lblDescrPInfo;
	protected JLabel lblPuesto;
	protected static Usuario usuarioAutenticado;
	protected static HashMap<PuestoTrabajo, TreeSet<Persona>> mapaPersonasPorPuesto;
	protected static HashMap<PuestoTrabajo, Iterator<Persona>> mapaIteradorPersonas;
	protected ServicioPersistencia servicio;

	public PnlExplorarEmpresa(Empresa e, ServicioPersistencia servicio) {
		setLayout(new BorderLayout());
		setBackground(Color.WHITE);

		setLayout(new BorderLayout(0, 0));
		
		mapaIteradorPersonas = new HashMap<>();
		this.usuarioAutenticado = e;
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
		pnlInfoUsu.setLayout(new BorderLayout());
		pnlAbajo = new JPanel();
		pnlAbajo.setBackground(pnlInfoUsu.getBackground());
		pnlAbajo.setPreferredSize(new Dimension(pnlInfoUsu.getWidth(), 100));
		pnlInfoUsu.add(pnlAbajo, BorderLayout.SOUTH);

		pnlInfo.add(pnlInfoUsu, BorderLayout.CENTER);

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

		JPanel pnlBotonera = new JPanel();
		pnlBotonera.setLayout(new GridLayout(0, 3));
//	 	        pnlBotonera.setBackground( Color.BLUE);
		pnlBotonera.setPreferredSize(new Dimension(getWidth() - 250, 120));
		pnlContenido.add(pnlBotonera, BorderLayout.SOUTH);
//	 	        System.out.println( tipoUsuario.getClass() );

		pnlLike = new JPanel();
		pnlLike.setLayout(new BorderLayout());
		JPanel pnlBotonLike = new JPanel();
		pnlBotonLike.setPreferredSize(new Dimension(100, 100));
		botonLike btnLike = new botonLike();
		pnlBotonLike.add(btnLike);
		pnlLike.add(pnlBotonLike, BorderLayout.CENTER);
		pnlBotonera.add(pnlLike);

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
		pnlDatos.add(lblExplorarPersonas, BorderLayout.NORTH);

		pnlInfoUsu.setPreferredSize(new Dimension(100, pnlInfo.getHeight()));
//     	pnlInfo.remove(pnlInfoPuesto);
		JPanel pnlCurriculum = new JPanel();
		pnlCurriculum.setPreferredSize(new Dimension(pnlInfo.getWidth() - 100, pnlInfo.getHeight()));
		pnlInfo.add(pnlCurriculum, BorderLayout.WEST);
		pnlInfo.repaint();

//       	Empresa e = (Empresa) usuarioAutenticado;
		mapaPersonasPorPuesto = crearMapaPersonasPorPuesto();

		JPanel pnlLista = new JPanel();
		pnlLista.setLayout(new BorderLayout());
		pnlLista.setBackground(new Color(129, 186, 207));

		JList<PuestoTrabajo> listaPuestos = new JList<PuestoTrabajo>();
		modeloListaPt = new DefaultListModel<PuestoTrabajo>();

		for (PuestoTrabajo pt : e.getPuestos()) {
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
		pnlBotoneraIzq.add(new botonAnEl("Añadir"));
		pnlBotoneraIzq.add(new botonAnEl("Eliminar"));

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
			PuestoTrabajo puestoActual = puestoElegido;
			Iterator<Persona> iterador = mapaIteradorPersonas.get(puestoElegido);
			System.out.println(mapaIteradorPersonas.get(puestoElegido));
			System.out.println(mapaIteradorPersonas.keySet());
			System.out.println(puestoElegido);
			System.out.println(mapaIteradorPersonas);
			if (iterador.hasNext()) {
				Persona personaActual = iterador.next();
				System.err.println(personaActual);
				lblNombreUsu.setText(personaActual.getNombre());
			} else {
				System.out.println("No quedan puestos. ");
				;
			}

		}
	}

	public static void main(String[] args) {
		ServicioPersistencia servicio = new ServicioPersistenciaFicheros();
		servicio.init();
// 		System.out.println(servicio.getPersonas().size());
		JFrame frame = new JFrame();
		frame.getContentPane().add(new PnlExplorarEmpresa(servicio.getEmpresas().get(3), servicio));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(750, 650);
		// frame.getContentPane().add(new
		// PnlExplorar(DatosFicheros.getEmpresas().get(0)));
		frame.setVisible(true);

	}
}
