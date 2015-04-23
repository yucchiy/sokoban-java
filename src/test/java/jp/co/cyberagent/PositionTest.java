package jp.co.cyberagent;

import org.junit.Test;

import static org.junit.Assert.*;

public class PositionTest {

    @Test
    public void testEquals() throws Exception {
        Position a = new Position(1, 2),
                 b = new Position(2, 3),
                 c = new Position(1, 2);

        assertEquals(a.equals(b), false);
        assertEquals(a.equals(c), true);
        assertEquals(c.equals(a), true);
    }

    @Test
    public void testHashCode() throws Exception {
        Position a = new Position(1, 2),
                 b = new Position(2, 3),
                 c = new Position(1, 2);

        assertEquals(a.hashCode() == b.hashCode(), false);
        assertEquals(a.hashCode() == c.hashCode(), true);
    }
}