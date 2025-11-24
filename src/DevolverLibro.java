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

            // Mostrar libros prestados
            System.out.println("Libros actualmente prestados:");
            if (listaPrestado.primero == null) {
                System.out.println("  (vacio)");
            } else {
                Nodo temp = listaPrestado.primero;
                while (temp != null) {
                    String titulo = extraerTitulo(temp.getDato());
                    System.out.println("  " + titulo);
                    temp = temp.getSiguiente();
                }
            }

            // Pedir título en lugar de ID
            System.out.print("\nIngrese el titulo del libro a devolver: ");
            String tituloDevolver = Main.getLeer().readLine().trim();

            // Buscar el nodo en la lista de prestados por título
            Nodo nodoEncontrado = listaPrestado.buscarPorTitulo(tituloDevolver);

            if (nodoEncontrado != null) {
                String libroActual = nodoEncontrado.getDato();
                String generoOriginal = nodoEncontrado.getGenero();
                String titulo = extraerTitulo(libroActual);

                // Eliminar por nodo de la lista de prestados (O(1))
                listaPrestado.eliminar(nodoEncontrado);

                // Agregar de vuelta a la biblioteca en su genero original
                biblioteca.get(generoOriginal).agregar(libroActual, generoOriginal);

                System.out.println("Libro devuelto exitosamente:");
                System.out.println("  Titulo: " + titulo);
                System.out.println("  Genero: " + generoOriginal);
            } else {
                System.out.println("No se encontro ningun libro prestado con el titulo: " + tituloDevolver);
            }
        } catch (Exception e) {
            System.out.println("Error al devolver libro: " + e.getMessage());
        }
    }

    // Extrae el titulo
    private static String extraerTitulo(String libroCompleto) {
        if (libroCompleto == null) return "";
        String[] partes = libroCompleto.split(" - ", 2);
        return partes.length == 2 ? partes[1].trim() : libroCompleto;
    }
}
