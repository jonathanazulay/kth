package se.kth.jazulay.project1;

import java.util.List;

public interface OrderableSearchEngine {
    public <T> List<T> search (String[] words, String orderBy, String direction);
}
