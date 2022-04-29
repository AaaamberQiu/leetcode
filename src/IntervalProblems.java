import java.util.*;

public class IntervalProblems {

    /**
     * 435. Non-overlapping Intervals
     * <p>
     *     classic greedy question
     *     sort intervals by end and schedule non-overlapping intervals
     * </p>
     * @param intervals
     * @return
     */
    public static int eraseOverlapIntervals(int[][] intervals) {
        if(intervals.length <=1) return 0;
        Arrays.sort(intervals, (x,y) -> Integer.compare(x[0], y[0]));

        int count = 1;
        int lastEnd = intervals[0][1];
        for(int i = 1; i< intervals.length; i++){
            int[] interval = intervals[i];
            if(interval[0] >= lastEnd){
                count++;
                lastEnd = interval[1];
            }
        }
        return intervals.length - count;
    }


    /**
     * 56. Merge Intervals
     * <p>
     *     intervals must sort by start
     * </p>
     * @param intervals
     * @return
     */
    public static int[][] merge(int[][] intervals) {
        if(intervals.length <=1) return intervals;
        Arrays.sort(intervals, (x,y) -> Integer.compare(x[0], y[0]));

        List<int[]> ret = new ArrayList<>();
        int start = intervals[0][0], end = intervals[0][1];
        for(int i = 1; i< intervals.length; i++){
            int[] interval = intervals[i];
            // keep merging until no overlapping
            if(interval[0] <= end){
                start = Math.min(start, interval[0]);
                end = Math.max(end, interval[1]);
            }else{
                ret.add(new int[]{start, end});
                start = interval[0];
                end = interval[1];
            }
        }
        ret.add(new int[]{start, end});
        int[][] finalInterval = new int[ret.size()][2];
        for(int i = 0; i<finalInterval.length; i++){
            finalInterval[i] = ret.get(i);
        }
        return finalInterval;
    }



    /**
     * 452. Minimum Number of Arrows to Burst Balloons
     *
     * <p>
     *     In a word, it doesn't matter where we shoot.
     *     As a greedy problem, the guideline here is that we can shoot as many balloons as possible
     *     when we shoot the current balloon. Shooting at the end or the start is decided by how we sort the array.
     *     And if we don't do the sorting, it's not guaranteed to start from the leftmost/rightmost balloon,
     *     which dangers our sub-optimal assumption.
     * </p>
     *
     * @param points
     * @return
     */
    public int findMinArrowShots(int[][] points) {
        int n = points.length;
        if(n == 0) return 0;

        // sort by end pos, we need to shoot the rightmost
        // Not overriding compare() by return x[1]-y[1], it will lead to stackoverflow in the worst case
        Arrays.sort(points, (x, y) -> Integer.compare(x[1], y[1]));

        int arrowPos = points[0][1];
        int count = 1;
        for(int i = 1; i<n; i++){
            if(arrowPos >= points[i][0]){
                continue;
            }
            count ++;
            // try to get rightmost position
            arrowPos = points[i][1];
        }
        return count;
    }


    // sort by start, traverse from the end, get leftmost position
    public int findMinArrowShots2(int[][] points) {
        int n = points.length;
        if(n == 0) return 0;

        // sort by start pos in ASC
        Arrays.sort(points, (x, y) -> Integer.compare(x[0], y[0]));

        int arrowPos = points[n-1][0];
        int count = 1;
        for(int i = n-2; i>=0; i--){
            if(points[i][1] >= arrowPos){
                continue;
            }
            count++;
            arrowPos = points[i][0];
        }
        return count;
    }


    /**
     * 354. Russian Doll Envelopes
     * @param envelopes
     * @return
     */
    public static int maxEnvelopes(int[][] envelopes) {
        if(envelopes.length <=1) return envelopes.length;

        // sort by width, if width is same, sort by height in DESC order!!
        // sorting in width reduces the problem by one dimension, If width is strictly increasing, the problem is equivalent to finding LIS in only the height dimension
        Arrays.sort(envelopes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0] != o2[0]) return o1[0] - o2[0];
                // since [3,3] cannot fit [3,4]
                // so we need to put [3, 4] before [3, 3] when sorting otherwise it will be counted as an increasing number if the order is [3, 3], [3, 4]
                else return o2[1] - o1[1];
            }
        });

        int ret = 0;
        // dp[i] means size of longest increasing subsequence ending at i
        int[] dp = new int[envelopes.length];
        for(int i = 0; i<envelopes.length; i++){
            dp[i] = 1;
            for(int j = 0; j<i; j++){
                if(envelopes[i][0] < envelopes[j][0] && envelopes[i][1] < envelopes[j][1]){
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
            ret = Math.max(ret, dp[i]);
        }
        return ret;
    }


    // fix TLE
    public static int maxEnvelopes2(int[][] envelopes) {
        if(envelopes.length <=1) return envelopes.length;

        // sort by width, if width is same, sort by height in DESC order!!
        // sorting in width reduces the problem by one dimension, If width is strictly increasing, the problem is equivalent to finding LIS in only the height dimension
        Arrays.sort(envelopes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0] != o2[0]) return o1[0] - o2[0];
                    // since [3,3] cannot fit [3,4]
                    // so we need to put [3, 4] before [3, 3] when sorting otherwise it will be counted as an increasing number if the order is [3, 3], [3, 4]
                else return o2[1] - o1[1];
            }
        });

        // dp[i] means the smallest integer that ends an increasing sequence of len i
        int[] dp = new int[envelopes.length];
        int pos = 0;
        for(int i = 0; i<envelopes.length; i++){
            int val = envelopes[i][1];
            // find a LIS that could end with val
            int left = 0, right = pos;
            while(left < right){
                int mid = left + (right - left)/2;
                if(dp[mid] < val) left = mid+1;
                else right = mid;
            }
            dp[left] = val;
            if(left == pos) pos++;

        }
        return pos;
    }


    /**
     * 436. Find Right Interval
     * @param intervals
     * @return
     */
    public int[] findRightInterval(int[][] intervals) {
        // 既要排序，又要记录index -> TreeMap
        // key = interval.start, val = index
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        int size = intervals.length;
        for(int i = 0; i<size; i++) treeMap.put(intervals[i][0], i);

        int[] ret = new int[size];
        for(int i = 0; i<size; i++){
            Integer nearestStart = treeMap.ceilingKey(intervals[i][1]);
            ret[i] = nearestStart == null ? -1: treeMap.get(nearestStart);
        }
        return ret;
    }


    public static void main(String[] args) {
        int[][] envelopes = new int[4][2];
        envelopes[0] = new int[]{5,4};
        envelopes[1] = new int[]{6,4};
        envelopes[2] = new int[]{6,7};
        envelopes[3] = new int[]{2,3};
        int ret = maxEnvelopes(envelopes);
        System.out.println(ret);

    }


}
