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

    // function for adding ship to the board, length is length of the ship, isVeritcal is wether it is oriented vertically
    // x and y are start coordinates, which should be the leftmost or topmost point of the ship, depending on if it's vertical or not
    // if isVertical is true, the ship is built downwards from there,
    // if isVertical is false then the ship is built to the right from there
    // returns true if placement succeeded without issue, false otherwise
    public boolean placeShip(int length, boolean isVertical, int x, int y) {

        // boolean to return at end of function, will be made true if successful
        boolean success = false;

        // set of spaces planned to be used for the ship
        int[][] plannedSpaces = new int[length][2];

        // fill in plannedSpaces based on wether ship is vertical or horizontal
        for(int i = 0; i < length; i++) {

            if(isVertical) {

                plannedSpaces[i][0] = x;
                plannedSpaces[i][1] = y + i;

            } else {

                plannedSpaces[i][0] = x + i;
                plannedSpaces[i][1] = y;

            }

        }

        int numOfValidSpaces = 0;

        // now that plannedSpaces is full, check that each one is within bounds and unoccupied
        for(int i = 0; i < length; i++) {

            int currentX = plannedSpaces[i][0];
            int currentY = plannedSpaces[i][1];

            // check that the space is on the board
            if(checkBounds(currentX, currentY)) {

                // check that the space is false (unoccupied) in data array
                if(!data[currentX][currentY]) {

                    numOfValidSpaces++;

                }

            }

        }

        // if these two are equal, then the ship is valid and can be placed
        if(numOfValidSpaces == length) {

            for(int i = 0; i < length; i++) {

                int currentX = plannedSpaces[i][0];
                int currentY = plannedSpaces[i][1];

                data[currentX][currentY] = true;

            }

        }

        success = (numOfValidSpaces == length);

        return success;

    }

    // checks if the given guess is a valid space for the player to guess
    public boolean isValidGuess(int x, int y) {

        boolean isValid = false;

        // checks if the guess is in bounds
        if(checkBounds(x, y)) {

            // checks if the guess is an unKnown space
            if(display[x][y] == '~') {

                isValid = true;

            }

        }

        return isValid;

    }

    // checks if coordinates given are within the board;
    public boolean checkBounds(int x, int y) {

        boolean inBounds = false;

        if((x < 9) && (x > -1)) {

            if((y < 9) && (y > -1)) {

                inBounds = true;

            }

        }

        return inBounds;

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