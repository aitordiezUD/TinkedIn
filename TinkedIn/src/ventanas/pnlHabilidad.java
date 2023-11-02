package ventanas;

import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class pnlHabilidad extends JPanel{
	
	private JTree tree;
	private DefaultTreeModel treeModel;
	private ArrayList < String > lAreas;
	
	public pnlHabilidad() {
		setBackground(Color.WHITE);
		
		setLayout(null);
		
		// Crear el panel del arbol
		
		JPanel pnlArbol = new JPanel();
		pnlArbol.setBackground(new Color(194, 146, 199));
		pnlArbol.setBounds(10, 11, 180, 578);
		add(pnlArbol);
		
		// Crear el boton de añadir
		
		JButton btnAñadirHab = new JButton("Añadir");
		btnAñadirHab.setBounds(37, 413, 89, 23);
		pnlArbol.add(btnAñadirHab);
	
		
		
		// Crear el panel de las habilidades para su diseño
		
		JPanel PnlHabi = new JPanel();
		PnlHabi.setBackground(new Color(89, 140, 140));
		PnlHabi.setBounds(200, 11, 547, 578);
		add(PnlHabi);
		PnlHabi.setLayout(null);
		
		JLabel lblHab = new JLabel("Habilidad");
		lblHab.setHorizontalAlignment(SwingConstants.CENTER);
		lblHab.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblHab.setBounds(48, 37, 489, 35);
		PnlHabi.add(lblHab);
		
		JPanel pnlDatosHabi = new JPanel();
		pnlDatosHabi.setBounds(98, 414, 341, 127);
		PnlHabi.add(pnlDatosHabi);
		pnlDatosHabi.setLayout(null);
		
		//Crear los labels
		
		JLabel lblAñosExp = new JLabel("Nivel de Destreza:");
		lblAñosExp.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAñosExp.setBounds(10, 11, 113, 24);
		pnlDatosHabi.add(lblAñosExp);
		
		//Crear el spinner
		
		SpinnerNumberModel spinnerModel = new SpinnerNumberModel(0, 0, 5, 1);
		JSpinner spinner = new JSpinner(spinnerModel);
		spinner.setBounds(133, 12, 30, 24);
		pnlDatosHabi.add(spinner);
		
		// Crear el arbol
		
		DefaultMutableTreeNode root = new DefaultMutableTreeNode( "Habilidades" );
		pnlArbol.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane( );
		scrollPane.setBounds(10, 11, 144, 390);
		pnlArbol.add( scrollPane );
		tree = new JTree();
		treeModel = new DefaultTreeModel(root);
		tree.setModel(treeModel);
		scrollPane.setViewportView(tree);
		
		
		
		//Crear el listener del arbol
		
				tree.addTreeSelectionListener( new TreeSelectionListener() {
					
					@Override
					public void valueChanged(TreeSelectionEvent e) {
						TreePath camino = e.getPath();
					    if (camino.getPathCount() == 2) { // Cambiar a 3 cuando se añadan las habilidades. 
					        Object selectedNode = tree.getLastSelectedPathComponent();
					        if (selectedNode instanceof DefaultMutableTreeNode) {
					            DefaultMutableTreeNode node = (DefaultMutableTreeNode) selectedNode;
					            Object area = node.getUserObject(); // Obtenemos el objeto asociado al nodo
					            if (area != null && area instanceof String) {
					               lblHab.setText( ( String ) area );
					            }
					        }
					    }
						
					}
					
				});
		
		// Añadir las distintas areas de conocimiento
		
		lAreas = new ArrayList<>();
		
		lAreas.add( "Informática" );
		lAreas.add( "Biología" );
		lAreas.add( "Arte y diseño" );
		lAreas.add( "Economía y finanzas" );
		lAreas.add( "Psicología" );
		lAreas.add( "Ciencias Ambientales" );
		lAreas.add( "Deporte" );
		lAreas.add( "Diseño Industrial" );
//		lAreas.add( "Ingenieria" );
//		lAreas.add( "Ingenieria" );
//		lAreas.add( "Ingenieria" );
//		lAreas.add( "Ingenieria" );
//		lAreas.add( "Ingenieria" );
//		lAreas.add( "Ingenieria" );
//		lAreas.add( "Ingenieria" );
//		lAreas.add( "Ingenieria" );
//		lAreas.add( "Ingenieria" );
//		lAreas.add( "Ingenieria" );
//		lAreas.add( "Ingenieria" );
		
		
		// Añadir los nodos hijos
		
		for (String s : lAreas) {
			DefaultMutableTreeNode nuevo = new DefaultMutableTreeNode(s);
			treeModel.insertNodeInto(nuevo, root, 0);
			
		}
		
		//Crear el listener del boton de añadir
		
				btnAñadirHab.addActionListener( new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						
						if( tree.getLastSelectedPathComponent() != null ) {
						String habilidad = JOptionPane.showInputDialog( " Introduce la habilidad que deseas añadir a " + tree.getLastSelectedPathComponent().toString() );
						DefaultMutableTreeNode nodo = new DefaultMutableTreeNode(habilidad);
						treeModel.insertNodeInto(nodo, root ,0); //Lo añade mal por que no tiene que añadirselo al root
						
						}else {
							System.out.println( "Error." );
						}
						
						
					}
					
				} );
		
		
	}
	
	public static void main(String[] args) {
		JFrame vent = new JFrame();
		vent.getContentPane().add(new pnlHabilidad());
		vent.setSize( 750,600 );
		vent.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		
		vent.setVisible(true);
	}
}
