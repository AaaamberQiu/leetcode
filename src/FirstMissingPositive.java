public class FirstMissingPositive {

    /**
     * 41. First Missing Positive
     *
     * <p>
     *     Observation: the first missing positive must in [1,n]
     * </p>
     * @param nums
     * @return
     */
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;

        // mark out-of-range num as n+1
        for(int i = 0; i<n; i++){
            if(nums[i] <= 0 || nums[i] > n) nums[i] = n+1;
        }

        for(int i = 0; i<n; i++){
            // retrieve the actual value
            int num = Math.abs(nums[i]);
            // skip out-of-range num
            if(num > n) continue;

            // reflect num to index since index in [0,n-1]
            int index = num-1;
            // prevent double negative ops
            if(nums[index] > 0){
                nums[index] = -nums[index];
            }
        }

        for(int i = 0; i<n; i++){
            // if index has a negative num, it means that index+1 in the array
            if(nums[i] > 0) {
                return i+1;
            }
        }
        return n+1;
    }
}
