package Grind75.stack;

import java.util.Stack;

public class DailyTemperatures_739 {

    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] ret = new int[n];
        if (n == 1) return ret;

        Stack<Integer> decrease = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!decrease.isEmpty() && temperatures[decrease.peek()] < temperatures[i]) {
                int curr = decrease.pop();
                ret[curr] = i - curr;
            }
            decrease.push(i);
        }
        return ret;
    }
}
