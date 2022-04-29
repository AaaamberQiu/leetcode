public class UnsortedSubArray {

    /**
     * 581. Shortest Unsorted Continuous Subarray
     * <p>
     *     [left_sorted][unsorted][right_sorted]
     *     max(left_sorted) < min(unsorted) < max(unsorted) < min(right_sorted)
     * </p>
     * @param nums
     * @return
     */
    public int findUnsortedSubarray(int[] nums) {
        int n = nums.length;
        if(n <= 1) return 0;

        int currMax = nums[0], currMin = nums[n-1];
        int start = -1, end = -1;
        for(int i = 1; i<n; i++){
            currMax = Math.max(currMax, nums[i]);
            currMin = Math.min(currMin, nums[n-1-i]);
            // if the array is fully sorted, it is expected that currMax == nums[i] and currMin == nums[n-i-1]
            // end records max(unsorted), start records min(unsorted)
            if(nums[i] < currMax){
                end = i;
            }
            if(nums[n-1-i] > currMin){
                start = n-1-i;
            }
        }

        if(start == -1 && end == -1) return 0;
        return end-start+1;
    }
}
