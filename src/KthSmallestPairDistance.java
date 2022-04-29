import java.util.*;

public class KthSmallestPairDistance {

    /**
     * 719. Find K-th Smallest Pair Distance
     * <p>
     *     bucket sort
     *     heap
     *     binary search
     * </p>
     * @param nums
     * @param k
     * @return
     */
    public int smallestDistancePair(int[] nums, int k) {
        // find max and min to get bucket num
        int max = nums[0], min = nums[0];
        for(int i = 1; i<nums.length; i++){
            max = Math.max(max, nums[i]);
            min = Math.min(min, nums[i]);
        }
        int maxDistance = max - min;
        int[] buckets = new int[maxDistance+1];
        // put distance into buckets
        for(int i = 0; i<nums.length-1; i++){
            for(int j = i+1; j<nums.length; j++){
                int distance = Math.abs(nums[i] - nums[j]);
                buckets[distance] += 1;
            }
        }

        // find kth smallest bucket from bucket0
        int i = 0;
        for(; i<buckets.length; i++){
            if(k <= 0) break;
            k -= buckets[i];
        }
        return i-1;
    }


    // max heap -> TLE
    public int smallestDistancePair2(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });
        for(int i = 0; i<nums.length-1; i++){
            for(int j = i+1; j<nums.length; j++){
                int distance = Math.abs(nums[i] - nums[j]);
                queue.offer(distance);
                // keep (k+1) in heap => remove (n-k-1) smaller nums
                if(queue.size() > k) queue.poll();
            }
        }
        return queue.poll();
    }


    public int smallestDistancePair3(int[] nums, int k) {
        Arrays.sort(nums);
        int minDistance = nums[1] - nums[0];
        for(int i = 1; i<nums.length; i++){
            minDistance = Math.min(minDistance, nums[i] - nums[i-1]);
        }
        int low = minDistance, high = nums[nums.length-1] - nums[0];

        while(low < high){
            int mid = (high-low)/2 + low;
            int count = count(nums, mid);
            if(count < k) low = mid+1;
            else high = mid;
        }
        return low;
    }

    public static int count(int[] sorted, int val){
        // return num of pairs with distance <= mid
        // j不是每一轮都从1开始非常重要！！j在上一轮的时候 sorted[j] - sorted[i-1] > val
        // sorted[i] > sorted[i-1], [1,j-1]显然满足 sorted[j-1] - sorted[i], 所以从j自己往下查就好
        int ret = 0, j = 1;
        for(int i = 0; i<sorted.length; i++){
            while(j < sorted.length && sorted[j]-sorted[i] <= val) j++;
            ret += j - i -1; // (i, j-1] => [i+1, j-1] => (j-1)-(i+1)+1
        }
        return ret;
    }


    public static void main(String[] args) {
        KthSmallestPairDistance solution = new KthSmallestPairDistance();
        int ret = solution.smallestDistancePair3(new int[]{1,6,1}, 3);
        System.out.println(ret);
    }


}
