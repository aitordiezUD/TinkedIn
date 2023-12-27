package ventanas;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import clases.Habilidad;
import datos.DatosFicheros;
import usuarios.Persona;
import usuarios.Usuario;

import javax.swing.JList;
import javax.swing.JOptionPane;

public class PnlRegistroPersona extends JPanel {
	
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
	private JPasswordField pfContrasena;
	private JPasswordField pfRepetirContrasena;
	private JLabel lblTelefono;
	private JTextField tfTelefono;
	private JLabel lblDatos;
	private JLabel lblCurriculum;
	private CardLayout layoutVentana;
	private JPanel pnlContenido;
	private JList<Habilidad> listaHabilidades;
	private DefaultListModel modeloLista;
	private File selectedFile = null;


	public PnlRegistroPersona(JPanel pnlContenido, CardLayout layoutVentana) {
		setBackground(Color.WHITE);
        setBounds(0, 0, 900, 650);
        setLayout(new BorderLayout());
        
        this.layoutVentana = layoutVentana;
        this.pnlContenido = pnlContenido;
        
//      PANEL IZQUIERDA
        JPanel pnlIzq = new JPanel(new BorderLayout());
        pnlIzq.setBackground(new Color(255, 255, 255));
        pnlIzq.setPreferredSize(new Dimension((int) (this.getSize().getWidth()/2),10000));
        add(pnlIzq, BorderLayout.WEST);
        
//      MARGEN NORTH
        JPanel p = new JPanel();
        p.setPreferredSize(new Dimension(50,50));
        p.setBackground(Color.WHITE);
        pnlIzq.add(p, BorderLayout.NORTH);
        
//      MARGEN SOUTH
        p = new JPanel();
        p.setPreferredSize(new Dimension(50,50));
        p.setBackground(Color.WHITE);
        pnlIzq.add(p, BorderLayout.SOUTH);
        
//      MARGEN EAST
        p = new JPanel();
        p.setPreferredSize(new Dimension(50,50));
        p.setBackground(Color.WHITE);
        pnlIzq.add(p, BorderLayout.EAST);
        
//      MARGEN SOUTH
        p = new JPanel();
        p.setPreferredSize(new Dimension(50,50));
        p.setBackground(Color.WHITE);
        pnlIzq.add(p, BorderLayout.WEST);
        
        JPanel pnlIzqCont = new JPanel();
        pnlIzqCont.setBackground(Color.WHITE);
        pnlIzqCont.setLayout(new BoxLayout(pnlIzqCont, BoxLayout.Y_AXIS));
        pnlIzq.add(pnlIzqCont);
        
        
//      PANEL DERECHA  
        JPanel pnlDer = new JPanel(new BorderLayout());
        p.setBackground(Color.WHITE);
        pnlDer.setPreferredSize(new Dimension((int) (this.getSize().getWidth()/2),10000));
//        SwingUtilities.invokeLater(() -> {pnlIzq.setPreferredSize(new Dimension((int) (this.getSize().getWidth()/2),10000));});
        add(pnlDer, BorderLayout.EAST);
        
//      MARGEN NORTH
        p = new JPanel();
        p.setBackground(Color.WHITE);
        p.setPreferredSize(new Dimension(50,50));
        pnlDer.add(p, BorderLayout.NORTH);
        
//      MARGEN SOUTH
        p = new JPanel();
        p.setBackground(Color.WHITE);
        p.setPreferredSize(new Dimension(50,50));
        pnlDer.add(p, BorderLayout.SOUTH);
        
//      MARGEN EAST
        p = new JPanel();
        p.setBackground(Color.WHITE);
        p.setPreferredSize(new Dimension(50,50));
        pnlDer.add(p, BorderLayout.EAST);
        
//      MARGEN SOUTH
        p = new JPanel();
        p.setBackground(Color.WHITE);
        p.setPreferredSize(new Dimension(50,50));
        pnlDer.add(p, BorderLayout.WEST);
        
        JPanel pnlDerCont = new JPanel();
        pnlDerCont.setBackground(Color.WHITE);
        pnlDerCont.setLayout(new BoxLayout(pnlDerCont, BoxLayout.Y_AXIS));
        pnlDer.add(pnlDerCont);
        
        lblDatos = new JLabel("Datos personales");
        lblDatos.setHorizontalAlignment(SwingConstants.CENTER);
        lblDatos.setFont(new Font("Trebuchet MS", Font.BOLD, 24));
        p = new JPanel(new BorderLayout());
        p.setMaximumSize(new Dimension(300,60));
        p.add(lblDatos);
        pnlIzqCont.add(p);
        
        lblNombre = new JLabel("Nombre:");
        p = new JPanel(new BorderLayout());
        p.setBackground(Color.WHITE);
        p.setMaximumSize(new Dimension(300,25));
        p.add(lblNombre);
        pnlIzqCont.add(p);
        
        tfNombre = new JTextField();
        tfNombre.setColumns(10);
        p = new JPanel(new BorderLayout());
        p.setBackground(Color.WHITE);
        p.setMaximumSize(new Dimension(300,25));
        p.add(tfNombre);
        pnlIzqCont.add(p);
        
        JLabel lblApellidos = new JLabel("Apellidos:");
        p = new JPanel(new BorderLayout());
        p.setBackground(Color.WHITE);
        p.setMaximumSize(new Dimension(300,25));
        p.add(lblApellidos);
        pnlIzqCont.add(p);
        
        tfApellidos = new JTextField();
        tfApellidos.setColumns(10);
        p = new JPanel(new BorderLayout());
        p.setBackground(Color.WHITE);
        p.setMaximumSize(new Dimension(300,25));
        p.add(tfApellidos);
        pnlIzqCont.add(p);
        
        JLabel lblCorreo = new JLabel("Correo electrónico:");
        p = new JPanel(new BorderLayout());
        p.setBackground(Color.WHITE);
        p.setMaximumSize(new Dimension(300,25));
        p.add(lblCorreo);
        pnlIzqCont.add(p);
        
        tfCorreo = new JTextField();
        p = new JPanel(new BorderLayout());
        p.setBackground(Color.WHITE);
        p.setMaximumSize(new Dimension(300,25));
        p.add(tfCorreo);
        pnlIzqCont.add(p);
        
        lblTelefono = new JLabel("Teléfono:");
        p = new JPanel(new BorderLayout());
        p.setBackground(Color.WHITE);
        p.setMaximumSize(new Dimension(300,25));
        p.add(lblTelefono);
        pnlIzqCont.add(p);
        
        tfTelefono = new JTextField();
        tfTelefono.setColumns(10);
        p = new JPanel(new BorderLayout());
        p.setBackground(Color.WHITE);
        p.setMaximumSize(new Dimension(300,25));
        p.add(tfTelefono);
        pnlIzqCont.add(p);
        
        JLabel lblContrasena = new JLabel("Contraseña:");
        p = new JPanel(new BorderLayout());
        p.setBackground(Color.WHITE);
        p.setMaximumSize(new Dimension(300,25));
        p.add(lblContrasena);
        pnlIzqCont.add(p);
        
        pfContrasena = new JPasswordField();
        p = new JPanel(new BorderLayout());
        p.setBackground(Color.WHITE);
        p.setMaximumSize(new Dimension(300,25));
        p.add(pfContrasena);
        pnlIzqCont.add(p);
        
        JLabel lblRepetirContrasena = new JLabel("Repetir contraseña:");
        p = new JPanel(new BorderLayout());
        p.setBackground(Color.WHITE);
        p.setMaximumSize(new Dimension(300,25));
        p.add(lblRepetirContrasena);
        pnlIzqCont.add(p);
        
        pfRepetirContrasena = new JPasswordField();
        p = new JPanel(new BorderLayout());
        p.setBackground(Color.WHITE);
        p.setMaximumSize(new Dimension(300,25));
        p.add(pfRepetirContrasena);
        pnlIzqCont.add(p);
        
        JLabel lblImagen = new JLabel("Seleccionar foto de perfil:");
        p = new JPanel(new BorderLayout());
        p.setBackground(Color.WHITE);
        p.setMaximumSize(new Dimension(300,25));
        p.add(lblImagen);
        pnlIzqCont.add(p);
        
        JButton btnSelImg = new JButton("Seleccionar imagen");
        JCheckBox check = new JCheckBox();
        check.setEnabled(false);
        check.setBackground(Color.WHITE);
        check.setPreferredSize(new Dimension(25,25));
        p = new JPanel(new BorderLayout());
        p.setBackground(Color.WHITE);
        p.setMaximumSize(new Dimension(300,25));
        p.add(btnSelImg);
        p.add(check, BorderLayout.EAST);
        pnlIzqCont.add(p);
        
        btnSelImg.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFileChooser fileChooser = new JFileChooser();

		        // Filtrar para mostrar solo archivos de imagen
		        FileNameExtensionFilter imageFilter = new FileNameExtensionFilter(
		                "Archivos de imagen", "jpg", "jpeg", "png");
		        fileChooser.setFileFilter(imageFilter);

		        // Mostrar el diálogo para seleccionar un archivo
		        int result = fileChooser.showOpenDialog(null);

		        // Verificar si el usuario seleccionó un archivo
		        if (result == JFileChooser.APPROVE_OPTION) {
		            selectedFile = fileChooser.getSelectedFile();
		            check.setSelected(true);
		        } else {
		        	check.setSelected(false);
		        }
			}
		});
        
        JLabel lblProvincia = new JLabel("Provincia:");
        p = new JPanel(new BorderLayout());
        p.setMaximumSize(new Dimension(300,25));
        p.add(lblProvincia);
        p.setBackground(Color.WHITE);
        pnlIzqCont.add(p);
        
        JComboBox<String> cbProvincia = new JComboBox<String>();
        p = new JPanel(new BorderLayout());
        p.setBackground(Color.WHITE);
        p.setMaximumSize(new Dimension(300,30));
        p.add(cbProvincia);
        pnlIzqCont.add(p);
        
        for(String prov: provincias) {
        	cbProvincia.addItem(prov);
        }
        

        
        lblCurriculum = new JLabel("Curriculum");
        lblCurriculum.setHorizontalAlignment(SwingConstants.CENTER);
        lblCurriculum.setFont(new Font("Trebuchet MS", Font.BOLD, 24));
        p = new JPanel(new BorderLayout());
        p.setMaximumSize(new Dimension(300,60));
        p.setPreferredSize(new Dimension(300,60));
        p.add(lblCurriculum);
        pnlDerCont.add(p);
        
        JLabel lblHabilidades = new JLabel("Habilidades:");
        p = new JPanel(new BorderLayout());
        p.setBackground(Color.WHITE);
        p.setMaximumSize(new Dimension(300,30));
        p.setPreferredSize(new Dimension(300,30));
        p.add(lblHabilidades);
        pnlDerCont.add(p);
        
        listaHabilidades = new JList<Habilidad>();
        modeloLista = new DefaultListModel<Habilidad>();
        listaHabilidades.setModel(modeloLista);
        
        JScrollPane spList = new JScrollPane(listaHabilidades);
        p = new JPanel(new BorderLayout());
        p.setBackground(Color.WHITE);
        p.setMaximumSize(new Dimension(300,10000));
//        p.setPreferredSize(new Dimension(300,300));
        p.add(spList);
        pnlDerCont.add(p);
        
        p = new JPanel(new FlowLayout());
        p.setBackground(Color.WHITE);
        p.setMaximumSize(new Dimension(300,60));
        JButton btnAnadirHab = new JButton("Añadir");
        p.add(btnAnadirHab);
        btnAnadirHab.setBackground(Color.WHITE);
        btnAnadirHab.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
//				Al hacer click que se muestre el panel para añadir habilidades
				asignarModeloParaPnlHabilidad();
				layoutVentana.show(pnlContenido,"pnlHabilidad");
			}
        	
        });
//        add(btnAnadirHab);
        
        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBackground(Color.WHITE);
        p.add(btnEliminar);
        pnlDerCont.add(p);
        
        p = new JPanel(new FlowLayout());
        p.setBackground(Color.WHITE);
        p.setMaximumSize(new Dimension(3000,50));
        
        JButton btnRegistrarse = new JButton("Registrarse");
        p.add(btnRegistrarse);
        
        JButton btnAtras = new JButton("Cancelar");
        p.add(btnAtras);
        add(p,BorderLayout.SOUTH);
        
        
        //Listener del boton registrarse
        
        btnRegistrarse.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				char[] passwordChars1 = pfContrasena.getPassword();
		        char[] passwordChars2 = pfRepetirContrasena.getPassword();

		        String contrasena1 = new String(passwordChars1);
		        String contrasena2 = new String(passwordChars2);
		        
		        
		        if (!contrasena1.equals(contrasena2)) {
		        	JOptionPane.showOptionDialog(
							null, 
							"Las dos contraseñas no coinciden.", 
							"Error", 
							JOptionPane.DEFAULT_OPTION, 
							JOptionPane.INFORMATION_MESSAGE,
							null,
							new Object[] {"Aceptar"}, 
							"Aceptar");	
//		        }else if (VentanaPrincipal.getDatos().containsEmail(tfCorreo.getText())) {
		        }else if (VentanaPrincipal.servicio.contieneCorreo(tfCorreo.getText())) {
		        	JOptionPane.showOptionDialog(
							null, 
							"El correo electrónico se encuentra asociado a otro usuario.", 
							"Error", 
							JOptionPane.DEFAULT_OPTION, 
							JOptionPane.INFORMATION_MESSAGE,
							null,
							new Object[] {"Aceptar"}, 
							"Aceptar");
//		        } else if (VentanaPrincipal.getDatos().containsTelefono(tfTelefono.getText())) {
		        } else if (VentanaPrincipal.servicio.contieneCorreo(tfTelefono.getText())) {
		        	JOptionPane.showOptionDialog(
							null, 
							"El teléfono se encuentra asociado a otro usuario.", 
							"Error", 
							JOptionPane.DEFAULT_OPTION, 
							JOptionPane.INFORMATION_MESSAGE,
							null,
							new Object[] {"Aceptar"}, 
							"Aceptar");
		        }else {
		        	Date fecha;
					try {
						fecha = new SimpleDateFormat("yyyy-MM-dd").parse("2000-01-01");
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						fecha = null;
						e1.printStackTrace();
					}
		        	
		        	ArrayList<Habilidad> habilidades = crearArrayListHabilidades();
					Object[] atribsP = {tfNombre.getText(), tfApellidos.getText(), ( String )cbProvincia.getSelectedItem(),
							 fecha, tfCorreo.getText(), tfTelefono.getText(),
							habilidades,selectedFile, contrasena1};
//					VentanaPrincipal.getDatos().anadirUsuarioPersona(p);
					VentanaPrincipal.servicio.crearUsuario(atribsP);
					layoutVentana.show(pnlContenido, "pnlLogIn");
		        }		
			}
        } );
        
        //Boton que mande a la pestaña anterior
        btnAtras.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				asignarModeloParaPnlHabilidad();
				layoutVentana.show(pnlContenido, "pnlLogIn");
				
			}
		});
        
      
        
        DefaultListModel<Habilidad> modeloLista = new DefaultListModel<Habilidad>();
        listaHabilidades.setModel(modeloLista);

    }
    
    @Override
    protected void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	
    	g.setColor(Color.BLACK);
    	g.drawLine(450, 50, 450, 540);
    }
    
    public void asignarModeloParaPnlHabilidad() {
    	PnlHabilidad pnlHabilidad = new PnlHabilidad(pnlContenido,layoutVentana,(DefaultListModel<Habilidad>) listaHabilidades.getModel(),0);
        pnlContenido.add(pnlHabilidad,"pnlHabilidad");
    }
    
    private ArrayList<Habilidad> crearArrayListHabilidades() {
    	ArrayList<Habilidad> arr = new ArrayList<Habilidad>();
    	for(int i = 0; i<modeloLista.size(); i++) {
    		arr.add((Habilidad) modeloLista.get(i));
    	}
    	return arr;
    }
    
    public static void lanzarAviso() {
		JOptionPane.showOptionDialog(
				null, 
				"El correo electrónico o teléfono se encuentran asociados a otro usuario.", 
				"Error", 
				JOptionPane.DEFAULT_OPTION, 
				JOptionPane.INFORMATION_MESSAGE,
				null,
				new Object[] {"Aceptar"}, 
				"Aceptar");    	
    };
    
    public static void main(String[] args) {
    	PnlRegistroPersona p = new PnlRegistroPersona(new JPanel(), new CardLayout());
    	JFrame f = new JFrame();
    	f.setSize(900,650);
    	f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	f.add(p);
    	
    	f.setVisible(true);
	}
    
}

