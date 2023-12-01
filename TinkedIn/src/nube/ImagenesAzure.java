package nube;

import java.io.File;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClientBuilder;

public class ImagenesAzure {
	public static void subirImagen(File f, String nombre) {
		String connectionString = "DefaultEndpointsProtocol=https;AccountName=tinkedin;AccountKey=q+hm0wS5nTIbBPsFRwQSpM7C2BTf67fMsVEY1o013ATlrUlBXXNJft+ZbdpfYvA8zn8WyvPLjMkX+AStV+gkVA==;EndpointSuffix=core.windows.net";
        String containerName = "tinkedinv1";
        
     // Ruta local del archivo a subir
//        String filePath = "foto.jpg";
        
     // Crea el cliente del servicio de blobs
        BlobContainerClient blobContainerClient = new BlobServiceClientBuilder().connectionString(connectionString)
        		.buildClient().getBlobContainerClient(containerName);
        
//        Crear cliente de blob
        BlobClient blobClient = blobContainerClient.getBlobClient(nombre);
        
     // Sube el archivo al contenedor
        blobClient.uploadFromFile(f.getAbsolutePath());
	}
}
