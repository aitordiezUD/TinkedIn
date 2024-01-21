package ventanas;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import nube.ImagenesAzure;
import servidor.ServicioPersistencia;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

public class VentanaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel pnlContenido;
	protected static ServicioPersistencia servicio;
	protected static Color ColorBase = new Color(208, 235, 242);
	protected static Font fuente_titulos = new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 18);

	public JPanel getPnlContenido() {
		return pnlContenido;
	}

	public void setPnlContenido(JPanel pnlContenido) {
		this.pnlContenido = pnlContenido;
	}

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

	public VentanaPrincipal() {
		servicio = new ServicioPersistencia();
		servicio.init();
		setTitle("TinkedIn");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(900, 650);
		setLocationRelativeTo(null);
		ImageIcon icon = new ImageIcon("TinkedinPNG.png");
		Image iconImage = icon.getImage();
		setIconImage(iconImage);
		new ImagenesAzure();
		pnlContenido = new JPanel();
		pnlContenido.setLayout(new BorderLayout());
		pnlContenido.setBackground(new Color(0, 255, 255));
		pnlContenido.setSize(900, 650);
		CardLayout layoutVentana = new CardLayout();
		pnlContenido.setLayout(layoutVentana);
		PnlLogIn pnlLog = new PnlLogIn(pnlContenido, layoutVentana, this);
		pnlLog.setSize(450, 600);
		pnlContenido.add(pnlLog, "pnlLogIn");
		layoutVentana.show(pnlContenido, "pnlLogIn");
		setContentPane(pnlContenido);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				servicio.close();
				System.out.println("Servicio cerrado");
			}
		});
	}
}
