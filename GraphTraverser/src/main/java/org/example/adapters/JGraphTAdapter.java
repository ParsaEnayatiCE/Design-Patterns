package org.example.adapters;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultUndirectedGraph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class JGraphTAdapter implements GraphAdapter {
    private final Graph<Integer, DefaultEdge> graph;
    private final Map<String, DefaultEdge> edgeMap;
    
    public JGraphTAdapter() {
        this.graph = new DefaultUndirectedGraph<>(DefaultEdge.class);
        this.edgeMap = new HashMap<>();
    }
    
    @Override
    public Collection<Integer> getNeighbors(Integer vertex) {
        return new ArrayList<>(Graphs.neighborListOf(graph, vertex));
    }
    
    @Override
    public void addVertex(Integer vertex) {
        graph.addVertex(vertex);
    }
    
    @Override
    public void addEdge(String edgeId, Integer source, Integer target) {
        DefaultEdge edge = graph.addEdge(source, target);
        edgeMap.put(edgeId, edge);
    }
}
