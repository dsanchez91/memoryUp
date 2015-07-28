package memoryUp;

import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

//Un boton con un icono que representa una carta
public class Carta extends JButton
{
    private static final long serialVersionUID = 1L;

    private int numeroCarta; //Numero identificattivo de la imagen
    private Icon imagenCarta; //Imagen de la carta (levantada)
    private Icon imagenFondoCarta;   //Parte de atras de la carta
    private boolean levantada; //Nos indica si esta levantada la carta o no

    public Carta(int numC,int tema)
    {
        numeroCarta = numC;
        levantada = false;
         
        try
        {
        	//dependiendo del tipo se obtendra una imagen u otra
        	
        	if(tema == 0)
        	{
        		imagenCarta = new ImageIcon(CargaImagen.obtenerImagen(new URL("file:animals/" + numeroCarta + ".gif")));

                imagenFondoCarta = new ImageIcon(CargaImagen.obtenerImagen(new URL("file:images/bg.gif")));
        	}
        	else if(tema == 1)
        	{
        	
        		imagenCarta = new ImageIcon(CargaImagen.obtenerImagen(new URL("file:fruits/" + numeroCarta + ".gif")));

                imagenFondoCarta = new ImageIcon(CargaImagen.obtenerImagen(new URL("file:images/bg.gif")));
        	}
        		
        }
        catch(MalformedURLException e)
        {
            System.out.println("Error: " + e.getMessage());
            System.exit(0);
        }
        //set del numero de la carta en formato string para facilitar la comparacion
        setActionCommand(String.valueOf(numeroCarta));
        
        //set de la imagen de fondo
        setIcon(imagenFondoCarta);
    }

 
    //metodo get del numero de la carta
	public int getNumber()
    {
        return numeroCarta;
    }

	//metodo de comparacion entre cartas
    public boolean iguales(Carta card)
    {
        return numeroCarta == card.numeroCarta ? true : false;
    }
    
    //metodo que cambia la imagen de la parte trasera de la carta por la delantera
    public void levantar()
    {
        setIcon(imagenCarta);
        levantada = true;
    }
    
    //metodo contrario al anterior
    public void voltear()
    {
        setIcon(imagenFondoCarta);
        levantada = false;
    }

    //devuelve si la carta esta levantada o no
    public boolean estaLevantada()
    {
        return levantada;
    }

    //devuelve el ancho de la carta
    public int getWidth()
    {
        return imagenCarta.getIconWidth();
    }

    //devuelve el alto de la carta
    public int getHeight()
    {
        return imagenCarta.getIconHeight();
    }
    
}
