import java.util.Stack;

public class LargestRectangleInHistogram {

    /**
     * 84. Largest Rectangle in Histogram
     * monotonic stack
     * @param heights
     * @return
     */
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        if(n == 1) return heights[0];

        Stack<Integer> stack = new Stack<>();
        int ret = 0;
        for(int i = 0; i<= n; i++){
            int h = i == n ? 0 : heights[i];
            while(!stack.isEmpty() && h < heights[stack.peek()]){
                int curr = stack.pop();
                int prevIndex = stack.isEmpty() ? -1 : stack.peek();
                int area = heights[curr] * (i - prevIndex - 1);
                ret = Math.max(ret, area);
            }

            stack.push(i);
        }
        return ret;
    }


    // DP
    public int largestRectangleArea2(int[] heights) {
        if(heights == null || heights.length == 0) return 0;

        int n = heights.length;
        // lessFromLeft[i] stores the index on the left that the nearest to i and less than height[i]
        int[] lessFromLeft = new int[n];
        int[] lessFromRight = new int[n];

        lessFromLeft[0] = -1;
        for(int i = 1; i < n; i++){
            int start = i - 1;
            while(start >= 0 && heights[start] >= heights[i]) start = lessFromLeft[start];
            lessFromLeft[i] = start;
        }

        lessFromRight[n-1] = n;
        for(int i = n-2; i>=0; i--){
            int start = i+1;
            while(start < n && heights[start] >= heights[i]) start = lessFromRight[start];
            lessFromRight[i] = start;
        }

        int ret = 0;
        for(int i = 0; i<heights.length; i++){
            ret = Math.max(ret, heights[i] * (lessFromRight[i] - lessFromLeft[i] - 1));
        }
        return ret;
    }

}
