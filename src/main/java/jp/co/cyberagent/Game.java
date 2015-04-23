package jp.co.cyberagent;

import java.util.Stack;

public class Game implements AutoCloseable {

    private Gui gui;
    private GameState state;
    private Stack<GuiAction> actionStack = new Stack<>();

    public boolean retry = false;

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
            switch (action) {
                case RESET:
                    retry = true;
                case QUIT:
                    playing = false;
                    break;
                case UNDO:
                    if (!actionStack.empty()) undoAction(actionStack.pop());
                    break;
                default:
                    doAction(action);
                    actionStack.push(action);
                    break;
            }

            if (action == GuiAction.QUIT) playing = false;

        } while (playing);
    }

    public void doAction(GuiAction action) {
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

    public void undoAction(GuiAction action) {
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

        Position np = new Position(state.field.player.x + dx, state.field.player.y + dy);
        FieldCell c = state.field.getCellViaPosition(np);

        Position from = (c != null && c.getObjectType() == FieldCell.ObjectType.FREIGHT) ?
                np : state.field.player;
        if (move(from, dx * -1, dy * -1)) refreshScreen();
    }

    public boolean move(Position from, int dx, int dy) {
        Position to = new Position(from.x + dx, from.y + dy);

        FieldCell tc = state.field.getCellViaPosition(to);
        if (tc == null || tc.getObjectType() == FieldCell.ObjectType.WALL) return false;
        if ((tc.getObjectType() != FieldCell.ObjectType.EMPTY) && !move(to, dx, dy)) return false;

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
