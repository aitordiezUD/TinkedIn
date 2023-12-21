package datos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.spi.FileSystemProvider;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import java.util.Vector;

import javax.swing.JOptionPane;

import clases.PuestoTrabajo;
import hilos.CargarEmpresasDAT;
import hilos.CargarPersonasDAT;
import hilos.GuardarEmpresasDAT;
import hilos.GuardarPersonasDAT;
import sistemaExplorar.Like;
import sistemaExplorar.Match;
import usuarios.Empresa;
import usuarios.Persona;
import usuarios.Usuario;
import ventanas.PnlRegistroPersona;

public class DatosFicheros implements ManejoDatos{
	
	protected static Vector<Usuario> usuarios;
	protected static Vector<Persona> personas;
	protected static Vector<Empresa> empresas;
	protected static HashMap<Integer, Usuario> mapaIdUsuario;
	protected static HashMap<String, Usuario> mapaEmailUsuario;
	protected static HashMap<String, Usuario> mapaTlfnoUsuario;
	protected static HashMap<Usuario, String> mapaContraseñaUsuario;
	protected static HashMap<Usuario, Vector<Like>> mapaLikesPorUsuario;
	protected static HashMap<Usuario, Vector<Usuario>> mapaMatchesDeUsuarios;
	protected static Vector<Match> matches; 
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
		mapaLikesPorUsuario = new HashMap<>();
		mapaMatchesDeUsuarios = new HashMap<>();
		matches = new Vector<>();
		properties = new Properties();
		
		try{
			InputStream input = new FileInputStream(new File("userCreation.properties"));
            properties.load(input);
        } catch (IOException e) {
        	System.err.println("Problema al leer properties");
            e.printStackTrace();
        }
		Usuario.setCount(Integer.parseInt(properties.getProperty("count")));
//		System.out.println("Set count usuarios a: " + Usuario.getCount());
		Runnable r1 = new CargarPersonasDAT(DatosFicheros.this);
		(new Thread(r1)).start();
		Runnable r2 = new CargarEmpresasDAT(DatosFicheros.this);
		(new Thread(r2)).start();
	}


	@Override
	public void fin() {
		// TODO Auto-generated method stub
//		System.out.println("Numero total de Usuarios: "  + Usuario.getCount()+ ""
//				+ " ; Guardando numero en properties");
		properties.setProperty("count", Usuario.getCount()+"");
		// Guardar los cambios en el archivo
        try (FileOutputStream fileOutput = new FileOutputStream("userCreation.properties")) {
            properties.store(fileOutput, null);
        } catch (IOException e) {
            e.printStackTrace();
        }

		
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
//			System.out.println(mapaIdUsuario);
//			System.out.println(mapaContraseñaUsuario);
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
//		System.out.println("Contiene correo? " + mapaEmailUsuario.containsKey(correo));
//		System.out.println("Bien contraseña? " + mapaEmailUsuario.get(correo).getPassword().equals(contraseña) );
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


	@Override
	public void crearLike(Usuario from, Usuario to) {
		// TODO Auto-generated method stub
		Like l = new Like(from, to);
		if (mapaLikesPorUsuario.get(from) == null) {
			mapaLikesPorUsuario.put(from, new Vector<>());
		}
		mapaLikesPorUsuario.get(from).add(l);
		
	}


	@Override
	public void comprobarMatch(Like like) {
		Usuario from = like.getFrom();
		Usuario to = like.getTo();
		
		Like likeInverso = new Like(to,from);
		
		if (mapaLikesPorUsuario.get(to).contains(likeInverso)) {
			if (mapaMatchesDeUsuarios.get(from) == null) {
				mapaMatchesDeUsuarios.put(from, new Vector<>());
			}
			if (mapaMatchesDeUsuarios.get(to) == null) {
				mapaMatchesDeUsuarios.put(to, new Vector<>());
			}
			
			mapaMatchesDeUsuarios.get(from).add(to);
			mapaMatchesDeUsuarios.get(to).add(from);
			Match m = new Match(from, to);
			matches.add(m);
		}
		
	}


	@Override
	public Usuario getUsuarioFromCorreo(String correo) {
		// TODO Auto-generated method stub
		return mapaEmailUsuario.get(correo);
	}


	@Override
	public void anadirPuesto(PuestoTrabajo puesto) {
		puesto.getEmpresaPertenece().getPuestos().add(puesto);
	}


	@Override
	public void delete() {
		// TODO Auto-generated method stub
		usuarios = new Vector<Usuario>();
		personas = new Vector<Persona>();
		empresas = new Vector<Empresa>();
		mapaIdUsuario = new HashMap<Integer, Usuario>();
		mapaEmailUsuario = new HashMap<String, Usuario>();
		mapaTlfnoUsuario = new HashMap<String, Usuario>();
		mapaContraseñaUsuario = new HashMap <Usuario, String>();
		mapaLikesPorUsuario = new HashMap<>();
		mapaMatchesDeUsuarios = new HashMap<>();
		matches = new Vector<>();
		properties = new Properties();
		
		try{
			InputStream input = new FileInputStream(new File("userCreation.properties"));
            properties.load(input);
        } catch (IOException e) {
        	System.err.println("Problema al leer properties");
            e.printStackTrace();
        }
		Usuario.setCount(Integer.parseInt(0+""));
	}
	
	

	
	
	
	
}
