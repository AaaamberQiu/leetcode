package Grind75.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeIntervals_56 {

    public int[][] merge(int[][] intervals) {
        // sort by start
        Arrays.sort(intervals, (a,b) -> a[0] - b[0]);

        List<int[]> list = new ArrayList<>();
        int start = intervals[0][0], end = intervals[0][1];
        for(int i = 1; i<=intervals.length; ){
            while(i < intervals.length && end >= intervals[i][0]){
                end = Math.max(end, intervals[i][1]);
                i++;
            }
            list.add(new int[]{start, end});
            if(i < intervals.length){
                start = intervals[i][0];
                end = intervals[i][1];
                i++;
            }else{
                break;
            }
        }
        return convert(list);
    }

    public static int[][] convert(List<int[]> list){
        int[][] array = new int[list.size()][2];
        for(int i = 0; i<list.size(); i++){
            array[i] = list.get(i);
        }
        return array;
    }
}
