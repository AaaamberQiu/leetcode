public class ContainerWithMostWater_11 {

    /**
     * 11. Container With Most Water
     * 随着width的减少，下一个大的面积只可能在height更大的地方
     */
    public int maxArea(int[] height) {
        int maxArea = 0;
        int i = 0;
        int j = height.length - 1;
        while(i!=j){
            int area = (j-i) * Math.min(height[i], height[j]);
            maxArea = Math.max(maxArea, area);
            if(height[i] < height[j]){
                i++;
            }else{
                j--;
            }
        }
        return maxArea;
    }
}
