/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.jazulay.lab5;

import se.kth.id1020.Edge;
import se.kth.id1020.Graph;

/**
 *
 * @author jonathanazulay
 */
public class SubgraphCounter {

    Graph graph;

    public SubgraphCounter (Graph g) {
        this.graph = g;
    }

    public int count () {
        boolean[] visited = DFS(graph, 0, null);
        int count = 1;
        int unvisitedNode = indexOfFirstUnvisited(visited);
        while (unvisitedNode != -1) {
            DFS(graph, unvisitedNode, visited);
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

    private boolean[] DFS(Graph g, int startVertex, boolean[] visited) {
        if (visited == null) {
            visited = new boolean[g.numberOfVertices()];
        }

        visited[startVertex] = true;
        for (Edge edge : g.adj(startVertex)) {
            if (visited[edge.to] == false) {
                DFS(g, edge.to, visited);
            }
        }
        return visited;
    }
}
