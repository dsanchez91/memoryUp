package memoryUp;


import javax.swing.JFrame;

import javax.swing.WindowConstants;

public class IniciaJuego
{
    private CicloJuego juego; //objeto que controlara el game loop (ciclo de juego)
    private JuegoPanel panel; //objeto que representa al panel de juego
    private String titulo; //titulo del panel
 
    
    //Constructor que le asigna un titulo y el panel de juego
    public IniciaJuego(String tituloFrame)
    {
    	
        this.titulo = tituloFrame;
        
        //creamos una nueva instancia del panel de juego (que contiene el panel de control y el panel de cartas)
        panel = new JuegoPanel();
        
        //el panel de juego que contiene la instancia panel sera el game loop de esta clase
        juego = panel.clJuego;
        
        //mostramos la interfaz
        crearYMostrarInterfaz();

    }

    //Crea y muestra la interfaz de usuario
    private void crearYMostrarInterfaz()
    {
        JFrame frame = new JFrame(titulo);//reservar memoria para el frame
        frame.getContentPane().add(panel);//a–adir el panel de juego
        frame.setResizable(false);//no permitimos que sea redimensinable
        frame.setSize(panel.getPreferredSize());//le damos el tama–o del panel que contiene
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//indicamos que se cierre la aplicacion al pulsar el boton de cerrar
        frame.pack();//llamada al metodo pack para poder mostrarla
        frame.setVisible(true);//la hacemos visible
    }
    
    //Esta funcion inicia todo el juego
    public static void main(String[] args)
    {
        IniciaJuego controlador = new IniciaJuego("MemoryUP");
        //ejecutamos el game loop
        controlador.juego.jugar();
        //cuando salga del game loop se cerrara la aplicacion
        System.exit(0);
    }
}
