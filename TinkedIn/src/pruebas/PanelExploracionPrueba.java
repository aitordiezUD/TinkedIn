package pruebas;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.Vector;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import clases.Habilidad;
import clases.PuestoTrabajo;
import servidor.ServicioPersistencia;
import sistemaExplorar.Like;
import usuarios.Empresa;
import usuarios.Persona;
import componentes.botonLike;
import componentes.botonX;
import nube.ImagenesAzure;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;

public class PanelExploracionPrueba extends JPanel {
	private static final long serialVersionUID = 1L;
	protected static DefaultListModel<PuestoTrabajo> modeloListaPt;
	private JPanel pnlLike;
	private JPanel pnlPass;
	private JPanel pnlInfoUsu;
	private JPanel pnlDatosPers;
	private JPanel pnlDatos;
	protected JLabel lblNombreUsu;
	protected JLabel lblNomEInfo;
	protected JLabel lblDescrPInfo;
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

	public PanelExploracionPrueba(Empresa empr, ServicioPersistencia servicio, PuestoTrabajo puestoTrabajo) {
		setLayout(new BorderLayout());
		setBackground(Color.WHITE);
		setLayout(new BorderLayout(0, 0));

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
				mostrarSiguientePersona();
				tsPersonas.remove(personaActual);
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
				return contador2 - contador1 + 1;
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

	public static void main(String[] args) {
		ServicioPersistencia servicio = new ServicioPersistencia();
		servicio.init();
		Vector<Empresa> empresas = servicio.getEmpresas();
		Empresa empr = empresas.get(4);
		PuestoTrabajo puesto = empr.getPuestos().get(0);
		SwingUtilities.invokeLater(() -> {
			JFrame frame = new JFrame();
			frame.setSize(600, 700);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.add(new PanelExploracionPrueba(empr, servicio, puesto));
			frame.setVisible(true);
		});
	}

}
