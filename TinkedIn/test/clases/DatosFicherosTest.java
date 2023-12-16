package clases;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import datos.DatosFicheros;
import usuarios.Empresa;
import usuarios.Persona;
import ventanas.PnlRegistroPersona;

public class DatosFicherosTest {
	
	Persona p1;
	Persona p2;
	Persona p3;
	Persona p4;
	Empresa e1;
	
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
		
		p3 = new Persona("Inigo", "Eguiluz", "Bizkaia", 19, "i.pena@opendeusto.es",
				"688680738",new ArrayList<Habilidad>(), null, "passWd");
		
		p4 = new Persona("Mateo", "Perez", "Bizkaia", 19, "mateo.perez.suarez@opendeusto.es",
				"688958738",new ArrayList<Habilidad>(), null, "passWd");
		
		ArrayList<String> ubicaciones = new ArrayList<>();
		ubicaciones.add("Alava");
		ubicaciones.add("Madrid");
		ArrayList<PuestoTrabajo> puestos = null;
		
		e1 = new Empresa("Google", "673264634", "google@gmail.com", "",
				ubicaciones, puestos, null, "psswd") ;
		
		datos = new DatosFicheros();
		DatosFicheros.setTest(true);
		try {Thread.sleep(100);} catch (InterruptedException e) {e.printStackTrace();}
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void pruebaInicializacion() {
		assertEquals(1, DatosFicheros.empresas.size());
		assertEquals(1, DatosFicheros.personas.size());
	}
	
	
//	@Test
//	public void anadirEmpresa() {
//		datos.anadirUsuarioEmpresa(e1);
//		try {Thread.sleep(200);} catch (InterruptedException e) {e.printStackTrace();}
//		assertEquals(2, DatosFicheros.empresas.size());
//		datos.fin();
//	}
	
	
	
//	Probamos que los mapas se actualizen correctamente
	@Test
	public void anadirPersonas() {
		datos.anadirUsuarioPersona(p4);
		try {Thread.sleep(200);} catch (InterruptedException e) {e.printStackTrace();}
		System.out.println(DatosFicheros.personas);
		System.out.println(DatosFicheros.personas.size());
		assertEquals(2, DatosFicheros.personas.size());
//		Ahora el esperado es 3 ya que tambien se cuentan los usuarios de tipo Empresa
		assertEquals(3, DatosFicheros.mapaEmailUsuario.size());
		assertEquals(3, DatosFicheros.mapaTlfnoUsuario.size());
	}
//	
////	Comparaciones de correo
//	@Test
//	public void compararCorreos() {
//		assertEquals(true, p1.getCorreoElectronico().equals(p2.getCorreoElectronico()));
//		assertEquals(false, p3.getCorreoElectronico().equals(p2.getCorreoElectronico()));
//	}
//	
////	Comparaciones de correo
//	@Test
//	public void compararTelefonos() {
//		assertEquals(false, p1.getTelefono().equals(p2.getTelefono()));
//		assertEquals(true, p1.getTelefono().equals(p3.getTelefono()));
//	}
//	
////	Probamos que los mapas se actualizen correctamente
//	@Test
//	public void anadirUsuariosConDatosRepetidos() {
//		datos = new DatosFicheros();
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
