package se.kth.jazulay.project1;

import java.util.ArrayList;
import se.kth.id1020.util.Attributes;
import se.kth.id1020.util.Word;

public class Index {
    /**
     * Uses a nested ArrayList where each nested list element contains just equal words
     * 
     * This makes indexing really fast when the same word
     * occurs many times because the same word does not have
     * to be sorted multiple times.
     */
    public final ArrayList<ArrayList<WordAttribute>> words = new ArrayList();
    
    class WordAttribute {
        public Word word;
        public Attributes attributes;
        public int count;
        
        public WordAttribute (Word word, Attributes attributes) {
            this.word = word;
            this.attributes = attributes;
            this.count = 1;
        }    
    }
    
    public void addWord (Word word, Attributes attrs) {
        WordAttribute wa = new WordAttribute(word, attrs);
        
        int insertAt = this.sortedPosition(word.word);
        
        boolean wordExists = false;
        if (insertAt < this.words.size()) {
            wordExists = this.words.get(insertAt).get(0).word.word.equals(word.word);
        }
        
        if (wordExists) {
            this.incrementOccuranceCount(insertAt, wa);
            this.addToExistingList(wa, insertAt);
        } else {
            this.addToNewList(wa, insertAt);
        }
    }
    
    private void incrementOccuranceCount (int index, WordAttribute wordAttr) {
        int count = 0;
        for (WordAttribute wa : this.words.get(index)) {
            if (wa.attributes.document.name.equals(wordAttr.attributes.document.name)) {
                wa.count += 1;
                wordAttr.count = wa.count;
            }
        }
    }
    
    private void addToExistingList (WordAttribute wa, int index) {
        this.words.get(index).add(wa);
    }
    
    private void addToNewList (WordAttribute wa, int index) {
        ArrayList<WordAttribute> newList = new ArrayList();
        newList.add(wa);
        this.words.add(index, newList);
    }
    
    /**
     * Returns a possible correct position of a string in the sorted index.
     * It's like a binary search but instead of returning -1 if item is not
     * found, It returns the position it would have existed at if it existed.
     * @param word
     * @return the index to place the word at in order to keep array sorted
     */
    private int sortedPosition (String word) {
        int lo = 0;
        int hi = this.words.size() - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int comparison = word.compareTo(this.words.get(mid).get(0).word.word);
            if      (comparison < 0) hi = mid - 1;
            else if (comparison > 0) lo = mid + 1;
            else return mid;
        }
        return lo;
    }
}
