package Grind75.array;

public class ContainerWithMostWater_11 {

    public int maxArea(int[] height) {
        int start = 0, end = height.length-1;
        int max = 0;
        while(start < end){
            int h = Math.min(height[start], height[end]);
            int w = (end - start + 1);
            max = Math.max(max, h*w);

            if(height[start] < height[end]){
                start++;
            }else{
                end--;
            }
        }
        return max;
    }
}
