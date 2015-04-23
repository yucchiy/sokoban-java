package jp.co.cyberagent;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class FieldOptionTest {

    @Test
    public void testGetHeightAndWidth() throws Exception {
        String[] fieldString = {
                "######",
                "#.   #",
                "#    #",
                "#  op#",
                "######"
        };

        FieldOption option = new FieldOption(Arrays.asList(fieldString));

        assertEquals(5, option.getHeight());
        assertEquals(6, option.getWidth());
    }

}