package Ejercicio01;

import java.io.*;

public class LeerNombre {

    public static void main(String[] args) {
        if (args.length == 1) {
            System.out.println("Hola " + args[0]);
            System.exit(1);
        } else {
            System.out.println("Parámetros incorrectos.");
            System.exit(-1);
        }
    }
}
