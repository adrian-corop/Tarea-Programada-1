
/**
 * Write a description of class Main here.
 * 
 * @author (Adrian Coronado - C02414) 
 * @version (8/10/20)
 */
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        int opcion = 0;
        Scanner reader = new Scanner(System.in);
        //While para el menu
        while(opcion<=2){
            System.out.println(
                "Digite la opcion que desea:\n\n");
            System.out.println("Para jugar modo Jugador vs Jugador digite 1\n");
            System.out.println("Para jugar modo Jugador vs CPU digite 2\n");
                
            System.out.println( "Para salir digite 0\n");
            opcion = reader.nextInt();
            /**
             * Switch para ejecutar el programa como decida el usuario 
             */
            switch (opcion){
                case 1:
                jugarMultijugador();
                break;
                case 2:
                jugarCPU();
                break;
                case 0:
                opcion = 10;
                return;
            }
        }
    }
    // metodo para jugar Multijugador
    private static void jugarMultijugador(){   
        String jugador1 = "X";
        String jugador2 = "0";
        Tablero metodo = new Tablero();
        metodo.llenaTablero();
        metodo.imprimeTablero();
        int jugando = 0;

        boolean ganaJugador1 = false;
        boolean ganaJugador2 = false;

        while(jugando < 1){
            //turno del jugador 1
            System.out.println("Turno del jugador 1");
            metodo.insertar(true);
            metodo.imprimeTablero();
            ganaJugador1 = metodo.revisarGana(jugador1);
            if (ganaJugador1 == true){
                System.out.println("Ha ganado el jugador 1");
                break;
            }
            //turno del jugador 2
            System.out.println("Turno del jugador 2");    
            metodo.insertar(false);
            metodo.imprimeTablero();
            ganaJugador2 = metodo.revisarGana(jugador2);
            if (ganaJugador2 == true){
                System.out.println("Ha ganado el jugador 2");
                break;
            }
        }
    }
    //Metodo para jugar contra el CPU
    private static void jugarCPU(){
        
        String jugador1 = "X";
        String jugador2 = "0";
        Tablero metodo = new Tablero();
        metodo.llenaTablero();
        metodo.imprimeTablero();
        int jugando = 0;
        boolean ganaJugador1 = false;
        boolean ganaJugador2 = false;
        
        while(jugando < 1){
            //turno del CPU
            System.out.println("Turno del CPU");
            metodo.insertarCPU(false);
            metodo.imprimeTablero();
            ganaJugador1 = metodo.revisarGana(jugador1);
            if (ganaJugador1 == true){
                System.out.println("Ha ganado el CPU");
                break;
            }
            //turno del jugador 1
            System.out.println("Turno del jugador 1");    
            metodo.insertar(true);
            metodo.imprimeTablero();
            ganaJugador2 = metodo.revisarGana(jugador2);
            if (ganaJugador2 == true){
                System.out.println("Ha ganado el jugador 1");
                break;
            }
        }
    
    }
}
