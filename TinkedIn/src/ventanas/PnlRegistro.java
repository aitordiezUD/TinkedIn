package ventanas;

import javax.swing.JPanel;
import javax.swing.JButton;

public class PnlRegistro extends JPanel {

	private static final long serialVersionUID = 1L;
	/**
	 * Create the panel.
	 */
	
	private JButton btnAtras;
	
	
	public JButton getBtnAtras() {
		return btnAtras;
	}


	public void setBtnAtras(JButton btnAtras) {
		this.btnAtras = btnAtras;
	}


	public PnlRegistro() {
		
		setBounds(0, 0, 410, 650);
		setLayout(null);
		
		btnAtras = new JButton("<--");
		btnAtras.setBounds(160, 537, 89, 23);
		add(btnAtras);
		
	}

}
