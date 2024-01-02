package ventanas;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.TreeSet;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import clases.Mensaje;
import clases.TipoMensaje;
import datos.DatosFicheros;
import nube.ImagenesAzure;
import servidor.ServicioPersistencia;
import usuarios.Empresa;
import usuarios.Persona;
import usuarios.Usuario;



public class PnlChat extends JPanel{
	protected Usuario usuario;
	private ServicioPersistencia servicio;
	private DefaultListModel<Usuario> modeloLista;
	private JList<Usuario> listaContactos;
	private Usuario contacto = null;
	private JPanel pnlChatsContent;
	private CardLayout layoutChats;
	protected HashMap<Integer, MiPanelChat> mapaPaneles;
	
//	PRUEBAS TIEMPO:
	long tiempoInicio;
	long tiempoActual;
	long tiempoResultante;
	
//	SOCKETS:
	public JList<Usuario> getListaContactos() {
		return listaContactos;
	}
	
	
	public HashMap<Integer, MiPanelChat> getMapaPaneles() {
		return mapaPaneles;
	}

	public void setMapaPaneles(HashMap<Integer, MiPanelChat> mapaPaneles) {
		this.mapaPaneles = mapaPaneles;
	}



	public PnlChat() {
		this.servicio = VentanaPrincipal.servicio;
		setLayout(new BorderLayout());
		this.usuario = PnlBotonera.usuarioAutenticado;
		setSize(800,600);
		if (usuario instanceof Persona) {
//			setTitle(((Persona)usuario).getCorreoElectronico());
		}else {
//			setTitle(((Empresa)usuario).getCorreoElectronico());
		}
		
//		(new Thread() {@Override public void run() {VentanaChat.this.lanzaCliente();}}).start();
//		(new Thread(() -> {PnlChat.this.lanzaCliente();})).start();
		
		this.usuario = usuario;
		mapaPaneles = new HashMap<>();
		
//		CREACION PANEL CHAT

		pnlChatsContent = new JPanel();
		layoutChats = new CardLayout();
		pnlChatsContent.setLayout(layoutChats);
		add(pnlChatsContent);
		
		
//		CREACION DE LA LISTA A LA PARTE IZQUIERDA DEL PANEL
		modeloLista = new DefaultListModel<Usuario>();
		listaContactos = new JList<Usuario>(modeloLista);
//		listaContactos.setPreferredSize(new Dimension(200,200));
		JScrollPane spLista = new JScrollPane(listaContactos);
		spLista.setPreferredSize(new Dimension(200,200));
		
		tiempoInicio = System.currentTimeMillis();
		
		anadirContactos();
		
		tiempoActual = System.currentTimeMillis();
		tiempoResultante = tiempoActual - tiempoInicio;
		System.out.println("Anadir Contactos: " + tiempoResultante);
		
		tiempoInicio = System.currentTimeMillis();
		servicio.getUsuarios();
		tiempoActual = System.currentTimeMillis();
		tiempoResultante = tiempoActual - tiempoInicio;
		System.out.println("Tiempo que tarda en obtener los usuarios: " + tiempoResultante);
		
//		Datos.getMapaActListas().put(this, listaContactos);
		add(spLista,BorderLayout.WEST);
		
		listaContactos.setCellRenderer((list, value, index, isSelected, cellHasFocus) -> {
		    JPanel pnl = new JPanel();
		    pnl.setPreferredSize(new Dimension(200, 40));

		    if (isSelected) {
		        pnl.setBackground(new Color(122, 199, 218));
		    } else {
		        pnl.setBackground(new Color(202, 232, 232));
		        pnl.setForeground(list.getForeground());
		    }
		    
		    JLabel lbl = null;
//		    JLabel lbl = new JLabel(value.toString());
		    if (value instanceof Persona) {
		    	lbl = new JLabel(((Persona)value).getCorreo());
		    }else {
		    	lbl = new JLabel(((Empresa)value).getCorreo());
		    }
//		    JLabel lbl = new JLabel(value.getId()+"");
		    pnl.add(lbl);

		    return pnl;
		});
		
		listaContactos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                listaContactos.clearSelection();
            }

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				int index = listaContactos.locationToIndex(e.getPoint());
				if (index != -1) {
					PnlChat.this.contacto = modeloLista.get(index);
					layoutChats.show(pnlChatsContent,contacto.getId()+"");
				}
			}
        });
		
		
		listaContactos.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int index = listaContactos.locationToIndex(e.getPoint());
                if (index != -1) {
                    listaContactos.setSelectedIndex(index);
                }
            }
        });
		if (modeloLista.getSize()>0) {
			layoutChats.show(pnlChatsContent, modeloLista.get(0).getId()+"");
		}
		
		tiempoInicio = System.currentTimeMillis();
		
		TreeSet<Mensaje> mensajesPendientes = servicio.mensajesPendientes();
		if (mensajesPendientes != null) {
			for (Mensaje m : mensajesPendientes) {
				if (m.getFrom() == usuario.getId()) {
					mapaPaneles.get(m.getTo()).locateMessage(TipoMensaje.ENVIO, m);
				}else {
					mapaPaneles.get(m.getFrom()).locateMessage(TipoMensaje.RECEPCION, m);
				}
			}
		}
		
		tiempoActual = System.currentTimeMillis();
		tiempoResultante = tiempoActual-tiempoInicio;
		System.out.println("Mensajes pendientes: " +  tiempoResultante);
		
		setVisible(true);
	}
	
	public void anadirContactos() {
//		for (Usuario u: DatosFicheros.getUsuarios()) {
		for (Usuario u: servicio.getUsuarios()) {
			long tiempoInicio1 = System.currentTimeMillis();
			if (!u.equals(this.usuario)) {
				if (!modeloLista.contains(u)) {
				modeloLista.add(modeloLista.getSize(),u);
				MiPanelChat p = new MiPanelChat(usuario,u, PnlChat.this);
				pnlChatsContent.add(p,u.getId()+"");
				mapaPaneles.put((int) u.getId(), p);
				}
			}
			long tiempoActual1 = System.currentTimeMillis();
			long tiempoResultante1 = tiempoActual1-tiempoInicio1;
			System.out.println("Tiempo en anadir un usuario: " + tiempoResultante1);
			
		}
	}
	
	
    public static class MiPanelChat extends JPanel{
    	JScrollPane spPnlChatMensajes;
    	JLabel lblContacto;
    	JPanel pnlChatMensajes;
    	JPanel pnlContacto;
    	JPanel pnlTextField;
    	JTextField tfMensaje;
    	
    	
    	public MiPanelChat(Usuario usuario ,Usuario contacto, PnlChat pc) {
    		
    		setBackground(Color.WHITE);
    		setLayout(new BorderLayout());
    		pnlChatMensajes = new JPanel();
    		pnlChatMensajes.setLayout(new BoxLayout(pnlChatMensajes, BoxLayout.Y_AXIS));
    		spPnlChatMensajes = new JScrollPane(pnlChatMensajes);
    		
    		
    		pnlContacto = new JPanel();
    		pnlContacto.setPreferredSize(new Dimension(50,40));
    		pnlContacto.setBackground(new Color(202, 232, 232));
    		pnlContacto.setLayout(new BorderLayout());
//    		pnlContacto.add(ImagenesAzure.crearImagen(contacto, 20, 20),BorderLayout.WEST);
    		lblContacto = new JLabel();
    		lblContacto.setHorizontalAlignment(SwingConstants.LEADING);
    		JPanel p = new JPanel();
    		p.add(ImagenesAzure.crearImagen(contacto, 30, 30));
    		p.add(lblContacto);
    		p.setBackground(new Color(202, 232, 232));
//    		pnlContacto.add(lblContacto, BorderLayout.CENTER);
    		pnlContacto.add(p, BorderLayout.CENTER);
    		add(pnlContacto, BorderLayout.NORTH);
    		
    		pnlTextField = new JPanel();
    		pnlTextField.setLayout(new BorderLayout());
    		tfMensaje = new JTextField();
    		pnlTextField.add(tfMensaje);
    		pnlTextField.setPreferredSize(new Dimension(200,40));
    		pnlTextField.setMinimumSize(new Dimension(200,60));
    		add(pnlTextField, BorderLayout.SOUTH);
    		
    		add(spPnlChatMensajes, BorderLayout.CENTER);
    		
    		setLblContacto(contacto);
    		
    		
    		
    		tfMensaje.addActionListener( new ActionListener() { // Evento de <enter> de textfield
    			@Override
    			public void actionPerformed(ActionEvent e) {
    				Mensaje mensaje = new Mensaje((int) usuario.getId(), (int) contacto.getId(), tfMensaje.getText(), new Date());
    				locateMessage(TipoMensaje.ENVIO, mensaje);
    				tfMensaje.setText( "" );
    				try {
    					VentanaPrincipal.servicio.enviarMensaje(mensaje);
//    					pc.getFlujoOut().writeObject(mensaje);
    					
    				} catch (Exception e1) {  // Error en writeObject
    					JOptionPane.showMessageDialog(null, "No se ha podido enviar el mensaje.");
    				}
    			}
    		});
    	}
    	
    	public void setLblContacto(Usuario u) {
    		
    		if (u instanceof Persona) {
    			lblContacto.setText(((Persona)u).getCorreo());
    		}else {
    			lblContacto.setText(((Empresa)u).getCorreo());
    		}
    	}
    	
		public void locateMessage(TipoMensaje tipo, Mensaje mensaje) {
		    	
				pnlChatMensajes.revalidate();
				pnlChatMensajes.repaint();
		        
		        JPanel messagePanel = null;
		        if (tipo.equals(TipoMensaje.ENVIO)) {
		        	messagePanel = sendedMessage(mensaje);
		        }else {
		        	messagePanel = receivedMessage(mensaje);
		        }
		        
		        
		        pnlChatMensajes.add(messagePanel);
		        
		        
		        // Actualizar la interfaz gráfica
		        pnlChatMensajes.revalidate();
		        pnlChatMensajes.repaint();
		        
		        // Desplazar la barra de desplazamiento hacia abajo
		        SwingUtilities.invokeLater(() -> {
		            JScrollBar verticalScrollBar = spPnlChatMensajes.getVerticalScrollBar();
		            verticalScrollBar.setValue(verticalScrollBar.getMaximum());
		        });
		        
		    }
		
		public static JPanel sendedMessage(Mensaje msg) {
	    	// Crear un nuevo panel para cada mensaje
	        JPanel panel = formatLabel(msg);
	        JPanel messagePanel = new JPanel(new BorderLayout());
	        messagePanel.add(panel, BorderLayout.EAST);
	        Integer width = (int) messagePanel.getMaximumSize().getWidth();
	        Integer height = (int) messagePanel.getPreferredSize().getHeight();
	        messagePanel.setMaximumSize(new Dimension(width, height));
	        return messagePanel;
	    }
	    
	    public static JPanel receivedMessage(Mensaje msg) {
	    	// Crear un nuevo panel para cada mensaje
	        JPanel panel = formatLabel(msg);
	        JPanel messagePanel = new JPanel(new BorderLayout());
	        messagePanel.add(panel, BorderLayout.WEST);
	        Integer width = (int) messagePanel.getMaximumSize().getWidth();
	        Integer height = (int) messagePanel.getPreferredSize().getHeight();
	        messagePanel.setMaximumSize(new Dimension(width, height));
	        return messagePanel;
	    }
	    
	    public static JPanel formatLabel(Mensaje out) {
	        JPanel panel = new JPanel();
	        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
	        
	        JLabel output = new JLabel("<html><p style=\"width: 150px\">" + out.getMensaje() + "</p></html>");
	        output.setFont(new Font("Tahoma", Font.PLAIN, 16));
	        output.setBackground(new Color(37, 211, 102));
	        output.setOpaque(true);
	        output.setBorder(new EmptyBorder(15, 15, 15, 50));
	        
	        panel.add(output);
	        
	//        Calendar cal = Calendar.getInstance();
	        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
	        
	        JLabel lblTime = new JLabel();
	        lblTime.setText(sdf.format(out.getDate()));
	        
	        panel.add(lblTime);
	        
	        return panel;
	    }
    	
    }
}
