package clases;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import javax.swing.ImageIcon;

import datos.DatosFicheros;
import nube.ImagenesAzure;
import servidor.ServicioPersistencia;
import servidor.ServicioPersistenciaFicheros;
import usuarios.Empresa;
import usuarios.Persona;

public class Main {

	public static void main(String[] args) {
		
		ServicioPersistencia servicio = new ServicioPersistenciaFicheros();
		servicio.init();
		servicio.delete();
//		ImagenesAzure.deleteBlobsFicheros();
		
//		DatosFicheros datos = new DatosFicheros();
//		Persona p1 = new Persona("Aitor", "Diez Mateo", "Alava", 19, "aitor.diez@opendeusto.es",
//				"688680738",new ArrayList<Habilidad>(), null, "passWd");
//		Empresa e1 = new Empresa("Google", "673264634", "google@gmail.com", "",
//				null, null, null, "psswd") ;
		
//		HABILIDADES
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
		
		
//      EMPRESAS
        
        
        // Empresa 1
        Object[] atribsE1 = {"TechIbérica", "912345678", "info@techiberica.es",
                "Desarrollo de soluciones tecnológicas avanzadas",
                new ArrayList<>(Arrays.asList("Madrid", "Barcelona", "Valencia")),
                new File("src/ventanas/defaultFotoPerfil.jpg"), "asdf"};
        Empresa e1 = (Empresa) servicio.crearUsuario(atribsE1);
        
        // Empresa 2
        Object[] atribsE2 = {"EcoVida", "654321098", "contacto@ecovida.es",
                "Comprometidos con la sostenibilidad y el medio ambiente",
                new ArrayList<>(Arrays.asList("Sevilla", "Málaga", "Granada")),
                new File("src/ventanas/defaultFotoPerfil.jpg"), "asdf"};
         Empresa e2 = (Empresa) servicio.crearUsuario(atribsE2);

        // Empresa 3
         Object[] atribsE3 = {"ModaEspañola", "789012345", "info@modaespanola.es",
                "Diseños exclusivos con la elegancia española",
                new ArrayList<>(Arrays.asList("Barcelona", "Madrid", "Valencia")),
                new File("src/ventanas/defaultFotoPerfil.jpg"), "asdf"};
         Empresa e3 = (Empresa) servicio.crearUsuario(atribsE3);

        // Empresa 4
        Object[] atribsE4 = {"Gastronómica", "678909234", "info@gastronomica.es",
                "Experiencias culinarias únicas y deliciosas",
                new ArrayList<>(Arrays.asList("Alicante", "Murcia", "Valencia")),
                new File("src/ventanas/defaultFotoPerfil.jpg"), "asdf"};
        Empresa e4 = (Empresa) servicio.crearUsuario(atribsE4);

        // Empresa 5
        Object[] atribsE5 = {"ViajesHispania", "667890123", "info@viajeshispania.es",
                "Descubre España a través de experiencias de viaje inolvidables",
                new ArrayList<>(Arrays.asList("Madrid", "Barcelona", "Sevilla")),
                new File("src/ventanas/defaultFotoPerfil.jpg"), "asdf"};
        Empresa e5 = (Empresa) servicio.crearUsuario(atribsE5);

        // Empresa 6
        Object[] atribsE6 = {"InmobiliariaPlus", "466789012", "info@inmobiliariaplus.es",
                "Soluciones inmobiliarias personalizadas y confiables",
                new ArrayList<>(Arrays.asList("Madrid", "Barcelona", "Valencia")),
                new File("src/ventanas/defaultFotoPerfil.jpg"), "asdf"};
        Empresa e6 = (Empresa) servicio.crearUsuario(atribsE6);

        // Empresa 7
        Object[] atribsE7 = {"EducaciónExcel", "341678901", "info@educacionexcel.es",
                "Formación de calidad para un futuro brillante",
                new ArrayList<>(Arrays.asList("Sevilla", "Valencia", "Barcelona")),
                new File("src/ventanas/defaultFotoPerfil.jpg"), "asdf"};
        Empresa e7 = (Empresa) servicio.crearUsuario(atribsE7);

        // Empresa 8
        Object[] atribsE8 = {"EstiloHogar", "234567190", "info@estilohogar.es",
                "Productos y decoración para un hogar con estilo",
                new ArrayList<>(Arrays.asList("Barcelona", "Madrid", "Valencia")),
                new File("src/ventanas/defaultFotoPerfil.jpg"), "asdf"};
        Empresa e8 = (Empresa) servicio.crearUsuario(atribsE8);

        // Empresa 9
        Object[] atribsE9 = {"TurismoRural", "123456789", "info@turismorural.es",
                "Descubre la belleza de la España rural",
                new ArrayList<>(Arrays.asList("Salamanca", "Toledo", "Ávila")),
                new File("src/ventanas/defaultFotoPerfil.jpg"), "asdf"};
        Empresa e9 = (Empresa) servicio.crearUsuario(atribsE9);

        // Empresa 10
        Object[] atribsE10 = {"ArteCreativo", "890123156", "info@artecreativo.es",
                "Expresiones artísticas que inspiran y emocionan",
                new ArrayList<>(Arrays.asList("Barcelona", "Madrid", "Valencia")),
                new File("src/ventanas/defaultFotoPerfil.jpg"), "asdf"};
        Empresa e10 = (Empresa) servicio.crearUsuario(atribsE10);
        
        Object[] atribsE11 = {"ObservatorioEstelar", "111222333", "info@observatorioestelar.es",
                "Exploración del cosmos y estudio astronómico",
                new ArrayList<>(Arrays.asList("Granada", "Almería", "Jaén")),
                new File("src/ventanas/defaultFotoPerfil.jpg"), "asdf"};
        Empresa e11 = (Empresa) servicio.crearUsuario(atribsE11);

        Object[] atribsE12 = {"BioTechLab", "222333444", "info@biotechlab.es",
                "Investigación biotecnológica y desarrollo de medicamentos",
                new ArrayList<>(Arrays.asList("Valencia", "Alicante", "Castellón")),
                new File("src/ventanas/defaultFotoPerfil.jpg"), "asdf"};
        Empresa e12 = (Empresa) servicio.crearUsuario(atribsE12);

        Object[] atribsE13 = {"QuantumSolutions", "333444555", "info@quantumsolutions.es",
                "Investigación en ciencias cuánticas y desarrollo tecnológico",
                new ArrayList<>(Arrays.asList("Barcelona", "Madrid", "Zaragoza")),
                new File("src/ventanas/defaultFotoPerfil.jpg"), "asdf"};
        Empresa e13 = (Empresa) servicio.crearUsuario(atribsE13);

        Object[] atribsE14 = {"MatemáticasAvanzadas", "444555666", "info@matematicasavanzadas.es",
                "Soluciones matemáticas innovadoras y análisis numérico",
                new ArrayList<>(Arrays.asList("Madrid", "Barcelona", "Sevilla")),
                new File("src/ventanas/defaultFotoPerfil.jpg"), "asdf"};
        Empresa e14 = (Empresa) servicio.crearUsuario(atribsE14);

        Object[] atribsE15 = {"FarmaciaInnovadora", "555666777", "info@farmaciainnovadora.es",
                "Desarrollo de medicamentos y cuidado farmacéutico",
                new ArrayList<>(Arrays.asList("Zaragoza", "Barcelona", "Valencia")),
                new File("src/ventanas/defaultFotoPerfil.jpg"), "asdf"};
        Empresa e15 = (Empresa) servicio.crearUsuario(atribsE15);
        
        
     // Puestos de Trabajo
        
        PuestoTrabajo astrónomo = new PuestoTrabajo("Astrónomo", "Investigación astronómica avanzada",
                new ArrayList<>(Arrays.asList(h52, h54, h55)), e11);
        servicio.anadirPuesto(astrónomo);
        PuestoTrabajo biólogo = new PuestoTrabajo("Biólogo", "Investigación en biología molecular y botánica",
                new ArrayList<>(Arrays.asList(h57, h59, h60)), e12);
        servicio.anadirPuesto(biólogo);
        PuestoTrabajo químico = new PuestoTrabajo("Químico", "Investigación en química analítica y farmacéutica",
                new ArrayList<>(Arrays.asList(h61, h62, h64, h65)), e13);
        servicio.anadirPuesto(químico);
        PuestoTrabajo matemático = new PuestoTrabajo("Matemático", "Investigación en teoría de números y cálculo numérico",
                new ArrayList<>(Arrays.asList(h67, h69, h70)), e14);
        servicio.anadirPuesto(matemático);
        PuestoTrabajo farmacéutico = new PuestoTrabajo("Farmacéutico", "Desarrollo de medicamentos y farmacovigilancia",
                new ArrayList<>(Arrays.asList(h72, h73, h74, h75)), e15);
        servicio.anadirPuesto(farmacéutico);
        // Puesto de Desarrollador Web en TechIbérica
        PuestoTrabajo puesto1 = new PuestoTrabajo("Desarrollador Web", "Desarrollo de aplicaciones web avanzadas",
                new ArrayList<>(Arrays.asList(h1, h35)), e1);
        servicio.anadirPuesto(puesto1);
        // Puesto de Diseñador Gráfico en ModaEspañola
        PuestoTrabajo puesto2 = new PuestoTrabajo("Diseñador Gráfico", "Creación de materiales visuales para la moda",
                new ArrayList<>(Arrays.asList(h2, h28)), e3);
        servicio.anadirPuesto(puesto2);
        // Puesto de Consultor de Marketing Digital en EcoVida
        PuestoTrabajo puesto3 = new PuestoTrabajo("Consultor de Marketing Digital", "Estrategias de marketing sostenible",
                new ArrayList<>(Arrays.asList(h5, h33)), e2);
        servicio.anadirPuesto(puesto3);
        // Puesto de Entrenador Personal en Gastronómica
        PuestoTrabajo puesto4 = new PuestoTrabajo("Entrenador Personal", "Asesoramiento en salud y bienestar",
                new ArrayList<>(Arrays.asList(h3, h27)), e4);
        servicio.anadirPuesto(puesto4);
        // Puesto de Psicólogo Clínico en EducaciónExcel
        PuestoTrabajo puesto5 = new PuestoTrabajo("Psicólogo Clínico", "Apoyo psicológico a estudiantes",
                new ArrayList<>(Arrays.asList(h4, h41)), e7);
        servicio.anadirPuesto(puesto5);
        // Puesto de Ingeniero Civil en InmobiliariaPlus
        PuestoTrabajo puesto6 = new PuestoTrabajo("Ingeniero Civil", "Diseño y gestión de proyectos de construcción",
                new ArrayList<>(Arrays.asList(h10, h23)), e6);
        servicio.anadirPuesto(puesto6);
        // Puesto de Especialista en Ventas Directas en ViajesHispania
        PuestoTrabajo puesto7 = new PuestoTrabajo("Especialista en Ventas Directas", "Venta de experiencias de viaje",
                new ArrayList<>(Arrays.asList(h11, h45)), e5);
        servicio.anadirPuesto(puesto7);
        // Puesto de Atención al Cliente en EstiloHogar
        PuestoTrabajo puesto8 = new PuestoTrabajo("Atención al Cliente", "Resolución de problemas y atención personalizada",
                new ArrayList<>(Arrays.asList(h12, h46)), e8);
        servicio.anadirPuesto(puesto8);
        // Puesto de Trabajador Social en TurismoRural
        PuestoTrabajo puesto9 = new PuestoTrabajo("Trabajador Social", "Apoyo comunitario en entornos rurales",
                new ArrayList<>(Arrays.asList(h13, h30)), e9);
        servicio.anadirPuesto(puesto9);
        // Puesto de Gestión Ambiental en ArteCreativo
        PuestoTrabajo puesto10 = new PuestoTrabajo("Gestión Ambiental", "Preservación del medio ambiente a través del arte",
                new ArrayList<>(Arrays.asList(h14, h36)), e10);
        servicio.anadirPuesto(puesto10);
        // Puesto de Diseñador de Moda en InmobiliariaPlus
        PuestoTrabajo puesto11 = new PuestoTrabajo("Diseñador de Moda", "Creación de diseños innovadores para espacios",
                new ArrayList<>(Arrays.asList(h28, h49)), e6);
        servicio.anadirPuesto(puesto11);
        // Puesto de Administración Pública en EducaciónExcel
        PuestoTrabajo puesto12 = new PuestoTrabajo("Administración Pública", "Gestión administrativa en el sector educativo",
                new ArrayList<>(Arrays.asList(h29, h7)), e7);
        servicio.anadirPuesto(puesto12);
        // Puesto de Gestión de ONGs en TurismoRural
        PuestoTrabajo puesto13 = new PuestoTrabajo("Gestión de ONGs", "Administración de organizaciones benéficas en entornos rurales",
                new ArrayList<>(Arrays.asList(h30, h13)), e9);
        servicio.anadirPuesto(puesto13);
        // Puesto de Relaciones con los Medios en ViajesHispania
        PuestoTrabajo puesto14 = new PuestoTrabajo("Relaciones con los Medios", "Gestión de relaciones públicas para viajes",
                new ArrayList<>(Arrays.asList(h31, h5)), e5);
        servicio.anadirPuesto(puesto14);
        // Puesto de Seguridad Informática en TechIbérica
        PuestoTrabajo puesto15 = new PuestoTrabajo("Seguridad Informática", "Protección de sistemas y datos",
                new ArrayList<>(Arrays.asList(h32, h1)), e1);
        servicio.anadirPuesto(puesto15);
        // Puesto de Energía Renovable en EcoVida
        PuestoTrabajo puesto16 = new PuestoTrabajo("Energía Renovable", "Desarrollo de fuentes de energía sostenible",
                new ArrayList<>(Arrays.asList(h33, h15)), e2);
        servicio.anadirPuesto(puesto16);
        // Puesto de Análisis de Datos en TurismoRural
        PuestoTrabajo puesto17 = new PuestoTrabajo("Análisis de Datos", "Análisis y visualización de datos para el turismo rural",
                new ArrayList<>(Arrays.asList(h34, h9)), e9);
        servicio.anadirPuesto(puesto17);
        // Puesto de Machine Learning en TechIbérica
        PuestoTrabajo puesto18 = new PuestoTrabajo("Machine Learning", "Implementación de algoritmos de aprendizaje automático",
                new ArrayList<>(Arrays.asList(h35, h17)), e1);
        servicio.anadirPuesto(puesto18);
        // Puesto de Diseño de Interiores en EstiloHogar
        PuestoTrabajo puesto19 = new PuestoTrabajo("Diseño de Interiores", "Diseño creativo de espacios para el hogar",
                new ArrayList<>(Arrays.asList(h36, h8)), e8);
        servicio.anadirPuesto(puesto19);
        // Puesto de Restauración en Gastronómica
        PuestoTrabajo puesto20 = new PuestoTrabajo("Restauración", "Gestión de restaurantes y cocina",
                new ArrayList<>(Arrays.asList(h27, h47)), e4);
        servicio.anadirPuesto(puesto20);
        //Puesto para el admin
//        PuestoTrabajo puestoAdmin = new PuestoTrabajo("Gestión de ONGs", "Administración de organizaciones benéficas en entornos rurales",
//                new ArrayList<>(Arrays.asList(h30, h13)), adminE);
//        servicio.anadirPuesto(puestoAdmin);
        
        
    	Date fecha;
		try {
			fecha = new SimpleDateFormat("yyyy-MM-dd").parse("2000-01-01");
		} catch (ParseException exc) {
			// TODO Auto-generated catch block
			fecha = null;
			exc.printStackTrace();
		}
        
        
        Object[] atribsP1 = {"Juan", "Gómez", "Madrid", fecha, "juan@gmail.com", "623456789", crearCurriculum(h1, h16, h31, h46, h61), new File("src/ventanas/defaultFotoPerfil.jpg"), "asdf"};
        Persona p1 = (Persona) servicio.crearUsuario(atribsP1);
        Object[] atribsP2 = {"María", "López", "Barcelona", fecha, "maria@gmail.com", "987654321", crearCurriculum(h2, h17, h32, h47, h62), new File("src/ventanas/defaultFotoPerfil.jpg"), "asdf"};
        Persona p2 = (Persona) servicio.crearUsuario(atribsP2);
        Object[] atribsP3 = {"Carlos", "Martínez", "Valencia", fecha, "carlos@gmail.com", "567890123", crearCurriculum(h15, h18, h33, h48, h63), new File("src/ventanas/defaultFotoPerfil.jpg"), "asdf"};
        Persona p3 = (Persona) servicio.crearUsuario(atribsP3);
        Object[] atribsP4 = {"Ana", "García", "Sevilla", fecha, "ana@gmail.com", "456789012", crearCurriculum(h3, h19, h34, h49, h64), new File("src/ventanas/defaultFotoPerfil.jpg"), "asdf"};
        Persona p4 = (Persona) servicio.crearUsuario(atribsP4);
        Object[] atribsP5 = {"Javier", "Fernández", "Zaragoza", fecha, "javier@gmail.com", "234567890", crearCurriculum(h4, h20, h35, h50, h65), new File("src/ventanas/defaultFotoPerfil.jpg"), "asdf"};
        Persona p5 = (Persona) servicio.crearUsuario(atribsP5);
        Object[] atribsP6 = {"Sara", "Ruiz", "Málaga", fecha, "sara@gmail.com", "890123456", crearCurriculum(h5, h21, h36, h51, h66), new File("src/ventanas/defaultFotoPerfil.jpg"), "asdf"};
        Persona p6 = (Persona) servicio.crearUsuario(atribsP6);
        Object[] atribsP7 = {"Pedro", "Sánchez", "Murcia", fecha, "pedro@gmail.com", "345678901", crearCurriculum(h6, h22, h37, h52, h67), new File("src/ventanas/defaultFotoPerfil.jpg"), "asdf"};
        Persona p7 = (Persona) servicio.crearUsuario(atribsP7);
        Object[] atribsP8 = {"Elena", "Jiménez", "Palma de Mallorca", fecha, "elena@gmail.com", "012345678", crearCurriculum(h7, h23, h38, h53, h68), new File("src/ventanas/defaultFotoPerfil.jpg"), "asdf"};
        Persona p8 = (Persona) servicio.crearUsuario(atribsP8);
        Object[] atribsP9 = {"Francisco", "Rodríguez", "Las Palmas de Gran Canaria", fecha, "francisco@gmail.com", "678901234", crearCurriculum(h8, h24, h39, h54, h69), new File("src/ventanas/defaultFotoPerfil.jpg"), "asdf"};
        Persona p9 = (Persona) servicio.crearUsuario(atribsP9);
        Object[] atribsP10 = {"Laura", "Gutiérrez", "Valladolid", fecha, "laura@gmail.com", "567827123", crearCurriculum(h9, h25, h40, h55, h70), new File("src/ventanas/defaultFotoPerfil.jpg"), "asdf"};
        Persona p10 = (Persona) servicio.crearUsuario(atribsP10);
        Object[] atribsP11 = {"Alejandro", "Hernández", "Pamplona", fecha, "alejandro@gmail.com", "123456119", crearCurriculum(h10, h26, h41, h56, h71), new File("src/ventanas/defaultFotoPerfil.jpg"), "asdf"};
        Persona p11 = (Persona) servicio.crearUsuario(atribsP11);
        Object[] atribsP12 = {"Isabel", "Díaz", "Toledo", fecha, "isabel@gmail.com", "987604321", crearCurriculum(h11, h27, h42, h57, h72), new File("src/ventanas/defaultFotoPerfil.jpg"), "asdf"};
        Persona p12 = (Persona) servicio.crearUsuario(atribsP12);
        Object[] atribsP13 = {"Víctor", "Moreno", "Santander", fecha, "victor@gmail.com", "234529890", crearCurriculum(h12, h28, h43, h58, h73), new File("src/ventanas/defaultFotoPerfil.jpg"), "asdf"};
        Persona p13 = (Persona) servicio.crearUsuario(atribsP13);
        Object[] atribsP14 = {"Carmen", "Álvarez", "Logroño", fecha, "carmen@gmail.com", "890121056", crearCurriculum(h13, h29, h44, h59, h74), new File("src/ventanas/defaultFotoPerfil.jpg"), "asdf"};
        Persona p14 = (Persona) servicio.crearUsuario(atribsP14);
        Object[] atribsP15 = {"Raúl", "Fernández", "Cáceres", fecha, "raul@gmail.com", "345671001", crearCurriculum(h14, h30, h45, h60, h75), new File("src/ventanas/defaultFotoPerfil.jpg"), "asdf"};
        Persona p15 = (Persona) servicio.crearUsuario(atribsP15);
        
		Object[] atribsPAdmin = {"admin", "admin", "Alava",fecha ,"admin","admin", crearCurriculum( h30, h13  ),new File("adminpng.png"),"admin"};
		Persona adminP = (Persona) servicio.crearUsuario(atribsPAdmin);
//		System.out.println("adminP: " + adminP);
		
		// Empresa admin
        Object[] atribsAdmin = {"adminE", "001010101", "adminE",
                "adminE",
                new ArrayList<>(Arrays.asList("Madrid", "Barcelona", "Sevilla")),
                new File("src/ventanas/defaultFotoPerfil.jpg"), "adminE"};
        Empresa adminE = (Empresa) servicio.crearUsuario(atribsAdmin);
        System.out.println("Id adminE :" + adminE.getId());
		
		Mensaje m1 = new Mensaje(adminP.getId(), adminE.getId(), "Hola!", new Date());
		servicio.anadirMensaje(m1);
		Mensaje m2 = new Mensaje(adminE.getId(), adminP.getId(), "Buenas!", new Date());
		servicio.anadirMensaje(m2);
		
		servicio.close();
		
	}
	
    private static ArrayList<Habilidad> crearCurriculum(Habilidad... habilidades) {
        ArrayList<Habilidad> curriculum = new ArrayList<>();
        for (Habilidad habilidad : habilidades) {
            curriculum.add(habilidad);
        }
        return curriculum;
    }
	
}
