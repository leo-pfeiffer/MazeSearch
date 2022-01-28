import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

public class QueueFrontier extends Frontier {

    private final LinkedList<Node> queue = new LinkedList<>();
    private final HashSet<Node> uniqueNodes = new HashSet<>();

    @Override
    public Iterator<Node> iterator() {
        return queue.iterator();
    }

    @Override
    public void insert(Node node) {
        // only add unique nodes
        if (!uniqueNodes.contains(node)) {
            uniqueNodes.add(node);
            queue.add(node);
        }
    }

    @Override
    public Node remove() {
        Node toRemove = queue.remove();
        uniqueNodes.remove(toRemove);
        return toRemove;
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    @Override
    public boolean contains(Node node) {
        return uniqueNodes.contains(node);
    }

    @Override
    public int size() {
        return queue.size();
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}
