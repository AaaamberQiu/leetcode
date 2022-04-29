import base.ListNode;

import java.util.PriorityQueue;

public class MergeSortedLists {

    /**
     * 21. Merge Two Sorted Lists
     * @param list1
     * @param list2
     * @return
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if(list1 == null) return list2;
        if(list2 == null) return list1;

        if(list1.val < list2.val) {
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        }else{
            list2.next = mergeTwoLists(list1, list2.next);
            return list2;
        }
    }

    /**
     * 23. Merge k Sorted Lists
     * <p>
     *     can be solved by Heap / Divide and conquer
     * </p>
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists == null || lists.length == 0) return null;

        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a,b) -> a.val-b.val);
        for(ListNode node: lists){
            if(node != null) minHeap.add(node);
        }

        ListNode dummy = new ListNode();
        ListNode pointer = dummy;
        while(!minHeap.isEmpty()){
            ListNode min = minHeap.poll();
            pointer.next = min;
            pointer = pointer.next;
            if(min.next != null){
                minHeap.add(min.next);
            }
        }
        return dummy.next;
    }

    public ListNode mergeKLists2(ListNode[] lists) {
        if(lists == null || lists.length == 0) return null;
        return mergeHelper(lists, 0, lists.length-1);
    }

    public static ListNode mergeHelper(ListNode[] lists, int start, int end){
        if(start == end) return lists[start];

        // divide to left part and right part, then sort, and combine
        int mid = (end - start)/2 + start;
        ListNode sortedLeft = mergeHelper(lists, start, mid);
        ListNode sortedRight = mergeHelper(lists, mid+1, end);
        return merge(sortedLeft, sortedRight);
    }


    public static ListNode merge(ListNode sortedA, ListNode sortedB){
        if(sortedA == null) return sortedB;
        if(sortedB == null) return sortedA;

        if(sortedA.val < sortedB.val){
            sortedA.next = merge(sortedA.next, sortedB);
            return sortedA;
        }else{
            sortedB.next = merge(sortedA, sortedB.next);
            return sortedB;
        }
    }


}
