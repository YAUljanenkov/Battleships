package battleship;

public class Ship {
    private final int length;
    private int size;
    private final Cell[] cells;

    protected Ship(int length) {
        this.length = length;
        size = 0;
        cells = new Cell[length];
    }

    protected int getLength() {
        return length;
    }

    @Override
    public String toString() {
        return "Ship";
    }

    protected void addCell(Cell cell) {
        cells[size] = cell;
        ++size;
    }
}