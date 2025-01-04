

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Juego {


    /*
     *Creamos constantes accesiblmes desde unicamente la clase Juego.
     */
    private static final int Intentos_Maximos=10;
    private static final int Puntos_Letra_Correcta=10;
    private static final int Puntos_Letras_Incorrecta=-10;
    private static final int Puntos_Titulo_Incorrecto= -20;
    private static final int Puntos_Titulo_Correcto=20;

    private static final Scanner scanner = new Scanner(System.in);

    private Jugador jugador;
    private String tituloPelicula;
    private char[] tituloMostrado;
    private Set<Character> letrasUsadas;
    private List<Character> letrasIncorrectas;
    private int intentosRestantes;
    private boolean juegoGanado;


    /**
     * Contructor que iniciara nuestro juego
     */

    public Juego() {
        jugador = new Jugador();
        letrasUsadas = new HashSet<>();
        letrasIncorrectas = new ArrayList<>();
        intentosRestantes = Intentos_Maximos;
        juegoGanado = false;
    }

    /*
    * Creamos un metodo llamado iniciar para que cuando llamemos a la clase juego, sea mas facil entender.
    * cuando creamos un menu usualmente es mejor usar do-while asi evitamos posibles bucles infinitos.
    * */
    public void iniciar(){
        iniciarJuego();
        boolean salir = false;

        do{
            mostrarEstadoJuego();
            int opcion = mostrarMenu();

            switch (opcion) {
                case 1:
                    manejarAdivinanzaLetra();
                    break;
                case 2:
                    manejarAdivinanzaTitulo();
                    break;
                 // creamos un tercera opcion por que es mas intuitiva la aplicacion.
                case 3:
                    mostrarRankingYReglas();
                    break;
                case 4:
                    salir = true;
                    salirJuego();
                    break;
            }
        // condiciones de salida o salir=true, intentos restantes >0 o juego ganado
        }while (!salir && intentosRestantes > 0 && !juegoGanado);

        mostrarResultadoJuego();
        actualizarRanking();

    }


    private void iniciarJuego() {
        tituloPelicula = seleccionPeliculaAletoria();
        tituloMostrado = new char[tituloPelicula.length()];

        for (int i = 0; i < tituloPelicula.length(); i++){
            if (Character.isLetter(tituloPelicula.charAt(i))) {
                tituloMostrado[i] = '*';
            }
            else{
                tituloMostrado[i] = tituloPelicula.charAt(i);
            }
        }

    }


    private String seleccionPeliculaAletoria(){
        List<String> peliculas = new ArrayList<>();
        /*Creamos un Try - Catch y dentro de este un bucle while,
        * para acceder a leer nuestro archivo peliculas.txt,
        * usaremos la clase externa BufeferedReader que es muy sencillo leer archivos,
        * DOC:
        * https://docs.oracle.com/javase/8/docs/api/java/io/BufferedReader.html
        */
        try(BufferedReader br = new BufferedReader(new FileReader("peliculas.txt"))){
            String lineaR;
            while ((lineaR = br.readLine()) != null){
                peliculas.add(lineaR.trim().toUpperCase());
            }
        }catch (IOException e){
            System.out.println(" Error al leer el archivo peliculas.txt");
            System.exit(1);
        }

        //seleccionamos un "pelicula" aleatoria usamos Randon de java.util.
        Random rand = new Random();
        return peliculas.get(rand.nextInt(peliculas.size()));
    }
    /*
    * Basicamente es mostrar el menu del estado del juego de forma modular.
    * */

    private void mostrarEstadoJuego() {
        System.out.println("\n Pelicula a adivinar: " + String.valueOf(tituloMostrado));
        System.out.println("Letras incorrectas: " + letrasIncorrectas);
        System.out.println("Intentos restantes: " + intentosRestantes);
        System.out.println("Puntuacion actual: " + jugador.getPuntuacion());
    }

    /*
     * creamos un metodo que retorne un numero int entre 1 y 4
     * */
    private int mostrarMenu() {
        int opcion;


        do {
            System.out.println("\n ============ MENU ============ ");
            System.out.println("[1] Adivinar una letra");
            System.out.println("[2] Adivinar el titulo");
            System.out.println("[3] RankingyReglas");
            System.out.println("[4] Salir");
            System.out.print("Elige una opcion: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                opcion = 0;
            }

            if (opcion < 1 || opcion > 4) {
                System.out.println("No valido, Intenta de nuevo.");
            }
        } while (opcion < 1 || opcion > 4);

        return opcion;
    }

    /*
     * Si seleccionamos opcion 1/ procedemos a trabajar con la letra en este caso este metodo,
     * su funcion principal es llevarlo a mayuculas,
     * si letra es mas de una le dira que solo ponga una,
     * si es una letra usada,
     * posteriormete lo pasamos a otro metodo que lo que hara sera terminar de procesar la letra.
     * */
    private void manejarAdivinanzaLetra() {
        char letra;

        do {
            System.out.print("Introduce una letra: ");
            String entrada = scanner.nextLine().toUpperCase();

            if (entrada.length() != 1 || !Character.isLetter(entrada.charAt(0))) {
                System.out.println("Por favor, introduce una unica letra.");
                continue;
            }

            letra = entrada.charAt(0);

            if (letrasUsadas.contains(letra)) {
                System.out.println("Ya has usado esta letra. Prueba con otra.");
                continue;
            }

            break;
        } while (true);

        procesarLetra(letra);
    }
    /*
     *funciones de este metodo:
     * comparar la letra con letra.pelicula
     * actualizar puntos
     *
     * */
    private void procesarLetra(char letra) {
            letrasUsadas.add(letra);
            boolean letraEncontrada = false;
            for (int i = 0; i < tituloPelicula.length(); i++) {
                if (tituloPelicula.charAt(i) == letra) {
                    tituloMostrado[i] = letra;
                    letraEncontrada = true;
                }
            }

            if (letraEncontrada) {
                System.out.println("¡Letra correcta!");
                jugador.agregarPuntos(Puntos_Letra_Correcta);
                verificarVictoria();
            } else {
                System.out.println("Letra incorrecta.");
                letrasIncorrectas.add(letra);
                jugador.agregarPuntos(Puntos_Letras_Incorrecta);
                intentosRestantes--;
            }
        }
    /*
     *funciones de metodo:
     * comparar cadena de string con el titulo original
     * */
    private void manejarAdivinanzaTitulo() {
            System.out.print("Introduce el titulo completo: ");
            String intento = scanner.nextLine().toUpperCase();
            if (intento.equals(tituloPelicula)) {
                System.out.println("Correcto! Has adivinado la película!");
                jugador.agregarPuntos(Puntos_Titulo_Correcto);
                juegoGanado = true;
            } else {
                System.out.println("Incorrecto. El titulo era: " + tituloPelicula);
                jugador.agregarPuntos(Puntos_Titulo_Incorrecto);
                intentosRestantes = 0;
            }
        }

    private void verificarVictoria() {
            if (String.valueOf(tituloMostrado).equals(tituloPelicula)) {
                System.out.println("Felicidades! Has adivinado el titulo");
                juegoGanado = true;
            }
        }

    /*
     *Reglas y ranking
     * */
    private void mostrarRankingYReglas() {
        System.out.println("\n ============ REGLAS DEL JUEGO ============");
        System.out.println("- Tienes " + Intentos_Maximos + " intentos para adivinar la película");
        System.out.println("- Por cada letra correcta: +" + Puntos_Letra_Correcta + " puntos");
        System.out.println("- Por cada letra incorrecta: " + Puntos_Letras_Incorrecta + " puntos");
        System.out.println("- Por adivinar el titulo completo: +" + Puntos_Titulo_Correcto + " puntos");
        System.out.println("- Por fallar el titulo completo: " + Puntos_Titulo_Incorrecto + " puntos");

        System.out.println("\n ============ RANKING ACTUAL ============");
        Ranking ranking = new Ranking();
        ranking.mostrarRanking();

        System.out.println("\nPresiona ENTER para continuar...");
        new Scanner(System.in).nextLine();
        }

    private void salirJuego() {
            System.out.println("Has decidido salir del juego.");
            intentosRestantes = 0;
        }

    private void mostrarResultadoJuego() {
            System.out.println("\n =========== FIN DEL JUEGO ============ ");
            System.out.println("Pelicula: " + tituloPelicula);
            System.out.println("Puntuacion final: " + jugador.getPuntuacion());
            System.out.println(juegoGanado ? "Has ganado!" : "Has perdido.");
        }
    /*
     * Si se completa el juego se crea el nickname y se agrega el ranking
     *
     * */
    private void actualizarRanking() {
            if (jugador.getPuntuacion() > 0) {
                Ranking ranking = new Ranking();
                if (ranking.esElegibleParaRanking(jugador.getPuntuacion())) {
                    Scanner scanner = new Scanner(System.in);
                    String nickname;
                    do {
                        System.out.print("Has entrado en el ranking. Introduce tu nickname: ");
                        nickname = scanner.nextLine();
                    } while (!ranking.esNicknameValido(nickname));

                    jugador.setNickname(nickname);
                    ranking.agregarPuntuacion(jugador);
                    ranking.mostrarRanking();
                }
            }
        }

}

