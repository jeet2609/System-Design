package TicTacToe;

import java.util.Scanner;

public class TicTacToeDemo {
    public static void main(String[] args) {
        Player player1 = new Player("Player 1", 'X');
        Player player2 = new Player("Player 2", 'O');

        // Game game = new Game(player1, player2);
        // game.play();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter size : ");
        int size = scanner.nextInt();
        DynamicGame dynamicGame = new DynamicGame(player1, player2, size);
        dynamicGame.play();
    }
}
