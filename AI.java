import java.util.Random;

public class AI {

    Random randomizer = new Random();
    
    int unknownWeight = 2;
    int hitWieght = 5;
    int missWieght = 1;

    // blank constructor
    public AI() {}

    // look through all the rows and columns of the board to find the space with the best chance of hitting
    public int[] findMove(Board boardObject) {

        // store board that the AI should be able to actually see
        char[][] board = boardObject.getDisplay();

        int[][] scoreBoard = getScoreBoard(boardObject);

        // find highest score
        int highscore = -1;

        for(int row = 0; row < Board.boardSize; row++) {

            for(int column = 0; column < Board.boardSize; column++) {

                if((scoreBoard[row][column] > highscore) && (boardObject.isValidGuess(row, column))) {

                    highscore = scoreBoard[row][column];

                }

            }

        }

        // count number of high scores from valid spaces
        int highscoreCount = 0;

        for(int row = 0; row < Board.boardSize; row++) {

            for(int column = 0; column < Board.boardSize; column++) {

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
        for(int row = 0; row < Board.boardSize; row++) {

            for(int column = 0; column < Board.boardSize; column++) {

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

    public int[][] getScoreBoard(Board boardObject) {

        char[][] board = boardObject.getDisplay();    
        
        int[] rowScores = getRowScores(board);
        int[] columnScores = getColumnScores(board);

        // grid of scores for later use
        int[][] scoreBoard = new int[Board.boardSize][Board.boardSize];

        // fill scoreBoard with scores of each square
        for(int row = 0; row < Board.boardSize; row++) {

            for(int column = 0; column < Board.boardSize; column++) {

                if(!boardObject.isValidGuess(row, column)) {

                    scoreBoard[row][column] = 0;

                } else {

                    scoreBoard[row][column] = rowScores[row] + columnScores[column];

                }
                
            }

        }
        
        return scoreBoard;

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

                    currentRowScore+= unknownWeight;

                }

                if(board[row][column] == 'X') {

                    currentRowScore+= hitWieght;

                }

                if(board[row][column] == ' ') {

                    currentRowScore+= missWieght;

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
                if(board[row][column] == Board.unknownChar) {

                    currentColumnScore+= unknownWeight;

                }

                if(board[row][column] == Board.hitChar) {

                    currentColumnScore+= hitWieght;

                }

                if(board[row][column] == Board.missChar) {

                    currentColumnScore+= missWieght;

                }

            }

            columnScores[column] = currentColumnScore;

            // reset current score for next column
            currentColumnScore = 0;

        }

        return columnScores;
        
    }

    // debug only function for seeing scoreBoard
    public void printScoreBoard(int[][] scoreBoard) {

        String[] rows = new String[Board.boardSize];

        // loop throught all the rows
        for(int i = 0; i < Board.boardSize; i++) {

            // create seed string
            String row = "";

            // loop through data array, adding X or O to seed string depending on wether space is occupied
            // with space to seperate
            for(int j = 0; j < Board.boardSize; j++) {

                String scoreString = "" + scoreBoard[i ][j];

                scoreString = scoreString + ((scoreString.length() == 1) ? " " : "");

                row = row + scoreString + " ";

            }

            // add completed string to array
            rows[i] = row;

        }

        // print out all the rows
        for(int i = 0; i < Board.boardSize; i++) {

            System.out.println(rows[i]);

        }

    }

}