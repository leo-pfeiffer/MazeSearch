public class SearchFactory {

    public Search makeSearch(String searchAlgorithm) throws InvalidSearch {
        switch (searchAlgorithm) {
            case "BFS": // run BFS
                return new BreadthFirstSearch();
            case "DFS": // run DFS
                return new DepthFirstSearch();
            case "BestF": // run BestF
                return new BestFirstSearch();
            case "AStar": // run AStar
                return new AStarSearch();
            default:
                throw new InvalidSearch(searchAlgorithm);
        }
    }
}
