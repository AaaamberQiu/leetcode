package Grind75.greedy;

import java.util.HashMap;
import java.util.Map;

public class TaskScheduler_621 {

    public int leastInterval(char[] tasks, int n) {
        // count map
        int mostFreq = 0;
        Map<Character, Integer> map = new HashMap<>();
        for(char t : tasks){
            map.put(t, map.getOrDefault(t, 0) + 1);
            mostFreq = Math.max(mostFreq, map.get(t));
        }

        // count most freq tasks
        int mostFreqCount = 0;
        for(Character task: map.keySet()){
            if(map.get(task) == mostFreq) mostFreqCount += 1;
        }

        int parts = mostFreq-1;
        int slots = parts * n;
        int rest = slots - (mostFreqCount-1) * parts;
        int idle = Math.max(0, rest - tasks.length - mostFreq * mostFreqCount);
        return tasks.length + idle;
    }

}
