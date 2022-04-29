import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class FindKPairsWithSmallestSums {

    /**
     * 373. Find K Pairs with Smallest Sums
     * @param nums1
     * @param nums2
     * @param k
     * @return
     */
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> ret = new ArrayList<>();
        PriorityQueue<int[]> heap = new PriorityQueue<>((a,b) -> nums1[a[0]]+nums2[a[1]] - (nums1[b[0]] + nums2[b[1]]));

        for(int i = 0; i<nums1.length; i++){
            heap.add(new int[]{i, 0});
        }

        while(k > 0 && !heap.isEmpty()){
            int[] top = heap.poll();
            List<Integer> list = new ArrayList<>();
            list.add(top[0]);
            list.add(top[1]);
            if(top[1]+1 < nums2.length) heap.add(new int[]{top[0], top[1]+1});
        }
        return ret;
    }

    public static void main(String[] args) {
        FindKPairsWithSmallestSums solution = new FindKPairsWithSmallestSums();
        solution.kSmallestPairs(new int[]{1,1,2}, new int[]{1,2,3}, 10);
    }
}
