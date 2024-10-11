package Ejercicio04;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Usuario
 */
public class Repetidor {
    public static void main(String[] args){
        if(args.length != 1)
            System.exit(1);
        for(int i=0; i<5;i++)
            System.out.println(args[0]);
    }
}
