package jp.co.cyberagent;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Field {

    @SerializedName("width")
    private final int width;

    @SerializedName("height")
    private final int height;

    @SerializedName("player_position")
    public Position player;

    @SerializedName("data")
    private List<FieldCell> data = new ArrayList<>();

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

                data.add(new FieldCell(c));
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

    public int getIndex(Position p) {
        return p.y * getWidth() + p.x;
    }

    public void updateFieldObject(Position p, FieldCell.ObjectType objectType) {
        FieldCell cc = getCellViaPosition(p);
        data.set(getIndex(p), new FieldCell(cc.getFloorType(), objectType));
    }

    public FieldCell getCellViaPosition(Position p) {
        return data.get(getIndex(p));
    }

    public String getFieldLine(int y) {
        if (y < 0 || y >= height) throw new IndexOutOfBoundsException("");

        StringBuilder sb = new StringBuilder();
        for (int x = 0; x < width; x++) sb.append(getCellViaPosition(new Position(x, y)));

        return sb.toString();
    }
}
