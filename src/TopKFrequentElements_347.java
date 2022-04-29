import java.util.*;

public class TopKFrequentElements_347 {

    /**
     * 347. Top K Frequent Elements
     * <p>
     *     bucket sort: O(n)
     *     Tree map/max heap: O(nlogn)
     * </p>
     * @param nums
     * @param k
     * @return
     */
    public int[] topKFrequent(int[] nums, int k) {
        // count frequency
        Map<Integer, Integer> freqMap = new HashMap<>();
        for(int n: nums){
            int freq = freqMap.getOrDefault(n, 0);
            freq += 1;
            freqMap.put(n, freq);
        }

        PriorityQueue<int[]> maxHeap = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[1] - o1[1];
            }
        });

        for(int num: freqMap.keySet()){
            maxHeap.add(new int[]{num, freqMap.get(num)});
        }

        int[] ret = new int[k];
        for(int i = 0; i<k; i++){
            int[] top = maxHeap.poll();
            ret[i] = top[0];
        }
        return ret;
    }


    // bucket sort
    public int[] topKFrequent2(int[] nums, int k) {
        // count frequency
        Map<Integer, Integer> freqMap = new HashMap<>();
        for(int n: nums){
            int freq = freqMap.getOrDefault(n, 0);
            freq += 1;
            freqMap.put(n, freq);
        }

        // the max possible frequency is nums.len
        List<Integer>[] bucket = new List[nums.length+1];
        for(int n: freqMap.keySet()){
            int freq = freqMap.get(n);
            if(bucket[freq] == null){
                List<Integer> temp = new ArrayList<>();
                temp.add(n);
                bucket[freq] = temp;
            }else{
                bucket[freq].add(n);
            }
        }

        int[] ret = new int[k];
        int index = 0;
        for(int i = bucket.length-1; i >= 0; i--){
            if(bucket[i] != null){
                List<Integer> numbers = bucket[i];
                for(int n: numbers){
                    if(index == k) return ret;
                    ret[index++] = n;
                }
            }
        }
        return ret;
    }
}
