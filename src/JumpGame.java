import java.util.Arrays;

public class JumpGame {

    public static int jump(int[] nums) {
        int n = nums.length;
        if(n<=1) return 0;

        int[] mem = new int[n];
        Arrays.fill(mem, Integer.MAX_VALUE);

        mem[0] = 0;
        for(int i = 1; i< n; i++){
            for(int j = 0; j<i; j++){
                if(j + nums[j] >= i){
                    mem[i] = Math.min(mem[i], mem[j]+1);
                }
            }
        }
        return mem[n-1];
    }


    // greedy
    public static int jump2(int[] nums) {
        int n = nums.length;
        if(n<=1) return 0;

        int end = 0;
        int maxPos = 0;
        int jumps = 0;
        // the range of current jump is [start, end], maxPos is the farthest position can reach
        // once pointer reached the end, it trigger another jump
        for(int i = 0; i<nums.length-1; i++){
            maxPos = Math.max(maxPos, i+nums[i]);
            if(i == end){
                jumps++;
                end = maxPos;
                if(end == nums.length-1){
                    return jumps;
                }
            }
        }
        return jumps;
    }

    public static void main(String[] args) {
        jump(new int[]{2,3,1,1,4});
    }

}
