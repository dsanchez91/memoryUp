package memoryUp;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.imageio.ImageIO;


/*
 * Clase de carga de imagenes que obtendra las mismas de memoria local
 * y las almacenara en la cache del programa
 * 
 */

public class CargaImagen
{
    private static HashMap<String, BufferedImage> cache;

    public synchronized static BufferedImage obtenerImagen(URL url)
    {
    	 cache = new HashMap<String, BufferedImage>();
    	 
        BufferedImage imagen = cache.get(url.toString());

        if(imagen == null)
        {
            imagen = cargarImagen(url);
            cache.put(url.toString(), imagen);
        }

        return imagen;
    }

    private static BufferedImage cargarImagen(URL imagenURL)
    {
        java.awt.Image imagenOrigen;

        try
        {
            imagenOrigen = ImageIO.read(imagenURL);
        }
        catch(IOException e)
        {
            e.printStackTrace();
            return null;
        }

        return cargarImagen(imagenOrigen);
    }

    private static BufferedImage cargarImagen(Image imagenOrig)
    {
        int imgAncho;
        int imgAlto;
        BufferedImage buffer;
        Graphics graficos;
        
        try
        {
            MediaTracker track = new MediaTracker(new Panel());
            track.addImage(imagenOrig, 0);
            track.waitForID(0);

            if(track.statusID(0, true) != MediaTracker.COMPLETE)
                throw new RuntimeException("Unable to load image");
        }
        catch(InterruptedException e)
        {
      
        }


        imgAncho = imagenOrig.getWidth(null);
        imgAlto = imagenOrig.getHeight(null);
        
        buffer = new BufferedImage(imgAncho, imgAlto,
                BufferedImage.TYPE_INT_RGB);

        graficos = buffer.createGraphics();
        graficos.drawImage(imagenOrig, 0, 0, null);

        return buffer;
    }
}
