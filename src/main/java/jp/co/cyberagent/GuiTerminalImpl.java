package jp.co.cyberagent;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.terminal.Terminal;

import java.nio.charset.Charset;

public class GuiTerminalImpl implements Gui {

    protected Terminal terminal;

    GuiTerminalImpl() {
    }

    @Override
    public void init() {
        terminal = TerminalFacade.createTerminal(System.in, System.out, Charset.forName("UTF8"));
        terminal.enterPrivateMode();
    }

    @Override
    public void close() {
        terminal.exitPrivateMode();
    }

    @Override
    public GuiAction getAction() {
        Key key;
        do {
            key = terminal.readInput();
        } while (key == null);

        return GuiAction.convertFromChar(key.getCharacter());
    }

    @Override
    public void refresh() {
        terminal.clearScreen();
    }

    @Override
    public void putString(int x, int y, String str) {
        terminal.moveCursor(x, y);
        for (int i = 0; i < str.length(); i++) terminal.putCharacter(str.charAt(i));
    }

}
