import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.*;


@SuppressWarnings("ConstantConditions")
public class CoordTest {
    @Test
    public void testCreation() {
        Coord c = new Coord(1, 2);
        assertEquals(1, c.getRow());
        assertEquals(2, c.getCol());
    }

    @Test
    public void testGetDir() {
        assertEquals(0, (new Coord(0, 0)).getDir());
        assertEquals(1, (new Coord(0, 1)).getDir());
        assertEquals(1, (new Coord(1, 0)).getDir());
        assertEquals(0, (new Coord(1, 1)).getDir());
        assertEquals(0, (new Coord(5, 3)).getDir());
        assertEquals(1, (new Coord(5, 4)).getDir());
    }

    @Test
    public void testGetPossibleMoves() {
        Coord c = new Coord(0, 0);
        HashSet<Integer> moves = c.getPossibleMoves();
        assertEquals(3, moves.size());
        assertTrue(moves.contains(1));
        assertTrue(moves.contains(2));
        assertTrue(moves.contains(3));

        c = new Coord(2, 0);
        moves = c.getPossibleMoves();
        assertEquals(3, moves.size());
        assertTrue(moves.contains(1));
        assertTrue(moves.contains(2));
        assertTrue(moves.contains(3));

        c = new Coord(1, 0);
        moves = c.getPossibleMoves();
        assertEquals(3, moves.size());
        assertTrue(moves.contains(1));
        assertTrue(moves.contains(3));
        assertTrue(moves.contains(4));

        c = new Coord(0, 3);
        moves = c.getPossibleMoves();
        assertEquals(3, moves.size());
        assertTrue(moves.contains(1));
        assertTrue(moves.contains(3));
        assertTrue(moves.contains(4));
    }

    @Test
    public void testGetAdjacentCoords() {
        int row = 0;
        int col = 0;

        Coord RIGHT = new Coord(row, col + 1);
        Coord DOWN = new Coord(row + 1, col);
        Coord LEFT = new Coord(row, col - 1);
        Coord UP;

        Coord c = new Coord(row, col);
        Coord[] moves = c.getAdjacentCoords();

        assertEquals(3, moves.length);
        assertEquals(moves[0], RIGHT);
        assertEquals(moves[1], DOWN);
        assertEquals(moves[2], LEFT);

        row = 0;
        col = 1;

        RIGHT = new Coord(row, col + 1);
        LEFT = new Coord(row, col - 1);
        UP = new Coord(row - 1, col);

        c = new Coord(row, col);
        moves = c.getAdjacentCoords();

        assertEquals(3, moves.length);
        assertEquals(moves[0], RIGHT);
        assertEquals(moves[1], LEFT);
        assertEquals(moves[2], UP);

        row = 1;
        col = 1;

        RIGHT = new Coord(row, col + 1);
        DOWN = new Coord(row + 1, col);
        LEFT = new Coord(row, col - 1);

        c = new Coord(row, col);
        moves = c.getAdjacentCoords();

        assertEquals(3, moves.length);
        assertEquals(moves[0], RIGHT);
        assertEquals(moves[1], DOWN);
        assertEquals(moves[2], LEFT);

        row = 0;
        col = 3;

        RIGHT = new Coord(row, col + 1);
        UP = new Coord(row - 1, col);
        LEFT = new Coord(row, col - 1);

        c = new Coord(row, col);
        moves = c.getAdjacentCoords();

        assertEquals(3, moves.length);
        assertEquals(moves[0], RIGHT);
        assertEquals(moves[1], LEFT);
        assertEquals(moves[2], UP);
    }

    @Test
    public void testGetManhattanDistance() {
        // todo
    }
}
