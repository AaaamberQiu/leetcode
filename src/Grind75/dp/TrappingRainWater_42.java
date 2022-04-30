package Grind75.dp;

public class TrappingRainWater_42 {

    public int trap(int[] height) {
        if(height.length == 1) return 0;
        int total = 0;
        int n = height.length;

        int[] rightMax = new int[n];
        for(int i = n-2; i>=0; i--){
            rightMax[i] = Math.max(rightMax[i+1], height[i+1]);
        }

        int leftMax = height[0];
        for(int i = 1; i<n-1; i++){
            int h = Math.min(leftMax, rightMax[i]);
            if(h > height[i]) total += h - height[i];
            leftMax = Math.max(leftMax, height[i]);
        }

        return total;
    }

    public static void main(String[] args) {
        TrappingRainWater_42 solution = new TrappingRainWater_42();
        int[] arr = {0,1,0,2,1,0,1,3,2,1,2,1};
        int res = solution.trap(arr);
        System.out.println(res);
    }

}
