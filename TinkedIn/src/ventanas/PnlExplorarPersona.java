package ventanas;

import javax.swing.JPanel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;
import javax.swing.border.LineBorder;
import clases.Habilidad;
import clases.PuestoTrabajo;
import nube.ImagenesAzure;
import servidor.ServicioPersistencia;
import sistemaExplorar.Like;
import usuarios.Empresa;
import usuarios.Persona;
import usuarios.Usuario;
import componentes.botonLike;
import componentes.botonX;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.BorderLayout;

public class PnlExplorarPersona extends JPanel {
	private static final long serialVersionUID = 1L;

	private JLabel lblGrafExpX;
	private JPanel pnlLike;
	private JPanel pnlPass;
	private JPanel pnlInfoUsu;
	private JPanel pnlAbajo;
	private JPanel pnlDatos;
	private JPanel pnlHabilidades;
	protected JLabel lblNombreUsu;
	protected JLabel lblNomEInfo;
	protected JLabel lblDescrPInfo;
	protected JLabel lblPuesto;
	protected JTextPane DescripcionPuesto;
	protected static Usuario usuarioAutenticado;
	protected static TreeSet<PuestoTrabajo> puestosCandidatos;
	protected static Iterator<PuestoTrabajo> iteradorPuestos;
	protected ServicioPersistencia servicio;
	protected Empresa empresaActual = null;
	protected PuestoTrabajo puestoActual = null;
	protected JLabel fotoPerfil;

	public PnlExplorarPersona(Persona pers, ServicioPersistencia servicio) {
		setLayout(new BorderLayout());
		setBackground(Color.WHITE);

		setLayout(new BorderLayout());

		this.usuarioAutenticado = pers;
		this.servicio = servicio;

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
		
		fotoPerfil = new JLabel();
		pnlInfoUsu.add(fotoPerfil,BorderLayout.CENTER);
		
		pnlInfo.add(pnlInfoUsu, BorderLayout.CENTER);

		pnlDatos = new JPanel();
		pnlDatos.setBackground(new Color(208, 235, 242));
		pnlDatos.setLayout(new BorderLayout());
		pnlDatos.setPreferredSize(new Dimension(getWidth() - 250, 125));
		pnlContenido.add(pnlDatos, BorderLayout.NORTH);

		lblPuesto = new JLabel("");
		lblPuesto.setForeground(new Color(192, 192, 192));
		lblPuesto.setFont(VentanaPrincipal.fuente_titulos);
		lblPuesto.setHorizontalAlignment(SwingConstants.CENTER);
		pnlDatos.add(lblPuesto, BorderLayout.CENTER);

		JPanel pnlBotonera = new JPanel();
		pnlBotonera.setLayout(new GridLayout(0, 3));
		pnlBotonera.setPreferredSize(new Dimension(getWidth() - 250, 120));
		pnlContenido.add(pnlBotonera, BorderLayout.SOUTH);

		pnlLike = new JPanel();
		pnlLike.setLayout(new BorderLayout());
		pnlLike.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0 - 1));
		JPanel p = new JPanel(new BorderLayout());
		p.setPreferredSize(new Dimension(150, 90));
		botonLike btnLike = new botonLike();
		btnLike.setVerticalAlignment(SwingConstants.CENTER);
		p.add(btnLike);
		pnlLike.add(p);
		pnlBotonera.add(pnlLike);

		btnLike.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Like like = new Like(pers, empresaActual);
				servicio.anadirLike(like);
				System.out.println(puestoActual);
				System.out.println("Antes: " + puestosCandidatos.size());
				puestosCandidatos.remove(puestoActual);
				System.out.println("Despues: " + puestosCandidatos.size());
				mostrarSiguientePuesto();
			}
		});

		JPanel pnlVacio = new JPanel();
		pnlVacio.setPreferredSize(new Dimension(10, 10));
		pnlBotonera.add(pnlVacio);
		pnlPass = new JPanel();
		pnlPass.setLayout(new FlowLayout());
		p = new JPanel(new BorderLayout());
		p.setPreferredSize(new Dimension(80, 80));
		botonX btnX = new botonX();
		btnX.setVerticalAlignment(SwingConstants.CENTER);
		p.add(btnX);
		pnlPass.add(p);
		pnlBotonera.add(pnlPass);

		pnlPass.add(btnX, BorderLayout.CENTER);
		pnlBotonera.add(pnlPass);

		lblNombreUsu = new JLabel("      Nombre de Usuario");
		lblNombreUsu.setFont(VentanaPrincipal.fuente_titulos);

		JPanel pnlInfoPuesto = new JPanel();
		pnlInfoPuesto.setLayout(new BorderLayout());
		pnlInfoPuesto.setBackground(Color.WHITE);
		pnlInfoPuesto.setForeground(Color.BLUE);
		pnlInfoPuesto.setPreferredSize(new Dimension(400, 200));

		JLabel lblDescripcionPuesto = new JLabel("   Descripcion del puesto: ");
		lblDescripcionPuesto.setPreferredSize(new Dimension(pnlInfoPuesto.getWidth(), 60));
		lblDescripcionPuesto.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 21));

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

		pnlInfoPuesto.add(lblDescripcionPuesto, BorderLayout.NORTH);
		JLabel lblExplorarPuestos = new JLabel("Explora Puestos De Trabajo");
		lblExplorarPuestos.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 30));
		lblExplorarPuestos.setHorizontalAlignment(SwingConstants.CENTER);
		pnlDatos.add(lblExplorarPuestos, BorderLayout.NORTH);

		btnX.addActionListener((ActionListener) new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mostrarSiguientePuesto();
			}
		});

		puestosCandidatos = CrearTreeSetPuestos();
		System.out.println(puestosCandidatos);
		System.out.println("Size: " + puestosCandidatos.size());
		iteradorPuestos = puestosCandidatos.iterator();
		lblNombreUsu.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 25));
		lblNombreUsu.setForeground(new Color(4, 32, 63));
		lblPuesto.removeAll();
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
		mostrarSiguientePuesto();
	}
	
	public void actualizar() {
		puestosCandidatos = CrearTreeSetPuestos();
		iteradorPuestos = puestosCandidatos.iterator();
		mostrarSiguientePuesto();
		repaint();
	};

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
		TreeSet<PuestoTrabajo> puestos = new TreeSet<PuestoTrabajo>(new Comparator<PuestoTrabajo>() {
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
					for (int y = 0; y < o2.getHabilidadesReq().size(); y++) {
						if (usuarioP.getCurriculum().get(i).equals(o2.getHabilidadesReq().get(y))) {
							contador2++;
						}
					}
				}
				if (contador2 != contador1) {
					return contador2 - contador1;
				}else if (contador2 == 0 && contador1 == 0) {
					return 0;
				}else {
					return 1;
				}
			}
		});
		for (Empresa e : servicio.getEmpresas()) {
			for (PuestoTrabajo p : e.getPuestos()) {
				puestos.add(p);
			}
		}
		return puestos;
	}

	private void mostrarSiguientePuesto() {
		if (iteradorPuestos.hasNext()) {
			puestoActual = iteradorPuestos.next();
			empresaActual = servicio.getEmpresaFromPuesto(puestoActual);
			lblNombreUsu.setText(empresaActual.getNombre());
			lblNomEInfo.setText(empresaActual.getNombre());
			DescripcionPuesto.setText(puestoActual.getDescripcion());
//			pnlInfoUsu.remove(1);
			JLabel Imagen = ImagenesAzure.crearImagen(empresaActual, 150, 150);
			fotoPerfil = Imagen;
			pnlInfoUsu.add(fotoPerfil, BorderLayout.CENTER);
			pnlInfoUsu.repaint();
			lblNombreUsu.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 27));
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
			iteradorPuestos = puestosCandidatos.iterator();
			mostrarSiguientePuesto();
		}
	}

	public static void main(String[] args) {
		ServicioPersistencia servicio = new ServicioPersistencia();
		servicio.init();
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
