package servidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.Vector;
import java.util.logging.Level;

import javax.swing.JOptionPane;

import clases.Mensaje;
import clases.PuestoTrabajo;
import clases.TipoMensaje;
import usuarios.Empresa;
import usuarios.Persona;
import usuarios.Usuario;
import ventanas.PnlChat;





public class ServicioPersistenciaFicheros implements ServicioPersistencia{
	private HiloComunicacionServidor hilo = null;
	private ObjectOutputStream flujoOut = null;
	private boolean finComunicacion = false;
	private long TIMEOUT_ESPERA_SERVIDOR = 15000;  // Máximo tiempo a esperar respuesta del servidor
	
	private Vector<Object> respuestasServidor = new Vector<>();  // Respuestas del servidor encoladas para ser procesadas según procedan
	private Vector<Mensaje> mensajesRecibidos = new Vector<>();  // Mensajes de otros usuarios recibidos (por medio del servidor) encolados para ser procesados según procedan
	
	private PnlChat pnlChat = null;
	
	
	
	public PnlChat getPnlChat() {
		return pnlChat;
	}
	
	@Override
	public void setPnlChat(PnlChat pnlChat) {
		this.pnlChat = pnlChat;
	}

	public ServicioPersistenciaFicheros() {
		
	}
	
	private class HiloComunicacionServidor extends Thread{
		private String server;
		private int puerto;
		HiloComunicacionServidor( String server, int puerto ) {
			this.server = server;
			this.puerto = puerto;
		}
		@Override
		public void run() {
	    	// Hilo que abre el socket y se va a quedar escuchando los mensajes del servidor
	        try (Socket socket = new Socket( server, puerto )) {
	    		socket.setSoTimeout( 5000 ); // Pone el timeout para que no se quede eternamente en la espera (1)
	            flujoOut = new ObjectOutputStream(socket.getOutputStream());
	            ObjectInputStream flujoIn = new ObjectInputStream(socket.getInputStream());
	            do { // Bucle de espera a mensajes del servidor
	            	try {
	            		Object respuesta = flujoIn.readObject();  // Devuelve mensaje de servidor o null cuando se cierra la comunicación
	            		if (respuesta instanceof Mensaje) {
	            			mensajesRecibidos.add( (Mensaje) respuesta );
	            		}else {
	            			respuestasServidor.add( respuesta );
						}
	    			} catch (SocketTimeoutException e) {} // Excepción de timeout - no es un problema
	            } while(!finComunicacion);
	            System.out.println("Socket cerrado");
				flujoOut.writeObject( ConfigServer.FIN );
	        } catch (Exception e) {
            	finComunicacion = true;
	        }
		}
	}
	
//	private class HiloEscuchadorMensajes extends Thread{
//		@Override
//		public void run() {
//			// TODO Auto-generated method stub
//			while (!finComunicacion) {
//				
//			}
//		}
//	}
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		try {
			hilo = new HiloComunicacionServidor( ConfigServer.HOST, ConfigServer.PUERTO );
//			hilo.setDaemon( true );
			hilo.start();
			(new Thread( () ->  escuchadorMensajes())).start();
			long time = System.currentTimeMillis();
			while (flujoOut==null && (System.currentTimeMillis()-time < TIMEOUT_ESPERA_SERVIDOR)) {
				Thread.sleep( 100 );
			}
			if (System.currentTimeMillis()-time >= TIMEOUT_ESPERA_SERVIDOR) {  // Timeout
				JOptionPane.showMessageDialog(null, "Timout en espera a conexión con servidor");
			}
			System.out.println("Conexion con servidor exitosa!");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Override
	public void escuchadorMensajes() {
		System.out.println("Escuchador de mensajes corriendo");
		while (!finComunicacion) {
			while(mensajesRecibidos.size() != 0) {
				Mensaje m = mensajesRecibidos.remove(0);
				this.pnlChat.getMapaPaneles().get(m.getFrom()).locateMessage(TipoMensaje.RECEPCION, m);
			}
			try {Thread.sleep(100);} catch (InterruptedException e) {e.printStackTrace();}
		}
		System.out.println("Escuchador de mensajes dead");
	}
	
	@Override
	public void close() {
		try {
			flujoOut.writeObject( ConfigServer.FIN );
			finComunicacion = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean logIn(String correo, String contrasena) {
		// TODO Auto-generated method stub
		try {
			flujoOut.writeObject(ConfigServer.LOGIN);
			flujoOut.writeObject(correo);
			flujoOut.writeObject(contrasena);
			// Espera a respuesta del servidor
			long time = System.currentTimeMillis();
			while (respuestasServidor.isEmpty() && (System.currentTimeMillis()-time < TIMEOUT_ESPERA_SERVIDOR)) {
				Thread.sleep( 100 );
			}
			if (System.currentTimeMillis()-time >= TIMEOUT_ESPERA_SERVIDOR) {  // Timeout
				return false;
			}
			
//			System.out.println("Respuestas servidor: " + respuestasServidor);
			Object resp = respuestasServidor.remove(0);
			if (resp.equals(ConfigServer.OK)) {
				return true;
			}else {
				return false;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Usuario getUsuarioFromCorreo(String correo) {
		// TODO Auto-generated method stub
		try {
			flujoOut.writeObject(ConfigServer.GET_USUARIO_FROM_CORREO);
			flujoOut.writeObject(correo);
			long time = System.currentTimeMillis();
			while (respuestasServidor.isEmpty() && (System.currentTimeMillis()-time < TIMEOUT_ESPERA_SERVIDOR)) {
				Thread.sleep( 100 );
			}
			if (System.currentTimeMillis()-time >= TIMEOUT_ESPERA_SERVIDOR) {  // Timeout
				System.err.println("No se ha podido devolver el usuario deseado, timeout servidor");
				return null;
			}
			Usuario u = (Usuario) respuestasServidor.remove(0);
			return u;
			
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("No se ha podido devolver el usuario deseado");
			return null;
		}
	}

	@Override
	public void editarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		try {
			flujoOut.writeObject(ConfigServer.EDITAR_USUARIO);
			flujoOut.writeObject(usuario);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.err.println("No se ha podido editar el usuario");
			e.printStackTrace();
		}
	}

	@Override
	public Usuario crearUsuario(Object[] atribs) {
		// TODO Auto-generated method stub
		try {
			flujoOut.writeObject(ConfigServer.CREAR_USUARIO);
			flujoOut.writeObject(atribs);
			long time = System.currentTimeMillis();
			while (respuestasServidor.isEmpty() && (System.currentTimeMillis()-time < TIMEOUT_ESPERA_SERVIDOR)) {
				Thread.sleep( 100 );
			}
			if (System.currentTimeMillis()-time >= TIMEOUT_ESPERA_SERVIDOR) {  // Timeout
				System.err.println("No se ha podido devolver el usuario deseado, timeout servidor");
				return null;
			}
			Usuario u = (Usuario) respuestasServidor.remove(0);
			return u;
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("No se ha podido crear el usuario");
			return null;
		}
	}

	@Override
	public Boolean contieneCorreo(String correo) {
		// TODO Auto-generated method stub
		try {
			flujoOut.writeObject(ConfigServer.COMPROBAR_CORREO);
			flujoOut.writeObject(correo);
			
			long time = System.currentTimeMillis();
			while (respuestasServidor.isEmpty() && (System.currentTimeMillis()-time < TIMEOUT_ESPERA_SERVIDOR)) {
				Thread.sleep( 100 );
			}
			if (System.currentTimeMillis()-time >= TIMEOUT_ESPERA_SERVIDOR) {  // Timeout
				System.err.println("No se ha podido comprobar el correo deseado, timeout servidor");
				return true;
			}
			
			String resp = (String) respuestasServidor.remove(0);
			if (resp.equals(ConfigServer.OK)) {
				return false;
			}else {
				return true;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("No se ha podido comprobar la validez del correo electrónico");
			e.printStackTrace();
			return true;
		}
	}

	@Override
	public Boolean contieneTelefono(String telefono) {
		try {
			flujoOut.writeObject(ConfigServer.COMPROBAR_TELEFONO);
			flujoOut.writeObject(telefono);
			long time = System.currentTimeMillis();
			while (respuestasServidor.isEmpty() && (System.currentTimeMillis()-time < TIMEOUT_ESPERA_SERVIDOR)) {
				Thread.sleep( 100 );
			}
			if (System.currentTimeMillis()-time >= TIMEOUT_ESPERA_SERVIDOR) {  // Timeout
				System.err.println("No se ha podido comprobar el correo deseado, timeout servidor");
				return true;
			}
			String resp = (String) respuestasServidor.remove(0);
			if (resp.equals(ConfigServer.OK)) {
				return false;
			}else {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("No se ha podido comprobar la validez del correo electrónico");
			e.printStackTrace();
			return true;
		}
	}

	@Override
	public void anadirPuesto(PuestoTrabajo puesto) {
		// TODO Auto-generated method stub
		try {
			flujoOut.writeObject(ConfigServer.ANADIR_PUESTO);
			flujoOut.writeObject(puesto);
//			puesto.getEmpresaPertenece().getPuestos().add(puesto);
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("No se ha podido añadir el puesto de trabajo");
		}
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		try {
			flujoOut.writeObject(ConfigServer.DELETE);
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("No se ha podido eliminar los datos");
			e.printStackTrace();
		}
	}

	@Override
	public void enviarMensaje(Mensaje mensaje) {
		// TODO Auto-generated method stub
		try {
			flujoOut.writeObject(ConfigServer.ENVIO_MENSAJE);
			flujoOut.writeObject(mensaje);
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("No se ha podido enviar el mensaje desde el cliente al servidor");
			e.printStackTrace();
		}
		
	}

	@Override
	public TreeSet<Mensaje> mensajesPendientes() {
		// TODO Auto-generated method stub
		try {
			flujoOut.writeObject(ConfigServer.MENSAJES_PENDIENTES);
			
			long time = System.currentTimeMillis();
			while (respuestasServidor.isEmpty() && (System.currentTimeMillis()-time < TIMEOUT_ESPERA_SERVIDOR)) {
				Thread.sleep( 100 );
			}
			if (System.currentTimeMillis()-time >= TIMEOUT_ESPERA_SERVIDOR) {  // Timeout
				System.err.println("No se ha podido comprobar el correo deseado, timeout servidor");
			}
			
			TreeSet<Mensaje> set = (TreeSet<Mensaje>) respuestasServidor.remove(0);
			return set;
			
//			String resp = (String) respuestasServidor.remove(0);
//			if (resp.equals(ConfigServer.OK)) {
//				TreeSet<Mensaje> mensajes = (TreeSet<Mensaje>) respuestasServidor.remove(0);
//				return mensajes;
//			} else {
//				return null;
//			}
			
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		
	}

	@Override
	public Vector<Empresa> getEmpresas() {
		// TODO Auto-generated method stub
		try {
			flujoOut.writeObject(ConfigServer.GET_EMPRESAS);
			
			long time = System.currentTimeMillis();
			while (respuestasServidor.isEmpty() && (System.currentTimeMillis()-time < TIMEOUT_ESPERA_SERVIDOR)) {
				Thread.sleep( 100 );
			}
			if (System.currentTimeMillis()-time >= TIMEOUT_ESPERA_SERVIDOR) {  // Timeout
				System.err.println("No se ha podido obtener el listado de empresas, timeout servidor");
				return new Vector<>();
			}
			
			Vector<Empresa> resp = (Vector<Empresa>) respuestasServidor.remove(0);
			return resp;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Vector<>();
		}
		
	}

	@Override
	public Vector<Usuario> getUsuarios() {
		// TODO Auto-generated method stub
				try {
					flujoOut.writeObject(ConfigServer.GET_USUARIOS);
					
					long time = System.currentTimeMillis();
					while (respuestasServidor.isEmpty() && (System.currentTimeMillis()-time < TIMEOUT_ESPERA_SERVIDOR)) {
						Thread.sleep( 100 );
					}
					if (System.currentTimeMillis()-time >= TIMEOUT_ESPERA_SERVIDOR) {  // Timeout
						System.err.println("No se ha podido obtener el listado de usuarios, timeout servidor");
						return new Vector<>();
					}
					
					Vector<Usuario> resp = (Vector<Usuario>) respuestasServidor.remove(0);
					return resp;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return new Vector<>();
				}
	}

	@Override
	public void anadirMensaje(Mensaje m) {
		// TODO Auto-generated method stub
		try {
			flujoOut.writeObject(ConfigServer.ANADIR_MENSAJE);
			flujoOut.writeObject(m);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Override
	public Vector<Persona> getPersonas() {
		try {
			flujoOut.writeObject( ConfigServer.GET_PERSONAS );
			
			long time = System.currentTimeMillis();
			while (respuestasServidor.isEmpty() && (System.currentTimeMillis()-time < TIMEOUT_ESPERA_SERVIDOR)) {
				Thread.sleep( 100 );
			}
			if (System.currentTimeMillis()-time >= TIMEOUT_ESPERA_SERVIDOR) {  // Timeout
				System.err.println("No se ha podido obtener el listado de empresas, timeout servidor");
				return new Vector<>();
			}
			
			Vector<Persona> resp = (Vector<Persona>) respuestasServidor.remove(0);
			return resp;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Vector<>();
		}
		
	}

	@Override
	public Empresa getEmpresaFromPuesto(PuestoTrabajo puesto) {
		// TODO Auto-generated method stub
		try {
			flujoOut.writeObject( ConfigServer.GET_EMPRESA_FROM_PUESTO);
			flujoOut.writeObject( puesto );
			
			long time = System.currentTimeMillis();
			while (respuestasServidor.isEmpty() && (System.currentTimeMillis()-time < TIMEOUT_ESPERA_SERVIDOR)) {
				Thread.sleep( 100 );
			}
			if (System.currentTimeMillis()-time >= TIMEOUT_ESPERA_SERVIDOR) {  // Timeout
				System.err.println("No se ha podido obtener el listado de empresas, timeout servidor");
				return null;
			}
			Empresa empresa = (Empresa) respuestasServidor.remove(0);
			return empresa;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


	
	
	
	
}
