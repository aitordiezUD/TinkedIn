package servidor;

import java.util.ArrayList;

import clases.Mensaje;
import clases.PuestoTrabajo;
import usuarios.Usuario;

public interface ServicioPersistencia {
	
	public void init();
	
	public void delete();
	
	public void close();
	
	public boolean logIn(String usuario, String contrasena);
	
	public Usuario getUsuarioFromCorreo(String correo);
	
	public void editarUsuario(Usuario usuario);
	
	public void crearUsuario(Usuario u);
	
	public Boolean contieneCorreo(String correo);
	
	public Boolean contieneTelefono(String telefono);
	
	public void anadirPuesto(PuestoTrabajo puesto);
	
	public void enviarMensaje(Mensaje mensaje);
	
	public ArrayList<Mensaje> mensajesPendientes();
	
	public void escuchadorMensajes();
	
}
