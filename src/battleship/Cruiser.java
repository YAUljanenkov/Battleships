package battleship;

/**
 * Represents a 3-cell ship called Cruiser.
 */
public class Cruiser extends Ship{

    /**
     * Creates a cruiser.
     */
    protected Cruiser() {
        super(3);
    }

    /**
     * Gives a name of a Cruiser.
     * @return a name of the ship.
     */
    @Override
    public String toString() {
        return "Cruiser";
    }
}
