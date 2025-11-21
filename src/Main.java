import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class Main {
    // Contador para generar IDs únicos
    private static int contadorId = 1;

    public static void main(String[] args) {
        BufferedReader leer = new BufferedReader(new InputStreamReader(System.in));
        String[] generos = {"Accion", "Fantasia", "Horror", "Romance", "Ciencia Ficcion", "Aventuras", "Comedia", "Drama"};

        // HashMap para organizar libros por género
        HashMap<String, ListaDoble> biblioteca = new HashMap<>();
        ListaDoble listaPrestado = new ListaDoble();

        // Inicializar todos los géneros
        for (String genero : generos) {
            biblioteca.put(genero, new ListaDoble());
        }

        String dato;
        int opcion = 0;
        do {
            try {
                System.out.println("\n=== MENÚ DE LIBRERIA ===");
                System.out.println("1. Agregar libro");
                System.out.println("2. Prestar libro");
                System.out.println("3. Devolver libro");
                System.out.println("4. Reordenamiento");
                System.out.println("5. Mostrar libros por género");
                System.out.println("6. Mostrar todos los libros");
                System.out.println("0. Salir del programa");
                System.out.print("Seleccione una opción (0-6): ");

                opcion = Integer.parseInt(leer.readLine());
                switch (opcion) {
                    case 1:
                        // Generar ID único para el libro
                        String idLibro = "LIB" + String.format("%03d", contadorId);
                        contadorId++;

                        System.out.println("ID generado para el libro: " + idLibro);

                        // Pedir título del libro
                        System.out.print("Ingrese el título del libro: ");
                        String titulo = leer.readLine();

                        // Mostrar lista de géneros con índices
                        System.out.println("\nSeleccione el género del libro:");
                        for (int i = 0; i < generos.length; i++) {
                            System.out.println((i + 1) + ". " + generos[i]);
                        }
                        System.out.print("Ingrese el número del género: ");

                        int generoSeleccionado = Integer.parseInt(leer.readLine()) - 1;

                        if (generoSeleccionado >= 0 && generoSeleccionado < generos.length) {
                            String generoElegido = generos[generoSeleccionado];

                            // Crear string con ID + título para almacenar
                            String libroCompleto = idLibro + " - " + titulo;

                            // Agregar a la lista del género correspondiente
                            biblioteca.get(generoElegido).agregar(libroCompleto);

                            System.out.println("✓ Libro agregado exitosamente:");
                            System.out.println("  ID: " + idLibro);
                            System.out.println("  Título: " + titulo);
                            System.out.println("  Género: " + generoElegido);
                        } else {
                            System.out.println("❌ Error: Género no válido");
                        }
                        break;

                    case 2:
                        // Lógica para prestar libro
                        System.out.println("Función de prestar libro - En desarrollo");
                        break;

                    case 3:
                        // Lógica para devolver libro
                        System.out.println("Función de devolver libro - En desarrollo");
                        break;

                    case 4:
                        System.out.println("Función de reordenamiento - En desarrollo");
                        /* MERGE O QUICKSORT AQUI */
                        break;

                    case 5:
                        System.out.println("\nSeleccione el género a mostrar:");
                        for (int i = 0; i < generos.length; i++) {
                            System.out.println((i + 1) + ". " + generos[i]);
                        }
                        System.out.print("Ingrese el número del género: ");
                        int generoMostrar = Integer.parseInt(leer.readLine()) - 1;

                        if (generoMostrar >= 0 && generoMostrar < generos.length) {
                            String genero = generos[generoMostrar];
                            System.out.println("\n=== LIBROS DE " + genero.toUpperCase() + " ===");
                            biblioteca.get(genero).imprimir();
                        } else {
                            System.out.println("❌ Error: Género no válido");
                        }
                        break;

                    case 6:
                        System.out.println("=== TODOS LOS LIBROS POR GÉNERO ===");
                        boolean hayLibros = false;

                        for (String genero : generos) {
                            // Verificar si el género tiene libros
                            Nodo primerLibro = biblioteca.get(genero).primero;
                            if (primerLibro != null) {
                                hayLibros = true;
                                System.out.println("\n--- " + genero + " ---");
                                biblioteca.get(genero).imprimir();
                            }
                        }

                        if (!hayLibros) {
                            System.out.println("No hay libros en la biblioteca.");
                        }
                        break;

                    case 0:
                        System.out.println("Saliendo del programa...");
                        break;

                    default:
                        System.out.println("❌ Opción no válida.");
                        break;
                }
            } catch (IOException e) {
                System.out.println("Error de entrada/salida: " + e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Error: Por favor ingrese un número válido.");
            } catch (Exception e) {
                System.out.println("Error inesperado: " + e.getMessage());
            }
        } while (opcion != 0);
    }
}