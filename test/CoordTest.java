import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.*;


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
    public void getPossibleMoves() {
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
}
