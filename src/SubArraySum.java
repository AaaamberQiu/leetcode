import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class SubArraySum {

    /**
     * 560. Subarray Sum Equals K
     *
     * 利用prefix sum转成 two sum (prefixSum[i] - prefixSum[j] = k)
     * 进一步可以on the fly 计算prefixSum
     */
    public static int subarraySum(int[] nums, int k) {
        if(nums.length == 1){
            return nums[0] == k ? 1 : 0;
        }
        int[] prefixSum = new int[nums.length + 1];
        prefixSum[0] = 0;

        for(int i = 0; i<nums.length; i++){
            prefixSum[i+1] = prefixSum[i] + nums[i];
        }

        int count = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i<prefixSum.length; i++){
            if(map.containsKey(prefixSum[i] - k)){
                count += map.get(prefixSum[i] - k);
            }
            map.put(prefixSum[i], map.getOrDefault(prefixSum[i], 0) + 1);
        }
        return count;
    }


    public static int subarraySum2(int[] nums, int k) {
        if(nums.length == 1){
            return nums[0] == k ? 1 : 0;
        }

        int count = 0;
        int sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        // similar to prefixSum[0]=0
        map.put(0, 1);
        for(int i = 0; i<nums.length; i++){
            sum += nums[i];
            if(map.containsKey(sum - k)){
                count += map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }


    /**
     * 523. Continuous Subarray Sum
     * 类似560，但是要求是 prefixSum[i] - prefixSum[j] = nk && at least two elements
     * map 存reminder是看看之前是不是有prefix sum=reminder, 这样这个区间的sum就可以被k整除
     */
    public static boolean checkSubarraySum(int[] nums, int k) {
        // key = reminder, val = index
        Map<Integer, Integer> map = new HashMap<>();
        // 0 is always a multiple of k
        map.put(0, -1);
        int sum = 0;
        for(int i = 0; i<nums.length; i++){
            sum += nums[i];
            int reminder = sum % k;
            if(map.containsKey(reminder)){
                if(i - map.get(reminder) > 1){
                    return true;
                }
            }
            map.putIfAbsent(reminder, i);
        }
        return false;
    }


    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int currSum = nums[0], ret = nums[0];
        for(int i = 1; i<n; i++){
            currSum = Math.max(currSum + nums[i], nums[i]);
            ret = Math.max(ret, currSum);
        }
        return ret;
    }


    /**
     * 209. Minimum Size Subarray Sum
     * <p>
     *     two pointer + sliding window
     *     prefix sum + binary search
     * </p>
     * @param target
     * @param nums
     * @return
     */
    public int minSubArrayLen(int target, int[] nums) {
        int n = nums.length;
        int start = 0, sum = 0;
        int ret = n+1;
        for(int end = 0; end < n; end++) {
            sum += nums[end];
            while (sum >= target && start <= end) {
                ret = Math.min(ret, end - start + 1);
                sum -= nums[start++];
            }
        }
        return ret == n+1? 0:ret;
    }

    // prefix sum + binary search
    public int minSubArrayLen2(int target, int[] nums) {
        int n = nums.length;
        int[] prefixSum = new int[n+1];
        for(int i = 1; i<=n; i++){
            prefixSum[i] = prefixSum[i-1] + nums[i-1];
        }
        int ret = n+1;
        for(int start = 0; start<prefixSum.length-1; start++){
            // find a num=prefixSum[start] + target in [start+1, prefixSum.len-1]
            int end = binarySearch(start+1, prefixSum.length-1,prefixSum[start] + target, prefixSum);
            if(end == prefixSum.length) break;
            System.out.printf("[%d, %d]\n", start, end);
            ret = Math.min(ret, end - start);
        }
        return ret == n+1? 0:ret;
    }


    private static int binarySearch(int low, int high, int target, int[] prefixSum){
        while(low <= high){
            int mid = (high - low)/2 + low;
            if(prefixSum[mid] >= target) high = mid-1;
            else low = mid+1;
        }
        return low;
    }

    public static void main(String[] args) {
        SubArraySum solution = new SubArraySum();
        int ret = solution.minSubArrayLen2(7, new int[]{2,3,1,2,4,3});
        System.out.println(ret);
    }


}
