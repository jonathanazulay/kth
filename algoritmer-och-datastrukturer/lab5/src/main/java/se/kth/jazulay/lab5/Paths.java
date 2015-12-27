package se.kth.jazulay.lab5;

import java.util.Arrays;
import se.kth.id1020.Graph;
import se.kth.id1020.DataSource;
import se.kth.id1020.Vertex;

public class Paths {
    public static void main(String[] args) {
        Graph g = DataSource.load();
        System.out.println("How many subgraphs? " + new SubgraphCounter(g).count());

        Vertex[] pathResult = new ShortestPath(g).shortest("Renyn", "Parses");
        System.out.println("Shortest path contains " + (pathResult.length - 1) + " jumps");
        System.out.println(Arrays.toString(
            pathResult
        ));
    }
}
