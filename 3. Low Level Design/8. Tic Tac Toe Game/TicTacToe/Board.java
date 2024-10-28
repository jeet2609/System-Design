package TicTacToe;

public class Board {
    public static Board instance;
    private final int size;
    private final char[][] grid;
    private int movesCount;

    private Board() {
        size = 3;
        grid = new char[size][size];
        initializeBoard();
    }

    public static Board getInstance() {
        if(instance == null) {
            instance = new Board();
        }
        return instance;
    }

    private void initializeBoard() {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                grid[row][col] = '-';
            }
        }
        movesCount = 0;
    }

    public synchronized void markMove(int row, int col, char symbol) {
        if(row < 0 || row >= size || col < 0 || col >= size || grid[row][col] != '-') {
            throw new IllegalArgumentException("\nInvalid move!\n");
        }

        grid[row][col] = symbol;
        movesCount++;
    }

    public boolean isFull() {
        return movesCount == size * size;
    }

    public boolean hasWinner() {
        // Check rows
        for(int row = 0; row < size; row++) {
            if(grid[row][0] != '-' && (grid[row][0] == grid[row][1]) && (grid[row][1] == grid[row][2])) {
                return true;
            }
        }

        // Check columns
        for(int col = 0; col < size; col++) {
            if(grid[0][col] != '-' && (grid[0][col] == grid[1][col]) && (grid[1][col] == grid[2][col])) {
                return true;
            }
        }

        // Check diagonal
        if(grid[0][0] != '-' && (grid[0][0] == grid[1][1]) && (grid[1][1] == grid[2][2])) {
            return true;
        }
        
        // Check anti-diagonal
        if(grid[0][2] != '-' && (grid[0][2] == grid[1][1]) && (grid[1][1] == grid[2][0])) {
            return true;
        }

        return false;
    }

    public void printBoard() {
        System.out.println();

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                System.out.print(grid[row][col] + " ");
            }
            System.out.println();
        }
        
        System.out.println();
    }
}
