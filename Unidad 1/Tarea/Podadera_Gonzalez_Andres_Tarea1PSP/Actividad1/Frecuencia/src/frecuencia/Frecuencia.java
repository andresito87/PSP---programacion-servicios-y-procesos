package frecuencia;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author andres
 */
public class Frecuencia {

    public static void main(String[] args) {
        // Con BufferedReader y InputStreamReader leo la información 
        // de la otra aplicación desde la salida estándar
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String linea;
            // compruebo que la línea leida no sea nula ni este vacia
            while ((linea = reader.readLine()) != null && !linea.isEmpty()) {
                int[] frecuencias = new int[5];  // a, e, i, o, u
                for (char c : linea.toLowerCase().toCharArray()) { // se podria usar un for normal y charAt(i)
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
                // saco la linea por la salida estandar
                System.out.println("Cadena: " + linea + " --> " + frecuencias[0] + " " + frecuencias[1] + " " + frecuencias[2] + " " + frecuencias[3] + " " + frecuencias[4]);
            }
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error: " + e.getMessage());
        }
    }
}
