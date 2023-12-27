package pruebas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import datos.SQLCredentials;

public class PruebaBD {
	protected static Connection connection;
	protected static Statement statement;
	static PreparedStatement prepStatement = null;
	
	
	public static void main(String[] args) throws SQLException {
		connection = DriverManager.getConnection(SQLCredentials.connectionString);
		pruebaUbicacion1();
		
		connection.close();
	}
	
	public static void pruebaUbicacion1() throws SQLException {
		prepStatement = connection.prepareStatement("SELECT ID FROM UBICACION WHERE NOMBRE = 'VITORIA'");
		ResultSet rs = prepStatement.executeQuery();
		if (rs == null) {
			System.out.println("null");
		}else {
			System.out.println(rs);
		}
		if (rs.next()) {
			System.out.println("true");
		}else {
			System.out.println("false");
		}
	}
	
	public void prueba1() throws SQLException {
		prepStatement = connection.prepareStatement("INSERT INTO USUARIO(FOTO_PERFIL,CONTRASENA,TIPO) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);
		prepStatement.setString(1, "erhhe");
		prepStatement.setString(2, "erg");
		prepStatement.setString(3, "PERSONA");
		int i = prepStatement.executeUpdate();
		
		try (ResultSet generatedKeys = prepStatement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                System.out.println(generatedKeys.getInt(1));
                System.out.println(i);
            } else {
                throw new SQLException("No se pudo obtener el ID generado para el usuario.");
            }
        }
	}
}
