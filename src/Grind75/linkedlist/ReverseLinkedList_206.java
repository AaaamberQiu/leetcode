package Grind75.linkedlist;

import base.ListNode;

public class ReverseLinkedList_206 {

    public ListNode reverseList(ListNode head) {
        ListNode newHead = new ListNode();

        while(head != null){
            ListNode temp = head.next;
            head.next = newHead.next;
            newHead.next = head;
            head = temp;
        }
        return newHead.next;
    }


    public ListNode reverseList_rec(ListNode head) {
        ListNode newHead = new ListNode();
        return helper(head, newHead);
    }

    public ListNode helper(ListNode head, ListNode newHead){
        ListNode next = head.next;
        head.next = newHead.next;
        newHead.next = head;
        return helper(next, newHead);
    }
}
