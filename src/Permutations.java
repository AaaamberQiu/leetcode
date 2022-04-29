import java.util.ArrayList;
import java.util.List;

/**
 * Permutation means all elements should exist in the combo,
 * that is, it never has a start point to limit the possible elements
 */
public class Permutations {

    /**
     * 46. Permutations
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ret = new ArrayList<>();
        helper(nums, new ArrayList<>(), ret);
        return ret;
    }

    public static void helper(int[] nums, List<Integer> temp, List<List<Integer>> ret){
        if(temp.size() == nums.length){
            ret.add(new ArrayList<>(temp));
            return;
        }

        for(int n : nums){
            // avoid same element using many times
            if(temp.contains(n)) continue;
            temp.add(n);
            helper(nums, temp, ret);
            temp.remove(temp.size()-1);
        }
    }


    /**
     * 47. Permutations II
     * @param nums
     * @return
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> ret = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        helper2(nums, used, new ArrayList<>(), ret);
        return ret;
    }


    public static void helper2(int[] nums, boolean[] used, List<Integer> temp, List<List<Integer>> ret){
        if(temp.size() == nums.length){
            ret.add(new ArrayList<>(temp));
            return;
        }

        for(int i = 0; i<nums.length; i++){
            // avoid same element using many times
            if(used[i]) continue;
            temp.add(nums[i]);
            used[i] = true;
            helper2(nums, used, temp, ret);
            temp.remove(temp.size()-1);
            used[i] = false;
            // once permutation start with nums[i] is completed, skip the following duplicates to ensure unique combo
            while(i+1 < nums.length && nums[i+1] == nums[i]) i++;
        }
    }
}
