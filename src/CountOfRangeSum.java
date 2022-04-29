public class CountOfRangeSum {

    /**
     * 327. Count of Range Sum
     * <p>
     *     advanced merge sort -> similar to count of smaller nums
     *     binary index tree
     *
     * </p>
     * @param nums
     * @param lower
     * @param upper
     * @return
     */
    public int countRangeSum(int[] nums, int lower, int upper) {
        int len = nums.length;
        long[] prefixSum = new long[len + 1];
        for (int i = 0; i < len; i++) {
            prefixSum[i + 1] = prefixSum[i] + (long)nums[i];
        }
        return mergeAndCount(prefixSum, 0, len, lower, upper);

    }

    public static int mergeAndCount(long[] prefixSum, int start, int end, int lower, int upper) {
        if (start >= end) return 0;
        int mid = (end - start) / 2 + start;

        int count = mergeAndCount(prefixSum, start, mid, lower, upper)
                + mergeAndCount(prefixSum, mid + 1, end, lower, upper);


        long[] cache = new long[end - start + 1];
        int left = start, right = mid + 1, index = 0;
        int pointer1 = mid + 1, pointer2 = mid + 1;
        // in this loop: complete both merge and count
        while (left <= mid) {
            // sum[i, [p1, p2)] satisfy the condition
            // why not refresh p1 and p2 for next i? if sum[i+1, [p1, p2)] also satisfy conditions, it is the sub-range of sum[i, [p1,p2)]
            while (pointer1 <= end && prefixSum[pointer1] - prefixSum[left] < lower) pointer1++;
            while (pointer2 <= end && prefixSum[pointer2] - prefixSum[left] <= upper) pointer2++;
            while(right <= end && prefixSum[right] < prefixSum[left]) cache[index++] = prefixSum[right++];

            count += pointer2 - pointer1;
            cache[index++] = prefixSum[left++];
        }

        // only copy cache[0 - (start-right)], this part is modified and need to update to prefixSum
        System.arraycopy(cache, 0, prefixSum, start, right-start);
        return count;
    }


    public static void main(String[] args) {
        CountOfRangeSum solution = new CountOfRangeSum();
        int res = solution.countRangeSum(new int[]{2147483647,-2147483648,-1,0}, -1, 0);
        System.out.println(res);
    }
}
