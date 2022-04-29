import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Subsets {

    /**
     * 78. Subsets
     * <p>
     *     nums are unique
     *
     * </p>
     * @param nums
     * @return
     */
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ret = new ArrayList<>();
        helper(0, nums, new ArrayList<>(), ret);
        return ret;
    }

    public static void helper(int pos, int[] nums, List<Integer> temp, List<List<Integer>> ret){
        ret.add(new ArrayList<>(temp));
        for(int i = pos; i<nums.length; i++){
            temp.add(nums[i]);
            helper(i+1, nums, temp, ret);
            temp.remove(temp.size()-1);
        }
    }

    /**
     * 90. Subsets II
     * <p>
     *     contain duplicate elements => sort + skip to ensure unique
     * </p>
     * @param nums
     * @return
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> ret = new ArrayList<>();
        Arrays.sort(nums);
        helper2(0, nums, new ArrayList<>(), ret);
        return ret;
    }


    public static void helper2(int pos, int[] nums, List<Integer> temp, List<List<Integer>> ret){
        ret.add(new ArrayList<>(temp));
        for(int i = pos; i<nums.length; i++){
            temp.add(nums[i]);
            helper2(i+1, nums, temp, ret);
            temp.remove(temp.size()-1);
            while(i+1 < nums.length && nums[i+1] == nums[i]) i++;
        }
    }

    public static void main(String[] args) {
        subsets(new int[]{1,2,3});
    }
}
