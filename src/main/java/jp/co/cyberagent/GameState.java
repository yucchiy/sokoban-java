package jp.co.cyberagent;

import com.google.gson.annotations.SerializedName;

import java.util.LinkedList;
import java.util.List;

public class GameState {

    @SerializedName("field")
    public Field field;

    @SerializedName("limit")
    public int limit;

    @SerializedName("action_stack")
    public List<GuiAction> actionStack = new LinkedList<>();

    GameState(Field field, int limit) {
        this.field = field;
        this.limit = limit;
    }
}
