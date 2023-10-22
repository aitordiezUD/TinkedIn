package ventanas;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import clases.Main;

public class PnlPrincipal extends JPanel {

	/**
	 * Create the panel.
	 */
	public PnlPrincipal() {
		setLayout(null);
		
		JPanel PnlBotones = new JPanel();
		PnlBotones.setBounds(0, 0, 150, 650);
		PnlBotones.setBackground(new Color(208, 235, 242));
		add(PnlBotones);
		PnlBotones.setLayout(null);
		
		JPanel pnlPerfil = new JPanel();
		pnlPerfil.setBounds(0, 161, 150, 38);
		PnlBotones.add(pnlPerfil);
		pnlPerfil.setLayout(null);
		
		JLabel lblPerfil = new JLabel("Mi Perfil");
		lblPerfil.setHorizontalAlignment(SwingConstants.CENTER);
		lblPerfil.setBounds(44, 11, 65, 14);
		pnlPerfil.add(lblPerfil);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 198, 150, 38);
		PnlBotones.add(panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 235, 150, 38);
		PnlBotones.add(panel_2);
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setBounds(0, 272, 150, 38);
		PnlBotones.add(panel_2_1);
		
		JPanel pnlFuncional = new JPanel();
		pnlFuncional.setBackground(Color.BLACK);
		pnlFuncional.setBounds(150, 0, 750, 650);
		setSize(900, 650);
		add(pnlFuncional);

	}
	
//	public static void main(String[] args) {
//		JFrame frame = new JFrame();
//		frame.setSize(900, 650);
//		frame.setVisible(true);
//		frame.getContentPane().add(new PnlPrincipal());
//	}
}
