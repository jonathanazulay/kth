package se.kth.jazulay.project1;

import java.util.ArrayList;
import se.kth.id1020.util.Attributes;
import se.kth.id1020.util.Word;

public class Index {
    
    /**
     * This ArrayList Contains one WordAttributesList for each distinct(!) word.
     * 
     * This makes indexing really fast when the same word
     * occurs many times because the same word does not have
     * to be sorted multiple times.
     */
    public final ArrayList<WordAttributesList> words = new ArrayList();
    
    class WordAttributesList {
        public String word;
        public final ArrayList<Word> words = new ArrayList();
        public final ArrayList<Attributes> attributes = new ArrayList();

        public WordAttributesList (String word) {
            this.word = word;
        }
        
        public void add (Word word, Attributes attrs) {
            if (this.word.equals(word.word)) {
                this.words.add(word);
                this.attributes.add(attrs);
            } else {
                throw new Error("word has to match the word for this collection (" + this.word + ")");
            }
        }
    }
    
    public void addWord (Word word, Attributes attrs) {
        int insertAt = this.sortedPosition(word.word);
        
        boolean wordExists = false;
        if (insertAt < this.words.size()) {
            wordExists = this.words.get(insertAt).word.equals(word.word);
        }
        
        if (wordExists) {
            this.words.get(insertAt).add(word, attrs);
        } else {
            WordAttributesList newList = new WordAttributesList(word.word);
            newList.add(word, attrs);
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
            int comparison = word.compareTo(this.words.get(mid).word);
            if      (comparison < 0) hi = mid - 1;
            else if (comparison > 0) lo = mid + 1;
            else return mid;
        }
        return lo;
    }
}
