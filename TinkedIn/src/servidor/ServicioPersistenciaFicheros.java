package servidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Vector;
import java.util.logging.Level;

import javax.swing.JOptionPane;

import clases.PuestoTrabajo;
import usuarios.Usuario;





public class ServicioPersistenciaFicheros implements ServicioPersistencia{
	private HiloComunicacionServidor hilo = null;
	private ObjectOutputStream flujoOut = null;
	private boolean finComunicacion = false;
	private long TIMEOUT_ESPERA_SERVIDOR = 5000;  // Máximo tiempo a esperar respuesta del servidor
	
	private Vector<Object> respuestasServidor = new Vector<>();  // Respuestas del servidor encoladas para ser procesadas según procedan
	
	
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
	            		respuestasServidor.add( respuesta );
	    			} catch (SocketTimeoutException e) {} // Excepción de timeout - no es un problema
	            } while(!finComunicacion);
	            System.out.println("Socket cerrado");
				flujoOut.writeObject( ConfigServer.FIN );
	        } catch (Exception e) {
            	finComunicacion = true;
	        }
		}
	}
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		try {
			hilo = new HiloComunicacionServidor( ConfigServer.HOST, ConfigServer.PUERTO );
//			hilo.setDaemon( true );
			hilo.start();
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
	public void crearUsuario(Usuario u) {
		// TODO Auto-generated method stub
		try {
			flujoOut.writeObject(ConfigServer.CREAR_USUARIO);
			flujoOut.writeObject(u);
			
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("No se ha podido crear el usuario");
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
			puesto.getEmpresaPertenece().getPuestos().add(puesto);
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


	
	
	
	
}
