package servidor;

import java.util.ArrayList;
import java.util.TreeSet;
import java.util.Vector;

import clases.Mensaje;
import clases.PuestoTrabajo;
import usuarios.Empresa;
import usuarios.Usuario;
import ventanas.PnlChat;

public interface ServicioPersistencia {
	
	public void init();
	
	
	public void setPnlChat(PnlChat pnlChat);
	
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
	
	public TreeSet<Mensaje> mensajesPendientes();
	
	public void escuchadorMensajes();
	
	public Vector<Empresa> getEmpresas();
	
	public Vector<Usuario> getUsuarios();
	
	public void anadirMensaje(Mensaje m);
	
	
}
