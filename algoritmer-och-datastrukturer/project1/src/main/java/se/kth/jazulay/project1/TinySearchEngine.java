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
        List<WordAttribute> tempResult = new ArrayList();
        for (String word : words) {
            int result = this.binarySearch(word);
            if (result != -1) {
                for (WordAttribute wa : this.index.words.get(result)) {
                    tempResult.add(wa);
                }
            }
        }
        
        if (orderBy != null) {
            boolean reverse = false;
            if (direction.equals("desc")) {
                reverse = true;
            }   
            this.sortBy(orderBy, tempResult, reverse);
        }
        
        List<Document> searchResult = new ArrayList();
        for (WordAttribute wa : tempResult) {
            searchResult.add(wa.attributes.document);
        }
        
        return this.distinct(searchResult);
    }
    
    private void sortBy (String by, List<WordAttribute> wordAttributes, boolean reverse) {
        switch (by) {
            case "popularity":
                this.sortByPopularity(wordAttributes, reverse);
                break;
            case "occurrence":
                this.sortByOccurance(wordAttributes, reverse);
                break;
            case "count":
                this.sortByCount(wordAttributes, reverse);
                break;
        }
    }
    
    private void sortByPopularity (List<WordAttribute> wordAttributes, boolean reverse) {
        BubbleSort<WordAttribute> sorter = new BubbleSort(new Comparator<WordAttribute>() {
            @Override
            public int compare(WordAttribute o1, WordAttribute o2) {
                if (o1.attributes.document.popularity < o2.attributes.document.popularity) { return -1; }
                else if (o1.attributes.document.popularity > o2.attributes.document.popularity) { return 1; }
                else { return 0; }
            }
        });
        sorter.sort(wordAttributes, reverse);
    }
    
    private void sortByOccurance (List<WordAttribute> wordAttributes, boolean reverse) {
        BubbleSort<WordAttribute> sorter = new BubbleSort(new Comparator<WordAttribute>() {
            @Override
            public int compare(WordAttribute o1, WordAttribute o2) {
                if (o1.attributes.occurrence < o2.attributes.occurrence) { return -1; }
                else if (o1.attributes.occurrence > o2.attributes.occurrence) { return 1; }
                else { return 0; }
            }
        });
        sorter.sort(wordAttributes, reverse);
    }

    private void sortByCount (List<WordAttribute> wordAttributes, boolean reverse) {
        throw new UnsupportedOperationException("count sorting not implemented");
    }
    
    /**
     * Returns a new list with only distinct elements
     * @param <T> type of elements
     * @param list to sort
     * @return new distinct list
     */
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
            int comparison = word.compareTo(this.index.words.get(mid).get(0).word.word);
            if      (comparison < 0) hi = mid - 1;
            else if (comparison > 0) lo = mid + 1;
            else return mid;
        }
        return -1;
    }
}
