package se.kth.jazulay.project1;

import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

import se.kth.id1020.TinySearchEngineBase;
import se.kth.id1020.util.Attributes;
import se.kth.id1020.util.Document;
import se.kth.id1020.util.Word;
import se.kth.jazulay.project1.Index.WordAttribute;

public class TinySearchEngine implements TinySearchEngineBase, OrderableSearchEngine {

    private final QueryParser queryParser = new QueryParser(this);
    private final Index index = new Index();
    
    @Override
    public void insert(Word word, Attributes atrbts) {
        index.addWord(word, atrbts);
    }

    @Override
    public List<Document> search(String string) {
        return queryParser.execute(string);
    }
    
    @Override
    public List<Document> search (String[] words, String orderBy, String direction) {
        boolean reverse = false;
        if (direction != null && direction.equals("desc")) {
            reverse = true;
        }
        
        List<WordAttribute> tempResult = new ArrayList();
        for (String word : words) {
            int result = this.binarySearch(word);
            if (result != -1) {
                for (WordAttribute wa : this.index.words.get(result)) {
                    tempResult.add(wa);
                }
            }
        }
        
        orderBy = "popularity";
        switch (orderBy) {
            case "occurance":
                this.sortByOccurance(tempResult, reverse);
                break;
            case "popularity":
                this.sortByPopularity(tempResult, reverse);
                break;
            case "count":
                this.sortByCount(tempResult, reverse);
                break;
        }
        
        
        List<Document> searchResult = new ArrayList();
        for (WordAttribute wa : tempResult) {
            searchResult.add(wa.attributes.document);
        }
        
        return searchResult;
    }
    
    private void sortByCount (List<WordAttribute> wordAttributes, boolean reverse) {
        
    }
    
    private void sortByPopularity (List<WordAttribute> wordAttributes, boolean reverse) {
        this.bubbleSort(wordAttributes, new Comparator<WordAttribute>() {
            @Override
            public int compare(WordAttribute o1, WordAttribute o2) {
                if (o1.attributes.document.popularity < o2.attributes.document.popularity) { return -1; }
                else if (o1.attributes.document.popularity > o2.attributes.document.popularity) { return 1; }
                else { return 0; }
            }
        }, reverse);
    }
    
    private void sortByOccurance (List<WordAttribute> wordAttributes, boolean reverse) {
        this.bubbleSort(wordAttributes, new Comparator<WordAttribute>() {
            @Override
            public int compare(WordAttribute o1, WordAttribute o2) {
                if (o1.attributes.occurrence < o2.attributes.occurrence) { return -1; }
                else if (o1.attributes.occurrence > o2.attributes.occurrence) { return 1; }
                else { return 0; }
            }
        }, reverse);
    }
    
    private void bubbleSort (List<WordAttribute> list, Comparator<WordAttribute> comparator, boolean reverse) {
        int shouldReverse = reverse ? -1 : 1;
        int r = list.size() - 2;
        boolean swapped = true;
        
        while (r >= 0 && swapped) {
            swapped = false;
            
            for (int i = 0; i <= r; i += 1) {
                int compareResult = comparator.compare(list.get(i), list.get(i + 1)) * shouldReverse;
                
                if (compareResult > 0) {
                    WordAttribute temp = list.get(i);
                    list.set(i, list.get(i + 1));
                    list.set(i + 1, temp);
                    swapped = true;
                }
            }
            r -= 1;
        }
    }

    /**
     * Looks for a word in the index using binary search
     * @param word to look for in the index
     * @return Location in array if found, -1 if not found
     */
    private int binarySearch (String word) {
        int lo = 0;
        int hi = this.index.words.size() - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int comparison = word.compareTo(this.index.words.get(mid).get(0).word.word);
            if      (comparison < 0) hi = mid - 1;
            else if (comparison > 0) lo = mid + 1;
            else return mid;
        }
        return -1;
    }
}
