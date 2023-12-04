package ventanas;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import clases.Empresa;
import clases.Habilidad;
import clases.Persona;
import clases.PuestoTrabajo;
import clases.Usuario;

public class PnlEditarPerfil  extends JPanel {

//	Provincias de España para añadirlas al ComboBox
	private final String[] provincias = {
	            "Álava", "Albacete", "Alicante", "Almería", "Asturias",
	            "Ávila", "Badajoz", "Barcelona", "Bizkaia", "Burgos", "Cáceres",
	            "Cádiz", "Cantabria", "Castellón", "Ceuta", "Ciudad Real", "Córdoba",
	            "Cuenca", "Gerona", "Granada", "Guadalajara", "Guipúzcoa",
	            "Huelva", "Huesca", "Islas Baleares", "Jaén", "La Coruña",
	            "La Rioja", "Las Palmas", "León", "Lérida", "Lugo",
	            "Madrid", "Málaga", "Melilla", "Murcia", "Navarra", "Orense",
	            "Palencia", "Pontevedra", "Salamanca", "Santa Cruz de Tenerife", "Segovia",
	            "Sevilla", "Soria", "Tarragona", "Teruel", "Toledo",
	            "Valencia", "Valladolid", "Zamora", "Zaragoza"
	        };
	
		private JPanel pnlBotonesAbajo;
	
		private JTextField tf_LinkedIn;
		private JTextField tf_Github;


		private JLabel lblNombre;
		private JTextField tfNombre;
		private JTextField tfApellidos;
		private JTextField tfCorreo;
		private JLabel lblTelefono;
		private JTextField tfTelefono;
		private JLabel lblDatosPersonales;
		private JLabel lblCurriculum;
		
		private JLabel lblDescripcion;
		private JTextArea taDescripcion;
		
		private JButton btnAnadir;
		private JButton btnEliminar;
		
		protected static DefaultListModel<String> modeloListaUbicaciones;
		protected static DefaultListModel modeloListaHabilidades;
		
		
		Usuario usuarioAutenticado;

	public PnlEditarPerfil() {
		
			usuarioAutenticado = PnlBotonera.usuarioAutenticado;
	        
	        setBackground(Color.WHITE);
	        setBounds(0, 0, 900, 650);
	        
	        //BOTONES DE CANCELAR Y ACTUALIZAR LOS CAMBIOS
	        pnlBotonesAbajo = new JPanel();
	        JButton btnCancelar = new JButton("Cancelar");
	        add(btnCancelar);
	        btnCancelar.setBounds(238, 471, 105, 23);
	        
	        JButton btnActualizar = new JButton("Actualizar");
	        add(btnActualizar);
	        btnActualizar.setBounds(390, 471, 105, 23);
	        
	        JPanel pnlListas = new JPanel();
	        
	        
	        
	        pnlBotonesAbajo.add(btnCancelar);
	        pnlBotonesAbajo.add(btnActualizar);
	        add(pnlBotonesAbajo,BorderLayout.SOUTH);

	        
	        
	        
	        if(usuarioAutenticado instanceof Persona) {

	        	setLayout(null);
	        
	        	//DATO NOMBRE
	        	lblNombre = new JLabel("Nombre:");
	        	lblNombre.setBounds(42, 64, 215, 14);
	        	add(lblNombre);
	        
	        	tfNombre = new JTextField();
	        	tfNombre.setBounds(42, 89, 215, 20);
	        	add(tfNombre);
	        	tfNombre.setColumns(10);
	        
	        	//DATO APELLIDO
	        	JLabel lblApellidos = new JLabel("Apellidos:");
	        	lblApellidos.setBounds(42, 117, 215, 14);
	        	add(lblApellidos);
	        
	        	tfApellidos = new JTextField();
	        	tfApellidos.setColumns(10);
	        	tfApellidos.setBounds(42, 142, 215, 20);
	        	add(tfApellidos);
	        
	        	//DATO CORREO
	        	JLabel lblCorreo = new JLabel("Correo electrónico:");
	        	lblCorreo.setBounds(42, 173, 150, 14);
	        	add(lblCorreo);
	        
	        	tfCorreo = new JTextField();
	        	tfCorreo.setColumns(10);
	        	tfCorreo.setBounds(42, 198, 215, 20);
	        	add(tfCorreo);
	        
	        	//DATO GITHUB
	        	JLabel lblGithub = new JLabel("Github:");
	        	lblGithub.setBounds(42, 285, 91, 14);
	        	add(lblGithub);
	        
	        	tf_Github = new JTextField();
	        	tf_Github.setBounds(42, 310, 215, 20);
	        	add(tf_Github);
	        	
	        	//DATO LINKEDIN
	        	JLabel lblLinkedIn = new JLabel("LinkedIn:");
	        	lblLinkedIn.setBounds(42, 341, 150, 14);
	        	add(lblLinkedIn);
	  
	        	tf_LinkedIn = new JTextField();
	        	tf_LinkedIn.setBounds(42, 366, 215, 20);
	        	add(tf_LinkedIn);
	        
	        	//DATO TELEFONO
	        	lblTelefono = new JLabel("Teléfono:");
	        	lblTelefono.setBounds(42, 229, 150, 14);
	        	add(lblTelefono);
	        
		        tfTelefono = new JTextField();
		        tfTelefono.setColumns(10);
		        tfTelefono.setBounds(42, 254, 215, 20);
		        add(tfTelefono);
		        
		        //DATO PROVINCIA
		        JLabel lblProvincia = new JLabel("Provincia:");
		        lblProvincia.setBounds(42, 397, 150, 14);
		        add(lblProvincia);
		        
		        JComboBox<String> cbProvincia = new JComboBox<String>();
		        cbProvincia.setBounds(42, 422, 215, 20);
		        add(cbProvincia);
		        
		        for(String p: provincias) {
		        	cbProvincia.addItem(p);
		        }
		        
		        //CABECERAS DE DATOS
		        lblDatosPersonales = new JLabel("Datos Empresa");
		        lblDatosPersonales.setHorizontalAlignment(SwingConstants.CENTER);
		        lblDatosPersonales.setFont(new Font("Trebuchet MS", Font.BOLD, 24));
		        lblDatosPersonales.setBounds(42, 11, 215, 42);
		        add(lblDatosPersonales);
		        
		        lblCurriculum = new JLabel("Curriculum");
		        lblCurriculum.setHorizontalAlignment(SwingConstants.CENTER);
		        lblCurriculum.setFont(new Font("Trebuchet MS", Font.BOLD, 24));
		        lblCurriculum.setBounds(460, 11, 215, 42);
		        add(lblCurriculum);
		        
		        JLabel lblHabilidades = new JLabel("Habilidades:");
		        lblHabilidades.setBounds(585, 128, 215, 14);
		        
		        //CREACION LISTA DE HABILIDADES DE LA PERSONA
		        modeloListaHabilidades = new DefaultListModel<Habilidad>();
		        JList<Habilidad> listaHab = new JList<Habilidad>();
		        listaHab.setModel(modeloListaHabilidades);
		        JScrollPane spList = new JScrollPane(listaHab);
		        
		        spList.setBounds(460, 74, 215, 351);
		        add(spList);
		        

		        }
		        
	        
	        if(usuarioAutenticado instanceof Empresa) {
	        	Empresa e = (Empresa) usuarioAutenticado;
	        	
		        setLayout(null);
		        
		        //DATO NOMBRE
		        lblNombre = new JLabel("Nombre:");
		        lblNombre.setBounds(42, 80, 215, 14);
		        add(lblNombre);
		        
		        tfNombre = new JTextField(e.getNombre());
		        tfNombre.setBounds(42, 100, 215, 20);
		        tfNombre.setColumns(10);

		        add(tfNombre);
		        
		        
		        //DATO CORREO
		        JLabel lblCorreo = new JLabel("Correo electrónico:");
		        lblCorreo.setBounds(42, 130, 150, 14);
		        add(lblCorreo);
		        
		        tfCorreo = new JTextField(e.getCorreoElectronico());
		        tfCorreo.setColumns(10);
		        tfCorreo.setBounds(42, 150, 215, 20);
		        add(tfCorreo);

		        
		        //DATO TELEFONO
		        lblTelefono = new JLabel("Teléfono:");
		        lblTelefono.setBounds(42, 180, 150, 14);
		        add(lblTelefono);
		        
		        tfTelefono = new JTextField(e.getTelefono());
		        tfTelefono.setColumns(10);
		        tfTelefono.setBounds(42, 200, 215, 20);
		        add(tfTelefono);
		        
		        //DATO DESCRIPCION
		        lblDescripcion = new JLabel("Descripcion:");
		        lblDescripcion.setBounds(42, 230, 150, 14);
		        add(lblDescripcion);
		        
		        
		        // Crear un TextArea
		        JPanel pnlDescripcion = new JPanel();
		        pnlDescripcion.setLayout(new BorderLayout(0,0));
		        pnlDescripcion.setBounds(42,250,215,300);
		        
		        
		        taDescripcion = new JTextArea();
		        taDescripcion.setLineWrap(true); // Activar el ajuste de línea automático
		        taDescripcion.setWrapStyleWord(true); // Ajustar palabras enteras al cambiar de línea

		        
		        
		        // Crear un JScrollPane y agregar el JTextArea a él
		        JScrollPane spDescripcion = new JScrollPane(taDescripcion);
		        spDescripcion.setPreferredSize(new Dimension(250,getHeight()));
		 	    spDescripcion.setMaximumSize( new Dimension(250, getHeight()) );
		        
		        pnlDescripcion.add(spDescripcion,BorderLayout.CENTER);
		        add(pnlDescripcion);
	
		        JComboBox<String> cbProvincia = new JComboBox<String>();
		        cbProvincia.setBounds(460, 560, 215, 20);
		        add(cbProvincia);
		        
		        //BOTONES AÑADIR Y ELIMINAR DE LA LISTA DE UBICACIONES
		        
		        JPanel pnlBotonesLista = new JPanel();
		        
		        btnAnadir = new JButton("Añadir");
		        btnAnadir.setBounds(460, 530, 85,20 );
		        pnlBotonesLista.add(btnAnadir);


		        btnEliminar = new JButton("Eliminar");
		        btnEliminar.setBounds(580,530,85,20);
		        pnlBotonesLista.add(btnEliminar);
		        
		        for(String p: provincias) {
		        	cbProvincia.addItem(p);
		        }
		        
		        lblDatosPersonales = new JLabel("Datos Empresa");
		        lblDatosPersonales.setHorizontalAlignment(SwingConstants.CENTER);
		        lblDatosPersonales.setFont(new Font("Trebuchet MS", Font.BOLD, 24));
		        lblDatosPersonales.setBounds(42, 11, 215, 42);
		        add(lblDatosPersonales);
		        
		        lblCurriculum = new JLabel("Ubicaciones");
		        lblCurriculum.setHorizontalAlignment(SwingConstants.CENTER);
		        lblCurriculum.setFont(new Font("Trebuchet MS", Font.BOLD, 24));
		        lblCurriculum.setBounds(410, 11, 300, 42);
		        add(lblCurriculum);
		        
		        
		        //CREACION JLIST DE UBICACIONES	
		        
		        pnlListas.setBounds(460, 100, 250, 400);
		        pnlListas.setLayout(new BorderLayout());
		        
		        modeloListaUbicaciones = new DefaultListModel<String>();
		        JList<String> listaUbi = new JList<String>();
		        
		        if (e.getUbicaciones()!=null) {
		        	for(String p: e.getUbicaciones()) {
			        	modeloListaUbicaciones.addElement(p);
			        }
		        }
		        

				listaUbi.setModel(modeloListaUbicaciones);
				listaUbi.setBackground(new Color(208, 235, 242));
				JScrollPane spListaUbi = new JScrollPane(listaUbi);
		 	    spListaUbi.setPreferredSize(new Dimension(250,pnlListas.getHeight()));
		 	    spListaUbi.setMaximumSize( new Dimension(250,pnlListas.getHeight()));
		 	    spListaUbi.setBackground(getBackground());
		        
		 	    
		        pnlListas.add(spListaUbi);
		        pnlListas.add(pnlBotonesLista,BorderLayout.SOUTH);
		        add(pnlListas);
	        	

	        }
	        
	}
}

	        
	        