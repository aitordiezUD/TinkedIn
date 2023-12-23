package datos;

import java.util.TreeSet;

import clases.Mensaje;
import clases.PuestoTrabajo;
import sistemaExplorar.Like;
import usuarios.Empresa;
import usuarios.Persona;
import usuarios.Usuario;

public class DatosBD implements ManejoDatos {

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fin() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void anadirUsuarioPersona(Persona persona) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void anadirUsuarioEmpresa(Empresa empresa) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Usuario getUsuarioFromCorreo(String correo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean autenticarUsuario(String correo, String contraseña) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsTelefono(String telefono) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsEmail(String email) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void crearLike(Usuario from, Usuario to) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void comprobarMatch(Like like) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void anadirPuesto(PuestoTrabajo puesto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void anadirMensaje(Mensaje mensaje) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TreeSet<Mensaje> filtrarMensajes(Usuario usuario) {
		// TODO Auto-generated method stub
		return null;
	}

}
