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
	private JLabel lblDesc;
	

	
	
    public PnlRegistroEmpresa(JPanel pnlContenido, CardLayout layoutVentana) {
        setBackground(Color.WHITE);
        setBounds(0, 0, 900, 610);
        setLayout(null);
        
//      Añadir 
        PnlHabilidad pnlHabilidad = new PnlHabilidad();
        pnlContenido.add(pnlHabilidad,"pnlHabilidad");
        
        lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(100, 105, 215, 14);
        add(lblNombre);
        
        tfNombre = new JTextField();
        tfNombre.setBounds(100, 125, 215, 20);
        add(tfNombre);
        tfNombre.setColumns(10);
        
        JLabel lblCorreo = new JLabel("Correo electrónico:");
        lblCorreo.setBounds(100, 156, 150, 14);
        add(lblCorreo);
        
        tfCorreo = new JTextField();
        tfCorreo.setColumns(10);
        tfCorreo.setBounds(100, 176, 215, 20);
        add(tfCorreo);
        
        JLabel lblContrasena = new JLabel("Contraseña:");
        lblContrasena.setBounds(100, 258, 91, 14);
        add(lblContrasena);
        
        JLabel lblRepetirContrasena = new JLabel("Repetir contraseña:\r\n");
        lblRepetirContrasena.setBounds(100, 309, 150, 14);
        add(lblRepetirContrasena);
        
        pfContrasena = new JPasswordField();
        pfContrasena.setBounds(100, 278, 215, 20);
        add(pfContrasena);
        
        pfRepetirContrasena = new JPasswordField();
        pfRepetirContrasena.setBounds(100, 329, 215, 20);
        add(pfRepetirContrasena);
        
        lblTelefono = new JLabel("Teléfono:");
        lblTelefono.setBounds(100, 207, 150, 14);
        add(lblTelefono);
        
        tfTelefono = new JTextField();
        tfTelefono.setColumns(10);
        tfTelefono.setBounds(100, 227, 215, 20);
        add(tfTelefono);
        
        JComboBox<String> cbProvincia = new JComboBox<String>();
        cbProvincia.setBounds(100, 513, 215, 22);
        add(cbProvincia);
        
        for(String p: provincias) {
        	cbProvincia.addItem(p);
        }
        
        JList<String> listaProvincias = new JList<String>();
        
        JScrollPane spProvincias = new JScrollPane(listaProvincias);
        spProvincias.setBounds(100, 380, 215, 130);
        add(spProvincias);
        
        lblDatos = new JLabel("Datos");
        lblDatos.setHorizontalAlignment(SwingConstants.CENTER);
        lblDatos.setFont(new Font("Trebuchet MS", Font.BOLD, 24));
        lblDatos.setBounds(100, 49, 215, 42);
        add(lblDatos);
        
        lblDesc = new JLabel("Descripción");
        lblDesc.setHorizontalAlignment(SwingConstants.CENTER);
        lblDesc.setFont(new Font("Trebuchet MS", Font.BOLD, 24));
        lblDesc.setBounds(585, 49, 215, 42);
        add(lblDesc);
        
        JLabel lblDesc_min = new JLabel("Breve descripción de la empresa:");
        lblDesc_min.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        lblDesc_min.setBounds(522, 105, 215, 14);
        add(lblDesc_min);
        
        JButton btnAnadirHab = new JButton("Añadir");
        btnAnadirHab.setBackground(Color.WHITE);
        btnAnadirHab.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}
        	
        });
        btnAnadirHab.setBounds(100, 537, 89, 23);
        add(btnAnadirHab);
        
        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBackground(Color.WHITE);
        btnEliminar.setBounds(226, 537, 89, 23);
        add(btnEliminar);
        
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(460, 576, 90, 23);
        add(btnCancelar);
        
        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.setBounds(350, 576, 90, 23);
        add(btnAceptar);
        
        JLabel lblProvincia = new JLabel("Provincia:");
        lblProvincia.setBounds(100, 360, 215, 14);
        add(lblProvincia);
        
        JTextArea textArea = new JTextArea();
        
        
        JScrollPane spTextArea = new JScrollPane(textArea);
        spTextArea.setBounds(522, 125, 324, 385);
        add(spTextArea);
        
        //Boton que mande a la pestaña anterior
        btnCancelar.addActionListener(new ActionListener() {
			
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

