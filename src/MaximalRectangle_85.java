import java.util.Stack;

public class MaximalRectangle_85 {

    /**
     * 85. Maximal Rectangle
     * @param matrix
     * @return
     */
    public int maximalRectangle(char[][] matrix) {
        if(matrix.length == 0) return 0;

        int colNum = matrix[0].length, rowNum = matrix.length;
        int[] height = new int[colNum];
        int ret = 0;

        for(int i = 0; i<rowNum; i++){
            // for each row, calculate max rectangle
            for(int j = 0; j<colNum; j++){
                height[j] = matrix[i][j] == '0' ? 0 : height[j] + 1;
            }
            int rowMax = getMaxRectangle(height);
            ret = Math.max(ret, rowMax);
        }
        return ret;
    }


    public int getMaxRectangle(int[] heights){
        Stack<Integer> monoStack = new Stack<>();
        int max = 0;
        for(int i = 0; i<= heights.length; i++){
            int h = i == heights.length ? 0 : heights[i];
            while(!monoStack.isEmpty() && h < heights[monoStack.peek()]){
                int recHeight = heights[monoStack.pop()];
                // (firstLeftIndex, firstRightIndex) both exclusive
                int leftIndex = monoStack.isEmpty() ? -1 : monoStack.peek();
                int recWidth = i - leftIndex - 1;
                max = Math.max(max, recHeight * recWidth);
            }
            monoStack.push(i);
        }
        return max;
    }

    public static void main(String[] args) {
        char[][] matrix = new char[2][2];
        matrix[0] = new char[]{'0', '1'};
        matrix[1] = new char[]{'1', '0'};

        MaximalRectangle_85 solution = new MaximalRectangle_85();
        int ret = solution.maximalRectangle(matrix);
        System.out.println(ret);
    }
}
