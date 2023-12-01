package clases;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.spi.FileSystemProvider;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import java.util.Vector;

import javax.swing.JOptionPane;

import hilos.CargarEmpresasDAT;
import hilos.CargarPersonasDAT;
import hilos.GuardarEmpresasDAT;
import hilos.GuardarPersonasDAT;
import ventanas.PnlRegistroPersona;

public class DatosFicheros implements ManejoDatos{
	
	protected static Vector<Usuario> usuarios;
	protected static Vector<Persona> personas;
	protected static Vector<Empresa> empresas;
	protected static HashMap<Integer, Usuario> mapaIdUsuario;
	protected static HashMap<String, Usuario> mapaEmailUsuario;
	protected static HashMap<String, Usuario> mapaTlfnoUsuario;
	protected static HashMap<Usuario, String> mapaContraseñaUsuario;
	protected static Properties properties;
	
	protected static boolean test = false;
	
	public static Vector<Usuario> getUsuarios() {
		return usuarios;
	}


	public static void setUsuarios(Vector<Usuario> usuarios) {
		DatosFicheros.usuarios = usuarios;
	}


	public static Vector<Persona> getPersonas() {
		return personas;
	}


	public static void setPersonas(Vector<Persona> personas) {
		DatosFicheros.personas = personas;
	}


	public static Vector<Empresa> getEmpresas() {
		return empresas;
	}


	public static void setEmpresas(Vector<Empresa> empresas) {
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


	public static void setTest(boolean test) {
		DatosFicheros.test = test;
	}


	public DatosFicheros() {
		init();
	}


	@Override
	public void init() {
		// TODO Auto-generated method stub
		usuarios = new Vector<Usuario>();
		personas = new Vector<Persona>();
		empresas = new Vector<Empresa>();
		mapaIdUsuario = new HashMap<Integer, Usuario>();
		mapaEmailUsuario = new HashMap<String, Usuario>();
		mapaTlfnoUsuario = new HashMap<String, Usuario>();
		mapaContraseñaUsuario = new HashMap <Usuario, String>();
		properties = new Properties();
		
		try{
			InputStream input = new FileInputStream(new File("userCreation.properties"));
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
		Usuario.setCount(Integer.parseInt(properties.getProperty("count")+""));
		Runnable r1 = new CargarPersonasDAT(DatosFicheros.this);
		(new Thread(r1)).start();
		Runnable r2 = new CargarEmpresasDAT(DatosFicheros.this);
		(new Thread(r2)).start();
//		
	}


	@Override
	public void fin() {
		// TODO Auto-generated method stub
		properties.setProperty("count", Integer.toString(Usuario.getCount()));
		Runnable r1 = new GuardarPersonasDAT();
		(new Thread(r1)).start();
		Runnable r2 = new GuardarEmpresasDAT();
		(new Thread(r2)).start();
		
	}


	@Override
	public void anadirUsuarioPersona(Persona persona) {
		// TODO Auto-generated method stub
		String correo = persona.getCorreoElectronico();
		String telefono = persona.getTelefono();
		String contraseña = persona.getPassword();
		if (mapaEmailUsuario.keySet().contains(correo) | mapaTlfnoUsuario.keySet().contains(telefono)) {
//			System.out.println("Correo " + correo + " ; tlfno: " + telefono);
//			System.out.println(mapaEmailUsuario);
//			System.out.println(mapaTlfnoUsuario);
//			System.err.println("Error");
			//Lanzar aviso
			if (!test) {
				PnlRegistroPersona.lanzarAviso();
			}
			
		}else {			
			usuarios.add(persona);
			personas.add(persona);
			mapaIdUsuario.put(Integer.parseInt(persona.getId()+""), persona);
			mapaEmailUsuario.put(persona.getCorreoElectronico(), persona);
			mapaTlfnoUsuario.put(persona.getTelefono(), persona);
			mapaContraseñaUsuario.put(persona, contraseña);
			
		}
		
		
	}
	
	public void anadirUsuarioEmpresa( Empresa empresa ) {
		//TODO Auto-generated method stub
		String correo = empresa.getCorreoElectronico();
		String telefono = empresa.getTelefono();
		String contrasñea = empresa.getPassword();
		
		if(mapaEmailUsuario.keySet().contains(correo) | mapaTlfnoUsuario.keySet().contains(telefono)){
			if (!test) {
				JOptionPane.showOptionDialog(null, 
						"El correo electrónico o teléfono se encuentran asociados a otro usuario.", 
						"Error", 
						JOptionPane.DEFAULT_OPTION, 
						JOptionPane.INFORMATION_MESSAGE,
						null,
						new Object[] {"Aceptar"}, 
						"Aceptar");
			}

		}else {
			usuarios.add(empresa);
			empresas.add(empresa);
			mapaIdUsuario.put(Integer.parseInt(empresa.getId()+""), empresa);
			mapaEmailUsuario.put(empresa.getCorreoElectronico(), empresa);
			mapaTlfnoUsuario.put(empresa.getTelefono(), empresa);
			mapaContraseñaUsuario.put(empresa, contrasñea);
		}
		
		
	}
	
	
	public boolean autenticarUsuario(String correo, String contraseña) {
		return mapaEmailUsuario.containsKey(correo) && mapaEmailUsuario.get(correo).getPassword().equals(contraseña);
	}


	@Override
	public boolean containsTelefono(String telefono) {
		// TODO Auto-generated method stub
		return mapaTlfnoUsuario.containsKey(telefono);
	}


	@Override
	public boolean containsEmail(String email) {
		// TODO Auto-generated method stub
		return mapaEmailUsuario.containsKey(email);
	}


	
	
	
	
}
