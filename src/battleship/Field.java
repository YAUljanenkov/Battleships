package battleship;

import java.util.Random;

public class Field {
    private int n;
    private int m;
    private Cell[][] field;
    private final Random random = new Random();

    public Field(int n, int m, int carrierCount, int battleshipCount, int cruiserCount, int destroyerCount,
                 int submarineCount) {
        this.n = n;
        this.m = m;
        if (n > 30 || m > 30) {
            throw new IllegalArgumentException("Field side cannot be bigger than 20 cells.");
        }
        if (this.n * this.m < (carrierCount * 21 + battleshipCount * 18 + cruiserCount * 15 + destroyerCount * 12
                + submarineCount * 9) / 3 * 2) {
            throw new IllegalArgumentException("Field is less than ships' fleet.");
        }
        field = new Cell[n][m];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; j++) {
                field[i][j] = new Cell(null);
            }
        }
        fillField(carrierCount, battleshipCount, cruiserCount, destroyerCount, submarineCount);
    }

    private void fillField(int carrierCount, int battleshipCount, int cruiserCount, int destroyerCount,
                           int submarineCount) {

        for (int i = 0; i < carrierCount; i++) {
            var carrier = new Carrier();
            addShipToMap(carrier);
        }

        for (int i = 0; i < battleshipCount; i++) {
            var battleship = new Battleship();
            addShipToMap(battleship);
        }

        for (int i = 0; i < cruiserCount; i++) {
            var cruiser = new Cruiser();
            addShipToMap(cruiser);
        }

        for (int i = 0; i < destroyerCount; i++) {
            var destroyer = new Destroyer();
            addShipToMap(destroyer);
        }

        for (int i = 0; i < submarineCount; i++) {
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
                field[i][j].ship = ship;
                ship.addCell(field[i][j]);
            }
        }
    }

    record ShipData(int x, int y, boolean isVertical) {
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
                if (field[i][j].ship != null) {
                    return false;
                }
            }
        }

        return true;
    }

    record CoordsData(int toX, int toY) {
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
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(field[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}