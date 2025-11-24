import java.io.BufferedReader;
import java.util.HashMap;

public class VisualizarLibros {
    public static void ejecutar() {
        try {
            BufferedReader leer = Main.getLeer();
            String[] generos = Main.getGeneros();
            HashMap<String, ListaDoble> biblioteca = Main.getBiblioteca();
            ListaDoble listaPrestado = Main.getListaPrestado();

            System.out.println("\n=== VISUALIZACION ===");
            System.out.println("1. Mostrar libros disponibles por genero");
            System.out.println("2. Mostrar libros en uso (prestados)");
            System.out.print("Seleccione opcion de visualizacion (1-2): ");

            int opcionVisualizacion = Integer.parseInt(leer.readLine());

            switch (opcionVisualizacion) {
                case 1:
                    System.out.println("\n=== LIBROS DISPONIBLES POR GENERO ===");
                    boolean hayLibrosDisponiblesVis = false;

                    for (String genero : generos) {
                        ListaDoble librosDelGenero = biblioteca.get(genero);
                        if (librosDelGenero.primero != null) {
                            hayLibrosDisponiblesVis = true;
                            System.out.println("\n--- " + genero + " ---");
                            librosDelGenero.imprimir();
                        }
                    }

                    if (!hayLibrosDisponiblesVis) {
                        System.out.println("No hay libros disponibles en la biblioteca.");
                    }
                    break;

                case 2:
                    System.out.println("\n=== LIBROS EN USO (PRESTADOS) ===");
                    if (listaPrestado.primero == null) {
                        System.out.println("No hay libros prestados actualmente.");
                    } else {
                        listaPrestado.imprimir();
                    }
                    break;

                default:
                    System.out.println("Opcion de visualizacion no valida.");
                    break;
            }
        } catch (Exception e) {
            System.out.println("Error en visualizacion: " + e.getMessage());
        }
    }
}
