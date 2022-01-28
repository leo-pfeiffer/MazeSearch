import java.util.Iterator;

public abstract class Frontier {
    public abstract void insert(Node node);
    public abstract Node remove();
    public abstract boolean isEmpty();
    public abstract boolean contains(Node node);
    public abstract int size();
    public abstract String toString();
    public abstract Iterator<Node> iterator();
    public void print() {
        System.out.println(this);
    }
}
