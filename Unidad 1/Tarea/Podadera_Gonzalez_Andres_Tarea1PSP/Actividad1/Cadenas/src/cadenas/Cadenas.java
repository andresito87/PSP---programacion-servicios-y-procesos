package cadenas;

import java.io.BufferedReader;
import java.io.InputStreamReader;
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
    };

    public static void main(String[] args) {
        Random random = new Random();
        int cantidadCadenas = 0;

        // Verificar si se ha pasado un argumento
        if (args.length > 0) {
            try {
                cantidadCadenas = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.err.println("El parámetro proporcionado no es un número válido.");
                return; // Salir si el parámetro no es un número
            }
        } else {
            // Si no se pasa un argumento, solicitar la cantidad de cadenas al usuario
            Scanner scanner = new Scanner(System.in);
            System.out.print("Introduce la cantidad de cadenas a generar: ");
            try {
                cantidadCadenas = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.err.println("Entrada no válida. Por favor, ingresa un número.");
                return; // Salir si la entrada no es válida
            }
        }

        // Genero un stream de salida de datos y voy enviandolos a la salida estándar
        try (OutputStream out = System.out; PrintWriter writer = new PrintWriter(out)) {

            for (int i = 0; i < cantidadCadenas; i++) {
                StringBuilder cadena = new StringBuilder();
                // Longitud aleatoria de 2, 4, 6, 8, 10 para evitar 
                // consonantes sobrantes y sean silabas completas  
                int longitudCadena = (random.nextInt(5) + 1) * 2;
                while (cadena.length() < longitudCadena) {
                    cadena.append(SILABAS[random.nextInt(SILABAS.length)]);
                }
                writer.println(cadena.substring(0, longitudCadena)); // me aseguro de no pasar de la longitud
            }
        } catch (Exception e) {
            System.err.println("Ha ocurrido un error: " + e.getMessage());
        }
    }
}
