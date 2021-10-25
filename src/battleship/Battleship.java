package battleship;

/**
 * Represents a 4-cell ship called Battleship.
 */
public class Battleship extends Ship{

    /**
     * Creates a battleship.
     */
    protected Battleship() {
        super(4);
    }

    /**
     * Gives a name of a Battleship.
     * @return a name of the ship.
     */
    @Override
    public String toString() {
        return "Battleship";
    }
}
