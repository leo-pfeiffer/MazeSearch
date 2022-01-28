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
        Iterator<Node> it = iterator();
        System.out.print("[");
        while (it.hasNext()) {
            System.out.print(it.next());
            if (it.hasNext()) {
                System.out.print(",");
            }
        }
        System.out.println("]");
    }
}
