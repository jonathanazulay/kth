/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.jazulay.lab1;

/**
 *
 * @author jonathanazulay
 */
public class Lab1 {
    public static void main(String[] args) {
        RecursivePascal pascalPrinter = new RecursivePascal(true);
        pascalPrinter.printPascal(10);
        
        System.out.println("");
        
        RecursivePascal revPascalPrinter = new RecursivePascal(false);
        revPascalPrinter.printPascal(10);
    }
}
