package cadenas;

import java.util.Random;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author andres
 */
public class Cadenas {

    public static final String[] SILABAS = {
            "ba", "be", "bi", "bo", "bu", "ca", "ce", "ci", "co", "cu",
            "da", "de", "di", "do", "du", "fa", "fe", "fi", "fo", "fu",
            "ga", "ge", "gi", "go", "gu", "la", "le", "li", "lo", "lu",
            "ma", "me", "mi", "mo", "mu", "na", "ne", "ni", "no", "nu",
            "pa", "pe", "pi", "po", "pu", "ra", "re", "ri", "ro", "ru",
            "sa", "se", "si", "so", "su", "ta", "te", "ti", "to", "tu",
            "va", "ve", "vi", "vo", "vu", "za", "ze", "zi", "zo", "zu"
    }; // Nota: no se comtemplan caracteres acentuados

    public static void main(String[] args) {
        Random random = new Random();
        int cantidadCadenas = 0;
        // Verificar si se ha pasado un argumento
        if (args.length != 0) {
            try {
                cantidadCadenas = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                // muestro aviso de no
                System.err.println("El parámetro proporcionado no es un número válido.");
                System.exit(1); // Salir si la entrada no es válida
            }
        } else {
            // Si no se pasa un argumento, solicitar la cantidad de cadenas al usuario por
            // la entrada error
            // no se puede usar la entrada estándar porque la aplicación cogería esos
            // caracteres para analizarlos
            System.err.print("Introduce la cantidad de cadenas a generar: (ENTER para terminar) ");
            Scanner scanner = new Scanner(System.in);
            try {
                cantidadCadenas = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.err.println("Entrada no válida. Por favor, ingresa un número.");
                System.exit(1); // Salir si la entrada no es válida
            }
            scanner.close();
        }

        // Genero un stream de salida de datos y voy enviándolos a la salida estándar
        try (OutputStream out = System.out; PrintWriter writer = new PrintWriter(out)) { // true para auto-flush

            for (int i = 0; i < cantidadCadenas; i++) {
                StringBuilder cadena = new StringBuilder();
                // Longitud aleatoria de 2, 4, 6, 8, 10 para evitar
                // consonantes sobrantes y sean sílabas completas
                int longitudCadena = (random.nextInt(5) + 1) * 2;
                while (cadena.length() < longitudCadena) {
                    cadena.append(SILABAS[random.nextInt(SILABAS.length)]);
                }
                writer.println(cadena.substring(0, longitudCadena)); // me aseguro de no pasar de la longitud
                // El auto-flush asegura que los datos se envíen de inmediato.
            }
        } catch (Exception e) {
            System.err.println("Ha ocurrido un error: " + e.getMessage());
        }
    }
}
