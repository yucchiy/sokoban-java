package jp.co.cyberagent;

public class Game implements AutoCloseable {

    private Gui gui;
    private GameState state;

    public Game(Gui gui, GameState state) {
        this.gui = gui;
        this.state = state;
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
            doStep(action);
        } while (playing);
    }

    public void doStep(GuiAction action) {
        int dx = 0, dy = 0;
        switch (action) {
            case MOVE_UP:
                dy = -1;
                break;
            case MOVE_RIGHT:
                dx = 1;
                break;
            case MOVE_DOWN:
                dy = 1;
                break;
            case MOVE_LEFT:
                dx = -1;
                break;
            default:
                break;
        }

        if (move(state.field.player, dx, dy)) refreshScreen();
    }

    public boolean move(Position from, int dx, int dy) {
        Position to = new Position(from.x + dx, from.y + dy);

        FieldCell tc = state.field.getCellViaPosition(to);
        if (tc == null || tc.getObjectType() == FieldCell.ObjectType.WALL) return false;
        if (state.field.getCellViaPosition(to).getObjectType() == FieldCell.ObjectType.FREIGHT && !move(to, dx, dy)) return false;

        if (state.field.getCellViaPosition(from).getObjectType() == FieldCell.ObjectType.PLAYER) state.field.player = to;
        state.field.updateFieldObject(to,   state.field.getCellViaPosition(from).getObjectType());
        state.field.updateFieldObject(from, FieldCell.ObjectType.EMPTY);
        return true;
    }

    public void refreshScreen() {
        gui.refresh();
        for (int y = 0; y < state.field.getHeight(); y++) gui.putString(0, y, state.field.getFieldLine(y));
    }

    @Override
    public void close() throws Exception {
        gui.close();
    }
}
