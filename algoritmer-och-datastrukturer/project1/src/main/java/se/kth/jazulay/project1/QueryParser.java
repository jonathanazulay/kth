package se.kth.jazulay.project1;

import java.util.List;

public class QueryParser {
    String delimiter = "orderBy";
    String[] properties = new String[]{"count", "popularity", "occurrence"};
    String[] directions = new String[]{"asc", "desc"};
    
    OrderableSearchEngine searchEngine;
    
    public QueryParser(OrderableSearchEngine engine) {
        this.searchEngine = engine;
    }

    public <T> List<T> execute (String query) {
        String[] strings = query.split("\\s+");
        boolean couldBeQuery = strings.length >= 4;
        boolean isQuery = couldBeQuery && strings[strings.length - 3].equals(delimiter);
   
        if (isQuery) {
            String property = strings[strings.length - 2];
            String direction = strings[strings.length - 1];
            
            if (isDirection(direction) && isProperty(property)) {
                return searchEngine.search(extractSearchTerms(strings), property, direction);
            }
        }
        return searchEngine.search(strings, null, null);
    }
    
    private boolean isProperty (String test) {
        for (String property : this.properties) {
            if (test.equals(property)) { return true; }
        }
        return false;
    }
    
    private boolean isDirection (String test) {
        for (String property : this.directions) {
            if (test.equals(property)) { return true; }
        }
        return false;
    }
    
    // deletes last 3 elements from string array
    private String[] extractSearchTerms (String[] query) {
        String[] searchTerms = new String[query.length - 3];
        for (int i = 0; i < query.length - 3; i += 1) {
            searchTerms[i] = query[i];
        }
        return searchTerms;
    }
}
