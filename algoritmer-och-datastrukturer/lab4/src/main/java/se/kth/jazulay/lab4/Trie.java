package se.kth.jazulay.lab4;

import java.util.AbstractMap.SimpleEntry;
import java.util.Iterator;
import java.util.Map;

public class Trie {
    public int value = 0;
    char c;
    private Trie[] children = new Trie[128];
    private int childCount = 0;

    public Trie () {
        this.c = ' ';
    }
    
    public Trie (char c) {
        this.c = c;
    }

    /**
     * Inserts k into the structure.
     * If k was not associated with any value v before,
     * associate it with 1, otherwise with v + 1 where v was the old value.
     * @param k key to put into trie
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
            child = new Trie(first);
            children[childPos] = child;
            childCount += 1;
        }
        child.put(rest);
    }
    
    /**
     * Returns associated value for a key
     * @param k key
     * @return associated value
     */
    public int get (String k) {
        Trie child = this.getChildTrie(k);
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
        
        Trie child = this.getChildTrie(k);
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
        if (k.length() == 0) {
            int count = 0;
            if (this.value > 0) {
                count = 1;
            }
            
            for (Trie child : children) {
                if (child == null) { continue; }
                count += child.distinct(k);
            }
            return count;
        }
        
        Trie child = this.getChildTrie(k);
        if (child == null) {
            return 0;
        } else {
            return child.distinct("");
        }
    }
    
    private Trie getChild (int n) {
        int count = -1;
        for (Trie trie : children) {
            if (trie != null) { count += 1; }
            else { continue; }
            if (count == n) { return trie; }
        }
        return null;
    }
    
    public Iterator<java.util.Map.Entry<String, Integer>> iterator (final String k) {        
        return new Iterator<Map.Entry<String, Integer>>() {
            
            StringBuilder sb = new StringBuilder(k);
            java.util.Stack<Trie> currentTrie;
            java.util.Stack<Integer> positionInTrie;
            boolean isMovingDown;
            
            {
                this.currentTrie = new java.util.Stack<>();
                this.positionInTrie = new java.util.Stack<>();
                
                isMovingDown = true;
                
                Trie startAt = Trie.this.getChildTrie(k);
                
                if (startAt.value > 0 || startAt.childCount > 0) {
                    this.positionInTrie.push(0);
                    this.currentTrie.push(startAt);
                }
            }
   
            @Override
            public boolean hasNext() {
                return currentTrie.size() > 0;
            }

            @Override
            public Map.Entry<String, Integer> next() {
                int pos = this.positionInTrie.pop();
                Trie trie = this.currentTrie.pop();

                java.util.Map.Entry<String, Integer> returnVal;
                
                if (pos + 1 < trie.childCount) {
                    // There's more tries here to get back to
                    this.positionInTrie.push(pos + 1);
                    this.currentTrie.push(trie);
                }
                
                if (pos > trie.childCount) {
                    return this.next();
                }
                
                boolean wasMovingDown = isMovingDown;
                if (pos < trie.childCount) { 
                    
                    this.positionInTrie.push(0);
                    this.currentTrie.push(trie.getChild(pos));
                    isMovingDown = true;
                } else {
                    isMovingDown = false;
                }

                
                if (trie.value > 0) {
                    if (wasMovingDown) {
                        returnVal = new SimpleEntry(sb.toString(), trie.value);
                    } else {
                        returnVal = this.next();
                    }
                } else {
                    returnVal = this.next();
                }
                
                return returnVal;
            }
            
            private void popChar () {
                if (sb.length() <= 0) { return; }
                sb.setLength(sb.length());
            }
            
            private void pushChar (char c) {
                sb.append(c);
            }
        };
    }
   
    
    
    private int getArrayPos (char c) {
        return c;
    }
    
    public Trie getChildTrie (String k) {
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
        return child.getChildTrie(rest);
    }
}
