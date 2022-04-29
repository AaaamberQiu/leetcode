public class SortColors {

    /**
     * 75. Sort Colors
     * <p>
     *     three-way partitioning
     *     counting sort
     * </p>
     * @param nums
     */
    public void sortColors(int[] nums) {
        int n = nums.length;
        int left = 0, right = n-1, i = 0, mid = 1;
        while(i<=right){
            if(nums[i] < mid){
                swap(nums, i++, left++);
            }else if(nums[i] > mid){
                swap(nums, i, right--);
            }else{
                i++;
            }
        }
    }

    public static void swap(int[] nums, int a, int b){
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }


    // counting sort
    public void sortColors2(int[] nums) {
        int count0 = 0, count1 = 0, count2 = 0;
        for(int i = 0; i<nums.length; i++){
            if(nums[i] == 0) count0++;
            else if(nums[i] == 1) count1++;
            else count2++;
        }

        for(int i = 0; i<nums.length; i++){
            if(i < count0) nums[i] = 0;
            else if (i < count0+count1) nums[i] = 1;
            else nums[i] = 2;
        }
    }
}
