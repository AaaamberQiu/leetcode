package Grind75.queue;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class FindMedianFromDataStream_295 {

    class MedianFinder {

        private Queue<Integer> left;
        private Queue<Integer> right;

        public MedianFinder() {
            // maxHeap: need to poll the largest
            left = new PriorityQueue<>((a,b) -> b-a);
            // minHeap: need to poll the smallest
            right = new PriorityQueue<>();
        }

        public void addNum(int num) {
            left.offer(num);
            right.offer(left.poll());
            balance();
        }

        public double findMedian() {
            if(left.size() == right.size()){
                return left.peek()/2.0 + right.peek()/2.0;
            }
            return left.peek();
        }

        private void balance(){
            while(left.size() < right.size()){
                left.offer(right.poll());
            }
        }
    }
}
