package Grind75.stack;

import java.util.Stack;

public class LargestRecInHistogram_84 {

    // monotonic stack
    public static int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();

        int max = 0;
        for(int i = 0; i<=heights.length; i++){
            int h = i == heights.length ? -1 : heights[i];
            while(!stack.isEmpty() && h < heights[stack.peek()]){
                int currH = heights[stack.pop()];
                int rightBound = i; // exclusive
                int leftBound = stack.peek() + 1;
                max = Math.max(max, currH * (rightBound - leftBound));
            }
            stack.push(i);
        }
        return max;
    }

    // pre-compute
    public static int largestRectangleArea_dp(int[] heights) {
        int n = heights.length;

        int[] lessFromLeft = new int[n];
        int[] lessFromRight = new int[n];

        // 最左边小于height[i]的index
        for(int i = 1; i<n; i++){
            int start = i-1;
            while(start >= 0 && heights[start] >= heights[i]) start = lessFromLeft[start];
            lessFromLeft[i] = start;
        }

        // 最右边小于height[i]的index
        for(int i = n-2; i>=0; i--){
            int start = i+1;
            while(start < n && heights[start] >= heights[i]) start = lessFromRight[i];
            lessFromRight[i] = start;
        }

        int max = 0;
        for(int i = 0; i<n; i++){
            max = Math.max(max, heights[i] * (lessFromRight[i] - lessFromLeft[i] - 1));
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(largestRectangleArea(new int[]{4,2}));
    }
}
