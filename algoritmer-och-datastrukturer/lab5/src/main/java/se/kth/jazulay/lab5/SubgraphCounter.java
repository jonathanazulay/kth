package se.kth.jazulay.lab5;

import se.kth.id1020.Edge;
import se.kth.id1020.Graph;

public class SubgraphCounter {

    Graph graph;

    public SubgraphCounter (Graph g) {
        this.graph = g;
    }

    public int count () {
        boolean[] visited = new boolean[this.graph.numberOfVertices()];
        int count = 0;

        for (int i = 0; i < visited.length; i += 1) {
            if (!visited[i]) {
                DFS(i, visited);
                count += 1;
            }
        }
        return count;
    }

    private boolean[] DFS(int startVertex) {
        return DFS(startVertex, new boolean[this.graph.numberOfVertices()]);
    }

    private boolean[] DFS(int startVertex, boolean[] visited) {
        visited[startVertex] = true;
        for (Edge edge : this.graph.adj(startVertex)) {
            if (visited[edge.to] == false) {
                DFS(edge.to, visited);
            }
        }
        return visited;
    }
}
