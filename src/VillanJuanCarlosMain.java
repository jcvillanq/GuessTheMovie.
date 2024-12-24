//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class VillanJuanCarlosMain {
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
    }
    private void bievenidaApp() {
        System.out.println("Bienvenido al juego de adivinar las peliculas");
        System.out.println("intenta adivinar el titulo de la pelicula en 10 intentos ");
    }

    private void ejecutarApp() {
        Juego juego = new Juego();
        juego.iniciar();
    }

    private void despedidaApp(){

        System.out.println("Gracias por jugar! Hasta pronto!");
    }

    
    
}