import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.TreeSet;

public class SlidingWindowMedian_480 {

    /**
     * 480. Sliding Window Median
     * @param nums
     * @param k
     * @return
     */
    public static double[] medianSlidingWindow(int[] nums, int k) {
        double[] ret = new double[nums.length - k + 1];
        int start = 0;

        TreeSet<Integer> lower = new TreeSet<>((a,b) -> nums[a] == nums[b] ? a-b : Integer.compare(nums[a], nums[b]));
        TreeSet<Integer> higher = new TreeSet<>((a,b) -> nums[a] == nums[b] ? a-b :Integer.compare(nums[a], nums[b]));

        // 因为有dup num，不能直接记录num，要记index
        for(int i = 0; i<nums.length; i++){
            lower.add(i);
            higher.add(lower.pollLast());
            if(higher.size() > lower.size()) lower.add(higher.pollFirst());
            if(lower.size() + higher.size() == k){
                ret[start] = lower.size() == higher.size()?
                        nums[lower.last()]/2.0 + nums[higher.first()]/2.0 : nums[lower.last()];
                if(lower.contains(start)) lower.remove(start);
                else higher.remove(start);
                start++;
            }
        }
        return ret;
    }


    public static double getMedian(int[] nums, int start, int end){
        int len = end - start + 1;
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for(int i = start; i<=end; i++) minHeap.offer(nums[i]);

        // even length
        if(len % 2 == 0){
            int count = len/2 - 1;
            while(count > 0) {
                minHeap.poll();
                count--;
            }
            double left = minHeap.poll()/2.0;
            double right = minHeap.poll()/2.0;
            return left+right;
        }else{
            int count = len/2;
            while(count > 0) {
                minHeap.poll();
                count--;
            }
            return minHeap.poll();
        }
    }

    public static void main(String[] args) {
        medianSlidingWindow(new int[]{2147483647,2147483647}, 2);
    }
}
