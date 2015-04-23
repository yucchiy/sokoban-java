package jp.co.cyberagent;

import org.junit.Test;

import static org.junit.Assert.*;

public class FloorTypeTest {

    @Test
    public void testConvertToChar() throws Exception {

        FieldCell emptyCell = new FieldCell(
                FieldCell.FloorType.EMPTY, FieldCell.ObjectType.EMPTY);
        assertEquals(' ', emptyCell.convertToChar());

        FieldCell wallCell_1 = new FieldCell(
                FieldCell.FloorType.EMPTY, FieldCell.ObjectType.WALL);
        assertEquals('#', wallCell_1.convertToChar());

        FieldCell freightCell_1 = new FieldCell(
                FieldCell.FloorType.EMPTY, FieldCell.ObjectType.FREIGHT);
        assertEquals('o', freightCell_1.convertToChar());

        FieldCell freightCell_2 = new FieldCell(
                FieldCell.FloorType.GOAL, FieldCell.ObjectType.FREIGHT);
        assertEquals('o', freightCell_2.convertToChar());

        FieldCell playerCell = new FieldCell(
                FieldCell.FloorType.EMPTY, FieldCell.ObjectType.PLAYER);
        assertEquals('p', playerCell.convertToChar());

        FieldCell goalCell = new FieldCell(
                FieldCell.FloorType.GOAL, FieldCell.ObjectType.EMPTY);
        assertEquals('.', goalCell.convertToChar());

    }
}