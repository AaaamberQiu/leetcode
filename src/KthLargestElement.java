import java.util.*;

public class KthLargestElement {

    /**
     * 215. Kth Largest Element in an Array
     * <p>
     * priority queue: O(nlogk)
     * <p>
     * quick select:
     * </p>
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {
        Queue<Integer> queue = new PriorityQueue<>();
        for (int n : nums) {
            queue.add(n);
            if (queue.size() > k) queue.poll();
        }
        return queue.poll();
    }


    // quick select
    public int findKthLargest2(int[] nums, int k) {
        // kth largest => find num in (k-1) index
        return quickSelect(nums, 0, nums.length - 1, k - 1);
    }

    public static int quickSelect(int[] nums, int start, int end, int k) {
        if (start > end) return Integer.MAX_VALUE;
        // select pivotIndex in random
        int pivotIndex = new Random().nextInt(end - start + 1) + start;
        int pivot = nums[pivotIndex];
        // put pivot at end
        swap(nums, pivotIndex, end);

        int pointer = start;
        for (int i = start; i < end; i++) {
            // put elements larger than pivot to left
            if (nums[i] > pivot) {
                swap(nums, pointer, i);
                pointer++;
            }
        }
        // move pivot to the position
        swap(nums, pointer, end);

        // always try to find element in (k-1) index
        if (pointer == k) return nums[pointer];
        else if (pointer < k) return quickSelect(nums, pointer + 1, end, k);
        else return quickSelect(nums, start, pointer - 1, k);
    }


    public static void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }


    /**
     * 1985. Find the Kth Largest Integer in the Array
     * <p>
     * minHeap: hold k elements in heap, return the top one
     * maxHeap: put all elements in heap, and polling k times, return top one
     * key points: how to compare string!!!
     * </p>
     *
     * @param nums
     * @param k
     * @return
     */
    public String kthLargestNumber(String[] nums, int k) {
        PriorityQueue<String> maxHeap = new PriorityQueue<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                // utilize lexi oder
                if (o1.length() == o2.length()) return o1.compareTo(o2);
                    // use Integer.compare
                else return Integer.compare(o1.length(), o2.length());
                // cannot use Integer.valueOf -> extreme large num
            }
        });

        for (String s : nums) {
            maxHeap.add(s);
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }
        return maxHeap.poll();
    }


    /**
     * 378. Kth Smallest Element in a Sorted Matrix
     * <p>
     * binary search
     * heap
     * </p>
     *
     * @param matrix
     * @param k
     * @return
     */
    public static int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2] - o2[2];
            }
        });

        // 每一行一列是sorted，不代表错位也是sorted，所以不能按行列遍历
        // init之后，每次放的应该是同位的下一行
        for (int col = 0; col < n; col++) {
            queue.offer(new int[]{0, col, matrix[0][col]});
        }
        for (int count = 0; count < k - 1; count++) {
            int[] top = queue.poll();
            int x = top[0], y = top[1];
            if (x == n - 1) continue;
            queue.offer(new int[]{x + 1, y, matrix[x + 1][y]});
        }
        return queue.poll()[2];
    }


    // binary search: try to find a mid that is kth smallest num => k nums <= mid
    public static int kthSmallest2(int[][] matrix, int k) {
        int n = matrix.length;
        int low = matrix[0][0], high = matrix[n - 1][n - 1];
        while (low < high) {
            int mid = (high - low) / 2 + low;
            int count = 0; // count nums <= mid

            for (int row = 0; row < n; row++) {
                int col = n - 1;
                while (col >= 0 && matrix[row][col] > mid) col--;
                count += (col + 1);
            }
            // low <= mid, if not use mid+1, it will drop in a loop
            if (count < k) low = mid + 1;
            else high = mid;
        }
        // both low and high are fine, since low == high here
        return low;
    }


    public static void main(String[] args) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        int k = 2;
        for(int i = 0; i<=10; i++){
            minHeap.offer(i);
            if(minHeap.size() > k){
                minHeap.poll();
            }
        }
        // print kth largest
        System.out.println(minHeap.poll());
    }
}
