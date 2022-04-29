import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class NextGreaterElement {

    /**
     * 496. Next Greater Element I
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] ret = new int[nums1.length];
        Map<Integer, Integer> nextGreaterMap = new HashMap<>();

        // mono-decreasing stack
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i<nums2.length; i++){
            // nums2[i] is the next greater element for all nums in the stack
            while(!stack.isEmpty() && nums2[i] > stack.peek()){
                int num = stack.pop();
                nextGreaterMap.put(num, nums2[i]);
            }
            stack.push(nums2[i]);
        }

        for(int i = 0; i< nums1.length; i++){
            ret[i] = nextGreaterMap.getOrDefault(nums1[i], -1);
        }

        return ret;
    }


    /**
     * 503. Next Greater Element II
     * @param nums
     * @return
     */
    public int[] nextGreaterElementsII(int[] nums) {
        int n = nums.length;
        // key = index, val = next greater element val
        Map<Integer, Integer> nextGreaterMap = new HashMap<>();

        // mono-decreasing stack, stores index
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < 2*n; i++){
            int index = i%n;
            // nums[i] is the next greater element for all nums in the stack
            while(!stack.isEmpty() && nums[index] > nums[stack.peek()]){
                int topIndex = stack.pop();
                if(!nextGreaterMap.containsKey(topIndex)){
                    nextGreaterMap.put(topIndex, nums[i]);
                }
            }
            stack.push(index);
        }

        int[] ret = new int[nums.length];
        Arrays.fill(ret, -1);
        for(Integer index: nextGreaterMap.keySet()){
            ret[index] = nextGreaterMap.get(index);
        }
        return ret;
    }
}
