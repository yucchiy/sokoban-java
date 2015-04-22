package jp.co.cyberagent;

import org.junit.Test;

import static org.junit.Assert.*;

public class GuiActionTest {

    @Test
    public void testConvertFromChar() throws Exception {
        assertEquals(GuiAction.MOVE_UP,    'k');
        assertEquals(GuiAction.MOVE_RIGHT, 'l');
        assertEquals(GuiAction.MOVE_DOWN,  'j');
        assertEquals(GuiAction.MOVE_LEFT,  'h');

        assertEquals(GuiAction.QUIT, 'q');
    }
}