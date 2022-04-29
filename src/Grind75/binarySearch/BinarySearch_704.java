package Grind75.binarySearch;

public class BinarySearch_704 {

    // the most basic one
    public int search(int[] nums, int target) {
        if(nums.length == 0) return -1;

        int start = 0, end = nums.length-1;
        while(start <= end){
            int mid = (end - start)/2 + start;
            if(nums[mid] == target) return mid;
            else if(nums[mid] < target) start = mid+1;
            else end = mid-1;
        }
        return -1;
    }
}
