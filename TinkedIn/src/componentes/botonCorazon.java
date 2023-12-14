package componentes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class botonCorazon extends JButton {
	public botonCorazon() {
		setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
        setOpaque(false);
        setPreferredSize(new Dimension(50, 50));
	}

	@Override
	protected void paintComponent(Graphics g) {
		if (getModel().isArmed()) {
            g.setColor(Color.red);
        } else {
            g.setColor(new Color(4, 32, 63));
        }
		
		Graphics2D g2d = (Graphics2D) g;
        g2d.fill(getHeartShape());;
        
		super.paintComponent(g);
	}
	
	private GeneralPath getHeartShape() {
        GeneralPath heart = new GeneralPath();

        int x = getWidth() / 2;
        int y = getHeight() / 2;
        

        heart.moveTo(328 , 256);
        heart.curveTo(329, 204, 397, 199, 401, 252);
        heart.curveTo(402 , 204, 470, 199, 474, 252);
        heart.curveTo(473, 304, 420, 345, 402, 350);
        heart.curveTo(388, 346, 328, 308, 328, 256);

        return heart;
    }
	
//	public static void main(String[] args) {
//		JFrame v = new JFrame();
//		v.setSize(950, 850);
//		v.setVisible(true);
//		v.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//		JPanel p = new JPanel(new BorderLayout());
//		botonCorazon c = new botonCorazon();
//		p.add(c, BorderLayout.CENTER);
//		v.add(p);
//	}
	
}
