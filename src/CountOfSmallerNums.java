import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CountOfSmallerNums {

    /**
     * 315. Count of Smaller Numbers After Self
     * <p>
     *     advanced merge sort
     * </p>
     * @param nums
     * @return
     */
    public List<Integer> countSmaller(int[] nums) {
        int len = nums.length;
        // build numWithIndex array
        NumWithIndex[] array = new NumWithIndex[len];
        for(int i = 0; i<len; i++){
            array[i] = new NumWithIndex(nums[i], i);
        }

        int[] count = new int[len];
        mergeAndCount(array, 0, len-1, count);

        List<Integer> ret = new ArrayList<>();
        for(int c : count){
            ret.add(c);
        }
        return ret;
    }

    public static void mergeAndCount(NumWithIndex[] array, int start, int end, int[] count){
        if(start >= end) return;

        int mid = (end - start)/2 + start;
        mergeAndCount(array, start, mid, count);
        mergeAndCount(array, mid+1, end, count);

        List<NumWithIndex> merged = new ArrayList<>();
        int smallerInRightCount = 0;
        int lower = start, higher = mid+1;
        while(lower <= mid && higher <= end){
            if(array[lower].num > array[higher].num){
                // count the num unexpected (all nums in the left should be less than nums in right)
                smallerInRightCount += 1;
                merged.add(array[higher++]);
            }else{
                count[array[lower].index] += smallerInRightCount;
                merged.add(array[lower++]);
            }
        }

        while(lower <= mid){
            count[array[lower].index] += smallerInRightCount;
            merged.add(array[lower++]);
        }

        while(higher <= end){
            merged.add(array[higher++]);
        }

        // copy merged to array
        int pos = start;
        for(NumWithIndex item: merged){
            array[pos++] = item;
        }

    }


    class NumWithIndex{
        int num;
        int index;
        NumWithIndex(int num, int index){
            this.num = num;
            this.index = index;
        }
    }

    public static void main(String[] args) {
        CountOfSmallerNums solution = new CountOfSmallerNums();
        solution.countSmaller(new int[]{5,2,6,1});
    }
}
