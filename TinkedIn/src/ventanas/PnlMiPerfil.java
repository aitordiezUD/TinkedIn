package ventanas;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.*;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

import clases.Habilidad;
import clases.PuestoTrabajo;
import componentes.botonAnEl;
import nube.ImagenesAzure;
import usuarios.Empresa;
import usuarios.Persona;
import usuarios.Usuario;


public class PnlMiPerfil  extends JPanel {
	private static final long serialVersionUID = 1L;
	private Persona personaAutenticada;
	private Empresa empresaAutenticada;
	private DefaultListModel<Habilidad> modeloListaPersona;
	private static DefaultListModel<PuestoTrabajo> modeloListaEmpresa;
	private static JList<PuestoTrabajo> listaPuestosTrabajo;
	public DefaultListModel<Habilidad> getModeloListaPersona() {
		return modeloListaPersona;
	}
	public DefaultListModel<PuestoTrabajo> getModeloListaEmpresa(){
		return modeloListaEmpresa;
	}
	
	public PnlMiPerfil(Usuario usuarioAutenticado) {
		
		setBackground(Color.WHITE);
		setLayout(new BorderLayout());
		setSize(750,650);
		
		if (usuarioAutenticado instanceof Persona) {
			personaAutenticada = (Persona) usuarioAutenticado;
			
			
			JPanel pnlSup = new JPanel(new BorderLayout());
			pnlSup.setBackground(VentanaPrincipal.ColorBase);
			pnlSup.add(ImagenesAzure.crearImagen(usuarioAutenticado,100,100));
			pnlSup.setPreferredSize(new Dimension(150,150));
			JPanel pnlNombre = new JPanel();
			pnlNombre.setBackground(VentanaPrincipal.ColorBase);
			JLabel lblNombre = new JLabel(personaAutenticada.getNombre() + " " + personaAutenticada.getApellidos());
			lblNombre.setFont(VentanaPrincipal.fuente_titulos);
			pnlNombre.add(lblNombre);
			pnlSup.add(pnlNombre, BorderLayout.SOUTH);
			add(pnlSup,BorderLayout.NORTH);
			
			JPanel pnlDatos = new JPanel(new GridLayout(1,2));
			add(pnlDatos);
			
			JPanel pnlDatosContacto = new JPanel(new BorderLayout());
			
			JPanel pnlDatosContactoContenido = new JPanel();
			pnlDatosContactoContenido.setLayout(new BoxLayout(pnlDatosContactoContenido, BoxLayout.Y_AXIS));
			pnlDatos.add(pnlDatosContacto);
			
			
			pnlDatosContacto.add(pnlDatosContactoContenido);
			
			JPanel p = new JPanel();
			p.setPreferredSize(new Dimension(30,30));
			pnlDatosContacto.add(p,BorderLayout.NORTH);
			p = new JPanel();
			p.setPreferredSize(new Dimension(10,10));
			pnlDatosContacto.add(p,BorderLayout.SOUTH);
			p = new JPanel();
			p.setPreferredSize(new Dimension(20,50));
			pnlDatosContacto.add(p,BorderLayout.WEST);
			p = new JPanel();
			p.setPreferredSize(new Dimension(20,50));
			pnlDatosContacto.add(p,BorderLayout.EAST);
			
			JLabel lblCorreoE = new JLabel("Correo Electrónico: " + personaAutenticada.getCorreo());
			p = new JPanel(new BorderLayout());
			p.setPreferredSize(new Dimension(1000,40));
			p.setMaximumSize(new Dimension(1000,40));
			p.add(lblCorreoE);
			pnlDatosContactoContenido.add(p);
			
			JLabel lblNTelefono = new JLabel("Nº Teléfono: " + personaAutenticada.getTelefono());
			p = new JPanel(new BorderLayout());
			p.setPreferredSize(new Dimension(1000,40));
			p.setMaximumSize(new Dimension(1000,40));
			p.add(lblNTelefono);
			pnlDatosContactoContenido.add(p);
			
			JLabel lblEdad = new JLabel("Edad: " + calcularDiferenciaAnios(personaAutenticada.getEdad()));
			p = new JPanel(new BorderLayout());
			p.setPreferredSize(new Dimension(1000,40));
			p.setMaximumSize(new Dimension(1000,40));
			p.add(lblEdad);
			pnlDatosContactoContenido.add(p);

			JLabel lblUbicacion = new JLabel("Ubicación: " + personaAutenticada.getUbicacion());
			p = new JPanel(new BorderLayout());
			p.setPreferredSize(new Dimension(1000,40));
			p.setMaximumSize(new Dimension(1000,40));
			p.add(lblUbicacion);
			pnlDatosContactoContenido.add(p);
			
			JButton btnEditar = new JButton("Editar perfil");
			p = new JPanel(new BorderLayout());
			p.add(btnEditar);
			
			btnEditar.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					PnlBotonera.CardLayout.show(PnlBotonera.pnlFuncional, "pnlEditarPerfil");
				}
			});
//			ImageIcon lapiz = new ImageIcon("EditarPerfil.png");
			
			
			JPanel pnlLista = new JPanel(new BorderLayout());
			
			JPanel pnlListaContenido = new JPanel(new BorderLayout());
			pnlLista.add(pnlListaContenido);
			pnlDatos.add(pnlLista);
			
			p = new JPanel();
			p.setPreferredSize(new Dimension(10,10));
			pnlLista.add(p,BorderLayout.NORTH);
			p = new JPanel();
			p.setPreferredSize(new Dimension(10,50));
			pnlLista.add(p,BorderLayout.SOUTH);
			p = new JPanel();
			p.setPreferredSize(new Dimension(30,50));
			pnlLista.add(p,BorderLayout.WEST);
			p = new JPanel();
			p.setPreferredSize(new Dimension(30,50));
			pnlLista.add(p,BorderLayout.EAST);
			
			JLabel lblFuenteCurriculum = new JLabel("CURRICULUM:");
			lblFuenteCurriculum.setFont(VentanaPrincipal.fuente_titulos);
			lblFuenteCurriculum.setHorizontalAlignment(SwingConstants.CENTER);
			p = new JPanel(new BorderLayout());
			p.setMaximumSize(new Dimension(1000,40));
			p.setPreferredSize(new Dimension(1000,40));
			p.add(lblFuenteCurriculum);
			pnlListaContenido.add(p, BorderLayout.NORTH);
			
			JList<Habilidad> list = new JList<Habilidad>();
			modeloListaPersona = new DefaultListModel<Habilidad>();
			modeloListaPersona.addListDataListener(new ListDataListener() {
				
				@Override
				public void intervalRemoved(ListDataEvent e) {
					// TODO Auto-generated method stub
					actualizarCurriculum();
				}
				
				@Override
				public void intervalAdded(ListDataEvent e) {
					// TODO Auto-generated method stub
					actualizarCurriculum();
				}
				
				@Override
				public void contentsChanged(ListDataEvent e) {
					// TODO Auto-generated method stub
					actualizarCurriculum();
				}
			});
			llenarListaCurriculum();
			list.setModel(modeloListaPersona);
			
			pnlListaContenido.add(new JScrollPane(list));
			
			p = new JPanel(new FlowLayout());
			botonAnEl btnAnadir = new botonAnEl("Añadir");
			
			btnAnadir.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					PnlBotonera.CardLayout.show(PnlBotonera.pnlFuncional, "pnlHabilidad");
				}
			});
			
			p.add(btnAnadir);
			botonAnEl btnEliminar = new botonAnEl("Eliminar");
			btnEliminar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (list.getSelectedValue()!=null) {
						Habilidad habilidad = list.getSelectedValue();
						VentanaPrincipal.servicio.eliminarHabilidad(habilidad, PnlBotonera.usuarioAutenticado.getId());
						modeloListaPersona.removeElement(habilidad);
						list.repaint();
						PnlBotonera.pExplorarPersona.repaint();
					}
				}
			});
			p.add(btnEliminar);
			pnlListaContenido.add(p, BorderLayout.SOUTH);
			
		} else {
			
			empresaAutenticada = (Empresa) usuarioAutenticado;
			
			JPanel pnlSup = new JPanel(new BorderLayout());
			pnlSup.setBackground(VentanaPrincipal.ColorBase);
			pnlSup.add(ImagenesAzure.crearImagen(empresaAutenticada,100,100));
			pnlSup.setPreferredSize(new Dimension(150,150));
			JPanel pnlNombre = new JPanel();
			pnlNombre.setBackground(VentanaPrincipal.ColorBase);
			JLabel lblNombre = new JLabel(empresaAutenticada.getNombre());
			lblNombre.setFont(VentanaPrincipal.fuente_titulos);
			pnlNombre.add(lblNombre);
			pnlSup.add(pnlNombre, BorderLayout.SOUTH);
			add(pnlSup,BorderLayout.NORTH);
			
			
			
//			JPanel pnlDatos = new JPanel() {
//				@Override
//				protected void paintComponent(Graphics g) {
//					super.paintComponent(g);
//			    	
//			    	g.setColor(Color.BLACK);
//			    	g.drawLine(  355,10,355,420 );
//				}
//			};
//			pnlDatos.setBackground(new Color(202, 232, 232));
//			add(pnlDatos);
//			pnlDatos.setLayout(null);
			
			
//			JPanel pnlFotoPerfil = new JPanel();
//			pnlFotoPerfil.setLayout(new BorderLayout());
//			pnlFotoPerfil.add(ImagenesAzure.crearImagen(usuarioAutenticado));
			
			JPanel pnlDatos = new JPanel(new GridLayout(1,2));
			add(pnlDatos);
			
			JPanel pnlDatosContacto = new JPanel(new BorderLayout());
			
			JPanel pnlDatosContactoContenido = new JPanel();
			pnlDatosContactoContenido.setLayout(new BoxLayout(pnlDatosContactoContenido, BoxLayout.Y_AXIS));
			pnlDatos.add(pnlDatosContacto);
			
			
			pnlDatosContacto.add(pnlDatosContactoContenido);
			
			JPanel p = new JPanel();
			p.setPreferredSize(new Dimension(30,30));
			pnlDatosContacto.add(p,BorderLayout.NORTH);
			p = new JPanel();
			p.setPreferredSize(new Dimension(10,10));
			pnlDatosContacto.add(p,BorderLayout.SOUTH);
			p = new JPanel();
			p.setPreferredSize(new Dimension(20,50));
			pnlDatosContacto.add(p,BorderLayout.WEST);
			p = new JPanel();
			p.setPreferredSize(new Dimension(20,50));
			pnlDatosContacto.add(p,BorderLayout.EAST);
			
			JLabel lblCorreoE = new JLabel("Correo Electrónico: " + empresaAutenticada.getCorreo());
			p = new JPanel(new BorderLayout());
			p.setPreferredSize(new Dimension(1000,40));
			p.setMaximumSize(new Dimension(1000,40));
			p.add(lblCorreoE);
			pnlDatosContactoContenido.add(p);
			
			
			JLabel lblNTelefono = new JLabel("Nº Teléfono: " + empresaAutenticada.getTelefono());
			p = new JPanel(new BorderLayout());
			p.setPreferredSize(new Dimension(1000,40));
			p.setMaximumSize(new Dimension(1000,40));
			p.add(lblNTelefono);
			pnlDatosContactoContenido.add(p);
			
			JLabel lblDescripcion = new JLabel("Descripcion: ");
			p = new JPanel(new BorderLayout());
			p.setPreferredSize(new Dimension(1000,40));
			p.setMaximumSize(new Dimension(1000,40));
			p.add(lblDescripcion);
			pnlDatosContactoContenido.add(p);
			
			JTextArea taDescripcion = new JTextArea();
			taDescripcion.setLineWrap(true);
			p = new JPanel(new BorderLayout());
			p.setPreferredSize(new Dimension(1000,150));
			p.setMaximumSize(new Dimension(1000,150));
			taDescripcion.setText(empresaAutenticada.getDescripcion());
			p.add(new JScrollPane(taDescripcion));
			pnlDatosContactoContenido.add(p);
			
			JButton btnEditar = new JButton("Editar perfil");
			p = new JPanel(new BorderLayout());
			btnEditar.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					PnlBotonera.CardLayout.show(PnlBotonera.pnlFuncional, "pnlEditarPerfil");
				}
			});
//			ImageIcon lapiz = new ImageIcon("EditarPerfil.png");
			
			
			JPanel pnlLista = new JPanel(new BorderLayout());
			
			JPanel pnlListaContenido = new JPanel(new BorderLayout());
			pnlLista.add(pnlListaContenido);
			pnlDatos.add(pnlLista);
			
			p = new JPanel();
			p.setPreferredSize(new Dimension(10,10));
			pnlLista.add(p,BorderLayout.NORTH);
			p = new JPanel();
			p.setPreferredSize(new Dimension(10,50));
			pnlLista.add(p,BorderLayout.SOUTH);
			p = new JPanel();
			p.setPreferredSize(new Dimension(30,50));
			pnlLista.add(p,BorderLayout.WEST);
			p = new JPanel();
			p.setPreferredSize(new Dimension(30,50));
			pnlLista.add(p,BorderLayout.EAST);
					
			JLabel lblFuentePuestosTrabajo = new JLabel("Puestos de Trabajo:");
			lblFuentePuestosTrabajo.setFont(VentanaPrincipal.fuente_titulos);
			lblFuentePuestosTrabajo.setHorizontalAlignment(SwingConstants.CENTER);
			p = new JPanel(new BorderLayout());
			p.setMaximumSize(new Dimension(1000,40));
			p.setPreferredSize(new Dimension(1000,40));
			p.add(lblFuentePuestosTrabajo);
			pnlListaContenido.add(p, BorderLayout.NORTH);
			
			listaPuestosTrabajo = new JList<PuestoTrabajo>();
			modeloListaEmpresa = new DefaultListModel<PuestoTrabajo>();
			modeloListaEmpresa.addListDataListener(new ListDataListener() {
				
				@Override
				public void intervalRemoved(ListDataEvent e) {
					// TODO Auto-generated method stub
					actualizarListaPuestosTrabajo();
				}
				
				@Override
				public void intervalAdded(ListDataEvent e) {
					// TODO Auto-generated method stub
					actualizarListaPuestosTrabajo();
				}
				
				@Override
				public void contentsChanged(ListDataEvent e) {
					// TODO Auto-generated method stub
					actualizarListaPuestosTrabajo();
				}
			});
			llenarListaPuestosTrabajo();
			listaPuestosTrabajo.setModel(modeloListaEmpresa);
			
			pnlListaContenido.add(new JScrollPane(listaPuestosTrabajo));


		}
}
	protected static int calcularDiferenciaAnios(Date fechaFin) {
		Date fechaInicio = new Date();
		Calendar calInicio = Calendar.getInstance();
		calInicio.setTime(fechaInicio);
		Calendar calFin = Calendar.getInstance();
		calFin.setTime(fechaFin);
		int anios = calInicio.get(Calendar.YEAR) - calFin.get(Calendar.YEAR);
		if (calInicio.get(Calendar.DAY_OF_YEAR) > calFin.get(Calendar.DAY_OF_YEAR)) {
			anios--;
		}
		return anios;
	}

	public static void anadirPuestoLista(PuestoTrabajo pt) {
		modeloListaEmpresa.addElement(pt);
		listaPuestosTrabajo.repaint();
	}
	
	private void llenarListaCurriculum() {
		if (personaAutenticada.getCurriculum()!=null) {
			for (Habilidad h : personaAutenticada.getCurriculum()) {
				modeloListaPersona.add(modeloListaPersona.size(),h);
			}
		}
	}
	
	private void actualizarCurriculum() {
		personaAutenticada.setCurriculum(new ArrayList<Habilidad>());
		for (int i = 0; i < modeloListaPersona.size(); i++) {
            personaAutenticada.getCurriculum().add(modeloListaPersona.getElementAt(i));
        }
	}

	private void llenarListaPuestosTrabajo() {
		if (empresaAutenticada.getPuestos()!=null) {
			for (PuestoTrabajo p : empresaAutenticada.getPuestos()) {
				modeloListaEmpresa.add(modeloListaEmpresa.size(),p);
			}
		}
	}
	
	private void actualizarListaPuestosTrabajo() {
		empresaAutenticada.setPuestos(new ArrayList<PuestoTrabajo>());
		for (int i = 0; i < modeloListaEmpresa.size(); i++) {
            empresaAutenticada.getPuestos().add(modeloListaEmpresa.getElementAt(i));
        }
	}
   
}
	

