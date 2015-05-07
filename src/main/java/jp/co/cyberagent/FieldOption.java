package jp.co.cyberagent;

import java.util.List;

public class FieldOption {

    private List<String> fieldStrings;

    FieldOption(List<String> fieldStrings) {
        this.fieldStrings = fieldStrings;
    }

    public int getHeight() {
        return (fieldStrings != null) ? fieldStrings.size() : 0;
    }

    public int getWidth() {
        return getHeight() > 0 ? fieldStrings.get(0).length() : 0;
    }

    public char getChar(int x, int y) {
        if (x < 0 || x >= getWidth() || y < 0 || y >= getHeight()) throw new IndexOutOfBoundsException("");
        return fieldStrings.get(y).charAt(x);
    }
}
