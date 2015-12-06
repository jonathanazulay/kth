package se.kth.jazulay.lab4;

import java.util.Iterator;

public class Trie {
    public int value = 0;
    private Trie[] children = new Trie[26];

    /**
     * Inserts k into the structure.
     * If k was not associated with any value v before,
     * associate it with 1, otherwise with v + 1 where v was the old value.
     * @param k put into structure
     */
    public void put (String k) {
        if (k.length() == 0) {
            this.value += 1;
            return;
        }
        
        char first = k.charAt(0);
        int childPos = this.getArrayPos(first);
        Trie child = children[childPos];
        String rest = k.substring(1);
        
        if (child == null) {
            child = new Trie();
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
        Trie child = this.getChild(k);
        if (child != null) {
            return child.value;
        }
        return 0;
    }
    
    /**
     * Calculates sum of all associated values
     * @param k prefix to start at
     * @return sum of all associated values
     */
    public int count (String k) {
        if (k.length() == 0) {            
            int count = this.value;
            for (Trie child : children) {
                if (child == null) { continue; }
                count += child.count(k);
            }
            return count;
        }
        
        Trie child = this.getChild(k);
        if (child == null) {
            return 0;
        } else {
            return child.count("");
        }        
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
        if (c < 97) {
            c = (char) (c + 32);
        }
        return c - 97;
    }
    
    private Trie getChild (String k) {
        if (k.length() == 0) {
            return this;       
        }
        
        char first = k.charAt(0);
        int childPos = this.getArrayPos(first);
        Trie child = children[childPos];
        
        if (child == null) {
            return null;
        }
        
        String rest = k.substring(1);
        return child.getChild(rest);
    }
}
