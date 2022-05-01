package Grind75.dfs;

import java.util.ArrayList;
import java.util.List;

public class Subsets_78 {

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ret = new ArrayList<>();
        helper(ret, new ArrayList<>(), nums, 0);
        return ret;
    }

    public static void helper(List<List<Integer>> ret, List<Integer> temp, int[] nums, int start){
        ret.add(new ArrayList<>(temp));

        for(int i = start; i<nums.length; i++){
            temp.add(nums[i]);
            helper(ret, temp, nums, i+1);
            temp.remove(temp.size()-1);
        }
    }


    public static void main(String[] args) {
        Subsets_78 solution = new Subsets_78();
        int[] nums = {1,2,3};
        solution.subsets(nums);
    }
}
