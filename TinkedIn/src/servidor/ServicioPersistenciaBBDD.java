package servidor;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.Vector;

import clases.Mensaje;
import clases.PuestoTrabajo;
import usuarios.Empresa;
import usuarios.Persona;
import usuarios.Usuario;
import ventanas.PnlChat;

public class ServicioPersistenciaBBDD implements ServicioPersistencia {

	private HiloEscuchaServidorRemoto hilo = null;
	private ObjectOutputStream flujoOut = null;
	private boolean finComunicacion = false;
	private long TIMEOUT_ESPERA_SERVIDOR = 5000;  // Máximo tiempo a esperar respuesta del servidor
	
	private Vector<Object> respuestasServidor = new Vector<>();
	
	public ServicioPersistenciaBBDD() {
	}
	
	private class HiloEscuchaServidorRemoto extends Thread{
		private String server;
		private int puerto;
		
		public HiloEscuchaServidorRemoto( String server, int puerto ) {
			this.server = server;
			this.puerto = puerto;
		}

		@Override
		public void run() {
			try (Socket socket = new Socket(server, puerto)) {
				socket.setSoTimeout( 1000 );
				flujoOut = new ObjectOutputStream(socket.getOutputStream());
	            ObjectInputStream flujoIn = new ObjectInputStream(socket.getInputStream());
	            do {
	            	try {
						Object respuesta = flujoIn.readObject();
						respuestasServidor.add(respuesta);
					} catch (SocketTimeoutException e) {
						// TODO: handle exception
					}
	            }while(!finComunicacion);
	            System.out.println("Socket cerrado");
	            flujoOut.writeObject(ConfigServer.FIN);
			} catch (Exception e) {
				finComunicacion = true;
			}
			
		}
		
	}
	
	@Override
	public void init(  ) {
		try {
			hilo = new HiloEscuchaServidorRemoto(ConfigServer.HOST, ConfigServer.PUERTO);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void close() {
		try {
			
		}catch (Exception e) {
			
		}
		
	}

	@Override
	public boolean logIn(String usuario, String contrasena) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Usuario getUsuarioFromCorreo(String correo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void editarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void crearUsuario(Usuario u) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Boolean contieneCorreo(String correo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean contieneTelefono(String telefono) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void anadirPuesto(PuestoTrabajo puesto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enviarMensaje(Mensaje mensaje) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TreeSet<Mensaje> mensajesPendientes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void escuchadorMensajes() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Vector<Empresa> getEmpresas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector<Persona> getPersonas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPnlChat(PnlChat pnlChat) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Vector<Usuario> getUsuarios() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void anadirMensaje(Mensaje m) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Usuario crearUsuario(Object[] atribs) {
		// TODO Auto-generated method stub
		return null;
	}

}
