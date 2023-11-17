package ventanas;
import java.awt.Dimension;

import javax.swing.*;
import java.awt.Color;
import java.awt.Font;

public class pnlPuestoDeTrabajo extends JPanel {
	private JTextField tfNombrePT;
	public pnlPuestoDeTrabajo() {
		setBackground(new Color(255, 255, 255));
		
		setPreferredSize( new Dimension( 750,650 ) );
		setLayout(null);
		
		JPanel pnlLista = new JPanel();
		pnlLista.setBounds(566, 84, 174, 476);
		add(pnlLista);
		pnlLista.setLayout(null);
		
		JScrollPane spLista = new JScrollPane();
		spLista.setBounds(10, 11, 154, 454);
		pnlLista.add(spLista);
		
		JList listaHabReq = new JList();
		spLista.setViewportView(listaHabReq);
		
		JLabel lblTitulo = new JLabel("PUESTO DE TRABAJO\r\n");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(21, 30, 400, 92);
		add(lblTitulo);
		
		JPanel pnlDatos = new JPanel();
		pnlDatos.setBounds(21, 138, 400, 379);
		add(pnlDatos);
		pnlDatos.setLayout(null);
		
		JLabel lblNombrePT = new JLabel("Nombre del puesto:");
		lblNombrePT.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombrePT.setBounds(10, 24, 112, 29);
		pnlDatos.add(lblNombrePT);
		
		tfNombrePT = new JTextField();
		tfNombrePT.setBounds(132, 28, 86, 20);
		pnlDatos.add(tfNombrePT);
		tfNombrePT.setColumns(10);
		
		JLabel lblDescripcionPT = new JLabel("Descripción del puesto:");
		lblDescripcionPT.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescripcionPT.setBounds(5, 110, 132, 29);
		pnlDatos.add(lblDescripcionPT);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(147, 75, 173, 201);
		pnlDatos.add(textArea);
		
	}
}
