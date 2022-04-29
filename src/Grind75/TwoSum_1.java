package Grind75;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TwoSum_1 {

    public int[] twoSum(int[] nums, int target) {
        int[] ret = new int[2];
        Map<Integer, List<Integer>> map = new HashMap<>();
        for(int i = 0; i<nums.length; i++){
            map.computeIfAbsent(nums[i], k -> new ArrayList<>());
            map.get(nums[i]).add(i);
        }


        for(int i = 0; i<nums.length; i++){
            int rest = target - nums[i];
            if(map.containsKey(rest) && map.get(rest).size() != 0){
                int index = -1;
                for(int candidate: map.get(rest)){
                    if(candidate != i){
                        index = candidate;
                        break;
                    }
                }
                if(index != -1) return new int[]{i, index};
            }
        }
        return ret;
    }
}
