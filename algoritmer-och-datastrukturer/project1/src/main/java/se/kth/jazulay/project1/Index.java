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
        
        public WordAttribute (Word word, Attributes attributes) {
            this.word = word;
            this.attributes = attributes;
        }    
    }
    
    public void addWord (Word word, Attributes attrs) {
        int insertAt = this.sortedPosition(word.word);
        
        boolean wordExists = false;
        if (insertAt < this.words.size()) {
            wordExists = this.words.get(insertAt).get(0).word.word.equals(word.word);
        }
        
        if (wordExists) {
            this.words.get(insertAt).add(new WordAttribute(word, attrs));
        } else {
            ArrayList<WordAttribute> newList = new ArrayList();
            newList.add(new WordAttribute(word, attrs));
            this.words.add(insertAt, newList);
        }
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
