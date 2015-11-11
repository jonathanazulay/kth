package se.kth.jazulay.lab1;

/******************************************************************************
 *  Compilation:  javac BinarySearch.java
 *  Execution:    java BinarySearch wordlist.txt < input.txt
 *
 *  Read in an alphabetical list of words from the given file.
 *  Then prompt the user to enter words. The program reports which
 *  words are *not* in the wordlist.
 * 
 *  % java BinarySearch wordlist.txt < index.html
 *
 ******************************************************************************/

import edu.princeton.cs.introcs.In;
import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;
import java.util.Arrays;

public class BinarySearch {

    private static int calls;
    
    // return the index of the key in the sorted array a[]; -1 if not found
    public static int search(String key, String[] a) {
        calls = 0;
        return search(key, a, 0, a.length);
    }
    public static int search(String key, String[] a, int lo, int hi) {
        System.out.println("made call, " + ++calls);
        // possible key indices in [lo, hi)
        if (hi <= lo) return -1;
        int mid = lo + (hi - lo) / 2;
        int cmp = a[mid].compareTo(key);
        if      (cmp > 0) return search(key, a, lo, mid);
        else if (cmp < 0) return search(key, a, mid+1, hi);
        else              return mid;
    }


    // whitelist, exception filter
    public static void main(String[] args) {
        System.out.println(search("1", new String[]{"1", "2", "3", "4", "5", "6", "7", "8"})); // worst case
        System.out.println(search("5", new String[]{"1", "2", "3", "4", "5", "6", "7", "8"})); // best case
        
        
        In in = new In("largeW.txt");
        String s = in.readAll();
        String[] words = s.split("\\s+");
        System.err.println("Done reading words");

        // sort the words (if needed)
        Arrays.sort(words);
        System.err.println("Done sorting words");

        // prompt user to enter a word and check if it's there
        while (!StdIn.isEmpty()) {
            String key = StdIn.readString();
            if (search(key, words) < 0) StdOut.println(key);
        }
    }
}

