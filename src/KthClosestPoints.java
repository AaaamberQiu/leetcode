import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

public class KthClosestPoints {

    /**
     * 973. K Closest Points to Origin
     * @param points
     * @param k
     * @return
     */
    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                long x1 = (long)o1[0], y1 = (long)o1[1];
                long x2 = (long)o2[0], y2 = (long)o2[1];
                return Long.compare(x2*x2 + y2*y2, x1*x1 + y1*y1);
            }
        });

        for(int[] point: points){
            queue.offer(point);
            if(queue.size() > k){
                queue.poll();
            }
        }

        int[][] ret = new int[k][2];
        while(k > 0){
            ret[--k] = queue.poll();
        }
        return ret;
    }


    // quick select
    public int[][] kClosest2(int[][] points, int k){
        // return the answer in any order -> quick select
        // choose a pivot, put all elements smaller on the left and other on the right
        int start = 0, end = points.length-1;
        while(start < end){
            int mid = quickSelect(start, end, points);
            if(mid == k) break;
            else if (mid < k) start = mid+1;
            else end = mid;
        }
        return Arrays.copyOfRange(points, 0, k);
    }


    public static int quickSelect(int start, int end, int[][] points){
        int[] pivot = points[start];
        while(start < end){
            while(start < end && compare(points[end], pivot) >= 0) end--;
            points[start] = points[end];
            while(start < end && compare(points[start], pivot) <= 0) start++;
            points[end] = points[start];
        }
        points[start] = pivot;
        return start;
    }


    public static int quickSelect2(int start, int end, int[][] points){
        int pivotIndex = new Random().nextInt(end - start + 1) + start;
        int[] pivot = points[pivotIndex];
        // put pivot into end
        swap(points, pivotIndex, end);
        int pointer = start;
        for(int i = start; i<end; i++){
            if(compare(points[i], pivot) > 0){
                swap(points, pointer, i);
                pointer++;
            }
        }
        swap(points, pointer, end);
        return pointer;
    }


    public static int compare(int[] p1, int[] p2){
        long x1 = (long)p1[0], y1 = (long)p1[1];
        long x2 = (long)p2[0], y2 = (long)p2[1];
        return Long.compare(x1*x1 + y1*y1, x2*x2 + y2*y2);
    }

    public static void swap(int[][] nums, int a, int b){
        int[]temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}
