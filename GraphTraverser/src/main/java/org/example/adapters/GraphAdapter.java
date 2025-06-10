package org.example.adapters;

import java.util.Collection;

public interface GraphAdapter {
    Collection<Integer> getNeighbors(Integer vertex);
    void addVertex(Integer vertex);
    void addEdge(String edgeId, Integer source, Integer target);
}
