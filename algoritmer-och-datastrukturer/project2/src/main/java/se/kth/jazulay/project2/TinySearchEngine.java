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
    RecursiveQueryParser parser = new RecursiveQueryParser();

    public TinySearchEngine() {
        this.wordOccurances = new HashMap<>();
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

    private void putWord (Word w, Attributes atrbts) {
        Set<Document> occurrences = this.wordOccurances.get(w.word);

        if (occurrences == null) {
            occurrences = new HashSet<>();
            this.wordOccurances.put(w.word, occurrences);
        }

        occurrences.add(atrbts.document);
    }

    @Override
    public List<Document> search(String string) {
        Query query = parser.parse(string);
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

        return sortedResult;
    }

    @Override
    public String infix(String string) {
        return parser.parse(string).toString();
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
            switch (qe.operator) {
                case MINUS:
                    return this.difference(
                        this.evaluate(qe.left),
                        this.evaluate(qe.right)
                    );
                case PLUS:
                    return this.intersection(
                        this.evaluate(qe.left),
                        this.evaluate(qe.right)
                    );
                case OR:
                    return this.union(
                        this.evaluate(qe.left),
                        this.evaluate(qe.right)
                    );
                default:
                    return new HashSet<>();
            }
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
