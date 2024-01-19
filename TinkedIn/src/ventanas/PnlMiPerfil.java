package ventanas;


import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

import clases.Habilidad;
import datos.DatosFicheros;
import nube.ImagenesAzure;
import usuarios.Empresa;
import usuarios.Persona;
import usuarios.Usuario;

import java.awt.Font;
import java.awt.Graphics;


public class PnlMiPerfil  extends JPanel {
	
	private PnlEditarPerfil pnlEditarPerfil;
//	private Usuario usuarioAutenticado;
	private Persona personaAutenticada;
	private Empresa empresaAutenticada;
	private DefaultListModel<Habilidad> modeloListaPersona;
	
	
	public DefaultListModel<Habilidad> getModeloListaPersona() {
		return modeloListaPersona;
	}
	public PnlMiPerfil(Usuario usuarioAutenticado) {
		
		setBackground(Color.WHITE);
		setLayout(new BorderLayout());
		setSize(750,650);
//		usuarioAutenticado = PnlBotonera.usuarioAutenticado;
		
		
		if (usuarioAutenticado instanceof Persona) {
			personaAutenticada = (Persona) usuarioAutenticado;
			
			pnlEditarPerfil = new PnlEditarPerfil();
			
			
			JPanel pnlSup = new JPanel(new BorderLayout());
			pnlSup.setBackground(VentanaPrincipal.ColorBase);
			pnlSup.add(ImagenesAzure.crearImagen(usuarioAutenticado,100,100));
			pnlSup.setPreferredSize(new Dimension(150,150));
			JPanel pnlNombre = new JPanel();
			pnlNombre.setBackground(VentanaPrincipal.ColorBase);
			JLabel lblNombre = new JLabel(personaAutenticada.getNombre() + " " + personaAutenticada.getApellidos());
			lblNombre.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
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
			
			JLabel lblFuenteCurriculum = new JLabel("Curriculum:");
			lblFuenteCurriculum.setFont(new Font("Tahoma", Font.BOLD, 15));
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
			JButton btnAnadir = new JButton("Añadir");
			
			btnAnadir.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					PnlBotonera.CardLayout.show(PnlBotonera.pnlFuncional, "pnlHabilidad");
				}
			});
			
			p.add(btnAnadir);
			JButton btnEliminar = new JButton("Eliminar");
			p.add(btnEliminar);
			pnlListaContenido.add(p, BorderLayout.SOUTH);
			
		} else {
			empresaAutenticada = (Empresa) usuarioAutenticado;
			
			pnlEditarPerfil = new PnlEditarPerfil();
			
			
			JPanel pnlSup = new JPanel(new BorderLayout());
			pnlSup.setBackground(new Color(255,255,220));
			pnlSup.add(ImagenesAzure.crearImagen(empresaAutenticada,100,100));
			pnlSup.setPreferredSize(new Dimension(150,150));
			JPanel pnlNombre = new JPanel();
			pnlNombre.setBackground(VentanaPrincipal.ColorBase);
			JLabel lblNombre = new JLabel(empresaAutenticada.getNombre());
			lblNombre.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
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
			
			JLabel lblFuenteCurriculum = new JLabel("Curriculum:");
			lblFuenteCurriculum.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblFuenteCurriculum.setHorizontalAlignment(SwingConstants.CENTER);
			p = new JPanel(new BorderLayout());
			p.setMaximumSize(new Dimension(1000,40));
			p.setPreferredSize(new Dimension(1000,40));
			p.add(lblFuenteCurriculum);
			pnlListaContenido.add(p, BorderLayout.NORTH);
			
//			JList<Habilidad> list = new JList<Habilidad>();
//			modeloListaPersona = new DefaultListModel<Habilidad>();
//			modeloListaPersona.addListDataListener(new ListDataListener() {
//				
//				@Override
//				public void intervalRemoved(ListDataEvent e) {
//					// TODO Auto-generated method stub
//					actualizarCurriculum();
//				}
//				
//				@Override
//				public void intervalAdded(ListDataEvent e) {
//					// TODO Auto-generated method stub
//					actualizarCurriculum();
//				}
//				
//				@Override
//				public void contentsChanged(ListDataEvent e) {
//					// TODO Auto-generated method stub
//					actualizarCurriculum();
//				}
//			});
//			llenarListaCurriculum();
//			list.setModel(modeloListaPersona);
			
//			pnlListaContenido.add(new JScrollPane(list));
			
			p = new JPanel(new FlowLayout());
			JButton btnAnadir = new JButton("Añadir");
			
			btnAnadir.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					PnlBotonera.CardLayout.show(PnlBotonera.pnlFuncional, "pnlHabilidad");
				}
			});
			
			p.add(btnAnadir);
			JButton btnEliminar = new JButton("Eliminar");
			p.add(btnEliminar);
			pnlListaContenido.add(p, BorderLayout.SOUTH);
			
		}
		
		
//		
//		try {
//	        InputStream imageStream = PnlBotonera.class.getResourceAsStream("EditarPerfil.png");
//	        BufferedImage originalImage = ImageIO.read(imageStream);
//
//	        // Redimensiona la imagen a un tamaño más pequeño (50x50 pixeles)
//	        int width = 25;
//	        int height = 25;
//	        Image scaledImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
//
//	        // Convierte la imagen escalada en un BufferedImage
//	        BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
//	        Graphics2D g2d = resizedImage.createGraphics();
//	        g2d.drawImage(scaledImage, 0, 0, null);
//	        g2d.dispose();
//			
//	        JButton btnEditar =new JButton(new ImageIcon(resizedImage));
//	        btnEditar.setBounds(20, 80, 25, 25);
//	  
//
//	        pnlDatosContacto.add(btnEditar);
//	        
//	        btnEditar.addActionListener(new ActionListener() {
//				
//				@Override
//				public void actionPerformed(ActionEvent e) {
//					
//					PnlBotonera.pnlFuncional.add(pnlEditarPerfil,"pnlEditarPerfil");
//					PnlBotonera.CardLayout.show(PnlBotonera.pnlFuncional, "pnlEditarPerfil");
//					
//				}
//			});
//			
//	    } catch (IOException e) {
//	        e.printStackTrace();
//	    }
//		
//		
//		JLabel lblDatosPerfil = new JLabel("Datos:");
//		lblDatosPerfil.setHorizontalAlignment(SwingConstants.LEFT);
//		lblDatosPerfil.setFont(new Font("Tahoma", Font.BOLD, 15));
//		pnlDatosContacto.add(lblDatosPerfil);
//		
//		JLabel lblFuenteCurriculum = new JLabel("Curriculum:");
//		lblFuenteCurriculum.setFont(new Font("Tahoma", Font.BOLD, 15));
//		lblFuenteCurriculum.setBounds(488, 11, 105, 22);
//		pnlDatosContacto.add(lblFuenteCurriculum);
//		
//		JList list = new JList();
//		list.setBounds(399, 44, 286, 332);
//		pnlDatosContacto.add(list);
//		
//		JLabel lblTitPrincipal = new JLabel("Titulacion principal (ejemplo)");
//		lblTitPrincipal.setForeground(new Color(128, 128, 128));
//		lblTitPrincipal.setFont(new Font("Trebuchet MS", Font.PLAIN, 17));
//		lblTitPrincipal.setBounds(20, 50, 261, 23);
//		pnlDatosContacto.add(lblTitPrincipal);
//		
//		JLabel lblNombre = new JLabel("Nombre de la persona (ejemplo)");
//		lblNombre.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
//		lblNombre.setBounds(20, 23, 332, 31);
//		pnlDatosContacto.add(lblNombre);
//		
//		JLabel lblProvincia = new JLabel("Provincia:");
//		lblProvincia.setFont(new Font("Trebuchet MS", Font.PLAIN, 17));
//		lblProvincia.setBounds(20, 202, 80, 23);
//		pnlDatosContacto.add(lblProvincia);
//		JLabel lblProvU = new JLabel("");
//		lblProvU.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
//		lblProvU.setBounds(106, 199, 163, 48);
//		pnlDatosContacto.add(lblProvU);
//		JLabel lblNumTlfU = new JLabel("");
//		lblNumTlfU.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
//		lblNumTlfU.setForeground(new Color(0, 0, 160));
//		lblNumTlfU.setBounds(118, 163, 151, 39);
//		pnlDatosContacto.add(lblNumTlfU);
//		
//		
//		if (usuarioAutenticado instanceof Persona) {
//			Persona per = (Persona) usuarioAutenticado;
//			System.out.println(per.getUbicacion());
//			lblNombre.setText( per.getNombre() + " " + per.getApellidos() );
//			lblProvU.setText( per.getUbicacion() );
//			lblNumTlfU.setText( per.getTelefono() );
//			
//		}	
//		
//		JButton btnCrearPt = new JButton("Crear Puestos");
//		btnCrearPt.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				PnlBotonera.CardLayout.show( PnlBotonera.pnlFuncional, "pnlPuestoTrabajo");
//			}
//		});
//		btnCrearPt.setBounds(20, 347, 133, 23);
//		pnlDatosContacto.add(btnCrearPt);
		

		
		//TODO: Arreglar
	
//		lblEditarFoto.addMouseListener( new MouseAdapter() {			
//		@Override
//		public void mouseClicked(MouseEvent e) {
//			// TODO Auto-generated method stub
//			lblEditarFoto.setBackground(new Color(208, 235, 242));
//		}
//		@Override
//		public void mouseEntered(MouseEvent e) {
//			// TODO Auto-generated method stub
//			lblEditarFoto.setBackground(new Color(122, 199, 218));
//		}
//		
//	});
	
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

   
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		DatosFicheros datos = new DatosFicheros();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		PnlMiPerfil pnlPerfil = new PnlMiPerfil(DatosFicheros.getEmpresas().get(0));

		
		frame.setSize(750, 650);
		frame.setVisible(true);
		
	}
}
	

