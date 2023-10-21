package ventanas;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class PnlLogIn extends JPanel {
	private JTextField tfCorreo;
	private JTextField tfContrasena;

	public PnlLogIn() {
		setBackground(new Color(202, 232, 232));
		setSize(900, 650);
		setLayout(null);
		
		JPanel pnlFunc = new JPanel();
		pnlFunc.setBounds(0, 0, 410, 650);
		add(pnlFunc);
		pnlFunc.setLayout(null);
		
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
		
		tfContrasena = new JTextField();
		tfContrasena.setColumns(10);
		tfContrasena.setBounds(53, 266, 310, 25);
		pnlFunc.add(tfContrasena);
		
		JLabel lblContrasena = new JLabel("Contraseña");
		lblContrasena.setBounds(53, 251, 206, 14);
		pnlFunc.add(lblContrasena);
		
		JButton btnIniciarSesion = new JButton("Iniciar sesión\r\n");
		btnIniciarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnIniciarSesion.setBounds(53, 319, 310, 38);
		pnlFunc.add(btnIniciarSesion);
		
		JLabel lblRegistro = new JLabel("¿No tienes cuenta todavía? Regístrate");
		lblRegistro.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistro.setBounds(53, 368, 310, 14);
		pnlFunc.add(lblRegistro);
	}
}
