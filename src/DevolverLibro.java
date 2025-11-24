import java.io.BufferedReader;
import java.util.HashMap;

public class DevolverLibro {
    public static void ejecutar() {
        try {
            HashMap<String, ListaDoble> biblioteca = Main.getBiblioteca();
            ListaDoble listaPrestado = Main.getListaPrestado();

            System.out.println("\n=== DEVOLVER LIBRO ===");

            if (listaPrestado.primero == null) {
                System.out.println("No hay libros prestados actualmente.");
                return;
            }

            System.out.println("Libros actualmente prestados:");
            listaPrestado.imprimir();

            // Buscar por ID de 5 números
            System.out.print("\nIngrese el ID del libro a devolver (ej: 00001): ");
            String idDevolver = Main.getLeer().readLine().trim();

            boolean libroEncontradoPrestado = false;

            Nodo tempPrestado = listaPrestado.primero;
            while (tempPrestado != null) {
                String libroActual = tempPrestado.getDato();

                // Buscar por ID de 5 números al inicio
                if (libroActual.startsWith(idDevolver + " - ")) {
                    libroEncontradoPrestado = true;
                    String generoOriginal = tempPrestado.getGenero();

                    // Eliminar de prestados
                    listaPrestado.eliminar(libroActual);

                    // Agregar de vuelta a la biblioteca en su género original
                    biblioteca.get(generoOriginal).agregar(libroActual, generoOriginal);

                    System.out.println("Libro devuelto exitosamente:");
                    System.out.println("  " + libroActual);
                    System.out.println("  Genero: " + generoOriginal);
                    break;
                }
                tempPrestado = tempPrestado.getSiguiente();
            }

            if (!libroEncontradoPrestado) {
                System.out.println("No se encontro ningun libro prestado con el ID: " + idDevolver);
            }
        } catch (Exception e) {
            System.out.println("Error al devolver libro: " + e.getMessage());
        }
    }
}
