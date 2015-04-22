package jp.co.cyberagent;

import org.junit.Test;

import static org.junit.Assert.*;

public class GuiActionTest {

    @Test
    public void testConvertFromChar() throws Exception {
        assertEquals(GuiAction.MOVE_UP,    GuiAction.convertFromChar('k'));
        assertEquals(GuiAction.MOVE_RIGHT, GuiAction.convertFromChar('l'));
        assertEquals(GuiAction.MOVE_DOWN,  GuiAction.convertFromChar('j'));
        assertEquals(GuiAction.MOVE_LEFT,  GuiAction.convertFromChar('h'));

        assertEquals(GuiAction.QUIT, GuiAction.convertFromChar('q'));
    }
}