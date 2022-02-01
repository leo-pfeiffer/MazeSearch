public abstract class InformedSearch extends GeneralSearch {
    public InformedSearch(Map map, Coord start, Coord goal) {
        super(map, start, goal);
    }

    /**
     * Overrides the method in GeneralSearch since we also need to calculate the hCost and fCost.
     * */
    @Override
    public void insertAll(Node[] nodes) {
        for (Node node : nodes) {
            calculateCost(node);
            frontier.insert(node);
        }
    }

    protected abstract void calculateCost(Node node);
}
