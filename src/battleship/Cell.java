package battleship;

public class Cell {
    Ship ship;
    CellType type = CellType.NonFired;

    public Cell(Ship ship) {
        this.ship = ship;
    }

    public CellType getType() {
        return type;
    }

    public void setType(CellType type) {
        this.type = type;
    }

//    @Override
//    public String toString() {
//        return switch (type) {
//            case NonFired -> "°";
//            case FiredMiss -> "•";
//            case FiredHit -> "^";
//            case Sunk -> "x";
//        };
//    }


    @Override
    public String toString() {
        return ship != null ? "X": "o";
    }
}
