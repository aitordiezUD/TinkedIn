package ventanas;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class PnlLogIn extends JPanel {
	private JTextField tfCorreo;
	private PnlRegistroPersona pnlRegistroPersona;
	private PnlRegistroEmpresa pnlRegistroEmpresa;
	private JPasswordField pfContrasnya;

	public PnlLogIn(JPanel pnlContenido, CardLayout layoutVentana) {
		setBackground(new Color(202, 232, 232));
		setSize(900, 650);
		setLayout(null);
		

		
		JPanel pnlFunc = new JPanel();
		pnlFunc.setBackground(Color.WHITE);
		pnlFunc.setBounds(0, 0, 410, 650);
		pnlFunc.setLayout(null);
		add(pnlFunc);
		
		pnlRegistroPersona = new PnlRegistroPersona(pnlContenido, layoutVentana);
		pnlContenido.add(pnlRegistroPersona,"pnlRegistroPersona");
		
		pnlRegistroEmpresa = new PnlRegistroEmpresa(pnlContenido, layoutVentana);
		pnlContenido.add(pnlRegistroEmpresa,"pnlRegistroEmpresa");
		
		JLabel lblBienvenida = new JLabel("Bienvenido de nuevo");
		lblBienvenida.setHorizontalAlignment(SwingConstants.CENTER);
		lblBienvenida.setFont(new Font("Trebuchet MS", Font.BOLD, 26));
		lblBienvenida.setBounds(10, 108, 390, 55);
		pnlFunc.add(lblBienvenida);
		
		tfCorreo = new JTextField();
		tfCorreo.setBounds(53, 210, 310, 25);
		pnlFunc.add(tfCorreo);
		tfCorreo.setColumns(10);
		
		JLabel lblCorreo = new JLabel("Correo electrónico\r\n");
		lblCorreo.setBounds(53, 195, 206, 14);
		pnlFunc.add(lblCorreo);
		
		JLabel lblContrasena = new JLabel("Contraseña");
		lblContrasena.setBounds(53, 251, 206, 14);
		pnlFunc.add(lblContrasena);
		
		JButton btnIniciarSesion = new JButton("Iniciar sesión\r\n");
		btnIniciarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PnlBotonera pnlBotones = new PnlBotonera();
				pnlContenido.add(pnlBotones,"pnlBotones");
				layoutVentana.show(pnlContenido, "pnlBotones");
				
			}
		});
		btnIniciarSesion.setBounds(53, 319, 310, 38);
		pnlFunc.add(btnIniciarSesion);
		
		JLabel lblRegistro = new JLabel("¿No tienes cuenta todavía? Regístrate");
		lblRegistro.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistro.setBounds(53, 368, 310, 14);
		pnlFunc.add(lblRegistro);
		
		pfContrasnya = new JPasswordField();
		pfContrasnya.setBounds(53, 266, 310, 25);
		pnlFunc.add(pfContrasnya);
		
//		Listener para abrir la ventana de registrarse
		lblRegistro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblRegistro.setText("<html><u>¿No tienes cuenta todavía? Regístrate</u></html>");
				lblRegistro.setForeground(new Color(122, 199, 218));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				lblRegistro.setText("¿No tienes cuenta todavía? Regístrate");
				lblRegistro.setForeground(new Color(0, 0, 0));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// Array de opciones
		        Object[] opciones = {"Empresa", "Persona"};

		        // Muestra el JOptionPane
		        int seleccion = JOptionPane.showOptionDialog(
		                null,
		                "¿Qué tipo de cuenta quieres crear?",
		                "Selecciona un tipo de cuenta",
		                JOptionPane.YES_NO_OPTION,
		                JOptionPane.QUESTION_MESSAGE,
		                null,
		                opciones,
		                opciones[0]
		        );

		        // Comprueba la opción seleccionada
		        if (seleccion == JOptionPane.YES_OPTION) {
		        	layoutVentana.show(pnlContenido, "pnlRegistroEmpresa");
		        } else if (seleccion == JOptionPane.NO_OPTION) {
		            // Opción "Persona" seleccionada
		        	layoutVentana.show(pnlContenido, "pnlRegistroPersona");
		        } else {}
				
				
				
				
			}
		});
		

	}
}
