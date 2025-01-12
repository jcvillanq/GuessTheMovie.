import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class VillanJuanCarlosMain {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
    VillanJuanCarlosMain programa =  new VillanJuanCarlosMain();

    programa.inicio();
    programa.configuracionApp();
    programa.bievenidaApp();
    programa.ejecutarApp();
    programa.despedidaApp();

    }


    // iniciamos nuestro flujo de trabajo
    private void inicio(){
        System.out.println("Inicializando nuestro juego de adivinar una pelicula");
    }
    private void configuracionApp() {
        System.out.println("Cargando configuracion y peliculas");
        // importante crear nuestro metodo que busque verificar si existen los archivos necesarios.
        verificarArchivos();
    }
    private void bievenidaApp() {
        System.out.println("Bienvenido al juego de adivinar las peliculas");
        System.out.println("intenta adivinar el titulo de la pelicula en 10 intentos ");
    }

    public void verificarArchivos(){
        File archivoPeliculas = new File("peliculas.txt");
        File ranking = new File("ranking.dat");
        if (!archivoPeliculas.exists()) {
            archivoPeliculasPorDefecto();
        }
        if (!ranking.exists()) {
            crearArchivoRankingVacio();
        }
    }



    private void archivoPeliculasPorDefecto(){
        ArrayList<String> peliculas = new ArrayList<>();
        //intrucciones para rellenar archivo peliculas.txt
        System.out.println("Por favor, ingrese los títulos de las películas para crear el archivo:");
        System.out.println("(Escriba 'FIN' para terminar de ingresar títulos)");
        /*
        * Creamos el archivo peliculas.txt.
        * WHILE para recoger las peliculas con scanner y cuando el usuario ponga FIN(ignorando mayus) salimos con un break que rompre el bucle.
        * Luego usamos un try - catch para cargar las peliculas con un bucle for.
        * Finalmente damos un mensaje de que todo ha funcionado.
        * */

        while (true) {
            System.out.print("Ingrese el título de la película: ");
            String titulo = scanner.nextLine();
            if (titulo.equalsIgnoreCase("FIN")) {
                break;
            }
            peliculas.add(titulo);
        }
        try(PrintStream stream = new PrintStream(new FileOutputStream("peliculas.txt"))){
            for (String pelicula : peliculas) {
                stream.println(pelicula);
            }
             System.out.println("Archivo 'pelicula.txt' creado con éxito.");

        }catch (IOException e) {
            System.out.println("Error al crear archivo de películas por defecto.");
        }
    }
    private void crearArchivoRankingVacio() {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream("ranking.dat"))) {
            ArrayList<RegistroRanking> rankingVacio = new ArrayList<>();
            oos.writeObject(rankingVacio);
        } catch (IOException e) {
            System.out.println("Error al crear archivo de ranking vacío.");
        }
    }

    private void ejecutarApp() {
       Juego nuevo_juego = new Juego();
       nuevo_juego.iniciar();
    }




    private void despedidaApp(){

        System.out.println("Gracias por jugar! Hasta pronto!");
    }

    
    
}