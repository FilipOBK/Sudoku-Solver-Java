public class SudokuSolver {

    private static final int GRID_SIZE = 9;

    public static void main(String[] args) {
        
        int[][] board = {
            {7, 0, 2, 0, 5, 0, 6, 0, 0},
            {0, 0, 0, 0, 0, 3, 0, 0, 0},
            {1, 0, 0, 0, 0, 9, 5, 0, 0},
            {8, 0, 0, 0, 0, 0, 0, 9, 0},
            {0, 4, 3, 0, 0, 0, 7, 5, 0},
            {0, 9, 0, 0, 0, 0, 0, 0, 8},
            {0, 0, 9, 7, 0, 0, 0, 0, 5},
            {0, 0, 0, 2, 0, 0, 0, 0, 0},
            {0, 0, 7, 0, 4, 0, 2, 0, 3}
        };

        if(solveBoard(board)) {
            System.out.println("Solved board!");
        } else {
            System.out.println("Unsolvable board :(");
        }

        printBoard(board);
    }

    private static void printBoard(int[][] board) {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                System.out.print(board[i][j]);
                System.out.print(' ');
            }
            System.out.println("");
        }
    }

    private static boolean isNumberInRow(int[][] board, int number, int row) {
        for (int i = 0; i < GRID_SIZE; i++) {
            if(board[row][i] == number) return true;
        }
        return false;
    }
    private static boolean isNumberInCol(int[][] board, int number, int col) {
        for (int i = 0; i < GRID_SIZE; i++) {
            if(board[i][col] == number) return true;
        }
        return false;
    }
    private static boolean isNumberInBox(int[][] board, int number, int row, int col) {
        // Top left corner
        int localBoxRow = row - row % 3;
        int localBoxCol = col - col % 3;

        for(int i = localBoxRow; i < localBoxRow + 3; i++) {
            for(int j = localBoxCol; j < localBoxCol + 3; j++) {
                if(board[i][j] == number) return true;
            }
        }
        return false;
    }
    private static boolean isValidPlacement(int[][] board, int num, int row, int col) {
        return (!isNumberInRow(board, num, row) && 
                !isNumberInCol(board, num, col) &&
                !isNumberInBox(board, num, row, col));
    }

    private static boolean solveBoard(int[][] board) {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                if(board[row][col] == 0) {
                    for(int numToTry = 1; numToTry <= 9; numToTry++) {
                        if(isValidPlacement(board, numToTry, row, col)) {
                            board[row][col] = numToTry;

                            if(solveBoard(board)) {
                                return true;
                            } else {
                                board[row][col] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
}