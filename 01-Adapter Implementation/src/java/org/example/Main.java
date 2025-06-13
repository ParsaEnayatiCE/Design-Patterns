package org.example;

import org.example.adapters.GraphAdapter;
import org.example.adapters.JGraphTAdapter; // تغییر از JUNGGraphAdapter به JGraphTAdapter
import org.example.graphTravelers.BfsGraphTraverser;
import org.example.graphTravelers.DfsGraphTraverser;
import org.example.graphTravelers.Traverser;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Create graph using adapter
        GraphAdapter graphAdapter = new JGraphTAdapter(); // تغییر کتابخانه فقط در این خط

        // Add vertices
        graphAdapter.addVertex(1);
        graphAdapter.addVertex(2);
        graphAdapter.addVertex(3);
        graphAdapter.addVertex(4);
        graphAdapter.addVertex(5);

        // Add edges
        graphAdapter.addEdge("E1", 1, 2);
        graphAdapter.addEdge("E2", 1, 3);
        graphAdapter.addEdge("E3", 2, 4);
        graphAdapter.addEdge("E4", 3, 5);
        graphAdapter.addEdge("E5", 4, 5);

        Traverser dfsGraphTraveler = new DfsGraphTraverser(graphAdapter);
        Traverser bfsGraphTraveler = new BfsGraphTraverser(graphAdapter);

        List<Integer> dfsPath = dfsGraphTraveler.traverse(1);
        List<Integer> bfsPath = bfsGraphTraveler.traverse(1);

        System.out.println("Graph-DFS From node 1 is : " + dfsPath);
        System.out.println("Graph-BFS From node 1 is : " + bfsPath);
    }
}
