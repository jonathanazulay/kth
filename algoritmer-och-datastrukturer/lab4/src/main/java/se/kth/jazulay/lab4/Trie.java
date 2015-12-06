package se.kth.jazulay.lab4;

import java.util.Iterator;

public class Trie {
    
    /**
     * Inserts k into the structure.
     * If k was not associated with any value v before,
     * associate it with 1, otherwise with v + 1 where v was the old value.
     * @param k put into structure
     */
    public void put (String k) {
        
    }
    
    /**
     * Returns associated value for a key
     * @param k key
     * @return associated value
     */
    public int get (String k) {
        return 0;
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
}
