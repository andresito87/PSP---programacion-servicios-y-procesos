/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package darnombres;

import java.util.Scanner;

/**
 *
 * @author Usuario
 */
public class DarNombres {
    public static void main(String args[]){
        String nombres[] = {"Fran", "Antonio","Pepe","Amalia","Elena","Ana","Diego"};
        int num=0; 
        Scanner sc = new Scanner(System.in);
        System.out.println("Dime cuántos nombres te muestro: ");
        num=sc.nextInt();
        while(num<=0){
            System.out.println("Dime cuántos nombres te muestro: ");
            num=sc.nextInt();
        }
        for(int i = 0; i<num;i++){
            System.out.println(nombres[i%7]);
        }
    }
}
