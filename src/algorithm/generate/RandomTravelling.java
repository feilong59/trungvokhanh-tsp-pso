package algorithm.generate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import graph.TSP;

public class RandomTravelling extends TSP {
    
    public RandomTravelling() {
        
    }

    public RandomTravelling(int n) {
        List<Integer> list = new ArrayList<Integer>();
        setN(n);
        for (int i = 1; i <= n; i++)
            list.add(i);
        Collections.shuffle(list);
        int index = 0;
        for (int node: list) {
            index++;
            setNodes(index, node);
        }
    }

}
