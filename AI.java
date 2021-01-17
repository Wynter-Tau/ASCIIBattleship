import java.util.Random;

public class AI {

    Random randomizer = new Random();

    // blank constructor
    public AI() {}

    // look through all the rows and columns of the board to find the space with the best chance of hitting
    public int[] findMove(Board boardObject) {

        // store board that the AI should be able to actually see
        char[][] board = boardObject.getDisplay();

        int[] rowScores = getRowScores(board);
        int[] columnScores = getColumnScores(board);

        int[] bestRows = findHighestScoringIndexes(rowScores);
        int[] bestColumns = findHighestScoringIndexes(columnScores);

        int[] moveCoordinates = new int[2];

        // randomly choose from the best indexes
        moveCoordinates[0] = bestRows[randomizer.nextInt(bestRows.length)];
        moveCoordinates[1] = bestColumns[randomizer.nextInt(bestColumns.length)];

        return moveCoordinates;

    }

    // get the scores of all the rows,
    public int[] getRowScores(char[][] board) {

        // score of the row currently be checked
        int currentRowScore = 0;

        // list of the row's scores
        int[] rowScores = new int[Board.boardSize];

        for(int row = 0; row < Board.boardSize; row++) {

            for(int column = 0; column < Board.boardSize; column++) {

                // increase score of the row based on the state of each space
                if(board[row][column] == '~') {

                    currentRowScore+= 1;

                }

                if(board[row][column] == 'X') {

                    currentRowScore+= 3;

                }

                if(board[row][column] == ' ') {

                    currentRowScore+= 0;

                }

            }

            rowScores[row] = currentRowScore;

            // reset current score for next row
            currentRowScore = 0;

        }

        return rowScores;
        
    }

    // get the scores of all the columns
    public int[] getColumnScores(char[][] board) {

        // score of the column currently be checked
        int currentColumnScore = 0;

        // list of the collumns's scores
        int[] columnScores = new int[Board.boardSize];

        for(int column = 0; column < Board.boardSize; column++) {
 
            for(int row = 0; row < Board.boardSize; row++) {

                // increase score of the column based on the state of each space
                if(board[row][column] == '~') {

                    currentColumnScore+= 1;

                }

                if(board[row][column] == 'X') {

                    currentColumnScore+= 3;

                }

                if(board[row][column] == ' ') {

                    currentColumnScore+= 0;

                }

            }

            columnScores[column] = currentColumnScore;

            // reset current score for next column
            currentColumnScore = 0;

        }

        return columnScores;
        
    }

    // find the largest scores out of the list, and the indexes that correlate to them
    public int[] findHighestScoringIndexes(int[] scores){
    
        int highestScore = -1;

        // idenitify the highest score
        for(int i = 0; i < Board.boardSize; i++) {

            if(scores[i] > highestScore) {

                highestScore = scores[i];

            }

        }

        int highScoreCount = 0;

        // count number of high scores
        for(int i = 0; i < Board.boardSize; i++) {

            if(scores[i] == highestScore) {

                highScoreCount++;

            }

        }

        // list of highest scoring indexes, will be final output once filled
        int[] highestScoringIndexes = new int[highScoreCount];

        // secondary counter for keeping track of the index in highestScoringIndexes
        int secondaryCounter = 0;

        for(int i = 0; i < Board.boardSize; i++) {

            if(scores[i] == highestScore) {

                highestScoringIndexes[secondaryCounter] = i;
                secondaryCounter++;

            }

        }

        return highestScoringIndexes;

    }

}