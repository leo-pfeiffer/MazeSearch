import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class SearchTest {

    Map map = Conf.valueOf("JCONF01").getMap();

    State s1 = new State(new Coord(0, 3), map); // hCost = 3
    State s2 = new State(new Coord(3, 3), map); // hCost = 6
    State s3 = new State(new Coord(3, 0), map); // hCost = 5

    Node n1 = new Node(null, s1, 100);
    Node n2 = new Node(null, s2, 200);
    Node n3 = new Node(null, s3, 300);
    Node n3Better = new Node(null, s3, 200);

    /**
     * Helper class that adds some methods to the AStarSearch class to allow for testing.
     * */
    private static class TestAStarSearch extends AStarSearch {
        public TestAStarSearch(Map map, Coord start, Coord goal) {
            super(map, start, goal);
        }
        public void addToExplored(Node node) {
            explored.add(node);
        }
        public void addToFrontier(Node node) {
            frontier.insert(node);
        }
    }

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

    @Test
    public void testGoalTest() {
        Conf conf = Conf.valueOf("JCONF01");

        Search search = new DepthFirstSearch(conf.getMap(), conf.getS(), conf.getG());

        State notGoalState = new State(new Coord(0, conf.getG().getCol() + 1), conf.getMap());
        State goalState = new State(conf.getG(), conf.getMap());
        Node notGoal = new Node(null, notGoalState, 0);
        Node goal = new Node(null, goalState, 0);

        assertTrue(search.goalTest(goal));
        assertFalse(search.goalTest(notGoal));
    }

    @Test
    public void testSolutionPath() {
        Conf conf = Conf.valueOf("JCONF00");
        Search search = new BreadthFirstSearch(conf.getMap(), conf.getS(), conf.getG());
        search.runSearch();
        String expected = "(1,1)(1,2)(1,3)(1,4)(1,5)(2,5)(2,4)(3,4)";
        assertEquals(expected, search.solutionPath());
    }

    /**
     * Tests if insert all works with a PriorityQueueFrontier if the path cost is 0.
     * */
    @Test
    public void testInformedSearchInsertAll1() {
        Map map = Conf.valueOf("JCONF01").getMap();

        State s1 = new State(new Coord(0, 3), map); // hCost = 3
        State s2 = new State(new Coord(3, 3), map); // hCost = 6
        State s3 = new State(new Coord(3, 0), map); // hCost = 5

        Node n1 = new Node(null, s1, 0);
        Node n2 = new Node(null, s2, 0);
        Node n3 = new Node(null, s3, 0);

        Node[] nodeArray = {n1, n2, n3};

        AStarSearch search = new AStarSearch(map, new Coord(0, 0), new Coord(0, 0));
        search.insertAll(nodeArray);

        // check if the order is correct
        assertEquals(n1, search.getFrontier().remove());
        assertEquals(n3, search.getFrontier().remove());
        assertEquals(n2, search.getFrontier().remove());
    }

    /**
     * Tests if insert all works with a PriorityQueueFrontier if we have path costs.
     * */
    @Test
    public void testInformedSearchInsertAll2() {

        Node[] nodeArray = {n1, n2, n3};

        AStarSearch search = new AStarSearch(map, new Coord(0, 0), new Coord(0, 0));
        search.insertAll(nodeArray);

        // check if the order is correct
        assertEquals(n1, search.getFrontier().remove());
        assertEquals(n2, search.getFrontier().remove());
        assertEquals(n3, search.getFrontier().remove());
    }


    /** Tests AStar method addToSuccessors
     * test condition: replace node in frontier if the new node has a lower path cost
     * */
    @Test
    public void testAStarAddToSuccessorsREPLACE() {

        ArrayList<Node> newNodes = new ArrayList<>();
        TestAStarSearch search = new TestAStarSearch(map, new Coord(0, 0), new Coord(0, 0));

        search.addToFrontier(n3);
        search.addToExplored(n3);
        search.addToSuccessors(n3Better, newNodes);

        assertEquals(search.getFrontier().getNode(n3Better).getCost(), n3Better.getCost(), 0.0);
        assertNotEquals(search.getFrontier().getNode(n3Better).getCost(), n3.getCost(), 0.0);
    }

    /** Tests AStar method addToSuccessors
     * test condition: node must not be in explored yet
     * */
    @Test
    public void testAStarAddToSuccessorsEXPLORED() {

        ArrayList<Node> newNodes = new ArrayList<>();
        TestAStarSearch search = new TestAStarSearch(map, new Coord(0, 0), new Coord(0, 0));
        search.addToExplored(n1);
        search.addToSuccessors(n1, newNodes);
        search.addToSuccessors(n2, newNodes);
        search.addToSuccessors(n3, newNodes);

        assertFalse(newNodes.contains(n1));
        assertTrue(newNodes.contains(n2));
        assertTrue(newNodes.contains(n3));

    }

    /** Tests AStar method addToSuccessors
     * test condition: node must not be in frontier yet
     * */
    @Test
    public void testAStarAddToSuccessorsFRONTIER() {

        ArrayList<Node> newNodes = new ArrayList<>();
        TestAStarSearch search = new TestAStarSearch(map, new Coord(0, 0), new Coord(0, 0));
        search.addToFrontier(n1);
        search.addToSuccessors(n1, newNodes);
        search.addToSuccessors(n2, newNodes);
        search.addToSuccessors(n3, newNodes);

        assertFalse(newNodes.contains(n1));
        assertTrue(newNodes.contains(n2));
        assertTrue(newNodes.contains(n3));
    }

}
