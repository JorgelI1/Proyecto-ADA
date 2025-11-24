import java.io.BufferedReader;
import java.util.HashMap;


public class AgregarLibro {
    static int contadorId = 1;

    public static void ejecutar() {
        try {
            BufferedReader leer = Main.getLeer();
            String[] generos = Main.getGeneros();
            HashMap<String, ListaDoble> biblioteca = Main.getBiblioteca();

            // Generar ID único para el libro (5 números)
            String idLibro = String.format("%05d", contadorId++);

            System.out.println("ID generado para el libro: " + idLibro);

            // Pedir título del libro
            System.out.print("Ingrese el titulo del libro: ");
            String titulo = leer.readLine();

            // Mostrar lista de géneros con índices
            System.out.println("\nSeleccione el genero del libro:");
            for (int i = 0; i < generos.length; i++) {
                System.out.println((i + 1) + ". " + generos[i]);
            }
            System.out.print("Ingrese el numero del genero: ");

            int generoSeleccionado = Integer.parseInt(leer.readLine()) - 1;

            if (generoSeleccionado >= 0 && generoSeleccionado < generos.length) {
                String generoElegido = generos[generoSeleccionado];

                // Crear string con ID + título para almacenar
                String libroCompleto = idLibro + " - " + titulo;

                // Agregar a la lista del género correspondiente CON EL GÉNERO
                biblioteca.get(generoElegido).agregar(libroCompleto, generoElegido);

                System.out.println("Libro agregado exitosamente:");
                System.out.println("  ID: " + idLibro);
                System.out.println("  Titulo: " + titulo);
                System.out.println("  Genero: " + generoElegido);
            } else {
                System.out.println("Error: Genero no valido");
            }
        } catch (Exception e) {
            System.out.println("Error al agregar libro: " + e.getMessage());
        }
    }
}
