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
    }
}
