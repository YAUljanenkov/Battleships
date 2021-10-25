package battleship;

public class Cell {
    private Ship ship;
    private CellType type = CellType.NonFired;

    protected void setShip(Ship ship) {
        this.ship = ship;
    }
    protected boolean hasShip() {
        return ship != null;
    }

    protected String getShipName() {
        return ship.toString();
    }

    protected Cell(Ship ship) {
        this.ship = ship;
    }

    protected CellType getType() {
        return type;
    }

    protected void setType(CellType type) {
        this.type = type;
    }

    protected CellType shoot() {
        if(ship != null) {
            type = CellType.FiredHit;
            ship.checkIfAfloat();
        } else {
            type = CellType.FiredMiss;
        }
        return type;
    }

    @Override
    public String toString() {
        return switch (type) {
            case NonFired -> "°";
            case FiredMiss -> "o";
            case FiredHit -> "H";
            case Sunk -> "S";
        };
    }


//    @Override
//    public String toString() {
//        return ship != null ? "X": "o";
//    }
}
