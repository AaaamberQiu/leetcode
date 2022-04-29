public class NextPermutation {

    /**
     * 31. Next Permutation
     * @param nums
     */
    public void nextPermutation(int[] nums) {
        if(nums.length <= 1) return;
        // step1: find the last index of increasing sub-array
        int i = nums.length-2;
        while(i >= 0 && nums[i] >= nums[i+1]) i--;
        if(i >= 0){
            // step2: find the rightmost larger element after that
            int rightMost = nums.length-1;
            while(rightMost > i && nums[rightMost] < nums[i]) rightMost--;
            // step3: swap pivotIndex and rightMostIndex
            swap(nums, i, rightMost);
        }

        // step4: reverse [rightMostIndex, end]
        int start = i+1, end = nums.length-1;
        while(start < end){
            swap(nums, start, end);
            start++; end--;
        }

    }


    public static void swap(int[] nums, int a, int b){
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }


}
