package Grind75.array;

import java.util.ArrayList;
import java.util.List;

public class InsertInterval_57 {

    public int[][] insert(int[][] intervals, int[] newInterval) {
        int n = intervals.length;
        List<int[]> list = new ArrayList<>();

        int start = newInterval[0], end = newInterval[1];
        int i = 0;
        while(i < n && intervals[i][1] < start){
            list.add(intervals[i++]);
        }

        // has overlapping, start merge
        while(i < n && intervals[i][0] <= end){
            start = Math.min(start, intervals[i][0]);
            end = Math.max(end, intervals[i][1]);
            i++;
        }
        list.add(new int[]{start, end});

        while(i < n) list.add(intervals[i]);
        return convert(list);
    }

    private static int[][] convert(List<int[]> list){
        int[][] ret = new int[list.size()][list.get(0).length];
        for(int i = 0; i<ret.length; i++){
            ret[i] = list.get(i);
        }
        return ret;
    }

    public static void main(String[] args) {
        int[][] input = {{1,2}, {3,5}, {6,7}, {8,10}, {12, 16}};
        InsertInterval_57 solution = new InsertInterval_57();
        solution.insert(input, new int[]{4,8});
    }
}
