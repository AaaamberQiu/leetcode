package Grind75.matrix;

import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix_54 {

    private List<Integer> ret = new ArrayList<>();

    public List<Integer> spiralOrder(int[][] matrix) {
        if (matrix.length == 0) return ret;

        int m = matrix.length, n = matrix[0].length;

        int top = 0, bottom = m - 1, left = 0, right = n - 1;
        while (top <= bottom && left <= right) {
            addRow(matrix, top, left, right, false);
            top++;

            addCol(matrix, right, top, bottom, false);
            right--;

            if(top <= bottom){
                addRow(matrix, bottom, left, right, true);
                bottom--;
            }

            if(left <= right){
                addCol(matrix, left, top, bottom, true);
                left++;
            }
        }
        return ret;

    }

    private void addRow(int[][] matrix, int row, int start, int end, boolean reverse) {
        if (reverse) {
            for (int i = end; i >= start; i--) {
                ret.add(matrix[row][i]);
            }
        } else {
            for (int i = start; i <= end; i++) {
                ret.add(matrix[row][i]);
            }
        }
    }

    private void addCol(int[][] matrix, int col, int start, int end, boolean reverse) {
        if (reverse) {
            for (int i = end; i >= start; i--) {
                ret.add(matrix[i][col]);
            }
        } else {
            for (int i = start; i <= end; i++) {
                ret.add(matrix[i][col]);
            }
        }
    }

    public static void main(String[] args) {
        SpiralMatrix_54 solution = new SpiralMatrix_54();
        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        solution.spiralOrder(matrix);
    }

}
