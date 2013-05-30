package graph;

public class TSP {
    private int n;
    private int[] nodes;
    
    public TSP() {
        
    }

    public TSP(TSP another) {
        setN(another.getN());  
        for (int i = 1; i <= n; i++)
            nodes[i] = another.nodes[i];
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
        nodes = new int[n + 1];
    }

    public int[] getNodes() {
        return nodes;
    }

    public void setNodes(int[] nodes) {
        this.nodes = nodes;
    }

    public void setNodes(int i, int node) {
        nodes[i] = node;
    }

}
