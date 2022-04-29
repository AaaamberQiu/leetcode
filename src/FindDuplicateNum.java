public class FindDuplicateNum {

    /**
     * 287. Find the Duplicate Number
     * <p>
     *     linked list detect cycle
     *     binary search
     * </p>
     * @param nums
     * @return
     */
    public int findDuplicate(int[] nums) {
        int n = nums.length-1;
        int start = 1, end = n;
        while(start < end){
            int mid = start + (end - start)/2;
            int count = 0;
            // 虽然nums不是sorted，但是我们可以通过找[1,n]的mid，数一下nums里面数字的分布
            for(int num : nums) {
                if(num <= mid) count+=1;
            }

            // count <= mid 说明 [1,mid] 这一段是正常的，重复的数字不在这个范围
            if(count <= mid) start = mid+1;
            else end = mid;
        }
        return start;
    }


    // suppose nums represents a linked list, index -> nums[index]，一共有n nodes，但是有n+1个link，说明有cycle
    public int findDuplicate2(int[] nums) {
        // find the entry of cycle
        int fast = 0, slow = 0;
        do{
            fast = nums[nums[fast]];
            slow = nums[slow];
        }while(fast != slow);

        slow = 0;
        while(slow != fast){
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }

    public static void main(String[] args) {
        FindDuplicateNum solution = new FindDuplicateNum();
        int ret = solution.findDuplicate(new int[]{1,3,4,2,2});
        System.out.println(ret);
    }
}
