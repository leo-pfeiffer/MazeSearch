import org.junit.Test;
import static org.junit.Assert.*;

public class FrontierTest {
    @Test
    public void testQueueFrontier() {
        QueueFrontier frontier = new QueueFrontier();

        Map map = Conf.valueOf("JCONF01").getMap();
        State topLeft = new State(new Coord(0, 0), map);
        State topMiddle = new State(new Coord(0, 1), map);
        State topRight = new State(new Coord(0, 2), map);

        assertTrue(frontier.isEmpty());

        frontier.insert(new Node(null, topLeft, 0));

        assertFalse(frontier.isEmpty());

        frontier.insert(new Node(null, topMiddle, 0));
        frontier.insert(new Node(null, topRight, 0));

        assertEquals(new Node(null, topLeft, 0), frontier.remove());
        assertEquals(new Node(null, topMiddle, 0), frontier.remove());
        assertEquals(new Node(null, topRight, 0), frontier.remove());

        assertTrue(frontier.isEmpty());

        assertEquals(0, frontier.size());
        frontier.insert(new Node(null, topLeft, 0));
        assertEquals(1, frontier.size());

        // should not be able to insert same node twice
        frontier.insert(new Node(null, topLeft, 0));
        assertEquals(1, frontier.size());
    }

    @Test
    public void testStackFrontier() {
        StackFrontier frontier = new StackFrontier();

        Map map = Conf.valueOf("JCONF01").getMap();
        State topLeft = new State(new Coord(0, 0), map);
        State topMiddle = new State(new Coord(0, 1), map);
        State topRight = new State(new Coord(0, 2), map);

        assertTrue(frontier.isEmpty());

        frontier.insert(new Node(null, topLeft, 0));

        assertFalse(frontier.isEmpty());

        frontier.insert(new Node(null, topMiddle, 0));
        frontier.insert(new Node(null, topRight, 0));

        assertEquals(new Node(null, topRight, 0), frontier.remove());
        assertEquals(new Node(null, topMiddle, 0), frontier.remove());
        assertEquals(new Node(null, topLeft, 0), frontier.remove());

        assertTrue(frontier.isEmpty());

        assertEquals(0, frontier.size());
        frontier.insert(new Node(null, topLeft, 0));
        assertEquals(1, frontier.size());

        // should not be able to insert same node twice
        frontier.insert(new Node(null, topLeft, 0));

    }

    @Test
    public void testPriorityQueueFrontier() {

        PriorityQueueFrontier frontier = new PriorityQueueFrontier();

        Map map = Conf.valueOf("JCONF01").getMap();

        State s1 = new State(new Coord(0, 0), map);
        State s2 = new State(new Coord(0, 1), map);
        State s3 = new State(new Coord(0, 2), map);
        State s4 = new State(new Coord(1, 0), map);

        Node n1 = new Node(null, s1, 0);
        n1.setFCost(1);
        Node n2 = new Node(null, s2, 0);
        n2.setFCost(2);
        Node n3 = new Node(null, s3, 0);
        n3.setFCost(3);
        Node n4 = new Node(null, s4, 0);
        n4.setFCost(4);

        assertTrue(frontier.isEmpty());

        frontier.insert(n2);
        frontier.insert(n1);

        assertEquals(2, frontier.size());

        Node removed = frontier.remove();
        assertEquals(n1, removed);

        frontier.insert(n1);
        frontier.insert(n4);
        frontier.insert(n3);

        assertEquals(n1, frontier.remove());
        assertEquals(n2, frontier.remove());
        assertEquals(n3, frontier.remove());
        assertEquals(n4, frontier.remove());

        assertTrue(frontier.isEmpty());
    }
}
