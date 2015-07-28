package memoryUp;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class BotonListener implements ActionListener
{
    private JButton boton; // Bot—n que ha sido pulsado
    private Object sincro; // Objeto sincronizado para usar el bot—n
    private boolean espera; // Indica la espera por una acci—n

    public BotonListener()
    {
        sincro = new Object();
    }

    //Metodo de obligada implementacion de la clase ActionListener
    public void actionPerformed(ActionEvent event)
    {
        boton = (JButton)event.getSource();
        espera = false;

        synchronized(sincro)
        {
            sincro.notifyAll();
        }
    }
    
    //"Pausa" el programa esperando hasta que un boton sea pulsado. Devuelve la referencia del bot—n pulsado
    public JButton esperaBoton()
    {
        espera = true;

        try
        {
            synchronized(sincro)
            {
                while(espera == true)
                    sincro.wait();
            }
        }
        catch(InterruptedException e)
        {
        }

        return boton;
    }
}
