package graph;

public class Node {
    private double x;
    private double y;
    
    public Node() {

    }

    public Node(double x, double y) {
        setX(x);
        setY(y);
    }
    
    public Node(Node another) {
        setX(another.getX());
        setY(another.getY());
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
    
    public String toString() {
        return x + "," + y;
    }

}
