package battleship;

public class Carrier extends Ship{

    protected Carrier() {
        super(5);
    }

    @Override
    public String toString() {
        return "Carrier";
    }
}
