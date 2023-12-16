package servidor;

import usuarios.Usuario;

public interface ServicioPersistencia {
	
	public void init();
	
	public void close();
	
	public boolean logIn(String usuario, String contrasena);
	
	public Usuario getUsuarioFromCorreo(String correo);
	
	public void editarUsuario(Usuario usuario);
	
	public void crearUsuario(Usuario u);
	
	public Boolean contieneCorreo(String correo);
	
	public Boolean contieneTelefono(String telefono);
	
}
