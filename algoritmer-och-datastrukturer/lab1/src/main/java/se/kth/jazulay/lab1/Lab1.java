package se.kth.jazulay.lab1;

import com.google.common.base.Stopwatch;

public class Lab1 {
    public static void main(String[] args) {
        int levels = 13;
        
        Stopwatch[] stopwatches = {
            Stopwatch.createUnstarted(),
            Stopwatch.createUnstarted(),
            Stopwatch.createUnstarted(),
            Stopwatch.createUnstarted()
        };
        
        stopwatches[0].start();
        (new IterativePascal(true)).printPascal(20);
        stopwatches[0].stop();
        
        stopwatches[1].start();
        (new IterativePascal(false)).printPascal(150);
        stopwatches[1].stop();
        
        stopwatches[2].start();
        (new UncachedRecursivePascal(true)).printPascal(levels);
        stopwatches[2].stop();
        
        stopwatches[3].start();
        (new UncachedRecursivePascal(false)).printPascal(levels);
        stopwatches[3].stop();
        
        System.out.println("");
        
        for (Stopwatch sw : stopwatches) {
            System.out.println(sw);
        }
    }
}
