package algorithm.heuristic;

import java.util.Random;

import graph.Graph;
import graph.TSP;
import graph.Tool;

public class Heuristic {
    public static final int NUMBER_OF_ITERATIONS = 10000;
    Graph g;
    
    public Heuristic(Graph g_) {
        this.g = new Graph(g_); 
    }
    
    public TSP twoOpt(TSP source) {
        TSP target = new TSP(source);
        Random rand = new Random();
        for (int i = 0; i < NUMBER_OF_ITERATIONS; i++) {
            int u = 2 + rand.nextInt(g.getN() - 17);
            int v = u + rand.nextInt(g.getN() - 1 - u);
            double cost = source.getCost();
            int min = Math.min(u, v);
            int max = Math.max(u, v);
            cost -=   Tool.distance(g.getNode(source.getNode(min)), g.getNode(source.getNode(min + 1)))
                    + Tool.distance(g.getNode(source.getNode(max)), g.getNode(source.getNode(max + 1)))
                    - Tool.distance(g.getNode(source.getNode(min)), g.getNode(source.getNode(max)))
                    - Tool.distance(g.getNode(source.getNode(min + 1)), g.getNode(source.getNode(max + 1)));
            if (cost < source.getCost()) {
                min++;
                int[] array = new int[max - min + 2];
                for (int index = min; index <= max; index++)
                    array[index - min] = source.getNode(index);
                for (int index = min; index <= max; index++)
                    target.setNode(index, array[max - index]);
                target.setCost(cost);
                break;
            }
        }
        return target;
    }

    public TSP dynamicProgramming(TSP source) {
        TSP target = new TSP(source);
        return target;
    }    
    

}
