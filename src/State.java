import java.util.ArrayList;
import java.util.HashSet;

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

    public State[] getSuccessorStates() {
        ArrayList<State> successors = new ArrayList<>();
        HashSet<Integer> possibleMoves = coord.getPossibleMoves();

        int RIGHT = 1;
        int DOWN = 2;
        int LEFT = 3;
        int UP = 4;


        // todo move this somewhere more useful
        if (possibleMoves.contains(RIGHT)) {
            Coord rightCoord = new Coord(coord.getRow(), coord.getCol() + 1);
            addStateToSuccessors(successors, rightCoord);
        }

        if (possibleMoves.contains(DOWN)) {
            Coord downCoord = new Coord(coord.getRow() + 1, coord.getCol());
            addStateToSuccessors(successors, downCoord);
        }

        if (possibleMoves.contains(LEFT)) {
            Coord leftCoord = new Coord(coord.getRow(), coord.getCol() - 1);
            addStateToSuccessors(successors, leftCoord);
        }

        if (possibleMoves.contains(UP)) {
            Coord upCoord = new Coord(coord.getRow() - 1, coord.getCol());
            addStateToSuccessors(successors, upCoord);
        }

        State[] successorsArray = new State[successors.size()];
        successors.toArray(successorsArray);

        return successorsArray;
    }

    private void addStateToSuccessors(ArrayList<State> successors, Coord newStateCoord) {
        if (map.isOnMap(newStateCoord) && !map.isIsland(newStateCoord)) {
            successors.add(new State(newStateCoord, map));
        }
    }
}
