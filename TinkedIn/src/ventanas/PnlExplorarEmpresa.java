package ventanas;

import javax.swing.JPanel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

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
import java.util.TreeSet;
import java.util.Vector;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import clases.Habilidad;
import clases.PuestoTrabajo;
import componentes.botonAnEl;
import servidor.ServicioPersistencia;
import sistemaExplorar.Like;
import usuarios.Empresa;
import usuarios.Persona;
import usuarios.Usuario;
import componentes.botonLike;
import componentes.botonX;
import nube.ImagenesAzure;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.CardLayout;

public class PnlExplorarEmpresa extends JPanel {

	protected static DefaultListModel<PuestoTrabajo> modeloListaPt;

	private static final long serialVersionUID = 1L;


	private JPanel pnlInicial;
	private PuestoTrabajo puestoElegido;
	protected static Empresa usuarioAutenticado;
	protected ServicioPersistencia servicio;
	protected SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	protected CardLayout clPaneles;
	protected Persona personaActual;
	protected HashMap<PuestoTrabajo,String> mapaPaneles = new HashMap<>();
	protected boolean pnlLogoOn = true;
	protected JList<PuestoTrabajo> listaPuestos;
	protected JPanel panelesPuestos;
	protected JFrame vent;
	protected ArrayList<PanelExploracion> listaPaneles = new ArrayList<>();

	public PnlExplorarEmpresa(Empresa empr, ServicioPersistencia servicio) {
		setLayout(new BorderLayout());
		setBackground(Color.WHITE);

		setLayout(new BorderLayout(0, 0));
		
		
		this.usuarioAutenticado = empr;
		this.servicio = servicio;

		
		JPanel pnlContenido = new JPanel();
		pnlContenido.setLayout(new BorderLayout());
		pnlContenido.setBackground(Color.WHITE);
		pnlContenido.setPreferredSize(new Dimension(getWidth() - 250, getHeight()));
		add(pnlContenido, BorderLayout.CENTER);


		JPanel pnlLista = new JPanel();
		pnlLista.setLayout(new BorderLayout());
		pnlLista.setBackground(new Color(129, 186, 207));

		
		pnlInicial = new JPanel();
		clPaneles = new CardLayout();
		pnlInicial.setLayout(clPaneles);
		pnlContenido.add(pnlInicial);
		
		JPanel pnlLogo = new JPanel(new BorderLayout());
		pnlLogo.setBackground(new Color(202, 232, 232));
		
		ImageIcon icono = new ImageIcon("TinkedinPNG.png");
		ImageIcon iconoRedimensionado = new ImageIcon(getScaledImage(icono.getImage(), 350, 300));
		
		JLabel lblLogo = new JLabel(iconoRedimensionado);
		lblLogo.setHorizontalAlignment(JLabel.CENTER);
        lblLogo.setVerticalAlignment(JLabel.CENTER);
        pnlLogo.add(lblLogo,BorderLayout.CENTER);
        
        pnlInicial.add( pnlLogo,"pnlLogo" );
        
        panelesPuestos = new JPanel();
        CardLayout clPanelesExplorar = new CardLayout();
        panelesPuestos.setLayout(clPanelesExplorar);
        pnlInicial.add(panelesPuestos, "panelesPuestos");
		
		listaPuestos = new JList<PuestoTrabajo>();
		modeloListaPt = new DefaultListModel<PuestoTrabajo>();

		for (PuestoTrabajo pt : empr.getPuestos()) {
			System.out.println(pt.getHabilidadesReq());
			modeloListaPt.addElement(pt);
			PanelExploracion pe = new PanelExploracion(empr, servicio, pt);
			panelesPuestos.add(pe, pt.hashCode()+"");
			listaPaneles.add(pe);
			mapaPaneles.put(pt,pt.hashCode()+"");
		}

		listaPuestos.setModel(modeloListaPt);
		listaPuestos.setBackground(new Color(202, 232, 232));
		
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
					if (pnlLogoOn) {
						clPaneles.show(pnlInicial, "panelesPuestos");
						pnlLogoOn = false;
					}
					clPanelesExplorar.show(panelesPuestos, puestoElegido.hashCode()+"");
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
				vent = new JFrame();
				vent.setSize(750,600);
				vent.setLocationRelativeTo(null);
				vent.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				vent.add(new pnlPuestoDeTrabajo(PnlExplorarEmpresa.this));
				vent.setVisible(true);
			}});
		


		listaPuestos.setCellRenderer(new DefaultListCellRenderer() {
			private static final long serialVersionUID = 1L;
			JPanel pnl;
			JLabel lbl1;

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
				pnl.add(lbl1);
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


		btnEliminar.addActionListener( (ActionListener) new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println(modeloListaPt.size());
				if (puestoElegido != null) {
					modeloListaPt.removeElement(puestoElegido);
					listaPuestos.repaint();
					servicio.deletePuesto((int)PnlBotonera.usuarioAutenticado.getId(), puestoElegido.getNombre(), puestoElegido.getDescripcion());
					mapaPaneles.remove(puestoElegido);
					clPanelesExplorar.show(panelesPuestos, mapaPaneles.get(modeloListaPt.get(0)));
					puestoElegido = null;
				}else {System.out.println("Nada seleccionado");}
			}
			
		});
        
		
		clPaneles.show(pnlInicial, "pnlLogo");
	}

	private Image getScaledImage(Image srcImg, int width, int height) {
        BufferedImage resizedImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resizedImg.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.drawImage(srcImg, 0, 0, width, height, null);
        g2d.dispose();
        return resizedImg;
    }
	
	public void anadirPuesto(PuestoTrabajo puesto) {
		modeloListaPt.addElement(puesto);
		listaPuestos.repaint();
		panelesPuestos.add(new PanelExploracion(usuarioAutenticado, servicio, puesto), puesto.hashCode()+"");
		mapaPaneles.put(puesto,puesto.hashCode()+"");
		vent.dispose();
	}
	
	public static void main(String[] args) {
		System.out.println("Creando pnlExplorar");
		ServicioPersistencia servicio = new ServicioPersistencia();
		servicio.init();
		JFrame frame = new JFrame();
		Vector<Empresa> empresasVc = servicio.getEmpresas();
		Empresa empr = empresasVc.get(3);
		System.out.println("Correo: " + empr.getCorreo());
		System.out.println("Contraseña: " + empr.getPassword());
		frame.getContentPane().add(new PnlExplorarEmpresa(empr, servicio));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(750, 650);
		frame.setVisible(true);

	}
	
	private static class PanelExploracion extends JPanel{
		private static final long serialVersionUID = 1L;
		private JPanel pnlLike;
		private JPanel pnlPass;
		private JPanel pnlInfoUsu;
		private JPanel pnlDatosPers;
		private JPanel pnlDatos;
		protected JLabel lblNombreUsu;
		protected JLabel lblPuesto;
		protected JLabel FotoPf;
		protected JLabel lblNombreDatosPer;
		protected JLabel lblApellidosDatosPer;
		protected JLabel lblFechaDatosPer;
		protected JLabel lblUbicacionDatosPer;
		protected Empresa empresaAutenticada;
		protected TreeSet<Persona> tsPersonas;
		protected Iterator<Persona> iteradorPersonas;
		protected ServicioPersistencia servicio;
		protected SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		protected Persona personaActual;
		protected DefaultListModel<Habilidad> modeloHP;
		protected PuestoTrabajo puestoTrabajo;

		public PanelExploracion(Empresa empr, ServicioPersistencia servicio, PuestoTrabajo puestoTrabajo) {
			setLayout(new BorderLayout());
			setBackground(Color.WHITE);

			this.servicio = servicio;
			this.empresaAutenticada = empr;
			this.puestoTrabajo = puestoTrabajo;

			tsPersonas = crearTreeSetPersonas(puestoTrabajo);
			iteradorPersonas = tsPersonas.iterator();
			System.err.println(tsPersonas.size());

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
			pnlInfoUsu.setPreferredSize(new Dimension(186, 207));
			pnlInfoUsu.setLayout(new BorderLayout());

			pnlInfo.add(pnlInfoUsu, BorderLayout.CENTER);

			JPanel pnlBotonera = new JPanel();
			pnlBotonera.setLayout(new GridLayout(0, 3));
			pnlBotonera.setPreferredSize(new Dimension(getWidth() - 250, 120));
			pnlContenido.add(pnlBotonera, BorderLayout.SOUTH);

			pnlLike = new JPanel();
			pnlLike.setLayout(new BorderLayout());
			pnlLike.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0-1));
			JPanel p = new JPanel(new BorderLayout());
			p.setPreferredSize(new Dimension(150,90));
			botonLike btnLike = new botonLike();
			btnLike.setVerticalAlignment(SwingConstants.CENTER);
			p.add(btnLike);
			pnlLike.add(p);
			pnlBotonera.add(pnlLike);

			btnLike.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					Like like = new Like(empr, personaActual);
					servicio.anadirLike(like);
					tsPersonas.remove(personaActual);
					mostrarSiguientePersona();
				}
			});

			JPanel pnlVacio = new JPanel();
			pnlVacio.setPreferredSize(new Dimension(10, 10));
			pnlBotonera.add(pnlVacio);
			pnlPass = new JPanel();
			pnlPass.setLayout(new FlowLayout());
			p = new JPanel(new BorderLayout());
			p.setPreferredSize(new Dimension(80,80));
			botonX btnX = new botonX();
			btnX.setVerticalAlignment(SwingConstants.CENTER);
			p.add(btnX);
			pnlPass.add(p);
			pnlBotonera.add(pnlPass);

			lblNombreUsu = new JLabel("Nombre de Usuario");
			lblNombreUsu.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 18));

			JLabel lblExplorarPersonas = new JLabel("Explora Trabajadores");
			lblExplorarPersonas.setFont(new Font("Tahoma", Font.BOLD, 30));
			lblExplorarPersonas.setHorizontalAlignment(SwingConstants.CENTER);

			pnlInfoUsu.setPreferredSize(new Dimension(100, pnlInfo.getHeight()));
			pnlInfo.repaint();
			btnX.addActionListener((ActionListener) new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					mostrarSiguientePersona();
				}
			});

			JPanel pnlInfoDatos = new JPanel();
			pnlInfoDatos.setLayout(new BorderLayout());

			pnlInfoUsu.add(pnlInfoDatos);

			JPanel pnlInfoHabi = new JPanel();
			pnlInfoHabi.setPreferredSize(new Dimension(258, 0));
			JList<Habilidad> habilidadesPersona = new JList<>();
			habilidadesPersona.setBorder(new LineBorder(new Color(129, 186, 207), 3));
			modeloHP = new DefaultListModel<>();
			habilidadesPersona.setModel(modeloHP);
			JScrollPane spListaHP = new JScrollPane(habilidadesPersona);
			spListaHP.setPreferredSize(new Dimension(250, 300));
			pnlInfoHabi.add(spListaHP);

			pnlInfoDatos.add(pnlInfoHabi, BorderLayout.EAST);

			JPanel pnlInfoPersonal = new JPanel();
			pnlInfoPersonal.setPreferredSize(new Dimension(240, 103));

			JLabel lblTitInfP = new JLabel("INFORMACIÓN PERSONAL");
			lblTitInfP.setForeground(new Color(4, 32, 63));
			pnlInfoPersonal.add(lblTitInfP, BorderLayout.NORTH);

			pnlDatosPers = new JPanel();
			JPanel pnlParaNombre = new JPanel();
			pnlDatosPers.setPreferredSize(new Dimension(200, 280));
			pnlDatosPers.setLayout(new BorderLayout());
			pnlDatosPers.add(pnlParaNombre, BorderLayout.SOUTH);
			pnlInfoPersonal.add(pnlDatosPers, BorderLayout.CENTER);
			FotoPf = new JLabel();
			lblNombreDatosPer = new JLabel("");
			lblNombreDatosPer.setFont(new Font("Segoe UI Black", Font.BOLD, 18));
			lblApellidosDatosPer = new JLabel("");
			lblApellidosDatosPer.setFont(new Font("Segoe UI Black", Font.BOLD, 18));
			lblFechaDatosPer = new JLabel("");
			lblUbicacionDatosPer = new JLabel("");
			lblUbicacionDatosPer.setForeground(new Color(150, 150, 150));

			pnlDatosPers.add(FotoPf, BorderLayout.CENTER);
			pnlParaNombre.add(lblNombreDatosPer, BorderLayout.SOUTH);
			pnlParaNombre.add(lblApellidosDatosPer, BorderLayout.SOUTH);
			pnlInfoPersonal.add(lblFechaDatosPer, BorderLayout.SOUTH);
			pnlInfoPersonal.add(lblUbicacionDatosPer, BorderLayout.SOUTH);

			pnlDatos = new JPanel();
			pnlDatos.setBackground(new Color(208, 235, 242));
			pnlDatos.setLayout(new BorderLayout());
			pnlDatos.setPreferredSize(new Dimension(getWidth() - 250, 125));
			pnlContenido.add(pnlDatos, BorderLayout.NORTH);

			lblPuesto = new JLabel("");
			lblPuesto.setForeground(new Color(192, 192, 192));
			lblPuesto.setFont(new Font("Tahoma", Font.BOLD, 20));
			lblPuesto.setHorizontalAlignment(SwingConstants.CENTER);
			pnlDatos.add(lblPuesto, BorderLayout.CENTER);
			pnlDatos.add(lblExplorarPersonas, BorderLayout.NORTH);

			pnlInfoDatos.add(pnlInfoPersonal, BorderLayout.WEST);

			habilidadesPersona.addListSelectionListener((ListSelectionListener) new ListSelectionListener() {
				@Override
				public void valueChanged(ListSelectionEvent e) {
					if (!e.getValueIsAdjusting()) {
						int indiceSeleccionado = habilidadesPersona.getSelectedIndex();
						Habilidad habilidadSeleccionada = (Habilidad) modeloHP.getElementAt(indiceSeleccionado);
						int destreza = habilidadSeleccionada.getDestreza();
						JOptionPane.showMessageDialog(null, "Nivel de destreza: " + destreza, "Nivel de destreza",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
			});
			mostrarSiguientePersona();
		}

		public TreeSet<Persona> crearTreeSetPersonas(PuestoTrabajo pt) {
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
						for (Habilidad h2 : o2.getCurriculum()) {
							if (h.equals(h2)) {
								contador2++;
							}
						}
					}
					if (contador2 != contador1) {
						return contador2 - contador1;
					}else if (contador1 == 0 && contador2 == 0){
						return 0;
					}else {
						return 1;
					}
				}
			});
			for (Persona p : servicio.getPersonas()) {
				personasCandidatas.add(p);
			}
			return personasCandidatas;
		}

		private void mostrarSiguientePersona() {
			if (iteradorPersonas != null && iteradorPersonas.hasNext()) {
				modeloHP.clear();
				personaActual = iteradorPersonas.next();
				lblNombreUsu.setText(personaActual.getNombre());
				lblNombreDatosPer.setText(personaActual.getNombre());
				lblApellidosDatosPer.setText(personaActual.getApellidos());
				lblFechaDatosPer.setText(sdf.format(personaActual.getEdad()));
				lblUbicacionDatosPer.setText(personaActual.getUbicacion());
				pnlDatosPers.remove(1);
				JLabel Imagen = ImagenesAzure.crearImagen(personaActual, 150, 150);
				FotoPf = Imagen;
				pnlDatosPers.add(FotoPf);
				pnlDatosPers.repaint();
				llenarListaHabilidades(personaActual);
				repaint();
			} else {
				iteradorPersonas = tsPersonas.iterator();
				mostrarSiguientePersona();
			}

		}

		private void llenarListaHabilidades(Persona p) {
			ArrayList<Habilidad> habilidades = p.getCurriculum();
			System.out.println("Habilidades: " + habilidades);
			for (Habilidad h : habilidades) {
				modeloHP.addElement(h);
			}
		}
		
		private void actualizarPanel() {
			tsPersonas = crearTreeSetPersonas(puestoTrabajo);
			iteradorPersonas = tsPersonas.iterator();
			mostrarSiguientePersona();
			repaint();
		}
	}

	public void actualizar() {
		for (PanelExploracion pe : listaPaneles) {
			pe.actualizarPanel();
		}
	}
}
