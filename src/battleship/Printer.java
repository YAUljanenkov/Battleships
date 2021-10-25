package battleship;

import java.io.IOException;

public class Printer {

    public static void inviteUser() {
        System.out.println("Welcome to the battleships game!");
    }

    public static void clear() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }

        } catch (IOException | InterruptedException ignored) {
        }
    }

    public static void wrongArgs() {
        System.out.println("Command line arguments were either empty or wrong.");
    }

    public static void askForInitArguments() {
        System.out.println("Please, enter the initial arguments of the game.");
    }

    public static void askForN() {
        System.out.println("Let's enter the height (N) of the field. It must be bigger than 6 and less than 40.");
    }

    public static void askForM() {
        System.out.println("Please, enter the width (M) of the field. It must be bigger than 6 and less than 40.");
    }

    public static void askForShips() {
        System.out.println("Please, enter the amount of ships in order <Carrier, Battleship, Cruiser, Destroyer, " +
                "Submarine> separated by spaces. Example: 0 1 2 3 4");
    }

    public static void wrongArgument() {
        System.out.println("You passed wrong argument! Are you trying to break my program?");
    }

    public static void cannotCreateField() {
        System.out.println("Oops! Seems, I cannot fit all these battleships on this field. Print \"quit\" if you want" +
                " to quit, or anything else to recreate field.");
    }

    public static void askForCoordinates() {
        System.out.println("Enter coordinates of a cell to fire at in a format \"x y\"");
    }

    public static void hit() {
        System.out.println("Hit!");
    }

    public static void miss() {
        System.out.println("Miss!");
    }

    public static void sink(String shipName) {
        System.out.println("You just have sunk a " + shipName);
    }

    public static void wrongCoords() {
        System.out.println("Your coords must in a range of a field size.");
    }

    public static void start() {
        System.out.println("Press Enter to start a game.");
    }

    public static void congratulate() {
        System.out.println("Hooray! You won!");
    }

    public static void bye() {
        System.out.println("Thanks for playing with me! Bye!");
    }
}