package battleship;

import java.util.Arrays;

/**
 * Base object for ship.
 */
public class Ship {

    // Amount of cells that ship takes.
    private final int length;

    // Amount of cells that are filled.
    private int size;

    // Cells that ship occupied.
    private final Cell[] cells;

    /**
     * Creates an instance of the ship
     * @param length amount of cells that ship occupies.
     */
    protected Ship(int length) {
        this.length = length;
        size = 0;
        cells = new Cell[length];
    }

    /**
     * Returns the size of the ship.
     * @return amount of cells that ship occupies.
     */
    protected int getLength() {
        return length;
    }

    /**
     * Gives a name of a Battleship.
     * @return a name of the ship.
     */
    @Override
    public String toString() {
        return "Ship";
    }

    /**
     * Adds new cell that ship occupies.
     * @param cell new cell that ship occupies.
     */
    protected void addCell(Cell cell) {
        cells[size] = cell;
        ++size;
    }

    /**
     * Check if the ship still alive. If not, all cells will be marked as Sunk.
     */
    protected void checkIfAfloat() {
        boolean isAllHit = Arrays.stream(cells).allMatch(val -> val.getType() == CellType.FiredHit);
        if(isAllHit) {
            for (var cell :
                    cells) {
                cell.setType(CellType.Sunk);
            }
        }
    }
}
