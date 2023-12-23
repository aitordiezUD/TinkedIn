package servidor;

public class ConfigServer {
		// Configuración de conexión
		static String HOST = "localhost";  // IP de conexión para la comunicación del cliente hacia el servidor
		static int PUERTO = 4000;         // Puerto de conexión para cliente y servidor
	
		// Login correcto
		static String OK = "ok";  
		
		// Login incorrecto
		static String NO_OK = "no_ok";  
		
		// -----------------------------
		// Envío de cliente a servidor
		// (Obsérvese como hay correspondencia con cada una de las operaciones que necesita el servicio de persistencia)
		// -----------------------------
		
		// Login de inicio - va seguido de dos strings: USUARIO y CONTRASEÑA y el servidor lo responde con un OK o NO_OK
		static String LOGIN = "login";
		
		// Fin de comunicación - no se responde nada del servidor
		static String FIN = "fin";  
		
//		Envio de un mensaje - va seguido de un objeto mensaje
		static String ENVIO_MENSAJE = "envio_mensaje";
		
//		Búsqueda de un usuario partiendo de un correo
		static String GET_USUARIO_FROM_CORREO = "get_usuario_from_correo";
	
//		Edición de un usuario - va seguido de un usuario
		static String EDITAR_USUARIO = "editar_usuario";
		
//		Añade un usuario a los datos - va seguido de un usuario
		static String CREAR_USUARIO = "crear_usuario";
		
//		Comprueba si ya existe algún usuario con el correo dado al registrarse - va seguido de un correo
		static String COMPROBAR_CORREO = "comprobar_correo";
		
//		Comprueba si ya existe algún usuario con el telefono dado al registrarse - va seguido de un telefono
		static String COMPROBAR_TELEFONO = "comprobar_telefono";
		
//		Añade un puesto de trabajo a una empresa - va seguido de un puesto de trabajo
		static String ANADIR_PUESTO = "anadir_puesto";
		
//		Eliminar todas los datos de la aplicación
		static String DELETE = "delete";
		
//		Coloca todos los mensajes enviados al usuario mientras este estaba offline
		static String MENSAJES_PENDIENTES = "mensajes_pendientes";
		
//		Obtiene todas las empresas
		static String GET_EMPRESAS = "get_empresas";
		
//		Obteiene todas las personas
		static String GET_PERSONAS = "get_personas";
		
//		Obtiene todos los usuarios
		static String GET_USUARIOS = "get_usuarios";
		
//		Añade un mensaje a los datos - va seguido de un mensaje
		static String ANADIR_MENSAJE = "anadir_mensaje";
		

}
