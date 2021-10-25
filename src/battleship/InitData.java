package battleship;


/**
 * An object that represents required initial data for the game.
 */
record InitData(int n, int m, int carrierCount, int battleshipCount, int cruiserCount, int destroyerCount,
                       int submarineCount) {

    /**
     * A custom constructor that creates the record from an array.
     * @param intArgs Array of 7 elements that contains data of all fields.
     */
    public InitData(int[] intArgs) {
        this(intArgs[0], intArgs[1], intArgs[2], intArgs[3], intArgs[4], intArgs[5], intArgs[6]);
    }
}
