/*
    NO CORRER ESTE MAIN
    A MENOS QUE LO ESTES
    USANDO PARA PRUEBAS
    DE RENDIMIENTO
     */
import java.util.*;

public class PruebasAutomatizadas {
    private static String[] generos = {"Accion", "Aventuras", "Ciencia Ficcion", "Comedia", "Drama", "Fantasia", "Horror", "Romance"};
    private static Random random = new Random();

    public static void main(String[] args) {
        System.out.println(" PRUEBAS DE COMPLEJIDAD ALGORÍTMICA \n");

        System.out.println(" PRUEBA O(1): AGREGAR LIBROS ");
        testAgregarLibros();

        System.out.println("\n PRUEBA O(n): BUSCAR LIBROS ");
        testBuscarLibros();

        System.out.println("\n PRUEBA O(n log n): ORDENAR LIBROS ");
        testOrdenarLibros();

        System.out.println("\n PRUEBA O(n): PRESTAR LIBROS ");
        testPrestarLibrosOptimizado();

        System.out.println("\n PRUEBA O(n): DEVOLVER LIBROS ");
        testDevolverLibrosOptimizado();
    }

    // Generar título aleatorio
    private static String generarTituloAleatorio() {
        String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder titulo = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            titulo.append(letras.charAt(random.nextInt(letras.length())));
        }
        return titulo.toString();
    }

    //PRUEBA O(1): AGREGAR LIBROS
    private static void testAgregarLibros() {
        int[] tamanios = {100, 500, 1000, 5000, 10000};

        System.out.println("n\tTiempo(ms)");
        System.out.println("----------------");

        for (int n : tamanios) {
            HashMap<String, ListaDoble> biblioteca = new HashMap<>();
            for (String genero : generos) {
                biblioteca.put(genero, new ListaDoble());
            }

            long startTime = System.nanoTime();

            for (int i = 1; i <= n; i++) {
                String id = String.format("%05d", i);
                String titulo = generarTituloAleatorio();
                String genero = generos[random.nextInt(generos.length)];
                String libroCompleto = id + " - " + titulo;
                biblioteca.get(genero).agregar(libroCompleto, genero);
            }

            long endTime = System.nanoTime();
            double tiempoTotal = (endTime - startTime) / 1_000_000.0;

            System.out.printf("%d\t%.2f\n", n, tiempoTotal);
        }
    }

    // PRUEBA O(n): BUSCAR LIBROS
    private static void testBuscarLibros() {
        int[] tamanios = {100, 500, 1000, 5000, 10000};

        System.out.println("n\tTiempo(ms)");
        System.out.println("----------------");

        for (int n : tamanios) {
            // Preparar biblioteca con n libros
            HashMap<String, ListaDoble> biblioteca = new HashMap<>();
            HashMap<String, String> generoPorTitulo = new HashMap<>(); // Índice para búsqueda rápida

            for (String genero : generos) {
                biblioteca.put(genero, new ListaDoble());
            }

            List<String> titulosAgregados = new ArrayList<>();
            for (int i = 1; i <= n; i++) {
                String id = String.format("%05d", i);
                String titulo = generarTituloAleatorio();
                String genero = generos[random.nextInt(generos.length)];
                String libroCompleto = id + " - " + titulo;
                biblioteca.get(genero).agregar(libroCompleto, genero);
                titulosAgregados.add(titulo);
                generoPorTitulo.put(titulo, genero); // Guardar mapeo título->género
            }

            // Buscar 1 libros aleatorios
            long startTime = System.nanoTime();

            for (int i = 0; i < 1; i++) {
                String tituloBuscar = titulosAgregados.get(random.nextInt(titulosAgregados.size()));
                String genero = generoPorTitulo.get(tituloBuscar);
                if (genero != null) {
                    biblioteca.get(genero).buscarPorTitulo(tituloBuscar);
                }
            }

            long endTime = System.nanoTime();
            double tiempoTotal = (endTime - startTime) / 1000000.0;

            System.out.printf("%d\t%.2f\n", n, tiempoTotal);
        }
    }

    // ========== PRUEBA O(n log n): ORDENAR LIBROS ==========
    private static void testOrdenarLibros() {
        int[] tamanios = {100, 500, 1000, 5000, 10000};

        System.out.println("n\tTiempo(ms)");
        System.out.println("----------------");

        for (int n : tamanios) {
            // Crear array de libros para ordenar
            ArrayList<String> librosArray = new ArrayList<>();
            for (int i = 1; i <= n; i++) {
                String id = String.format("%05d", i);
                String titulo = generarTituloAleatorio();
                librosArray.add(id + " - " + titulo);
            }

            // Medir tiempo de QuickSort
            long startTime = System.nanoTime();
            quickSort(librosArray, 0, librosArray.size() - 1);
            long endTime = System.nanoTime();

            double tiempoTotal = (endTime - startTime) / 1000000.0;

            System.out.printf("%d\t%.2f\n", n, tiempoTotal);
        }
    }

    //PRUEBA O(n): PRESTAR LIBROS
    private static void testPrestarLibrosOptimizado() {
        int[] tamanios = {100, 500, 1000, 5000, 10000};

        System.out.println("n\tTiempo(ms)");
        System.out.println("----------------");

        for (int n : tamanios) {
            // Preparar biblioteca con índice de búsqueda
            HashMap<String, ListaDoble> biblioteca = new HashMap<>();
            ListaDoble prestados = new ListaDoble();
            HashMap<String, Nodo> nodoPorTitulo = new HashMap<>(); // Índice directo título->nodo

            for (String genero : generos) {
                biblioteca.put(genero, new ListaDoble());
            }

            // Agregar n + 100 libros
            List<String> titulosDisponibles = new ArrayList<>();
            for (int i = 1; i <= n + 100; i++) {
                String id = String.format("%05d", i);
                String titulo = generarTituloAleatorio();
                String genero = generos[random.nextInt(generos.length)];
                String libroCompleto = id + " - " + titulo;

                // Agregar a la lista
                ListaDoble listaGenero = biblioteca.get(genero);
                listaGenero.agregar(libroCompleto, genero);

                // Obtener el nodo recién agregado (último)
                Nodo nuevoNodo = listaGenero.ultimo;
                nodoPorTitulo.put(titulo, nuevoNodo);
                titulosDisponibles.add(titulo);
            }

            // Prestar n libros (O(n) usando índice)
            long startTime = System.nanoTime();

            for (int i = 0; i < n; i++) {
                String titulo = titulosDisponibles.get(i);
                Nodo nodo = nodoPorTitulo.get(titulo);

                if (nodo != null) {
                    String genero = nodo.getGenero();

                    // Eliminar de biblioteca (O(1) con el nodo)
                    biblioteca.get(genero).eliminar(nodo);

                    // Agregar a prestados
                    prestados.agregar(nodo.getDato(), genero);

                    // Actualizar índice
                    nodoPorTitulo.remove(titulo);
                }
            }

            long endTime = System.nanoTime();
            double tiempoTotal = (endTime - startTime) / 1000000.0;

            System.out.printf("%d\t%.2f\n", n, tiempoTotal);
        }
    }

    // PRUEBA O(n): DEVOLVER LIBROS
    private static void testDevolverLibrosOptimizado() {
        int[] tamanios = {100, 500, 1000, 5000, 10000};

        System.out.println("n\tTiempo(ms)");
        System.out.println("----------------");

        for (int n : tamanios) {
            // Preparar lista de prestados con índice
            ListaDoble prestados = new ListaDoble();
            HashMap<String, ListaDoble> biblioteca = new HashMap<>();
            HashMap<String, Nodo> nodoPorTitulo = new HashMap<>();

            for (String genero : generos) {
                biblioteca.put(genero, new ListaDoble());
            }

            // Agregar n libros directamente a prestados
            List<String> titulosPrestados = new ArrayList<>();
            for (int i = 1; i <= n; i++) {
                String id = String.format("%05d", i);
                String titulo = generarTituloAleatorio();
                String genero = generos[random.nextInt(generos.length)];
                String libroCompleto = id + " - " + titulo;

                // Agregar a prestados
                prestados.agregar(libroCompleto, genero);

                // Obtener el nodo recién agregado
                Nodo nuevoNodo = prestados.ultimo;
                nodoPorTitulo.put(titulo, nuevoNodo);
                titulosPrestados.add(titulo);
            }

            // Devolver n libros (O(n) usando índice)
            long startTime = System.nanoTime();

            for (String titulo : titulosPrestados) {
                Nodo nodo = nodoPorTitulo.get(titulo);

                if (nodo != null) {
                    String genero = nodo.getGenero();

                    // Eliminar de prestados (O(1))
                    prestados.eliminar(nodo);

                    // Agregar de vuelta a la biblioteca
                    biblioteca.get(genero).agregar(nodo.getDato(), genero);

                    // Limpiar índice
                    nodoPorTitulo.remove(titulo);
                }
            }

            long endTime = System.nanoTime();
            double tiempoTotal = (endTime - startTime) / 1_000_000.0;

            System.out.printf("%d\t%.2f\n", n, tiempoTotal);
        }
    }

    // METODOS
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
