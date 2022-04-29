import java.util.HashSet;
import java.util.Set;

public class LongestConsecutiveSeq {

    /**
     * 128. Longest Consecutive Sequence
     * <p>
     * Similar to union find -> find the largest connected component
     * </p>
     *
     * @param nums
     * @return
     */
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        Set<Integer> set = new HashSet<>();
        for (int num : nums) set.add(num);
        int max = 1;
        for (int n : nums) {
            if (set.remove(n)) {
                int left = n - 1, right = n + 1;
                int temp = 1;
                while (set.remove(left)) left--;
                while (set.remove(right)) right++;
                // both sides exclusive
                temp += n - left - 1;
                temp += right - n - 1;

                max = Math.max(max, temp);
            }
        }
        return max;
    }
}
