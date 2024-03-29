import java.util.HashSet;
import java.util.Iterator;
import java.util.PriorityQueue;

public class PriorityQueueFrontier extends Frontier {

    private PriorityQueue<Node> queue = new PriorityQueue<>(new Node.NodeComparator());
    protected HashSet<Node> uniqueNodes = new HashSet<>();

    public Iterator<Node> iterator() {
        return queue.iterator();
    }

    @Override
    public HashSet<Node> toSet() {
        return uniqueNodes;
    }

    public void setQueue(PriorityQueue<Node> queue) {
        this.queue = queue;
    }

    public void setUniqueNodes(HashSet<Node> uniqueNodes) {
        this.uniqueNodes = uniqueNodes;
    }

    public void print() {
        Iterator<Node> it = iterator();
        System.out.print("[");
        while (it.hasNext()) {
            Node next = it.next();
            System.out.print(next + ":" + next.getFCost());
            if (it.hasNext()) {
                System.out.print(",");
            }
        }
        System.out.println("]");
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
