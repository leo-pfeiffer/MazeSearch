import java.util.ArrayList;

public class State {

    private final Coord coord;
    private final Map map;


    public State(Coord coord, Map map) {
        this.coord = coord;
        this.map = map;
    }

    @Override
    public String toString() {
        return "State" + coord.toString();
    }

    /**
     * Two State objects are equal if their coordinates are equal
     * and their map is equal.
     * */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        State state = (State) o;

        if (!coord.equals(state.coord)) return false;
        return map.equals(state.map);
    }

    @Override
    public int hashCode() {
        int result = coord.hashCode();
        result = 31 * result + map.hashCode();
        return result;
    }

    public Map getMap() {
        return map;
    }

    public Coord getCoord() {
        return coord;
    }

    /**
     * Get a list of all the possible successors of this state.
     * @return A list of all the possible successors of this state.
     * */
    public State[] getSuccessorStates() {
        ArrayList<State> successors = new ArrayList<>();

        // Add applicable adjacent states to the list of successors
        Coord[] adjacentCoords = coord.getAdjacentCoords();
        for (Coord coord : adjacentCoords) {
            addStateToSuccessors(successors, coord);
        }

        // Convert to array
        State[] successorsArray = new State[successors.size()];
        successors.toArray(successorsArray);

        return successorsArray;
    }

    /**
     * Add a coordinate to the list of successors if it is a valid move, i.e. on the map and not an island.
     * @param successors The list of successors to add to
     * @param newStateCoord The coordinate to add
     * */
    private void addStateToSuccessors(ArrayList<State> successors, Coord newStateCoord) {
        if (map.isOnMap(newStateCoord) && !map.isIsland(newStateCoord)) {
            successors.add(new State(newStateCoord, map));
        }
    }
}
