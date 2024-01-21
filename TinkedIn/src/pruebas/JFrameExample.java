package pruebas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class JFrameExample extends JFrame {

	JPanel blackPanel;
	JPanel blackPanel2;
    public JFrameExample() {
        // Configurar el JFrame
        setTitle("Ejemplo JFrame");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel pnlReferencia = new JPanel(new BorderLayout());
        
        // Crear un JPanel negro para el fondo
        blackPanel = new JPanel();
        blackPanel.setBackground(Color.BLACK);
        blackPanel.setLayout(new BorderLayout());
        int y = (int) ((this.getSize().getHeight()-100)/2);
        blackPanel.setPreferredSize(new Dimension(100,y));
        System.out.println(this.getSize().getHeight());
        
        // Crear un JPanel amarillo en el medio vertical
        JPanel yellowPanel = new JPanel();
        yellowPanel.setBackground(Color.YELLOW);
        yellowPanel.setPreferredSize(new Dimension(400, 100));
        yellowPanel.setMaximumSize(new Dimension(400,100));

        // Añadir el panel amarillo al panel negro en el centro

        pnlReferencia.add(blackPanel, BorderLayout.NORTH);
        pnlReferencia.add(yellowPanel);
        blackPanel2 = new JPanel();
        blackPanel2.setBackground(Color.BLACK);
        blackPanel2.setLayout(new BorderLayout());
        blackPanel2.setPreferredSize(new Dimension(100,y));
        pnlReferencia.add(blackPanel2, BorderLayout.SOUTH);
        // Añadir el panel negro al JFrame
        add(pnlReferencia);
        
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                updatePanelSize();
            }
        });
        // Centrar el JFrame en la pantalla
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrameExample example = new JFrameExample();
            example.setVisible(true);
        });
    }
    
    private void updatePanelSize() {
        // Actualizar la preferredSize del panel amarillo al ancho completo del JFrame y 100 píxeles de alto
        blackPanel.setPreferredSize(new Dimension(100, (getHeight()-100)/2));
        blackPanel2.setPreferredSize(new Dimension(100, (getHeight()-100)/2));

        // Repintar el panel para aplicar los cambios
        blackPanel.revalidate();
        blackPanel.repaint();
        blackPanel2.revalidate();
        blackPanel2.repaint();
    }
}
