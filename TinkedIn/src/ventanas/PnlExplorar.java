package ventanas;

import javax.swing.JPanel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.Icon;
import javax.swing.UIManager;

import clases.Empresa;
import clases.Usuario;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.DropMode;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class PnlExplorar extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public PnlExplorar( Usuario tipoUsuario) {
		setBackground(new Color(129, 186, 207));
		setLayout(null);
		
		try {
	        // Carga la imagen original desde el archivo en el paquete "imagenes"
	        InputStream imageStream = PnlBotonera.class.getResourceAsStream("fotoPerfilEjemplo.JPG");
	        BufferedImage originalImage = ImageIO.read(imageStream);

	        // Redimensiona la imagen a un tamaño más pequeño (50x50 pixeles)
	        int width = 100;
	        int height = 100;
	        Image scaledImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);

	        // Convierte la imagen escalada en un BufferedImage
	        BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	        Graphics2D g2d = resizedImage.createGraphics();
	        Shape circle = new Ellipse2D.Float(0, 0, width, height);
	        g2d.setClip(circle);
	        g2d.drawImage(scaledImage, 0, 0, null);
	        g2d.dispose();
	        
	        JPanel pnlDatos = new JPanel();
	        pnlDatos.setBounds(442, 46, 297, 143);
	        add(pnlDatos);
	        pnlDatos.setLayout(null);
	        
			//Crea in JLabel y asigna la imagen
	        JLabel lblFotoUsuario = new JLabel(new ImageIcon(resizedImage));
	        lblFotoUsuario.setBounds(93, 0, 100, 100);
	        pnlDatos.add(lblFotoUsuario);
	        lblFotoUsuario.setBackground(new Color(240, 240, 240));
	        
	        JLabel lblNombreUsuario = new JLabel("Nombre Usuario");
	        lblNombreUsuario.setBounds(82, 96, 142, 36);
	        pnlDatos.add(lblNombreUsuario);
	        lblNombreUsuario.setFont(new Font("Tahoma", Font.PLAIN, 18));
	        lblNombreUsuario.setHorizontalAlignment(SwingConstants.CENTER);
	        
	       
	        
	        
	        if(tipoUsuario instanceof Empresa) {
	        	
	        JPanel pnlLista = new JPanel();
	 	    pnlLista.setBounds(10, 11, 174, 630);
	 	    pnlLista.setBackground( new Color( 129, 186, 207 ) );
	 	    add(pnlLista);
	 	    pnlLista.setLayout(null);
	 	        
	        DefaultListModel<String> modeloLista = new DefaultListModel<String>();
			JList<String> listaPuestos = new JList<String>();
			
			listaPuestos.setModel(modeloLista);
			JScrollPane spLista = new JScrollPane(listaPuestos);
	 	    spLista.setBounds(10, 5, 154, 614);
	 	    pnlLista.add(spLista);	

			
			for (int i = 0; i < 20; i++) {
				modeloLista.add(i, "Opcion " + i);
			}
			
			listaPuestos.setCellRenderer(new DefaultListCellRenderer() {
				private static final long serialVersionUID = 1L;
				
				JPanel pnl;
				JLabel lbl;
				
				@Override
				public Component getListCellRendererComponent(JList<?> list, Object value, int index,
						boolean isSelected, boolean cellHasFocus) {
					// TODO Auto-generated method stub
					
					pnl = new JPanel();
					pnl.setSize(200,50);
					if (isSelected) {
	                    pnl.setBackground(new Color(122, 199, 218));
	                } else {
	                    pnl.setBackground(new Color(202, 232, 232));
	                    pnl.setForeground(list.getForeground());
	                }
					lbl = new JLabel(value.toString());
					pnl.add(lbl);
					return pnl;
				}
				
				
			});
			
			listaPuestos.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseExited(MouseEvent e) {
	                listaPuestos.clearSelection();
	            }
	        });

			listaPuestos.addMouseMotionListener(new MouseMotionAdapter() {
	            @Override
	            public void mouseMoved(MouseEvent e) {
	                int index = listaPuestos.locationToIndex(e.getPoint());
	                if (index != -1) {
	                    listaPuestos.setSelectedIndex(index);
	                }
	            }
	        });
			
			
	       }
	        	
	       
	        
	        
			
			
	  
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
		
	}
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(750, 650);
		frame.getContentPane().add(new PnlExplorar( new Empresa(new ImageIcon(PnlExplorar.class.getResource("fotoPerfilEjemplo.jpg")), "nada") ));
		frame.setVisible(true);
		
	}
}
