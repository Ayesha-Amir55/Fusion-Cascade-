
import java.util.Random;


class GameBoard {
    private static final int GRID_SIZE = 4;
    private int[][] board;
    private int score;

    public GameBoard() {
        board = new int[GRID_SIZE][GRID_SIZE];
        score = 0;
        initGame();
    }

    public int getScore() {
        return score;
    }

    public void initGame() {
        board = new int[GRID_SIZE][GRID_SIZE];  // Reset the board
        addRandomTile();
        addRandomTile();
    }

    // Add a random tile (2 or 4) at an empty position on the board
    public void addRandomTile() {
        Random random = new Random();
        int row, col;

        if (isFull()) return;

        do {
            row = random.nextInt(GRID_SIZE);
            col = random.nextInt(GRID_SIZE);
        } while (board[row][col] != 0);  // Ensure the spot is empty

        board[row][col] = (random.nextInt(10) < 9) ? 2 : 4;  // 90% chance for 2, 10% for 4
    }

    // Check if the board is full (no empty spaces)
    private boolean isFull() {
        for (int[] row : board) {
            for (int cell : row) {
                if (cell == 0) return false;
            }
        }
        return true;
    }

    // Move the tiles and handle merges for UP, DOWN, LEFT, RIGHT
    public void moveLeft() {
        for (int r = 0; r < GRID_SIZE; r++) {
            int[] row = board[r];
            mergeTiles(row);
        }
    }

    public void moveRight() {
        for (int r = 0; r < GRID_SIZE; r++) {
            int[] row = reverse(board[r]);
            mergeTiles(row);
            board[r] = reverse(row);
        }
    }

    public void moveUp() {
        for (int c = 0; c < GRID_SIZE; c++) {
            int[] col = new int[GRID_SIZE];
            for (int r = 0; r < GRID_SIZE; r++) {
                col[r] = board[r][c];
            }
            mergeTiles(col);
            for (int r = 0; r < GRID_SIZE; r++) {
                board[r][c] = col[r];
            }
        }
    }

    public void moveDown() {
        for (int c = 0; c < GRID_SIZE; c++) {
            int[] col = new int[GRID_SIZE];
            for (int r = 0; r < GRID_SIZE; r++) {
                col[r] = board[r][c];
            }
            col = reverse(col);
            mergeTiles(col);
            col = reverse(col);
            for (int r = 0; r < GRID_SIZE; r++) {
                board[r][c] = col[r];
            }
        }
    }

    // Merge tiles in the line (row or column)
    private void mergeTiles(int[] line) {
        for (int i = 0; i < GRID_SIZE - 1; i++) {
            if (line[i] == 0) continue;

            for (int j = i + 1; j < GRID_SIZE; j++) {
                if (line[j] == 0) continue;

                if (line[i] == line[j]) {
                    line[i] *= 2;  // Merge tiles
                    line[j] = 0;  // Clear the merged tile
                    score ++;  // Update score
                }
                break;  // Stop after one merge
            }
        }

        // Shift tiles to the left
        int[] newLine = new int[GRID_SIZE];
        int index = 0;
        for (int value : line) {
            if (value != 0) {
                newLine[index++] = value;
            }
        }

        System.arraycopy(newLine, 0, line, 0, GRID_SIZE);  // Update the original line
    }

    // Reverse the order of the array (used for right and down moves)
    private int[] reverse(int[] line) {
        int[] reversed = new int[GRID_SIZE];
        for (int i = 0; i < GRID_SIZE; i++) {
            reversed[i] = line[GRID_SIZE - 1 - i];
        }
        return reversed;
    }

    public int[][] getBoard() {
        return board;
    }

    public void resetGame() {
        score = 0;
        initGame();
    }

    public boolean canMove() {
        for (int r = 0; r < GRID_SIZE; r++) {
            for (int c = 0; c < GRID_SIZE; c++) {
                if (board[r][c] == 0) return true;
                if (r < GRID_SIZE - 1 && board[r][c] == board[r + 1][c]) return true;
                if (c < GRID_SIZE - 1 && board[r][c] == board[r][c + 1]) return true;
            }
        }
        return false;
    }
}
