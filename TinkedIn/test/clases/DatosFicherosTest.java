package clases;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ventanas.PnlRegistroPersona;

public class DatosFicherosTest {
	
	Persona p1;
	Persona p2;
	Persona p3;
	DatosFicheros datos;
	PnlRegistroPersona pnlRegistroPersona;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
		p1 = new Persona("Aitor", "Diez Mateo", "Alava", 19, "aitor.diez@opendeusto.es",
				"688680738",new ArrayList<Habilidad>(), null, "passWd");
		
		p2 = new Persona("Inigo", "Pena", "Bizkaia", 19, "aitor.diez@opendeusto.es",
				"675737647",new ArrayList<Habilidad>(), null, "sacapuntas");
		
		p3 = new Persona("Inigo", "Eguiluz", "Bizkaia", 19, "i.eguiluz@opendeusto.es",
				"688680738",new ArrayList<Habilidad>(), null, "passWd");
		
		datos = new DatosFicheros();
		
	}

	@After
	public void tearDown() throws Exception {
	}
	
	
	
//	@Test
//	public void pruebaInicializacion() {
//		datos = new DatosFicheros();
//		try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
//		System.out.println(datos.getPersonas());
//		
//		assertEquals(1, DatosFicheros.personas.size());
//	}
//	
//	Probamos que los mapas se actualizen correctamente
	@Test
	public void anadirUsuarios() {
		datos.anadirUsuarioPersona(p1);
		assertEquals(1, DatosFicheros.personas.size());
		assertEquals(1, DatosFicheros.mapaEmailUsuario.size());
		assertEquals(1, DatosFicheros.mapaTlfnoUsuario.size());
	}
	
//	Comparaciones de correo
//	@Test
//	public void compararCorreos() {
//		assertEquals(true, p1.getCorreoElectronico().equals(p2.getCorreoElectronico()));
//		assertEquals(false, p3.getCorreoElectronico().equals(p2.getCorreoElectronico()));
//	}
	
//	Comparaciones de correo
//	@Test
//	public void compararTelefonos() {
//		assertEquals(false, p1.getTelefono().equals(p2.getTelefono()));
//		assertEquals(true, p1.getTelefono().equals(p3.getTelefono()));
//	}
	
//	Probamos que los mapas se actualizen correctamente
//	@Test
//	public void anadirUsuariosConDatosRepetidos() {
//		datos.anadirUsuarioPersona(p1);
//		datos.anadirUsuarioPersona(p2);
//		assertEquals(1, DatosFicheros.personas.size());
//		assertEquals(1, DatosFicheros.mapaEmailUsuario.size());
//		assertEquals(1, DatosFicheros.mapaTlfnoUsuario.size());
//		datos.anadirUsuarioPersona(p3);
//		assertEquals(1, DatosFicheros.personas.size());
//		assertEquals(1, DatosFicheros.mapaEmailUsuario.size());
//		assertEquals(1, DatosFicheros.mapaTlfnoUsuario.size());
//	}

}
