package jp.co.cyberagent;

import org.junit.Test;

import static org.junit.Assert.*;

public class FieldCellTest {

    @Test
    public void testToString() throws Exception {
        assertEquals(".", new FieldCell('.').toString());
        assertEquals("p", new FieldCell('p').toString());
        assertEquals("#", new FieldCell('#').toString());
        assertEquals("o", new FieldCell('o').toString());
        assertEquals(" ", new FieldCell(' ').toString());
        assertEquals("#", new FieldCell('b').toString());
    }

    @Test
    public void testEquals() throws Exception {
        FieldCell a = new FieldCell('.');
        FieldCell b = new FieldCell('.');
        FieldCell c = new FieldCell('#');

        assertEquals(a, b);
        assertNotEquals(a, c);
    }
}