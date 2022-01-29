import org.junit.Test;
import static org.junit.Assert.*;

public class SearchTest {

    Conf conf = Conf.valueOf("JCONF00");
    Map map = conf.getMap();
    Coord start = conf.getS();
    Coord goal = conf.getG();

    @Test
    public void testBreadthFirst() {
        Search search = new BreadthFirstSearch(map, start, goal);
        search.runSearch();
    }

    @Test
    public void testDepthFirst() {
        Search search = new DepthFirstSearch(map, start, goal);
        search.runSearch();
    }

    @Test
    public void testBestFirst() {
        Search search = new BestFirstSearch(map, start, goal);
        search.runSearch();
    }

    @Test
    public void testAStar() {
        Search search = new AStarSearch(map, start, goal);
        search.runSearch();
    }

    @Test
    public void testBidirectional() {
        Search search = new BidirectionalSearch(map, start, goal);
        search.runSearch();
    }




}
