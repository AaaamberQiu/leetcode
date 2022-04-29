import java.util.*;

public class KdiffPairsInAnArray {

    /**
     * 532. K-diff Pairs in an Array
     * <p>
     *     sort + binary search
     *     hashmap
     * </p>
     * @param nums
     * @param k
     * @return
     */
    public static int findPairs(int[] nums, int k) {
        Arrays.sort(nums);
        int count = 0;

        for(int i = 0; i<nums.length; i++){
            if(i > 0 && nums[i] == nums[i-1]) continue;
            int num = nums[i];
            int target = num + k;
            int start = i+1, end = nums.length-1;
            while(start <= end){
                int mid = (end - start)/ 2+ start;
                if(nums[mid] == target){
                    count += 1;
                    break;
                }else if(nums[mid] < target) start = mid+1;
                else end = mid-1;
            }
        }
        return count;
    }


    // hash map： 注意k=0的特殊情况
    public static int findPairs2(int[] nums, int k) {
        Map<Integer, Set<Integer>> map = new HashMap<>();

        for(int i = 0; i<nums.length; i++){
            map.computeIfAbsent(nums[i], key -> new HashSet<>());
            map.get(nums[i]).add(i);
        }

        int count = 0;
        if(k == 0){
            count = (int)map.values().stream().filter(x -> x.size() >=2).count();
        }else{
            for(int num: map.keySet()){
                if(map.containsKey(num + k)) count += 1;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int ret = findPairs(new int[]{1,3,1,5,4}, 0);
        System.out.println(ret);
    }

}
