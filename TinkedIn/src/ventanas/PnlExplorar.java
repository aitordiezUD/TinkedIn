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
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Vector;

import javax.swing.Icon;
import javax.swing.UIManager;
import javax.swing.border.StrokeBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import clases.Habilidad;
import clases.PuestoTrabajo;
import componentes.botonAnEl;
import datos.DatosFicheros;
import usuarios.Empresa;
import usuarios.Persona;
import usuarios.Usuario;
import componentes.botonCorazon;
import componentes.botonX;

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
	
	private JLabel lblGrafExpCor; 
	private JLabel lblGrafExpX; 
	private JPanel pnlLike;
	private JPanel pnlPass;
	protected static HashMap<PuestoTrabajo, Vector<Persona>> mapaPersonasPorPuesto;
	protected static TreeSet<PuestoTrabajo> puestosCandidatos;
	
	public PnlExplorar( Usuario usuarioAutenticado ) {
		setLayout(new BorderLayout());
		setBackground(Color.WHITE);
		
		setLayout(new BorderLayout(0,0));
	     
	    	if(usuarioAutenticado instanceof Persona) {
	    		puestosCandidatos = PnlBotonera.puestosCandidatos;
	    		System.out.println(puestosCandidatos);
	    		
	    	}
    		
	        //PuestoTrabajo primerPuesto = puestosCandidatos.first();
	        
	        
	        JPanel pnlContenido = new JPanel();
	        pnlContenido.setLayout( new BorderLayout());
	        pnlContenido.setBackground(Color.WHITE);
	        pnlContenido.setPreferredSize(new Dimension(getWidth()-250, getHeight()));
	        add(pnlContenido, BorderLayout.CENTER);
      
	        JPanel pnlInfo = new JPanel();
	        pnlInfo.setLayout( new BorderLayout() );
	        //pnlInfo.setBackground( Color.GREEN);
	        pnlContenido.add(pnlInfo, BorderLayout.CENTER);
	       
	        JPanel pnlDatos = new JPanel();
//	        pnlDatos.setBackground(Color.GREEN);
	        pnlDatos.setPreferredSize(new Dimension(getWidth()-250, 125));
	        pnlContenido.add( pnlDatos, BorderLayout.NORTH);
	        
	        JPanel pnlfotoPerfil = new JPanel();
	        pnlfotoPerfil.setPreferredSize( new Dimension( getWidth()/2, 100 ) );
	        pnlfotoPerfil.setBackground( Color.CYAN );
	        pnlInfo.add(pnlfotoPerfil, BorderLayout.WEST);
	        JLabel lblGraficoFoto = new JLabel( "Aqui va la foto" );
	        pnlfotoPerfil.add(lblGraficoFoto);
	        
	        
	        
	        JPanel pnlBotonera = new JPanel();
	        pnlBotonera.setLayout( new GridLayout(0,3) );
//	        pnlBotonera.setBackground( Color.BLUE);
	        pnlBotonera.setPreferredSize( new Dimension(getWidth()-250, 120 ));
	        pnlContenido.add(pnlBotonera, BorderLayout.SOUTH);
//	        System.out.println( tipoUsuario.getClass() );

	        pnlLike = new JPanel();
	        pnlLike.setLayout(new BorderLayout());
	        botonCorazon btnCorazon = new botonCorazon();
	        pnlLike.add(btnCorazon, BorderLayout.EAST);
	        pnlBotonera.add(pnlLike);
	        
	        

	        
	        JPanel pnlVacio = new JPanel();
	        pnlVacio.setPreferredSize( new Dimension(10,10));
	        pnlBotonera.add(pnlVacio);
	        
	        pnlPass = new JPanel();
	        pnlPass.setLayout(new BorderLayout());
	        botonX btnX = new botonX();
	        pnlPass.add(btnX, BorderLayout.WEST);
	        pnlBotonera.add(pnlPass);
	        
	        
	        
	        
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
	            
	            
	            
	            
	            JLabel lblNombreUsu = new JLabel( "      Nombre de Usuario" );
	            lblNombreUsu.setFont(new Font("Segoe UI Black", Font.BOLD, 18));
	            pnlDatos.add(lblNombreUsu);
	            
	            // Crea un JLabel y asigna la imagen escalada como ícono
	            JLabel lblGrafExp = new JLabel(new ImageIcon(resizedImage));
	            lblGrafExp.setBackground(new Color(240, 240, 240));
	    		lblGrafExp.setBounds(20, 0, 38, 38);
	    		pnlDatos.add(lblGrafExp);
	      
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        
	        //botonCorazon btnCorazon = new botonCorazon();
	        //pnlLike.add(btnCorazon, BorderLayout.EAST);
	       
	        
	        if( usuarioAutenticado instanceof Empresa ) {
	        	
	        	JPanel pnlLista = new JPanel();
		        pnlLista.setLayout( new BorderLayout() );
		       
	        	ArrayList<PuestoTrabajo> puestos = new ArrayList<>();
	        	for (int i = 0; i<5; i++) {
	        		PuestoTrabajo pt = new PuestoTrabajo("Puesto "+ i,"Este es el puesto " + i, new ArrayList<>(), (Empresa) usuarioAutenticado);
	        		puestos.add(pt);
	        	}
	        	
	        	Empresa e = (Empresa) usuarioAutenticado;	
	        	e.setPuestos(puestos);
	        	
	        	pnlLista.setBackground( new Color( 129, 186, 207 ) );
	 	    
	        	pnlLista.setLayout(new BorderLayout());
	 	        
	        	modeloListaPt = new DefaultListModel<PuestoTrabajo>();
	        	
	        	for ( PuestoTrabajo p : e.getPuestos()) {
	        		modeloListaPt.addElement(p);
	        	}
	        	JList<PuestoTrabajo> listaPuestos = new JList<PuestoTrabajo>();
			
				listaPuestos.setModel(modeloListaPt);
				listaPuestos.setBackground(new Color(202, 232, 232));
				JScrollPane spLista = new JScrollPane(listaPuestos);
		 	    spLista.setPreferredSize(new Dimension(250,getHeight()));
		 	    spLista.setMaximumSize( new Dimension(250, getHeight()) );
		 	    spLista.setBackground(getBackground());
		 	    pnlLista.add(spLista);
		 	    
		 	   add(pnlLista,BorderLayout.WEST);
		 	   JPanel pnlBotoneraIzq = new JPanel();
		 	   pnlBotoneraIzq.setBorder( BorderFactory.createBevelBorder(0));
		 	   pnlBotoneraIzq.setLayout( new FlowLayout() );
		       pnlLista.add(pnlBotoneraIzq, BorderLayout.SOUTH);
		       pnlBotoneraIzq.setPreferredSize( new Dimension( pnlLista.getWidth(), 75));
		       pnlBotoneraIzq.add( new botonAnEl("Añadir") );
		       pnlBotoneraIzq.add( new botonAnEl("Eliminar") );
		       
	
				
				
				
				listaPuestos.setCellRenderer(new DefaultListCellRenderer() {
					private static final long serialVersionUID = 1L;
					
					JPanel pnl;
					JLabel lbl1;
					JLabel lbl2;
					
					@Override
					public Component getListCellRendererComponent(JList<?> list, Object value, int index,
							boolean isSelected, boolean cellHasFocus) {
						// TODO Auto-generated method stub
						
						pnl = new JPanel();
						pnl.setPreferredSize(new Dimension(pnlLista.getWidth()-5,70));
						pnl.setSize(200,50);
						pnl.setBorder(BorderFactory.createMatteBorder(0,1,1,1,new Color(0, 64, 128)));
						if (isSelected) {
		                    pnl.setBackground(new Color(122, 199, 218));
		                } else {
		                    pnl.setBackground(new Color(202, 232, 232));
		                    pnl.setForeground(list.getForeground());
		                }
						lbl1 = new JLabel(value.toString());
						lbl2 = new JLabel("Numero de plazas");
						lbl2.setForeground(new Color(128, 128, 128));
						pnl.add(lbl1);
						pnl.add(lbl2);
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
				
//				INTENTO DE PONER DATOS EN EL PANEL INFO
				
//				listaPuestos.addListSelectionListener(new ListSelectionListener() {
//					
//					@Override
//					public void valueChanged(ListSelectionEvent e) {
//						Vector<Persona> candidatos = new Vector<>();
//				 	    for(Persona p : DatosFicheros.getPersonas()) {
//				 	    	PuestoTrabajo puesto = listaPuestos.getSelectedValue();
//				 	    	if(p.getHabilidadesTecnicas().contains(puesto.getHabilidadesReq())) {
//				 	    		candidatos.add(p);
//				 	    		}
//				 	    	}
//				 	    pnlInfo.add(new JLabel(candidatos.get(0).getNombre()), BorderLayout.CENTER);
//				 	    
//				 	    
//						
//					}
//				});
			
			
	       }
	       
	     
	       
		
	}
	
	

	public void xArojo () {
		try {
            // Carga la imagen original desde el archivo en el paquete "imagenes"
            InputStream imageStream = PnlBotonera.class.getResourceAsStream("xRoja.png");
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
            lblGrafExpX = new JLabel(new ImageIcon(resizedImage));
            lblGrafExpX.setBackground(new Color(240, 240, 240));
    		lblGrafExpX.setBounds(20, 0, 38, 38);
    		pnlPass.add(lblGrafExpX, BorderLayout.WEST);
      
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		
	}

	
	public static void main(String[] args) {
		DatosFicheros datos = new DatosFicheros();
		JFrame frame = new JFrame();
		frame.getContentPane().add( new PnlExplorar(DatosFicheros.getPersonas().get(0)));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(750, 650);
		//frame.getContentPane().add(new PnlExplorar(DatosFicheros.getEmpresas().get(0)));
		frame.setVisible(true);
		
	}
}
