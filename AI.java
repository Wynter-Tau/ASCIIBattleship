import java.util.Random;

public class AI {

    Random randomizer = new Random();
    
    // increase in weight to squares around a hit
    int hitWieght = 1;

    // decrease in wieght to squares around a miss
    int missWieght = 0;

    // start value of a square
    int startValue = 1;

    int boardSize = Board.boardSize;

    int[][] scoreBoard = new int[boardSize][boardSize];

    // set up scoreBoard
    public AI() {

        for(int row = 0; row < boardSize; row++) {

            for(int column = 0; column < boardSize; column++) {

                scoreBoard[row][column] = startValue;

            }

        }

    }

    // look through all the rows and columns of the board to find the space with the best chance of hitting
    public int[] findMove(Board boardObject) {

        // find highest score
        int highscore = -1;

        for(int row = 0; row < boardSize; row++) {

            for(int column = 0; column < boardSize; column++) {

                if((scoreBoard[row][column] > highscore) && (boardObject.isValidGuess(row, column))) {

                    highscore = scoreBoard[row][column];

                }

            }

        }

        // count number of high scores from valid spaces
        int highscoreCount = 0;

        for(int row = 0; row < boardSize; row++) {

            for(int column = 0; column < boardSize; column++) {

                if(scoreBoard[row][column] == highscore && (boardObject.isValidGuess(row, column))) {

                    highscoreCount++;

                }

            }

        }

        // list of best possible moves, the ones with the highest possible score
        int[][] bestMoves = new int[highscoreCount][2];

        // secondary counter for keeping track of index in bestMoves
        int secondaryCounter = 0;

        // find all of the best moves, excluding the invalid ones
        for(int row = 0; row < boardSize; row++) {

            for(int column = 0; column < boardSize; column++) {

                if((scoreBoard[row][column] == highscore) && (boardObject.isValidGuess(row, column))) {

                    int[] coordinates = {row, column};

                    bestMoves[secondaryCounter] = coordinates;

                    secondaryCounter++;

                }

            }

        }
        
        int[] bestMove = bestMoves[randomizer.nextInt(bestMoves.length)];

        return bestMove;

    }

    public void updateScoreBoard(int[] move, Board boardObject) {

        int row = move[0];
        int column = move[1];

        char[][] board = boardObject.getDisplay();

        // weight to place on spaces surrounding the given move
        int weight = charToWeight(board[row][column]);

        // all the surrounding spaces
        int[] upSpace = {row - 1, column};
        int[] downSpace = {row + 1, column};
        int[] rightSpace = {row, column - 1};
        int[] leftSpace = {row, column + 1};

        int[][] spacesToUpdate = {upSpace, downSpace, rightSpace, leftSpace};

        // loop throught the surrounding spaces, and if they are in bounds, and not played on yet, update them
        for(int i = 0; i < 4; i++) {

            int[] coordinates = spacesToUpdate[i];

            if(boardObject.isValidGuess(coordinates[0], coordinates[1])) {

                scoreBoard[coordinates[0]][coordinates[1]] += weight;

            }

        }

        scoreBoard[row][column] = 0;

    }

    public int charToWeight(char cha) {

        int weight = 0;

        switch(cha) {

            case 'X':

                weight = hitWieght;

            break;

            case '*':

                weight = missWieght;
            
            break;

        }

        return weight;

    }

    // debug only function for seeing scoreBoard
    public void printScoreBoard() {

        String[] rows = new String[boardSize];

        // loop throught all the rows
        for(int i = 0; i < boardSize; i++) {

            // create seed string
            String row = "";

            // loop through data array, adding X or O to seed string depending on wether space is occupied
            // with space to seperate
            for(int j = 0; j < boardSize; j++) {

                String scoreString = "" + scoreBoard[i ][j];

                scoreString = scoreString + ((scoreString.length() == 1) ? " " : "");

                row = row + scoreString + " ";

            }

            // add completed string to array
            rows[i] = row;

        }

        // print out all the rows
        for(int i = 0; i < boardSize; i++) {

            System.out.println(rows[i]);

        }

    }

}