public class SortedArray {

    /**
     * 34. Find First and Last Position of Element in Sorted Array
     * <p>
     *     left boundary BS and right boundary BS
     * </p>
     * @param nums
     * @param target
     * @return
     */
    // left boundary search and right boundary search
    public int[] searchRange(int[] nums, int target) {
        int[] ret = new int[]{-1,-1};
        if(nums.length == 0) return ret;

        int start = 0, end = nums.length -1;
        while(start < end){
            //  biased to the left
            int mid = start + (end - start)/2;
            // =留给else, if nums[mid] = nums[start] 不能start=mid+1
            if (nums[mid] < target) start = mid+1;
            else end = mid;
        }
        if(nums[start] != target) return ret;
        ret[0] = start;

        end = nums.length - 1;
        while(start < end){
            //  biased to the right
            int mid = start + (end - start)/2 + 1;
            if (nums[mid] > target) end = mid-1;
            else start = mid;
        }
        if(nums[end] != target) return ret;
        ret[1] = end;
        return ret;
    }


    /**
     * 540. Single Element in a Sorted Array
     * @param nums
     * @return
     */
    public static int singleNonDuplicate(int[] nums) {
        int n = nums.length;
        if(n == 1) return nums[0];

        int start = 0, end = n-1;
        while(start < end-1){
            int mid = start + (end - start)/2;
            // len[start, mid] is even
            if(mid %2 == 1){
                if(nums[mid] == nums[mid-1]) start = mid+1;
                else end = mid;
            }else{
                // mid+2 ！！
                if(nums[mid] == nums[mid+1]) start = mid+2;
                else end = mid;
            }
        }
        return nums[start];
    }

    public static void main(String[] args) {
        System.out.println(singleNonDuplicate(new int[]{1,1,2,3,3,4,4,8,8}));
    }

}
