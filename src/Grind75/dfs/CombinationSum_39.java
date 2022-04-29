package Grind75.dfs;

import java.util.ArrayList;
import java.util.List;

public class CombinationSum_39 {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ret = new ArrayList<>();
        helper(ret, new ArrayList<>(), target, candidates, 0);
        return ret;
    }

    public void helper(List<List<Integer>> ret, List<Integer> temp, int target, int[] candidates, int start){
        if(target < 0) return;
        if(target == 0){
            ret.add(new ArrayList<>(temp));
            return;
        }

        for(int i = start; i<candidates.length; i++){
            temp.add(candidates[i]);
            helper(ret, temp, target - candidates[i], candidates, i);
            temp.remove(temp.size()-1);
        }
    }


}
