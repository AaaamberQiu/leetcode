public class SearchInRotatedSortedArray {

    /**
     * 33. Search in Rotated Sorted Array
     * <p>
     *     专注于确定的部分，看target是不是在 sorted part中
     * </p>
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        if(nums.length == 1) return nums[0] == target ? 0:-1;
        int left = 0, right = nums.length-1;
        while(left < right){
            int mid = left + (right - left)/2;
            if(nums[mid] == target) return mid;

            // left part is sorted
            if(nums[left] <= nums[mid]){
                if(target >= nums[left] && target < nums[mid]) right = mid-1;
                else left = mid+1;
            }else{
                // right part is sorted
                if(target <= nums[right] && target > nums[mid]) left = mid+1;
                else right = mid-1;
            }
        }
        return nums[left] == target ? left : -1;
    }


    /**
     * 81. Search in Rotated Sorted Array II
     * <p>
     *     和上一道唯一的不同是有duplicates
     *     Attention:
     *     要么用一个条件判断哪边是sorted(if nums[start] > nums[mid]; else if nums[start] < nums[mid]);
     *     要么就check two parts: (nums[left] < nums[mid] || nums[right] < nums[mid])
     *     千万不要交叉查。。。(if nums[start] < nums[mid]; else if nums[mid] < nums[end])
     *     这样不能确保进入else的是 nums[left]=nums[mid]= nums[right]
     * </p>
     * @param nums
     * @param target
     * @return
     */
    public boolean searchII(int[] nums, int target) {
        if(nums.length == 1) return nums[0] == target;
        int left = 0, right = nums.length-1;
        while(left < right){
            int mid = left + (right - left)/2;
            if(nums[mid] == target) return true;

            // left part is sorted
            if(nums[left] < nums[mid] || nums[right] < nums[mid]){
                if(target >= nums[left] && target < nums[mid]) right = mid-1;
                else left = mid+1;
            }else if(nums[mid] < nums[right] || nums[mid] < nums[left]){
                // right part is sorted
                if(target <= nums[right] && target > nums[mid]) left = mid+1;
                else right = mid-1;
            }else{
                // nums[left] == nums[right] == nums[mid]
                left++;
            }
        }
        return nums[left] == target;
    }


    /**
     * 153. Find Minimum in Rotated Sorted Array
     * @param nums
     * @return
     */
    public int findMin(int[] nums) {
        int n = nums.length;
        if(n == 1) return nums[0];

        // find the real start point
        int start = 0, end = n-1;
        while(start < end){
            int mid = start + (end - start)/2;
            // point in the right
            if(nums[mid] > nums[end]){
                start = mid+1;
            }else{
                end = mid;
            }
        }
        return nums[start];
    }


    /**
     * 154. Find Minimum in Rotated Sorted Array II
     * @param nums
     * @return
     */
    public int findMinII(int[] nums) {
        int n = nums.length;
        if(n == 1) return nums[0];

        // find the real start point
        int start = 0, end = n-1;
        while(start < end){
            int mid = start + (end - start)/2;
            if(nums[mid] > nums[end]){
                // point in the right
                start = mid+1;
            }else if(nums[mid] < nums[end]){
                // point in left
                // 更新end的时候，不要用mid-1！！！如果mid biased to left,一定要 end = mid
                end = mid;
            }else{
                end--;
            }
        }
        return nums[start];
    }


    public static void main(String[] args) {
        SearchInRotatedSortedArray solution = new SearchInRotatedSortedArray();
        int ret = solution.findMinII(new int[]{3,1,3});
        System.out.println(ret);

    }
}
