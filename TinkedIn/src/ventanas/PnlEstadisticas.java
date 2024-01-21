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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.awt.BorderLayout;
import java.awt.CardLayout;
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
	private JFreeChart chartPuestos;
	private ChartPanel pnlHabPers;
	private ChartPanel pnlHabPuestos;
	private JComboBox<String> cbCampos;
	private JPanel botonSelecccionado = null;
	
	public PnlEstadisticas() {
		
		setLayout( new BorderLayout() );
		
		//Persistencia
		servicio = new ServicioPersistencia();
		servicio.init();
		
		//Listas para crear los datasets.
		
		personas = servicio.getPersonas();
		empresas = servicio.getEmpresas();
		puestos = new Vector<PuestoTrabajo>();
		
		for(Empresa e : empresas) {
			for(PuestoTrabajo p : e.getPuestos()) {
				puestos.add(p);
			}
		}
		
		campos = new HashSet<String>();
		
		for(Persona p : personas) {
			for(Habilidad h : p.getCurriculum()) {
				campos.add(h.getCampo());
			}
		}
		
		//CategoryDataSets para construir las tablas:
		CategoryDataset dataSetPersonas = crearDataSetPersonas(personas);
		CategoryDataset datasetPuestos = crearDataSetPuestos(puestos);
		
		//Crear los graficos
		chartPersonas = createBarChart(
				"Habilidades en Personas",
				"Habilidades",
				"Frecuencia",
				dataSetPersonas
				);
		
		chartPuestos = createBarChart(
				"Habilidades en Puestos",
				"Habilidades",
				"Frecuencia",
				datasetPuestos
				);
		

		
		//Panel botonera norte
		
		JPanel pnlBotoneraNort = new JPanel();
		pnlBotoneraNort.setPreferredSize( new Dimension(750,100));
		pnlBotoneraNort.setLayout( new BoxLayout(pnlBotoneraNort, BoxLayout.Y_AXIS) );
		
		//Paneles Boton
		
		JPanel pnlBotonPers = new JPanel();
		pnlBotonPers.setPreferredSize( new Dimension(750,50) );
		pnlBotoneraNort.add(pnlBotonPers);
		JLabel lblTablaPers = new JLabel("Visualizar personas");
		lblTablaPers.setHorizontalAlignment(JLabel.CENTER);
		pnlBotonPers.add(lblTablaPers);
		pnlBotonPers.setBackground(new Color(208, 235, 242));
		
		JPanel pnlBotonPuestos = new JPanel();
		pnlBotonPuestos.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK));
		pnlBotonPuestos.setPreferredSize( new Dimension(750,50) );
		pnlBotoneraNort.add(pnlBotonPuestos);
		JLabel lblTablaPuestos = new JLabel("Visualizar Puestos");
		lblTablaPuestos.setHorizontalAlignment(JLabel.CENTER);
		pnlBotonPuestos.add(lblTablaPuestos);
		pnlBotonPuestos.setBackground(new Color(208, 235, 242));
		
		add(pnlBotoneraNort, BorderLayout.NORTH);
		

		
		//Panel para las tablas
		
		JPanel pnlTablas = new JPanel();
		CardLayout layoutTablas = new CardLayout();
		pnlTablas.setLayout( layoutTablas );
		add(pnlTablas, BorderLayout.CENTER);
		
		//Panel para visualizar habilidades más frecuentes en personas.
		
		pnlHabPers = new ChartPanel(chartPersonas);
		pnlHabPers.setPreferredSize( new Dimension(750,450) );
		pnlHabPers.setBackground( VentanaPrincipal.ColorBase );
		pnlTablas.add( pnlHabPers, "pnlHabPers" );
		
		//Panel para visualizar habilidades más frecuentes en puestos.
		pnlHabPuestos = new ChartPanel(chartPuestos);
		pnlHabPuestos.setPreferredSize( new Dimension(750,450) );
		pnlHabPuestos.setBackground( VentanaPrincipal.ColorBase );
		pnlTablas.add( pnlHabPuestos, "pnlHabPuestos" );
		
		//Panel para el combo box
		JPanel pnlCbCampos = new JPanel();
		pnlCbCampos.setLayout( new BorderLayout() );
		pnlCbCampos.setPreferredSize( new Dimension(750,100));
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
		        if (botonSelecccionado != null) {
		            if (botonSelecccionado.equals(pnlBotonPers)) {
		                if (campoSeleccionado.equals("-- General --")) {
		                    actualizarGrafico(crearDataSetPersonas(personas));
		                } else {
		                    actualizarGrafico(crearDataSetCampos(personas, campoSeleccionado));
		                }
		            } else if (botonSelecccionado.equals(pnlBotonPuestos)) {
		                if (campoSeleccionado.equals("-- General --")) {
		                    actualizarGrafico(crearDataSetPuestos(puestos));
		                } else {
		                    actualizarGrafico(crearDataSetPuestosCampos(puestos, campoSeleccionado));
		                }
		            }
		        }
			}
		});
		
		//Listener de los paneles boton
		
		pnlBotonPers.addMouseListener((MouseListener) new MouseAdapter() {
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				if(botonSelecccionado == null) {
					
				}else {
					pnlBotonPers.setBackground(new Color(208, 235, 242));
				}
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				if(botonSelecccionado == null) {
					
				}else {
					pnlBotonPers.setBackground(new Color(122, 199, 218));
				}
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
		        if (botonSelecccionado != pnlBotonPers) {
		            if (botonSelecccionado != null) {
		                botonSelecccionado.setBackground(new Color(208, 235, 242));
		            }
		            botonSelecccionado = pnlBotonPers;
		            layoutTablas.show(pnlTablas, "pnlHabPers");
		            pnlBotonPers.setBackground(new Color(122, 199, 218));
		        }
			}
		});
		
		pnlBotonPuestos.addMouseListener( (MouseListener) new MouseAdapter() {
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				if(botonSelecccionado == null) {
					
				}else {
					pnlBotonPuestos.setBackground(new Color(208, 235, 242));
				}
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				if(botonSelecccionado == null) {
					
				}else {
					pnlBotonPuestos.setBackground(new Color(122, 199, 218));
				}
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
		        if (botonSelecccionado != pnlBotonPuestos) {
		            if (botonSelecccionado != null) {
		                botonSelecccionado.setBackground(new Color(208, 235, 242));
		            }
		            botonSelecccionado = pnlBotonPuestos;
		            layoutTablas.show(pnlTablas, "pnlHabPuestos");
		            pnlBotonPuestos.setBackground(new Color(122, 199, 218));
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
	
	private CategoryDataset crearDataSetPuestos(Vector<PuestoTrabajo> puestos) {
		DefaultCategoryDataset datasetPuestos = new DefaultCategoryDataset();
		
		//Mapa de frecuencias por campos
		Map<String, Integer> frecuenciaCamposPuesto = new HashMap<String, Integer>();
		for(PuestoTrabajo p : puestos) {
			for(Habilidad h : p.getHabilidadesReq()) {
				frecuenciaCamposPuesto.put(h.getCampo(), frecuenciaCamposPuesto.getOrDefault(h.getCampo(), 0) + 1);
			}
		}
		
		
		//Añadir los datos al dataset.
		for(Map.Entry<String, Integer> entry: frecuenciaCamposPuesto.entrySet()) {
			datasetPuestos.addValue( entry.getValue(), "Campos" , entry.getKey() );
		}
		
		return datasetPuestos;
	}
	/**
	 * @param puestos Vector de todos los puestos de trabajo de todas las empresas
	 * @return Devuelve un dataset con la frecuencia en la que las habilidades
	 * aparecen en los puestos de trabajo
	 */
	private CategoryDataset crearDataSetPuestosCampos(Vector<PuestoTrabajo> puestos, String campo) {
		DefaultCategoryDataset datasetPuestos = new DefaultCategoryDataset();
		
	    // Mapa de frecuencia de habilidades por campo
	    Map<String, Integer> habilidadesPorPuestos = new HashMap<>();
	    
	    habilidadesPorPuestos = servicio.getFreHabPuestos(campo);


	    // Ordenar el mapa por frecuencia en orden descendente
	    for(Map.Entry<String, Integer> entry : habilidadesPorPuestos.entrySet()) {
	    	datasetPuestos.addValue(entry.getValue(), "Habilidades", entry.getKey());
	    }
		
		
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
