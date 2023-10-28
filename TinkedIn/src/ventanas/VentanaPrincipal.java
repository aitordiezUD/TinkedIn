package ventanas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

public class VentanaPrincipal extends JFrame {

	private JPanel pnlContenido;
	


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
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	
	/**
	 * Create the frame.
	 */
	public VentanaPrincipal() {
		setTitle("TinkedIn");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 900, 650);
		setResizable(false);
		pnlContenido = new JPanel();
		pnlContenido.setLayout(new BorderLayout());
		pnlContenido.setBackground(new Color(0, 255, 255));
		pnlContenido.setSize(900, 650);
		PnlLogIn pnlLog = new PnlLogIn(this);
		pnlLog.setSize(450, 600);
		pnlContenido.add(pnlLog,BorderLayout.CENTER);
		
		setContentPane(pnlContenido);
		
			
	}
	
}
