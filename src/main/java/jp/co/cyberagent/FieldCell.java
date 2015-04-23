package jp.co.cyberagent;

public class FieldCell {

    enum FloorType {
        EMPTY,
        GOAL,
    }

    enum ObjectType {
        EMPTY,
        WALL,
        PLAYER,
        FREIGHT
    }

    private final FloorType floorType;
    private final ObjectType objectType;

    FieldCell(char c) {
        switch (c) {
            case ' ':
                this.floorType =  FloorType.EMPTY;
                this.objectType = ObjectType.EMPTY;
                break;
            case '#':
                this.floorType =  FloorType.EMPTY;
                this.objectType = ObjectType.WALL;
                break;
            case 'o':
                this.floorType =  FloorType.EMPTY;
                this.objectType = ObjectType.FREIGHT;
                break;
            case '.':
                this.floorType =  FloorType.GOAL;
                this.objectType = ObjectType.EMPTY;
                break;
            case 'p':
                this.floorType =  FloorType.EMPTY;
                this.objectType = ObjectType.PLAYER;
                break;
            default:
                this.floorType =  FloorType.EMPTY;
                this.objectType = ObjectType.WALL;
                break;
        }
    }

    FieldCell(FloorType floorType, ObjectType objectType) {
        this.floorType = floorType;
        this.objectType = objectType;
    }

    public FloorType getFloorType() {
        return floorType;
    }

    public ObjectType getObjectType() {
        return objectType;
    }

    public char convertToChar() {
        if (objectType == ObjectType.EMPTY) {
            switch (floorType) {
                case EMPTY:
                    return ' ';
                case GOAL:
                    return '.';
            }
        } else if (objectType == ObjectType.WALL) {
            return '#';
        } else if (objectType == ObjectType.PLAYER) {
            return 'p';
        } else {
            return 'o';
        }

        return '?';
    }

    @Override
    public String toString() {
        return String.valueOf(convertToChar());
    }

    @Override
    public boolean equals(Object obj) {
        FieldCell c = (FieldCell)obj;
        return floorType == c.floorType && objectType == c.objectType;
    }

}
