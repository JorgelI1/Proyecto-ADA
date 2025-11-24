import java.util.ArrayList;
import java.util.HashMap;

public class Reordenamiento {
    public static void ejecutar() {
        try {
            HashMap<String, ListaDoble> biblioteca = Main.getBiblioteca();
            String[] generos = Main.getGeneros();

            System.out.println("\n=== REORDENAMIENTO CON QUICKSORT ===");
            System.out.println("Ordenando libros alfabeticamente por titulo...");

            int totalLibros = 0;

            for (String genero : generos) {
                ListaDoble listaGenero = biblioteca.get(genero);
                if (listaGenero.primero != null) {
                    ArrayList<String> librosArray = convertirListaAArray(listaGenero);
                    quickSort(librosArray, 0, librosArray.size() - 1);

                    ListaDoble listaOrdenada = new ListaDoble();
                    for (String libro : librosArray) {
                        // Recuperar el género original de cada nodo
                        Nodo temp = listaGenero.primero;
                        String generoOriginal = "Desconocido";
                        while (temp != null) {
                            if (temp.getDato().equals(libro)) {
                                generoOriginal = temp.getGenero();
                                break;
                            }
                            temp = temp.getSiguiente();
                        }
                        listaOrdenada.agregar(libro, generoOriginal);
                    }

                    biblioteca.put(genero, listaOrdenada);
                    totalLibros += librosArray.size();
                    System.out.println("Genero '" + genero + "' ordenado: " + librosArray.size() + " libros");
                }
            }

            System.out.println("\nReordenamiento completado exitosamente!");
            System.out.println("Total de libros ordenados: " + totalLibros);

        } catch (Exception e) {
            System.out.println("Error durante el reordenamiento: " + e.getMessage());
        }
    }


    // Convierte ListaDoble a ArrayList
    private static ArrayList<String> convertirListaAArray(ListaDoble lista) {
        ArrayList<String> array = new ArrayList<>();
        Nodo actual = lista.primero;

        while (actual != null) {
            array.add(actual.getDato());
            actual = actual.getSiguiente();
        }

        return array;
    }

    // Implementacion de QuickSort
    private static void quickSort(ArrayList<String> array, int low, int high) {
        if (low < high) {
            // Encontrar el índice de partición
            int pi = particion(array, low, high);

            // Ordenar recursivamente los elementos antes y después de la partición
            quickSort(array, low, pi - 1);
            quickSort(array, pi + 1, high);
        }
    }

    // Partir el array list para QuickSort
    private static int particion(ArrayList<String> array, int low, int high) {
        // Tomar el último elemento como pivote
        String pivote = extraerTitulo(array.get(high));
        int i = (low - 1); // Índice del elemento más pequeño

        for (int j = low; j < high; j++) {
            // Comparar títulos alfabéticamente
            String tituloActual = extraerTitulo(array.get(j));
            if (tituloActual.compareToIgnoreCase(pivote) <= 0) {
                i++;

                // Intercambiar elementos
                String temp = array.get(i);
                array.set(i, array.get(j));
                array.set(j, temp);
            }
        }

        // Intercambiar el pivote con el elemento en i+1
        String temp = array.get(i + 1);
        array.set(i + 1, array.get(high));
        array.set(high, temp);

        return i + 1;
    }

    // Devolver solo el titulo porque en el String esta pegado el id
    private static String extraerTitulo(String libroCompleto) {
        if (libroCompleto == null) return "";

        // Formato pa imprimir
        String[] partes = libroCompleto.split(" - ", 2);
        if (partes.length == 2) {
            return partes[1].trim();
        }

        return libroCompleto; // Si no tiene formato esperado, devolver completo
    }
}
