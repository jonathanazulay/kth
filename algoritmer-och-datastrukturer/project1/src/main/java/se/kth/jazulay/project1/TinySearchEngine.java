package se.kth.jazulay.project1;

import java.util.List;
import java.util.ArrayList;

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
        return searchResult;
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
