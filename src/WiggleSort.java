import java.util.PriorityQueue;
import java.util.Queue;

public class WiggleSort {

    /**
     * 324. Wiggle Sort II
     * <p>
     *     put all nums < median to odd index
     *     put all nums > median to even index
     * </p>
     * @param nums
     */
    public void wiggleSort(int[] nums) {
        int len = nums.length;
        int median = findKthLargest(nums, (len+1)/2);

        int left = 0, i = 0, right = len-1;
        while(i <= right){
            int index = mapping(i, len);
            if(nums[index] > median){
                swap(nums, mapping(left, len), index);
                left++;
            }else if(nums[index] < median){
                swap(nums, mapping(right, len), index);
                right--;
            }
            i++;
        }
    }

    public static int mapping(int i, int n){
        return (2*i + 1) % (n|1);
    }

    public static int findKthLargest(int[] nums, int k) {
        Queue<Integer> queue = new PriorityQueue<>();
        for(int n: nums){
            queue.add(n);
            if(queue.size() > k) queue.poll();
        }
        return queue.poll();
    }

    public static void swap(int[] nums, int a, int b){
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }


    public static void main(String[] args) {
        WiggleSort solution = new WiggleSort();
        int[] input = new int[]{1,3,2,2,3,1,2};
        System.out.println("n|1: " + (input.length|1));
        for(int i = 0; i< input.length; i++){
            System.out.println(mapping(i, input.length));
        }
    }

}
