package se.kth.jazulay.lab5;

import se.kth.id1020.Edge;
import se.kth.id1020.Graph;

public class SubgraphCounter {

    Graph graph;

    public SubgraphCounter (Graph g) {
        this.graph = g;
    }

    public int count () {
        boolean[] visited = DFS(0);
        int count = 1;
        int unvisitedNode = indexOfFirstUnvisited(visited);
        while (unvisitedNode != -1) {
            DFS(unvisitedNode, visited);
            count += 1;
            unvisitedNode = indexOfFirstUnvisited(visited);
        }
        return count;
    }

    private int indexOfFirstUnvisited(boolean[] visits) {
        int i = 0;
        for (boolean visit : visits) {
            if (visit == false) {
                return i;
            }
            i += 1;
        }
        return -1;
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
