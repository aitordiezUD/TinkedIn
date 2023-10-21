package pruebas;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.image.BufferedImage;

public class CargarImagenURL{
	
    public static void main(String[] args) {
        try {
            // URL de la imagen que deseas cargar
            String imageUrl = "https://i.imgur.com/VtnFAmT.jpeg";

            // Crea una URL a partir de la dirección HTTPS
            URL url = new URL(imageUrl);

            // Abre una conexión a la URL
            InputStream inputStream = url.openStream();

            // Lee la imagen desde la conexión
            BufferedImage imagen = ImageIO.read(inputStream);

            // Cierra la conexión
            inputStream.close();

            // Puedes utilizar la imagen cargada como desees
            // Por ejemplo, mostrarla en un JFrame
             JFrame frame = new JFrame();
             JLabel label = new JLabel(new ImageIcon(imagen));
             frame.add(label);
             frame.pack();
             frame.setVisible(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
