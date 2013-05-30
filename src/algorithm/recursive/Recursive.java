package algorithm.recursive;

import graph.Graph;
import graph.TSP;

import java.util.ArrayList;
import java.util.List;

import algorithm.generate.RandomTravelling;

public class Recursive {
    int n;
    Graph g;
    TSP bestTour;
    int[] tour;
    boolean[] visited;
    List<Integer>[] adjacencyList;
    
    public Recursive(Graph graph) {
        n = graph.getN();
        g = new Graph(graph);
        tour = new int[n + 1];
        visited = new boolean[n + 1];
        bestTour = new RandomTravelling(n);
    }
    
    private void buildingAdjacencyList(TSP firstTour, TSP secondTour) {
        adjacencyList = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++)
            adjacencyList[i] = new ArrayList<Integer>();
        
    }

    private void attemp(int i) {
        if (i > n) {
            double cost = g.calculateCost(tour);
            if (cost < bestTour.getCost()) {
                bestTour.setCost(cost);
                bestTour.setNodes(tour);
            }
        } else
            for (int j : adjacencyList[i])
                if (!visited[j]) {
                    tour[i] = j;
                    visited[j] = true;
                    attemp(i + 1);
                    visited[j] = false;
                }
    }
    
    public void run() {
        
    }

}
