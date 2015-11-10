/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.jazulay.lab1;

import com.google.common.base.Stopwatch;

/**
 *
 * @author jonathanazulay
 */
public class Driver {
    public static void main(String[] args) {
        Pascal[] pascals = {
            new IterativePascal(true),
            new IterativePascal(false),
            new RecursivePascal(true),
            new RecursivePascal(false)
        };
 
        // Test a triangle with each generator
        for (Pascal p : pascals) {
            Stopwatch sw = Stopwatch.createStarted();
            p.printPascal(15);
            System.out.flush();
            System.err.flush();
            System.err.println("");
            System.err.println("----------");
            System.err.println("Time: " + sw);
            System.err.println("----------");
        }
        // test some binom values
        for (Pascal p : pascals) {
            Stopwatch sw = Stopwatch.createStarted();
            System.err.println("");
            testBinom(p, 10, 4);
            testBinom(p, 5, 1);
            testBinom(p, 6, 1);
            testBinom(p, 11, 11);
            testBinom(p, 5, 0);
            testBinom(p, 8, 8);
            testBinom(p, 0, 0);
            testBinom(p, 1, 0);
            System.err.println("--------");
        }
    }
    
    public static void testBinom (Pascal with, int n, int k) {
        System.out.println(n + ", " + k + ": " + with.binom(n, k));
    }
}
