import java.util.*;

public class Pruebas {
    private static String[] generos = {"Accion", "Aventuras", "Ciencia Ficcion", "Comedia", "Drama", "Fantasia", "Horror", "Romance"};
    private static HashMap<String, ListaDoble> biblioteca = new HashMap<>();
    private static Random random = new Random();

    public static void main(String[] args) {
        // Inicializar biblioteca
        for (String genero : generos) {
            biblioteca.put(genero, new ListaDoble());
        }

        System.out.println("=== PRUEBAS DE RENDIMIENTO DEL SISTEMA ===\n");

        testAgregarLibros(1000);
        testBuscarLibro(10000);
        testOrdenarLibros(2000);
    }

    // Generar título aleatorio de 5 letras
    private static String generarTituloAleatorio() {
        String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder titulo = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            titulo.append(letras.charAt(random.nextInt(letras.length())));
        }
        return titulo.toString();
    }

    // Agregar libros a la biblioteca
    private static void agregarLibros(int cantidad) {
        for (int i = 1; i <= cantidad; i++) {
            String id = String.format("%05d", i);
            String titulo = generarTituloAleatorio();
            String genero = generos[random.nextInt(generos.length)];
            String libroCompleto = id + " - " + titulo;
            biblioteca.get(genero).agregar(libroCompleto, genero);
        }
    }

    // Obtener un título aleatorio que existe en la biblioteca
    private static String obtenerTituloAleatorio() {
        // Buscar cualquier género que tenga libros
        for (String genero : generos) {
            ListaDoble lista = biblioteca.get(genero);
            if (lista.primero != null) {
                // Tomar el primer libro como muestra
                return extraerTitulo(lista.primero.getDato());
            }
        }
        return generarTituloAleatorio();
    }

    // Prueba de agregar libros
    private static void testAgregarLibros(int cantidad) {
        System.out.println("PRUEBA 1: Agregar " + cantidad + " libros");

        long startTime = System.nanoTime();
        agregarLibros(cantidad);
        long endTime = System.nanoTime();

        double durationMillis = (endTime - startTime) / 1_000_000.0;
        System.out.println("Tiempo total: " + durationMillis + " ms\n");
    }

    // Prueba de buscar 1 libro en 10000 libros
    private static void testBuscarLibro(int cantidad) {
        System.out.println("PRUEBA 2: Buscar 1 libro entre " + cantidad + " libros");

        // Limpiar y agregar libros
        for (String genero : generos) {
            biblioteca.put(genero, new ListaDoble());
        }
        agregarLibros(cantidad);

        String tituloABuscar = obtenerTituloAleatorio();
        System.out.println("Buscando: '" + tituloABuscar + "'");

        long startTime = System.nanoTime();

        // Buscar en todos los géneros
        Nodo encontrado = null;
        for (String genero : generos) {
            encontrado = biblioteca.get(genero).buscarPorTitulo(tituloABuscar);
            if (encontrado != null) break;
        }

        double durationMillis = (System.nanoTime() - startTime) / 1_000_000.0;
        System.out.println("Resultado: " + (encontrado != null ? "ENCONTRADO" : "NO ENCONTRADO"));
        System.out.println("Tiempo: " + durationMillis + " ms\n");
    }

    // Prueba de ordenamiento de libros
    private static void testOrdenarLibros(int cantidad) {
        System.out.println("PRUEBA 3: Ordenar " + cantidad + " libros");

        // Limpiar y agregar libros
        for (String genero : generos) {
            biblioteca.put(genero, new ListaDoble());
        }
        agregarLibros(cantidad);

        long startTime = System.nanoTime();

        // Ordenar cada género
        int totalOrdenados = 0;
        for (String genero : generos) {
            ListaDoble lista = biblioteca.get(genero);
            if (lista.primero != null) {
                ArrayList<String> librosArray = listaAArray(lista);
                quickSort(librosArray, 0, librosArray.size() - 1);

                // Reconstruir lista ordenada
                ListaDoble ordenada = new ListaDoble();
                for (String libro : librosArray) {
                    ordenada.agregar(libro, genero); // Usar el género actual
                }
                biblioteca.put(genero, ordenada);
                totalOrdenados += librosArray.size();
            }
        }

        double durationMillis = (System.nanoTime() - startTime) / 1_000_000.0;
        System.out.println("Libros ordenados: " + totalOrdenados);
        System.out.println("Tiempo total: " + durationMillis + " ms");
        System.out.println("Tiempo por libro: " + (durationMillis / totalOrdenados) + " ms\n");
    }

    // Métodos auxiliares
    private static ArrayList<String> listaAArray(ListaDoble lista) {
        ArrayList<String> array = new ArrayList<>();
        Nodo actual = lista.primero;
        while (actual != null) {
            array.add(actual.getDato());
            actual = actual.getSiguiente();
        }
        return array;
    }

    private static void quickSort(ArrayList<String> array, int low, int high) {
        if (low < high) {
            int pi = particion(array, low, high);
            quickSort(array, low, pi - 1);
            quickSort(array, pi + 1, high);
        }
    }

    private static int particion(ArrayList<String> array, int low, int high) {
        String pivote = extraerTitulo(array.get(high));
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (extraerTitulo(array.get(j)).compareToIgnoreCase(pivote) <= 0) {
                i++;
                Collections.swap(array, i, j);
            }
        }
        Collections.swap(array, i + 1, high);
        return i + 1;
    }

    private static String extraerTitulo(String libroCompleto) {
        String[] partes = libroCompleto.split(" - ", 2);
        return partes.length == 2 ? partes[1].trim() : libroCompleto;
    }
}
