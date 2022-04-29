public class MaxConsecutiveOnes {

    /**
     * 1004. Max Consecutive Ones III
     * @param nums
     * @param k
     * @return
     */
    public static int longestOnesIII(int[] nums, int k) {
        int start = 0, i = 0;
        for(; i<nums.length; i++){
            if(nums[i] == 0) k--;
            if(k < 0 && nums[start++] == 0) k++;
        }
        return i-start;
    }

    public static void main(String[] args) {
        int ret = longestOnesIII(new int[]{0,0,0,0,0}, 2);
        System.out.println(ret);
    }
}
