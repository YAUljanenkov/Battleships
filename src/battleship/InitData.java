package battleship;

record InitData(int n, int m, int carrierCount, int battleshipCount, int cruiserCount, int destroyerCount,
                       int submarineCount) {
    public InitData(int[] intArgs) {
        this(intArgs[0], intArgs[1], intArgs[2], intArgs[3], intArgs[4], intArgs[5], intArgs[6]);
    }
}
