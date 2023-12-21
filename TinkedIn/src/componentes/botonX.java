package componentes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JButton;
import javax.swing.JFrame;


public class botonX extends JButton {

    public botonX() {
    	setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
        setOpaque(false);
        setPreferredSize(new Dimension(50, 50));
    }

    @Override
    protected void paintComponent(Graphics g) {
    	
    	super.paintComponent(g);
    	
    	if (getModel().isArmed()) {
            g.setColor(Color.red);
        } else {
            g.setColor(new Color(4, 32, 63));
        }

        
        int width = getWidth();
        int height = getHeight();

        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(20));

        
        g.drawLine(60, 50, 110, 100);
        g.drawLine(60, 100, 110, 50);
    }

    public static void main(String[] args) {
        
        JFrame frame = new JFrame("Botón X");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        
        botonX botonX = new botonX();
        frame.getContentPane().add(botonX);

        frame.setSize(200, 200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
