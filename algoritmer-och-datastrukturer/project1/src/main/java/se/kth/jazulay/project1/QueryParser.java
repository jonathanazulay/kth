package se.kth.jazulay.project1;

import java.util.List;

public class QueryParser {
    OrderableSearchEngine searchEngine;
    
    public QueryParser(OrderableSearchEngine engine) {
        this.searchEngine = engine;
    }

    public <T> List<T> execute (String query) {
        String[] strings = query.split("\\s+");
        
        if (strings.length >= 4 && strings[strings.length - 3].equals("orderBy")) {
            return searchEngine.search(new String[]{strings[0]}, strings[strings.length - 2], strings[strings.length - 1]);
        } else {
            return searchEngine.search(strings, null, null);
        }
    }
}
