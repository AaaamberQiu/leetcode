import java.util.*;

public class TaskScheduler {

    /**
     * 621. Task Scheduler
     *
     * @param tasks
     * @param n
     * @return
     */
    public int leastInterval(char[] tasks, int n) {
        int maxFreq = 0;
        Map<Character, Integer> map = new HashMap<>();
        for(char c: tasks){
            Integer count = map.getOrDefault(c, 0);
            count += 1;
            maxFreq = Math.max(maxFreq, count);
            map.put(c, count);
        }

        List<Character> mostFreqChars = new ArrayList<>();
        for(Character c: map.keySet()){
            if(map.get(c) == maxFreq){
                mostFreqChars.add(c);
            }
        }

        // split slots
        int parts = maxFreq - 1;
        // compute empty slots: empty slots could only be in the parts
        int emptySlots = parts * n - parts * (mostFreqChars.size()-1);
        int restTasks = tasks.length - mostFreqChars.size() * maxFreq;
        int idles = Math.max(0, emptySlots - restTasks);
        return tasks.length + idles;
    }

    // based on greedy => first arrange task with highest frequency
    public int leastInterval2(char[] tasks, int n) {
        Map<Character, Integer> map = new HashMap<>();
        for(char t: tasks){
            map.put(t, map.getOrDefault(t, 0) + 1);
        }

        // sort by freq in decreasing order
        PriorityQueue<Map.Entry<Character, Integer>> queue = new PriorityQueue<>((a,b) -> b.getValue() - a.getValue());
        queue.addAll(map.entrySet());

        int ret = 0;
        while(!queue.isEmpty()){
            int slots = n+1;
            List<Map.Entry<Character, Integer>> tempList = new ArrayList<>();

            // polling tasks to consume this part of slots
            while(slots > 0 && !queue.isEmpty()){
                Map.Entry<Character, Integer> task = queue.poll();
                task.setValue(task.getValue() - 1);
                tempList.add(task);
                slots--;
                ret++;
            }
            // re-add not-completed task
            for(Map.Entry<Character, Integer> e: tempList){
                if(e.getValue() > 0) queue.add(e);
            }

            if(queue.isEmpty()) break;
            // add idle slots
            ret += slots;
        }

        return ret;
    }
}
