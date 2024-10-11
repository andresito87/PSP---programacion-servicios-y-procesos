/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio03;

import java.io.*;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class Ejercicio3 {
    public static void main(String args[]) throws IOException {

        File directorio = new File("C:\\Relacion1");
        
        ProcessBuilder pb2 = new ProcessBuilder("javac","EjemploLectura.java");
        pb2.directory(directorio);
       	Process p2 = pb2.start();
        
        try {
            p2.waitFor();
        } catch (InterruptedException ex) {
            Logger.getLogger(Ejercicio3.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        ProcessBuilder pb1 = new ProcessBuilder("java","EjemploLectura");
        pb1.directory(directorio);
        Process p1 = pb1.start();
        		
        try{
            OutputStream os = p1.getOutputStream();
            os.write("Hola Fran\n".getBytes());
            os.flush();
            
            InputStream is = p1.getInputStream();
            int c;
            while((c= is.read())!=-1)
                System.out.print((char)c);
            is.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        
        int exitVal;
        try{
            //El proceso actual espera hasta que el subproceso Process finalice
            exitVal = p1.waitFor(); //Recoge la salida de System.exit()
            System.out.println("Valor de salida "+exitVal);
            
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}
