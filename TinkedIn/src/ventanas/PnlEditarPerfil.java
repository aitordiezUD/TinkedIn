package ventanas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PnlEditarPerfil  extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textFieldExp;
	private JTextField textFieldDes;
	public PnlEditarPerfil() {
		setLayout(null);
		setSize(750,650);
		
		JPanel panelFotoPerfil = new JPanel();
		panelFotoPerfil.setBounds(243, 29, 680, 305);
		add(panelFotoPerfil);
		panelFotoPerfil.setLayout(null);
		
		JLabel lblNewLabel_9 = new JLabel("Apellidos");
		lblNewLabel_9.setBounds(146, 36, 98, 14);
		panelFotoPerfil.add(lblNewLabel_9);
		
		JLabel lblNewLabel_8 = new JLabel("Nombre");
		lblNewLabel_8.setBounds(146, 11, 50, 14);
		panelFotoPerfil.add(lblNewLabel_8);
		
		
		JPanel pnlDatos = new JPanel();
		pnlDatos.setBounds(70, 358, 680, 215);
		add(pnlDatos);
		pnlDatos.setLayout(null);
		
		
		
		try {
	        // Carga la imagen original desde el archivo en el paquete "imagenes"
	        InputStream imageStream = PnlBotonera.class.getResourceAsStream("FotoPerfil.png");
	        BufferedImage originalImage = ImageIO.read(imageStream);

	        // Redimensiona la imagen a un tamaño más pequeño (50x50 pixeles)
	        int width = 100;
	        int height = 100;
	        Image scaledImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);

	        // Convierte la imagen escalada en un BufferedImage
	        BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	        Graphics2D g2d = resizedImage.createGraphics();
	        g2d.drawImage(scaledImage, 0, 0, null);
	        g2d.dispose();
	        
			//Crea in JLabel y asigna la imagen
	        JLabel lblGraphics = new JLabel(new ImageIcon(resizedImage));
	        lblGraphics.setBackground(new Color(240, 240, 240));
    		lblGraphics.setBounds(10, 11, 100, 100);
	        panelFotoPerfil.add(lblGraphics);
	        
	        textField = new JTextField();
	        textField.setBounds(250, 8, 96, 20);
	        panelFotoPerfil.add(textField);
	        textField.setColumns(10);
	        
	        textField_1 = new JTextField();
	        textField_1.setBounds(250, 33, 96, 20);
	        panelFotoPerfil.add(textField_1);
	        textField_1.setColumns(10);
	        
	        
	        JLabel lblNewLabel = new JLabel("Correo Electrónico");
	        lblNewLabel.setBounds(146, 95, 94, 16);
	        panelFotoPerfil.add(lblNewLabel);
	        
	        textField_3 = new JTextField();
	        textField_3.setBounds(250, 93, 96, 20);
	        panelFotoPerfil.add(textField_3);
	        textField_3.setColumns(10);
	        
	        JLabel lblNewLabel_1 = new JLabel("NºTeléfono");
	        lblNewLabel_1.setBounds(150, 122, 94, 14);
	        panelFotoPerfil.add(lblNewLabel_1);
	        
	        textField_2 = new JTextField();
	        textField_2.setBounds(250, 124, 96, 20);
	        panelFotoPerfil.add(textField_2);
	        textField_2.setColumns(10);
	        
	        JLabel lblNewLabel_6 = new JLabel("LinkedIn");
	        lblNewLabel_6.setBounds(146, 162, 150, 14);
	        panelFotoPerfil.add(lblNewLabel_6);
	        
	        JLabel lblNewLabel_7 = new JLabel("Github");
	        lblNewLabel_7.setBounds(146, 187, 140, 14);
	        panelFotoPerfil.add(lblNewLabel_7);
	        
	        textField_4 = new JTextField();
	        textField_4.setBounds(250, 159, 96, 20);
	        panelFotoPerfil.add(textField_4);
	        textField_4.setColumns(10);
	        
	        textField_5 = new JTextField();
	        textField_5.setBounds(250, 184, 96, 20);
	        panelFotoPerfil.add(textField_5);
	        textField_5.setColumns(10);
			
			
	  
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setBounds(266, 108, 48, 14);
		pnlDatos.add(lblNewLabel_3);
		
		JLabel lblDestreza = new JLabel("Destreza:");
		lblDestreza.setBounds(10, 70, 70, 14);
		pnlDatos.add(lblDestreza);
		
		JLabel lblExperiencia = new JLabel("Experiencia:");
		lblExperiencia.setBounds(10, 11, 96, 14);
		pnlDatos.add(lblExperiencia);
		
		JComboBox<String> comboBoxExp = new JComboBox<String>();
		comboBoxExp.setBounds(479, 7, 191, 22);
		pnlDatos.add(comboBoxExp);
		
		JButton btnNewButton = new JButton("Guardar");
		btnNewButton.setBounds(376, 181, 89, 23);
		pnlDatos.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.setBounds(225, 181, 89, 23);
		pnlDatos.add(btnNewButton_1);
		
		JButton btnAnadirExp = new JButton("+");
		btnAnadirExp.setBounds(274, 7, 45, 22);
		pnlDatos.add(btnAnadirExp);
		
		JButton btnEliminarExp = new JButton("-");
		btnEliminarExp.setBounds(333, 7, 40, 22);
		pnlDatos.add(btnEliminarExp);
		
		JComboBox<String> comboBoxDes = new JComboBox();
		comboBoxDes.setBounds(479, 66, 191, 22);
		pnlDatos.add(comboBoxDes);
		
		JButton btnAnadirDes = new JButton("+");
		btnAnadirDes.setBounds(274, 66, 45, 22);
		pnlDatos.add(btnAnadirDes);
		
		JButton btnEliminarDes = new JButton("-");
		btnEliminarDes.setBounds(333, 66, 40, 23);
		pnlDatos.add(btnEliminarDes);
		
		textFieldExp = new JTextField();
		textFieldExp.setBounds(77, 8, 187, 20);
		pnlDatos.add(textFieldExp);
		textFieldExp.setColumns(10);
		
		textFieldDes = new JTextField();
		textFieldDes.setBounds(77, 67, 187, 20);
		pnlDatos.add(textFieldDes);
		textFieldDes.setColumns(10);
	
		
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
		
		
		
//		//BOTON AÑADIR EXP
		btnAnadirExp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              // Obtener el texto del JTextField
              String texto = textFieldExp.getText();

                // Añadir el texto al JComboBox
               comboBoxExp.addItem(texto);

                // Limpiar el JTextField
            textFieldExp.setText("");
        }
		});
	btnAnadirDes.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          // Obtener el texto del JTextField
          String texto = textFieldDes.getText();

            // Añadir el texto al JComboBox
           comboBoxDes.addItem(texto);

            // Limpiar el JTextField
        textFieldDes.setText("");
    }
	});
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(900, 650);
		frame.getContentPane().add(new PnlEditarPerfil());
		frame.setVisible(true);
		
	}
}