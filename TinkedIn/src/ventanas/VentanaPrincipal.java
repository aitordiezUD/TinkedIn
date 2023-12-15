package ventanas;

import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import chat.Servidor;
import clases.DatosFicheros;
import clases.Empresa;
import clases.Persona;
import nube.ImagenesAzure;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;

public class VentanaPrincipal extends JFrame {

	private JPanel pnlContenido;
	private static DatosFicheros datos;
	
	


	public static DatosFicheros getDatos() {
		return datos;
	}



	public static void setDatos(DatosFicheros datos) {
		VentanaPrincipal.datos = datos;
	}



	public JPanel getPnlContenido() {
		return pnlContenido;
	}



	public void setPnlContenido(JPanel pnlContenido) {
		this.pnlContenido = pnlContenido;
	}



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		datos = new DatosFicheros();
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal(datos);
					frame.setVisible(true);
					System.out.println(DatosFicheros.getPersonas());
					System.out.println(DatosFicheros.getEmpresas());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	
	/**
	 * Create the frame.
	 */
	public VentanaPrincipal( DatosFicheros datos ) {
		setTitle("TinkedIn");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(900, 650);
//		setResizable(false);
		setLocationRelativeTo(null);
		new ImagenesAzure();
		pnlContenido = new JPanel();
		pnlContenido.setLayout(new BorderLayout());
		pnlContenido.setBackground(new Color(0, 255, 255));
		pnlContenido.setSize(900, 650);
		CardLayout layoutVentana = new CardLayout();
		pnlContenido.setLayout(layoutVentana);
		PnlLogIn pnlLog = new PnlLogIn(pnlContenido,layoutVentana);
		pnlLog.setSize(450, 600);
		pnlContenido.add(pnlLog,"pnlLogIn");
		layoutVentana.show(pnlContenido, "pnlLogIn");
		
		
		setContentPane(pnlContenido);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				getDatos().fin();
			}
		});
			
	}
	
}
