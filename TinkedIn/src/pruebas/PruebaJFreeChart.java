package pruebas;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class PruebaJFreeChart extends JFrame {

	public PruebaJFreeChart(String title) {
		// TODO Auto-generated constructor stub
		super(title);
		CategoryDataset dataset = createDataSet();
		
		JFreeChart chart = ChartFactory.createBarChart("Ejemplo",
				"Categorias", "Series", dataset,
				PlotOrientation.VERTICAL,
				true,true,false);
		
		ChartPanel panel = new ChartPanel(chart);
		panel.setPreferredSize(new Dimension(600,600));
		setContentPane(panel);
	}
	
	private CategoryDataset createDataSet() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		
		dataset.addValue(1.0, "Serie 1", "Categoria 1");
		dataset.addValue(4.0, "Serie 1", "Categoria 2");
		dataset.addValue(3.0, "Serie 1", "Categoria 3");
		dataset.addValue(5.0, "Serie 1", "Categoria 4");
		
		return dataset;
		
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(()->{
			PruebaJFreeChart example = new PruebaJFreeChart("Prueba");
			example.setSize(600,600);
			example.setLocationRelativeTo(null);
			example.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			example.setVisible(true);
		});
	}
	
	
}
