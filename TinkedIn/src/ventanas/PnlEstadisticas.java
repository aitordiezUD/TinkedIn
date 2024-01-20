package ventanas;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import clases.Habilidad;
import clases.PuestoTrabajo;
import datos.DatosFicheros;
import servidor.ServicioPersistencia;
import usuarios.Empresa;
import usuarios.Persona;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;


public class PnlEstadisticas extends JPanel {

	//Datos para la construccion de tablas
	
	private Vector<Persona> personas;
	private Vector<Empresa> empresas;
	private Vector<PuestoTrabajo> puestos;
	private HashSet<String> campos; 
	private ServicioPersistencia servicio;
	
	
	//Componentes
	private JFreeChart chartPersonas;
	private ChartPanel pnlHabPers;
	private JComboBox<String> cbCampos;
	
	
	public PnlEstadisticas() {
		
		setLayout( new BorderLayout() );
		
		//Persistencia
		servicio = new ServicioPersistencia();
		servicio.init();
		
		//Listas para crear los datasets.
		
		personas = servicio.getPersonas();
		empresas = servicio.getEmpresas();
		puestos = new Vector<PuestoTrabajo>();
		campos = new HashSet<String>();
		for(Persona p : personas) {
			for(Habilidad h : p.getCurriculum()) {
				campos.add(h.getCampo());
			}
		}
		
		//CategoryDataSets para construir las tablas:
		CategoryDataset dataSetPersonas = crearDataSetPersonas(personas);
		
		//Crear los graficos
		chartPersonas = createBarChart(
				"Habilidades en Personas",
				"Habilidades",
				"Frecuencia",
				dataSetPersonas
				);
		
		for(Empresa e : empresas) {
			for(PuestoTrabajo p : e.getPuestos()) {
				puestos.add(p);
			}
		}
		
		//Panel para visualizar habilidades más frecuentes en personas.
		
		pnlHabPers = new ChartPanel(chartPersonas);
		pnlHabPers.setPreferredSize( new Dimension(750,550) );
		pnlHabPers.setBackground( VentanaPrincipal.ColorBase );
		add( pnlHabPers, BorderLayout.CENTER );
		
		//Panel para el combo box
		JPanel pnlCbCampos = new JPanel();
		pnlCbCampos.setLayout( new BorderLayout() );
		pnlCbCampos.setPreferredSize( new Dimension(750,50));
		pnlCbCampos.setBackground( VentanaPrincipal.ColorBase );
		
		//Combo box
		JLabel lblTit = new JLabel("Selecciona el campo sobre el que deseas conocer:");
		lblTit.setFont( new Font("Arial", 2, 14) );
		lblTit.setHorizontalAlignment(JLabel.CENTER);
		pnlCbCampos.add(lblTit, BorderLayout.NORTH);
		
		//Crear combo box
		cbCampos = new JComboBox<String>();
		cbCampos.setRenderer( new CbRendererCentrado() );
		cbCampos.addItem("-- General --");
		for(String str: campos) {
			cbCampos.addItem(str);
		}
		pnlCbCampos.add(cbCampos, BorderLayout.CENTER);
		add(pnlCbCampos,BorderLayout.SOUTH);

		//Listener del combobox
		
		cbCampos.addActionListener( (ActionListener) new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String campoSeleccionado = (String) cbCampos.getSelectedItem();
				if(campoSeleccionado.equals("-- General --")) {
					actualizarGrafico(crearDataSetPersonas(personas));
				}else {
					actualizarGrafico(crearDataSetCampos(personas, campoSeleccionado));
					
				}
				
			}
		});
		
		
	}
	
 

	/**
	 * @param Vector de todas los usuarios de tipo persona de la aplicación.
	 * @return Devuelve un dataset con la frecuencia en la que los campos
	 * aparecen en cada persona.
	 */
	private CategoryDataset crearDataSetPersonas(Vector<Persona> personas) {
		DefaultCategoryDataset datasetPersonas = new DefaultCategoryDataset();
		
		//Mapa de frecuencias por campos
		Map<String, Integer> frecuenciaCamposPersona = new HashMap<String, Integer>();
		for(Persona p : personas) {
			for(Habilidad h : p.getCurriculum()) {
				frecuenciaCamposPersona.put(h.getCampo(), frecuenciaCamposPersona.getOrDefault(h.getCampo(), 0)+1 );
			}
		}
		//Añadir los datos al dataset.
		for(Map.Entry<String, Integer> entry: frecuenciaCamposPersona.entrySet()) {
			datasetPersonas.addValue( entry.getValue(), "Campos" , entry.getKey() );
		}
		
		return datasetPersonas;
	}
	
	
	/**
	 * @param puestos Vector de todos los puestos de trabajo de todas las empresas
	 * @return Devuelve un dataset con la frecuencia en la que las habilidades
	 * aparecen en los puestos de trabajo
	 */
	private CategoryDataset crearDataSetPuestos(Vector<PuestoTrabajo> puestos) {
		DefaultCategoryDataset datasetPuestos = new DefaultCategoryDataset();
		
		return datasetPuestos;
	}
	
	/**
	 * @param personas Vector de todas los usuarios de tipo persona de la aplicación.
	 * @param campo Campo seleccionado del que se quiere saber la frecuencia de las habilidades
	 * @return Devuelve el dataset con la informacion de la frecuencia de habilidades en un campo.
	 */
	private CategoryDataset crearDataSetCampos(Vector<Persona> personas, String campo) {
	    DefaultCategoryDataset datasetCampos = new DefaultCategoryDataset();
	    
	    // Mapa de frecuencia de habilidades por campo
	    Map<String, Integer> habilidadesPorCampo = new HashMap<>();
	    
	    habilidadesPorCampo = servicio.getFreHab(campo);

	    // Ordenar el mapa por frecuencia en orden descendente
	    habilidadesPorCampo.entrySet()
	        .stream()
	        .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
	        .forEach(entry -> datasetCampos.addValue(entry.getValue(), "Habilidades", entry.getKey()));

	    return datasetCampos;
	}
	
	/**
	 * @param title Titulo del gráfico
	 * @param TitEjeX Título del eje de X
	 * @param TitEjeY Título del eje de Y
	 * @param dataset Datos para crear la tabla
	 * @return La tabla con la frecuencia de los campos de las habilidades
	 */
	private JFreeChart createBarChart(String title, String TitEjeX, String TitEjeY, CategoryDataset dataset) {
		JFreeChart chart = ChartFactory.createBarChart(title, TitEjeX, TitEjeY, dataset, PlotOrientation.VERTICAL,
				true, true, false);
		
		// Obtener el plot y la categoría del eje X
	    CategoryPlot plot = (CategoryPlot) chart.getPlot();
	    CategoryAxis xAxis = plot.getDomainAxis();

	    // Rotar las etiquetas del eje X
	    xAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_90);

		
		return chart;
	}
	
	/**
	 * @param dataset El dataset sobre el cual se quiere construir (en este caso actualizar)
	 * la tabla
	 */
	private void actualizarGrafico( CategoryDataset dataset ) {
		chartPersonas.getCategoryPlot().setDataset(dataset);
	}
		
	public static void main(String[] args) {
		
		JFrame vent = new JFrame();
		vent.setSize(750,650);
		vent.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		vent.setLocationRelativeTo(null);
		vent.setContentPane( new PnlEstadisticas() );
		vent.setVisible(true);
	}
	
	/**
	 * Renderer para mostrar los elementos del combobox centrados
	 */
	private static class CbRendererCentrado extends DefaultListCellRenderer{

		@Override
		public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {
			// TODO Auto-generated method stub
			JLabel lbl = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
			lbl.setHorizontalAlignment(JLabel.CENTER);
			return lbl;
		}
		
	}
	
}
