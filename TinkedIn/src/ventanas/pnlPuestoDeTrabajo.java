package ventanas;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import clases.Habilidad;
import clases.PuestoTrabajo;
import componentes.SquareLabel;
import componentes.botonAceptar;
import componentes.botonAnEl;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.TreeMap;
import java.awt.BorderLayout;

public class pnlPuestoDeTrabajo extends JPanel {
	private JTree ArbolHabilidades;
	private JPanel pnlArbol;
	private TreeMap<String, ArrayList<String>> areasDeTrabajo;
	private DefaultTreeModel modeloArbol;
	private DefaultMutableTreeNode root;
	private DefaultListModel modeloLista;
	private DefaultListModel modeloListaPt;
	private JTextField tfNombrePuesto;
	
	public pnlPuestoDeTrabajo( ) {

		setSize(750,650);
		setLayout( new BorderLayout());
		
		areasDeTrabajo = new TreeMap<>();
		
		JPanel pnlTitulo = new JPanel();
		pnlTitulo.setBackground( Color.WHITE );
		pnlTitulo.setPreferredSize( new Dimension(getWidth(), 50));
		
		JLabel lblTit = new JLabel("CREAR PUESTOS DE TRABAJO");
		lblTit.setFont(new Font("Segoe UI Black", Font.BOLD, 18));
		pnlTitulo.add(lblTit);
		
		JPanel pnlCreador = new JPanel();
		pnlCreador.setBackground( Color.GREEN);
		pnlCreador.setLayout( new BorderLayout() );
		
		JPanel pnlArbolyLista = new JPanel();
		pnlArbolyLista.setLayout( new BorderLayout() );
		pnlArbolyLista.setPreferredSize( new Dimension( getWidth()/2, pnlCreador.getHeight()));
		pnlArbolyLista.setBackground( Color.WHITE );
		
		JPanel pnlEditHab = new JPanel();
		pnlEditHab.setLayout( new BorderLayout() );
		pnlEditHab.setPreferredSize( new Dimension( getWidth()/2, pnlCreador.getHeight()));
		
		JPanel pnlEditCampos = new JPanel();
		pnlEditCampos.setLayout(  new BoxLayout(  pnlEditCampos ,BoxLayout.Y_AXIS) );
		pnlEditCampos.setBackground( Color.WHITE );
		pnlEditCampos.setPreferredSize( new Dimension( getWidth()/2, getHeight()/3-pnlTitulo.getHeight()));
		
		JPanel pnlPreviewHab = new JPanel();
		pnlPreviewHab.setLayout( new BoxLayout(pnlPreviewHab, BoxLayout.Y_AXIS) );
		pnlPreviewHab.setPreferredSize( new Dimension(  getWidth()/2, getHeight()/3-pnlTitulo.getHeight() ) );
		pnlPreviewHab.setBackground( Color.GREEN );
		
		JPanel pnlTituloPreview = new JPanel();
		pnlTituloPreview.setPreferredSize( new Dimension(getWidth()/2, 30));
		pnlTituloPreview.setBorder( BorderFactory.createLineBorder(Color.BLACK) );
		JLabel lblTituloPreview = new JLabel( "VISTA PREVIA DE LA HABILIDAD" );
		lblTituloPreview.setFont(new Font("Segoe UI Black", Font.BOLD, 14));
		pnlTituloPreview.add(lblTituloPreview);
		
		JPanel pnlNombrePrev = new JPanel();
		pnlNombrePrev.setBorder( BorderFactory.createLineBorder( Color.BLACK));
		JLabel lblNombrePrev = new JLabel("Nombre de la habilidad: ");
		pnlNombrePrev.add(lblNombrePrev);
		
		JPanel pnlPrevDestreza = new JPanel();
		pnlPrevDestreza.setBorder( BorderFactory.createLineBorder( Color.BLACK ) );
		JLabel lblPrevDestreza = new JLabel( "Destreza: " );
		
		JPanel pnlPrevDescrHab = new JPanel();
		pnlPrevDescrHab.setBorder( BorderFactory.createLineBorder( Color.BLACK ) ); 
		JLabel lblPreviewDescr = new JLabel("Descripcion: ");
		pnlPrevDescrHab.add(lblPreviewDescr);
		
		JPanel pnlBtnConfirm = new JPanel();
		pnlBtnConfirm.add( new botonAceptar("Confirmar habilidad"));
		
		pnlPrevDestreza.add(lblPrevDestreza);
		
		pnlPreviewHab.add(pnlTituloPreview);
		pnlPreviewHab.add(pnlNombrePrev);
		pnlPreviewHab.add(pnlPrevDestreza);
		pnlPreviewHab.add(pnlPrevDescrHab);
		pnlPrevDestreza.add( new SquareLabel(0) );
		pnlPreviewHab.add(pnlBtnConfirm);
		
		
		JPanel pnlSpinner = new JPanel();
		pnlSpinner.setBorder( BorderFactory.createLineBorder( Color.BLACK ) );
		pnlSpinner.setLayout( new FlowLayout() );
		JPanel pnlDescrHab = new JPanel();
		pnlDescrHab.setLayout( new FlowLayout() );
		pnlDescrHab.setBorder( BorderFactory.createLineBorder( Color.BLACK) );
		
		JLabel lblDestreza = new JLabel("Destreza: ");
		SpinnerNumberModel modelSp = new SpinnerNumberModel(0, 0, 5, 1);
		JSpinner spDestreza = new JSpinner( modelSp );
		
		JLabel lblDescrHab = new JLabel("Descripcion: ");
		JTextArea taDescrHab = new JTextArea();
		JScrollPane spDescrHab = new JScrollPane(taDescrHab);
		spDescrHab.setPreferredSize( new Dimension(100,40) );
		pnlDescrHab.add(lblDescrHab);
		pnlDescrHab.add(spDescrHab);
		pnlSpinner.add(lblDestreza);
		pnlSpinner.add( spDestreza );
		
		pnlEditCampos.add( pnlSpinner );
		pnlEditCampos.add( pnlDescrHab );
		JPanel pnlBotonAc = new JPanel();
		pnlBotonAc.setPreferredSize( new Dimension(getWidth()/2, 35));
		pnlBotonAc.setBackground( Color.WHITE );
		pnlBotonAc.setLayout( new FlowLayout() );
		
		pnlBotonAc.add( new botonAceptar("Aceptar") );
		
		pnlEditHab.setBackground( Color.BLUE );
		
		JPanel pnlLista = new JPanel();
		pnlLista.setBackground( Color.WHITE );
		pnlLista.setPreferredSize( new Dimension(getWidth()/4, pnlCreador.getHeight()) );
		
		pnlArbol = new JPanel();
		pnlArbol.setBackground( Color.BLUE);
		pnlArbol.setPreferredSize( new Dimension( getWidth()/4, pnlCreador.getHeight() ));
		
		JPanel pnlBotoneraIzq = new JPanel();
	 	pnlBotoneraIzq.setBorder( BorderFactory.createBevelBorder(0));
	 	pnlBotoneraIzq.setLayout( new FlowLayout() );
	    pnlBotoneraIzq.setPreferredSize( new Dimension( getWidth()/4, 50));
	    pnlBotoneraIzq.add( new botonAnEl("Añadir") );
	    pnlBotoneraIzq.add( new botonAnEl("Eliminar") );
	    
	    JList<?> listaHab = new JList<Object>();
		modeloLista = new DefaultListModel<Object>();
		listaHab.setModel(modeloLista);
		
		JScrollPane spLista = new JScrollPane(listaHab);
		spLista.setPreferredSize( new Dimension(187, 505));
		pnlLista.add(spLista);
		
		add(pnlTitulo, BorderLayout.NORTH);
		add(pnlCreador);
		pnlCreador.add( pnlArbolyLista, BorderLayout.WEST );
		pnlCreador.add( pnlEditHab, BorderLayout.EAST );
		pnlArbolyLista.add(pnlLista, BorderLayout.EAST);
		pnlArbolyLista.add( pnlArbol, BorderLayout.WEST );
		pnlLista.add(pnlBotoneraIzq, BorderLayout.SOUTH);
		pnlEditHab.add(pnlEditCampos, BorderLayout.NORTH);
		pnlEditHab.add( pnlPreviewHab);
		pnlEditCampos.add(pnlBotonAc, BorderLayout.SOUTH);
		
		
        
		this.crearMapaAreas();
		this.crearArbol(areasDeTrabajo);
		
	}
	
	
	public void crearArbol(TreeMap<String,ArrayList<String>> areasDeTrabajo) {
		root = new DefaultMutableTreeNode( "Habilidades" );
		pnlArbol.setLayout(new BorderLayout(0, 0));
		JScrollPane spTree = new JScrollPane( );
		spTree.setBounds(10, 35, 180, 495);
		pnlArbol.add( spTree );
		ArbolHabilidades = new JTree();
		modeloArbol = new DefaultTreeModel(root);
		ArbolHabilidades.setModel(modeloArbol);
		this.anyadirHabilidades();
		spTree.setViewportView(ArbolHabilidades);
		
		
	}
	
	private void anyadirHabilidades() {
		int posiGeneral = 0;
		for (String Area : areasDeTrabajo.keySet()) {
			DefaultMutableTreeNode nodoArea = crearNodo(Area,root,posiGeneral);
			posiGeneral++;
			int posiArea = 0;
			for (String Subarea : areasDeTrabajo.get(Area)) {
				crearNodo(Subarea,nodoArea,posiArea);
				posiArea++;
			}
		}
	}
	
	private DefaultMutableTreeNode crearNodo( Object dato, DefaultMutableTreeNode nodoPadre, int posi ) {
		DefaultMutableTreeNode nodo1 = new DefaultMutableTreeNode( dato );
		modeloArbol.insertNodeInto( nodo1, nodoPadre, posi );
		return nodo1;
	}
	
	public void crearMapaAreas() {
		// Crear un diccionario de áreas de trabajo con subáreas
        TreeMap<String, ArrayList<String>> areasDeTrabajo = new TreeMap<String, ArrayList<String>>();

        // Informática
        areasDeTrabajo.put("Informática", new ArrayList<>());
        areasDeTrabajo.get("Informática").add("Desarrollo Web");
        areasDeTrabajo.get("Informática").add("Machine Learning");
        areasDeTrabajo.get("Informática").add("Seguridad Informática");
        areasDeTrabajo.get("Informática").add("Desarrollo de Software");
        areasDeTrabajo.get("Informática").add("Base de Datos");

        // Arte y Diseño
        areasDeTrabajo.put("Arte y Diseño", new ArrayList<>());
        areasDeTrabajo.get("Arte y Diseño").add("Diseño Gráfico");
        areasDeTrabajo.get("Arte y Diseño").add("Arquitectura de Interiores");
        areasDeTrabajo.get("Arte y Diseño").add("Animación");
        areasDeTrabajo.get("Arte y Diseño").add("Fotografía");
        areasDeTrabajo.get("Arte y Diseño").add("Diseño de Moda");

        // Deporte
        areasDeTrabajo.put("Deporte", new ArrayList<>());
        areasDeTrabajo.get("Deporte").add("Entrenador Personal");
        areasDeTrabajo.get("Deporte").add("Fisioterapia Deportiva");
        areasDeTrabajo.get("Deporte").add("Nutrición Deportiva");
        areasDeTrabajo.get("Deporte").add("Gestión Deportiva");
        areasDeTrabajo.get("Deporte").add("Deporte Profesional");

        // Psicología
        areasDeTrabajo.put("Psicología", new ArrayList<>());
        areasDeTrabajo.get("Psicología").add("Psicología Clínica");
        areasDeTrabajo.get("Psicología").add("Psicología Educativa");
        areasDeTrabajo.get("Psicología").add("Neuropsicología");
        areasDeTrabajo.get("Psicología").add("Consejería Psicológica");
        areasDeTrabajo.get("Psicología").add("Psicología Organizacional");

        // Marketing
        areasDeTrabajo.put("Marketing", new ArrayList<>());
        areasDeTrabajo.get("Marketing").add("Marketing Digital");
        areasDeTrabajo.get("Marketing").add("Gestión de Marcas");
        areasDeTrabajo.get("Marketing").add("Investigación de Mercado");
        areasDeTrabajo.get("Marketing").add("Publicidad");
        areasDeTrabajo.get("Marketing").add("Marketing de Contenidos");

        // Finanzas
        areasDeTrabajo.put("Finanzas", new ArrayList<>());
        areasDeTrabajo.get("Finanzas").add("Banca y Finanzas");
        areasDeTrabajo.get("Finanzas").add("Contabilidad");
        areasDeTrabajo.get("Finanzas").add("Gestión de Inversiones");
        areasDeTrabajo.get("Finanzas").add("Seguros");
        areasDeTrabajo.get("Finanzas").add("Finanzas Corporativas");

        // Educación
        areasDeTrabajo.put("Educación", new ArrayList<>());
        areasDeTrabajo.get("Educación").add("Docencia");
        areasDeTrabajo.get("Educación").add("Educación Especial");
        areasDeTrabajo.get("Educación").add("Tecnología Educativa");
        areasDeTrabajo.get("Educación").add("Orientación Educativa");
        areasDeTrabajo.get("Educación").add("Dirección de Centros Educativos");

        // Medicina
        areasDeTrabajo.put("Medicina", new ArrayList<>());
        areasDeTrabajo.get("Medicina").add("Medicina General");
        areasDeTrabajo.get("Medicina").add("Cirugía");
        areasDeTrabajo.get("Medicina").add("Cardiología");
        areasDeTrabajo.get("Medicina").add("Pediatría");
        areasDeTrabajo.get("Medicina").add("Psiquiatría");

        // Ciencias de la Salud
        areasDeTrabajo.put("Ciencias de la Salud", new ArrayList<>());
        areasDeTrabajo.get("Ciencias de la Salud").add("Enfermería");
        areasDeTrabajo.get("Ciencias de la Salud").add("Fisioterapia");
        areasDeTrabajo.get("Ciencias de la Salud").add("Terapia Ocupacional");
        areasDeTrabajo.get("Ciencias de la Salud").add("Nutrición");
        areasDeTrabajo.get("Ciencias de la Salud").add("Farmacia");

        // Ingeniería
        areasDeTrabajo.put("Ingeniería", new ArrayList<>());
        areasDeTrabajo.get("Ingeniería").add("Ingeniería Civil");
        areasDeTrabajo.get("Ingeniería").add("Ingeniería Eléctrica");
        areasDeTrabajo.get("Ingeniería").add("Ingeniería Mecánica");
        areasDeTrabajo.get("Ingeniería").add("Ingeniería de Sistemas");
        areasDeTrabajo.get("Ingeniería").add("Ingeniería Industrial");

        // Ventas
        areasDeTrabajo.put("Ventas", new ArrayList<>());
        areasDeTrabajo.get("Ventas").add("Ventas Directas");
        areasDeTrabajo.get("Ventas").add("Ventas en Línea");
        areasDeTrabajo.get("Ventas").add("Ventas B2B");
        areasDeTrabajo.get("Ventas").add("Gestión de Cuentas");
        areasDeTrabajo.get("Ventas").add("Telemarketing");

        // Servicio al Cliente
        areasDeTrabajo.put("Servicio al Cliente", new ArrayList<>());
        areasDeTrabajo.get("Servicio al Cliente").add("Atención al Cliente");
        areasDeTrabajo.get("Servicio al Cliente").add("Soporte Técnico");
        areasDeTrabajo.get("Servicio al Cliente").add("Gestión de Quejas");
        areasDeTrabajo.get("Servicio al Cliente").add("Experiencia del Cliente");
        areasDeTrabajo.get("Servicio al Cliente").add("Call Center");

        // Ciencias Sociales
        areasDeTrabajo.put("Ciencias Sociales", new ArrayList<>());
        areasDeTrabajo.get("Ciencias Sociales").add("Trabajo Social");
        areasDeTrabajo.get("Ciencias Sociales").add("Psicología Social");
        areasDeTrabajo.get("Ciencias Sociales").add("Antropología");
        areasDeTrabajo.get("Ciencias Sociales").add("Sociología");
        areasDeTrabajo.get("Ciencias Sociales").add("Educación Social");

        // Ciencias Ambientales
        areasDeTrabajo.put("Ciencias Ambientales", new ArrayList<>());
        areasDeTrabajo.get("Ciencias Ambientales").add("Gestión Ambiental");
        areasDeTrabajo.get("Ciencias Ambientales").add("Conservación de la Naturaleza");
        areasDeTrabajo.get("Ciencias Ambientales").add("Evaluación de Impacto Ambiental");
        areasDeTrabajo.get("Ciencias Ambientales").add("Recursos Naturales");
        areasDeTrabajo.get("Ciencias Ambientales").add("Sostenibilidad Ambiental");

        // Arquitectura
        areasDeTrabajo.put("Arquitectura", new ArrayList<>());
        areasDeTrabajo.get("Arquitectura").add("Diseño de Edificios");
        areasDeTrabajo.get("Arquitectura").add("Urbanismo");
        areasDeTrabajo.get("Arquitectura").add("Restauración Arquitectónica");
        areasDeTrabajo.get("Arquitectura").add("Planificación Urbana");
        areasDeTrabajo.get("Arquitectura").add("Diseño de Paisaje");

        // Turismo y Hospitalidad
        areasDeTrabajo.put("Turismo y Hospitalidad", new ArrayList<>());
        areasDeTrabajo.get("Turismo y Hospitalidad").add("Gestión Hotelera");
        areasDeTrabajo.get("Turismo y Hospitalidad").add("Agencia de Viajes");
        areasDeTrabajo.get("Turismo y Hospitalidad").add("Restaurante y Catering");
        areasDeTrabajo.get("Turismo y Hospitalidad").add("Turismo Sostenible");
        areasDeTrabajo.get("Turismo y Hospitalidad").add("Entretenimiento Turístico");

        // Agricultura
        areasDeTrabajo.put("Agricultura", new ArrayList<>());
        areasDeTrabajo.get("Agricultura").add("Agronomía");
        areasDeTrabajo.get("Agricultura").add("Horticultura");
        areasDeTrabajo.get("Agricultura").add("Silvicultura");
        areasDeTrabajo.get("Agricultura").add("Agricultura de Precisión");
        areasDeTrabajo.get("Agricultura").add("Agricultura Orgánica");

        // Derecho
        areasDeTrabajo.put("Derecho", new ArrayList<>());
        areasDeTrabajo.get("Derecho").add("Derecho Penal");
        areasDeTrabajo.get("Derecho").add("Derecho Civil");
        areasDeTrabajo.get("Derecho").add("Derecho Laboral");
        areasDeTrabajo.get("Derecho").add("Derecho Comercial");
        areasDeTrabajo.get("Derecho").add("Derecho Internacional");

        // Comunicación
        areasDeTrabajo.put("Comunicación", new ArrayList<>());
        areasDeTrabajo.get("Comunicación").add("Periodismo");
        areasDeTrabajo.get("Comunicación").add("Comunicación Corporativa");
        areasDeTrabajo.get("Comunicación").add("Comunicación Visual");
        areasDeTrabajo.get("Comunicación").add("Comunicación Digital");
        areasDeTrabajo.get("Comunicación").add("Relaciones Públicas");

        // Periodismo
        areasDeTrabajo.put("Periodismo", new ArrayList<>());
        areasDeTrabajo.get("Periodismo").add("Periodismo de Investigación");
        areasDeTrabajo.get("Periodismo").add("Periodismo Deportivo");
        areasDeTrabajo.get("Periodismo").add("Periodismo Político");
        areasDeTrabajo.get("Periodismo").add("Periodismo de Datos");
        areasDeTrabajo.get("Periodismo").add("Periodismo Multimedia");

        // Música
        areasDeTrabajo.put("Música", new ArrayList<>());
        areasDeTrabajo.get("Música").add("Interpretación Musical");
        areasDeTrabajo.get("Música").add("Composición Musical");
        areasDeTrabajo.get("Música").add("Producción Musical");
        areasDeTrabajo.get("Música").add("Educación Musical");
        areasDeTrabajo.get("Música").add("Gestión de Eventos Musicales");

        // Investigación y Desarrollo
        areasDeTrabajo.put("Investigación y Desarrollo", new ArrayList<>());
        areasDeTrabajo.get("Investigación y Desarrollo").add("Investigación Científica");
        areasDeTrabajo.get("Investigación y Desarrollo").add("Desarrollo de Producto");
        areasDeTrabajo.get("Investigación y Desarrollo").add("Investigación de Mercado");
        areasDeTrabajo.get("Investigación y Desarrollo").add("Desarrollo Tecnológico");
        areasDeTrabajo.get("Investigación y Desarrollo").add("Innovación Empresarial");

        // Construcción
        areasDeTrabajo.put("Construcción", new ArrayList<>());
        areasDeTrabajo.get("Construcción").add("Ingeniería Civil");
        areasDeTrabajo.get("Construcción").add("Arquitectura");
        areasDeTrabajo.get("Construcción").add("Gestión de Proyectos de Construcción");
        areasDeTrabajo.get("Construcción").add("Diseño Estructural");
        areasDeTrabajo.get("Construcción").add("Construcción Sostenible");

        // Mantenimiento
        areasDeTrabajo.put("Mantenimiento", new ArrayList<>());
        areasDeTrabajo.get("Mantenimiento").add("Mantenimiento de Edificios");
        areasDeTrabajo.get("Mantenimiento").add("Mantenimiento Industrial");
        areasDeTrabajo.get("Mantenimiento").add("Mantenimiento de Vehículos");
        areasDeTrabajo.get("Mantenimiento").add("Mantenimiento de Equipos Electrónicos");
        areasDeTrabajo.get("Mantenimiento").add("Mantenimiento de Jardines");

        // Logística
        areasDeTrabajo.put("Logística", new ArrayList<>());
        areasDeTrabajo.get("Logística").add("Gestión de la Cadena de Suministro");
        areasDeTrabajo.get("Logística").add("Logística de Transporte");
        areasDeTrabajo.get("Logística").add("Gestión de Inventarios");
        areasDeTrabajo.get("Logística").add("Distribución de Mercancías");
        areasDeTrabajo.get("Logística").add("Logística de Almacén");

        // Transporte
        areasDeTrabajo.put("Transporte", new ArrayList<>());
        areasDeTrabajo.get("Transporte").add("Transporte Terrestre");
        areasDeTrabajo.get("Transporte").add("Transporte Marítimo");
        areasDeTrabajo.get("Transporte").add("Transporte Aéreo");
        areasDeTrabajo.get("Transporte").add("Transporte Público");
        areasDeTrabajo.get("Transporte").add("Logística de Transporte");

        // Alimentación y Hostelería
        areasDeTrabajo.put("Alimentación y Hostelería", new ArrayList<>());
        areasDeTrabajo.get("Alimentación y Hostelería").add("Restauración");
        areasDeTrabajo.get("Alimentación y Hostelería").add("Cocina");
        areasDeTrabajo.get("Alimentación y Hostelería").add("Pastelería");
        areasDeTrabajo.get("Alimentación y Hostelería").add("Gestión de Restaurantes");
        areasDeTrabajo.get("Alimentación y Hostelería").add("Hospitalidad");

        // Moda
        areasDeTrabajo.put("Moda", new ArrayList<>());
        areasDeTrabajo.get("Moda").add("Diseño de Moda");
        areasDeTrabajo.get("Moda").add("Producción Textil");
        areasDeTrabajo.get("Moda").add("Gestión de Marcas de Moda");
        areasDeTrabajo.get("Moda").add("Estilismo de Moda");
        areasDeTrabajo.get("Moda").add("Venta al por Menor de Moda");

        // Gobierno y Política
        areasDeTrabajo.put("Gobierno y Política", new ArrayList<>());
        areasDeTrabajo.get("Gobierno y Política").add("Administración Pública");
        areasDeTrabajo.get("Gobierno y Política").add("Diplomacia");
        areasDeTrabajo.get("Gobierno y Política").add("Asuntos Internacionales");
        areasDeTrabajo.get("Gobierno y Política").add("Política Pública");
        areasDeTrabajo.get("Gobierno y Política").add("Gestión de Campañas Políticas");

        // Organizaciones sin ánimo de lucro
        areasDeTrabajo.put("Organizaciones sin ánimo de lucro", new ArrayList<>());
        areasDeTrabajo.get("Organizaciones sin ánimo de lucro").add("Gestión de ONGs");
        areasDeTrabajo.get("Organizaciones sin ánimo de lucro").add("Desarrollo Comunitario");
        areasDeTrabajo.get("Organizaciones sin ánimo de lucro").add("Trabajo Social");
        areasDeTrabajo.get("Organizaciones sin ánimo de lucro").add("Recaudación de Fondos");
        areasDeTrabajo.get("Organizaciones sin ánimo de lucro").add("Educación Social");

        // Relaciones Públicas
        areasDeTrabajo.put("Relaciones Públicas", new ArrayList<>());
        areasDeTrabajo.get("Relaciones Públicas").add("Relaciones con los Medios");
        areasDeTrabajo.get("Relaciones Públicas").add("Comunicación Corporativa");
        areasDeTrabajo.get("Relaciones Públicas").add("Relaciones Gubernamentales");
        areasDeTrabajo.get("Relaciones Públicas").add("Gestión de Crisis");
        areasDeTrabajo.get("Relaciones Públicas").add("Responsabilidad Social Empresarial");

        // Seguridad
        areasDeTrabajo.put("Seguridad", new ArrayList<>());
        areasDeTrabajo.get("Seguridad").add("Seguridad Informática");
        areasDeTrabajo.get("Seguridad").add("Seguridad Física");
        areasDeTrabajo.get("Seguridad").add("Seguridad de Datos");
        areasDeTrabajo.get("Seguridad").add("Seguridad Privada");
        areasDeTrabajo.get("Seguridad").add("Seguridad Pública");

        // Energía y Recursos Naturales
        areasDeTrabajo.put("Energía y Recursos Naturales", new ArrayList<>());
        areasDeTrabajo.get("Energía y Recursos Naturales").add("Energía Renovable");
        areasDeTrabajo.get("Energía y Recursos Naturales").add("Extracción de Recursos");
        areasDeTrabajo.get("Energía y Recursos Naturales").add("Gestión Ambiental");
        areasDeTrabajo.get("Energía y Recursos Naturales").add("Petróleo y Gas");
        areasDeTrabajo.get("Energía y Recursos Naturales").add("Minería");

        // Ciencia de Datos
        areasDeTrabajo.put("Ciencia de Datos", new ArrayList<>());
        areasDeTrabajo.get("Ciencia de Datos").add("Análisis de Datos");
        areasDeTrabajo.get("Ciencia de Datos").add("Aprendizaje Automático");
        areasDeTrabajo.get("Ciencia de Datos").add("Big Data");
        areasDeTrabajo.get("Ciencia de Datos").add("Minería de Datos");
        areasDeTrabajo.get("Ciencia de Datos").add("Visualización de Datos");

        // Astronomía
        areasDeTrabajo.put("Astronomía", new ArrayList<>());
        areasDeTrabajo.get("Astronomía").add("Observación Astronómica");
        areasDeTrabajo.get("Astronomía").add("Astrofísica");
        areasDeTrabajo.get("Astronomía").add("Cosmología");
        areasDeTrabajo.get("Astronomía").add("Astronomía Teórica");
        areasDeTrabajo.get("Astronomía").add("Astrobiología");

        // Biología
        areasDeTrabajo.put("Biología", new ArrayList<>());
        areasDeTrabajo.get("Biología").add("Biología Celular");
        areasDeTrabajo.get("Biología").add("Ecología");
        areasDeTrabajo.get("Biología").add("Genética");
        areasDeTrabajo.get("Biología").add("Zoología");
        areasDeTrabajo.get("Biología").add("Botánica");

        // Química
        areasDeTrabajo.put("Química", new ArrayList<>());
        areasDeTrabajo.get("Química").add("Química Orgánica");
        areasDeTrabajo.get("Química").add("Química Inorgánica");
        areasDeTrabajo.get("Química").add("Química Analítica");
        areasDeTrabajo.get("Química").add("Química Física");

        // Matemáticas
        areasDeTrabajo.put("Matemáticas", new ArrayList<>());
        areasDeTrabajo.get("Matemáticas").add("Álgebra");
        areasDeTrabajo.get("Matemáticas").add("Geometría");
        areasDeTrabajo.get("Matemáticas").add("Cálculo");
        areasDeTrabajo.get("Matemáticas").add("Estadísticas");
        areasDeTrabajo.get("Matemáticas").add("Teoría de Números");

        // Farmacia
        areasDeTrabajo.put("Farmacia", new ArrayList<>());
        areasDeTrabajo.get("Farmacia").add("Farmacia Clínica");
        areasDeTrabajo.get("Farmacia").add("Farmacia Hospitalaria");
        areasDeTrabajo.get("Farmacia").add("Industria Farmacéutica");
        areasDeTrabajo.get("Farmacia").add("Farmacia Comunitaria");
        areasDeTrabajo.get("Farmacia").add("Investigación Farmacéutica");

        // Geología
        areasDeTrabajo.put("Geología", new ArrayList<>());
        areasDeTrabajo.get("Geología").add("Geología del Petróleo");
        areasDeTrabajo.get("Geología").add("Geología Ambiental");
        areasDeTrabajo.get("Geología").add("Geofísica");
        areasDeTrabajo.get("Geología").add("Hidrogeología");
        areasDeTrabajo.get("Geología").add("Geología Planetaria");

        // Traducción e Interpretación
        areasDeTrabajo.put("Traducción e Interpretación", new ArrayList<>());
        areasDeTrabajo.get("Traducción e Interpretación").add("Traducción Literaria");
        areasDeTrabajo.get("Traducción e Interpretación").add("Interpretación de Conferencias");
        areasDeTrabajo.get("Traducción e Interpretación").add("Traducción Técnica");
        areasDeTrabajo.get("Traducción e Interpretación").add("Localización de Software");
        areasDeTrabajo.get("Traducción e Interpretación").add("Traducción Jurídica");

        // Investigación de Mercado
        areasDeTrabajo.put("Investigación de Mercado", new ArrayList<>());
        areasDeTrabajo.get("Investigación de Mercado").add("Estudios de Consumidor");
        areasDeTrabajo.get("Investigación de Mercado").add("Investigación de Opinión Pública");
        areasDeTrabajo.get("Investigación de Mercado").add("Análisis de Datos de Mercado");
        areasDeTrabajo.get("Investigación de Mercado").add("Etnografía de Mercado");
        areasDeTrabajo.get("Investigación de Mercado").add("Investigación de Producto");

        // Consultoría
        areasDeTrabajo.put("Consultoría", new ArrayList<>());
        areasDeTrabajo.get("Consultoría").add("Consultoría Empresarial");
        areasDeTrabajo.get("Consultoría").add("Consultoría de Gestión");
        areasDeTrabajo.get("Consultoría").add("Consultoría Financiera");
        areasDeTrabajo.get("Consultoría").add("Consultoría de Recursos Humanos");
        areasDeTrabajo.get("Consultoría").add("Consultoría en Tecnología");

        // Artes Escénicas
        areasDeTrabajo.put("Artes Escénicas", new ArrayList<>());
        areasDeTrabajo.get("Artes Escénicas").add("Teatro");
        areasDeTrabajo.get("Artes Escénicas").add("Danza");
        areasDeTrabajo.get("Artes Escénicas").add("Música en Vivo");
        areasDeTrabajo.get("Artes Escénicas").add("Producción de Eventos");
        areasDeTrabajo.get("Artes Escénicas").add("Dirección Escénica");

        // Desarrollo Web
        areasDeTrabajo.put("Desarrollo Web", new ArrayList<>());
        areasDeTrabajo.get("Desarrollo Web").add("Desarrollo Front-end");
        areasDeTrabajo.get("Desarrollo Web").add("Desarrollo Back-end");
        areasDeTrabajo.get("Desarrollo Web").add("Diseño Web");
        areasDeTrabajo.get("Desarrollo Web").add("Desarrollo de Aplicaciones Web");
        areasDeTrabajo.get("Desarrollo Web").add("E-commerce");

        // Redes y Telecomunicaciones
        areasDeTrabajo.put("Redes y Telecomunicaciones", new ArrayList<>());
        areasDeTrabajo.get("Redes y Telecomunicaciones").add("Administración de Redes");
        areasDeTrabajo.get("Redes y Telecomunicaciones").add("Seguridad en Redes");
        areasDeTrabajo.get("Redes y Telecomunicaciones").add("VoIP");
        areasDeTrabajo.get("Redes y Telecomunicaciones").add("Ingeniería de Telecomunicaciones");
        areasDeTrabajo.get("Redes y Telecomunicaciones").add("Desarrollo de Redes Móviles");

        // Electrónica
        areasDeTrabajo.put("Electrónica", new ArrayList<>());
        areasDeTrabajo.get("Electrónica").add("Ingeniería Electrónica");
        areasDeTrabajo.get("Electrónica").add("Sistemas Embebidos");
        areasDeTrabajo.get("Electrónica").add("Automatización Industrial");
        areasDeTrabajo.get("Electrónica").add("Robótica");
        areasDeTrabajo.get("Electrónica").add("Diseño de Circuitos");

        // Tecnología de la Información
        areasDeTrabajo.put("Tecnología de la Información", new ArrayList<>());
        areasDeTrabajo.get("Tecnología de la Información").add("Gestión de Proyectos TI");
        areasDeTrabajo.get("Tecnología de la Información").add("Administración de Bases de Datos");
        areasDeTrabajo.get("Tecnología de la Información").add("Soporte Técnico");
        areasDeTrabajo.get("Tecnología de la Información").add("Desarrollo de Software");
        areasDeTrabajo.get("Tecnología de la Información").add("Ciberseguridad");
        
        // Seguros
        areasDeTrabajo.put("Seguros",new ArrayList<>());
        areasDeTrabajo.get("Seguros").add("Agente de Seguros");
        areasDeTrabajo.get("Seguros").add("Gestión de Riesgos");
        areasDeTrabajo.get("Seguros").add("Suscripción de Pólizas");
        areasDeTrabajo.get("Seguros").add("Reclamaciones de Seguros");
        areasDeTrabajo.get("Seguros").add("Actuaría de Seguros");

        // Investigación de Operaciones
        areasDeTrabajo.put("Investigación de Operaciones", new ArrayList<>());
        areasDeTrabajo.get("Investigación de Operaciones").add("Optimización");
        areasDeTrabajo.get("Investigación de Operaciones").add("Modelado Matemático");
        areasDeTrabajo.get("Investigación de Operaciones").add("Análisis de Decisiones");
        areasDeTrabajo.get("Investigación de Operaciones").add("Logística y Transporte");
        areasDeTrabajo.get("Investigación de Operaciones").add("Gestión de la Cadena de Suministro");

        // Arqueología
        areasDeTrabajo.put("Arqueología", new ArrayList<>());
        areasDeTrabajo.get("Arqueología").add("Excavación Arqueológica");
        areasDeTrabajo.get("Arqueología").add("Restauración de Artefactos");
        areasDeTrabajo.get("Arqueología").add("Investigación Histórica");
        areasDeTrabajo.get("Arqueología").add("Antropología Arqueológica");
        areasDeTrabajo.get("Arqueología").add("Conservación del Patrimonio");

        // Ciencias Políticas
        areasDeTrabajo.put("Ciencias Políticas", new ArrayList<>());
        areasDeTrabajo.get("Ciencias Políticas").add("Asesor Político");
        areasDeTrabajo.get("Ciencias Políticas").add("Análisis Político");
        areasDeTrabajo.get("Ciencias Políticas").add("Diplomacia");
        areasDeTrabajo.get("Ciencias Políticas").add("Gestión Pública");
        areasDeTrabajo.get("Ciencias Políticas").add("Relaciones Internacionales");

        // Comida y Nutrición
        areasDeTrabajo.put("Comida y Nutrición", new ArrayList<>());
        areasDeTrabajo.get("Comida y Nutrición").add("Nutrición Clínica");
        areasDeTrabajo.get("Comida y Nutrición").add("Dietética");
        areasDeTrabajo.get("Comida y Nutrición").add("Nutrición Deportiva");
        areasDeTrabajo.get("Comida y Nutrición").add("Gestión de Alimentos");
        areasDeTrabajo.get("Comida y Nutrición").add("Investigación Alimentaria");

        // Fotografía
        areasDeTrabajo.put("Fotografía", new ArrayList<>());
        areasDeTrabajo.get("Fotografía").add("Fotografía de Retrato");
        areasDeTrabajo.get("Fotografía").add("Fotografía de Bodas");
        areasDeTrabajo.get("Fotografía").add("Fotografía de Moda");
        areasDeTrabajo.get("Fotografía").add("Fotografía de Producto");
        areasDeTrabajo.get("Fotografía").add("Fotoperiodismo");

        // Diseño Gráfico
        areasDeTrabajo.put("Diseño Gráfico", new ArrayList<>());
        areasDeTrabajo.get("Diseño Gráfico").add("Diseño de Marca");
        areasDeTrabajo.get("Diseño Gráfico").add("Diseño Editorial");
        areasDeTrabajo.get("Diseño Gráfico").add("Diseño de Envases");
        areasDeTrabajo.get("Diseño Gráfico").add("Diseño Web");
        areasDeTrabajo.get("Diseño Gráfico").add("Ilustración");

        // Relaciones Internacionales
        areasDeTrabajo.put("Relaciones Internacionales", new ArrayList<>());
        areasDeTrabajo.get("Relaciones Internacionales").add("Diplomacia");
        areasDeTrabajo.get("Relaciones Internacionales").add("Organismos Internacionales");
        areasDeTrabajo.get("Relaciones Internacionales").add("Negociación Internacional");
        areasDeTrabajo.get("Relaciones Internacionales").add("Derechos Humanos");
        areasDeTrabajo.get("Relaciones Internacionales").add("Cooperación Internacional");

        // Diseño de Interiores
        areasDeTrabajo.put("Diseño de Interiores", new ArrayList<>());
        areasDeTrabajo.get("Diseño de Interiores").add("Diseño Residencial");
        areasDeTrabajo.get("Diseño de Interiores").add("Diseño Comercial");
        areasDeTrabajo.get("Diseño de Interiores").add("Diseño de Espacios Públicos");
        areasDeTrabajo.get("Diseño de Interiores").add("Diseño de Hoteles");
        areasDeTrabajo.get("Diseño de Interiores").add("Diseño de Restaurantes");

        // Astronomía Observacional
        areasDeTrabajo.put("Astronomía Observacional", new ArrayList<>());
        areasDeTrabajo.get("Astronomía Observacional").add("Observación del Cielo Nocturno");
        areasDeTrabajo.get("Astronomía Observacional").add("Astronomía Solar");
        areasDeTrabajo.get("Astronomía Observacional").add("Astronomía de Radio");
        areasDeTrabajo.get("Astronomía Observacional").add("Astrofotografía");
        areasDeTrabajo.get("Astronomía Observacional").add("Observatorios Astronómicos");

        // Arte Digital
        areasDeTrabajo.put("Arte Digital", new ArrayList<>());
        areasDeTrabajo.get("Arte Digital").add("Ilustración Digital");
        areasDeTrabajo.get("Arte Digital").add("Animación Digital");
        areasDeTrabajo.get("Arte Digital").add("Arte 3D");
        areasDeTrabajo.get("Arte Digital").add("Concept Art");
        areasDeTrabajo.get("Arte Digital").add("Diseño de Videojuegos");

        // Educación Especial
        areasDeTrabajo.put("Educación Especial", new ArrayList<>());
        areasDeTrabajo.get("Educación Especial").add("Educación Inclusiva");
        areasDeTrabajo.get("Educación Especial").add("Atención a Discapacidades");
        areasDeTrabajo.get("Educación Especial").add("Estimulación Temprana");
        areasDeTrabajo.get("Educación Especial").add("Psicopedagogía");
        areasDeTrabajo.get("Educación Especial").add("Logopedia");
        areasDeTrabajo.get("Educación Especial").add("Terapia Ocupacional");
        areasDeTrabajo.get("Educación Especial").add("Acompañamiento Terapéutico");
        areasDeTrabajo.get("Educación Especial").add("Educación para Autismo");

        // Odontología
        areasDeTrabajo.put("Odontología", new ArrayList<>());
        areasDeTrabajo.get("Odontología").add("Odontología General");
        areasDeTrabajo.get("Odontología").add("Ortodoncia");
        areasDeTrabajo.get("Odontología").add("Cirugía Oral y Maxilofacial");
        areasDeTrabajo.get("Odontología").add("Odontopediatría");
        areasDeTrabajo.get("Odontología").add("Periodoncia");

        // Terapia Física
        areasDeTrabajo.put("Terapia Física", new ArrayList<>());
        areasDeTrabajo.get("Terapia Física").add("Fisioterapia");
        areasDeTrabajo.get("Terapia Física").add("Rehabilitación Física");
        areasDeTrabajo.get("Terapia Física").add("Terapia Ocupacional");
        areasDeTrabajo.get("Terapia Física").add("Terapia de Movimiento");
        areasDeTrabajo.get("Terapia Física").add("Terapia de Deportes");

        // Psicología
        areasDeTrabajo.put("Psicología", new ArrayList<>());
        areasDeTrabajo.get("Psicología").add("Psicología Clínica");
        areasDeTrabajo.get("Psicología").add("Psicología Educativa");
        areasDeTrabajo.get("Psicología").add("Psicología Organizacional");
        areasDeTrabajo.get("Psicología").add("Psicología Forense");
        areasDeTrabajo.get("Psicología").add("Terapia Psicológica");

        // Terapia Ocupacional
        areasDeTrabajo.put("Terapia Ocupacional", new ArrayList<>());
        areasDeTrabajo.get("Terapia Ocupacional").add("Rehabilitación Ocupacional");
        areasDeTrabajo.get("Terapia Ocupacional").add("Terapia Pediátrica");
        areasDeTrabajo.get("Terapia Ocupacional").add("Terapia Geriátrica");
        areasDeTrabajo.get("Terapia Ocupacional").add("Terapia en Salud Mental");
        areasDeTrabajo.get("Terapia Ocupacional").add("Ergoterapia");

        // Gerontología
        areasDeTrabajo.put("Gerontología", new ArrayList<>());
        areasDeTrabajo.get("Gerontología").add("Cuidado de Adultos Mayores");
        areasDeTrabajo.get("Gerontología").add("Gestión de Centros de Atención");
        areasDeTrabajo.get("Gerontología").add("Salud Geriátrica");
        areasDeTrabajo.get("Gerontología").add("Calidad de Vida en la Tercera Edad");
        areasDeTrabajo.get("Gerontología").add("Recreación para Adultos Mayores");

        // Musicoterapia
        areasDeTrabajo.put("Musicoterapia", new ArrayList<>());
        areasDeTrabajo.get("Musicoterapia").add("Terapia de Música para Niños");
        areasDeTrabajo.get("Musicoterapia").add("Rehabilitación con Música");
        areasDeTrabajo.get("Musicoterapia").add("Terapia de Música en Salud Mental");
        areasDeTrabajo.get("Musicoterapia").add("Música en Hospitales");
        areasDeTrabajo.get("Musicoterapia").add("Musicoterapia en Personas con Autismo");

        // Psicopedagogía
        areasDeTrabajo.put("Psicopedagogía", new ArrayList<>());
        areasDeTrabajo.get("Psicopedagogía").add("Orientación Escolar");
        areasDeTrabajo.get("Psicopedagogía").add("Dificultades de Aprendizaje");
        areasDeTrabajo.get("Psicopedagogía").add("Atención a la Diversidad");
        areasDeTrabajo.get("Psicopedagogía").add("Intervención en Lectura y Escritura");
        areasDeTrabajo.get("Psicopedagogía").add("Evaluación Psicopedagógica");
		
        // Criminología
        areasDeTrabajo.put("Criminología", new ArrayList<>());
        areasDeTrabajo.get("Criminología").add("Investigación Criminal");
        areasDeTrabajo.get("Criminología").add("Peritaje Forense");
        areasDeTrabajo.get("Criminología").add("Prevención del Crimen");
        areasDeTrabajo.get("Criminología").add("Policía Científica");
        areasDeTrabajo.get("Criminología").add("Psicología Forense");

        // Biblioteconomía y Documentación
        areasDeTrabajo.put("Biblioteconomía y Documentación", new ArrayList<>());
        areasDeTrabajo.get("Biblioteconomía y Documentación").add("Gestión de Bibliotecas");
        areasDeTrabajo.get("Biblioteconomía y Documentación").add("Archivística");
        areasDeTrabajo.get("Biblioteconomía y Documentación").add("Bibliotecas Digitales");
        areasDeTrabajo.get("Biblioteconomía y Documentación").add("Documentación Empresarial");
        areasDeTrabajo.get("Biblioteconomía y Documentación").add("Bibliotecas Escolares");

        // Teología
        areasDeTrabajo.put("Teología", new ArrayList<>());
        areasDeTrabajo.get("Teología").add("Ministerio Religioso");
        areasDeTrabajo.get("Teología").add("Estudios Bíblicos");
        areasDeTrabajo.get("Teología").add("Ética Religiosa");
        areasDeTrabajo.get("Teología").add("Teología Pastoral");
        areasDeTrabajo.get("Teología").add("Historia de las Religiones");

        // Filosofía
        areasDeTrabajo.put("Filosofía", new ArrayList<>());
        areasDeTrabajo.get("Filosofía").add("Docencia de Filosofía");
        areasDeTrabajo.get("Filosofía").add("Ética Filosófica");
        areasDeTrabajo.get("Filosofía").add("Filosofía Política");
        areasDeTrabajo.get("Filosofía").add("Filosofía de la Mente");
        areasDeTrabajo.get("Filosofía").add("Filosofía de la Ciencia");
        
        this.areasDeTrabajo=areasDeTrabajo;
	}
	
	public static void main(String[] args) {
		JFrame vent = new JFrame();
		vent.add( new pnlPuestoDeTrabajo() );
		vent.getContentPane().add(new pnlPuestoDeTrabajo());
		vent.setSize(750,650);
		vent.setVisible(true);
	}
}
