package jp.co.cyberagent;

import java.util.Map;
import java.util.HashMap;

public class Field {

    private final int width, height;
    public Position player;
    private Map<Position, FieldCell> data = new HashMap<>();

    Field(FieldOption fieldOption) {
        width = fieldOption.getWidth();
        height = fieldOption.getHeight();

        int px = 0, py = 0;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                char c = fieldOption.getChar(x, y);
                if (c == 'p') {
                    px = x;
                    py = y;
                }

                data.put(new Position(x, y), new FieldCell(c));
            }
        }

        player = new Position(px, py);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void updateFieldObject(Position p, FieldCell.ObjectType objectType) {
        FieldCell cc = getCellViaPosition(p);
        data.replace(p, new FieldCell(cc.getFloorType(), objectType));
    }

    public FieldCell getCellViaPosition(Position pos) {
        return data.get(pos);
    }

    public String getFieldLine(int y) {
        if (y < 0 || y >= height) throw new IndexOutOfBoundsException("");

        StringBuilder sb = new StringBuilder();
        for (int x = 0; x < width; x++) sb.append(getCellViaPosition(new Position(x, y)));

        return sb.toString();
    }
}
