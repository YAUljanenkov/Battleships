package battleship;

import java.util.Random;

public class Field {
    private final int n;
    private final int m;
    private final Cell[][] field;
    private int shipsCount;
    private final Random random = new Random();

    public Field(InitData init) {
        this.n = init.n();
        this.m = init.m();
        shipsCount = init.carrierCount() + init.battleshipCount() + init.cruiserCount() + init.destroyerCount() +
                init.submarineCount();
        if (n > 40 || m > 40 || n < 6 || m < 6) {
            throw new IllegalArgumentException("Field side cannot be bigger than 40 cells and less then 6 cells.");
        }
        if (this.n * this.m < (init.carrierCount() * 21 + init.battleshipCount() * 18 + init.cruiserCount() * 15 +
                init.destroyerCount() * 12 + init.submarineCount() * 9) / 3 * 2) {
            throw new IllegalArgumentException("Field is less than ships' fleet.");
        }
        field = new Cell[n][m];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; j++) {
                field[i][j] = new Cell(null);
            }
        }
        fillField(init);
    }

    protected boolean hasShips() {
        return shipsCount > 0;
    }

    public int getN() {
        return n;
    }

    public int getM() {
        return m;
    }

    private void fillField(InitData data) {

        for (int i = 0; i < data.carrierCount(); i++) {
            var carrier = new Carrier();
            addShipToMap(carrier);
        }

        for (int i = 0; i < data.battleshipCount(); i++) {
            var battleship = new Battleship();
            addShipToMap(battleship);
        }

        for (int i = 0; i < data.cruiserCount(); i++) {
            var cruiser = new Cruiser();
            addShipToMap(cruiser);
        }

        for (int i = 0; i < data.destroyerCount(); i++) {
            var destroyer = new Destroyer();
            addShipToMap(destroyer);
        }

        for (int i = 0; i < data.submarineCount(); i++) {
            var submarine = new Submarine();
            addShipToMap(submarine);
        }
    }

    private void addShipToMap(Ship ship) {
        var data = getCoordinatesOfTheShip(ship);
        int toX = data.isVertical ? data.x + 1: data.x + ship.getLength();
        int toY = !data.isVertical ? data.y + 1: data.y + ship.getLength();
        for (int i = data.x; i < toX; i++) {
            for (int j = data.y; j < toY; j++) {
                field[i][j].setShip(ship);
                ship.addCell(field[i][j]);
            }
        }
    }

    private record ShipData(int x, int y, boolean isVertical) {
    }

    private ShipData getCoordinatesOfTheShip(Ship ship) {
        boolean isVertical = random.nextBoolean();
        int x, y;

        if (!isVertical) {
            x = random.nextInt(n - ship.getLength());
            y = random.nextInt(m);
        } else {
            x = random.nextInt(n);
            y = random.nextInt(m - ship.getLength());
        }

        var data = new ShipData(x, y, isVertical);

        if (checkCoordinatesOfTheShip(data, ship)) {
            return data;
        } else {
            return getCoordinatesOfTheShip(ship);
        }
    }

    private boolean checkCoordinatesOfTheShip(ShipData data, Ship ship) {
        int fromX = (data.x == 0) ? data.x : data.x - 1;
        int fromY = (data.y == 0) ? data.y : data.y - 1;

        var coords = getEndCoordinates(data, ship);

        if (coords.toX == -1 || coords.toY == -1) {
            return false;
        }
        for (int i = fromX; i < coords.toX; i++) {
            for (int j = fromY; j < coords.toY; j++) {
                if (field[i][j].hasShip()) {
                    return false;
                }
            }
        }

        return true;
    }

    private record CoordsData(int toX, int toY) {
    }

    private CoordsData getEndCoordinates(ShipData data, Ship ship) {
        int toX = -1;

        if (!data.isVertical && data.x + ship.getLength() == n) {
            toX = data.x + ship.getLength();
        } else if (!data.isVertical && data.x + ship.getLength() < n) {
            toX = data.x + ship.getLength() + 1;
        } else if (data.x == n - 1 && data.isVertical) {
            toX = data.x + 1;
        } else if (data.x < n - 1 && data.isVertical) {
            toX = data.x + 2;
        }

        int toY = -1;

        if (data.y + ship.getLength() == m && data.isVertical) {
            toY = data.y + ship.getLength();
        } else if (data.y + ship.getLength() < m && data.isVertical) {
            toY = data.y + ship.getLength() + 1;
        } else if (data.y == m - 1 && !data.isVertical) {
            toY = data.y + 1;
        } else if (data.y < m - 1 && !data.isVertical) {
            toY = data.y + 2;
        }

        return new CoordsData(toX, toY);
    }

    protected void printField() {
        System.out.print("\t|");
        for (int i = 0; i < m; i++) {
            System.out.print((i + 1) + "\t");
        }
        System.out.println();
        for (int i = -1; i < m; i++) {
            if(i == -1) {
                System.out.print("_\t|");
            } else {
                System.out.print("_\t");
            }
        }
        System.out.println();
        for (int i = 0; i < n; i++) {
            System.out.print((i + 1) + "\t|");
            for (int j = 0; j < m; j++) {
                System.out.print(field[i][j]);
                System.out.print("\t");
            }
            System.out.println();
        }
    }

    protected void fireAtCoordinates(int x, int y) {
        var Cell = field[x][y];
        Cell.shoot();
        switch (Cell.getType()) {
            case FiredHit -> Printer.hit();
            case FiredMiss -> Printer.miss();
            case Sunk -> {
                --shipsCount;
                Printer.sink(Cell.getShipName());
            }
        }
    }
}