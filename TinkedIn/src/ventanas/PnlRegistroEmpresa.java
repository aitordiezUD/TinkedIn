package ventanas;

import javax.swing.JPanel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;

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

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.SwingConstants;

import clases.Habilidad;

import javax.swing.JList;

public class PnlRegistroEmpresa extends JPanel {
	
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
	private JTextField tfUsername;
	private JTextField tfCorreo;
	private JPasswordField pfContrasena;
	private JPasswordField pfRepetirContrasena;
	private JLabel lblTelefono;
	private JTextField tfTelefono;
	private JLabel lblDatos;
	private JLabel lblCurriculum;
	

	
	
    public PnlRegistroEmpresa(JPanel pnlContenido, CardLayout layoutVentana) {
        setBackground(Color.WHITE);
        setBounds(0, 0, 900, 650);
        setLayout(null);
        
//      Añadir 
        PnlHabilidad pnlHabilidad = new PnlHabilidad();
        pnlContenido.add(pnlHabilidad,"pnlHabilidad");
        
        lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(100, 128, 215, 14);
        add(lblNombre);
        
        tfNombre = new JTextField();
        tfNombre.setBounds(100, 148, 215, 20);
        add(tfNombre);
        tfNombre.setColumns(10);
        
        JLabel lblCorreo = new JLabel("Correo electrónico:");
        lblCorreo.setBounds(100, 179, 150, 14);
        add(lblCorreo);
        
        tfCorreo = new JTextField();
        tfCorreo.setColumns(10);
        tfCorreo.setBounds(100, 199, 215, 20);
        add(tfCorreo);
        
        JLabel lblContrasena = new JLabel("Contraseña:");
        lblContrasena.setBounds(100, 281, 91, 14);
        add(lblContrasena);
        
        JLabel lblRepetirContrasena = new JLabel("Repetir contraseña:\r\n");
        lblRepetirContrasena.setBounds(100, 332, 150, 14);
        add(lblRepetirContrasena);
        
        pfContrasena = new JPasswordField();
        pfContrasena.setBounds(100, 301, 215, 20);
        add(pfContrasena);
        
        pfRepetirContrasena = new JPasswordField();
        pfRepetirContrasena.setBounds(100, 352, 215, 20);
        add(pfRepetirContrasena);
        
        lblTelefono = new JLabel("Teléfono:");
        lblTelefono.setBounds(100, 230, 150, 14);
        add(lblTelefono);
        
        tfTelefono = new JTextField();
        tfTelefono.setColumns(10);
        tfTelefono.setBounds(100, 250, 215, 20);
        add(tfTelefono);
        
        JLabel lblProvincia = new JLabel("Provincia:");
        lblProvincia.setBounds(100, 434, 150, 14);
        add(lblProvincia);
        
        JComboBox<String> cbProvincia = new JComboBox<String>();
        cbProvincia.setBounds(100, 454, 215, 22);
        add(cbProvincia);
        
        for(String p: provincias) {
        	cbProvincia.addItem(p);
        }
        
        lblDatos = new JLabel("Datos");
        lblDatos.setHorizontalAlignment(SwingConstants.CENTER);
        lblDatos.setFont(new Font("Trebuchet MS", Font.BOLD, 24));
        lblDatos.setBounds(100, 72, 215, 42);
        add(lblDatos);
        
        lblCurriculum = new JLabel("Curriculum");
        lblCurriculum.setHorizontalAlignment(SwingConstants.CENTER);
        lblCurriculum.setFont(new Font("Trebuchet MS", Font.BOLD, 24));
        lblCurriculum.setBounds(585, 72, 215, 42);
        add(lblCurriculum);
        
        JLabel lblHabilidades = new JLabel("Habilidades:");
        lblHabilidades.setBounds(585, 128, 215, 14);
        add(lblHabilidades);
        
        JList<Habilidad> listaHabilidades = new JList<Habilidad>();
        
        JScrollPane spList = new JScrollPane(listaHabilidades);
        spList.setBounds(585, 148, 215, 351);
        add(spList);
        
        JButton btnAñadirHab = new JButton("Añadir");
        btnAñadirHab.setBackground(Color.WHITE);
        btnAñadirHab.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
//				Al hacer click que se muestre el panel para añadir habilidades
				layoutVentana.show(pnlContenido,"pnlHabilidad");
			}
        	
        });
        btnAñadirHab.setBounds(585, 505, 89, 23);
        add(btnAñadirHab);
        
        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBackground(Color.WHITE);
        btnEliminar.setBounds(711, 505, 89, 23);
        add(btnEliminar);
        
        JButton btnAtras = new JButton("Cancelar");
        btnAtras.setBounds(405, 573, 90, 23);
        add(btnAtras);
        
        //Boton que mande a la pestaña anterior
        btnAtras.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				layoutVentana.show(pnlContenido, "pnlLogIn");
				
			}
		});
        
      
        
        DefaultListModel<String> modeloLista = new DefaultListModel<String>();

    }
    
    @Override
    protected void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	
    	g.setColor(Color.BLACK);
    	g.drawLine(450, 50, 450, 540);
    }
}
