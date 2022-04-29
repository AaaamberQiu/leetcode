import java.util.Collections;
import java.util.PriorityQueue;

public class FindMedianFromDs_295 {

    class MedianFinder {
        private PriorityQueue<Integer> left = new PriorityQueue<>(Collections.reverseOrder());
        private PriorityQueue<Integer> right = new PriorityQueue<>();

        public MedianFinder() {}

        // make sure that the left size is larger than right size
        public void addNum(int num) {
            left.add(num);
            right.add(left.poll());
            if(left.size() < right.size()){
                left.add(right.poll());
            }
        }

        public double findMedian() {
            if(left.size() == right.size()){
                return left.peek()/2.0 + right.peek()/2.0;
            }else{
                return left.peek();
            }
        }
    }

}
