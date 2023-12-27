package datos;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TreeSet;

import clases.Habilidad;
import clases.Mensaje;
import clases.PuestoTrabajo;
import nube.ImagenesAzure;
import sistemaExplorar.Like;
import usuarios.Empresa;
import usuarios.Persona;
import usuarios.Usuario;

public class DatosBD implements ManejoDatos {
	
	protected Connection connection;
	protected Statement statement;
	protected PreparedStatement prepStatement;
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		try {
			connection = DriverManager.getConnection(SQLCredentials.connectionString);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void fin() {
		// TODO Auto-generated method stub
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
		final String buscarUsuarios = "SELECT * FROM USUARIO WHERE CORREO = ?";
		final String buscarPersona = "SELECT * FROM PERSONA WHERE ID = ?";
		final String buscarHabilidadesPersona = "SELECT * FROM HABILIDAD WHERE ID_PERSONA = ?";
		final String buscarEmpresa = "SELECT * FROM EMPRESA WHERE ID = ?";
		final String buscarPuesto = "SELECT * FROM PUESTO_TRABAJO WHERE ID_EMPRESA = ?";
		final String buscarHabilidadesPuesto = "SELECT * FROM PUESTO_TRABAJO WHERE ID_PUESTO = ?";
		int id;
		String fotoDePerfil;
		String password;
		String tipo;
		String telefono;
		
		try {
			prepStatement = connection.prepareStatement(buscarUsuarios);
			prepStatement.setString(1, correo);
			ResultSet rs = prepStatement.executeQuery();
			if (rs.next()) {
				id = rs.getInt(1);
				fotoDePerfil = rs.getString(2);
				password = rs.getString(3);
				tipo = rs.getString(4);
				telefono = rs.getString(6);
			}else {
				rs.close();
				prepStatement.close();
				return null;
			}
			rs.close();
			prepStatement.close();
			if (tipo.equals("PERSONA")) {
				String nombre = null;
				String apellidos = null;
				String ubicacion = null;
				Date nacimiento = null;
				ArrayList<Habilidad> habilidades = new ArrayList<Habilidad>();
				prepStatement = connection.prepareStatement(buscarPersona);
				prepStatement.setInt(1,id);
				ResultSet rsPersona = prepStatement.executeQuery();
				if (rsPersona.next()) {
					nombre = rsPersona.getString(2);
					apellidos = rsPersona.getString(3);
					nacimiento =  rsPersona.getDate(4);
					ubicacion = rsPersona.getString(5);
				}
				rsPersona.close();
				prepStatement.close();
//				OBTENCION DEL ARRAYLIST DE HABILIDADES
				prepStatement = connection.prepareStatement(buscarHabilidadesPersona);
				prepStatement.setInt(1, id);
				ResultSet rsHabilidadesPersona = prepStatement.executeQuery();
				while (rsHabilidadesPersona.next()) {
					Habilidad h = new Habilidad(rsHabilidadesPersona.getString(2),rsHabilidadesPersona.getString(3),rsHabilidadesPersona.getInt(4),
							rsHabilidadesPersona.getString(5));
					habilidades.add(h);
				}
				rsHabilidadesPersona.close();
				prepStatement.close();
				return new Persona(id, nombre, apellidos, ubicacion, nacimiento, correo, telefono, habilidades, fotoDePerfil, password);
			}else {
//				OBTENER EMPRESA
				String nombre = null;
				String descripcion = null;
				ArrayList<String> ubicaciones = new ArrayList<String>();
				ArrayList<PuestoTrabajo> puestos = new ArrayList<PuestoTrabajo>();
				prepStatement = connection.prepareStatement(buscarEmpresa);
				prepStatement.setInt(1, id);
				ResultSet rsEmpresa = prepStatement.executeQuery();
				if (rsEmpresa.next()) {
					nombre = rsEmpresa.getString(2);
					descripcion = rsEmpresa.getString(3);
				}
				rsEmpresa.close();
				prepStatement.close();
//				OBTENER PUESTOS
				int idPuesto = -1;
				int idEmpresa = id;
				String nombrePuesto = null;
				String descripcionPuesto = null;
				
				prepStatement = connection.prepareStatement(buscarPuesto);
				prepStatement.setInt(1, idEmpresa);
				ResultSet rsPuestos = prepStatement.executeQuery();
				while (rsPuestos.next()) {
					ArrayList<Habilidad> habilidadesPuesto = new ArrayList<Habilidad>();
					idPuesto = rsPuestos.getInt(1);
					//BUSQUEDA DE LAS HABILIDADES DEL PUESTO ACUAL
					PreparedStatement busquedaHabilidad = connection.prepareStatement(buscarHabilidadesPuesto);
					busquedaHabilidad.setInt(1, idPuesto);
					ResultSet rsHabilidadesPuestos = busquedaHabilidad.executeQuery();
					while (rsHabilidadesPuestos.next()) {
						Habilidad h = new Habilidad(rsHabilidadesPuestos.getString(2),rsHabilidadesPuestos.getString(3),rsHabilidadesPuestos.getInt(4),
								rsHabilidadesPuestos.getString(5));
						habilidadesPuesto.add(h);
					}
					PuestoTrabajo p = new PuestoTrabajo(nombrePuesto, descripcionPuesto, habilidadesPuesto, (long) idEmpresa);
					puestos.add(p);
				}
				return new Empresa(id, nombre, telefono, correo, descripcion, ubicaciones, puestos, fotoDePerfil, password);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
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

	@Override
	public Persona crearUsuarioPersona(String nombre, String apellidos, String ubicacion, Date nacimiento,String correoElectronico, 
			String telefeno, ArrayList<Habilidad> habilidades, File fotoDePerfil,String password) {
		// TODO Auto-generated method stub
		final String anadirUsuario = "INSERT INTO USUARIO(TIPO) VALUES (?)";
		final String actualizarUsuario = "UPDATE USUARIO SET FOTO_PERFIL = ?, CONTRASENA = ?, CORREO = ?, TELEFONO = ? WHERE ID = ?";
		final String anadirPersona = "INSERT INTO PERSONA VALUES(?, ?, ?, ?, ?)";
		final String anadirHabilidad = "INSERT INTO HABILIDAD(CAMPO, NOMBRE, DESTREZA, DESCRIPCION, ID_PERSONA) VALUES(?,?,?,?,?)";
		final String comprobarUbicacion = "SELECT ID FROM UBICACION WHERE 'NOMBRE' = ?";
		final String anadirUbicacion = "INSERT INTO UBICACION_EMPRESA VALUES(?,?)";
		final String crearUbicacion = "INSERT INTO UBICACION(NOMBRE) VALUES(?)";
		int id;
		try {
			connection.setAutoCommit(false);
//			INTRODUCCION DEL USUARIO EN LA TABLA USUARIO
			prepStatement = connection.prepareStatement(anadirUsuario, Statement.RETURN_GENERATED_KEYS);
			prepStatement.setString(1, "PERSONA");
			prepStatement.executeUpdate();
			ResultSet generatedKeys = prepStatement.getGeneratedKeys();
			if (generatedKeys.next()) {
				id = generatedKeys.getInt(1);
			}else {
				return null;
			}
			generatedKeys.close();
			prepStatement.close();
//			ACTUALIZACION DE DATOS
			prepStatement = connection.prepareStatement(actualizarUsuario);
//			new ImagenesAzure();
			String rutaImagen = ImagenesAzure.subirImagenBD(fotoDePerfil, id+".jpg");
			prepStatement.setString(1, rutaImagen);
			prepStatement.setString(2, password);
			prepStatement.setString(3, correoElectronico);
			prepStatement.setString(4, telefeno);
			prepStatement.setInt(5, id);
			prepStatement.executeUpdate();
			prepStatement.close();
//			INTRODUCCION DE USUARIO EN LA TABLA PERSONA
			prepStatement = connection.prepareStatement(anadirPersona);
			prepStatement.setInt(1, id); //ID
			prepStatement.setString(2, nombre); //NOMBRE
			prepStatement.setString(3, apellidos); //APELLIDOS
			prepStatement.setString(4, nacimiento.toString()); //NACIMIENTO

			
			PreparedStatement psUbicacion = connection.prepareStatement(comprobarUbicacion);
			int idUbi;
			psUbicacion.setString(1, ubicacion);
			ResultSet rs = psUbicacion.executeQuery();
			if(rs.next()) {
				idUbi = rs.getInt(1);
				rs.close();
			}else {
				rs.close();
				psUbicacion = connection.prepareStatement(crearUbicacion, Statement.RETURN_GENERATED_KEYS);
				psUbicacion.setString(1, ubicacion);
				psUbicacion.executeUpdate();
				ResultSet generatedKeysUbis = psUbicacion.getGeneratedKeys();
				if (generatedKeysUbis.next()) {
					idUbi = generatedKeysUbis.getInt(1);
					generatedKeysUbis.close();
				}else {
					generatedKeysUbis.close();
					return null;
				}
			}
			prepStatement.setInt(5, idUbi); //UBICACION
			prepStatement.executeUpdate();
			prepStatement.close();
//			INTRODUCCION DE HABILIDADES EN LA TABLA HABILIDAD
			for (Habilidad h : habilidades) {
				prepStatement = connection.prepareStatement(anadirHabilidad);
				prepStatement.setString(1, h.getCampo()); //CAMPO
				prepStatement.setString(2, h.getNombre()); //NOMBRE
				prepStatement.setInt(3, h.getDestreza()); //DESTREZA
				prepStatement.setString(4, h.getDescripcion()); //DESCRIPCION
				prepStatement.setInt(5, id); //ID_PERSONA
				prepStatement.close();
			}
			connection.commit();
			connection.setAutoCommit(true);
			return new Persona(id, nombre, apellidos, ubicacion, nacimiento, correoElectronico, telefeno, habilidades, rutaImagen, password);
		} catch (Exception e) {
			try {
				connection.rollback();
				connection.setAutoCommit(true);
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		    e.printStackTrace();
		    return null;
		}
	}

	@Override
	public Empresa crearUsuarioEmpresa(String nombre, String telefono, String correo, String descripcion,
			ArrayList<String> ubicaciones, File fotoDePerfil, String password) {
		// TODO Auto-generated method stub
		final String anadirUsuario = "INSERT INTO USUARIO(TIPO) VALUES (?)";
		final String actualizarUsuario = "UPDATE USUARIO SET FOTO_PERFIL = ?, CONTRASENA = ?, CORREO = ?, TELEFONO = ? WHERE ID = ?";
		final String anadirEmpresa = "INSERT INTO EMPRESA VALUES(?, ?, ?)";
		final String comprobarUbicacion = "SELECT ID FROM UBICACION WHERE 'NOMBRE' = ?";
		final String anadirUbicacion = "INSERT INTO UBICACION_EMPRESA VALUES(?,?)";
		final String crearUbicacion = "INSERT INTO UBICACION(NOMBRE) VALUES(?)";
		int id;
		int idUbi;
		try {
			connection.setAutoCommit(false);
//			INTRODUCCION DEL USUARIO EN LA TABLA USUARIO
			prepStatement = connection.prepareStatement(anadirUsuario, Statement.RETURN_GENERATED_KEYS);
			prepStatement.setString(1, "EMPRESA");
			prepStatement.executeUpdate();
			ResultSet generatedKeys = prepStatement.getGeneratedKeys();
			if (generatedKeys.next()) {
				id = generatedKeys.getInt(1);
			}else {
				return null;
			}
			generatedKeys.close();
			prepStatement.close();
//			ACTUALIZACION DE DATOS
			prepStatement = connection.prepareStatement(actualizarUsuario);
//			new ImagenesAzure();
			String rutaImagen = ImagenesAzure.subirImagenBD(fotoDePerfil, id+".jpg");
			prepStatement.setString(1, rutaImagen);
			prepStatement.setString(2, password);
			prepStatement.setString(3, correo);
			prepStatement.setString(4, telefono);
			prepStatement.executeUpdate();
			prepStatement.close();
//			INTRODUCCION DE USUARIO EN LA TABLA EMPRESA
			prepStatement = connection.prepareStatement(anadirEmpresa);
			prepStatement.setInt(1, id); //Id
			prepStatement.setString(2, nombre); //NOMBRE
			prepStatement.setString(3, descripcion); //DESCRIPCION
			prepStatement.executeUpdate();
			prepStatement.close();
//			INTRODUCCION DE UBICACIONES EN LA TABLA UBICACION_EMPRESA
			for (String u: ubicaciones) {
				prepStatement = connection.prepareStatement(comprobarUbicacion);
				prepStatement.setString(1, u);
				ResultSet rs = prepStatement.executeQuery();
				if(rs.next()) {
					idUbi = rs.getInt(1);
					rs.close();
				}else {
					rs.close();
					prepStatement = connection.prepareStatement(crearUbicacion, Statement.RETURN_GENERATED_KEYS);
					prepStatement.setString(1, u);
					prepStatement.executeUpdate();
					ResultSet generatedKeysUbis = prepStatement.getGeneratedKeys();
					if (generatedKeysUbis.next()) {
						idUbi = generatedKeysUbis.getInt(1);
						generatedKeysUbis.close();
					}else {
						generatedKeysUbis.close();
						return null;
					}
				}
				prepStatement.close();
				prepStatement = connection.prepareStatement(anadirUbicacion);
				prepStatement.setInt(1, id);
				prepStatement.setInt(2,idUbi);
				prepStatement.executeUpdate();
			}
			connection.commit();
			connection.setAutoCommit(true);
			return new Empresa(id, nombre, telefono, correo, descripcion, ubicaciones, rutaImagen, password);
		}catch (Exception e) {
			try {
				connection.rollback();
				connection.setAutoCommit(true);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		    e.printStackTrace();
		    return null;
		}
	}
	
	public static void main(String[] args) {
		DatosBD datos = new DatosBD();
		datos.init();
		Date fecha;
		try {
			fecha = new SimpleDateFormat("yyyy-MM-dd").parse("2000-01-01");
		} catch (ParseException exc) {
			// TODO Auto-generated catch block
			fecha = null;
			exc.printStackTrace();
		}
		Persona p = datos.crearUsuarioPersona("admin", "admin", "Alava",fecha ,"admin","admin", new ArrayList<Habilidad>(),new File("adminpng.png"),"admin");
		System.out.println(p);
		datos.fin();
	}

}
