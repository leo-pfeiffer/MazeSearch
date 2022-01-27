public class Node {

    private final Node parent;
    private final State state;
    private int depth;
    private final double cost;

    public Node(Node parent, State state, double cost) {
        this.parent = parent;
        this.state = state;
        this.cost = cost;
        setDepthFromParent();
    }

    public Node getParent() {
        return parent;
    }

    public Node[] getTree() {
        Node[] tree = new Node[depth];
        Node current = this;
        while (current != null) {
            tree[current.depth - 1] = current;
            current = current.parent;
        }
        return tree;
    }

    public int getDepth() {
        return depth;
    }

    public double getCost() {
        return cost;
    }

    public State getState() {
        return state;
    }

    private void setDepthFromParent() {
        if (parent == null) {
            this.depth = 1;
        } else {
            this.depth = parent.getDepth() + 1;
        }
    }

    @Override
    public String toString() {
        return state.getCoord().toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;
        if (parent == null && node.parent != null) return false;
        if (parent != null && !parent.equals(node.parent)) return false;
        if (!state.equals(node.state)) return false;
        if (depth != node.depth) return false;
        return cost == node.cost;
    }

    @Override
    public int hashCode() {
        int result = parent != null ? parent.hashCode() : 0;
        result = 31 * result + state.hashCode();
        result = 31 * result + depth;
        result = (int) (31 * result + cost);
        return result;
    }

}
