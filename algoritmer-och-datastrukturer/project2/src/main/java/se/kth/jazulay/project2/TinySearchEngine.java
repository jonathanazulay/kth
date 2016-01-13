package se.kth.jazulay.project2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import se.kth.id1020.TinySearchEngineBase;
import se.kth.id1020.util.Attributes;
import se.kth.id1020.util.Document;
import se.kth.id1020.util.Sentence;
import se.kth.id1020.util.Word;

public class TinySearchEngine implements TinySearchEngineBase {
    HashMap<String, Set<Document>> wordOccurances;
    HashMap<Document, Integer> wordCount;
    HashMap<String, HashMap<Document, Integer>> occurrenceCount;

    RecursiveQueryParser parser = new RecursiveQueryParser();
    QueryCache<Set<Document>> cache = new QueryCache<>();

    public TinySearchEngine() {
        this.wordOccurances = new HashMap<>();
        this.wordCount = new HashMap<>();
        this.occurrenceCount = new HashMap<>();
    }

    @Override
    public void preInserts() {

    }

    @Override
    public void postInserts() {

    }

    @Override
    public void insert(Sentence sntnc, Attributes atrbts) {
        for (Word w : sntnc.getWords()) {
            this.putWord(w, atrbts);
        }
    }

    @Override
    public List<Document> search(String string) {
        final Query query = parser.parse(string);

        List<Document> sortedResult = new ArrayList(
            this.evaluate(query.expression)
        );

        Collections.sort(sortedResult, new Comparator<Document>() {
            @Override
            public int compare (Document o1, Document o2) {
                if (o1.popularity < o2.popularity) { return 1; }
                else if (o1.popularity > o2.popularity) { return -1; }
                else { return 0; }
            }
        });

        Collections.sort(sortedResult, new Comparator<Document>() {
            @Override
            public int compare (Document o1, Document o2) {
                double relDoc1 = TinySearchEngine.this.tfidf(query.expression, o1);
                double relDoc2 = TinySearchEngine.this.tfidf(query.expression, o2);

                if (relDoc1 < relDoc2) { return 1; }
                else if (relDoc1 > relDoc2) { return -1; }
                else { return 0; }
            }
        });

        return sortedResult;
    }

    @Override
    public String infix(String string) {
        return parser.parse(string).toString();
    }

    private double tfidf (QueryExpression qe, Document doc) {
        if (qe.value != null) {
            if (numberOfDocumentsContainingWord(qe.value) == 0) {
                return 0;
            }
            return (
                Math.log10((double)this.numberOfDocuments() / (double)this.numberOfDocumentsContainingWord(qe.value))
                + (double)this.numberOfOccurrencesInDocument(qe.value, doc) / (double)this.numberOfWords(doc)
            );
        } else {
            if (qe.operator == Operator.MINUS) {
                return this.tfidf(qe.left, doc);
            } else {
                return this.tfidf(qe.left, doc) + this.tfidf(qe.right, doc);
            }
        }
    }

    private void putWord (Word w, Attributes atrbts) {
        // Increase number of occurrences of this word in this document, used for relevance calc
        if (this.occurrenceCount.get(w.word) == null) {
            this.occurrenceCount.put(w.word, new HashMap<Document, Integer>());
        }

        if (this.occurrenceCount.get(w.word).get(atrbts.document) == null) {
            this.occurrenceCount.get(w.word).put(atrbts.document, 1);
        } else {
            this.occurrenceCount.get(w.word).put(atrbts.document, this.occurrenceCount.get(w.word).get(atrbts.document) + 1);
        }

        // Increase number of word this document, used for relevance calc
        Integer nWords = this.wordCount.get(atrbts.document);
        if (nWords == null) {
            nWords = 1;
        } else {
            nWords += 1;
        }
        this.wordCount.put(atrbts.document, nWords);

        // Add this document to the word map
        Set<Document> occurrences = this.wordOccurances.get(w.word);
        if (occurrences == null) {
            occurrences = new HashSet<>();
            this.wordOccurances.put(w.word, occurrences);
        }
        occurrences.add(atrbts.document);
    }

    private int numberOfOccurrencesInDocument (String word, Document doc) {
        try {
            return this.occurrenceCount.get(word).get(doc);
        } catch (NullPointerException e) {
            return 0;
        }
    }

    private int numberOfDocumentsContainingWord (String word) {
        try {
            return this.wordOccurances.get(word).size();
        } catch (NullPointerException e) {
            return 0;
        }
    }

    private int numberOfDocuments () {
        try {
            return this.wordCount.size();
        } catch (NullPointerException e) {
            return 0;
        }
    }

    private int numberOfWords (Document in) {
        try {
            return this.wordCount.get(in);
        } catch (NullPointerException e) {
            return 0;
        }
    }

    private Set<Document> find (String term) {
        Set<Document> lookup = this.wordOccurances.get(term);
        if (lookup == null) { return new HashSet<>(); }

        Set<Document> result = new HashSet<>(lookup.size());
        for (Document doc : lookup) {
            result.add(doc);
        }
        return result;
    }

    private Set<Document> evaluate (QueryExpression qe) {
        if (qe.value != null) { return this.find(qe.value); }
        else {
            if (this.cache.hasCachedResult(qe)) {
                return this.cache.get(qe);
            }

            Set<Document> result = null;
            switch (qe.operator) {
                case MINUS:
                    result = this.difference(
                        this.evaluate(qe.left),
                        this.evaluate(qe.right)
                    );
                    break;
                case PLUS:
                    result = this.intersection(
                        this.evaluate(qe.left),
                        this.evaluate(qe.right)
                    );
                    break;
                case OR:
                    result = this.union(
                        this.evaluate(qe.left),
                        this.evaluate(qe.right)
                    );
                    break;
            }
            this.cache.put(qe, result);
            return result;
        }
    }

    private Set<Document> intersection (Set<Document> list1, Set<Document> list2) {
        Set<Document> result = new HashSet<>();

        for (Document doc : list1) {
            if (list2.contains(doc)) {
                result.add(doc);
            }
        }

        return result;
    }

    private Set<Document> union (Set<Document> list1, Set<Document> list2) {
        Set<Document> result = new HashSet<>(list1);

        for (Document doc : list2) {
            result.add(doc);
        }

        return result;
    }

    private Set<Document> difference (Set<Document> list1, Set<Document> list2) {
        Set<Document> result = new HashSet<>();

        for (Document doc : list1) {
            if (!list2.contains(doc)) {
                result.add(doc);
            }
        }

        return result;
    }
}
