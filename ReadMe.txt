Welcome to my version of "Battleships" game!

Both torpedo or recovery modes are not implemented.

To start a game you should type:

java -jar /path/to/game/battlefield.jar <height> <width> <carrier amount> <battleship amount> <cruiser amount> <destroyer amount> <submarine amount>

Where all arguments are positive integers separated by space. Height and width must be in [6, 40] range.

You are allowed to not pass command line arguments at all. In this scenario you will be asked to enter required arguments in console.

All what you pass to console should be a positive integer.

First you will be asked to enter a height of the field. Please pass a number in range [6, 40].

Then you will be asked to enter a width of the field. Please pass a number in range [6, 40].

Then you will be asked to write info about how many ships there should be. Write 5 numbers in the console separated by
space, from bigger ship to smaller ship:
<carrier amount> <battleship amount> <cruiser amount> <destroyer amount> <submarine amount>.
Example:
0 1 2 3 4

Then the program will try to create a field and place ships on it. If it fails, the program will ask you if you want to try
again or quit. Press Enter if you want to try again. Write "quit" and press Enter if you want to quit.

If the field is created and filled with ships, you will be asked if you are ready to start a game. Press Enter to start
a game.

You will see a field. Now you can write coordinates of the cell you want to strike. Write them in a format <height coord> <width coord>,
so two positive integers separated by space.

When you destroy all the ships, the game will write you amount of tries you have made and finish.