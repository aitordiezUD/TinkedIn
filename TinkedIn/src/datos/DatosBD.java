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
import java.util.IntSummaryStatistics;
import java.util.TreeSet;
import java.util.Vector;

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
	
	public DatosBD() {
		init();
	};
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		try {
			this.connection = DriverManager.getConnection(SQLCredentials.connectionString);
			System.out.println("Conexion establecida: " + connection);
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
//				int idPuesto = -1;
//				int idEmpresa = id;
//				String nombrePuesto = null;
//				String descripcionPuesto = null;
//				
//				prepStatement = connection.prepareStatement(buscarPuesto);
//				prepStatement.setInt(1, idEmpresa);
//				ResultSet rsPuestos = prepStatement.executeQuery();
//				while (rsPuestos.next()) {
//					ArrayList<Habilidad> habilidadesPuesto = new ArrayList<Habilidad>();
//					idPuesto = rsPuestos.getInt(1);
//					//BUSQUEDA DE LAS HABILIDADES DEL PUESTO ACUAL
//					PreparedStatement busquedaHabilidad = connection.prepareStatement(buscarHabilidadesPuesto);
//					busquedaHabilidad.setInt(1, idPuesto);
//					ResultSet rsHabilidadesPuestos = busquedaHabilidad.executeQuery();
//					while (rsHabilidadesPuestos.next()) {
//						Habilidad h = new Habilidad(rsHabilidadesPuestos.getString(2),rsHabilidadesPuestos.getString(3),rsHabilidadesPuestos.getInt(4),
//								rsHabilidadesPuestos.getString(5));
//						habilidadesPuesto.add(h);
//					}
//					PuestoTrabajo p = new PuestoTrabajo(nombrePuesto, descripcionPuesto, habilidadesPuesto, (long) idEmpresa);
//					puestos.add(p);
//				}
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
				rs.close();
				prepStatement.close();
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
	public void crearLike(Usuario from, Usuario to) {
		// TODO Auto-generated method stub
		final String crearLike = "INSERT INTO LIKES VALUES(?,?)";
		final long idFrom = from.getId();
		final long idTo = to.getId();
		try {
			prepStatement = connection.prepareStatement(crearLike);
			prepStatement.setInt( 1, (int) idFrom );
			prepStatement.setInt( 2, (int) idTo );
			prepStatement.executeUpdate();
			prepStatement.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

	@Override
	public boolean comprobarMatch(Like like) {
		// TODO Auto-generated method stub 
		int idFrom = (int) like.getFrom().getId();
		int idTo = (int)like.getTo().getId();
		final String comprobarLikeInverso = "SELECT * FROM LIKES WHERE 'TO_US' = ? AND 'FROM_US' = ? ";
		final String anadirMatch = "INSERT INTO MATCHES VALUES(?,?) ";
		try {
			prepStatement = connection.prepareStatement(comprobarLikeInverso);
			prepStatement.setInt(1, idFrom);
			prepStatement.setInt(2, idTo);
			ResultSet rs = prepStatement.executeQuery();
			
			if(rs.next()) {
				prepStatement = connection.prepareStatement(anadirMatch);
				prepStatement.setInt(1, idFrom);
				prepStatement.setInt(2, idTo);
				prepStatement.executeUpdate();
				rs.close();
				prepStatement.close();
				return true;
			}
			rs.close();
			prepStatement.close();
			return false;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public void anadirPuesto(PuestoTrabajo puesto) {
		// TODO Auto-generated method stub
		final String anadirPuesto = "INSERT INTO PUESTO_TRABAJO(ID_EMPRESA,NOMBRE,DESCRIPCION) VALUES(?,?,?)";
		final String anadirHabilidad = "INSERT INTO HABILIDAD(CAMPO,NOMBRE,DESTREZA,DESCRIPCION,ID_PUESTO) VALUES(?,?,?,?,?)";
		int clave;
		try {
			connection.setAutoCommit(false);
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
				+ "	FROM_US INT,\r\n"
				+ "	TO_US INT,\r\n"
				+ "	FECHA DATE,\r\n"
				+ "	MENSAJE_TEXTO TEXT,\r\n"
				+ "	PRIMARY KEY (FROM_US,TO_US,FECHA),\r\n"
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
		final String insertarMensaje = "INSERT INTO MENSAJE VALUES(?,?,?,?);";
		try {
			prepStatement = connection.prepareStatement(insertarMensaje);
			prepStatement.setInt(1, (int) mensaje.getFrom());
			prepStatement.setInt(2, (int) mensaje.getTo());
			java.sql.Date sqlDate = new java.sql.Date(mensaje.getDate().getTime());
			prepStatement.setDate(3, sqlDate);
			prepStatement.setString(4, mensaje.getMensaje());
			prepStatement.close();
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
				Mensaje m = new Mensaje(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getDate(4));
				set.add(m);
			}
			rs.close();
			prepStatement.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("Error: no se han podido obtener los mensajes filtrados");
		}
		return set;
	}
	
	private int subirUbicacion(String ubicacion) {
		final String comprobarUbicacion = "SELECT ID FROM UBICACION WHERE NOMBRE = ?";
		final String crearUbicacion = "INSERT INTO UBICACION(NOMBRE) VALUES(?)";
		try {
			System.err.println(ubicacion);
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
//		final String comprobarUbicacion = "SELECT ID FROM UBICACION WHERE NOMBRE = ?";
//		final String crearUbicacion = "INSERT INTO UBICACION(NOMBRE) VALUES(?)";
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

			
//			PreparedStatement psUbicacion = connection.prepareStatement(comprobarUbicacion);
//			int idUbi;
//			psUbicacion.setString(1, ubicacion);
//			ResultSet rs = psUbicacion.executeQuery();
//			if(rs.next()) {
//				idUbi = rs.getInt(1);
//				System.out.println("ID Ubicacion: " + idUbi);
//				rs.close();
//			}else {
//				rs.close();
//				psUbicacion = connection.prepareStatement(crearUbicacion, Statement.RETURN_GENERATED_KEYS);
//				psUbicacion.setString(1, ubicacion);
//				psUbicacion.executeUpdate();
//				ResultSet generatedKeysUbis = psUbicacion.getGeneratedKeys();
//				if (generatedKeysUbis.next()) {
//					idUbi = generatedKeysUbis.getInt(1);
//					generatedKeysUbis.close();
//				}else {
//					generatedKeysUbis.close();
//					return null;
//				}
//			}
			
			int idUbi = subirUbicacion(ubicacion);
			prepStatement.setInt(5, idUbi); //UBICACION
			prepStatement.executeUpdate();
			prepStatement.close();
//			INTRODUCCION DE HABILIDADES EN LA TABLA HABILIDAD
//			System.out.println("Añadiendo habilidades: ");
			for (Habilidad h : habilidades) {
				System.out.println(h);
				prepStatement = connection.prepareStatement(anadirHabilidad);
				prepStatement.setString(1, h.getCampo()); //CAMPO
				prepStatement.setString(2, h.getNombre()); //NOMBRE
				prepStatement.setInt(3, h.getDestreza()); //DESTREZA
				prepStatement.setString(4, h.getDescripcion()); //DESCRIPCION
				prepStatement.setInt(5, id); //ID_PERSONA
				prepStatement.executeUpdate();
				prepStatement.close();
			}
//			System.out.println("Habilidades añadidas");
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
//				prepStatement = connection.prepareStatement(comprobarUbicacion);
//				prepStatement.setString(1, u);
//				ResultSet rs = prepStatement.executeQuery();
//				if(rs.next()) {
//					idUbi = rs.getInt(1);
//					System.out.println("ID Ubi (Empresa): " + idUbi);
//					rs.close();
//				}else {
//					rs.close();
//					prepStatement = connection.prepareStatement(crearUbicacion, Statement.RETURN_GENERATED_KEYS);
//					prepStatement.setString(1, u);
//					prepStatement.executeUpdate();
//					ResultSet generatedKeysUbis = prepStatement.getGeneratedKeys();
//					if (generatedKeysUbis.next()) {
//						idUbi = generatedKeysUbis.getInt(1);
//						generatedKeysUbis.close();
//					}else {
//						generatedKeysUbis.close();
//						return null;
//					}
//				}
//				prepStatement.close();
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
//		final String buscarPuesto = "SELECT * FROM PUESTO_TRABAJO WHERE ID_EMPRESA = ?";
//		final String buscarHabilidadesPuesto = "SELECT * FROM PUESTO_TRABAJO WHERE ID_PUESTO = ?";
		String correo;
		String fotoDePerfil;
		String password;
		String tipo;
		String telefono;
		
		try {
			prepStatement = connection.prepareStatement(buscarUsuarios);
			prepStatement.setInt(1, id);
			ResultSet rs = prepStatement.executeQuery();
			if (rs.next()) {
				fotoDePerfil = rs.getString(2);
				password = rs.getString(3);
				tipo = rs.getString(4);
				correo = rs.getString(5);
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
//				int idPuesto = -1;
//				int idEmpresa = id;
//				String nombrePuesto = null;
//				String descripcionPuesto = null;
//				
//				prepStatement = connection.prepareStatement(buscarPuesto);
//				prepStatement.setInt(1, idEmpresa);
//				ResultSet rsPuestos = prepStatement.executeQuery();
//				while (rsPuestos.next()) {
//					ArrayList<Habilidad> habilidadesPuesto = new ArrayList<Habilidad>();
//					idPuesto = rsPuestos.getInt(1);
//					//BUSQUEDA DE LAS HABILIDADES DEL PUESTO ACUAL
//					PreparedStatement busquedaHabilidad = connection.prepareStatement(buscarHabilidadesPuesto);
//					busquedaHabilidad.setInt(1, idPuesto);
//					ResultSet rsHabilidadesPuestos = busquedaHabilidad.executeQuery();
//					while (rsHabilidadesPuestos.next()) {
//						Habilidad h = new Habilidad(rsHabilidadesPuestos.getString(2),rsHabilidadesPuestos.getString(3),rsHabilidadesPuestos.getInt(4),
//								rsHabilidadesPuestos.getString(5));
//						habilidadesPuesto.add(h);
//					}
//					PuestoTrabajo p = new PuestoTrabajo(nombrePuesto, descripcionPuesto, habilidadesPuesto, (long) idEmpresa);
//					puestos.add(p);
//				}
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
		final String selectUsuarios = "SELECT * FROM USUARIO WHERE ID = ?";
		final String selectEmpresas = "SELECT * FROM EMPRESA";
		try {
//			RECORRER EMPRESAS:
			prepStatement = connection.prepareStatement(selectEmpresas);
			ResultSet rs = prepStatement.executeQuery();
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
				nombre = rs.getString(2);
				descripcion = rs.getString(3);
				PreparedStatement prepStatementUsuario = connection.prepareStatement(selectUsuarios);
				prepStatementUsuario.setInt(1, id);
				ResultSet rsUsuario = prepStatementUsuario.executeQuery();
				if (rsUsuario.next()) {
					fotoDePerfil = rsUsuario.getString(2);
					password = rsUsuario.getString(3);
					correo = rsUsuario.getString(5);
					telefono = rsUsuario.getString(6);
				}else {
					return null;
				}
				rsUsuario.close();
				prepStatementUsuario.close();
				puestos = crearPuestos(id);
				ubicaciones = crearUbicaciones(id);
				Empresa e = new Empresa(id, nombre, telefono, correo, descripcion, ubicaciones, puestos,
						fotoDePerfil, password);
				empresas.add(e);
			}
			rs.close();
			prepStatement.close();
			return empresas;
		
		} catch (Exception e) {
			e.printStackTrace();
			return empresas;
		}
		
	}
	
	@Override
	public Vector<Persona> getPersonas() {
		Vector<Persona> personas = new Vector<>();
		final String selectUsuarios = "SELECT * FROM USUARIO WHERE ID = ?";
		final String selectPersonas = "SELECT * FROM PERSONA";
		try {
			prepStatement = connection.prepareStatement(selectPersonas);
			ResultSet rs = prepStatement.executeQuery();
			while(rs.next()) {
				int id = rs.getInt(1);
				String nombre = rs.getString(2);
				String apellidos = rs.getString(3);
				java.sql.Date sqlDate = rs.getDate(4);
				Date nacimiento = new Date(sqlDate.getTime());
				int idUbicacion = rs.getInt(5);
				String ubicacion = getUbicacionFromId(idUbicacion);
				String correo;
				String fotoDePerfil;
				String password;
				String telefono;
				ArrayList<Habilidad> habilidades = new ArrayList<>();
				PreparedStatement psUsuarios = connection.prepareStatement(selectUsuarios);
				psUsuarios.setInt(1, id);
				ResultSet rsUsuarios = psUsuarios.executeQuery();
				if(rsUsuarios.next()) {
					fotoDePerfil = rsUsuarios.getString(2);
					password = rsUsuarios.getString(3);
					correo = rs.getString(5);
					telefono = rsUsuarios.getString(6);
				}else {
					return personas;
				}
				rsUsuarios.close();
				psUsuarios.close();
				habilidades = crearHabilidades(id);
				Persona persona = new Persona(id, nombre, apellidos, ubicacion, nacimiento, correo, telefono, habilidades, fotoDePerfil, password);
				personas.add(persona);
			}
			prepStatement.close();
			rs.close();
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
		for (Empresa e : getEmpresas()) {
			usuarios.add(e);
		}
		for (Persona p : getPersonas()) {
			usuarios.add(p);
		}
		return usuarios;
	}


	
	public static void main(String[] args) {
		DatosBD datos = new DatosBD();
		System.out.println(datos.getEmpresas().get(2).getPuestos());
//		System.out.println(datos.getUsuarios());
		datos.fin();
	}



}
