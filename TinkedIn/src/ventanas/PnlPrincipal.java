package ventanas;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
		
		JPanel pnlFuncional = new JPanel();
		pnlFuncional.setBackground(Color.BLACK);
		pnlFuncional.setBounds(150, 0, 750, 650);
		add(pnlFuncional);

	}

}
