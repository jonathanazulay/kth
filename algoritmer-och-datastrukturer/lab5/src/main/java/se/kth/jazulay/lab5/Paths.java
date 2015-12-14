package se.kth.jazulay.lab5;

import java.util.Arrays;
import se.kth.id1020.Graph;
import se.kth.id1020.DataSource;

public class Paths {
    public static void main(String[] args) {
        Graph g = DataSource.load();
        System.out.println(new SubgraphCounter(g).count());
        System.out.println(Arrays.toString(
            new ShortestPath(g).shortest("Renyn", "Parses")
        ));
    }
}
