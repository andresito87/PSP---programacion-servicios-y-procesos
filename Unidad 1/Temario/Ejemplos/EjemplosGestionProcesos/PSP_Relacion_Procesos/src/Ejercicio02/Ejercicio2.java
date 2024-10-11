package Ejercicio02;

import java.io.*;

/**
 *
 * @author Usuario
 */
public class Ejercicio2 {

    public static void main(String[] args) throws IOException {
        Process p1 = new ProcessBuilder("CMD", "/C", "DIR").start();
        int exitVal;
        try {
            //El proceso actual espera hasta que el subproceso Process finalice
            exitVal = p1.waitFor(); //Recoge la salida de System.exit()
            System.out.println("Valor de salida " + exitVal);
            if (exitVal == 0) {
                InputStream is = p1.getInputStream();
                int c;
                while ((c = is.read()) != -1) {
                    System.out.print((char) c);
                }
                is.close();
            } else {
                File f = new File("errores.txt");
                ProcessBuilder pb1 = new ProcessBuilder("CMD", "/C", "DIRRR");
                //ProcessBuilder pb1 = new ProcessBuilder("java", "LeerNombre");
                pb1.redirectError(f);
                Process p = pb1.start();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
