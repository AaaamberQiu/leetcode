public class SubArrayProduct {

    /**
     * 152. Maximum Product Subarray
     *
     * each nums[i] has two options: 1) append to the prev subarray; 2) start a new subarray
     * since nums has both postive and negative numbers, then the max subarray product could from
     * 1) maxProduct[0,i-1] * nums[i]; 2) minProduct[0,i-1] * nums[i]
     * @param nums
     * @return
     */
    public int maxProduct(int[] nums) {
        if(nums.length == 1) return nums[0];
        int max = nums[0], min = nums[0], ret = nums[0];
        for(int i = 1; i<nums.length; i++){
            int tempMax = max, tempMin = min;
            max = getMax(nums[i], tempMax*nums[i], tempMin*nums[i]);
            min = getMin(nums[i], tempMax*nums[i], tempMin*nums[i]);
            ret = Math.max(ret, max);
        }
        return ret;

    }

    private static int getMax(int a, int b, int c){
        return Math.max(a, Math.max(b,c));
    }

    private static int getMin(int a, int b, int c){
        return Math.min(a, Math.min(b,c));
    }


    /**
     * 713. Subarray Product Less Than K
     * @param nums
     * @param k
     * @return
     */
    public static int numSubarrayProductLessThanK(int[] nums, int k) {
        int count = 0;

        int start = 0, product = 1;
        for(int i = 0; i<nums.length; i++){
            product *= nums[i];
            while(start <= i && product >= k){
                product /= nums[start];
                start++;
            }
            // Each step introduces x new subarrays, where x is the size of the current window (j + 1 - i);
            // for window (5, 2), when 6 is introduced, it add 3 new subarray: (5, (2, (6)))
            count+= (i - start + 1);
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(numSubarrayProductLessThanK(new int[]{10,5,2,6}, 100));
    }
}
