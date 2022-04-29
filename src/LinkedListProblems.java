import base.ListNode;

public class LinkedListProblems {

    /**
     * 148. Sort List
     * @param head
     * @return
     */
    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null) return head;

        // step1: cut in the middle
        ListNode prev = null, slow = head, fast = head;
        while(fast != null && fast.next != null){
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        prev.next = null;

        // step2: sort each half
        ListNode left = sortList(head);
        ListNode right = sortList(slow);

        return merge(left, right);
    }

    public static ListNode merge(ListNode left, ListNode right){
        ListNode dummy = new ListNode();
        ListNode pointer = dummy;

        while(left != null && right != null){
            if(left.val < right.val){
                pointer.next = left;
                left = left.next;
            }else{
                pointer.next = right;
                right = right.next;
            }
            pointer = pointer.next;
        }

        if(left != null) pointer.next = left;
        if(right != null) pointer.next = right;
        return dummy.next;
    }


    // split list into two parts: sorted and unsorted
    public static ListNode insertSort(ListNode head){
        if(head == null || head.next == null) return head;

        ListNode sorted = new ListNode();
        sorted.next = head;
        ListNode unsorted = head.next;

        head.next = null;

        while(unsorted != null){
            ListNode pointer = sorted;
            while(pointer.next != null && pointer.next.val <= unsorted.val){
                pointer = pointer.next;
            }
            ListNode nextUnsorted = unsorted.next;
            if(pointer.next == null){
                pointer.next = unsorted;
                unsorted.next = null;
            }else{
                unsorted.next = pointer.next;
                pointer.next = unsorted;
            }
            unsorted = nextUnsorted;
        }
        return sorted.next;
    }


    // append list node to dummy head one by one
    public static ListNode insertSort2(ListNode head){
        if(head == null || head.next == null) return head;

        ListNode dummy = new ListNode();
        ListNode prev = dummy, curr = head, next = null;
        while(curr != null){
            next = curr.next;
            while(prev.next != null && prev.next.val < curr.val){
                prev = prev.next;
            }

            curr.next = prev.next;
            prev.next = curr;

            // reset
            prev = dummy;
            curr = next;
        }
        return dummy.next;
    }


    /**
     * 61. Rotate List
     * @param head
     * @param k
     * @return
     */
    public static ListNode rotateRight(ListNode head, int k) {
        if(head == null) return null;

        // step1: get len
        ListNode pointer = head;
        int count = 0;
        while(pointer != null){
            count += 1;
            pointer = pointer.next;
        }

        k = k % count;
        if(k == 0) return head;

        // step2: get last k elements
        pointer = head;
        int n = k;
        while(n > 0){
            pointer = pointer.next;
            n--;
        }
        ListNode prev = head;
        while(pointer.next != null){
            prev = prev.next;
            pointer = pointer.next;
        }

        ListNode newHead = prev.next;
        prev.next = null;

        // step3: put it as head
        pointer.next = head;
        return newHead;
    }




    /**
     * 86. Partition List
     * @param head
     * @param x
     * @return
     */
    public ListNode partition(ListNode head, int x) {
        ListNode dummyLess = new ListNode();
        ListNode dummyLarger = new ListNode();

        ListNode pointer = head, p1 = dummyLess, p2 = dummyLarger;
        while(pointer != null){
            ListNode next = pointer.next;
            pointer.next = null;
            if(pointer.val < x){
                p1.next = pointer;
                p1 = p1.next;
            }else{
                p2.next = pointer;
                p2 = p2.next;
            }
            pointer = next;
        }
        p1.next = dummyLarger.next;
        return dummyLess.next;
    }


    /**
     * 143. Reorder List
     * @param head
     */
    public static void reorderList(ListNode head) {
        if(head == null || head.next == null) return;

        // step1: find second half of linked list
        // prev = tail of the first half, slow is the head of second half
        ListNode prev = null, slow = head, fast = head;
        while(fast != null && fast.next != null){
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode secondHalf;
        // even length
        if(fast == null){
            prev.next = null;
            secondHalf = slow;
        }else{
            secondHalf = slow.next;
            slow.next = null;
        }

        // step2: reverse
        secondHalf = reverse(secondHalf);

        // step 3: insert the reversed second half to the first half
        while(secondHalf != null){
            ListNode secondHalfNext = secondHalf.next, firstHalfNext = head.next;
            // insert
            secondHalf.next = head.next;
            head.next = secondHalf;
            // move to next
            secondHalf = secondHalfNext;
            head = firstHalfNext;
        }
    }



    public static ListNode reverse(ListNode head){
        if(head == null || head.next == null) return head;

        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode prev = dummy, curr = head, next = head.next;

        while(next != null){
            ListNode temp = next.next;
            next.next = prev.next;
            prev.next = next;
            curr.next = temp;

            next = curr.next;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        // [-1,5,3,4,0]
        ListNode head = new ListNode(1, new ListNode(2));
        head.next.next = new ListNode(3, new ListNode(4));
        //head.next.next.next.next = new ListNode(5);
        reorderList(head);
    }


}
