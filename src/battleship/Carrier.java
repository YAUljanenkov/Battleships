package battleship;

/**
 * Represents a 5-cell ship called Carrier.
 */
public class Carrier extends Ship{

    /**
     * Creates a carrier.
     */
    protected Carrier() {
        super(5);
    }

    /**
     * Gives a name of a Carrier.
     * @return a name of the ship.
     */
    @Override
    public String toString() {
        return "Carrier";
    }
}
