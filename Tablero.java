
/**
 * Write a description of class Tablero here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.Scanner;

public class Tablero {
    String  tablero [][] = new String [6][7];
    Scanner movimiento = new Scanner(System.in);
    String fichajugador = "X";
    String fichaJugador2 = "0";
    
    /**
     * ----------------------------------------------------------------------
     * Metodo que se encarga de insertar una ficha y lleva los turnos
     * cuando se encuentra en modo Jugador vs Jugador
     * ----------------------------------------------------------------------
     */
    public void insertar(boolean turno) {
        int fila = 0;
        System.out.println("Seleccione la columna desea insertar su ficha");
        int columna = movimiento.nextInt();
        columna = columna - 1;
        if(columna < 0 || columna >= 7){
            System.out.println("Posicion invalida");
            return;
        } 

        if(tablero[0][columna] == fichajugador || tablero[0][columna] == fichaJugador2){
            System.out.println("No hay mas espacio en esta columna!");  
            return;
        }

        while(tablero[fila][columna]!= fichajugador && tablero[fila][columna]!= fichaJugador2 && fila < tablero.length-1){
            fila++;

        }
        if(tablero[fila][columna] == fichajugador || tablero[fila][columna] == fichaJugador2){
            fila--;
        }
        if(turno){
            tablero[fila][columna] = fichajugador;
            
            turno = false;
        }else{
            tablero[fila][columna] = fichaJugador2;
            
            turno = true;
        }

    }

    /**
     * ----------------------------------------------------------------------
     * Metodo que se encarga de insertar una ficha y lleva los turnos
     * cuando se encuentra en modo Jugador vs CPU
     * ----------------------------------------------------------------------
     */
    public void insertarCPU(boolean turno) {

        int fila = 0;
        int columna;
        double columnaCpu = Math.round(Math.random()*(6-1+1)+1);

        if(turno){
            System.out.println("Seleccione la columna desea insertar su ficha");
            columna = movimiento.nextInt();
            columna = columna-1;
        }else{
            columna = (int)columnaCpu-1;  
        }
        if(columna < 0 || columna >= 7){
            System.out.println("Posicion invalida");
            return;
        } 

        if(tablero[0][columna] == fichajugador || tablero[0][columna] == fichaJugador2){
            System.out.println("No hay mas espacio en esta columna!");  

        }

        while(tablero[fila][columna]!= fichajugador && tablero[fila][columna]!= fichaJugador2 && fila < tablero.length-1){
            fila++;

        }
        if(tablero[fila][columna] == fichajugador || tablero[fila][columna] == fichaJugador2){
            fila--;
        }
        if(turno){
            tablero[fila][columna] = fichajugador;
            
            turno = false;
        }else{

            tablero[fila][columna] = fichaJugador2;
            
            turno = true;
        }

    }

    /**
     * ----------------------------------------------------------------------
     * Metodo que se encarga de llenar el tablero con espacios vacios
     * ----------------------------------------------------------------------
     */
    public void llenaTablero(){

        for(int fila = 0; fila<tablero.length; fila++){

            for(int fila2 = 0; fila2<tablero[fila].length;fila2++){
                tablero[fila][fila2] = " ";
            }
        }  
    }

    /**
     * ----------------------------------------------------------------------
     * Metodo que se encarga de imprimir el tablero, mientras a su vez
     * crea las lineas que dividen las columnas
     * ----------------------------------------------------------------------
     */
    public void imprimeTablero(){
        System.out.println("1|2|3|4|5|6|7|");
        for(int fila = 0; fila<tablero.length; fila++){

            for(int fila2 = 0; fila2<tablero[fila].length;fila2++){
                System.out.print(tablero[fila][fila2]);
                System.out.print("|");

            }
            System.out.println();
        }
    }

    /**
     * ----------------------------------------------------------------------
     * Metodo que se encarga de revisar despues de cada jugada si 
     * alguien gana para terminar el juego
     * ----------------------------------------------------------------------
     */
    public boolean revisarGana(String fichajugador){
        if (revisarEmpate(fichajugador)){
            return true;
        }
        if (revisarGanarFila(fichajugador)){
            return true;
        }

        if (revisarGanarColumna(fichajugador)){
            return true;
        }

        if (revisarGanarDecreciente(fichajugador)){
            return true;
        }

        if (revisarGanarCreciente(fichajugador)){
            return true;
        }

        return false;
    }

    private boolean revisarEmpate(String jugador){
        int contadorEmpate = 0;
        for(int columnaEmpate = 0; columnaEmpate < tablero.length+1; columnaEmpate++){
            if(tablero[0][columnaEmpate] == jugador || tablero[0][columnaEmpate] == jugador){

                contadorEmpate++;

            }
            if(contadorEmpate == 7){
                System.out.println("No hay mas espacio en el tablero!"); 
                System.out.println("Ha sido un empate");
                return true;
            }
        }
        return false;
    }

    private boolean revisarGanarCreciente(String jugador) {
        //Recorre el tablero de forma Creciente
        int contador = 0;
        boolean diagonalCreciente;

        for (int fila = 0; fila < tablero.length; fila++){//recorrer filas
            for(int columna = 0; columna < tablero[fila].length-1; columna++){ //recorrer columnas
                //---------
                if(tablero[fila][columna] != jugador){
                    contador = 0;
                    diagonalCreciente = false;
                } else {
                    //---------
                    contador++;
                    diagonalCreciente = true;
                    int columnaAux = columna;
                    int filaAux = fila;

                    if (filaAux - 1 > tablero.length || filaAux - 1 < 0){
                        diagonalCreciente = false;
                    }
                    if (columnaAux + 1 > tablero[filaAux].length || columnaAux + 1 == 6 ){
                        diagonalCreciente = false;
                    }

                    while(diagonalCreciente && contador < 4 ){

                        if (tablero [filaAux-1][columnaAux+1]==jugador){
                            contador++;
                            filaAux=filaAux-1;
                            columnaAux = columnaAux+1;
                            if (filaAux - 1 > tablero.length || filaAux - 1 < 0){
                                diagonalCreciente = false;
                            }
                            if (columnaAux + 1 > tablero[filaAux].length ){
                                diagonalCreciente = false;
                            }
                            if(contador==4){
                                return true;
                            }
                        }else{
                            diagonalCreciente = false;
                            contador = 0;
                        }
                    }
                }
            }
        }
        //En el momento que el contador llega a 4 significa que ya alguien gano
        if(contador==4){
            return true;
        }

        return false;
    }

    private boolean revisarGanarDecreciente(String jugador) {
        /**
         * -----------------------------------------------------------------
         * Esta parte del metodo revisa las diagonales en busca de 
         * 4 simbolos en linea
         * -----------------------------------------------------------------
         */

        int contador = 0;
        boolean diagonalDecreciente = false;

        //Recorre el tablero de forma Decreciente
        for (int fila = 0; fila < tablero.length; fila++){
            //fila = filas
            for(int columna = 0; columna < tablero[fila].length-1; columna++){
                //columna = columnas

                if(tablero[fila][columna] != jugador){
                    contador = 0; 
                }
                //-----
                if (tablero[fila][columna]==jugador){
                    contador++;
                    diagonalDecreciente = true;

                    if (fila + 1 >= tablero.length){
                        diagonalDecreciente = false;
                    }
                    if (columna + 1 > tablero[fila].length){
                        diagonalDecreciente = false;
                    }

                    int columnaAux = columna;
                    int filaAux = fila;

                    while(diagonalDecreciente && contador < 4){

                        if (tablero [filaAux+1][columnaAux+1]==jugador){
                            contador++;
                            filaAux=filaAux+1;
                            columnaAux = columnaAux+1;
                            if (filaAux + 1 >= tablero.length){
                                diagonalDecreciente = false;
                            }
                            if (columnaAux + 1 > tablero[filaAux].length){
                                diagonalDecreciente = false;
                            }
                            if(contador==4){
                                return true;
                            }
                        }else{
                            diagonalDecreciente = false;
                            contador = 0;
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean revisarGanarColumna(String jugador) {
        int fila = 0, columna = 0, contador = 0;
        //este while busca combinaciones ganadoras en las columnas
        while (columna < tablero.length + 1){
            fila = 0;
            //System.out.println("columna numero"+columna);
            while(fila < tablero.length){
                if(tablero[fila][columna] != jugador){
                    contador = 0; //System.out.println("reinicia contador"+contador);
                }
                if(tablero[fila][columna] == jugador){
                    contador++; //System.out.println("aumenta contador"+contador);
                }
                fila++;
                if(contador==4){
                    return true;
                }
            }
            contador = 0;
            columna++;
        }
        return false;
    }

    private boolean revisarGanarFila(String jugador){
        //este while busca combinaciones ganadoras en las filas
        int contador = 0;
        for (int fila =0; fila < tablero.length; fila++){
            for (int columna =0; columna < tablero[fila].length; columna++){
                if (!tablero[fila][columna].equals(jugador)) {
                    contador = 0; //System.out.println("reinica contador");
                }else{
                    contador++; //System.out.println("aumenta contador");
                }
                if (contador == 4) {
                    return true;
                }
            }
        }
        return false;
    }
}

