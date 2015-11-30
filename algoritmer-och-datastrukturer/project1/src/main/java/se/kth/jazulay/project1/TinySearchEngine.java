package se.kth.jazulay.project1;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

import se.kth.id1020.TinySearchEngineBase;
import se.kth.id1020.util.Attributes;
import se.kth.id1020.util.Document;
import se.kth.id1020.util.Word;

public class TinySearchEngine implements TinySearchEngineBase {

    private final ArrayList<Word> words = new ArrayList();
    private final ArrayList<Attributes> attributes = new ArrayList();
    
    @Override
    public void insert(Word word, Attributes atrbts) {
        int insertAt = this.sortedPosition(word);
        this.words.add(insertAt, word);
        this.attributes.add(insertAt, atrbts);
    }

    @Override
    public List<Document> search(String string) {
        int low = 0;
        int high = this.words.size() - 1;

        int middle = 0;
        while (high >= low) {
            middle = (low + high) / 2;            
            int compareResult = this.words.get(middle).word.compareTo(string);
            
            if (compareResult < 0) {
                low = middle + 1;
            } else if (compareResult > 0) {
                high = middle - 1;
            } else {
                return null;
            }
        }
        
        return null;
    }
    
    private int sortedPosition (Word word) {
        int low = 0;
        int high = this.words.size() - 1;

        int middle = 0;
        while (high >= low) {
            middle = (low + high) / 2;            
            int compareResult = this.words.get(middle).word.compareTo(word.word);
            
            if (compareResult < 0) {
                low = middle + 1;
            } else if (compareResult > 0) {
                high = middle - 1;
            } else {
                break;
            }
        }
        return middle;        
    }
}
