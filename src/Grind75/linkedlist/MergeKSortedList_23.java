package Grind75.linkedlist;

import base.ListNode;

import java.util.Arrays;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.Collectors;

public class MergeKSortedList_23 {

    // priority queue
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists == null) return null;

        Queue<ListNode> queue = new PriorityQueue<>((a, b) -> a.val - b.val);
        queue.addAll(Arrays.stream(lists).filter(Objects::nonNull).collect(Collectors.toList()));

        ListNode dummy = new ListNode();
        ListNode pointer = dummy;
        while(!queue.isEmpty()){
            ListNode top = queue.poll();
            pointer.next = top;
            pointer = pointer.next;

            if(top.next != null) queue.offer(top.next);
        }
        return dummy.next;
    }


    // divide and conquer + merge sort
    public ListNode mergeKLists_2(ListNode[] lists) {
        return mergeSort(lists, 0, lists.length-1);
    }


    private ListNode mergeSort(ListNode[] lists, int start, int end){
        if(start > end) return null;
        if(start == end) return lists[start];
        int mid = (end - start)/2 + start;

        ListNode left = mergeSort(lists, start, mid);
        ListNode right = mergeSort(lists, mid+1, end);
        return merge(left, right);
    }

    private ListNode merge(ListNode l1, ListNode l2){
        if(l1 == null) return l2;
        if(l2 == null) return l1;

        if(l1.val < l2.val){
            l1.next = merge(l1.next, l2);
            return l1;
        }else{
            l2.next = merge(l2.next, l1);
            return l2;
        }
    }
}
