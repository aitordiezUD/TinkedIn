package servidor;


import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.Vector;
import java.util.logging.Level;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import clases.Habilidad;
import clases.Mensaje;
import clases.PuestoTrabajo;
import datos.DatosBD;
import datos.DatosFicheros;
import datos.ManejoDatos;
import sistemaExplorar.Like;
import sistemaExplorar.Match;
import usuarios.Empresa;
import usuarios.Persona;
import usuarios.Usuario;

public class Servidor {
	private static final int PUERTO = ConfigServer.PUERTO;
	private static ManejoDatos datos;
	private static Servidor instance;
	
	public static ManejoDatos getDatos() {
		return datos;
	}

	public static void setDatos(ManejoDatos datos) {
		Servidor.datos = datos;
	}

	//Atributos para comunicacion
	private static Map<Integer, ObjectOutputStream> mapaDirecciones;
	private static Vector<HiloComunicacion> listaHilos; // Lista de hilos de comunicación  
	private static Vector<Socket> listaSockets;
	private static boolean finComunicacion = false;
    private static Map<Integer, List<Mensaje>> mapaMensajasPorEnviar;
	
	// Atributos visuales
	private static VentanaServidor vs;  // Ventana del servidor
	private static JLabel lEstado = new JLabel( " " );
	private static JTextArea taMensajes = new JTextArea( 10, 1 );
	
	public Servidor() {
		datos = new DatosBD();
		mapaDirecciones = new HashMap<>();
		mapaDirecciones = Collections.synchronizedMap(mapaDirecciones);
		mapaMensajasPorEnviar = new HashMap<>();
		mapaMensajasPorEnviar = Collections.synchronizedMap(mapaMensajasPorEnviar);
		listaHilos = new Vector<>();
		listaSockets = new Vector<>();
		vs = new VentanaServidor();
		vs.setVisible( true );
		new Thread(() -> {vs.lanzaServidor();}).start();
	}
	
    public static Servidor getInstance() {
        if (instance == null) {
            instance = new Servidor();
        }
        return instance;
    }
	
	public static void main(String[] args) {
		new Servidor();
	}
	
	@SuppressWarnings("serial")
	public static class VentanaServidor extends JFrame {
		public VentanaServidor() {
			setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
			setSize( 500, 300 );
			setLocation( 400, 0 );
			setTitle( "Ventana servidor" );
			getContentPane().add( new JScrollPane(taMensajes), BorderLayout.CENTER );
			getContentPane().add( lEstado, BorderLayout.SOUTH );
			addWindowListener( new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					finComunicacion = true;
					datos.fin();
				}
			});
		}
	    public void lanzaServidor() {
	    	try(ServerSocket serverSocket = new ServerSocket( PUERTO )) {
	    		serverSocket.setSoTimeout( 5000 );  // Pone un timeout de 5 milisegundos
		    	while (!finComunicacion) {
		    		// Escucha una conexión por parte de cliente y se queda bloqueado hasta que no se recibe
		    		try {
		    			Socket socket = serverSocket.accept();   
			    		HiloComunicacion hilo = new HiloComunicacion( socket);
			    		hilo.start();
			    		
		    		} catch (SocketTimeoutException e) {}  // Si es un timeout no es un error, simplemente se vuelve a intentar salvo que la ventana se cierre (finComunicacion)
		    	}
		    	serverSocket.close();  // Cierra el socket de servidor
	    	} catch(IOException e) {
//	    		e.printStackTrace();
	    		lEstado.setText("Error en servidor: " + e.getMessage());
	    	}
	    }
	}
	
	private static class HiloComunicacion extends Thread {
		private Socket socket;  // socket de comunicación con cada cliente
		private int idSender = -1; // nombre de cada cliente
		public HiloComunicacion(Socket socket) {
			this.socket = socket;
		}
		@Override
		public void run() {
	    	try {
//	    		System.out.println("Conexion establecida en Servidor");
	    		socket.setSoTimeout( 1000 ); // Pone el timeout para que no se quede eternamente en la espera (1)
	    		ObjectInputStream input = new ObjectInputStream(socket.getInputStream());  // Canal de entrada de socket (leer del cliente)
//	    		System.out.println("Input en servidor " + input.readObject());
//	    		idSender = (int) input.readObject();
//	    		System.out.println("idSender: " + idSender);
	    		ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());  // Canal de salida de socket (escribir al cliente)
//	    		output.writeObject("Recibido");
//	    		taMensajes.append("Usuario " + idSender+ " conectado." + "\n");
	    		taMensajes.append("Nueva conexión" + "\n");
	    		listaHilos.add( this );
	    		listaSockets.add( socket );
	    		while(!finComunicacion) {  // Bucle de comunicación de tiempo real con el cliente
	    			try {
		    			Object objRecibido = input.readObject();  // Espera a recibir petición de cliente (1) - se acaba con timeout
		    			if (objRecibido.equals(ConfigServer.FIN)) {
		    				break;
		    			}
		    			
		    			if (objRecibido.equals(ConfigServer.ENVIO_MENSAJE)) {
		    				Mensaje mensaje = (Mensaje) input.readObject();
			    			taMensajes.append(mensaje.toString()+ "\n");
			    			taMensajes.setSelectionStart( taMensajes.getText().length() );  // Pone el cursor al final del textarea
			    			// Envía el mensaje al cliente destinatario si su OOS esta en el mapa de direcciones, sino se almacenara en el 
			    			//mapa de mensajes por enviar hasta que el destinatario este conectado.
			    			if (mapaDirecciones.get((int) mensaje.getTo()) != null) {
			    				mapaDirecciones.get((int) mensaje.getTo()).writeObject(mensaje);
			    				datos.anadirMensaje(mensaje);
			    			}else {
			    				datos.anadirMensaje(mensaje);
			    			}
		    			}
		    			
		    			if (objRecibido.equals(ConfigServer.ANADIR_LIKE)) {
		    				Like like = (Like) input.readObject();
		    				Match match = datos.comprobarMatch(like);
		    				taMensajes.append(like.toString()+ "\n");
			    			taMensajes.setSelectionStart( taMensajes.getText().length() );
			    			System.out.println("Match: " + match);
		    				if (match != null) {
		    					int idOther;
			    				if (match.getU1() == idSender) {
			    					idOther = match.getU2();
			    				}else {
			    					idOther = match.getU1();
			    				}
		    					output.writeObject(match);
		    					
		    					if (mapaDirecciones.get(idOther) != null) {
		    						mapaDirecciones.get(idOther).writeObject(match);
		    					}
		    					taMensajes.append(match.toString()+ "\n");
				    			taMensajes.setSelectionStart( taMensajes.getText().length() );
		    				}
		    			}
		    			
		    			if (objRecibido.equals(ConfigServer.ANADIR_MATCH)) {
		    				Match m = (Match) input.readObject();
		    				output.writeObject(m);
		    			}
		    			
		    			if (objRecibido.equals(ConfigServer.LOGIN)) {
		    				String email = (String) input.readObject();
		    				String contrasena = (String) input.readObject();
		    				if (datos.autenticarUsuario(email, contrasena)) {
		    					output.writeObject(ConfigServer.OK);
		    					idSender = (int) datos.getUsuarioFromCorreo(email).getId();
		    		    		mapaDirecciones.put( idSender, output );
		    		    		taMensajes.append("Usuario " + idSender + " conectado" + "\n");
		    				}else {
		    					output.writeObject(ConfigServer.NO_OK);
		    				}
		    			}
		    			
		    			if (objRecibido.equals(ConfigServer.GET_USUARIO_FROM_CORREO)) {
		    				String email = (String) input.readObject();
		    				Usuario u = datos.getUsuarioFromCorreo(email);
		    				output.writeObject(u);
		    			}
		    			
		    			if (objRecibido.equals(ConfigServer.EDITAR_USUARIO)) {
		    				Usuario u1 = (Usuario) input.readObject();
		    				if (u1 instanceof Persona) {
		    					Persona p1 = (Persona) u1;
		    					Persona p2 = (Persona) datos.getUsuarioFromCorreo(p1.getCorreo());
		    					p2.setNombre(p1.getNombre());
		    					p2.setApellidos(p1.getApellidos());
		    					p2.setEdad(p1.getEdad());
		    					p2.setUbicacion(p1.getUbicacion());
		    					p2.setPassword(p1.getPassword());
		    				} else {
		    					Empresa e1 = (Empresa) u1;
		    					Empresa e2 = (Empresa) datos.getUsuarioFromCorreo(e1.getCorreo());
		    					e2.setNombre(e1.getNombre());
		    					e2.setDescripcion(e1.getDescripcion());
		    					e2.setUbicaciones(e1.getUbicaciones());
		    					e2.setPuestos(e1.getPuestos());
		    					e2.setPassword(e1.getPassword());
		    				}
		    			}
		    			
		    			if (objRecibido.equals(ConfigServer.CREAR_USUARIO)) {
	    					Object[] atribs = (Object[]) input.readObject();
		    				if (atribs.length == 9 ) {
		    					Persona p = datos.crearUsuarioPersona((String) atribs[0],(String) atribs[1],(String) atribs[2],(Date) atribs[3],
		    							(String) atribs[4],(String) atribs[5], (ArrayList<Habilidad>) atribs[6], (File) atribs[7], (String) atribs[8]);
		    					output.writeObject(p);
		    				}else {
		    					Empresa e = datos.crearUsuarioEmpresa((String) atribs[0], (String) atribs[1], (String) atribs[2], (String) atribs[3], 
		    							(ArrayList<String>) atribs[4], (File) atribs[5], (String) atribs[6]);
		    					output.writeObject(e);
		    				}
	    				}
		    			
		    			if (objRecibido.equals(ConfigServer.COMPROBAR_CORREO)) {
		    				String correo = (String) input.readObject();
		    				if (datos.containsEmail(correo)){
		    					output.writeObject(ConfigServer.NO_OK);
		    				}else {
		    					output.writeObject(ConfigServer.OK);
		    				}
		    			}
		    			
		    			if (objRecibido.equals(ConfigServer.COMPROBAR_TELEFONO)) {
		    				String telefono = (String) input.readObject();
		    				if (datos.containsEmail(telefono)){
		    					output.writeObject(ConfigServer.NO_OK);
		    				}else {
		    					output.writeObject(ConfigServer.OK);
		    				}
		    			}
		    			
		    			if (objRecibido.equals(ConfigServer.ANADIR_PUESTO)) {
		    				PuestoTrabajo p = (PuestoTrabajo) input.readObject();
		    				datos.anadirPuesto(p);
		    			}
		    			
		    			if (objRecibido.equals(ConfigServer.DELETE)) {
		    				datos.delete();
		    			}
		    			
		    			if (objRecibido.equals(ConfigServer.MENSAJES_PENDIENTES)) {
		    				TreeSet<Mensaje> set = datos.filtrarMensajes(datos.getUsuarioFromId(idSender));
		    				System.err.println("Mensajes pendientes: " + set);
		    				output.writeObject(set);
		    			}
		    			
		    			if (objRecibido.equals(ConfigServer.GET_EMPRESAS)) {
		    				output.writeObject(datos.getEmpresas());
		    			}
		    			
		    			if (objRecibido.equals(ConfigServer.GET_USUARIOS)) {
		    				output.writeObject(datos.getUsuarios());
		    			}
		    			
		    			if (objRecibido.equals(ConfigServer.ANADIR_MENSAJE)) {
		    				Mensaje m = (Mensaje) input.readObject();
		    				datos.anadirMensaje(m);
		    				System.out.println("Nuevo mensaje anadido");
		    			}
		    				
		    			if (objRecibido.equals(ConfigServer.GET_PERSONAS)) {
		    				output.writeObject(datos.getPersonas());
		    			}
		    			
		    			if (objRecibido.equals(ConfigServer.GET_EMPRESA_FROM_PUESTO)) {
		    				PuestoTrabajo puesto = (PuestoTrabajo) input.readObject();
		    				Empresa empresa = (Empresa) datos.getUsuarioFromId( (int) puesto.getIdEmpresa());
		    				output.writeObject(empresa);
		    			}
		    			if(objRecibido.equals(ConfigServer.GET_FRECUENCIA_HABS)) {
		    				String campo = (String) input.readObject();
		    				Map<String, Integer> mapaFrec = (Map<String, Integer>) datos.getFreHab(campo);
		    				output.writeObject(mapaFrec);
		    			}
		    			if(objRecibido.equals(ConfigServer.GET_FRECUENCIA_HABS_PUESTOS)) {
		    				String campo = (String) input.readObject();
		    				Map<String, Integer> mapaFrec = (Map<String, Integer>) datos.getFreHab(campo);
		    				output.writeObject(mapaFrec);
		    			}
		    			if (objRecibido.equals(ConfigServer.GET_NOMBRE_EMPRESA_FROM_ID)) {
		    				int id = (int) input.readObject();
		    				
		    			}
		    			
	    			} catch (SocketTimeoutException e) {} // Excepción de timeout - no es un problema
	    		}
	    		System.out.println("Servidor: fin comunicacion");
	    		if (idSender != -1) {
	    			taMensajes.append("Usuario " + idSender + " desconectado. \n");
	    		}
	    		socket.close();
	    		// Quita el cliente de las listas para que no se use en lo sucesivo
	    		int clienteACerrar = listaSockets.indexOf( socket );
	    		listaHilos.removeElementAt( clienteACerrar );
	    		listaSockets.removeElementAt( clienteACerrar );
	    		mapaDirecciones.remove(idSender);
	    	} catch(Exception e) {
	    		System.out.println("Servidor: Error en la primera parte del run");
	    		e.printStackTrace();
	    	}
		}
	}
	
}