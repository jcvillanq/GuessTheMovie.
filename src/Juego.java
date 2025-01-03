

import java.util.List;

public class Juego {


    /*
     *Creamos variables globales.
     */
    private static final int Intentos_Maximos=10;
    private static final int Puntos_Letra_Correcta=10;
    private static final int Puntos_Letras_Incorrecta=-10;
    private static final int Puntos_Titulo_Incorrecto= -20;
    private static final int Puntos_Titulo_Correcto=20;



    private Jugador jugador;
    private String tituloPelicula;
    private char[] tituloMostrado;
    private List<Character> letrasIncorrectas;
    private int intentosRestantes;
    private boolean juegoGanado;


    /**
     * Contructor que iniciara nuestro juego
     */

    public Juego() {
        this.jugador = jugador;
        this.letrasIncorrectas = letrasIncorrectas;
        this.intentosRestantes = intentosRestantes;
        this.juegoGanado = juegoGanado;
    }

    /*
    * Creamos un metodo llamado iniciar para que cuando llamemos a la clase juego, sea mas facil entender.
    * */
    public void iniciar(){
        iniciarJuego();
        boolean salir = false;

        do{
            //v
            int opcion = 1;
            switch (opcion){
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    salir = true;
                    break;
            }
        }while(!salir);


    }

    private void iniciarJuego() {
        tituloPelicula = seleccionPeliculaAletoria();
        tituloMostrado = new char[tituloPelicula.length()];

    }

    private String seleccionPeliculaAletoria(){


        return "";
    }


}

