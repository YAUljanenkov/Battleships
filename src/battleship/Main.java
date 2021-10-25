package battleship;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Main {

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
        while (field.hasShips()) {
            var coords = getFireCoordinates();
            nextRound(coords);
        }
        Printer.congratulate();
        Printer.bye();
    }

    private record Coordinates(int x, int y) {}

    protected static void nextRound(Coordinates coords) {
        Printer.clear();
        field.fireAtCoordinates(coords.x, coords.y);
        field.printField();
    }

    protected static void startGame() {
        var in = new Scanner(System.in);
        Printer.start();
        in.nextLine();
    }

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
