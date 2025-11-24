import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class Main {
    private static String[] generos = {"Accion", "Aventuras", "Ciencia Ficcion", "Comedia", "Drama", "Fantasia", "Horror", "Romance"};
    private static HashMap<String, ListaDoble> biblioteca = new HashMap<>();
    private static ListaDoble listaPrestado = new ListaDoble();
    private static BufferedReader leer = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {
        // Inicializar todos los géneros
        for (String genero : generos) {
            biblioteca.put(genero, new ListaDoble());
        }

        int opcion = 0;
        do {
            try {
                System.out.println("\n=== MENU DE LIBRERIA ===");
                System.out.println("1. Agregar libro");
                System.out.println("2. Prestar libro");
                System.out.println("3. Devolver libro");
                System.out.println("4. Reordenamiento");
                System.out.println("5. Visualizacion");
                System.out.println("0. Salir del programa");
                System.out.print("Seleccione una opcion (0-5): ");

                opcion = Integer.parseInt(leer.readLine());
                switch (opcion) {
                    case 1:
                        AgregarLibro.ejecutar();
                        break;
                    case 2:
                        PrestarLibro.ejecutar();
                        break;
                    case 3:
                        DevolverLibro.ejecutar();
                        break;
                    case 4:
                        Reordenamiento.ejecutar();
                        break;
                    case 5:
                        VisualizarLibros.ejecutar();
                        break;
                    case 0:
                        System.out.println("Saliendo del programa...");
                        break;
                    default:
                        System.out.println("Opcion no valida.");
                        break;
                }
            } catch (IOException e) {
                System.out.println("Error de entrada/salida: " + e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Error: Por favor ingrese un numero valido.");
            } catch (Exception e) {
                System.out.println("Error inesperado: " + e.getMessage());
            }
        } while (opcion != 0);
    }

    // Getters para las clases

    public static String[] getGeneros() {
        return generos;
    }

    public static HashMap<String, ListaDoble> getBiblioteca() {
        return biblioteca;
    }

    public static ListaDoble getListaPrestado() {
        return listaPrestado;
    }

    public static BufferedReader getLeer() {
        return leer;
    }

}