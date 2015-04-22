package jp.co.cyberagent;

public enum GuiAction {
    MOVE_UP,
    MOVE_RIGHT,
    MOVE_DOWN,
    MOVE_LEFT,
    QUIT,
    UNKNOWN;

    public static GuiAction convertFromChar(char c) {
        switch (c) {
            case 'k':
                return MOVE_UP;
            case 'l':
                return MOVE_RIGHT;
            case 'j':
                return MOVE_DOWN;
            case 'h':
                return MOVE_LEFT;
            case 'q':
                return QUIT;
        }

        return UNKNOWN;
    }
}
