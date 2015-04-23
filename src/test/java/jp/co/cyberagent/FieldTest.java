package jp.co.cyberagent;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class FieldTest {

    @Test
    public void testGetCellViaPosition() throws Exception {
        String[] fieldString = {
                "######",
                "#.   #",
                "#    #",
                "#  op#",
                "######"
        };

        Field field = new Field(new FieldOption(Arrays.asList(fieldString)));

        assertEquals(field.getCellViaPosition(new Position(0, 0)), new FieldCell('#'));
        assertEquals(field.getCellViaPosition(new Position(1, 1)), new FieldCell('.'));
        assertEquals(field.getCellViaPosition(new Position(1, 2)), new FieldCell(' '));
        assertEquals(field.getCellViaPosition(new Position(3, 3)), new FieldCell('o'));
        assertEquals(field.getCellViaPosition(new Position(4, 3)), new FieldCell('p'));

        assertNull(field.getCellViaPosition(new Position(-1, 0)));
        assertNull(field.getCellViaPosition(new Position(6, 5)));
    }

    @Test
    public void testGetFieldLine() throws Exception {
        String[] fieldString = {
                "######",
                "#.   #",
                "#    #",
                "#  op#",
                "######"
        };

        Field field = new Field(new FieldOption(Arrays.asList(fieldString)));

        assertEquals(field.getFieldLine(0).equals("######"), true);
        assertEquals(field.getFieldLine(1).equals("#.   #"), true);
        assertEquals(field.getFieldLine(3).equals("#  op#"), true);

    }
}