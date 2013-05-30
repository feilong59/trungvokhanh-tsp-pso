package graph;

public class TSP {
    private int n;
    private int[] nodes;
    private double cost;
    
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
    
    public double getCost() {
        return cost;
    }
    
    public void setCost(double cost) {
        this.cost = cost;
    }

    public int[] getNodes() {
        return nodes;
    }

    public void setNodes(int[] nodes) {
        for (int i = 1; i<= n; i++)
            this.nodes[i] = nodes[i];
    }
    public int getNode(int i) {
        return nodes[i];
    }

    public void setNode(int i, int node) {
        nodes[i] = node;
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++)
            sb.append(nodes[i]).append(",");
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
    
    public void decode(String str) {
        String[] chunks = str.split(",");
        for (int i = 1; i <= n; i++) 
            nodes[i] = Integer.parseInt(chunks[i]);
    }

}
