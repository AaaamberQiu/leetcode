public class FindPeakElement {


    /**
     * 162. Find Peak Element
     * @param nums
     * @return
     */
    public int findPeakElement(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = (right - left) / 2 + left;
            boolean largerThanLeft = mid == 0 || nums[mid] > nums[mid - 1];
            boolean largerThanRight = mid == nums.length - 1 || nums[mid] > nums[mid + 1];
            if (largerThanLeft && largerThanRight) return mid;
            else if (!largerThanLeft) right = mid - 1;
            else left = mid + 1;
        }
        return -1;
    }

    /**
     * 1901. Find a Peak Element II
     * <p>
     *     binary search：如果curr col max没有neighbor大，那么max in neighbor col must larger than its neighbor in current col
     * </p>
     * @param mat
     * @return
     */
    public int[] findPeakGrid(int[][] mat) {
        int colNum = mat[0].length;
        int start = 0, end = colNum-1;
        while(start <= end){
            int mid = (end - start)/2 + start;
            int maxRow = maxInCol(mid, mat);
            int val = mat[maxRow][mid];
            int left = mid > 0 ? mat[maxRow][mid-1] : -1;
            int right = mid < colNum-1 ? mat[maxRow][mid+1] : -1;
            if(val > left && val > right) return new int[]{maxRow, mid};
            else if (val < left){
                end = mid - 1;
            }else{
                start = mid + 1;
            }
        }
        return null;
    }

    public static int maxInCol(int col, int[][] mat){
        int max = mat[0][col];
        int maxRow = 0;
        for(int i = 1; i<mat.length; i++){
            if(max < mat[i][col]){
                max = mat[i][col];
                maxRow = i;
            }
        }
        return maxRow;
    }


    public static void main(String[] args) {

    }
}
