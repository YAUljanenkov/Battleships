package battleship;

/**
 * Represents a 1-cell ship called Submarine.
 */
public class Submarine extends Ship{

    /**
     * Creates a submarine.
     */
    protected Submarine() {
        super(1);
    }

    /**
     * Gives a name of a Submarine.
     * @return a name of the ship.
     */
    @Override
    public String toString() {
        return "Submarine";
    }
}
