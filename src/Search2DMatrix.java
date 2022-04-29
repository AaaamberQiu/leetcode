public class Search2DMatrix {


    /**
     * 74. Search a 2D Matrix
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrixI(int[][] matrix, int target) {
        int rowNum = matrix.length, colNum = matrix[0].length;
        int start = 0, end = rowNum*colNum-1;
        while(start <= end){
            int mid = (end - start)/2 + start;
            int midVal = matrix[mid/colNum][mid%colNum];
            if(midVal == target) return true;
            else if(midVal < target){
                start = mid+1;
            }else{
                end = mid-1;
            }
        }
        return false;
    }

    /**
     * 240. Search a 2D Matrix II
     * @param matrix
     * @param target
     * @return
     */
    // binary search: O(mlogn)
    public boolean searchMatrix(int[][] matrix, int target) {
        int rowNum = matrix.length, colNum = matrix[0].length;
        for(int i = 0; i< rowNum; i++){
            int[] row = matrix[i];
            if(target > row[colNum-1] || target < row[0]) continue;
            if(binarySearch(row, target, 0, matrix[i].length)) return true;
        }
        return false;
    }


    public static boolean binarySearch(int[] row, int target, int start, int end){
        if(start < row.length || end < 0 || start > end) return false;
        if(start == end) return row[start] == target;
        int mid = (end - start)/2 + start;
        if(row[mid] == target) return true;
        else if(row[mid] > target) return binarySearch(row, target, start, mid-1);
        else return binarySearch(row, target, mid+1, end);
    }


    // start from top-right corner, from right to left -> decrease, from top -> down increase
    // another kind of binary search O(m+n)
    public boolean searchMatrix2(int[][] matrix, int target) {
        int rowNum = matrix.length, colNum = matrix[0].length;
        int i = 0, j = colNum-1;
        while(i < rowNum && j >= 0){
            if(matrix[i][j] < target){
                i++;
            }else if(matrix[i][j] > target){
                j--;
            }else{
                return true;
            }
        }
        return false;
    }


    // divide and conquer
    public boolean searchMatrix3(int[][] matrix, int target) {
        int rowNum = matrix.length, colNum = matrix[0].length;
        return helper(matrix, 0, rowNum-1, 0, colNum-1, target);

    }

    public static boolean helper(int[][] matrix, int top, int bottom, int left, int right, int target){
        int rowNum = matrix.length, colNum = matrix[0].length;
        boolean valid = top >= 0 && top <= bottom && bottom < rowNum && left >= 0 && left <= right && right < colNum;
        if(!valid) return false;
        if(top == bottom && left == right) return matrix[top][left] == target;

        int rowMid = (bottom - top)/2 + top;
        int colMid = (right - left)/2 + left;
        if(matrix[rowMid][colMid] == target) return true;
        else if(matrix[rowMid][colMid] < target){
            // 取右边和左下，左下的右边界是colMid 不是colMid+1!!!!
            return helper(matrix, top, bottom, colMid+1, right, target)
                    || helper(matrix, rowMid+1, bottom, left, colMid, target);
        }else{
            // 取左边和右上, 右上的左边界是colMid 不是colMid-1
            return helper(matrix, top, bottom, left, colMid-1, target)
                    || helper(matrix, top, rowMid-1, colMid, right, target);
        }
    }



    public static void main(String[] args) {
        Search2DMatrix solution = new Search2DMatrix();
        int[][] matrix = new int[1][2];
        matrix[0] = new int[]{-1,3};
        System.out.println(solution.searchMatrix3(matrix, 3));
    }
}
