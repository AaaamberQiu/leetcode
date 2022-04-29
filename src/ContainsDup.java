import java.util.*;

public class ContainsDup {

    /**
     * 217. Contains Duplicate
     */
    public boolean containsDuplicate(int[] nums) {
        return nums.length < Arrays.stream(nums).distinct().count();
    }


    /**
     * 219. Contains Duplicate II
     * hashmap
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            int n = nums[i];
            if(map.containsKey(n)){
                if(Math.abs(i - map.get(n)) <= k){
                    return true;
                }
            }
            map.put(n, i);
        }
        return false;
    }


    /**
     * 220. Contains Duplicate III
     * <p>
     *     bucket sort: O(n), put nums into bucket whose size is t
     *     tree set: O(nlogn), put recent k nums in tree set
     * </p>
     */
    public static boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        TreeSet<Long> set = new TreeSet<>();
        set.add((long)nums[0]);
        for(int i = 1; i < nums.length; i++){
            // ensure set size <= k
            // remove O(logn)
            if(i - k > 0) set.remove((long)nums[i-k-1]);
            int n = nums[i];
            long lowerBound = (long)n - t;
            long upperBound = (long)n + t;
            // subset() O(logn)
            if(!set.subSet(lowerBound, upperBound+1).isEmpty()) return true;
            set.add((long)nums[i]);
        }
        return false;
    }


    // bucket sort
    // If two numbers the diff <= t, then
    // 1) they are in the same bucket, or 2) they are in the adjacent buckets and diff <= t.
    // Only keep k entries in map
    public static boolean containsNearbyAlmostDuplicate2(int[] nums, int k, int t) {
        Map<Long, Long> buckets = new HashMap<>();
        // add 1 to handle k = 0
        int bucketSize = t + 1;
        for(int i = 0; i<nums.length; i++){
            long num = (long)nums[i];
            long bucketId = getBucketId(num, bucketSize);
            if(buckets.containsKey(bucketId)) return true;
            if(buckets.containsKey(bucketId-1) && (num - buckets.get(bucketId-1)) <= t) return true;
            if(buckets.containsKey(bucketId+1) && (buckets.get(bucketId+1) - num) <= t) return true;
            buckets.put(bucketId, num);

            // when bucket size = k, i <= k-1
            if(buckets.size() > k){
                buckets.remove(getBucketId(nums[i-k], bucketSize));
            }

        }
        return false;
    }

    public static long getBucketId(long num, int size){
        long ret = num/size;
        // If num < 0, bucketId start from -1,-2,... instead of 0, -1,-2,...
        if(num < 0) ret -=1;
        return ret;
    }


    public static void main(String[] args) {
        boolean res = containsNearbyAlmostDuplicate2(new int[]{1,5,9,1,5,9}, 2, 3);
        System.out.println(res);
    }





}
