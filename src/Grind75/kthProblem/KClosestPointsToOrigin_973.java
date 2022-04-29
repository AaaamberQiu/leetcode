package Grind75.kthProblem;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class KClosestPointsToOrigin_973 {

    public int[][] kClosest(int[][] points, int k) {
        Queue<int[]> maxHeap = new PriorityQueue<>((a,b) -> (b[0]*b[0] + b[1]*b[1]) - (a[0]*a[0] + a[1]*a[1]));
        for(int[] point : points){
            maxHeap.offer(point);
            if(maxHeap.size() > k){
                maxHeap.poll();
            }
        }

        int[][] ret = new int[k][2];
        int i = 0;
        while(!maxHeap.isEmpty()){
            ret[i++] = maxHeap.poll();
        }
        return ret;
    }

    public int[][] kClosest_quick_select(int[][] points, int k) {
        int left = 0, right = points.length-1;
        while(left <= right){
            int pos = quickSelect(points, left, right);
            if(pos == k) break;
            else if(pos < k) left = pos+1;
            else right = pos-1;
        }
        return Arrays.copyOfRange(points, 0 , k);
    }

    public static int quickSelect(int[][] points, int left, int right){
        int[] pivot = points[left];
        swap(points, left, right);
        int p = left;
        for(int i = left; i<right; i++){
            if(compare(points[i], pivot) < 0){
                swap(points, p, i);
                p++;
            }
        }
        swap(points, p, right);
        return p;
    }

    private static int compare(int[] a, int[] b){
        return (a[0]*a[0] + a[1]*a[1]) - (b[0]*b[0] + b[1]*b[1]);
    }

    private static void swap(int[][] arr, int i, int j){
        int[] temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
