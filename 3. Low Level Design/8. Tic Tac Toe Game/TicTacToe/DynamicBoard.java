package TicTacToe;

public class DynamicBoard {
    private static DynamicBoard instance;
    private final int size;
    private final char[][] grid;
    private int movesCount;

    private DynamicBoard(int size) {
        this.size = size;
        grid = new char[size][size];
        initializeBoard();
    }

    public static DynamicBoard getInstance(int size) {
        if(instance == null) {
            instance = new DynamicBoard(size);
        }
        return instance;
    }

    public static DynamicBoard getInstance() {
        if(instance == null) {
            throw new IllegalStateException("Board not initialized. Call getInstance(size) first.");
        }
        return instance;
    }

    public void resetBoard() {
        initializeBoard();
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
        return checkRow() || checkColumn() || checkDiagonal() || checkAntiDiagonal();
    }

    private boolean checkRow() {
        char symbol = grid[0][0];

        if(symbol == '-') {
            return false;
        }

        for(int row = 0; row < size ; row++) {
            boolean allSame = true;
            for(int col = 0; col < size; col++) {
                if(grid[row][col] != symbol) {
                    allSame = false;
                    break;
                }
            }

            if(allSame) {
                return true;
            }
        }

        return false;
    }

    private boolean checkColumn() {
        char symbol = grid[0][0];

        if(symbol == '-') {
            return false;
        }

        for(int col = 0; col < size ; col++) {
            boolean allSame = true;
            for(int row = 0; row < size; row++) {
                if(grid[row][col] != symbol) {
                    allSame = false;
                    break;
                }
            }

            if(allSame) {
                return true;
            }
        }

        return false;
    }

    private boolean checkDiagonal() {
        char symbol = grid[0][0];

        if(symbol == '-') {
            return false;
        }

        for(int i = 0; i < size; i++) {
            if(grid[i][i] != symbol) {
                return false;
            }
        }

        return true;
    }

    private boolean checkAntiDiagonal() {
        char symbol = grid[0][size - 1];

        if(symbol == '-') {
            return false;
        }

        for(int i = 0; i < size; i++) {
            if(grid[i][size - i - 1] != symbol) {
                return false;
            }
        }

        return true;
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

    // Getters
    public int getSize() { return size; }
}
