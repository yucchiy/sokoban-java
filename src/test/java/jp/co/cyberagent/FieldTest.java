package jp.co.cyberagent;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class FieldTest {

    private Field field;

    private String[] fieldString = {
            "########",
            "##  ####",
            "##     #",
            "##o.. .#",
            "#  ##p##",
            "# oo  ##",
            "#     ##",
            "########"
    };

    @Before
    public void setup() throws Exception {
        field = new Field(new FieldOption(Arrays.asList(fieldString)));
    }

    @Test
    public void testGetIndex() throws Exception {
        assertEquals(field.getIndex(new Position(0, 0)), 0);
        assertEquals(field.getIndex(new Position(5, 0)), 5);
        assertEquals(field.getIndex(new Position(0, 1)), 6);
    }

    @Test
    public void testGetCellViaPosition() throws Exception {
        assertEquals(field.getCellViaPosition(new Position(0, 0)), new FieldCell('#'));
        assertEquals(field.getCellViaPosition(new Position(1, 1)), new FieldCell('.'));
        assertEquals(field.getCellViaPosition(new Position(1, 2)), new FieldCell(' '));
        assertEquals(field.getCellViaPosition(new Position(3, 3)), new FieldCell('o'));
        assertEquals(field.getCellViaPosition(new Position(4, 3)), new FieldCell('p'));

        // assertNull(field.getCellViaPosition(new Position(-1, 0)));
        // assertNull(field.getCellViaPosition(new Position(6, 5)));
    }

    @Test
    public void testGetFieldLine() throws Exception {
        assertEquals(field.getFieldLine(0).equals("######"), true);
        assertEquals(field.getFieldLine(1).equals("#.   #"), true);
        assertEquals(field.getFieldLine(3).equals("#  op#"), true);
    }

    @Test
    public void testGetWidthAndGetHeight() throws Exception {
        assertEquals(field.getHeight(), 5);
        assertEquals(field.getWidth(),  6);
    }
}