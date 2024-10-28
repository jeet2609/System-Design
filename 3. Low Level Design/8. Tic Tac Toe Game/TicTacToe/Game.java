package TicTacToe;

import java.util.Scanner;

public class Game {
    private static Game instance;
    private final Player player1;
    private final Player player2;
    private final Board board;
    private Player currentPlayer;
    private final Scanner scanner;

    private Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        board = Board.getInstance();
        currentPlayer = player1;
        scanner = new Scanner(System.in);
    }

    public static Game getInstance(Player player1, Player player2) {
        if(instance == null) {
            instance = new Game(player1, player2);
        }
        return instance;
    }

    public static Game getInstance() {
        if(instance == null) {
            throw new IllegalStateException("Game not initialized. Call getInstance(player1, player2) first.");
        }
        return instance;
    }

    public void play() {
        board.printBoard();

        while (!board.isFull() && !board.hasWinner()) {
            System.out.println(currentPlayer.getName() + "'s turn:");
            int row = getValidInput("Enter row (1 - 3): ") - 1;         // -1 for index
            int col = getValidInput("Enter column (1 - 3): ") - 1;      // -1 for index

            try {
                board.markMove(row, col, currentPlayer.getSymbol());
                board.printBoard();
                switchPlayer();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        if(board.hasWinner()) {
            switchPlayer();
            System.out.println(currentPlayer.getName() + " wins!\n");
        } else {
            System.out.println("It's a draw!\n");
        }
    }

    private void switchPlayer() {
        currentPlayer = currentPlayer == player1 ? player2 : player1;
    }

    private int getValidInput(String message) {
        while(true) {
            System.out.print(message);
            if(scanner.hasNextInt()) {
                return scanner.nextInt();
            } else {
                scanner.next();
            }

            System.out.println("Invalid input! Please enter a number between 0 and 2.");
        }
    }
}
