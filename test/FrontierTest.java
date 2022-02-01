import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class FrontierTest {

    Map map = Conf.valueOf("JCONF01").getMap();

    State s1 = new State(new Coord(0, 0), map);
    State s2 = new State(new Coord(0, 1), map);
    State s3 = new State(new Coord(0, 2), map);
    State s4 = new State(new Coord(1, 0), map);

    Node n1 = new Node(null, s1, 0);
    Node n2 = new Node(null, s2, 0);
    Node n3 = new Node(null, s3, 0);
    Node n4 = new Node(null, s4, 0);

    PriorityQueueFrontier PQF = new PriorityQueueFrontier();
    StackFrontier SF = new StackFrontier();
    QueueFrontier QF = new QueueFrontier();

    @Before
    public void setUp() {
        n1.setFCost(1);
        n2.setFCost(2);
        n3.setFCost(3);
        n4.setFCost(4);
    }

    @Test
    public void testQueueFrontier() {
        State topLeft = new State(new Coord(0, 0), map);
        State topMiddle = new State(new Coord(0, 1), map);
        State topRight = new State(new Coord(0, 2), map);

        assertTrue(QF.isEmpty());

        QF.insert(new Node(null, topLeft, 0));

        assertFalse(QF.isEmpty());

        QF.insert(new Node(null, topMiddle, 0));
        QF.insert(new Node(null, topRight, 0));

        assertEquals(new Node(null, topLeft, 0), QF.remove());
        assertEquals(new Node(null, topMiddle, 0), QF.remove());
        assertEquals(new Node(null, topRight, 0), QF.remove());

        assertTrue(QF.isEmpty());

        assertEquals(0, QF.size());
        QF.insert(new Node(null, topLeft, 0));
        assertEquals(1, QF.size());

        // should not be able to insert same node twice
        QF.insert(new Node(null, topLeft, 0));
        assertEquals(1, QF.size());
    }

    @Test
    public void testStackFrontier() {

        State topLeft = new State(new Coord(0, 0), map);
        State topMiddle = new State(new Coord(0, 1), map);
        State topRight = new State(new Coord(0, 2), map);

        assertTrue(SF.isEmpty());

        SF.insert(new Node(null, topLeft, 0));

        assertFalse(SF.isEmpty());

        SF.insert(new Node(null, topMiddle, 0));
        SF.insert(new Node(null, topRight, 0));

        assertEquals(new Node(null, topRight, 0), SF.remove());
        assertEquals(new Node(null, topMiddle, 0), SF.remove());
        assertEquals(new Node(null, topLeft, 0), SF.remove());

        assertTrue(SF.isEmpty());

        assertEquals(0, SF.size());
        SF.insert(new Node(null, topLeft, 0));
        assertEquals(1, SF.size());

        // should not be able to insert same node twice
        SF.insert(new Node(null, topLeft, 0));

    }

    @Test
    public void testPriorityQueueFrontier() {

        assertTrue(PQF.isEmpty());

        PQF.insert(n2);
        PQF.insert(n1);

        assertEquals(2, PQF.size());

        Node removed = PQF.remove();
        assertEquals(n1, removed);

        PQF.insert(n1);
        PQF.insert(n4);
        PQF.insert(n3);

        assertEquals(n1, PQF.remove());
        assertEquals(n2, PQF.remove());
        assertEquals(n3, PQF.remove());
        assertEquals(n4, PQF.remove());

        assertTrue(PQF.isEmpty());
    }

    @Test
    public void testGetNode() {

        PQF.insert(n1);
        SF.insert(n1);
        QF.insert(n1);
        PQF.insert(n2);
        SF.insert(n2);
        QF.insert(n2);

        assertEquals(n1, PQF.getNode(n1));
        assertEquals(n1, SF.getNode(n1));
        assertEquals(n1, QF.getNode(n1));
        assertEquals(n2, SF.getNode(n2));
        assertEquals(n2, QF.getNode(n2));
        assertEquals(n2, PQF.getNode(n2));
    }

    @Test
    public void testContains() {
        PQF.insert(n1);
        SF.insert(n1);
        QF.insert(n1);

        assertTrue(PQF.contains(n1));
        assertTrue(QF.contains(n1));
        assertTrue(SF.contains(n1));

        assertFalse(PQF.contains(n2));
        assertFalse(SF.contains(n2));
        assertFalse(QF.contains(n2));
    }
}
