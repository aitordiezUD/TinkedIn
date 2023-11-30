package ventanas;

import javax.swing.JPanel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.UIManager;
import javax.swing.border.StrokeBorder;

import clases.Empresa;
import clases.Habilidad;
import clases.PuestoTrabajo;
import clases.Usuario;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.DropMode;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;

public class PnlExplorar extends JPanel {

	protected static DefaultListModel modeloListaPt;
	
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public PnlExplorar( Usuario tipoUsuario ) {
		setLayout(new BorderLayout());
		setBackground(Color.WHITE);
		
		
	        
	       
	        JPanel pnlLista = new JPanel();
	        setLayout(new BorderLayout(0,0));
	        
	        
	        JPanel pnlContenido = new JPanel();
	        pnlContenido.setLayout( new BorderLayout());
	        pnlContenido.setBackground(Color.RED);
	        pnlContenido.setPreferredSize(new Dimension(getWidth()-250, getHeight()));
	        add(pnlContenido, BorderLayout.CENTER);
	        
	        JPanel pnlInfo = new JPanel();
	        pnlInfo.setLayout( new BorderLayout() );
	        pnlInfo.setBackground( Color.WHITE);
	        pnlContenido.add(pnlInfo);
	       
	        JPanel pnlDatos = new JPanel();
//	        pnlDatos.setBackground(Color.GREEN);
	        pnlDatos.setPreferredSize(new Dimension(getWidth()-250, 125));
	        pnlContenido.add( pnlDatos, BorderLayout.NORTH);
	        
	        
	        JPanel pnlBotonera = new JPanel();
	        pnlBotonera.setLayout( new GridLayout(0,3) );
//	        pnlBotonera.setBackground( Color.BLUE);
	        pnlBotonera.setPreferredSize( new Dimension(getWidth()-250, 120 ));
	        pnlContenido.add(pnlBotonera, BorderLayout.SOUTH);
//	        System.out.println( tipoUsuario.getClass() );

	        JPanel pnlLike = new JPanel();
	        pnlLike.setLayout(new BorderLayout());
	        pnlBotonera.add(pnlLike);

	        
	        JPanel pnlVacio = new JPanel();
	        pnlVacio.setPreferredSize( new Dimension(10,10));
	        pnlBotonera.add(pnlVacio);
	        
	        JPanel pnlPass = new JPanel();
	        pnlLike.setLayout(new BorderLayout());
	        pnlBotonera.add(pnlPass);
	        pnlPass.setLayout(new BorderLayout(0, 0));
	        
	        
	        
	        
	        try {
	            // Carga la imagen original desde el archivo en el paquete "imagenes"
	            InputStream imageStream = PnlBotonera.class.getResourceAsStream("fotoPerfilEjemplo.jpg");
	            BufferedImage originalImage = ImageIO.read(imageStream);

	            // Redimensiona la imagen a un tamaño más pequeño (50x50 pixeles)
	            int width = 70;
	            int height = 70;
	            Image scaledImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);

	            // Convierte la imagen escalada en un BufferedImage
	            BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	            Graphics2D g2d = resizedImage.createGraphics();
	            Shape circle = new Ellipse2D.Float(0, 0, width, height);
	            g2d.setClip(circle);
	            g2d.drawImage(scaledImage, 0, 0, null);
	            g2d.dispose();
	            
	            // Crea un JLabel y asigna la imagen escalada como ícono
	            JLabel lblGrafExp = new JLabel(new ImageIcon(resizedImage));
	            lblGrafExp.setBackground(new Color(240, 240, 240));
	    		lblGrafExp.setBounds(20, 0, 38, 38);
	    		pnlDatos.add(lblGrafExp);
	      
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        
	        try {
	            // Carga la imagen original desde el archivo en el paquete "imagenes"
	            InputStream imageStream = PnlBotonera.class.getResourceAsStream("cruzar.png");
	            BufferedImage originalImage = ImageIO.read(imageStream);

	            // Redimensiona la imagen a un tamaño más pequeño (50x50 pixeles)
	            int width = 40;
	            int height =  40;
	            Image scaledImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);

	            // Convierte la imagen escalada en un BufferedImage
	            BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	            Graphics2D g2d = resizedImage.createGraphics();
	            g2d.drawImage(scaledImage, 0, 0, null);
	            g2d.dispose();
	            
	            // Crea un JLabel y asigna la imagen escalada como ícono
	            JLabel lblGrafExp = new JLabel(new ImageIcon(resizedImage));
	            lblGrafExp.setBackground(new Color(240, 240, 240));
	    		lblGrafExp.setBounds(20, 0, 38, 38);
	    		pnlPass.add(lblGrafExp, BorderLayout.WEST);
	      
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        
	        try {
	            // Carga la imagen original desde el archivo en el paquete "imagenes"
	            InputStream imageStream = PnlBotonera.class.getResourceAsStream("corazon.png");
	            BufferedImage originalImage = ImageIO.read(imageStream);

	            // Redimensiona la imagen a un tamaño más pequeño (50x50 pixeles)
	            int width = 40;
	            int height =  40;
	            Image scaledImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);

	            // Convierte la imagen escalada en un BufferedImage
	            BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	            Graphics2D g2d = resizedImage.createGraphics();
	            g2d.drawImage(scaledImage, 0, 0, null);
	            g2d.dispose();
	            
	            // Crea un JLabel y asigna la imagen escalada como ícono
	            JLabel lblGrafExp = new JLabel(new ImageIcon(resizedImage));
	            lblGrafExp.setBackground(new Color(240, 240, 240));
	    		lblGrafExp.setBounds(20, 0, 38, 38);
	    		pnlLike.add(lblGrafExp, BorderLayout.EAST);
	      
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        
	        JLabel lblNombreUsu = new JLabel( "      Nombre de Usuario" );
	        lblNombreUsu.setFont(new Font("Segoe UI Black", Font.BOLD, 18));
	        pnlDatos.add(lblNombreUsu);
	        
	        
	        if( tipoUsuario instanceof Empresa ) {
	        	
	        	ArrayList<PuestoTrabajo> puestos = new ArrayList<>();
	        	for (int i = 0; i<5; i++) {
	        		PuestoTrabajo pt = new PuestoTrabajo("Puesto "+ i,"Este es el puesto " + i, new ArrayList<>());
	        		puestos.add(pt);
	        	}
	        	
	        	Empresa e = (Empresa) tipoUsuario;	
	        	e.setPuestos(puestos);
	        	
	        	pnlLista.setBackground( new Color( 129, 186, 207 ) );
	 	    
	        	pnlLista.setLayout(new BorderLayout());
	 	        
	        	modeloListaPt = new DefaultListModel<PuestoTrabajo>();
	        	
	        	for ( PuestoTrabajo p : e.getPuestos()) {
	        		modeloListaPt.addElement(p);
	        	}
	        	JList<String> listaPuestos = new JList<String>();
			
			listaPuestos.setModel(modeloListaPt);
			listaPuestos.setBackground(new Color(202, 232, 232));
			JScrollPane spLista = new JScrollPane(listaPuestos);
	 	    spLista.setPreferredSize(new Dimension(250,getHeight()));
	 	    spLista.setMaximumSize( new Dimension(250, getHeight()) );
	 	    spLista.setBackground(getBackground());
	 	    pnlLista.add(spLista);
	 	    
	 	   

			
			
			
			listaPuestos.setCellRenderer(new DefaultListCellRenderer() {
				private static final long serialVersionUID = 1L;
				
				JPanel pnl;
				JLabel lbl;
				
				@Override
				public Component getListCellRendererComponent(JList<?> list, Object value, int index,
						boolean isSelected, boolean cellHasFocus) {
					// TODO Auto-generated method stub
					
					pnl = new JPanel();
					pnl.setPreferredSize(new Dimension(pnlLista.getWidth()-5,70));
					pnl.setSize(200,50);
					pnl.setBorder(BorderFactory.createMatteBorder(0,1,1,1,Color.BLACK));
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
			
			add(pnlLista,BorderLayout.WEST);
	       }
	       
	     
		
	}
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(750, 650);
		frame.getContentPane().add(new PnlExplorar( new Empresa("nada", "9292932","nada","nada", new ArrayList<>(), new ArrayList<>(), (new ImageIcon(PnlExplorar.class.getResource("fotoPerfilEjemplo.jpg"))), "nada" )));
		frame.setVisible(true);
		
	}
}
