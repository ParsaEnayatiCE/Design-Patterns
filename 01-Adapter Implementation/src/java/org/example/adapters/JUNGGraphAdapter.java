package org.example.adapters;

import edu.uci.ics.jung.graph.SparseMultigraph;
import java.util.Collection;

public class JUNGGraphAdapter implements GraphAdapter {
    private final SparseMultigraph<Integer, String> graph;
    
    public JUNGGraphAdapter() {
        this.graph = new SparseMultigraph<>();
    }
    
    @Override
    public Collection<Integer> getNeighbors(Integer vertex) {
        return graph.getNeighbors(vertex);
    }
    
    @Override
    public void addVertex(Integer vertex) {
        graph.addVertex(vertex);
    }
    
    @Override
    public void addEdge(String edgeId, Integer source, Integer target) {
        graph.addEdge(edgeId, source, target);
    }
}
