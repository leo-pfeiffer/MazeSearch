import java.util.HashSet;

/**
 * Set of explored nodes (or rather, their states).
 * Wraps a hash set and allows checking if a node's state is already in the set.
 * */
public class ExploredSet {

    private final HashSet<State> exploredStates = new HashSet<>();

    public boolean contains(Node node) {
        State state = node.getState();
        return exploredStates.contains(state);
    }

    public boolean contains(State state) {
        return exploredStates.contains(state);
    }

    public boolean add(Node node) {
        State state = node.getState();
        return exploredStates.add(state);
    }

    public boolean add(State state) {
        return exploredStates.add(state);
    }

    public boolean isEmpty() {
        return exploredStates.isEmpty();
    }

    public int size() {
        return exploredStates.size();
    }
}
