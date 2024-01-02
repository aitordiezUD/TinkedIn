package componentes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class botonLike extends JButton {

	public botonLike() {
		// TODO Auto-generated constructor stub
		setContentAreaFilled(false);  // Hace que el fondo del botón sea transparente
        setPreferredSize(new Dimension(50, 50));  // Ajusta el tamaño del botón según sea necesario
	}
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        int width = getWidth();
        int height = getHeight();

        // Dibuja un corazón
        GeneralPath path = new GeneralPath();
        path.moveTo(width / 2, height / 5);
        path.curveTo(width / 2, 0, width, 0, width / 2, height);
        path.curveTo(0, 0, width / 2, 0, width / 2, height / 5);

        g2d.setColor(Color.RED); 
        g2d.fill(path);

        g2d.dispose();
    }

	 public static void main(String[] args) {
	        SwingUtilities.invokeLater(() -> {
	            JFrame frame = new JFrame("Botón con forma de corazón");
	            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	            botonLike botonCorazon = new botonLike();
	            frame.getContentPane().add(botonCorazon);

	            frame.setSize(750, 650);
	            frame.setLocationRelativeTo(null);
	            frame.setVisible(true);
	        });
	    }
}
