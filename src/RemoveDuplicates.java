import base.ListNode;

public class RemoveDuplicates {

    /**
     * 26. Remove Duplicates from Sorted Array
     *
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        int left = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == nums[left]) continue;
            nums[++left] = nums[i];
        }
        return left + 1;
    }


    /**
     * 80. Remove Duplicates from Sorted Array II
     * <p>
     * each unique element appears at most twice
     * </p>
     *
     * @param nums
     * @return
     */
    public int removeDuplicatesII(int[] nums) {
        if (nums.length == 1) return 1;
        int left = 0, count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[left]) {
                if (count < 2) {
                    nums[++left] = nums[i];
                    count += 1;
                }
            } else {
                nums[++left] = nums[i];
                count = 1;
            }
        }
        return left + 1;
    }


    /**
     * 82. Remove Duplicates from Sorted List II
     *
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode();

        ListNode prev = dummy, curr = dummy.next;
        while (curr != null) {
            ListNode start = curr;
            while (curr != null && curr.next != null && curr.val == curr.next.val) curr = curr.next;
            if (curr == start) {
                ListNode next = curr.next;
                prev.next = curr;
                curr.next = null;

                prev = prev.next;
                curr = next;
            }else {
                curr = curr.next;
            }

        }
        return dummy.next;
    }
}
