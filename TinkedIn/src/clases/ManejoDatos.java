package clases;

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
	
}
