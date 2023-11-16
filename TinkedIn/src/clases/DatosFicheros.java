package clases;

import java.util.ArrayList;
import java.util.HashMap;

public class DatosFicheros implements ManejoDatos{
	
	private ArrayList<Usuario> usuarios;
	private ArrayList<Persona> personas;
	private ArrayList<Empresa> empresas;
	private HashMap<String, String> mapaUsernameUsuario;
	private HashMap<String, String> mapaEmailUsuario;
	private HashMap<String, String> mapaTlfnoUsuario;
	
	
	
	
	public ArrayList<Usuario> getUsuarios() {
		return usuarios;
	}


	public void setUsuarios(ArrayList<Usuario> usuarios) {
		this.usuarios = usuarios;
	}


	public DatosFicheros() {
		init();
		
	}


	@Override
	public void init() {
		// TODO Auto-generated method stub
		usuarios = new ArrayList<>();
		
		
	}


	@Override
	public void fin() {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
}
