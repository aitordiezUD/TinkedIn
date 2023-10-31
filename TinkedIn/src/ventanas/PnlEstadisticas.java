package ventanas;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.Font;
import java.awt.Color;

public class PnlEstadisticas extends JPanel {
	JTable tablaEstadisticas;
	DefaultTableModel modelo;
	
	public PnlEstadisticas() {
		setLayout(null);
		
		//Crear los componentes
		
		JPanel pnlTitulo = new JPanel();
		pnlTitulo.setBackground(new Color(176, 224, 230));
		pnlTitulo.setBounds(0, 0, 750, 170);
		add(pnlTitulo);
		pnlTitulo.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Estadísticas");
		lblTitulo.setFont(new Font("Tw Cen MT", Font.PLAIN, 50));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(181, 40, 400, 100);
		pnlTitulo.add(lblTitulo);
		
		JPanel pnlTabla = new JPanel();
		pnlTabla.setBackground(new Color(255, 239, 213));
		pnlTabla.setBounds(0, 170, 750, 425);
		add(pnlTabla);
		
		tablaEstadisticas = new JTable();
		
		//Crear el modelo de la tabla con columnas aleatorias
		
		modelo = new DefaultTableModel();
		modelo.addColumn("aleatorio1");
		modelo.addColumn("aleatorio2");
		modelo.addColumn("aleatorio3");
		modelo.addColumn("aleatorio4");
		modelo.addColumn("aleatorio5");
		
		tablaEstadisticas.setModel(modelo);
		tablaEstadisticas.getTableHeader().setReorderingAllowed(false);
		pnlTabla.add(new JScrollPane(tablaEstadisticas));
	}
	
	public static void main(String[] args) {
		JFrame vent = new JFrame();
		vent.getContentPane().add(new PnlEstadisticas());
		vent.setVisible(true);
		vent.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		vent.setSize(750,600);
		
	}
}
