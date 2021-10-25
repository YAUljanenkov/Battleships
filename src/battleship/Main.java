package battleship;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    // Main field object.
    static Field field;

    public static void main(String[] args) {
        Printer.inviteUser();
        field = initializeField(args);
        if(field == null) {
            Printer.bye();
            return;
        }
        startGame();
        Printer.clear();
        field.printField();
        int tries = 0;
        while (field.hasShips()) {
            var coords = getFireCoordinates();
            ++tries;
            nextRound(coords);
        }
        Printer.congratulate(tries);
        Printer.bye();
    }

    /**
     * Record containing coordinates on user's hit.
     */
    private record Coordinates(int x, int y) {}

    /**
     * make new hit.
     * @param coords a cell where user hits.
     */
    protected static void nextRound(Coordinates coords) {
        Printer.clear();
        field.fireAtCoordinates(coords.x, coords.y);
        field.printField();
    }

    /**
     * THis method welcomes user to start a game.
     */
    protected static void startGame() {
        var in = new Scanner(System.in);
        Printer.start();
        in.nextLine();
    }

    /**
     * This method gets coordinates from user of cell to hit.
     * @return coordinates of a cell to hit.
     */
    private static Coordinates getFireCoordinates() {
        int x, y;
        while (true) {
            Printer.askForCoordinates();
            var in = new Scanner(System.in);
            try {
                x = in.nextInt();
                --x;
                y = in.nextInt();
                --y;
                if(x < 0 || x > field.getN() - 1 || y < 0 || y > field.getM() - 1) {
                    Printer.wrongCoords();
                    continue;
                }
                break;
            } catch (Exception e) {
                Printer.wrongArgument();
            }
        }
        return new Coordinates(x, y);
    }

    /**
     * Tries to make a field from a command line args.
     * @param args command line args.
     * @return a field instance or null if user wants to quit.
     */
    public static Field initializeField(String[] args) {
        Field field = null;
        InitData data;
        try {
            data = readArgs(args);
        } catch (IllegalArgumentException e) {
            Printer.wrongArgs();
            data = readInitFromLine();
        }

        try {
            field = new Field(data);
        } catch (IllegalArgumentException e) {

            while (field == null) {
                try {
                    Printer.cannotCreateField();
                    var in = new Scanner(System.in);
                    if(Objects.equals(in.nextLine(), "quit")) break;
                    field = new Field(readInitFromLine());
                } catch (IllegalArgumentException ignored) {
                }
            }

        }

        return field;
    }

    /**
     * Makes an InitData object from Command Line Args.
     * @param args command line args.
     * @return a data required to initialize game.
     */
    private static InitData readArgs(String[] args) {
        if (args.length != 7) {
            throw new IllegalArgumentException("Not enough arguments");
        }

        var intArgs = new int[7];

        try {
            for (int i = 0; i < 7; i++) {
                intArgs[i] = Integer.parseInt(args[i]);
            }
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Wrong arguments");
        }

        return new InitData(intArgs);
    }

    /**
     * Gets all required data from user and command line.
     * @return InitData object.
     */
    private static InitData readInitFromLine() {
        Printer.askForInitArguments();
        Scanner in = new Scanner(System.in);
        int n, m;
        Printer.askForN();
        n = readNumber(in);
        while (n < 6 || n > 30) {
            Printer.wrongArgument();
            Printer.askForN();
            n = readNumber(in);
        }

        Printer.askForM();
        m = readNumber(in);
        while (m < 6 || m > 30) {
            Printer.wrongArgument();
            Printer.askForM();
            m = readNumber(in);
        }

        var shipsData = new ArrayList<Integer>();
        shipsData.add(n);
        shipsData.add(m);
        while (true) {
            try {
                Printer.askForShips();
                for (int i = 0; i < 5; i++) {
                    shipsData.add(in.nextInt());
                }
                break;
            } catch (Exception e) {
                shipsData.clear();
                shipsData.add(n);
                shipsData.add(m);
                in.nextLine();
                Printer.wrongArgument();
            }

        }
        return new InitData(shipsData.stream().mapToInt(i -> i).toArray());
    }

    /**
     * Reads number from a line.
     * @param in scanner object.
     * @return a number or -1 if user did not input a number.
     */
    public static int readNumber(Scanner in) {
        int n = -1;
        try {
            n = in.nextInt();
        } catch (Exception e) {
            in.next();
        }

        return n;
    }
}
