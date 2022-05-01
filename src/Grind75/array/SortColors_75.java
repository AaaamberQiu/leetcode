package Grind75.array;

public class SortColors_75 {

    public void sortColors(int[] nums) {
        int red = 0, blue = nums.length-1;
        int i = 0;
        while(i < blue){
            if(nums[i] == 0){
                swap(nums, red, i);
                red++;
                i++;
            }else if(nums[i] == 1) {
                i++;
            }else{
                swap(nums, blue, i);
                blue--;
            }
        }
    }

    public static void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
