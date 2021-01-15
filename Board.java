public class Board {

    // holds true states of all squares, true if the square has a ship in it, false otherwise
    // this will be modified once during ship placement, then never again
    boolean[][] data;

    // holds set of characters used to display the board, "~"" for unknown, " " for miss, "X" for hit
    // this will be modified continuously throughout the game as the player/AI hits or misses
    char[][] display;

    public Board() {

        data = new boolean[8][8];

        display = new char[8][8];

        // fills data and display with blank spaces, prior to ship placement
        for(int i = 0; i < data.length; i++) {

            for(int j = 0; j < data[i].length; j++) {

                data[i][j] = false;

                display[i][j] = '~';

            }

        }

    }

    public boolean isValidGuess(int x, int y) {

        isValid = false;

        // check if space is within x bound
        if(x < 9) {

            // check if space is within y bound
            if(y < 9) {

                // check that the space hasn't been guessed already
                if(display[x][y] == '~') {

                    isValid = true;

                }

            }

        }

        return isValid;

    }

    // does what it says on the tin
    public void reset() {

        data = new boolean[8][8];

        display = new char[8][8];

        // fills data and display with blank spaces, prior to ship placement
        for(int i = 0; i < data.length; i++) {

            for(int j = 0; j < data[i].length; j++) {

                data[i][j] = false;

                display[i][j] = '~';

            }

        }

    }

    // prints display for viewing by player
    public void printBoard() {

        String[] rows = new String[8];

        // loop through all the rows
        for(int i = 0; i < rows.length; i++) {

            // create seed string
            String row = "";

            // loop through characters in current row, adding them to the seed string, spaced apart by one space
            for(int j = 0; j < display[i].length; j++) {

                row = row + display[i][j] + " ";

            }

            // put completed string in array
            rows[i] = row;

        }

        // print out all the rows
        for(int i = 0; i < rows.length; i++) {

            System.out.println(rows[i]);

        }

    }

}