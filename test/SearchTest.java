import org.junit.Test;
import static org.junit.Assert.*;

public class SearchTest {

    public Boolean testBreadthFirst(Map map, Coord start, Coord goal, Boolean fail) {
        Search search = new BreadthFirstSearch(map, start, goal);
        search.runSearch();

        if (fail == null) return search.getSolution() != null;
        if (!fail) assertEquals(search.getSolution().getState().getCoord(), goal);
        else assertNull(search.getSolution());
        return null;
    }

    public Boolean testDepthFirst(Map map, Coord start, Coord goal, Boolean fail) {
        Search search = new DepthFirstSearch(map, start, goal);
        search.runSearch();

        if (fail == null) return search.getSolution() != null;
        if (!fail) assertEquals(search.getSolution().getState().getCoord(), goal);
        else assertNull(search.getSolution());
        return null;
    }

    public Boolean testBestFirst(Map map, Coord start, Coord goal, Boolean fail) {
        Search search = new BestFirstSearch(map, start, goal);
        search.runSearch();

        if (fail == null) return search.getSolution() != null;
        if (!fail) assertEquals(search.getSolution().getState().getCoord(), goal);
        else assertNull(search.getSolution());
        return null;
    }

    public Boolean testAStar(Map map, Coord start, Coord goal, Boolean fail) {
        Search search = new AStarSearch(map, start, goal);
        search.runSearch();

        if (fail == null) return search.getSolution() != null;
        if (!fail) assertEquals(search.getSolution().getState().getCoord(), goal);
        else assertNull(search.getSolution());
        return null;
    }

    public Boolean testBidirectional(Map map, Coord start, Coord goal, Boolean fail) {
        Search search = new BidirectionalSearch(map, start, goal);
        search.runSearch();

        if (fail == null) return search.getSolution() != null;
        if (!fail) assertEquals(search.getSolution().getState().getCoord(), goal);
        else assertNull(search.getSolution());
        return null;
    }

    /**
     * Tests all algorithms on the JCONF* configurations, comparing the expected results
     * with the actual results.
     * */
    @Test
    public void testAll() {
        String[] confs = {"JCONF01", "JCONF11", "JCONF01", "JCONF02", "JCONF03", "JCONF04", "JCONF05"};
        boolean[] fail = {false, true, false, false, false, false, true};

        for (int i = 0; i < confs.length; i++) {
            System.out.println("Testing " + confs[i]);
            Conf conf = Conf.valueOf(confs[i]);
            Map map = conf.getMap();
            Coord start = conf.getS();
            Coord goal = conf.getG();
            testBreadthFirst(map, start, goal, fail[i]);
            testDepthFirst(map, start, goal, fail[i]);
            testBestFirst(map, start, goal, fail[i]);
            testAStar(map, start, goal, fail[i]);
            testBidirectional(map, start, goal, fail[i]);
        }
    }

    /**
     * Test runs all algorithms on all configurations.
     * The test makes sure that the algorithm actually runs and doesn't crash.
     * To test the correctness of the algorithms, the results of all algorithms
     * are compared to each other and must be equal.
     * Use the testAll test to test the correctness of the algorithms in more detail with
     * expected results.
     * */
    @Test
    public void testRunAll() {

        for (Conf conf : Conf.values()) {

            System.out.println("Testing " + conf.name() + " ==========================================");

            Map map = conf.getMap();
            Coord start = conf.getS();
            Coord goal = conf.getG();

            Boolean a = testBreadthFirst(map, start, goal, null);
            Boolean b = testDepthFirst(map, start, goal, null);
            Boolean c = testBestFirst(map, start, goal, null);
            Boolean d = testAStar(map, start, goal, null);
            Boolean e = testBidirectional(map, start, goal, null);

            Boolean[] expected = {a, a, a, a, a};
            Boolean[] results = {a, b, c, d, e};

            // all should give same result
            assertArrayEquals(expected, results);
        }
    }

}
