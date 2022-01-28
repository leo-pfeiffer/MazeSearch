import java.util.HashSet;
import java.util.PriorityQueue;

public class PriorityQueueFrontier extends Frontier {

    private final NodeComparator comparator = new NodeComparator();
    private final PriorityQueue<Node> queue = new PriorityQueue<>(comparator);
    protected final HashSet<Node> uniqueNodes = new HashSet<>();

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
