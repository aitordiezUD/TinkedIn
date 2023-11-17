package clases;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import javax.swing.JOptionPane;

import hilos.CargarPersonasDAT;
import hilos.GuardarEmpresasDAT;
import hilos.GuardarPersonasDAT;

public class DatosFicheros implements ManejoDatos{
	
	protected static ArrayList<Usuario> usuarios;
	protected static ArrayList<Persona> personas;
	protected static ArrayList<Empresa> empresas;
	protected static HashMap<Integer, Usuario> mapaIdUsuario;
	protected static HashMap<String, Usuario> mapaEmailUsuario;
	protected static HashMap<String, Usuario> mapaTlfnoUsuario;
	protected static Properties properties;
	
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
//		Runnable r = new CargarPersonasDAT(DatosFicheros.this);
//		(new Thread(r)).start();
		
	}


	@Override
	public void fin() {
		// TODO Auto-generated method stub
		properties.setProperty("count", Integer.toString(Usuario.getCount()));
	}


	@Override
	public void anadirUsuarioPersona(Persona persona) {
		// TODO Auto-generated method stub
		String correo = persona.getCorreoElectronico();
		String telefono = persona.getTelefono();
		
		if (mapaEmailUsuario.keySet().contains(correo) | mapaTlfnoUsuario.keySet().contains(telefono)) {
			JOptionPane.showOptionDialog(
					null, 
					"El correo electrónico o teléfono se encuentran asociados a otro usuario.", 
					"Error", 
					JOptionPane.DEFAULT_OPTION, 
					JOptionPane.INFORMATION_MESSAGE,
					null,
					new Object[] {"Aceptar"}, 
					"Aceptar");	
		}else {			
			usuarios.add(persona);
			personas.add(persona);
			mapaIdUsuario.put(Integer.parseInt(persona.getId()+""), persona);
			mapaEmailUsuario.put(persona.getCorreoElectronico(), persona);
			mapaTlfnoUsuario.put(persona.getTelefono(), persona);
			Runnable r = new GuardarPersonasDAT();
			(new Thread(r)).start();
		}
		
		
	}
	
	public void anadirUsuarioEmpresa( Empresa empresa ) {
		//TODO Auto-generated method stub
		String correo = empresa.getCorreoElectronico();
		String telefono = empresa.getTelefono();
		
		if(mapaEmailUsuario.keySet().contains(correo) | mapaTlfnoUsuario.keySet().contains(telefono)){
			JOptionPane.showOptionDialog(null, 
					"El correo electrónico o teléfono se encuentran asociados a otro usuario.", 
					"Error", 
					JOptionPane.DEFAULT_OPTION, 
					JOptionPane.INFORMATION_MESSAGE,
					null,
					new Object[] {"Aceptar"}, 
					"Aceptar");
		}else {
			usuarios.add(empresa);
			empresas.add(empresa);
			mapaIdUsuario.put(Integer.parseInt(empresa.getId()+""), empresa);
			mapaEmailUsuario.put(empresa.getCorreoElectronico(), empresa);
			mapaTlfnoUsuario.put(empresa.getTelefono(), empresa);
			Runnable r = new GuardarEmpresasDAT();
			(new Thread(r)).start();
		}
		
		
	}
	
	
	
}
