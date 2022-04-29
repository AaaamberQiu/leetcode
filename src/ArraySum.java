import java.util.*;

public class ArraySum {

    /**
     * 1. Two Sum
     * map to reduce time
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        int[] ret = new int[2];
        for(int i = 0; i<nums.length; i++){
            int rest = target - nums[i];
            if(map.containsKey(rest)){
                ret[1] = i;
                ret[0] = map.get(rest);
                return ret;
            }
            map.put(nums[i], i);
        }
        return ret;
    }


    /**
     * 167. Two Sum II - Input Array Is Sorted
     * two pointers (binary search)
     */
    public static int[] twoSumV2(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length -1;
        while(left < right){
            if(numbers[left] + numbers[right] == target){
                return new int[]{left + 1, right + 1};
            }else if(numbers[left] + numbers[right] < target){
                left++;
            }else{
                right--;
            }
        }
        return new int[2];
    }


    /**
     * 15. 3Sum
     * Sort + two pointers (binary search)
     */
    public List<List<Integer>> threeSum(int[] nums) {
        if(nums == null || nums.length < 3){
            return Collections.emptyList();
        }
        Set<List<Integer>> ret = new HashSet<>();
        Arrays.sort(nums);
        for(int i = 0; i< nums.length-2; i++){
            int target = -nums[i];
            int left = i+1;
            int right = nums.length-1;
            if(nums[right] < 0){
                return Collections.emptyList();
            }

            while(left < right){
                if(nums[left] + nums[right] == target){
                    ret.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while (left < right && nums[left] == nums[left + 1]){
                        left += 1;
                    }
                    while (left < right && nums[right] == nums[right -1]){
                        right -= 1;
                    }

                    left++;
                    right--;
                }else if(nums[left] + nums[right] < target) {
                    left += 1;
                }else{
                    right -= 1;
                }
            }
        }
        return new ArrayList<>(ret);
    }



    /**
     * 16. 3Sum Closest
     * sort + two pointers (binary search)
     */
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int ret = nums[0] + nums[1] + nums[2];
        for(int i = 0; i<nums.length-2; i++){
            int left = i + 1;
            int right = nums.length-1;
            while(left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if(Math.abs(target-sum) < Math.abs(target-ret)){
                    ret = sum;
                }
                if(sum > target){
                    right--;
                }else{
                    left++;
                }
            }
        }
        return ret;
    }


    /**
     * 18. 4Sum
     * <p>
     *     unique quadruplets -> sort
     * </p>
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        return kSum(nums, target, 0, 4);
    }

    public static List<List<Integer>> kSum(int[] sorted, int target, int start, int k){
        int n = sorted.length;
        List<List<Integer>> ret = new ArrayList<>();
        // base case
        if(k == 2){
            int left = start, right = n-1;
            while(left < right){
                int sum = sorted[left] + sorted[right];
                if(sum == target){
                    List<Integer> solution = new ArrayList<>();
                    solution.add(sorted[left]);
                    solution.add(sorted[right]);
                    ret.add(solution);
                    // ensure the set is unique
                    while(left + 1 < n && sorted[left] == sorted[left+1]) left++;
                    while(right -1 >= 0 && sorted[right] == sorted[right-1]) right--;
                    // not break => get all possible combinations
                }
                else if(sum < target) left++;
                else right--;
            }
        }else{
            // the end is at most (n-k) since n - i >= k
            for(int i = start; i<=n-k; i++){
                if(i > start && sorted[i] == sorted[i-1]) continue;
                List<List<Integer>> sub = kSum(sorted, target-sorted[i], i+1, k-1);
                for(List<Integer> solution: sub){
                    solution.add(sorted[i]);
                }
                ret.addAll(sub);
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        ArraySum solution = new ArraySum();
        List<List<Integer>> res = solution.fourSum(new int[]{0,0,0,0}, 0);
        System.out.println();
    }
}
