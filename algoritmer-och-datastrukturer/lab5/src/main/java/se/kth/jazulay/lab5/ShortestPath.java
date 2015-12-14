package se.kth.jazulay.lab5;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import se.kth.id1020.Edge;
import se.kth.id1020.Graph;
import se.kth.id1020.Vertex;

public class ShortestPath {

    Graph graph;

    public ShortestPath (Graph g) {
        this.graph = g;
    }
    
    public Vertex[] shortest (String from, String to) {
        // Do a BFS, start at the 'from' node. Store all paths into pathTo.
        Vertex vfrom = this.getVertex(from);
        Vertex vto = this.getVertex(to);

        boolean visited[] = new boolean[this.graph.numberOfVertices()];
        int pathTo[] = new int[this.graph.numberOfVertices()];

        Queue<Integer> queue = new LinkedList();
        queue.add(vfrom.id);

        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            visited[vertex] = true;

            for (Edge edge : this.graph.adj(vertex)) {
                if (!visited[edge.to]) {
                    queue.add(edge.to);
                    pathTo[edge.to] = vertex;
                }
            }

        }

        // Find the shortest path by following the path starting at to
        // and keep going until reach destination
        int pointer = vto.id;
        ArrayList<Integer> arr = new ArrayList();
        while (pointer != vfrom.id) {
            arr.add(pointer);
            pointer = pathTo[pointer];
        }
        arr.add(vfrom.id);

        // Reverse order
        Vertex[] result = new Vertex[arr.size()];
        for (int i = 0; i < arr.size(); i += 1) {
            result[result.length - i - 1] = this.getVertex(arr.get(i));
        }
        return result;
    }

    private Vertex getVertex (String label) {
        for (Vertex v : this.graph.vertices()) {
            if (v.label.equals(label)) {
                return v;
            }
        }
        return null;
    }

    private Vertex getVertex (int id) {
        for (Vertex v : this.graph.vertices()) {
            if (v.id == id) {
                return v;
            }
        }
        return null;
    }
}
