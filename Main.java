public class Main {

    public static void main(String[] args) {

        test();

        //run();

    }

    public static void test() {

        Board board = new Board();

        AI test = new AI();

        board.placeShip(5, true, 0, 0);
/*
        board.play(0, 0);

        board.play(1, 0);

        board.play(0, 1);
*/
        board.printBoard();

        int[] aiMove = test.findMove(board);

        System.out.println(Game.numToCha(aiMove[0]) + ", " + (aiMove[1] + 1));

    }

    public static void run() {

        Game game = new Game();

        game.run();

    }

}