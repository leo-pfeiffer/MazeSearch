import java.util.HashSet;
import java.util.Stack;

public class StackFrontier extends Frontier {

    private final Stack<Node> stack = new Stack<>();
    private final HashSet<Node> uniqueNodes = new HashSet<>();

    @Override
    public void insert(Node node) {
        // only add unique nodes
        if (!uniqueNodes.contains(node)) {
            uniqueNodes.add(node);
            stack.push(node);
        }
    }

    @Override
    public Node remove() {
        Node toRemove = stack.pop();
        uniqueNodes.remove(toRemove);
        return toRemove;
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public boolean contains(Node node) {
        return uniqueNodes.contains(node);
    }

    @Override
    public int size() {
        return stack.size();
    }

    @Override
    public String toString() {
        return stack.toString();
    }
}
