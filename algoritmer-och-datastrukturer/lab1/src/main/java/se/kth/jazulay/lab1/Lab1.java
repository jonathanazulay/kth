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
public class Lab1 {
    public static void main(String[] args) {
        Stopwatch swCached = Stopwatch.createStarted();
        Pascal pascalPrinter = new RecursivePascal(false);
        pascalPrinter.printPascal(30);
        swCached.stop();
        
        Stopwatch swUncached = Stopwatch.createStarted();
        Pascal unchached = new UncachedRecursivePascal(false);
        unchached.printPascal(30);
        swUncached.stop();
        
        System.out.println("");
        System.out.println("");
        
        System.out.println(swCached);
        System.out.println(swUncached);
    }
}
