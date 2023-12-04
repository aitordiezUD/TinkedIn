package componentes;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.*;


public class botonAnEl extends JButton {

	private Color hoverBackgroundColor = new Color(60, 60, 60);
	private Color pressedBackgroundColor = new Color(30, 30, 30);

	
	public botonAnEl( String text ) {
		super(text);
		setContentAreaFilled(false);
	    setFocusPainted(false);
	    setBorderPainted(false);
	    setOpaque(true);
	    setBackground(new Color(41, 128, 185));
	    setForeground(Color.WHITE);
	    setFont(new Font("Arial", Font.BOLD, 14));
	}
	
	@Override
    protected void paintComponent(Graphics g) {
        if (getModel().isPressed()) {
            g.setColor(pressedBackgroundColor);
        } else if (getModel().isRollover()) {
            g.setColor(hoverBackgroundColor);
        } else {
            g.setColor(getBackground());
        }
        g.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        g.setColor(getForeground());
        g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 10, 10);
    }
	
}
