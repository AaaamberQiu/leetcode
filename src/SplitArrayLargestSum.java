public class SplitArrayLargestSum {

    /**
     * 410. Split Array Largest Sum
     * <p>
     *     binary search
     *     if m=nums.length, each subarray has one element -> largest sub array sum = largest num
     *     if m=1, all elements in one subarray -> largest sub array sum = sum(array)
     *     all largest subarray sum should be in [largest element, sum(array)]
     *     search in the range to find a possible largest subarray sum which total subarray count is m
     * </p>
     * @param nums
     * @param m
     * @return
     */
    public int splitArray(int[] nums, int m) {
        int start = -1, end = 0;
        for(int num: nums){
            start = Math.max(start, num);
            end += num;
        }

        while(start < end){
            int mid = (end - start)/2 + start;
            int pieces = split(nums, mid);
            if(pieces > m) start = mid+1;
            // if pieces = mid, it could be candidate, but we still try to find any smaller candidate
            else end = mid;
        }
        return start;
    }

    public int split(int[] nums, int maxSum){
        // at least, it should be one piece
        int pieces = 1;
        int temp = 0;
        for(int num: nums){
            if(temp + num <= maxSum){
                temp += num;
            }else{
                pieces += 1;
                temp = num;
            }
        }
        return pieces;
    }
}
