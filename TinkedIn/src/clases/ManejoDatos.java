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
	 * Añadir un nuevo Usuario de tipo Persona a la base de datos
	 * @param persona
	 */
	public void anadirUsuarioPersona(Persona persona);
	
	
}
