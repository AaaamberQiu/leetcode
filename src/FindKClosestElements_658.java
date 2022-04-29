import java.util.ArrayList;
import java.util.List;

public class FindKClosestElements_658 {

    /**
     * 658. Find K Closest Elements
     * @param arr
     * @param k
     * @param x
     * @return
     */
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int start = 0, end = arr.length - k;
        while(start < end){
            int mid = (end - start)/2 + start;
            // 这里本质上比较的是two windows
            // If x - A[mid] > A[mid + k] - x, it means A[mid + 1] ~ A[mid + k] is better than A[mid] ~ A[mid + k - 1]
            if(x - arr[mid] > arr[mid+k] - x) start = mid+1;
            else end = mid;
        }
        List<Integer> ret = new ArrayList<>();
        for(int i = 0; i < k; i++){
            ret.add(arr[start+i]);
        }
        return ret;
    }
}
