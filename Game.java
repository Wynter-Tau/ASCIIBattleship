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

            System.out.println("Input your move in the format: row,column");

            String rawInput = input.nextLine();

            // check that input format is correct, after this loop, the input is definitely a correctly formatted move
            while(!isValidInput(rawInput)) {

                System.out.println("Invalid, please input your move in the format: row,column");

                rawInput = input.nextLine();

            }

            // split the string
            String[] numStrings = rawInput.split(",");

            // convert the strings to numbers
            int[] coordinates = {Integer.parseInt(numStrings[0]), Integer.parseInt(numStrings[1])};

            // check that the move is valid, and once it is, play it
            while(!board.play(coordinates[0], coordinates[1])) {

                System.out.println("Invalid, your move was either out of bounds or already played, choose again");

                rawInput = input.nextLine();

                // checking again to make sure the input is formatted right
                while(!isValidInput(rawInput)) {

                    System.out.println("Invalid, please input your move in the format: row,column");
    
                    rawInput = input.nextLine();
    
                }

                numStrings = rawInput.split(",");

                coordinates[0] = Integer.parseInt(numStrings[0]);
                coordinates[1] = Integer.parseInt(numStrings[1]);

            }

        }

        board.printBoard();

        System.out.println("you won!");

    }

    public boolean isValidInput(String input) {

        boolean isValid = false;

        if(input.length() == 3) {

            if(input.charAt(1) == ',') {

                String[] numStrings = input.split(",");

                try {

                    int num1 = Integer.parseInt(numStrings[0]);
                    int num2 = Integer.parseInt(numStrings[1]);

                    isValid = true;

                } catch(Exception e) {}

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

                int row = randomizer.nextInt(8);
                int column = randomizer.nextInt(8);

                while(!board.placeShip(lengths[i], isVertical, row, column)) {

                    isVertical = randomizer.nextInt(2) == 0;

                    row = randomizer.nextInt(8);
                    column = randomizer.nextInt(8);

                }

                shipsPlaced[i] = true;

            }

        }

    }

}