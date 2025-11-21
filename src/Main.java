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
                        System.out.println("\n=== PRESTAR LIBRO ===");

                        // Mostrar todos los libros disponibles por género
                        System.out.println("Libros disponibles en la biblioteca:");
                        boolean hayLibrosDisponibles = false;

                        for (String genero : generos) {
                            ListaDoble librosDelGenero = biblioteca.get(genero);
                            if (librosDelGenero.primero != null) {
                                hayLibrosDisponibles = true;
                                System.out.println("\n--- " + genero + " ---");

                                Nodo temp = librosDelGenero.primero;
                                while (temp != null) {
                                    System.out.println("  " + temp.getDato());
                                    temp = temp.getSiguiente();
                                }
                            }
                        }

                        if (!hayLibrosDisponibles) {
                            System.out.println("❌ No hay libros disponibles para prestar.");
                            break;
                        }

                        // Pedir ID del libro a prestar
                        System.out.print("\nIngrese el ID del libro a prestar (ej: LIB001): ");
                        String idPrestar = leer.readLine().trim();

                        // Buscar el libro en todos los géneros
                        boolean libroEncontrado = false;
                        String libroPrestado = null;

                        for (String genero : generos) {
                            ListaDoble librosDelGenero = biblioteca.get(genero);
                            Nodo temp = librosDelGenero.primero;

                            while (temp != null) {
                                String libroActual = temp.getDato();
                                // Verificar si el libro contiene el ID buscado
                                if (libroActual.contains(idPrestar)) {
                                    libroPrestado = libroActual;
                                    libroEncontrado = true;

                                    // Eliminar de la biblioteca
                                    librosDelGenero.eliminar(libroActual);

                                    // Agregar a la lista de prestados
                                    listaPrestado.agregar(libroActual);

                                    System.out.println("✓ Libro prestado exitosamente:");
                                    System.out.println("  " + libroActual);
                                    System.out.println("  Género: " + genero);
                                    break;
                                }
                                temp = temp.getSiguiente();
                            }

                            if (libroEncontrado) {
                                break;
                            }
                        }

                        if (!libroEncontrado) {
                            System.out.println("❌ No se encontró ningún libro con el ID: " + idPrestar);
                        }
                        break;

                    case 3:
                        System.out.println("\n=== DEVOLVER LIBRO ===");

                        // Mostrar libros prestados
                        if (listaPrestado.primero == null) {
                            System.out.println("No hay libros prestados actualmente.");
                            break;
                        }

                        System.out.println("Libros actualmente prestados:");
                        listaPrestado.imprimir();

                        // Pedir ID del libro a devolver
                        System.out.print("\nIngrese el ID del libro a devolver (ej: LIB001): ");
                        String idDevolver = leer.readLine().trim();

                        // Buscar el libro en la lista de prestados
                        boolean libroEncontradoPrestado = false;
                        String libroDevuelto = null;

                        Nodo tempPrestado = listaPrestado.primero;
                        while (tempPrestado != null) {
                            String libroActual = tempPrestado.getDato();

                            if (libroActual.contains(idDevolver)) {
                                libroDevuelto = libroActual;
                                libroEncontradoPrestado = true;

                                // Extraer género del libro (asumiendo formato: "ID - Título")
                                String[] partes = libroActual.split(" - ");
                                if (partes.length >= 1) {
                                    // Buscar en qué género estaba originalmente
                                    for (String genero : generos) {
                                        // Verificar si el título podría pertenecer a este género
                                        // En una implementación real, deberías guardar el género original
                                        ListaDoble librosDelGenero = biblioteca.get(genero);
                                        Nodo tempGenero = librosDelGenero.primero;
                                        boolean encontradoEnGenero = false;

                                        while (tempGenero != null) {
                                            if (tempGenero.getDato().equals(libroActual)) {
                                                encontradoEnGenero = true;
                                                break;
                                            }
                                            tempGenero = tempGenero.getSiguiente();
                                        }

                                        // Si no está en este género, probablemente era de aquí
                                        if (!encontradoEnGenero) {
                                            // Eliminar de prestados
                                            listaPrestado.eliminar(libroActual);

                                            // Agregar de vuelta a la biblioteca
                                            librosDelGenero.agregar(libroActual);

                                            System.out.println("✓ Libro devuelto exitosamente:");
                                            System.out.println("  " + libroActual);
                                            System.out.println("  Género: " + genero);
                                            break;
                                        }
                                    }
                                }
                                break;
                            }
                            tempPrestado = tempPrestado.getSiguiente();
                        }

                        if (!libroEncontradoPrestado) {
                            System.out.println("❌ No se encontró ningún libro prestado con el ID: " + idDevolver);
                        }
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