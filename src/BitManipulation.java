import java.util.Arrays;

public class BitManipulation {

    /**
     * 190. Reverse Bits
     * @param n
     * @return
     */
    public int reverseBits(int n) {
        if(n == 0) return 0;
        int result = 0;
        for(int i = 0; i<32; i++){
            // 相当于result后面添0
            result = result << 1;
            // focus on rightmost bit
            if((n & 1) == 1){
                result += 1;
            }
            // 每次处理完之后，n右移一位，相当于丢掉刚刚处理的那个bit
            n = n >> 1;
        }
        return result;
    }


    /**
     * 268. Missing Number
     * <p>
     *     XOR: O(n)
     *     Binary search: O(nlogn)
     *     Sum: O(n) => may have stack overflow if using expected = (0+n)*n/2
     * </p>
     * @param nums
     * @return
     */
    public int missingNumber(int[] nums) {
        int ret = 0;
        for(int i = 0; i<nums.length; i++){
            ret ^= i;
            ret ^= nums[i];
        }
        return ret ^ nums.length;
    }


    public int missingNumber2(int[] nums) {
        Arrays.sort(nums);
        int left = 0, right = nums.length-1;
        while(left < right){
            int mid = (right - left)/2 + left;
            // the missing number in left part
            if(nums[mid] > mid) right = mid;
            else left = mid+1;
        }
        return left;
    }
}
