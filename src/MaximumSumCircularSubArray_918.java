import java.util.ArrayDeque;
import java.util.Deque;

public class MaximumSumCircularSubArray_918 {

    public static int longestSubarray(int[] nums, int limit) {
        int ret = 0;
        int start = 0;
        Deque<Integer> maxQue = new ArrayDeque<>();
        Deque<Integer> minQue = new ArrayDeque<>();
        maxQue.add(0);
        minQue.add(0);

        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            while (!minQue.isEmpty() && num < nums[minQue.getLast()]) minQue.removeLast();
            while (!maxQue.isEmpty() && num > nums[maxQue.getLast()]) maxQue.removeLast();
            minQue.add(i);
            maxQue.add(i);

            int min = nums[minQue.getFirst()];
            int max = nums[maxQue.getFirst()];

            while (Math.abs(min - num) > limit || Math.abs(max - num) > limit) {
                if (start == minQue.getFirst()) {
                    minQue.removeFirst();
                    min = nums[minQue.getFirst()];
                }

                if (start == maxQue.getFirst()) {
                    maxQue.removeFirst();
                    max = nums[maxQue.getFirst()];
                }
                start++;
            }

            int windowSize = i - start + 1;
            ret = Math.max(ret, windowSize);
        }
        return ret;
    }


    public static void main(String[] args) {
        int ret = longestSubarray(new int[]{10, 1, 2, 4, 7, 2}, 5);
        System.out.println(ret);
    }
}
