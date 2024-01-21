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
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;

import clases.Habilidad;
import clases.PuestoTrabajo;
import datos.DatosFicheros;
import usuarios.Empresa;
import usuarios.Persona;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import java.awt.ComponentOrientation;

public class PnlRegistroEmpresa extends JPanel {
	
//	Provincias de España para añadirlas al ComboBox
	private final String[] provincias = {
            "Álava", "Albacete", "Alicante", "Almería", "Asturias",
            "Ávila", "Badajoz", "Barcelona", "Bizkaia", "Burgos", "Cáceres",
            "Cádiz", "Cantabria", "Castellón", "Ceuta", "Ciudad Real", "Córdoba",
            "Cuenca", "Gerona", "Granada", "Guadalajara", "Guipúzcoa",
            "Huelva", "Huesca", "Islas Baleares", "Jaén", "La Coruña",
            "La Rioja", "Las Palmas", "León", "Lérida", "Lugo",
            "Madrid", "Málaga", "Melilla", "Murcia", "Navarra", "Orense"                                                                                                                          ,
            "Palencia", "Pontevedra", "Salamanca", "Santa Cruz de Tenerife", "Segovia",
            "Sevilla", "Soria", "Tarragona", "Teruel", "Toledo",
            "Valencia", "Valladolid", "Zamora", "Zaragoza"
        };
	private JLabel lblNombre;
	private JTextField tfNombre;
	private JTextField tfUsername;
	private JTextField tfCorreo;
	private JPasswordField pfContrasena;
	private JPasswordField pfRepetirContrasena;
	private JLabel lblTelefono;
	private JTextField tfTelefono;
	private JLabel lblDatos;
	private JLabel lblDesc;
	private DefaultListModel<String> modeloLista;
	private File selectedFile = null;
	private JTextArea tADescripcion;
	private JComboBox<String> cbProvincia;
	
    public PnlRegistroEmpresa(JPanel pnlContenido, CardLayout layoutVentana) {
        setBackground(Color.WHITE);
        setBounds(0, 0, 900, 610);
        setLayout(new BorderLayout());

//      PANEL IZQUIERDA
        JPanel pnlIzq = new JPanel(new BorderLayout());
        pnlIzq.setBackground(Color.WHITE);
        pnlIzq.setPreferredSize(new Dimension((int) (this.getSize().getWidth()/2),10000));
        add(pnlIzq, BorderLayout.WEST);
        
//      MARGEN NORTH
        JPanel p = new JPanel();
        p.setPreferredSize(new Dimension(50,50));
        p.setBackground(Color.WHITE);
        pnlIzq.add(p, BorderLayout.NORTH);
        
//      MARGEN SOUTH
        p = new JPanel();
        p.setBackground(Color.WHITE);
        p.setPreferredSize(new Dimension(50,50));
        pnlIzq.add(p, BorderLayout.SOUTH);
        
//      MARGEN EAST
        p = new JPanel();
        p.setBackground(Color.WHITE);
        p.setPreferredSize(new Dimension(50,50));
        pnlIzq.add(p, BorderLayout.EAST);
        
//      MARGEN SOUTH
        p = new JPanel();
        p.setBackground(Color.WHITE);
        p.setPreferredSize(new Dimension(50,50));
        pnlIzq.add(p, BorderLayout.WEST);
        
        JPanel pnlIzqCont = new JPanel();
        pnlIzqCont.setBackground(Color.WHITE);
        pnlIzqCont.setLayout(new BoxLayout(pnlIzqCont, BoxLayout.Y_AXIS));
        pnlIzq.add(pnlIzqCont);
        
        
//      PANEL DERECHA  
        JPanel pnlDer = new JPanel(new BorderLayout());
        pnlDer.setBackground(new Color(255, 255, 255));
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
        
        
        
//      Añadir 
        lblDatos = new JLabel("Datos");
        lblDatos.setHorizontalAlignment(SwingConstants.CENTER);
        lblDatos.setFont(new Font("Trebuchet MS", Font.BOLD, 24));
        p = new JPanel(new BorderLayout());
        p.setBackground(Color.WHITE);
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
        check.setPreferredSize(new Dimension(25,25));
        check.setBackground(Color.WHITE);
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
        
        modeloLista = new DefaultListModel<String>();
        JList<String> listaProvincias = new JList<String>();
        listaProvincias.setModel(modeloLista);
        
        JScrollPane spProvincias = new JScrollPane(listaProvincias);
        p = new JPanel(new BorderLayout());
        p.setBackground(Color.WHITE);
        p.setMaximumSize(new Dimension(300,300));
        p.add(spProvincias);
        pnlIzqCont.add(p);
        
        cbProvincia = new JComboBox<String>();
        p = new JPanel(new BorderLayout());
        p.setBackground(Color.WHITE);
        p.setMaximumSize(new Dimension(300,30));
        p.add(cbProvincia);
        pnlIzqCont.add(p);
        
        for(String prov: provincias) {
        	cbProvincia.addItem(prov);
        }
        
        p = new JPanel(new FlowLayout());
        p.setBackground(Color.WHITE);
        JButton btnAnadirProv = new JButton("Añadir");
        btnAnadirProv.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				modeloLista.add(modeloLista.size(), cbProvincia.getSelectedItem().toString());
			}
		});
        
        btnAnadirProv.setBackground(Color.WHITE);
        p.add(btnAnadirProv);
        
        
        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				modeloLista.remove(listaProvincias.getSelectedIndex());
			}
		});
        btnEliminar.setBackground(Color.WHITE);
        p.add(btnEliminar);
        
        pnlIzqCont.add(p);
        


        
        btnEliminar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				modeloLista.removeElement(listaProvincias.getSelectedValue());
				
			}
		});
        
        p = new JPanel(new FlowLayout());
        p.setBackground(Color.WHITE);
        p.setPreferredSize(new Dimension(300,60));
        JButton btnCancelar = new JButton("Cancelar");
        p.add(btnCancelar, BorderLayout.EAST);
        JButton btnRegistrarse = new JButton("Registrarse");
        p.add(btnRegistrarse, BorderLayout.WEST);	
        add(p, BorderLayout.SOUTH);
			
        lblDesc = new JLabel("Descripción");
        lblDesc.setHorizontalAlignment(SwingConstants.CENTER);
        lblDesc.setFont(new Font("Trebuchet MS", Font.BOLD, 24));
        p = new JPanel(new BorderLayout());
        p.setBackground(Color.WHITE);
        p.setMaximumSize(new Dimension(300,60));
        p.add(lblDesc);
        pnlDerCont.add(p);
        
        JLabel lblDesc_min = new JLabel("Breve descripción de la empresa:");
        lblDesc_min.setHorizontalAlignment(SwingConstants.CENTER);
        p = new JPanel(new BorderLayout());
        p.setMaximumSize(new Dimension(300,50));
        p.setBackground(Color.WHITE);
        p.setPreferredSize(new Dimension(300,40));
        p.add(lblDesc_min);
        pnlDerCont.add(p);
        
        
        tADescripcion = new JTextArea();
        JScrollPane spTextArea = new JScrollPane(tADescripcion);
        p = new JPanel(new BorderLayout());
        p.setBackground(Color.WHITE);
        p.setMaximumSize(new Dimension(300,10000));
        p.add(spTextArea);
        pnlDerCont.add(p);
        
        //Boton que mande a la pestaña anterior
        btnCancelar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				layoutVentana.show(pnlContenido, "pnlLogIn");
				
			}
		});
        
        btnRegistrarse.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
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
//			        } else if (VentanaPrincipal.getDatos().containsTelefono(tfTelefono.getText())) {
			        } else if (VentanaPrincipal.servicio.contieneTelefono(tfTelefono.getText())) {
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
		        	ArrayList<String> ubis = crearArrayListUbicaciones();
//		        	ArrayList<PuestoTrabajo> puestos = new ArrayList<>();
		        	
//		        	Empresa emp = new Empresa(tfNombre.getText(), tfTelefono.getText(),
//		        			tfCorreo.getText(), tADescripcion.getText(),
//		        			ubis, selectedFile, contrasena1);
		        	
		        	Object[] atribsEmp = {tfNombre.getText(), tfTelefono.getText(),
		        			tfCorreo.getText(), tADescripcion.getText(),
		        			ubis, selectedFile, contrasena1};
//		        	VentanaPrincipal.getDatos().anadirUsuarioEmpresa(emp);
		        	VentanaPrincipal.servicio.crearUsuario(atribsEmp);
		        	layoutVentana.show(pnlContenido, "pnlLogIn");
		        	
		        }
			}
        	
        });
      
        

    }
    
    @Override
    protected void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	
    	g.setColor(Color.BLACK);
    	g.drawLine(450, 50, 450, 540);
    }
    
    private ArrayList<String> crearArrayListUbicaciones() {
    	ArrayList<String> arr = new ArrayList<>();
    	for(int i = 0; i<modeloLista.size(); i++) {
    		arr.add((String) modeloLista.get(i));
    	}
    	return arr;
    }
    public void limpiarCampos () {
    	tfNombre.setText("");
    	tfCorreo.setText("");
    	tfTelefono.setText("");
    	pfContrasena.setText("");
    	pfRepetirContrasena.setText("");
    	tADescripcion.setText("");
    	cbProvincia.setSelectedIndex(0);
    	selectedFile = null;
    	modeloLista.clear();
    	this.repaint();
    }
    
}

