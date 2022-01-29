import java.util.HashSet;
import java.util.Iterator;

public abstract class Frontier {
    public abstract void insert(Node node);
    public abstract Node remove();
    public abstract boolean isEmpty();
    public abstract boolean contains(Node node);
    public abstract int size();
    public abstract String toString();
    public abstract Iterator<Node> iterator();
    public abstract HashSet<Node> toSet();

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

    public Node getNode(Node node) {
        Iterator<Node> it = iterator();
        while (it.hasNext()) {
            Node n = it.next();
            if (n.equals(node)) {
                return n;
            }
        }
        return null;
    }
}
