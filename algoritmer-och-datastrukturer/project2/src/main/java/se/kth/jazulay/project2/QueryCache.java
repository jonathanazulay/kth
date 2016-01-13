package se.kth.jazulay.project2;

import java.util.HashMap;

public class QueryCache <T> {
    HashMap<QueryExpression, T> cache = new HashMap<>();

    public void put (QueryExpression qe, T result) {
        cache.put(qe, result);
    }

    public T get (QueryExpression qe) {
        if (cache.containsKey(qe)) {
            return cache.get(qe);
        } else {
            return null;
        }
    }

    public boolean hasCachedResult (QueryExpression qe) {
        return this.get(qe) != null;
    }
}
