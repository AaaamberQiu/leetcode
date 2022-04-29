import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NQueens {

    /**
     * 51. N-Queens
     * <p>
     *     1) conditions: no conflict in row/col/diagonals
     *     2) fill one pos and traverse whole board will lead to TLE;
     *     3) since each col can only have one 'Q', just fill column one by one
     *     4) it will be better to fill the cell AFTER it is valid
     * </p>
     * @param n
     * @return
     */
    public List<List<String>> solveNQueens(int n) {
        Set<List<String>> set = new HashSet<>();
        for(int i = 0; i<n; i++){
            for(int j = 0; j<n; j++){
                int[][] matrix = new int[n][n];
                matrix[i][j] = 'Q';
                helper(matrix, n-1, set);
            }
        }
        List<List<String>> ret = new ArrayList<>();
        ret.addAll(set);
        return ret;
    }


    public static void helper(int[][] matrix, int count, Set<List<String>> ret){
        int rowNum = matrix.length;
        int colNum = matrix[0].length;
        if(count == 0) {
            ret.add(convert(matrix));
            return;
        }

        for(int i = 0; i<rowNum; i++){
            for(int j = 0; j<colNum; j++){
                if(matrix[i][j] == 0){
                    if(isValid(matrix, i, j)){
                        matrix[i][j] = 'Q';
                        helper(matrix, count-1, ret);
                        matrix[i][j] = 0;
                    }
                }
            }
        }
    }

    // solve col by col
    public List<List<String>> solveNQueens2(int n) {
        List<List<String>> ret = new ArrayList<>();
        int[][] matrix = new int[n][n];
        helper2(matrix, 0, ret);
        return ret;
    }

    public static void helper2(int[][] matrix, int colIndex, List<List<String>> ret){
        int rowNum = matrix.length;
        int colNum = matrix[0].length;
        if(colIndex == colNum) {
            ret.add(convert(matrix));
            return;
        }

        for(int i = 0; i<rowNum; i++){
            if(isValid(matrix, i, colIndex)){
                matrix[i][colIndex] = 'Q';
                helper2(matrix, colIndex+1, ret);
                matrix[i][colIndex] = 0;
            }
        }
    }


    public static boolean isValid(int[][] matrix, int row, int col){
        int rowNum = matrix.length;
        int colNum = matrix[0].length;
        // no conflict in col, check row by row
        for(int i = 0; i<rowNum; i++){
            if(i == row) continue;
            if(matrix[i][col] == 'Q') return false;
        }

        // no conflict in row, check col by col
        for(int j = 0; j<colNum; j++){
            if(j == col) continue;
            if(matrix[row][j] == 'Q') return false;
        }

        // no conflict in diagonals
        for(int i = 0; i<rowNum; i++){
            for(int j = 0; j<colNum; j++){
                if(i == row && j == col) continue;
                if(Math.abs(i-row) == Math.abs(j-col) && matrix[i][j] == 'Q') return false;
            }
        }
        return true;
    }




    public static List<String> convert(int[][] matrix){
        List<String> str = new ArrayList<>();
        int rowNum = matrix.length;
        int colNum = matrix[0].length;
        for(int i = 0; i<rowNum; i++){
            StringBuilder sb = new StringBuilder();
            for(int j = 0; j<colNum; j++){
                if(matrix[i][j] == 'Q') sb.append("Q");
                else sb.append(".");
            }
            str.add(sb.toString());
        }
        return str;
    }


    /**
     * 52. N-Queens II
     * @param n
     * @return
     */
    int count = 0;
    public int totalNQueens(int n) {
        int[][] matrix = new int[n][n];
        helper3(matrix, 0);
        return count;
    }


    public void helper3(int[][] matrix, int colIndex){
        int rowNum = matrix.length, colNum = matrix[0].length;
        if(colIndex == colNum){
            count += 1;
            return;
        }

        for(int i = 0; i<rowNum; i++){
            if(isValid2(matrix, i, colIndex)){
                matrix[i][colIndex] = 'Q';
                helper3(matrix, colIndex+1);
                matrix[i][colIndex] = 0;
            }
        }
    }


    public static boolean isValid2(int[][] matrix, int row, int col){
        int rowNum = matrix.length;
        for(int i = 0; i<rowNum; i++){
            // only need to check filled part
            for(int j = 0; j<col; j++){
                if(matrix[i][j] == 'Q' && (i == row || Math.abs(i-row) == Math.abs(j-col))) return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        NQueens nQueens = new NQueens();
        nQueens.solveNQueens(4);
    }
}
