import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class CourseScheduleIII {

    public int scheduleCourse(int[][] courses) {
        // greedy: handle the earlier ddl firstly
        Arrays.sort(courses, (a, b) -> a[1]-b[1]);
        // duration in decreasing order
        PriorityQueue<Integer> queue = new PriorityQueue<>((a,b) -> b-a);

        int start = 0;
        for(int[] course: courses){
            int duration = course[0], end = course[1];
            if(duration > end) continue;
            start += duration;
            queue.add(duration);
            while(start > end){
                start -= queue.poll();
            }
        }

        return queue.size();
    }

    public static void main(String[] args) {

    }
}
