package graph;
public class Edge {
    private Node a;
    private Node b;
    
    public Edge() {
        
    }

    public Edge(Node a, Node b) {
        setA(a);
        setB(b);
    }

    public Edge(Edge another) {
        setA(another.getA());
        setA(another.getB());
    }

    public Node getB() {
        return b;
    }

    public void setB(Node b) {
        this.b = b;
    }

    public Node getA() {
        return a;
    }

    public void setA(Node a) {
        this.a = a;
    }
}
