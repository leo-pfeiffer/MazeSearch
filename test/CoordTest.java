import org.junit.Test;
import static org.junit.Assert.assertEquals;


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
}
