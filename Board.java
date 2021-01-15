public class Board {

    // holds true states of all squares, true if the square has a ship in it, false otherwise
    // this will be modified once during ship placement, then never again
    boolean[][] data;

    // holds set of characters used to display the board, "~"" for unknown, " " for miss, "X" for hit
    // this will be modified continuously throughout the game as the player/AI hits or misses
    char[][] display;

    int boardSize = 10;

    public Board() {

        data = new boolean[boardSize][boardSize];

        display = new char[boardSize][boardSize];

        // fills data and display with blank spaces, prior to ship placement
        for(int i = 0; i < boardSize; i++) {

            for(int j = 0; j < boardSize; j++) {

                data[i][j] = false;

                display[i][j] = '~';

            }

        }

    }

    // function for adding ship to the board, length is length of the ship, isVeritcal is wether it is oriented vertically
    // row and column are start coordinates, which should be the leftmost or topmost point of the ship, depending on if it's vertical or not
    // if isVertical is true, the ship is built downwards from there,
    // if isVertical is false then the ship is built to the right from there
    // returns true if placement succeeded without issue, false otherwise
    public boolean placeShip(int length, boolean isVertical, int row, int column) {

        // boolean to return at end of function, will be made true if successful
        boolean success = false;

        // set of spaces planned to be used for the ship
        int[][] plannedSpaces = new int[length][2];

        // fill in plannedSpaces based on wether ship is vertical or horizontal
        for(int i = 0; i < length; i++) {

            if(isVertical) {

                plannedSpaces[i][0] = row + i;
                plannedSpaces[i][1] = column;

            } else {

                plannedSpaces[i][0] = row;
                plannedSpaces[i][1] = column + i;

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
                try {

                    if(!data[currentX][currentY]) {

                        numOfValidSpaces++;
    
                    }

                } catch(Exception e) {}

            }

        }

        // if these two are equal, then the ship is valid and can be placed
        boolean isValid = numOfValidSpaces == length;

        // final check for validity of move
        if(isValid) {

            // if the move is valid, then place the ship
            for(int i = 0; i < length; i++) {

                int currentX = plannedSpaces[i][0];
                int currentY = plannedSpaces[i][1];

                data[currentX][currentY] = true;

            }

            // ship has successfully been placed in valid squares
            success = true;

        }

        return success;

    }

    public boolean play(int row, int column) {

        boolean success = false;

        boolean isValid = isValidGuess(row, column);

        // if the move is valid, make it
        if(isValid) {

            // if data is true at the space, it's a hit, update the board accordingly
            // otherwise it's a miss, update the board accordingly
            if(data[row][column]) {

                display[row][column] = 'X';

            } else {

                display[row][column] = ' ';

            }

            // move was made successfully
            success = true;

        }

        return success;

    }

    // prints display for viewing by player
    public void printBoard() {

        String topString = "   ";

        for(int i = 0; i < boardSize; i++) {

            topString = topString + (i + 1) + "  ";

        }

        System.out.println(topString);

        String[] rows = new String[boardSize];

        // loop through all the rows
        for(int i = 0; i < boardSize; i++) {

            // create seed string
            String row = "" + Game.numToCha(i) + "  ";

            // loop through characters in current row, adding them to the seed string, spaced apart by one space
            for(int j = 0; j < boardSize; j++) {

                row = row + display[i][j] + "  ";

            }

            // put completed string in array
            rows[i] = row;

        }

        // print out all the rows
        for(int i = 0; i < boardSize; i++) {

            System.out.println(rows[i]);

        }

    }

    public boolean detectVictory() {

        int shipSpaces = 0;

        int hits = 0;

        // loop through arrays
        for(int i = 0; i < boardSize; i++) {

            for(int j = 0; j < boardSize; j++) {

                // if data is true at space, there's a ship there
                if(data[i][j]) {

                    shipSpaces++;

                }

                // if display has an X at that space, the player has hit it
                if(display[i][j] == 'X') {

                    hits++;

                }

            }

        }

        // if the number of spaces with ships is the same as the number of hits, the player has sunk all ships and won
        return shipSpaces == hits;

    }

    // checks if the given guess is a valid space for the player to guess
    public boolean isValidGuess(int row, int column) {

        boolean isValid = false;

        // checks if the guess is in bounds
        if(checkBounds(row, column)) {

            // checks if the guess is an unknown space
            if(display[row][column] == '~') {

                isValid = true;

            }

        }

        return isValid;

    }

    // checks if coordinates given are within the board;
    public boolean checkBounds(int row, int column) {

        boolean inBounds = false;

        if((row < boardSize) && (row > -1)) {

            if((column < boardSize) && (column > -1)) {

                inBounds = true;

            }

        }

        return inBounds;

    }

    // debug only function for veiwing data array directly
    public void printTrueBoard() {

        String[] rows = new String[boardSize];

        // loop throught all the rows
        for(int i = 0; i < boardSize; i++) {

            // create seed string
            String row = "";

            // loop through data array, adding X or O to seed string depending on wether space is occupied
            // with space to seperate
            for(int j = 0; j < boardSize; j++) {

                row = row + (data[i][j] ? 'X' : 'O') + " ";

            }

            // add completed string to array
            rows[i] = row;

        }

        // print out all the rows
        for(int i = 0; i < boardSize; i++) {

            System.out.println(rows[i]);

        }

    }

    // does what it says on the tin
    public boolean[][] getData() {

        return data;

    }

    // does what it says on the tin
    public char[][] getDisplay() {

        return display;

    }

    // does what it says on the tin
    public void reset() {

        data = new boolean[boardSize][boardSize];

        display = new char[boardSize][boardSize];

        // fills data and display with blank spaces, prior to ship placement
        for(int i = 0; i < boardSize; i++) {

            for(int j = 0; j < boardSize; j++) {

                data[i][j] = false;

                display[i][j] = '~';

            }

        }

    }

}