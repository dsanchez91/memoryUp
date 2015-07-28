package memoryUp;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.JPanel;

public class botonPanel extends JPanel
{
    private static final long serialVersionUID = 1L;

    private Dimension tam; //dimensiones que tendra el panel
    private Image fondo;//imagen de fondo que tendra


    /*
     * Crea el panel para asociar a el la lista de cartas y le asigna una imagen de fondo.
     * La dimension estara acorde al numero de cartas y su tama–o
     *
     */
    public botonPanel(ArrayList<Carta> listaCartas, Image imagenFondo)
    {
        int ancho = (listaCartas.get(0)).getWidth();
        int alto = (listaCartas.get(0)).getHeight();
        int numPorLinea =4;

        tam = new Dimension(numPorLinea * ancho, numPorLinea * alto);

        setLayout(new GridLayout(numPorLinea, numPorLinea));
        setOpaque(false);
        
        reiniciar(listaCartas, imagenFondo);
    }

    /*
     * Reinicia el panel eliminando las cartas actuales y a–adiendolas de nuevo
     * ademas fuerza el repintado
     */
    public void reiniciar(ArrayList<Carta> listaCartas, Image imagenFondo)
    {
    	//llamamos al metodo removeAll para borrar todo lo anterior
        removeAll();

        //recorremos la lista a–adiendo las cartas
        for(Carta cb : listaCartas)
            add(cb);

        //a–adimos la imagen de fondo si no estaba antes
        if(imagenFondo != null)
            fondo = imagenFondo;

        //actualiza los componentes del panel haciendo un "switch" entre visible y no visible
        ((Component)getComponent(0)).setVisible(false);
        ((Component)getComponent(0)).setVisible(true);
        
        //repintamos todo el panel
        repaint();
    }

    //Pinta el fondo y las cartas
    public void paint(Graphics g)
    {
        g.drawImage(fondo, 0, 0, this);
        super.paint(g);
    }

    //Devuelve el tama–o del panel
    public Dimension getPreferredSize()
    {
        return tam;
    }

    //Repinta el panel y espera un tiempo
    public void actualizarInterfaz(int milliseconds)
    {
        repaint();
        try
        {
            Thread.sleep(milliseconds);
        }
        catch(InterruptedException e)
        {
        }
    }
}
