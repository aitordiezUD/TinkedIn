package clases;

import java.io.File;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import datos.DatosFicheros;
import usuarios.Empresa;
import usuarios.Persona;
import ventanas.PnlExplorar;

public class Main {

	public static void main(String[] args) {
		
		DatosFicheros datos = new DatosFicheros();
//		Persona p1 = new Persona("Aitor", "Diez Mateo", "Alava", 19, "aitor.diez@opendeusto.es",
//				"688680738",new ArrayList<Habilidad>(), null, "passWd");
//		Empresa e1 = new Empresa("Google", "673264634", "google@gmail.com", "",
//				null, null, null, "psswd") ;
        Habilidad h1 = new Habilidad("Informática", "Desarrollo Web", 4, "Experiencia en desarrollo de sitios web");
        Habilidad h2 = new Habilidad("Arte y Diseño", "Diseño Gráfico", 5, "Diseño creativo y conceptualización");
        Habilidad h3 = new Habilidad("Deporte", "Entrenador Personal", 3, "Entrenamiento personalizado");
        Habilidad h4 = new Habilidad("Psicología", "Psicología Clínica", 4, "Evaluación y tratamiento psicológico");
        Habilidad h5 = new Habilidad("Marketing", "Marketing Digital", 5, "Estrategias de marketing online");
        Habilidad h6 = new Habilidad("Finanzas", "Banca y Finanzas", 3, "Gestión financiera y análisis");
        Habilidad h7 = new Habilidad("Educación", "Docencia", 4, "Enseñanza y planificación de clases");
        Habilidad h8 = new Habilidad("Medicina", "Medicina General", 5, "Práctica médica general");
        Habilidad h9 = new Habilidad("Ciencias de la Salud", "Enfermería", 4, "Cuidado de pacientes y gestión de salud");
        Habilidad h10 = new Habilidad("Ingeniería", "Ingeniería Civil", 3, "Diseño y construcción de estructuras");
        Habilidad h11 = new Habilidad("Ventas", "Ventas Directas", 5, "Negociación y cierre de ventas");
        Habilidad h12 = new Habilidad("Servicio al Cliente", "Atención al Cliente", 4, "Resolución de problemas y satisfacción del cliente");
        Habilidad h13 = new Habilidad("Ciencias Sociales", "Trabajo Social", 3, "Trabajo social comunitario");
        Habilidad h14 = new Habilidad("Ciencias Ambientales", "Gestión Ambiental", 4, "Preservación del medio ambiente");
        Habilidad h15 = new Habilidad("Arquitectura", "Diseño de Edificios", 5, "Diseño arquitectónico innovador");
        Habilidad h16 = new Habilidad("Turismo y Hospitalidad", "Gestión Hotelera", 3, "Administración hotelera");
        Habilidad h17 = new Habilidad("Agricultura", "Agronomía", 4, "Cultivo y gestión agrícola");
        Habilidad h18 = new Habilidad("Derecho", "Derecho Penal", 5, "Asesoramiento legal en casos penales");
        Habilidad h19 = new Habilidad("Comunicación", "Periodismo", 3, "Reportaje y redacción periodística");
        Habilidad h20 = new Habilidad("Periodismo", "Periodismo de Investigación", 4, "Investigación a fondo de temas relevantes");
        Habilidad h21 = new Habilidad("Música", "Interpretación Musical", 5, "Interpretación musical en vivo");
        Habilidad h22 = new Habilidad("Investigación y Desarrollo", "Investigación Científica", 3, "Conducción de experimentos y análisis de datos");
        Habilidad h23 = new Habilidad("Construcción", "Ingeniería Civil", 4, "Gestión de proyectos de construcción");
        Habilidad h24 = new Habilidad("Mantenimiento", "Mantenimiento de Edificios", 5, "Mantenimiento preventivo y correctivo");
        Habilidad h25 = new Habilidad("Logística", "Gestión de la Cadena de Suministro", 3, "Optimización de la cadena de suministro");
        Habilidad h26 = new Habilidad("Transporte", "Transporte Terrestre", 4, "Logística y gestión de transporte terrestre");
        Habilidad h27 = new Habilidad("Alimentación y Hostelería", "Restauración", 5, "Gestión de restaurantes y cocina");
        Habilidad h28 = new Habilidad("Moda", "Diseño de Moda", 3, "Diseño de moda y tendencias");
        Habilidad h29 = new Habilidad("Gobierno y Política", "Administración Pública", 4, "Gestión administrativa gubernamental");
        Habilidad h30 = new Habilidad("Organizaciones sin ánimo de lucro", "Gestión de ONGs", 5, "Administración de organizaciones benéficas");
        Habilidad h31 = new Habilidad("Relaciones Públicas", "Relaciones con los Medios", 3, "Gestión de relaciones con los medios");
        Habilidad h32 = new Habilidad("Seguridad", "Seguridad Informática", 4, "Protección de sistemas y datos");
        Habilidad h33 = new Habilidad("Energía y Recursos Naturales", "Energía Renovable", 5, "Desarrollo de fuentes de energía sostenible");
        Habilidad h34 = new Habilidad("Ciencia de Datos", "Análisis de Datos", 3, "Análisis y visualización de datos");
        Habilidad h35 = new Habilidad("Informática", "Machine Learning", 4, "Implementación de algoritmos de aprendizaje automático");
        Habilidad h36 = new Habilidad("Arte y Diseño", "Arquitectura de Interiores", 5, "Diseño de interiores creativo");
        Habilidad h37 = new Habilidad("Deporte", "Fisioterapia Deportiva", 3, "Rehabilitación física para atletas");
        Habilidad h38 = new Habilidad("Psicología", "Psicología Educativa", 4, "Evaluación y asesoramiento psicológico en entornos educativos");
        Habilidad h39 = new Habilidad("Marketing", "Gestión de Marcas", 5, "Desarrollo y posicionamiento de marcas");
        Habilidad h40 = new Habilidad("Finanzas", "Contabilidad", 3, "Registros financieros y análisis contable");
        Habilidad h41 = new Habilidad("Educación", "Educación Especial", 4, "Enseñanza adaptada a necesidades especiales");
        Habilidad h42 = new Habilidad("Medicina", "Cirugía", 5, "Realización de procedimientos quirúrgicos");
        Habilidad h43 = new Habilidad("Ciencias de la Salud", "Fisioterapia", 3, "Rehabilitación física y terapia");
        Habilidad h44 = new Habilidad("Ingeniería", "Ingeniería Eléctrica", 4, "Diseño y mantenimiento de sistemas eléctricos");
        Habilidad h45 = new Habilidad("Ventas", "Ventas en Línea", 5, "Comercialización de productos en plataformas en línea");
        Habilidad h46 = new Habilidad("Servicio al Cliente", "Soporte Técnico", 3, "Asistencia técnica y resolución de problemas");
        Habilidad h47 = new Habilidad("Ciencias Sociales", "Psicología Social", 4, "Estudio de comportamiento social");
        Habilidad h48 = new Habilidad("Ciencias Ambientales", "Conservación de la Naturaleza", 5, "Preservación y gestión de la biodiversidad");
        Habilidad h49 = new Habilidad("Arquitectura", "Urbanismo", 3, "Planificación urbana y diseño de espacios");
        Habilidad h50 = new Habilidad("Turismo y Hospitalidad", "Agencia de Viajes", 4, "Organización de viajes y experiencias turísticas");
        Habilidad h51 = new Habilidad("Astronomía", "Astrobiología", 4, "Estudio de la vida en el universo");
        Habilidad h52 = new Habilidad("Astronomía", "Astronomía Teórica", 5, "Desarrollo de modelos y teorías astronómicas");
        Habilidad h53 = new Habilidad("Astronomía", "Astrofotografía", 3, "Captura y procesamiento de imágenes astronómicas");
        Habilidad h54 = new Habilidad("Astronomía", "Cosmología", 4, "Estudio del origen y evolución del universo");
        Habilidad h55 = new Habilidad("Astronomía", "Exploración Espacial", 4, "Participación en misiones espaciales");
        Habilidad h56 = new Habilidad("Biología", "Microbiología", 4, "Estudio de microorganismos y bacterias");
        Habilidad h57 = new Habilidad("Biología", "Botánica", 5, "Investigación de plantas y vegetación");
        Habilidad h58 = new Habilidad("Biología", "Zoología", 3, "Estudio de animales y su comportamiento");
        Habilidad h59 = new Habilidad("Biología", "Biología Molecular", 5, "Análisis de la estructura y función de las moléculas biológicas");
        Habilidad h60 = new Habilidad("Biología", "Genética", 4, "Investigación de la herencia genética");
        Habilidad h61 = new Habilidad("Química", "Química Analítica", 4, "Análisis de sustancias químicas");
        Habilidad h62 = new Habilidad("Química", "Química Física", 5, "Estudio de las propiedades físicas de las sustancias");
        Habilidad h63 = new Habilidad("Química", "Química Computacional", 3, "Uso de métodos computacionales en la química");
        Habilidad h64 = new Habilidad("Química", "Química Orgánica", 4, "Investigación de compuestos orgánicos");
        Habilidad h65 = new Habilidad("Química", "Química Inorgánica", 4, "Estudio de compuestos inorgánicos");
        Habilidad h66 = new Habilidad("Matemáticas", "Estadísticas", 4, "Análisis y interpretación de datos");
        Habilidad h67 = new Habilidad("Matemáticas", "Teoría de Números", 5, "Estudio de propiedades de los números");
        Habilidad h68 = new Habilidad("Matemáticas", "Geometría Diferencial", 3, "Investigación de geometría avanzada");
        Habilidad h69 = new Habilidad("Matemáticas", "Cálculo Numérico", 4, "Resolución de problemas mediante métodos numéricos");
        Habilidad h70 = new Habilidad("Matemáticas", "Álgebra Abstracta", 4, "Estudio de estructuras algebraicas avanzadas");
        Habilidad h71 = new Habilidad("Farmacia", "Farmacia Comunitaria", 4, "Atención farmacéutica en entornos comunitarios");
        Habilidad h72 = new Habilidad("Farmacia", "Farmacia Industrial", 5, "Producción y control de calidad de medicamentos");
        Habilidad h73 = new Habilidad("Farmacia", "Investigación Farmacéutica", 3, "Desarrollo de nuevos medicamentos");
        Habilidad h74 = new Habilidad("Farmacia", "Farmacovigilancia", 4, "Detección y evaluación de efectos adversos de medicamentos");
        Habilidad h75 = new Habilidad("Farmacia", "Farmacia Clínica", 4, "Asesoramiento farmacéutico en entornos clínicos");
		
		
		Persona pAdmin = new Persona("admin", "admin", "Alava",20,"admin","admin", null,new File("adminpng.png"),"admin");
		datos.anadirUsuarioPersona(pAdmin);
		
		
		
		Empresa adminE = new Empresa("adminE", "adminE", "adminE","adminE",null,null, new File("TinkedinPNG.png"),"adminE");
		datos.anadirUsuarioEmpresa(adminE);
		
//		Empresa adminE2 = new Empresa("adminE2", "adminE2", "adminE2","adminE2",null,null, (new ImageIcon(PnlExplorar.class.getResource("fotoPerfilEjemplo.jpg"))),"adminE2");
//		datos.anadirUsuarioEmpresa(adminE2);
		
		datos.fin();
		
	}
	
}
