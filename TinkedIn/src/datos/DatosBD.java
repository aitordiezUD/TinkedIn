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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.IntSummaryStatistics;
import java.util.Map;
import java.util.TreeSet;
import java.util.Vector;

import clases.Habilidad;
import clases.Mensaje;
import clases.PuestoTrabajo;
import nube.ImagenesAzure;
import sistemaExplorar.Like;
import sistemaExplorar.Match;
import usuarios.Empresa;
import usuarios.Persona;
import usuarios.Usuario;




public class DatosBD implements ManejoDatos {
	
	protected Connection connection;
	protected Statement statement;
	protected PreparedStatement prepStatement;
	
//	PRUEBAS TIEMPO:
	long tiempoInicio;
	long tiempoActual;
	long tiempoResultante;
	
	public DatosBD() {
		init();
	};
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		try {
			this.connection = DriverManager.getConnection(SQLCredentials.connectionString);
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
				System.out.println(fotoDePerfil);
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
				int idUbicacion;
				String ubicacion = null;
				Date nacimiento = null;
				ArrayList<Habilidad> habilidades = new ArrayList<Habilidad>();
				prepStatement = connection.prepareStatement(buscarPersona);
				prepStatement.setInt(1,id);
				ResultSet rsPersona = prepStatement.executeQuery();
				if (rsPersona.next()) {
					nombre = rsPersona.getString(2);
					apellidos = rsPersona.getString(3);
					java.sql.Date sqlDate = rsPersona.getDate(4);
					nacimiento = new Date(sqlDate.getTime());
					idUbicacion = rsPersona.getInt(5);
					ubicacion = getUbicacionFromId(idUbicacion);
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
//				OBTENER PUESTOS Y UBICACIONES
				puestos = crearPuestos(id);
				ubicaciones = crearUbicaciones(id);
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
		final String comprobarContraseña = "SELECT * FROM USUARIO WHERE CORREO = ? AND CONTRASENA = ? ";
		try {
			prepStatement = connection.prepareStatement(comprobarContraseña);
			prepStatement.setString(1, correo);
			prepStatement.setString(2, contraseña);
			ResultSet rs = prepStatement.executeQuery();
			if (rs.next()) {
//				rs.close();
//				prepStatement.close();
				return true;
			}
			rs.close();
			prepStatement.close();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean containsTelefono(String telefono) {
		// TODO Auto-generated method stub
		final String contieneTlf = "SELECT * FROM USUARIO WHERE 'TELEFONO' = ? ";
		try {
			prepStatement = connection.prepareStatement(contieneTlf);
			prepStatement.setString(1, contieneTlf);
			ResultSet rs = prepStatement.executeQuery();
			if (rs.next()) {
				rs.close();
				prepStatement.close();
				return true;
			}
			rs.close();
			prepStatement.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean containsEmail(String email) {
		// TODO Auto-generated method stub
		final String contieneEMail = "SELECT * FROM USUARIO WHERE 'TELEFONO' = ? ";
		try {
			prepStatement = connection.prepareStatement(contieneEMail);
			prepStatement.setString(1, email);
			ResultSet rs = prepStatement.executeQuery();
			if (rs.next()) {
				rs.close();
				prepStatement.close();
				return true;
			}
			rs.close();
			prepStatement.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public void crearLike(int from, int to) {
		// TODO Auto-generated method stub
		final String crearLike = "INSERT INTO LIKES VALUES(?,?)";
		try {
			prepStatement = connection.prepareStatement(crearLike);
			prepStatement.setInt( 1, (int) from );
			prepStatement.setInt( 2, (int) to );
			prepStatement.executeUpdate();
			prepStatement.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

	@Override
	public Match comprobarMatch(Like like) {
		// TODO Auto-generated method stub 
		int idFrom = (int) like.getFrom().getId();
		int idTo = (int)like.getTo().getId();
		crearLike(idFrom, idTo);
		final String comprobarLikeInverso = "SELECT * FROM LIKES WHERE TO_US = ? AND FROM_US = ?";
		final String anadirMatch = "INSERT INTO MATCHES VALUES(?,?) ";
		try {
			prepStatement = connection.prepareStatement(comprobarLikeInverso);
			System.out.println("ID FROM: " + idFrom);
			System.out.println("ID TO:" + idTo);
			prepStatement.setInt(1, idFrom);
			prepStatement.setInt(2, idTo);
			ResultSet rs = prepStatement.executeQuery();
			if(rs.next()) {
				System.out.println("Si que hay Match");
				prepStatement = connection.prepareStatement(anadirMatch);
				prepStatement.setInt(1, idFrom);
				prepStatement.setInt(2, idTo);
				prepStatement.executeUpdate();
				rs.close();
				prepStatement.close();
				return new Match(idFrom, idTo);
			}
			rs.close();
			prepStatement.close();
			return null;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error en comprobarMatch");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void anadirPuesto(PuestoTrabajo puesto) {
		// TODO Auto-generated method stub
		final String anadirPuesto = "INSERT INTO PUESTO_TRABAJO(ID_EMPRESA,NOMBRE,DESCRIPCION) VALUES(?,?,?)";
		final String anadirHabilidad = "INSERT INTO HABILIDAD(CAMPO,NOMBRE,DESTREZA,DESCRIPCION,ID_PUESTO) VALUES(?,?,?,?,?)";
		int clave;
		try {
			//INTRODUCCION DEL PUESTO DE TRABAJO EN SU TABLA
			prepStatement = connection.prepareStatement(anadirPuesto,Statement.RETURN_GENERATED_KEYS);
			prepStatement.setInt(1, (int) puesto.getIdEmpresa());
			prepStatement.setString(2, puesto.getNombre());
			prepStatement.setString(3, puesto.getDescripcion());
			prepStatement.executeUpdate();
			ResultSet claveGenerada =  prepStatement.getGeneratedKeys();
			if (claveGenerada.next()) {
				clave = claveGenerada.getInt(1);
			}else {
				claveGenerada.close();
				prepStatement.close();
				return;
			}
			claveGenerada.close();
			prepStatement.close();
			for (Habilidad h : puesto.getHabilidadesReq()) {
				prepStatement = connection.prepareStatement(anadirHabilidad);
				prepStatement.setString(1, h.getCampo());
				prepStatement.setString(2, h.getNombre());
				prepStatement.setInt(3, h.getDestreza());
				prepStatement.setString(4, h.getDescripcion());
				prepStatement.setInt(5, clave);
				prepStatement.executeUpdate();
				prepStatement.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		ImagenesAzure.deleteBlobsBd();
		String sql = "IF EXISTS (SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'MATCHES') DROP TABLE MATCHES;\r\n"
				+ "IF EXISTS (SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'MENSAJE') DROP TABLE MENSAJE;\r\n"
				+ "IF EXISTS (SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'LIKES') DROP TABLE LIKES;\r\n"
				+ "IF EXISTS (SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'HABILIDAD') DROP TABLE HABILIDAD;\r\n"
				+ "IF EXISTS (SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'PUESTO_TRABAJO') DROP TABLE PUESTO_TRABAJO;\r\n"
				+ "IF EXISTS (SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'UBICACION_EMPRESA') DROP TABLE UBICACION_EMPRESA;\r\n"
				+ "IF EXISTS (SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'PERSONA') DROP TABLE PERSONA;\r\n"
				+ "IF EXISTS (SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'EMPRESA') DROP TABLE EMPRESA;\r\n"
				+ "IF EXISTS (SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'USUARIO') DROP TABLE USUARIO;\r\n"
				+ "IF EXISTS (SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'UBICACION') DROP TABLE UBICACION;"
				+ "CREATE TABLE UBICACION(\r\n"
				+ "	ID INT PRIMARY KEY IDENTITY(0,1),\r\n"
				+ "	NOMBRE VARCHAR(255)\r\n"
				+ ");\r\n"
				+ "\r\n"
				+ "CREATE TABLE USUARIO (\r\n"
				+ "    ID INT PRIMARY KEY IDENTITY(0,1),\r\n"
				+ "    FOTO_PERFIL VARCHAR(255),\r\n"
				+ "    CONTRASENA VARCHAR(255),\r\n"
				+ "    TIPO VARCHAR(10),\r\n"
				+ "	CORREO VARCHAR(255),\r\n"
				+ "    TELEFONO VARCHAR(9),\r\n"
				+ "	UNIQUE (CORREO),\r\n"
				+ "	UNIQUE (TELEFONO)\r\n"
				+ "    \r\n"
				+ ");\r\n"
				+ "\r\n"
				+ "CREATE TABLE EMPRESA (\r\n"
				+ "    ID INT PRIMARY KEY,\r\n"
				+ "    NOMBRE VARCHAR(255),\r\n"
				+ "    DESCRIPCION TEXT,\r\n"
				+ "    FOREIGN KEY (ID) REFERENCES USUARIO(ID)\r\n"
				+ ");\r\n"
				+ "\r\n"
				+ "CREATE TABLE PERSONA (\r\n"
				+ "    ID INT PRIMARY KEY,\r\n"
				+ "    NOMBRE VARCHAR(255),\r\n"
				+ "    APELLIDOS VARCHAR(255),\r\n"
				+ "    NACIMIENTO DATE,\r\n"
				+ "    UBICACION INT,\r\n"
				+ "    FOREIGN KEY (ID) REFERENCES USUARIO(ID),\r\n"
				+ "	FOREIGN KEY (UBICACION) REFERENCES UBICACION(ID)\r\n"
				+ ");\r\n"
				+ "\r\n"
				+ "CREATE TABLE UBICACION_EMPRESA(\r\n"
				+ "	ID_EMPRESA INT NOT NULL,\r\n"
				+ "	ID_UBICACION INT NOT NULL,\r\n"
				+ "	PRIMARY KEY (ID_EMPRESA,ID_UBICACION),\r\n"
				+ "	FOREIGN KEY (ID_EMPRESA) REFERENCES EMPRESA(ID) ON DELETE CASCADE,\r\n"
				+ "	FOREIGN KEY (ID_UBICACION) REFERENCES UBICACION(ID)\r\n"
				+ ");\r\n"
				+ "\r\n"
				+ "CREATE TABLE PUESTO_TRABAJO (\r\n"
				+ "	ID INT PRIMARY KEY IDENTITY(0,1),\r\n"
				+ "	ID_EMPRESA INT,\r\n"
				+ "	NOMBRE VARCHAR(255),\r\n"
				+ "	DESCRIPCION TEXT,\r\n"
				+ "	FOREIGN KEY (ID_EMPRESA) REFERENCES EMPRESA(ID)\r\n"
				+ ");\r\n"
				+ "\r\n"
				+ "CREATE TABLE HABILIDAD (\r\n"
				+ "	ID INT PRIMARY KEY IDENTITY(0,1),\r\n"
				+ "	CAMPO VARCHAR(255),\r\n"
				+ "	NOMBRE VARCHAR(255),\r\n"
				+ "	DESTREZA NUMERIC(1),\r\n"
				+ "	DESCRIPCION TEXT,\r\n"
				+ "	ID_PERSONA INT DEFAULT NULL,\r\n"
				+ "	ID_PUESTO INT DEFAULT NULL,\r\n"
				+ "	FOREIGN KEY (ID_PERSONA) REFERENCES PERSONA(ID) ON DELETE CASCADE,\r\n"
				+ "	FOREIGN KEY (ID_PUESTO) REFERENCES PUESTO_TRABAJO(ID) ON DELETE CASCADE\r\n"
				+ ");\r\n"
				+ "\r\n"
				+ "CREATE TABLE LIKES (\r\n"
				+ "	FROM_US INT,\r\n"
				+ "	TO_US INT,\r\n"
				+ "	PRIMARY KEY (FROM_US,TO_US),\r\n"
				+ "	FOREIGN KEY (FROM_US) REFERENCES USUARIO(ID) ON DELETE CASCADE,\r\n"
				+ "	FOREIGN KEY (TO_US) REFERENCES USUARIO(ID) ON DELETE NO ACTION\r\n"
				+ ");\r\n"
				+ "\r\n"
				+ "CREATE TABLE MENSAJE(\r\n"
				+ " ID INT PRIMARY KEY IDENTITY(0,1),"
				+ "	FROM_US INT,\r\n"
				+ "	TO_US INT,\r\n"
				+ "	FECHA DATETIME,\r\n"
				+ "	MENSAJE_TEXTO TEXT,\r\n"
				+ "	FOREIGN KEY (FROM_US) REFERENCES USUARIO(ID) ON DELETE NO ACTION,\r\n"
				+ "	FOREIGN KEY (TO_US) REFERENCES USUARIO(ID) ON DELETE NO ACTION\r\n"
				+ ");\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "CREATE TABLE MATCHES (\r\n"
				+ "	USUARIO1 INT,\r\n"
				+ "	USUARIO2 INT,\r\n"
				+ "	PRIMARY KEY (USUARIO1,USUARIO2),\r\n"
				+ "	FOREIGN KEY (USUARIO1) REFERENCES USUARIO(ID) ON DELETE NO ACTION,\r\n"
				+ "	FOREIGN KEY (USUARIO2) REFERENCES USUARIO(ID) ON DELETE NO ACTION\r\n"
				+ ");";
		try {
			statement = connection.createStatement();
			statement.executeUpdate(sql);
			statement.close();
			System.out.println("Borrado finalizado");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void anadirMensaje(Mensaje mensaje) {
		// TODO Auto-generated method stub
		final String insertarMensaje = "INSERT INTO MENSAJE(FROM_US,TO_US,FECHA,MENSAJE_TEXTO) VALUES(?,?,?,?);";
		try {
			prepStatement = connection.prepareStatement(insertarMensaje);
			prepStatement.setInt(1, (int) mensaje.getFrom());
			prepStatement.setInt(2, (int) mensaje.getTo());
			java.sql.Timestamp sqlTimestamp = java.sql.Timestamp.valueOf(mensaje.getDate());
			prepStatement.setTimestamp(3, sqlTimestamp);
			prepStatement.setString(4, mensaje.getMensaje());
			prepStatement.executeUpdate();
			prepStatement.close();
			System.out.println(new Object[]{mensaje.getFrom(),mensaje.getTo(),sqlTimestamp,mensaje.getMensaje()});
			System.out.println("Mensaje anadido correctamente: " + mensaje);
		} catch (Exception e) {
			System.err.println("No se ha podido añadir el mensaje: " +  mensaje);
			e.printStackTrace();
		}
	}

	@Override
	public TreeSet<Mensaje> filtrarMensajes(Usuario usuario) {
		// TODO Auto-generated method stub
		final String buscarMensajes = "SELECT * FROM MENSAJE WHERE FROM_US = ? OR TO_US=? ORDER BY FECHA ASC";
		int idUsuario = (int) usuario.getId();
		TreeSet<Mensaje> set = new TreeSet<>();
		try {
			prepStatement = connection.prepareStatement(buscarMensajes);
			prepStatement.setInt(1, idUsuario);
			prepStatement.setInt(2, idUsuario);
			ResultSet rs = prepStatement.executeQuery();
			while(rs.next()) {
				java.sql.Timestamp sqlTimestamp = rs.getTimestamp(4);
				LocalDateTime fecha = sqlTimestamp.toLocalDateTime();
				Mensaje m = new Mensaje(rs.getInt(2), rs.getInt(3), rs.getString(5), fecha);
				set.add(m);
			}
			rs.close();
			prepStatement.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("Error: no se han podido obtener los mensajes filtrados");
			e.printStackTrace();
		}
		return set;
	}
	
	private int subirUbicacion(String ubicacion) {
		final String comprobarUbicacion = "SELECT ID FROM UBICACION WHERE NOMBRE = ?";
		final String crearUbicacion = "INSERT INTO UBICACION(NOMBRE) VALUES(?)";
		try {
			PreparedStatement psUbicacion = connection.prepareStatement(comprobarUbicacion);
			int idUbi;
			psUbicacion.setString(1, ubicacion);
			ResultSet rs = psUbicacion.executeQuery();
			if(rs.next()) {
				idUbi = rs.getInt(1);
				rs.close();
				psUbicacion.close();
				return idUbi;
			}else {
				rs.close();
				psUbicacion.close();
				psUbicacion = connection.prepareStatement(crearUbicacion, Statement.RETURN_GENERATED_KEYS);
				psUbicacion.setString(1, ubicacion);
				psUbicacion.executeUpdate();
				ResultSet generatedKeysUbis = psUbicacion.getGeneratedKeys();
				if (generatedKeysUbis.next()) {
					idUbi = generatedKeysUbis.getInt(1);
					generatedKeysUbis.close();
					psUbicacion.close();
					return idUbi;
				}else {
					generatedKeysUbis.close();
					return -1;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public Persona crearUsuarioPersona(String nombre, String apellidos, String ubicacion, Date nacimiento,String correoElectronico, 
			String telefeno, ArrayList<Habilidad> habilidades, File fotoDePerfil,String password) {
		// TODO Auto-generated method stub
		final String anadirUsuario = "INSERT INTO USUARIO(TIPO) VALUES (?)";
		final String actualizarUsuario = "UPDATE USUARIO SET FOTO_PERFIL = ?, CONTRASENA = ?, CORREO = ?, TELEFONO = ? WHERE ID = ?";
		final String anadirPersona = "INSERT INTO PERSONA VALUES(?, ?, ?, ?, ?)";
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
			java.sql.Date sqlDate = new java.sql.Date(nacimiento.getTime());
			prepStatement.setDate(4, sqlDate); //NACIMIENTO

			int idUbi = subirUbicacion(ubicacion);
			prepStatement.setInt(5, idUbi); //UBICACION
			prepStatement.executeUpdate();
			prepStatement.close();
//			INTRODUCCION DE HABILIDADES EN LA TABLA HABILIDAD
			for (Habilidad h : habilidades) {
//				System.out.println(h);
				prepStatement = connection.prepareStatement(anadirHabilidad);
				prepStatement.setString(1, h.getCampo()); //CAMPO
				prepStatement.setString(2, h.getNombre()); //NOMBRE
				prepStatement.setInt(3, h.getDestreza()); //DESTREZA
				prepStatement.setString(4, h.getDescripcion()); //DESCRIPCION
				prepStatement.setInt(5, id); //ID_PERSONA
				prepStatement.executeUpdate();
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
		final String anadirUbicacion = "INSERT INTO UBICACION_EMPRESA VALUES(?,?)";
		int id;
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
			String rutaImagen = ImagenesAzure.subirImagenBD(fotoDePerfil, id+".jpg");
			System.err.println("ruta imagen: " + rutaImagen);
			prepStatement.setString(1, rutaImagen);
			prepStatement.setString(2, password);
			prepStatement.setString(3, correo);
			prepStatement.setString(4, telefono);
			prepStatement.setInt(5, id);
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
				int idUbi = subirUbicacion(u);
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

	@Override
	public Usuario getUsuarioFromId(int id) {
		final String buscarUsuarios = "SELECT * FROM USUARIO WHERE ID = ?";
		final String buscarPersona = "SELECT * FROM PERSONA WHERE ID = ?";
		final String buscarHabilidadesPersona = "SELECT * FROM HABILIDAD WHERE ID_PERSONA = ?";
		final String buscarEmpresa = "SELECT * FROM EMPRESA WHERE ID = ?";
		String correo;
		String fotoDePerfil;
		String password;
		String tipo;
		String telefono;
		PreparedStatement prepStatementUsuarioId;
		try {
			prepStatementUsuarioId = connection.prepareStatement(buscarUsuarios);
			prepStatementUsuarioId.setInt(1, id);
			ResultSet rs = prepStatementUsuarioId.executeQuery();
			if (rs.next()) {
				fotoDePerfil = rs.getString(2);
				password = rs.getString(3);
				tipo = rs.getString(4);
				correo = rs.getString(5);
				telefono = rs.getString(6);
			}else {
				rs.close();
				prepStatementUsuarioId.close();
				return null;
			}
			rs.close();
			prepStatementUsuarioId.close();
			if (tipo.equals("PERSONA")) {
				String nombre = null;
				String apellidos = null;
				int idUbicacion;
				String ubicacion = null;
				Date nacimiento = null;
				ArrayList<Habilidad> habilidades = new ArrayList<Habilidad>();
				prepStatementUsuarioId = connection.prepareStatement(buscarPersona);
				prepStatementUsuarioId.setInt(1,id);
				ResultSet rsPersona = prepStatementUsuarioId.executeQuery();
				if (rsPersona.next()) {
					nombre = rsPersona.getString(2);
					apellidos = rsPersona.getString(3);
					java.sql.Date sqlDate = rsPersona.getDate(4);
					nacimiento = new Date(sqlDate.getTime());
					idUbicacion = rsPersona.getInt(5);
					ubicacion = getUbicacionFromId(idUbicacion);
				}
				rsPersona.close();
				prepStatementUsuarioId.close();
//				OBTENCION DEL ARRAYLIST DE HABILIDADES
				prepStatementUsuarioId = connection.prepareStatement(buscarHabilidadesPersona);
				prepStatementUsuarioId.setInt(1, id);
				ResultSet rsHabilidadesPersona = prepStatementUsuarioId.executeQuery();
				while (rsHabilidadesPersona.next()) {
					Habilidad h = new Habilidad(rsHabilidadesPersona.getString(2),rsHabilidadesPersona.getString(3),rsHabilidadesPersona.getInt(4),
							rsHabilidadesPersona.getString(5));
					habilidades.add(h);
				}
				rsHabilidadesPersona.close();
				prepStatementUsuarioId.close();
				return new Persona(id, nombre, apellidos, ubicacion, nacimiento, correo, telefono, habilidades, fotoDePerfil, password);
			}else {
//				OBTENER EMPRESA
				String nombre = null;
				String descripcion = null;
				ArrayList<String> ubicaciones = new ArrayList<String>();
				ArrayList<PuestoTrabajo> puestos = new ArrayList<PuestoTrabajo>();
				PreparedStatement prepStatementEmpr = connection.prepareStatement(buscarEmpresa);
				prepStatementEmpr.setInt(1, id);
				ResultSet rsEmpresa = prepStatementEmpr.executeQuery();
				if (rsEmpresa.next()) {
					nombre = rsEmpresa.getString(2);
					descripcion = rsEmpresa.getString(3);
				}
				rsEmpresa.close();
				prepStatementEmpr.close();
//				OBTENER PUESTOS Y UBICACIONES
				puestos = crearPuestos(id);
				ubicaciones = crearUbicaciones(id);
				return new Empresa(id, nombre, telefono, correo, descripcion, ubicaciones, puestos, fotoDePerfil, password);

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public Vector<Empresa> getEmpresas() {
		// TODO Auto-generated method stub
		Vector<Empresa> empresas = new Vector<>();
		final String selectEmpresas = "SELECT * FROM USUARIO U, EMPRESA E WHERE U.ID = E.ID";
		ResultSet rs = null;
		PreparedStatement prepStatementGetEmpresas = null;
		try {
//			RECORRER EMPRESAS:
			prepStatementGetEmpresas = connection.prepareStatement(selectEmpresas);
			rs = prepStatementGetEmpresas.executeQuery();
			while (rs.next()) {
				int id;
				String nombre;
				String descripcion;
				String correo;
				String fotoDePerfil;
				String password;
				String telefono;
				ArrayList<String> ubicaciones = new ArrayList<>();
				ArrayList<PuestoTrabajo> puestos = new ArrayList<>();
				
				id = rs.getInt(1);
				nombre = rs.getString(8);
				descripcion = rs.getString(9);
				telefono = rs.getString(6);
				correo = rs.getString(5);
				fotoDePerfil = rs.getString(2);
				password = rs.getString(3);
				puestos = crearPuestos(id);
				ubicaciones = crearUbicaciones(id);
				
				Empresa e = new Empresa(id, nombre, telefono, correo, descripcion, ubicaciones, puestos,
						fotoDePerfil, password);
				empresas.add(e);
			}
			rs.close();
			prepStatementGetEmpresas.close();
		
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
		    try {
		        if (rs != null) {
		            rs.close();
		        }
		        if (prepStatementGetEmpresas != null) {
		        	prepStatementGetEmpresas.close();
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		}

		return empresas;
		
	}
	
	@Override
	public Vector<Persona> getPersonas() {
		Vector<Persona> personas = new Vector<>();
		final String selectPersonas = "SELECT * FROM PERSONA P, USUARIO U WHERE P.ID = U.ID";
		PreparedStatement prepStatementGetPersonas;
		try {
			prepStatementGetPersonas = connection.prepareStatement(selectPersonas);
			tiempoInicio = System.currentTimeMillis();
			ResultSet rs = prepStatementGetPersonas.executeQuery();
			tiempoActual = System.currentTimeMillis();
			tiempoResultante = tiempoActual-tiempoInicio;
			
			while(rs.next()) {
				tiempoInicio = System.currentTimeMillis();
				int id = rs.getInt(1);
				String nombre = rs.getString(2);
				String apellidos = rs.getString(3);
				java.sql.Date sqlDate = rs.getDate(4);
				Date nacimiento = new Date(sqlDate.getTime());
				int idUbicacion = rs.getInt(5);
				String fotoDePerfil = rs.getString(7);
				String correo = rs.getString(10);
				String telefono = rs.getString(11);
				String password = rs.getString(8);
				String ubicacion = getUbicacionFromId(idUbicacion);
				ArrayList<Habilidad> habilidades = new ArrayList<>();

				tiempoActual = System.currentTimeMillis();
				tiempoResultante = tiempoActual - tiempoInicio;
//				System.out.println("Obtener atribs basicos: " + tiempoResultante);
				
				
				tiempoInicio = System.currentTimeMillis();
				habilidades = crearHabilidades(id);
				tiempoActual = System.currentTimeMillis();
				tiempoResultante = tiempoActual-tiempoInicio;
//				System.out.println("Crear habilidades: " + tiempoResultante);
				
				tiempoInicio = System.currentTimeMillis();
				Persona persona = new Persona(id, nombre, apellidos, ubicacion, nacimiento, correo, telefono, habilidades, fotoDePerfil, password);
				personas.add(persona);
				tiempoActual = System.currentTimeMillis();
				tiempoResultante = tiempoActual-tiempoInicio;
//				System.out.println("Tiempo Vector: " + tiempoResultante);
				
			}
			rs.close();
			prepStatementGetPersonas.close();
			return personas;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return personas;
		}
		
	}
	
	
	
	private String getUbicacionFromId(int id) {
		final String buscarUbi = "SELECT * FROM UBICACION WHERE ID = ?";
		String ubicacion = "";
		try {
			PreparedStatement psUbicacion = connection.prepareStatement(buscarUbi);
			psUbicacion.setInt(1, id);
			ResultSet rsUbicacion = psUbicacion.executeQuery();
			if (rsUbicacion.next()) {
				ubicacion = rsUbicacion.getString(2);
			}
			return ubicacion;
		} catch (Exception e) {
			e.printStackTrace();
			return ubicacion;
		}
	}
	
	private ArrayList<PuestoTrabajo> crearPuestos(int idEmpresa){
		final String buscarPuesto = "SELECT * FROM PUESTO_TRABAJO WHERE ID_EMPRESA = ?";
		final String buscarHabilidadesPuesto = "SELECT * FROM HABILIDAD WHERE ID_PUESTO = ?";
		
		ArrayList<PuestoTrabajo> puestos = new ArrayList<>();
		
		int idPuesto = -1;
		String nombrePuesto = null;
		String descripcionPuesto = null;
		
		try {
			PreparedStatement prepStatementPuestos = connection.prepareStatement(buscarPuesto);
			prepStatementPuestos.setInt(1, idEmpresa);
			ResultSet rsPuestos = prepStatementPuestos.executeQuery();
			while (rsPuestos.next()) {
				ArrayList<Habilidad> habilidadesPuesto = new ArrayList<Habilidad>();
				idPuesto = rsPuestos.getInt(1);
				nombrePuesto = rsPuestos.getString(3);
				descripcionPuesto = rsPuestos.getString(4);
				//BUSQUEDA DE LAS HABILIDADES DEL PUESTO ACUAL
				PreparedStatement busquedaHabilidad = connection.prepareStatement(buscarHabilidadesPuesto);
				busquedaHabilidad.setInt(1, idPuesto);
				ResultSet rsHabilidadesPuestos = busquedaHabilidad.executeQuery();
				while (rsHabilidadesPuestos.next()) {
					Habilidad h = new Habilidad(rsHabilidadesPuestos.getString(2),rsHabilidadesPuestos.getString(3),rsHabilidadesPuestos.getInt(4),
							rsHabilidadesPuestos.getString(5));
					habilidadesPuesto.add(h);
				}
				rsHabilidadesPuestos.close();
				busquedaHabilidad.close();
				PuestoTrabajo p = new PuestoTrabajo(nombrePuesto, descripcionPuesto, habilidadesPuesto, (long) idEmpresa);
				puestos.add(p);
			}
			rsPuestos.close();
			prepStatementPuestos.close();
			return puestos;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	private ArrayList<String> crearUbicaciones(int id){
		final String obtenerUbicacionEmpresa = "SELECT * FROM UBICACION_EMPRESA WHERE ID_EMPRESA = ?";
		
		ArrayList<String> ubicaciones = new ArrayList<>();
		try {
			PreparedStatement psUbicaciones = connection.prepareStatement(obtenerUbicacionEmpresa);
			psUbicaciones.setInt(1, id);
			ResultSet rsUbicaciones = psUbicaciones.executeQuery();
			while (rsUbicaciones.next()) {
				int idUbicacion = rsUbicaciones.getInt(2);
				String ubicacion = getUbicacionFromId(idUbicacion);
				ubicaciones.add(ubicacion);
			}
			rsUbicaciones.close();
			psUbicaciones.close();
			return ubicaciones;
		} catch (Exception e) {
			e.printStackTrace();
			return ubicaciones;
		}
	}
	
	private ArrayList<Habilidad> crearHabilidades(int id){
		ArrayList<Habilidad> habilidades = new ArrayList<>();
		final String buscarHabilidades = "SELECT * FROM HABILIDAD WHERE ID_PERSONA = ?";
		try {
			PreparedStatement psHabilidades = connection.prepareStatement(buscarHabilidades);
			psHabilidades.setInt(1, id);
			ResultSet rsHabilidades = psHabilidades.executeQuery();
			while(rsHabilidades.next()) {
				String campo = rsHabilidades.getString(2);
				String nombre = rsHabilidades.getString(3);
				int destreza = rsHabilidades.getInt(4);
				String descripcion = rsHabilidades.getString(5);
				Habilidad habilidad = new Habilidad(campo, nombre, destreza, descripcion);
				habilidades.add(habilidad);
			}
			return habilidades;
		} catch (Exception e) {
			e.printStackTrace();
			return habilidades;
		}
	}

	@Override
	public Vector<Usuario> getUsuarios() {
		Vector<Usuario> usuarios = new Vector<>();

        Thread threadEmpresas = new Thread(() -> {
            usuarios.addAll(new Vector<>(getEmpresas()));
        });

        Thread threadPersonas = new Thread(() -> {
            usuarios.addAll(new Vector<>(getPersonas()));
        });

        threadEmpresas.start();
        threadPersonas.start();

        try {
            threadEmpresas.join();
            threadPersonas.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return usuarios;
    }

	@Override
	public String getNombrePersonaFromId(int id) {
		final String queryNombre = "SELECT * FROM PERSONA WHERE ID = ?";
		String nombreApellidos = "";
		PreparedStatement prepStatementNombre;
		try {
			prepStatementNombre = connection.prepareStatement(queryNombre);
			prepStatementNombre.setInt(1, id);
			ResultSet rs = prepStatementNombre.executeQuery();
			if (rs.next()) {
				String nombre = rs.getString(2);
				String apellidos = rs.getString(3);
				nombreApellidos = nombre + " " + apellidos;
			}
			rs.close();
			prepStatementNombre.close();
			return nombreApellidos;
		} catch (SQLException e) {
			e.printStackTrace();
			return nombreApellidos;
		}
	}

	@Override
	public String getNombreEmpresaFromId(int id) {
		final String queryNombre = "SELECT * FROM EMPRESA WHERE ID = ?";
		String nombre = "";
		PreparedStatement prepStatementNombre;
		try {
			prepStatementNombre = connection.prepareStatement(queryNombre);
			prepStatementNombre.setInt(1, id);
			ResultSet rs = prepStatementNombre.executeQuery();
			if (rs.next()) {
				nombre = rs.getString(2);
			}
			rs.close();
			prepStatementNombre.close();
			return nombre;
		} catch (SQLException e) {
			e.printStackTrace();
			return nombre;
		}
	}

	@Override
	public Map<String, Integer> getFreHab(String campo) {
		// TODO Auto-generated method stub
		Map<String, Integer> mapaFreHab = new HashMap<String, Integer>();
		final String queryCampo = "SELECT NOMBRE,COUNT(*) AS CUENTA FROM HABILIDAD WHERE ID_PUESTO IS NULL AND CAMPO = ? GROUP BY NOMBRE";
		try {
			prepStatement = connection.prepareStatement(queryCampo);
			prepStatement.setString(1, campo);
			ResultSet rs = prepStatement.executeQuery();
			while(rs.next()) {
				int frecuencia = rs.getInt("CUENTA");
				String nombre = rs.getString("NOMBRE");
				mapaFreHab.put(nombre, frecuencia);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return mapaFreHab;
	}
	
	@Override
	public Map<String, Integer> getFreHabPues(String campo) {
		// TODO Auto-generated method stub
		Map<String, Integer> mapaFreHab = new HashMap<String, Integer>();
		final String queryCampo = "SELECT NOMBRE,COUNT(*) AS CUENTA FROM HABILIDAD WHERE ID_PERSONA IS NULL AND CAMPO = 'Química' GROUP BY NOMBRE;";
		try {
			prepStatement = connection.prepareStatement(queryCampo);
			prepStatement.setString(1, campo);
			ResultSet rs = prepStatement.executeQuery();
			while(rs.next()) {
				int frecuencia = rs.getInt("CUENTA");
				String nombre = rs.getString("NOMBRE");
				mapaFreHab.put(nombre, frecuencia);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return mapaFreHab;
	}
	
	@Override
	public Vector<Usuario> getUsuariosConMatch(int id) {
		final String querySQL = "SELECT * FROM MATCHES WHERE USUARIO1 = ? OR USUARIO2 = ?";
		PreparedStatement prepStatementMatch = null;
		Vector<Usuario> usuarios = new Vector<>();
		try {
			prepStatementMatch = connection.prepareStatement(querySQL);
			prepStatementMatch.setInt(1, id);
			prepStatementMatch.setInt(2, id);
			ResultSet rs = prepStatementMatch.executeQuery();
			while (rs.next()) {
				int id1 = rs.getInt(1);
				int id2 = rs.getInt(2);
				Usuario usuario = null;
				if (id == id1) {
					usuario = getUsuarioFromId(id2);
				}else {
					usuario = getUsuarioFromId(id1);
				}
				usuarios.add(usuario);
			}
			rs.close();
			prepStatementMatch.close();
			return usuarios;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public String getUrlImagenFromId(int id) {
		final String querySQL = "SELECT FOTO_PERFIL FROM USUARIO WHERE ID = ?";
		String url = "";
		PreparedStatement prepStatementUrl;
		try {
			prepStatementUrl = connection.prepareStatement(querySQL);
			prepStatementUrl.setInt(1, id);
			ResultSet rs = prepStatementUrl.executeQuery();
			if (rs.next()) {
				url = rs.getString(1);
			};
			rs.close();
			prepStatementUrl.close();
			return url;
		} catch (SQLException e) {
			e.printStackTrace();
			return url;
		}
	}

	@Override
	public void anadirHabilidad(Habilidad habilidad, long id) {
		// TODO Auto-generated method stub
		final String anadirHabilidad = "INSERT INTO HABILIDAD(CAMPO, NOMBRE, DESTREZA, DESCRIPCION, ID_PERSONA) VALUES(?,?,?,?,?)";
		PreparedStatement prepStatementHabilidad = null;
		try {
			prepStatementHabilidad = connection.prepareStatement(anadirHabilidad);
			prepStatementHabilidad.setString(1, habilidad.getCampo());
			prepStatementHabilidad.setString(2, habilidad.getNombre());
			prepStatementHabilidad.setInt(3, habilidad.getDestreza());
			prepStatementHabilidad.setString(4, habilidad.getDescripcion());
			prepStatementHabilidad.setInt(5, (int) id);
			prepStatementHabilidad.executeUpdate();
			prepStatementHabilidad.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void eliminarHabilidad(Habilidad habilidad, long id) {
		// TODO Auto-generated method stub
		final String eliminarHabilidad = "DELETE FROM HABILIDAD WHERE ID_PERSONA = ? AND NOMBRE = ?";
		PreparedStatement preparedStatementEliminarHab = null;
		try {
			preparedStatementEliminarHab = connection.prepareStatement(eliminarHabilidad);
			preparedStatementEliminarHab.setInt(1, (int) id);
			preparedStatementEliminarHab.setString(2,habilidad.getNombre());
			preparedStatementEliminarHab.executeUpdate();
			preparedStatementEliminarHab.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	@Override
	public void deletePuesto(int id, String nombre, String descripcion) {
		// TODO Auto-generated method stub
		final String updateSql = "DELETE FROM PUESTO_TRABAJO WHERE ID_EMPRESA = ? AND NOMBRE = ?";
		try {
			prepStatement = connection.prepareStatement(updateSql);
			prepStatement.setInt(1, id);
			prepStatement.setString(2, nombre);
			prepStatement.executeUpdate();
			prepStatement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void generarDatosPrueba() {
		// TODO Auto-generated method stub
		
	}
	
}
