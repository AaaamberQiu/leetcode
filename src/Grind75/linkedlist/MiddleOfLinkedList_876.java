package Grind75.linkedlist;

import base.ListNode;

public class MiddleOfLinkedList_876 {

    public ListNode middleNode(ListNode head) {
        if(head == null) return null;
        ListNode slow = head, fast = head;

        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
