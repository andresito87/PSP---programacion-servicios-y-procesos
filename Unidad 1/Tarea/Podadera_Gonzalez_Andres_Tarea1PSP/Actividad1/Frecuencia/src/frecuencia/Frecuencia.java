package frecuencia;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 *
 * @author andres
 */
public class Frecuencia {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String linea;

            // El programa contempla varias posibilidades de entrada de la información
            if (args.length != 0) {
                // Si le pasan la cadena como parametro en la llamada, la analiza
                linea = args[0];
                analizarCadena(linea);
            } else if (System.console() == null) {
                // Si no hay consola disponible, significa que se está usando un pipe
                while ((linea = reader.readLine()) != null) {
                    analizarCadena(linea);
                }
            } else {
                // Modo interactivo: No esta usando ni pipe ni los parametros, pide cadena al usuario
                Scanner scanner = new Scanner(System.in);
                System.out.print("Introduce una cadena: (ENTER) para terminar ");
                linea = scanner.nextLine();
                if (!linea.isEmpty()) {
                    analizarCadena(linea);
                }
            }
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error: " + e.getMessage());
        }
    }

    private static void analizarCadena(String linea) {
        int[] frecuencias = new int[5];  // a, e, i, o, u
        for (char c : linea.toLowerCase().toCharArray()) {
            switch (c) {
                case 'a' ->
                    frecuencias[0]++;
                case 'e' ->
                    frecuencias[1]++;
                case 'i' ->
                    frecuencias[2]++;
                case 'o' ->
                    frecuencias[3]++;
                case 'u' ->
                    frecuencias[4]++;
            }
        }
        System.out.println("Cadena: " + linea + " --> " + frecuencias[0] + " " + frecuencias[1] + " " + frecuencias[2] + " " + frecuencias[3] + " " + frecuencias[4]);
    }
}
