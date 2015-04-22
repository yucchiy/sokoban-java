package jp.co.cyberagent;

public class Game implements AutoCloseable {

    private Gui gui;

    public Game(Gui gui) {
        this.gui = gui;
        init();
    }

    private void init() {
        gui.init();
        refreshScreen();
    }

    public void play() {
        boolean playing = true;
        do {
            GuiAction action = gui.getAction();
            if (action == GuiAction.QUIT) playing = false;

            processStep(action);
        } while (playing);
    }

    public void processStep(GuiAction action) {
        refreshScreen();
    }

    public void refreshScreen() {
        gui.refresh();
        gui.putString(0, 0, "This is a sokoban project");
    }

    @Override
    public void close() throws Exception {
        gui.close();
    }
}
