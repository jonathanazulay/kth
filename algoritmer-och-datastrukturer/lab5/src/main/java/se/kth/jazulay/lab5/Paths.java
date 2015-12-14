package se.kth.jazulay.lab5;

import se.kth.id1020.Graph;
import se.kth.id1020.DataSource;

public class Paths {
    public static void main(String[] args) {
        Graph g = DataSource.load();
        System.out.println(new SubgraphCounter(g).count());
    }
}
