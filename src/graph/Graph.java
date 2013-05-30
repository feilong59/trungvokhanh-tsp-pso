package graph;

public class Graph {
    private int n;
    private Node[] nodes;
    
    public Graph(Graph another) {
        setN(n);
        for (int i = 1; i <= n; i++)
            nodes[i] = another.nodes[i];
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
        nodes = new Node[n+1];
    }

    public Node[] getNodes() {
        return nodes;
    }

    public void setNodes(Node[] nodes) {
        this.nodes = nodes;
    }
    
    public void setNodes(int i, double x, double y) {
        nodes[i] = new Node(x, y);
    }
    
    
}
