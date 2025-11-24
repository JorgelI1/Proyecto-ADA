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

            System.out.print("\nIngrese el ID del libro a devolver (ej: 00001): ");
            String idDevolver = Main.getLeer().readLine().trim();

            // Buscar el nodo en la lista de prestados
            Nodo nodoEncontrado = listaPrestado.buscarPorId(idDevolver);

            if (nodoEncontrado != null) {
                String libroActual = nodoEncontrado.getDato();
                String generoOriginal = nodoEncontrado.getGenero();

                // Eliminar por nodo de la lista de prestados (O(1))
                listaPrestado.eliminar(nodoEncontrado);

                // Agregar de vuelta a la biblioteca en su género original
                biblioteca.get(generoOriginal).agregar(libroActual, generoOriginal);

                System.out.println("Libro devuelto exitosamente:");
                System.out.println("  " + libroActual);
                System.out.println("  Genero: " + generoOriginal);
            } else {
                System.out.println("No se encontro ningun libro prestado con el ID: " + idDevolver);
            }
        } catch (Exception e) {
            System.out.println("Error al devolver libro: " + e.getMessage());
        }
    }
}
