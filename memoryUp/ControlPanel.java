package memoryUp;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/*
 * Esta clase creara el panel con los botones denominados como botones de control
 * 
 * 
 */

public class ControlPanel extends JPanel
{
    private static final long serialVersionUID = 1L;

    private Dimension tamano;//dimension del panel
    public static final int ALTURA = 40;//altura del panel (sera siempre la misma)

    /*
     * Crea un panel con el array de botones que se le pasa como parametro
     * 
     */
    public ControlPanel(JButton[] botones, int ancho)
    {
        tamano = new Dimension(ancho, ALTURA);
        
        setBackground(Color.DARK_GRAY);

        setLayout(new FlowLayout(FlowLayout.CENTER));

        if(botones != null)
            for(int i = 0; i < botones.length; i++)
                add(botones[i]);
    }

    /*
     * Crea un panel vacio
     */
    public ControlPanel(int ancho)
    {
        this(null, ancho);
    }

    //Devuelve la dimension del panel
    public Dimension getPreferredSize()
    {
        return tamano;
    }
}
