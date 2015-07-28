package memoryUp;

import javax.swing.JButton;
import javax.swing.JOptionPane;

/*
 * 
 * Esta clase es la que controla el ciclo de juego, cambiando la interfaz segun
 * sea necesario con la ayuda de un listener que asocia a cada carta y esperando por la pulsacion
 * de dos botones
 */
public class CicloJuego
{
    private BotonListener btnListener; //Listener para las cartas
    private JuegoPanel interfazJuego; //Panel de juego donde estaran los controles y las cartas

    public static int NUM_CARTAS; //total de cartas en juego

    /*
     * Crea un nuevo control de juego para la interfaz
     */
    public CicloJuego(JuegoPanel interfaz)
    {
    	NUM_CARTAS = 8;
        interfazJuego = interfaz;
        
        btnListener = new BotonListener();

        //A–ade el listener a cada carta y a los botones de control
        for(Carta c : interfazJuego.listaCartasAnimales)
            c.addActionListener(btnListener);
        
        for(Carta c : interfazJuego.listaCartasFrutas)
            c.addActionListener(btnListener);
        
        for(int i = 0; i < interfazJuego.botonesControl.length; i++)
            interfazJuego.botonesControl[i].addActionListener(btnListener);
    }

    /*
     * 
     * Bucle principal del juego en el que mietras no se descubran todas las parejas y no
     * se salga de forma intencionada esperaremos por la pulsacion de dos botones
     * los cuales pueden ser cartas o no y en funcion de ello realizaremos uan tarea u otra
     */
    public void jugar()
    {
        int parejasAcertadas;//numero de pares acertados
        int intentos;//numero de intentos
        boolean jugando = true;//variable de control de juego
        JButton botonPulsado; //Referencia al boton que retorna el listener
        Carta carta1, carta2; //Los dos botones a comparar
       

        while(jugando == true)
        {
            parejasAcertadas = 0;
            intentos = 0;
            botonPulsado = null;

            //Mientras todas las parejas no sean descubiertas...
            while(parejasAcertadas != NUM_CARTAS / 2)
            {
                //Esperamos hasta que se pulse el primer boton
                botonPulsado = btnListener.esperaBoton();
                
                //si es una carta...
                if(botonPulsado instanceof Carta)
                {
                    carta1 = (Carta)botonPulsado;
                    carta1.levantar();
                }
                else//si es otro tipo de boton
                {
                    if(botonPulsado.getActionCommand().equals("Salir"))
                        jugando = false;
                   
                    break;
                }

                //Esperamos hasta que se pulse el segundo boton
                botonPulsado = btnListener.esperaBoton();

                if(botonPulsado instanceof Carta)
                {
                    carta2 = (Carta)botonPulsado;
                    carta2.levantar();
                }
                else
                {          	
                    if(botonPulsado.getActionCommand().equals("Salir"))
                        jugando = false;
                   
                    break;
                        
                 }

                //Actualizamos la interfaz y esperamos 0.8 segundos
                interfazJuego.actualizar(800);

                //Dependiendo de si hemos acertado la pareja o no hacemos una cosa u otra
                if(!carta1.iguales(carta2) || carta1 == carta2)
                {
                    ((Carta)carta1).voltear();
                    ((Carta)carta2).voltear();
                }
                else
                {
                    carta1.setVisible(false);
                    carta2.setVisible(false);
                    parejasAcertadas++;
                }
                
                //Aumentamos el numero de intentos
                intentos++;

                //Si hemos conseguido todas las parejas mostramos un mensaje
                if(parejasAcertadas == NUM_CARTAS / 2)
                {
                    JOptionPane.showMessageDialog(interfazJuego, "Has ganado!\n"
                                    + "Numero de intentos: " + intentos
                                    );
                    //cambiamos el texto de boton reset por jugar de nuevo
                    interfazJuego.botonesControl[2].setText("Jugar de nuevo");

                    botonPulsado = btnListener.esperaBoton();
                }
            }
            
            //si el boton pulsado ha sido el de jugar de nuevo, reset, cambio de dificultad o cambio de tematica
            if(botonPulsado.getActionCommand().equals("Jugar de nuevo") || botonPulsado.getActionCommand().equals("Reiniciar"))
                interfazJuego.reiniciar();
            else if(botonPulsado.getActionCommand().equals("Salir"))
                jugando = false;
            else if(botonPulsado.getActionCommand().equals("Cambiar tematica")) 
            	interfazJuego.cambiar_tematica();
            else if(botonPulsado.getActionCommand().equals("Cambiar dificultad"))
            	interfazJuego.cambiar_dificultad();
        }

        return;
    }
}
