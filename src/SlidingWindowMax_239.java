import java.util.ArrayDeque;
import java.util.Deque;

public class SlidingWindowMax_239 {

    /**
     * 239. Sliding Window Maximum
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] ret = new int[nums.length - k + 1];
        int index = 0;

        // use a dequeue to represent window
        Deque<Integer> queue = new ArrayDeque<>();
        for(int i = 0; i<nums.length; i++){
            // Before we push an element into the deque, we first pop everything smaller than it out of the deque.
            // keep it as monotonic dequeue
            while(!queue.isEmpty() && nums[i] > nums[queue.getLast()]){
                queue.removeLast();
            }
            queue.addLast(i);

            // remove the first element if it is outside the window
            // since it is a decreasing queue, once the first element is removed, the second is the largest
            if(queue.getFirst() == i - k) queue.removeFirst();
            if(i >= k-1) ret[index++] = nums[queue.getFirst()];
        }
        return ret;
    }
}
