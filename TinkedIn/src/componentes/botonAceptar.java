package componentes;

import javax.swing.*;
import java.awt.*;

public class botonAceptar extends JButton {

	 private Color normalColor = new Color(46, 204, 113);
	 private Color hoverColor = new Color(39, 174, 96);
	 private Color pressedColor = new Color(33, 150, 83);
	
	public botonAceptar( String text ) {
		super(text);
        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setOpaque(false);
        setForeground(Color.WHITE);
        setFont(new Font("Arial", Font.PLAIN, 14));

	}
	 @Override
	    protected void paintComponent(Graphics g) {
	        Graphics2D g2d = (Graphics2D) g.create();
	        int width = getWidth();
	        int height = getHeight();

	        if (getModel().isPressed()) {
	            g2d.setColor(pressedColor);
	        } else if (getModel().isRollover()) {
	            g2d.setColor(hoverColor);
	        } else {
	            g2d.setColor(normalColor);
	        }

	        g2d.fillRoundRect(0, 0, width, height, 10, 10);

	        g2d.setColor(Color.WHITE);
	        g2d.setFont(getFont());
	        FontMetrics fontMetrics = g2d.getFontMetrics();
	        int textX = (width - fontMetrics.stringWidth(getText())) / 2;
	        int textY = (height - fontMetrics.getHeight()) / 2 + fontMetrics.getAscent();

	        g2d.drawString(getText(), textX, textY);

	        g2d.dispose();
	    }
}
