import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        BufferedReader leer = new BufferedReader(new InputStreamReader(System.in));
        int opcion = 0;
        do {
            try {
                // Mostrar el menú
                System.out.println("=== MENÚ DE LIBRERIA ===");
                System.out.println("1. Agregar libro");
                System.out.println("2. Prestar libro");
                System.out.println("3. Devolver libro");
                System.out.println("4. Reordenarmiento");
                System.out.println("0. Continuar");
                System.out.print("Seleccione una opción (0-3): ");
                // Leer la opción del usuario
                opcion = Integer.parseInt(leer.readLine());
                switch (opcion) {
                    case 1:

                        break;
                    case 2:

                        break;
                    case 3:

                        break;
                    case 4:
                        /*MERGE O QUICKSORT AQUI*/
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Opción no válida. Por favor, seleccione 1-3.");
                        break;
                }
            } catch (IOException e) {
                System.out.println("Error de entrada/salida: " + e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Error: Por favor ingrese un número válido.");
            }
        }while (opcion != 0);


    }
}