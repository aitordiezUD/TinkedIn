package datos;

import java.util.TreeSet;

import clases.Mensaje;
import clases.PuestoTrabajo;
import sistemaExplorar.Like;
import usuarios.Empresa;
import usuarios.Persona;
import usuarios.Usuario;

/**
 * @author aitor
 *
 */
/**
 * @author aitor
 *
 */
public interface ManejoDatos {
	

	/**
	 * Proceso de inicialización de todos los datos del programa
	 */
	public void init();
	
	
	
	/**
	 * Proceso de finalización del programa donde guardaremos los datos necesarios
	 */
	public void fin();
	
	
	/**
	 * Añadir un nuevo Usuario de tipo Persona a la base de datos. Comprobaremos que los datos de usuario como 
	 * telefono o email no esten ya asignados a otros usuarios de nuestro sistema
	 * @param persona que queremos añadir al sistema
	 */
	public void anadirUsuarioPersona(Persona persona);
	
	
	/**
	 * Añadir un nuevo usuario de tipo Empresa a la base de datos. Comprobaremos que los datos de usuario como
	 * telefono o email no esten ya asignados a otros usuarios de nuestro sistema
	 * @param persona que queremos añadir al sistema
	 */
	public void anadirUsuarioEmpresa(Empresa empresa);
	
	/**A través de un correo electronico, obtener el usuario registrado con ese correo
	 * @param correo Correo perteneciente al usuario que queremos buscar
	 * @return el usuario que consta con el correo suministrado
	 */
	public Usuario getUsuarioFromCorreo(String correo);
	
	/**
	 *Devuelve true si el correo del usuario coincide con la contraseña del usuario
	 *@param correo que se indica, contraseña que se indica
	 */
	public boolean autenticarUsuario( String correo, String contraseña);
	
	
	
	/**Comprueba si el telefono esta en la base de datos
	 * @param telefono
	 */
	public boolean containsTelefono(String telefono);
	
	
	
	/**Comprueba si el telefono esta en la base de datos
	 * @param email
	 */
	public boolean containsEmail(String email);
	
	/**Funcion que añade un like a la base de datos
	 * @param from: Usuario que da like 
	 * @param to: Usuario que recibe like
	 */
	public void crearLike(Usuario from, Usuario to);
	
	/**Funcion que dado un like, comprueba si hay match entre los dos usuarios 
	 * @param like
	 */
	public void comprobarMatch(Like like);
	
	/**Funcion para añadir un puesto a una empresa (la empresa va como atributo del puesto)
	 * @param puesto PuestoDeTrabajo que queremos anadir
	 */
	public void anadirPuesto(PuestoTrabajo puesto);
	
	/**
	 * Función para eliminar todos los datos de la app. Pensado para introducir datos de prueba
	 */
	public void delete();

	/** Funcion para añadir un mensaje a los datos
	 * @param mensaje
	 */
	public void anadirMensaje(Mensaje mensaje);
	
	
	/**Función para que cuando se crean los paneles de los chats, poder obtener los mensajes donde ha interferido un Usuario (para posteriormente colocarlo en el chat)
	 * @param usuario Usuario del cual queremos obtener sus mensajes
	 */
	public TreeSet<Mensaje> filtrarMensajes(Usuario usuario);
}