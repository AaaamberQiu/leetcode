import java.util.*;

public class SudokuSolver {

    /**
     * 36. Valid Sudoku
     *
     * @param board
     * @return
     */
    public boolean isValidSudoku(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    char c = board[i][j];
                    if (!isValid(board, i, j, c)) return false;
                }
            }
        }
        return true;
    }


    /**
     * 37. Sudoku Solver
     *
     * @param board
     */
    public static void solveSudoku(char[][] board) {
        solve(board);
    }

    public static boolean solve(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    for (char c = '1'; c <= '9'; c++) {
                        // if c could be used, continue filling
                        if (isValid(board, i, j, c)) {
                            // fill it as c
                            board[i][j] = c;
                            // keep filling
                            if (solve(board)) {
                                return true;
                            } else {
                                board[i][j] = '.';
                            }
                        }
                    }
                    // try all options but no return true, then false
                    return false;
                }
            }
        }
        return true;
    }


    public static boolean isValid(char[][] board, int row, int col, char c) {
        for (int i = 0; i < 9; i++) {
            if(i == row) continue;
            if (board[i][col] == c) return false;
        }


        for (int j = 0; j < 9; j++) {
            if(j == col) continue;
            if (board[row][j] == c) return false;
        }

        int topLeftRow = row / 3 * 3;
        int topLeftCol = col / 3 * 3;
        for (int i = topLeftRow; i < topLeftRow + 3; i++) {
            for (int j = topLeftCol; j < topLeftCol + 3; j++) {
                if(i == row && j == col) continue;
                if (board[i][j] == c) return false;
            }
        }
        return true;
    }
}
