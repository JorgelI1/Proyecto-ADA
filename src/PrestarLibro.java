import java.io.BufferedReader;
import java.util.HashMap;

public class PrestarLibro {
    public static void ejecutar() {
        try {
            BufferedReader leer = Main.getLeer();
            String[] generos = Main.getGeneros();
            HashMap<String, ListaDoble> biblioteca = Main.getBiblioteca();
            ListaDoble listaPrestado = Main.getListaPrestado();

            System.out.println("\n=== PRESTAR LIBRO ===");

            System.out.println("Libros disponibles en la biblioteca:");
            boolean hayLibrosDisponibles = false;

            for (String genero : generos) {
                ListaDoble librosDelGenero = biblioteca.get(genero);
                if (librosDelGenero.primero != null) {
                    hayLibrosDisponibles = true;
                    System.out.println("\n--- " + genero + " ---");
                    librosDelGenero.imprimir();
                }
            }

            if (!hayLibrosDisponibles) {
                System.out.println("No hay libros disponibles para prestar.");
                return;
            }

            // Ahora busca por ID de 5 números (ej: 00001)
            System.out.print("\nIngrese el ID del libro a prestar (ej: 00001): ");
            String idPrestar = leer.readLine().trim();

            boolean libroEncontrado = false;

            for (String genero : generos) {
                ListaDoble librosDelGenero = biblioteca.get(genero);
                Nodo temp = librosDelGenero.primero;

                while (temp != null) {
                    String libroActual = temp.getDato();
                    // Buscar por ID de 5 números al inicio del string
                    if (libroActual.startsWith(idPrestar + " - ")) {
                        libroEncontrado = true;
                        String generoOriginal = temp.getGenero();

                        // Eliminar de la biblioteca
                        librosDelGenero.eliminar(libroActual);

                        // Agregar a la lista de prestados con el género
                        listaPrestado.agregar(libroActual, generoOriginal);

                        System.out.println("Libro prestado exitosamente:");
                        System.out.println("  " + libroActual);
                        System.out.println("  Genero: " + generoOriginal);
                        break;
                    }
                    temp = temp.getSiguiente();
                }

                if (libroEncontrado) {
                    break;
                }
            }

            if (!libroEncontrado) {
                System.out.println("No se encontro ningun libro con el ID: " + idPrestar);
            }
        } catch (Exception e) {
            System.out.println("Error al prestar libro: " + e.getMessage());
        }
    }
}
