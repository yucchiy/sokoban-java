package jp.co.cyberagent;

public class Position {

    public final int x, y;
    Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        Position pos = (Position)obj;
        return x == pos.x && y == pos.y;
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = ((hash + x) << 5) - (hash + x);
        hash = ((hash + y) << 5) - (hash + y);
        return hash;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
