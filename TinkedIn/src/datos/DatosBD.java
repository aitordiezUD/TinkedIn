package datos;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
		final String buscarUsuarios = "SELECT * FROM USUARIO WHERE 'CORREO' = ?";
		final String buscarPersona = "SELECT * FROM PERSONA WHERE 'ID' = ?";
		int id;
		String fotoDePerfil;
		String password;
		String tipo;
		
		try {
			prepStatement = connection.prepareStatement(buscarUsuarios);
			prepStatement.setString(1, correo);
			ResultSet rs = prepStatement.executeQuery();
			if (rs.next()) {
				id = rs.getInt(1);
				fotoDePerfil = rs.getString(2);
				password = rs.getString(3);
				tipo = rs.getString(4);
				
			}else {
				rs.close();
				prepStatement.close();
				return null;
			}
			rs.close();
			prepStatement.close();
			if (tipo.equals("PERSONA")) {
				prepStatement = connection.prepareStatement(buscarPersona);
				prepStatement.setId
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
		final String anadirPersona = "INSERT INTO PERSONA VALUES(?, ?, ?, ?, ?, ?, ?)";
		final String anadirHabilidad = "INSERT INTO HABILIDAD(CAMPO, NOMBRE, DESTREZA, DESCRIPCION, ID_PERSONA) VALUES(?,?,?,?,?)";
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
			prepStatement.executeUpdate();
			prepStatement.close();
//			INTRODUCCION DE USUARIO EN LA TABLA PERSONA
			prepStatement = connection.prepareStatement(anadirPersona);
			prepStatement.setInt(1, id); //ID
			prepStatement.setString(2, nombre); //NOMBRE
			prepStatement.setString(3, apellidos); //APELLIDOS
			prepStatement.setDate(4, (java.sql.Date) nacimiento); //NACIMIENTO
			prepStatement.setString(5, correoElectronico); //CORREO
			prepStatement.setString(6, telefeno); //TELEFONO
			prepStatement.setString(7, ubicacion); //UBICACION
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
				connection.setAutoCommit(true);
				connection.rollback();
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
		final String actualizarUsuario = "UPDATE USUARIO SET FOTO_PERFIL = ?, CONTRASENA = ? WHERE ID = ?";
		final String anadirEmpresa = "INSERT INTO EMPRESA VALUES(?, ?, ?, ?, ?)";
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
			prepStatement.executeUpdate();
			prepStatement.close();
//			INTRODUCCION DE USUARIO EN LA TABLA EMPRESA
			prepStatement = connection.prepareStatement(anadirEmpresa);
			prepStatement.setInt(1, id); //Id
			prepStatement.setString(2, nombre); //NOMBRE
			prepStatement.setString(3, telefono); //TELEFONO
			prepStatement.setString(4, correo); //CORREO
			prepStatement.setString(5, descripcion); //DESCRIPCION
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
					prepStatement = connection.prepareStatement(crearUbicacion, Statement.RETURN_GENERATED_KEYS);
					prepStatement.setString(1, u);
					prepStatement.executeUpdate();
					ResultSet generatedKeysUbis = prepStatement.getGeneratedKeys();
					if (generatedKeysUbis.next()) {
						idUbi = generatedKeysUbis.getInt(1);
						generatedKeysUbis.close();
					}else {
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

}
