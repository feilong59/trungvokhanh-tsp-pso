package graph;

public class Graph {
    private int n;
    private Node[] nodes;
    
    public Graph() {
        
    }

    public Graph(int n) {
        setN(n);
    }
    
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

    public Node getNode(int i) {
        return nodes[i];
    }

    public void setNodes(Node[] nodes) {
        this.nodes = nodes;
    }
    
    public void setNodes(int i, double x, double y) {
        nodes[i] = new Node(x, y);
    }
    
    public double calculateCost(int[] tour) {
        double cost = 0;
        for (int i = 1; i < n; i++)
            cost += Tool.distance(nodes[tour[i]], nodes[tour[i + 1]]);
        cost += Tool.distance(nodes[tour[n]], nodes[tour[1]]);
        return cost;
    }
    
    public String code() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++)
            sb.append(nodes[i].toString()).append(" ");
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
    
    public void decode(String str) {
        String[] chunks = str.split(" ");
        for (int i = 0; i < n; i++) {
            String[] vertex = chunks[i].split(",");
            double x = Double.parseDouble(vertex[0]);
            double y = Double.parseDouble(vertex[1]);
            setNodes(i + 1, x, y);
        }
    }
}
