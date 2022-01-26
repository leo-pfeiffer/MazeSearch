import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class StateTest {

    @Test
    public void testGetSuccessorStates() {
        Map map = Conf.valueOf("JCONF01").getMap();
        State topLeft = new State(new Coord(0, 0), map);
        State topMiddle = new State(new Coord(0, 1), map);
        State topRight = new State(new Coord(0, 2), map);

        State middleLeft = new State(new Coord(1, 0), map);
        State middleMiddle = new State(new Coord(1, 1), map);
        State middleRight = new State(new Coord(1, 2), map);

        State bottomLeft = new State(new Coord(2, 0), map);
        State bottomMiddle = new State(new Coord(2, 1), map);
        State bottomRight = new State(new Coord(2, 2), map);

        State[] topLeftSuccessors = topLeft.getSuccessorStates();
        assertEquals(2, topLeftSuccessors.length);
        assertEquals(topMiddle, topLeftSuccessors[0]);
        assertEquals(middleLeft, topLeftSuccessors[1]);

        State[] topRightSuccessors = topRight.getSuccessorStates();
        assertEquals(2, topRightSuccessors.length);
        assertEquals(middleRight, topRightSuccessors[0]);
        assertEquals(topMiddle, topRightSuccessors[1]);

        State[] bottomLeftSuccessors = bottomLeft.getSuccessorStates();
        assertEquals(1, bottomLeftSuccessors.length);
        assertEquals(bottomMiddle, bottomLeftSuccessors[0]);

        State[] bottomRightSuccessors = bottomRight.getSuccessorStates();
        assertEquals(1, bottomLeftSuccessors.length);
        assertEquals(bottomMiddle, bottomLeftSuccessors[0]);

        State[] middleMiddleSuccessors = middleMiddle.getSuccessorStates();
        assertEquals(3, middleMiddleSuccessors.length);
        assertEquals(middleRight, middleMiddleSuccessors[0]);
        assertEquals(bottomMiddle, middleMiddleSuccessors[1]);
        assertEquals(middleLeft, middleMiddleSuccessors[2]);
    }
}
