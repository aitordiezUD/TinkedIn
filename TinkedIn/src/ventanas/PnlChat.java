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
import java.util.Date;
import java.util.HashMap;

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

import clases.DatosFicheros;
import clases.Empresa;
import clases.Mensaje;
import clases.Persona;
import clases.Usuario;

enum tipoMensaje {ENVIO, RECEPCION};

public class PnlChat extends JPanel{
	protected Usuario usuario;
	private DefaultListModel<Usuario> modeloLista;
	private JList<Usuario> listaContactos;
	private Usuario contacto = null;
	private JPanel pnlChatsContent;
	private CardLayout layoutChats;
	private HashMap<Integer, MiPanelChat> mapaPaneles;
	
//	SOCKETS:
	private boolean finComunicacion = false;
	private ObjectOutputStream flujoOut;
	
	
	
	public ObjectOutputStream getFlujoOut() {
		return flujoOut;
	}

	public JList<Usuario> getListaContactos() {
		return listaContactos;
	}
	
	public PnlChat() {
		setLayout(new BorderLayout());
		this.usuario = PnlBotonera.usuarioAutenticado;
		setSize(800,600);
		if (usuario instanceof Persona) {
//			setTitle(((Persona)usuario).getCorreoElectronico());
		}else {
//			setTitle(((Empresa)usuario).getCorreoElectronico());
		}
		
//		(new Thread() {@Override public void run() {VentanaChat.this.lanzaCliente();}}).start();
		(new Thread(() -> {PnlChat.this.lanzaCliente();})).start();
		
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
		listaContactos.setPreferredSize(new Dimension(200,200));
		anadirContactos();
		
//		Datos.getMapaActListas().put(this, listaContactos);
		add(new JScrollPane(listaContactos),BorderLayout.WEST);
		
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
		    	lbl = new JLabel(((Persona)value).getCorreoElectronico());
		    }else {
		    	lbl = new JLabel(((Empresa)value).getCorreoElectronico());
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
		setVisible(true);
	}
	
	public void anadirContactos() {
		for (Usuario u: DatosFicheros.getUsuarios()) {
			if (!u.equals(this.usuario)) {
				if (!modeloLista.contains(u)) {
				modeloLista.add(modeloLista.getSize(),u);
				MiPanelChat p = new MiPanelChat(usuario,u, PnlChat.this);
				pnlChatsContent.add(p,u.getId()+"");
				mapaPaneles.put((int) u.getId(), p);
				}
			}
		}
	}
	
	public void lanzaCliente() {
		try (Socket socket = new Socket( "localhost", 4000 )) {
    		socket.setSoTimeout( 1000 ); // Pone el timeout para que no se quede eternamente en la espera (1)
            flujoOut = new ObjectOutputStream(socket.getOutputStream());
            int idAenviar = (int) usuario.getId();
            flujoOut.writeObject(idAenviar); // Mensaje que envia el ID del sender
            System.out.println("Id enviado por cliente");
            ObjectInputStream echoes = new ObjectInputStream(socket.getInputStream());
            Object echo1 = echoes.readObject();
            System.out.println(echo1);
            do {
            	try {
            		Mensaje mensaje = (Mensaje) echoes.readObject();  // Devuelve mensaje de servidor o null cuando se cierra la comunicación
            		mapaPaneles.get(mensaje.getFrom()).locateMessage(tipoMensaje.RECEPCION, mensaje);
    			} catch (SocketTimeoutException e) {} // Excepción de timeout - no es un problema
            }while(!finComunicacion);
			flujoOut.writeObject( "\\#FINDECOMUNICACION" );
        } catch (Exception e) {
//        	e.printStackTrace();
        	JOptionPane.showMessageDialog(null, "Recuerda lanzar el servidor");
        	finComunicacion = true;
//        	VentanaPrincipal.dispose();
        }
	}
	
	
    private static class MiPanelChat extends JPanel{
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
    		lblContacto = new JLabel();
    		lblContacto.setHorizontalAlignment(SwingConstants.CENTER);
    		pnlContacto.add(lblContacto, BorderLayout.CENTER);
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
    				locateMessage(tipoMensaje.ENVIO, mensaje);
    				tfMensaje.setText( "" );
    				try {
    					pc.getFlujoOut().writeObject(mensaje);
    					
    				} catch (IOException e1) {  // Error en writeObject
    					JOptionPane.showMessageDialog(null, "No se ha podido enviar el mensaje.");
    				}
    			}
    		});
    	}
    	
    	public void setLblContacto(Usuario u) {
    		
    		if (u instanceof Persona) {
    			lblContacto.setText(((Persona)u).getCorreoElectronico());
    		}else {
    			lblContacto.setText(((Empresa)u).getCorreoElectronico());
    		}
    	}
    	
		private void locateMessage(tipoMensaje tipo, Mensaje mensaje) {
		    	
				pnlChatMensajes.revalidate();
				pnlChatMensajes.repaint();
		        
		        JPanel messagePanel = null;
		        if (tipo.equals(tipoMensaje.ENVIO)) {
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
