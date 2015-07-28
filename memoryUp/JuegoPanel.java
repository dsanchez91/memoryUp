package memoryUp;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JButton;
import javax.swing.JPanel;

public class JuegoPanel extends JPanel
{
    private static final long serialVersionUID = 1L;

    private botonPanel panelCartas; //Panel para las cartas
    private ControlPanel panelControles;     //Panel para los botones de control
    private Dimension tamano;          //Tama–o del frame
    private Image imagenFondo; //imagen de fondo
    private int tematica;  //tematica del juego
    private int dificultad; //dificultad del juego
    ArrayList<Carta> listaCartasAnimales; //Array con las cartas
    ArrayList<Carta> listaCartasFrutas; //Array con las cartas
    ArrayList<Carta> listaCartasActuales; //Array con las cartas actuales
    JButton[] botonesControl; //Array de botones de control
    CicloJuego clJuego;       //Instancia de control del juego
    public final int NUM_IMAGENES = 10; //Numero de imagenes que vamos a utilizar

    public JuegoPanel()
    {
    	//comenzamos siempre con la tematica de animales y 8 cartas
    	tematica = 0;
    	dificultad = 8;
    	
    	CicloJuego.NUM_CARTAS = dificultad;
    	
        //Botones de control
        botonesControl = new JButton[4];
        botonesControl[0] = new JButton("Cambiar tematica"); 
        botonesControl[1] = new JButton("Cambiar dificultad"); 
        botonesControl[2] = new JButton("Reiniciar");
        botonesControl[3] = new JButton("Salir");


        try {
        	//cargamos la imagen de fondo
        	imagenFondo = CargaImagen.obtenerImagen(new URL("file:images/back.gif"));
        	
		} catch (MalformedURLException e) {
			System.out.println("Error: " + e.getMessage());
		}
        
        //reservamos memoria para las listas de cartas
        listaCartasAnimales = new ArrayList<Carta>(NUM_IMAGENES * 2);
        listaCartasFrutas = new ArrayList<Carta>(NUM_IMAGENES * 2);
        listaCartasActuales = new ArrayList<Carta>(CicloJuego.NUM_CARTAS);

        //Creamos las cartas del tipo animales y las a–adimos al array
        for(int i = 0; i < NUM_IMAGENES; i++)
        {
        	 Carta cb;

            cb = new Carta(i,0);
            cb.setBorderPainted(false);
            listaCartasAnimales.add(cb);

            cb = new Carta(i,0);
            cb.setBorderPainted(false);
            listaCartasAnimales.add(cb);
            
            
        }

      //Creamos las cartas del tipo frutas y las a–adimos al array
        for(int i = 0; i < NUM_IMAGENES; i++)
        {    	
        	Carta cb1;
        	 
        	cb1 = new Carta(i,1);
            cb1.setBorderPainted(false);
            listaCartasFrutas.add(cb1);
            
            cb1 = new Carta(i,1);
            cb1.setBorderPainted(false);
            listaCartasFrutas.add(cb1);
        }


        //Creamos el controlador de juego (game loop)
        clJuego = new CicloJuego(this);

        //Creamos los paneles para las cartas y para los botones de control
        panelCartas = new botonPanel(obtenerListaCartas(), 
                imagenFondo);
        
        panelControles = new ControlPanel(botonesControl, 
                (int)panelCartas.getPreferredSize().getWidth());

        //El tama–o vendra dado por la suma de los tama–os de los dos paneles
        tamano = new Dimension((int)(panelCartas.getPreferredSize().getWidth()),
                (int)(panelCartas.getPreferredSize().getHeight() + 
                        panelControles.getPreferredSize().getHeight()));
        
        //a–adimos los paneles a la ventana
        setLayout(new BorderLayout());
        add(panelCartas, BorderLayout.NORTH);
        add(panelControles, BorderLayout.SOUTH);
    }

    /*
     * Resetea las cartas y vuelve a comenzar el juego
     */
    public void reiniciar()
    {
        
    	if(tematica == 0)//tematica animales
    	{	//Poner boca abajo todas las cartas de animales
    		 for(Carta cb : listaCartasAnimales)
    	        {
    	            cb.voltear();
    	            cb.setVisible(true);       
    	        }
    		 	
    	        botonesControl[2].setText("Reiniciar");
    	        
    	        //llamamos a la funcion reiniciar y le pasamos la nueva lista de cartas que obtenemos
    	        //mediannte la funcion obtenercartas
    	        panelCartas.reiniciar(obtenerListaCartas(), 
    	                imagenFondo);
    	        
    	        //forzamos el repintado
    	        actualizar(0);
    	}
    	else if(tematica  == 1)//tematica frutas
    	{
    		//Poner boca abajo todas las cartas de frutas
    		 for(Carta cb : listaCartasFrutas)
    	        {
    	            cb.voltear();
    	            cb.setVisible(true);
    	            
    	        }

    	        botonesControl[2].setText("Reiniciar");
    	        
    	      //llamamos a la funcion reiniciar y le pasamos la nueva lista de cartas que obtenemos
    	        //mediannte la funcion obtenercartas
    	        panelCartas.reiniciar(obtenerListaCartas(), 
    	                imagenFondo);
    	        
    	        //forzamos el repintado
    	        actualizar(0);
    	}
       
    }
    
    /*
     * Funcion que se ejecutara al pulsar el boton de cambiar tematica y que
     * alternara entre los animales y las frutas, para ello se valdra de 
     * la funcion reiniciar de la clase botonPanel
     * 
     */
    public void cambiar_tematica()
    {
    	if(tematica == 0)
    	{
    		//Poner boca abajo todas las cartas
            for(Carta cb : listaCartasAnimales)
            {
                cb.voltear();
                cb.setVisible(true);
                
            }
            
            //cambio la tematica
            tematica = 1;
    	}
    		
    	else if(tematica == 1)
    	{
    		//Poner boca abajo todas las cartas
            for(Carta cb : listaCartasFrutas)
            {
                cb.voltear();
                cb.setVisible(true);
                
            }
            //cambio la tematica
            tematica = 0;
    	}
    	
    	//hacemos uso de la funcion reiniciar
    	panelCartas.reiniciar(obtenerListaCartas(), 
                imagenFondo);
        
        //forzamos el repintado
        actualizar(0);
    }
    
    /*
     * 
     * Funcion que se ejecutara al pulsar el boton de cambiar dificultad
     * que ira aumentando la dificultad hasta un limite y reiniciara 
     * todas las cartas del tablero
     * 
     */
    public void cambiar_dificultad()
    {
    	//dependiendo de la dificultad activa en ese momento, ira aumentandola hasta un limite de 16
    	if(CicloJuego.NUM_CARTAS == 8)
    	{
    		CicloJuego.NUM_CARTAS = 12;
    	}
    	else if(CicloJuego.NUM_CARTAS == 12)
    	{
    		CicloJuego.NUM_CARTAS = 14;
    	}
    	else if(CicloJuego.NUM_CARTAS == 14)
    	{
    		CicloJuego.NUM_CARTAS = 16;
    	}
    	else if(CicloJuego.NUM_CARTAS == 16)
    	{
    		CicloJuego.NUM_CARTAS = 8;
    	}
    	
    	if(tematica == 0)
    	{
    		//Poner boca abajo todas las cartas
            for(Carta cb : listaCartasAnimales)
            {
                cb.voltear();
                cb.setVisible(true);
                
            }
    	}
        else if(tematica == 1)
        {
        	//Poner boca abajo todas las cartas
            for(Carta cb : listaCartasFrutas)
            {
                cb.voltear();
                cb.setVisible(true);
                
            }
        }
    	
    	//llamamos a la funcion reiniciar
    	panelCartas.reiniciar(obtenerListaCartas(), 
                imagenFondo);
    	
    	//forzamos el repintado
        actualizar(0);
    }

    /*
     * Devuelve una lista de parejas aleatorias de la lista de cartas que tenemos
     * almacenadas dependiendo de la tematica y de la dificultad
     */
    public ArrayList<Carta> obtenerListaCartas()
    {
    	
    	if(tematica == 0)//el tema no se ha cambiado
    	{
    		//borramos las cartas que tenga la lista de actuales
    		listaCartasActuales.clear();
    		
    		//calculamos el numero de images que necesitamos
    		int numImgs = NUM_IMAGENES * 2;
            
    		//bucle que obtendra las cartas de manera aleatoria
            for(int i = (int)(Math.random() * numImgs) * 2; listaCartasActuales.size() != CicloJuego.NUM_CARTAS; i += 2)
            {
            		//aqui es donde obtenemos la pareja de forma aleatoria
                    listaCartasActuales.add(listaCartasAnimales.get(i % numImgs));
                    listaCartasActuales.add(listaCartasAnimales.get((i % numImgs) + 1));      
            }
            //funcion que realiza permutaciones entre los elementos de un array para asegurarnos la aletoriedad
            Collections.shuffle(listaCartasActuales);
    	}
    	else if(tematica == 1)//esta parte es igual solo que obtenemos las cartas de la lista de frutas
    	{
    	
    		listaCartasActuales.clear();
    		int numImgs = NUM_IMAGENES * 2;
            
            for(int i = (int)(Math.random() * numImgs) * 2; listaCartasActuales.size() != CicloJuego.NUM_CARTAS; i += 2)
            {
            		//aqui es donde obtenemos la pareja de forma aleatoria
                    listaCartasActuales.add(listaCartasFrutas.get(i % numImgs));
                    listaCartasActuales.add(listaCartasFrutas.get((i % numImgs) + 1));         
            }
                 
            Collections.shuffle(listaCartasActuales);                
    	}   

        return listaCartasActuales;
    }

   //devuelve el tama–o del frame
    public Dimension getPreferredSize()
    {
        return tamano;
    }

   //vuelve a pintar el panel
    public void actualizar(int millis)
    {
        panelCartas.actualizarInterfaz(millis);
    }
}
