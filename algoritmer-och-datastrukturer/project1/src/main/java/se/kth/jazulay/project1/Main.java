package se.kth.jazulay.project1;

import se.kth.id1020.Driver;
import se.kth.id1020.TinySearchEngineBase;

public class Main {
    public static void main (String[] args) throws Exception {
        TinySearchEngine searchEngine = new TinySearchEngine();
        Driver.run(searchEngine);
    }
}