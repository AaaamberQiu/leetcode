import java.util.*;

public class CombinationSum {

    /**
     * 39. Combination Sum
     * <p>
     *     1) distinct candidates
     *     2) unique combinations
     *     3) same candidate can be used in an unlimited times
     * </p>
     * @param candidates
     * @param target
     * @return
     */
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ret = new ArrayList<>();
        if(candidates.length == 0) return ret;
        Set<List<Integer>> combo = new HashSet<>();
        helper(0, candidates, target, new ArrayList<>(), combo);
        ret.addAll(combo);
        return ret;

    }

    public static void helper(int pos, int[] candidates, int target, List<Integer> temp, Set<List<Integer>> ret){
        if(target == 0){
            Collections.sort(temp);
            ret.add(new ArrayList<>(temp));
            return;
        }
        if(target < 0) return;
        for(int i = pos; i< candidates.length; i++){
            temp.add(candidates[i]);
            helper(i, candidates, target-candidates[i], temp, ret);
            temp.remove(temp.size()-1);
        }
    }


    /**
     * 40. Combination Sum II
     * <p>
     *     1) unique combinations => sort + if(i > pos && candidates[i] == candidates[i-1]) continue;
     *     2) Each number in candidates may only be used once in the combination => next pos start from i+1
     * </p>
     * @param candidates
     * @param target
     * @return
     */
    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> ret = new ArrayList<>();
        if(candidates.length == 0) return ret;
        helper2(0, candidates, target, new ArrayList<>(), ret);
        return ret;
    }


    public static void helper2(int pos, int[] candidates, int target, List<Integer> temp, List<List<Integer>> ret){
        if(target == 0){
            ret.add(new ArrayList<>(temp));
            return;
        }
        if(target < 0) return;
        for(int i = pos; i< candidates.length; i++){
            if(i > pos && candidates[i] == candidates[i-1]) continue;
            temp.add(candidates[i]);
            helper2(i+1, candidates, target-candidates[i], temp, ret);
            temp.remove(temp.size()-1);
        }
    }


    /**
     * 216. Combination Sum III
     * <p>
     *     1) Each number is used at most once. => next pos start from i+1
     * </p>
     * @param k
     * @param n
     * @return
     */
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> ret = new ArrayList<>();
        helper3(1, n, new ArrayList<>(), ret, k);
        return ret;
    }


    public static void helper3(int start, int target, List<Integer> temp, List<List<Integer>> ret, int count){
        if(target == 0 && count == 0){
            ret.add(new ArrayList<>(temp));
            return;
        }
        if(count == 0 || target < 0) return;

        for(int i = start; i<=9; i++){
            temp.add(i);
            helper3(i+1, target-i, temp, ret, count-1);
            temp.remove(temp.size()-1);
        }
    }

    /**
     * 377. Combination Sum IV
     * @param nums
     * @param target
     * @return
     */
    int count = 0;
    public int combinationSum4(int[] nums, int target) {
        helper4(nums, target);
        return count;
    }

    public void helper4(int[] nums, int target){
        if(target == 0){
            count += 1;
            return;
        }
        if(target < 0) return;
        for(int n : nums){
            helper4(nums, target-n);
        }
    }

    // recursion is TLE, it is actually coin change
    public int combinationSum4DP(int[] nums, int target){
        // dp[i] means the num of combo which sum is i
        int[] dp = new int[target+1];
        dp[0] = 1;
        for(int i = 1; i <= target; i++){
            for(int j = 0; j<i; j++){
                if(i - nums[j] >= 0) dp[i] += dp[i -nums[j]];
            }
        }
        return dp[target];
    }




    public static void main(String[] args) {
        combinationSum2(new int[]{2,5,2,1,2}, 5);
    }
}
