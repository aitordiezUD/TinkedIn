package nube;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.concurrent.Flow;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.blob.models.BlobItem;

import datos.DatosFicheros;
import usuarios.Usuario;

public class ImagenesAzure {
	private final static String connectionString = "DefaultEndpointsProtocol=https;AccountName=tinkedin;AccountKey=q+hm0wS5nTIbBPsFRwQSpM7C2BTf67fMsVEY1o013ATlrUlBXXNJft+ZbdpfYvA8zn8WyvPLjMkX+AStV+gkVA==;EndpointSuffix=core.windows.net";
	private final static String containerNameFicheros = "tinkedinv1";
	private final static String containerNameBd = "tinkedinbd";
	private final static String rutaBaseLectura = "https://tinkedin.blob.core.windows.net/";
	
	
	public static String subirImagenFicheros(File f, String nombre) {        
     // Crea el cliente del servicio de blobs
        BlobContainerClient blobContainerClient = new BlobServiceClientBuilder().connectionString(connectionString)
        		.buildClient().getBlobContainerClient(containerNameFicheros);
        
//      Crear cliente de blob
        BlobClient blobClient = blobContainerClient.getBlobClient(nombre);
        
     // Sube el archivo al contenedor
        blobClient.uploadFromFile(f.getAbsolutePath());
        
        return rutaBaseLectura + containerNameFicheros + nombre;
	}
	
	public static String subirImagenBD(File f, String nombre) {        
	     // Crea el cliente del servicio de blobs
	        BlobContainerClient blobContainerClient = new BlobServiceClientBuilder().connectionString(connectionString)
	        		.buildClient().getBlobContainerClient(containerNameBd);
	        
//	      Crear cliente de blob
	        BlobClient blobClient = blobContainerClient.getBlobClient(nombre);
	        
	     // Sube el archivo al contenedor
	        blobClient.uploadFromFile(f.getAbsolutePath());
	
	        return rutaBaseLectura + containerNameBd + nombre;
	}
	
	public static JLabel crearImagen(Usuario u, int width, int height) {
		InputStream is = null;
		JLabel label = null;
        try {
            URL url = new URL(u.getFotoDePerfil());
            is = url.openStream();
//            int width = 100;
//	        int height = 100;
            BufferedImage originalImage = ImageIO.read(is);
            
            Image scaledImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);

	        // Convierte la imagen escalada en un BufferedImage
	        BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	        Graphics2D g2d = resizedImage.createGraphics();
	        Shape circle = new Ellipse2D.Float(0, 0, width, height);
	        g2d.setClip(circle);
	        g2d.drawImage(scaledImage, 0, 0, null);
	        g2d.dispose();
            
	        label =new JLabel(new ImageIcon(resizedImage));
	        label.setBounds(35, 7, 150, 150);
	        label.setBackground(new Color(240, 240, 240));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return label;	
	}
	
	public static void deleteBlobsFicheros() {
        // Crear el cliente del servicio Blob
        BlobContainerClient containerClient = new BlobServiceClientBuilder()
        		.connectionString(connectionString)
        		.buildClient().
        		getBlobContainerClient(containerNameFicheros);

        // Listar todos los blobs en el contenedor
        for (BlobItem blobItem : containerClient.listBlobs()) {
            // Obtener el nombre del blob
            String blobName = blobItem.getName();

            // Eliminar el blob
            containerClient.getBlobClient(blobName).delete();
            System.out.println("Blob eliminado: " + blobName);
        }

        System.out.println("Todos los blobs han sido eliminados.");
	}
	
	public static void deleteBlobsBd() {
        // Crear el cliente del servicio Blob
        BlobContainerClient containerClient = new BlobServiceClientBuilder()
        		.connectionString(connectionString)
        		.buildClient().
        		getBlobContainerClient(containerNameBd);

        // Listar todos los blobs en el contenedor
        for (BlobItem blobItem : containerClient.listBlobs()) {
            // Obtener el nombre del blob
            String blobName = blobItem.getName();

            // Eliminar el blob
            containerClient.getBlobClient(blobName).delete();
            System.out.println("Blob eliminado: " + blobName);
        }

        System.out.println("Todos los blobs han sido eliminados.");
	}
	

}
