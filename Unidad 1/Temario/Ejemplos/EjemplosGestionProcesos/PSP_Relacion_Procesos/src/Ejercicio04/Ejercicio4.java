/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio04;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class Ejercicio4 {

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);

            System.out.println("Dame un texto para repetir: ");

            String texto = sc.next();

            File directorio = new File("C:\\Relacion1");

            ProcessBuilder pb2 = new ProcessBuilder("javac", "Repetidor.java");
            pb2.directory(directorio);
            Process p2 = pb2.start();

            try {
                p2.waitFor();
            } catch (InterruptedException ex) {
                Logger.getLogger(Ejercicio4.class.getName()).log(Level.SEVERE, null, ex);
            }

            ProcessBuilder pb1 = new ProcessBuilder("java", "Repetidor", texto);
            pb1.directory(directorio);
            Process p1 = pb1.start();

            int exitVal;
            try {
                //El proceso actual espera hasta que el subproceso Process finalice
                exitVal = p1.waitFor(); //Recoge la salida de System.exit()
                if(exitVal == 0){
                    System.out.println("Salida del repetidor");
                    System.out.println("====================");
                }else{
                    System.out.println("MAL");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                InputStream is = p1.getInputStream();
                int c;
                while ((c = is.read()) != -1) {
                    System.out.print((char) c);
                }
                is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (IOException ex) {
            Logger.getLogger(Ejercicio4.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
