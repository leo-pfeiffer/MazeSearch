import java.util.HashSet;
import java.util.LinkedList;

public class QueueFrontier extends Frontier {

    private final HashSet<Node> uniqueNodes = new HashSet<>();
    private final LinkedList<Node> queue = new LinkedList<>();

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
}
