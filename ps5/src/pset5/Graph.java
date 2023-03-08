package pset5;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Graph {
    private int numNodes; // number of nodes in the graph
    private boolean[][] edges;
    // edges[i][j] is true if and only if there is an edge from node i to node j
    // class invariant: fields "edges" is non-null;
    // "edges" is a square matrix;
    // numNodes is number of rows in "edges"
    public Graph(int size) {
        numNodes = size;
        // your code goes here
        // ...
        edges = new boolean[numNodes][numNodes];
        for (int i = 0; i < numNodes; i++){
            for(int j = 0; j < numNodes; j++){
                edges[i][j] = false;
            }
        }
    }

    public String toString() {
        return "numNodes: " + numNodes + "\n" + "edges: " + Arrays.deepToString(edges);
    }

    public boolean equals(Object o) {
        if (o.getClass() != Graph.class) return false;
        return toString().equals(o.toString());
    }

    public void addEdge(int from, int to) {
        // postcondition: adds a directed edge "from" -> "to" to this graph
        // your code goes here
        //...
        if(from < numNodes && to < numNodes) {
            edges[from][to] = true;
        }
    }

    public boolean reachable(Set<Integer> sources, Set<Integer> targets) {
        if (sources == null || targets == null) throw new IllegalArgumentException();
        // postcondition: returns true if 
        // (1) "sources" does not contain an illegal node,
        // (2) "targets" does not contain an illegal node, and
        // (3) for each node "m" in set "targets", there is some
        // node "n" in set "sources" such that there is a directed
        // path that starts at "n" and ends at "m" in "this"; and
        // false otherwise
        // your code goes here
        //...
        if (hasValidNodes(sources) && hasValidNodes(targets) && hasDirectedPaths(sources, targets)){
            return true;
        }
        return false;
    }

    private boolean hasValidNodes(Set<Integer> set) {
        for (Integer node : set){
            if (node >= numNodes || node == null){
                return false;
            }
        }
        return true;
    }

    private boolean hasDirectedPaths(Set<Integer> sources, Set<Integer> targets){
        for(int source : sources){
            for(int target : targets){
                if(hasDirectedPath(source, target)){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hasDirectedPath(int source, int target){
        if (source == target){
            return true;
        }

        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(source);

        while (!queue.isEmpty()) {
            int popped = queue.poll();
            if (popped == target) {
                return true; 
            }

            for (int i = 0; i < numNodes; i++) {
                if (edges[popped][i]){
                    queue.add(i);
                }
            }
        }

        return false;
    }
}