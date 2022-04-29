import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MajorityElement {

    /**
     * 229. Majority Element II
     *
     * @param nums
     * @return
     */
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> ret = new ArrayList<>();
        if(nums.length <= 2){
            for(int n: nums){
                if(!ret.contains(n)) ret.add(n);
            }
            return ret;
        }

        // 必须必须是 num1 nums2 = nums[0]，一个count=0 一个是1
        // 如果分别取nums[0], nums[1]，在这两个相等的时候，一定过不了test，因为相当于count被分散了，本来可以是candidate，分散之后就不是了
        int count1 = 1, num1 = nums[0], count2 = 0, num2 = nums[0];
        for(int i = 1; i<nums.length; i++){
            int n = nums[i];
            if(n == num1) count1++;
            else if(n == num2) count2++;
            else if (count1 == 0){
                num1 = n;
                count1 = 1;
            }else if(count2 == 0){
                num2 = n;
                count2 = 1;
            }else{
                count1--;
                count2--;
            }
        }
        // find 2 candidates, but need to re-check
        count1=0;
        count2=0;
        for(int n : nums){
            if(n == num1) count1++;
            else if(n == num2) count2++;
        }
        int bond = (int)Math.floor(nums.length/3.0);
        if(count1 > bond) ret.add(num1);
        if(count2 > bond) ret.add(num2);
        return ret;
    }

    public static void main(String[] args) {
        MajorityElement solution = new MajorityElement();
        solution.majorityElement(new int[]{2,2,1,3});
    }
}
