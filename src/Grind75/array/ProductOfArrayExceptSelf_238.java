package Grind75.array;

public class ProductOfArrayExceptSelf_238 {

    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] ret = new int[n];

        ret[0] = 1;
        for(int i = 0; i<n-1; i++){
            nums[i+1] = ret[i] * nums[i];
        }

        int product = 1;
        for(int i = n-1; i>=0; i--){
            ret[i] = ret[i] * product;
            product *= nums[i];
        }
        return ret;
    }
}
