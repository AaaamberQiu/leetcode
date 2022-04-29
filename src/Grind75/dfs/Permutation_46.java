package Grind75.dfs;

import java.util.ArrayList;
import java.util.List;

public class Permutation_46 {

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

        for(int i = 0; i<nums.length; i++){
            if(temp.contains(nums[i])) continue;
            temp.add(nums[i]);
            helper(nums, temp, ret);
            temp.remove(temp.size()-1);
        }
    }
}
