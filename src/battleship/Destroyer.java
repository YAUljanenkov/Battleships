package battleship;

/**
 * Represents a 2-cell ship called Destroyer.
 */
public class Destroyer extends Ship{

    /**
     * Creates a destroyer.
     */
    protected Destroyer() {
        super(2);
    }

    /**
     * Gives a name of a Destroyer.
     * @return a name of the ship.
     */
    @Override
    public String toString() {
        return "Destroyer";
    }
}
