package Grind75.binarySearch;

import java.util.*;

public class ThreeSum_15 {

    public static List<List<Integer>> threeSum(int[] nums) {
        Set<List<Integer>> ret = new HashSet<>();
        if(nums.length < 3) return Collections.emptyList();

        Arrays.sort(nums);
        if(nums[nums.length-1] < 0) return Collections.emptyList();
        for(int i = 0; i < nums.length-2; i++){
            int target = -nums[i];
            int start = i+1, end = nums.length-1;
            while(start < end){
                if(nums[start] + nums[end] == target){
                    ret.add(Arrays.asList(nums[i], nums[start], nums[end]));
                    while(start < end && nums[start] == nums[start+1]){
                        start+=1;
                    }
                    while(start < end && nums[end] == nums[end - 1]){
                        end -= 1;
                    }
                    start += 1;
                    end -=1;
                }else if (nums[start] + nums[end] < target){
                    start += 1;
                }else{
                    end -= 1;
                }
            }
        }
        return new ArrayList<>(ret);
    }

    public static void main(String[] args) {
        int[] arr = {-2,0,1,1,2};
        List<List<Integer>> ret = threeSum(arr);
        System.out.println();
    }
}
