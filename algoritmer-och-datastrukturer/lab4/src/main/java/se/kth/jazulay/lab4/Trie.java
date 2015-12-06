package se.kth.jazulay.lab4;

import java.util.Iterator;

public class Trie {
    
    public char c = ' ';
    public int value = 0;
    public boolean isValue = false;
    private Trie[] children = new Trie[26];

    /**
     * Inserts k into the structure.
     * If k was not associated with any value v before,
     * associate it with 1, otherwise with v + 1 where v was the old value.
     * @param k put into structure
     */
    public void put (String k) {
        if (k.length() == 0) {
            this.isValue = true;
            this.value += 1;
            return;
        }
        
        char first = k.charAt(0);
        int childPos = this.getArrayPos(first);
        Trie child = children[childPos];
        String rest = k.substring(1);
        
        if (child == null) {
            child = new Trie();
            child.c = first;
            children[childPos] = child;
        }
        child.put(rest);
    }
    
    /**
     * Returns associated value for a key
     * @param k key
     * @return associated value
     */
    public int get (String k) {
        if (k.length() == 0) {
            if (this.isValue) {
                return this.value;
            } else {
                return 0;
            }
            
        }
        
        char first = k.charAt(0);
        int childPos = this.getArrayPos(first);
        Trie child = children[childPos];
        
        if (child == null) {
            return 0;
        }
        
        String rest = k.substring(1);
        return child.get(rest);
    }
    
    /**
     * Calculates sum of all associated values
     * @param k prefix to start at
     * @return sum of all associated values
     */
    public int count (String k) {
        return 0;
    }
    
    /**
     * Returns the number of distinct keys that have associated values
     * @param k prefix to start at
     * @return distinct count
     */
    public int distinct (String k) {
        return 0;
    }
    
    public Iterator<java.util.Map.Entry<String, Integer>> iterator (String k) {
        return null;
    }
    
    private int getArrayPos (char c) {
        return c - 97;
    }
}
