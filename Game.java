import java.util.Scanner;
import java.util.Random;

public class Game {

    Scanner input = new Scanner(System.in);

    Board board = new Board();    

    Random randomizer = new Random();

    // blank constructor
    public Game() {}

    public void run() {

        placeRandomShips();

        // primary game loop
        while(!board.detectVictory()) {

            // space out from previous loop
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");

            board.printBoard();

            System.out.println("Input your move");

            String rawInput = input.nextLine();

            // check that input format is correct, after this loop, the input is definitely a correctly formatted move
            while(!isValidInput(rawInput)) {

                System.out.println("Invalid format, please input your move");

                rawInput = input.nextLine();

            }

            // split the string
            String[] splitStrings = rawInput.split("");

            // convert the strings to numbers and characters;
            char row = splitStrings[0].charAt(0);

            String numString = splitStrings[1] + ((splitStrings.length == 3) ? splitStrings[2]:"");

            int column = Integer.parseInt(numString);

            // check that the move is valid, and once it is, play it
            while(!board.play(charToNum(row), column - 1)) {

                System.out.println("Invalid, your move was either out of bounds or already played, choose again");

                rawInput = input.nextLine();

                // checking again to make sure the input is formatted right
                while(!isValidInput(rawInput)) {

                    System.out.println("Invalid, please input your move in the format: row,column");
    
                    rawInput = input.nextLine();
    
                }

                splitStrings = rawInput.split("");

                row = splitStrings[0].charAt(0);
                numString = splitStrings[1] + ((splitStrings.length == 3) ? splitStrings[2]:"");

                column = Integer.parseInt(numString);

            }

        }

        board.printBoard();

        System.out.println("you won!");

    }

    public boolean isValidInput(String input) {

        input = input.toLowerCase();

        boolean isValid = false;

        // for if the input is like a1
        if(input.length() == 2) {

            String[] splitStrings = input.split("");

            if(isLetter(splitStrings[0].charAt(0))) {

                try {

                    int num = Integer.parseInt(splitStrings[1]);

                    isValid = true;

                } catch (Exception e) {}

            }

        }

        // for if the input is like a10
        if(input.length() == 3) {

            String numString = "" + (char)input.charAt(1) + (char)input.charAt(2);

            if(isLetter(input.charAt(0))) {

                try {

                    int num = Integer.parseInt(numString);

                    isValid = true;

                } catch (Exception e) {}

            }

        }

        return isValid;

    }

    public void placeRandomShips() {

        // record of which ships have been placed, in order:
        // 5 ship, 4 ship, 3 ship, 3 ship, 2 ship
        boolean[] shipsPlaced = {false, false, false, false, false};
        int[] lengths = {5, 4, 3, 3, 2};

        //int exampleInt = randomizer.nextInt(max + 1) + min;

        for(int i = 0; i < shipsPlaced.length; i++) {

            while(!shipsPlaced[i]) {

                boolean isVertical = randomizer.nextInt(2) == 0;

                int row = randomizer.nextInt(board.boardSize);
                int column = randomizer.nextInt(board.boardSize);

                while(!board.placeShip(lengths[i], isVertical, row, column)) {

                    isVertical = randomizer.nextInt(2) == 0;

                    row = randomizer.nextInt(8);
                    column = randomizer.nextInt(8);

                }

                shipsPlaced[i] = true;

            }

        }

    }

    public boolean isLetter(char cha) {

        char[] letters = new char[board.boardSize];

        for(int i = 0; i < board.boardSize; i++) {

            letters[i] = numToCha(i);

        }

        boolean isLetter = false;

        for(int i = 0; i < letters.length; i++) {

            if (cha == letters[i]) {

                isLetter = true;

            }

        }

        return isLetter;

    }

    public static int charToNum(char cha) {

        int num = (int)(cha) - 97;

        return num;

    }

    public static char numToCha(int num) {

        char cha = (char)(num + 97);

        return cha;

    }

}