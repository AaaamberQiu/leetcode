package Grind75.array;

public class MajorityElement_169 {

    public int majorityElement(int[] nums) {
        int n = nums.length;
        if(n == 1) return nums[0];

        int candidate = nums[0], count = 1;
        for(int i = 1; i<n; i++){
            if(count == 0){
                candidate = nums[i];
                count = 1;
            }
            if(nums[i] == candidate) count += 1;
            else count -= 1;
        }
        return candidate;
    }
}
