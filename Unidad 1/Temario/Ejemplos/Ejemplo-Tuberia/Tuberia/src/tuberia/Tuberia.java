/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuberia;

import java.io.*;
/**
 *
 * @author Usuario
 */
public class Tuberia {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader bf = new BufferedReader(isr);
        String linea = null;
        try{
            System.out.println("Vamos a saludar ('Fin' para terminar)");
            linea=bf.readLine();
            while(linea!=null && linea.compareTo("Fin")!=0){
                System.out.println("Hola "+linea);
                linea=bf.readLine();
            }
        }catch(IOException ex){
            System.err.println("Se ha producido un error en E/S");
        }
    }
    
}
