package jp.co.cyberagent;

import com.google.gson.Gson;
import com.google.gson.stream.JsonWriter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Game implements AutoCloseable {

    private Gui gui;
    private GameState state;
    private Gson gson = new Gson();

    public Game(Gui gui, GameState state) {
        this.gui = gui;
        this.state = state;
        init();
    }

    private void init() {
        gui.init();
        refreshScreen();
    }

    public void play() throws IOException {
        boolean playing = true;
        do {
            GuiAction action = gui.getAction();
            switch (action) {
                case SAVE:
                    try (JsonWriter writer = new JsonWriter(new BufferedWriter(new FileWriter("./state.json")))) {
                        gson.toJson(state, GameState.class, writer);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                case QUIT:
                    playing = false;
                    break;
                case RESET:
                    while (!state.actionStack.isEmpty()) undoAction(state.actionStack.remove(state.actionStack.size() - 1));
                case UNDO:
                    if (!state.actionStack.isEmpty()) undoAction(state.actionStack.remove(state.actionStack.size() - 1));
                    break;
                default:
                    if (state.limit < 0 || state.actionStack.size() < state.limit) doAction(action);
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

        if (move(state.field.player, dx, dy)) {
            state.actionStack.add(action);
            refreshScreen();
        }
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
        if (state.limit > 0) gui.putString(0, state.field.getHeight() + 1, state.actionStack.size() + " / " + state.limit);
    }

    @Override
    public void close() throws Exception {
        gui.close();
    }
}
