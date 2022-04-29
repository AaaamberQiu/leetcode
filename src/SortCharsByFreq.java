import java.util.Comparator;
import java.util.PriorityQueue;

public class SortCharsByFreq {

    /**
     * 451. Sort Characters By Frequency
     * @param s
     * @return
     */
    public String frequencySort(String s) {
        int[] count = new int[256];
        for(char c: s.toCharArray()){
            count[c] += 1;
        }
        // sort by count
        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[1] - o1[1];
            }
        });

        for(int i = 0; i<count.length; i++){
            queue.offer(new int[]{i, count[i]});
        }

        StringBuilder sb = new StringBuilder();
        while(!queue.isEmpty()){
            int[] top = queue.poll();
            char c = (char)top[0];
            int times = top[1];
            while(times > 0) {
                sb.append(c);
                times--;
            }
        }
        return sb.toString();
    }
}
