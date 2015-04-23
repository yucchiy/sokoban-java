package jp.co.cyberagent;

public class GameState {

    public Field field;
    public int limit;

    GameState(Field field, int limit) {
        this.field = field;
        this.limit = limit;
    }
}
