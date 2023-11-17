package clases;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

public class DatosFicheros implements ManejoDatos{
	
	private static ArrayList<Usuario> usuarios;
	private static ArrayList<Persona> personas;
	private static ArrayList<Empresa> empresas;
	private static HashMap<Integer, Usuario> mapaIdUsuario;
	private static HashMap<String, Usuario> mapaEmailUsuario;
	private static HashMap<String, Usuario> mapaTlfnoUsuario;
	private static Properties properties;
	
	public static ArrayList<Usuario> getUsuarios() {
		return usuarios;
	}


	public static void setUsuarios(ArrayList<Usuario> usuarios) {
		DatosFicheros.usuarios = usuarios;
	}


	public static ArrayList<Persona> getPersonas() {
		return personas;
	}


	public static void setPersonas(ArrayList<Persona> personas) {
		DatosFicheros.personas = personas;
	}


	public static ArrayList<Empresa> getEmpresas() {
		return empresas;
	}


	public static void setEmpresas(ArrayList<Empresa> empresas) {
		DatosFicheros.empresas = empresas;
	}


	public static HashMap<Integer, Usuario> getMapaIdUsuario() {
		return mapaIdUsuario;
	}


	public static void setMapaIdUsuario(HashMap<Integer, Usuario> mapaIdUsuario) {
		DatosFicheros.mapaIdUsuario = mapaIdUsuario;
	}


	public static HashMap<String, Usuario> getMapaEmailUsuario() {
		return mapaEmailUsuario;
	}


	public static void setMapaEmailUsuario(HashMap<String, Usuario> mapaEmailUsuario) {
		DatosFicheros.mapaEmailUsuario = mapaEmailUsuario;
	}


	public static HashMap<String, Usuario> getMapaTlfnoUsuario() {
		return mapaTlfnoUsuario;
	}


	public static void setMapaTlfnoUsuario(HashMap<String, Usuario> mapaTlfnoUsuario) {
		DatosFicheros.mapaTlfnoUsuario = mapaTlfnoUsuario;
	}


	
	
	
	


	public DatosFicheros() {
		init();
		
		
	}


	@Override
	public void init() {
		// TODO Auto-generated method stub
		usuarios = new ArrayList<Usuario>();
		personas = new ArrayList<Persona>();
		empresas = new ArrayList<Empresa>();
		mapaIdUsuario = new HashMap<Integer, Usuario>();
		mapaEmailUsuario = new HashMap<String, Usuario>();
		mapaTlfnoUsuario = new HashMap<String, Usuario>();
		properties = new Properties();
		
		try{
			InputStream input = new FileInputStream(new File("userCreation.properties"));
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
		Usuario.setCount(Integer.parseInt(properties.getProperty("count")+""));
		
		
	}


	@Override
	public void fin() {
		// TODO Auto-generated method stub
		properties.setProperty("count", Integer.toString(Usuario.getCount()));
	}


	@Override
	public void anadirUsuarioPersona(Persona persona) {
		// TODO Auto-generated method stub
		usuarios.add(persona);
		personas.add(persona);
		mapaIdUsuario.put(Integer.parseInt(persona.getId()+""), persona);
		mapaEmailUsuario.put(persona.getCorreoElectronico(), persona);
		mapaTlfnoUsuario.put(persona.getTelefeno(), persona);
	}
	
	
	
	
	
}
