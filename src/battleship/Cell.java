package battleship;

/**
 * Represents a Cell - a piece of field.
 */
public class Cell {

    // Instance of a ship that is placed in the cell.
    private Ship ship = null;

    // A status of cell.
    private CellType type = CellType.NonFired;

    /**
     * Allows to place a ship on this cell.
     * @param ship - a ship that should be placed in that cell.
     */
    protected void setShip(Ship ship) {
        this.ship = ship;
    }

    /**
     * Checks if there is a ship in the cell.
     * @return true if there is a ship in the cell and false in other case.
     */
    protected boolean hasShip() {
        return ship != null;
    }

    /**
     * Allow to get a string name of the ship placed in the cell.
     * @return a name of the ship placed in the cell.
     */
    protected String getShipName() {
        return ship != null ? ship.toString() : "";
    }

    /**
     * Returns a type of the current cell.
     * @return a type of cell.
     */
    protected CellType getType() {
        return type;
    }

    /**
     * Allows to set a state of current cell.
     * @param type a new state of current cell.
     */
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

    /**
     * Returns visualize of current cell in the battlefield.
     * @return a symbol that represents a state of the cell.
     */
    @Override
    public String toString() {
        return switch (type) {
            case NonFired -> "°";
            case FiredMiss -> "o";
            case FiredHit -> "H";
            case Sunk -> "S";
        };
    }
}
