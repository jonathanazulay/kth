package se.kth.jazulay.project1;

import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

import se.kth.id1020.TinySearchEngineBase;
import se.kth.id1020.util.Attributes;
import se.kth.id1020.util.Document;
import se.kth.id1020.util.Word;

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
        
        List<Document> searchResult = new ArrayList();
        for (String word : words) {
            int result = this.binarySearch(word);
            if (result != -1) {
                for (Attributes attribute : this.index.words.get(result).attributes) {
                    searchResult.add(attribute.document);
                }
            }
        }
        searchResult = distinct(searchResult);
        
        this.bubbleSort(searchResult, new Comparator<Document>() {
            @Override
            public int compare(Document o1, Document o2) {
                if (o1.popularity < o2.popularity) { return -1; }
                else if (o1.popularity > o2.popularity) { return 1; }
                else { return 0; }
            }
        }, reverse);
        return searchResult;
    }
    
    private void bubbleSort (List<Document> list, Comparator<Document> comparator, boolean reverse) {
        int shouldReverse = reverse ? -1 : 1;
        int r = list.size() - 2;
        boolean swapped = true;
        
        while (r >= 0 && swapped) {
            swapped = false;
            
            for (int i = 0; i <= r; i += 1) {
                int compareResult = comparator.compare(list.get(i), list.get(i + 1)) * shouldReverse;
                
                if (compareResult > 0) {
                    Document temp = list.get(i);
                    list.set(i, list.get(i + 1));
                    list.set(i + 1, temp);
                    swapped = true;
                }
            }
            r -= 1;
        }
    }

    private <T> List<T> distinct (List<T> list) {
        List<T> distinctList = new ArrayList<>();

        for (T e : list) {
            boolean found = false;
            for (T e2 : distinctList) {
                if (e2.equals(e)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                distinctList.add(e);
            }
        }

        return distinctList;
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
            int comparison = word.compareTo(this.index.words.get(mid).word);
            if      (comparison < 0) hi = mid - 1;
            else if (comparison > 0) lo = mid + 1;
            else return mid;
        }
        return -1;
    }
}
