package ventanas;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
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
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import clases.Habilidad;
import clases.Persona;
import clases.Usuario;

public class PnlEditarPerfil  extends JPanel {
	
	private JTextField tf_Nombre;
	private JTextField tf_apellido;
	private JTextField tf_Telefono;
	private JTextField tf_Correo;
	private JTextField tf_LinkedIn;
	private JTextField tf_Github;
	private JTextField textFieldExp;
	private JTextField textFieldDes;
	private PnlMiPerfil pnlMiPerfil;

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
		private JLabel lblNombre;
		private JTextField tfNombre;
		private JTextField tfApellidos;
		private JTextField tfUsername;
		private JTextField tfCorreo;
		private JTextField tfGithub;
		private JTextField tfLinkedIn;
		private JLabel lblTelefono;
		private JTextField tfTelefono;
		private JLabel lblDatosPersonales;
		private JLabel lblCurriculum;
		private CardLayout layoutVentana;
		private JPanel pnlContenido;
		private DefaultListModel modeloLista;


	public PnlEditarPerfil() {
	        setBackground(Color.WHITE);
	        setBounds(0, 0, 900, 650);
	        
	        
	        JPanel pnlDatos = new JPanel();
	        JPanel pnlHab = new JPanel();


	        this.layoutVentana = layoutVentana;
	        this.pnlContenido = pnlContenido;
	        setLayout(null);
	        
	        lblNombre = new JLabel("Nombre:");
	        lblNombre.setBounds(42, 64, 215, 14);
	        add(lblNombre);
	        
	        tfNombre = new JTextField();
	        tfNombre.setBounds(42, 89, 215, 20);
	        add(tfNombre);
	        tfNombre.setColumns(10);
	        
	        JLabel lblApellidos = new JLabel("Apellidos:");
	        lblApellidos.setBounds(42, 117, 215, 14);
	        add(lblApellidos);
	        
	        tfApellidos = new JTextField();
	        tfApellidos.setColumns(10);
	        tfApellidos.setBounds(42, 142, 215, 20);
	        add(tfApellidos);
	        
	        JLabel lblCorreo = new JLabel("Correo electrónico:");
	        lblCorreo.setBounds(42, 173, 150, 14);
	        add(lblCorreo);
	        
	        tfCorreo = new JTextField();
	        tfCorreo.setColumns(10);
	        tfCorreo.setBounds(42, 198, 215, 20);
	        add(tfCorreo);
	        
	        JLabel lblGithub = new JLabel("Github:");
	        lblGithub.setBounds(42, 285, 91, 14);
	        add(lblGithub);
	        
	        JLabel lblLinkedIn = new JLabel("LinkedIn:");
	        lblLinkedIn.setBounds(42, 341, 150, 14);
	        add(lblLinkedIn);
	        
	        tf_Github = new JTextField();
	        tf_Github.setBounds(42, 310, 215, 20);
	        add(tf_Github);
	        
	        tf_LinkedIn = new JTextField();
	        tf_LinkedIn.setBounds(42, 366, 215, 20);
	        add(tf_LinkedIn);
	        
	        lblTelefono = new JLabel("Teléfono:");
	        lblTelefono.setBounds(42, 229, 150, 14);
	        add(lblTelefono);
	        
	        tfTelefono = new JTextField();
	        tfTelefono.setColumns(10);
	        tfTelefono.setBounds(42, 254, 215, 20);
	        add(tfTelefono);
	        
	        JLabel lblProvincia = new JLabel("Provincia:");
	        lblProvincia.setBounds(42, 397, 150, 14);
	        add(lblProvincia);
	        
	        JComboBox<String> cbProvincia = new JComboBox<String>();
	        cbProvincia.setBounds(42, 422, 215, 20);
	        add(cbProvincia);
	        
	        for(String p: provincias) {
	        	cbProvincia.addItem(p);
	        }
	        
	        lblDatosPersonales = new JLabel("Datos personales");
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
	        modeloLista = new DefaultListModel<Habilidad>();
	        
	        JScrollPane spList = new JScrollPane();
	        spList.setBounds(460, 74, 215, 351);
	        add(spList);
	        
	        JPanel pnlBotonesHab = new JPanel();
	        pnlBotonesHab.setBackground(Color.WHITE);
	        
	        JButton btnAñadirHab = new JButton("Añadir");
	        btnAñadirHab.setBackground(Color.WHITE);
	        btnAñadirHab.setBounds(585, 505, 89, 23);
	        pnlBotonesHab.add(btnAñadirHab);
	        
	        JButton btnEliminar = new JButton("Eliminar");
	        btnEliminar.setBackground(Color.WHITE);
	        btnEliminar.setBounds(711, 505, 89, 23);
	        
	        
	        JButton btnCancelar = new JButton("Cancelar");
	        add(btnCancelar);
	        btnCancelar.setBounds(238, 471, 105, 23);
	        
	        JButton btnActualizar = new JButton("Actualizar");
	        add(btnActualizar);
	        btnActualizar.setBounds(390, 471, 105, 23);
	        
	}
}

	        
	        