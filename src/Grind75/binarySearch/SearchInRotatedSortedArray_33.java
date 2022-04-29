package Grind75.binarySearch;

public class SearchInRotatedSortedArray_33 {

    public int search(int[] nums, int target) {
        int start = 0, end = nums.length-1;
        while(start < end){
            int mid = (end - start)/2 + start;
            if(nums[mid] == target) return mid;
            // if the left part is sorted
            if(nums[mid] >= nums[start]){
                // target is in the left part
                if(nums[start] <= target && target < nums[mid]) end = mid-1;
                else start = mid+1;
            }else{
                // target is in the right part
                if(nums[mid] < target && target <= nums[end]) start = mid+1;
                else end = mid-1;
            }
        }
        return nums[start] == target ? start : -1;
    }
}
