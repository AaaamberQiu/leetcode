package Grind75.dp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MaxProfitInJobScheduling_1235 {

    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        List<Job> jobs = convert(startTime, endTime, profit);
        // sort by start time
        Collections.sort(jobs, (a, b) -> a.end - b.end);

        // dp[i]: max profit ending at job_1.endTime
        int[] dp = new int[jobs.size()];
        dp[0] = jobs.get(0).profit;
        int max = 0;
        for(int i = 1; i<dp.length; i++){
            int prev = binarySearch(jobs, i);
            if(prev != -1){
                dp[i] = jobs.get(i).profit + dp[prev];
            }
            dp[i] = Math.max(dp[i], dp[i-1]);
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    private List<Job> convert(int[] startTime, int[] endTime, int[] profit){
        List<Job> jobs = new ArrayList<>();
        int size = startTime.length;
        for(int i = 0; i<size; i++){
            jobs.add(new Job(startTime[i], endTime[i], profit[i]));
        }
        return jobs;
    }


    private int search(List<Job> jobs, int i){
        for(int j = i-1; j>=0; j--){
            if(jobs.get(j).end <= jobs.get(i).start){
                return j;
            }
        }
        return -1;
    }

    private int binarySearch(List<Job> jobs, int i){
        int startTime = jobs.get(i).start;
        int left = 0, right = i-1;
        while(left <= right){
            int mid = (right - left)/2 + left;
            if(jobs.get(mid).end <= startTime){
                if(jobs.get(mid + 1).end > startTime) return mid;
                else left = mid + 1;
            }else{
                right = mid - 1;
            }
        }
        return -1;
    }


    class Job{
        int start;
        int end;
        int profit;
        Job(int s, int e, int p){
            this.start = s;
            this.end = e;
            this.profit = p;
        }
    }

    public static void main(String[] args) {
        MaxProfitInJobScheduling_1235 solution = new MaxProfitInJobScheduling_1235();
        int[] startTime = {1,2,3,3};
        int[] endTime = {3,4,5,6};
        int[] profit = {50,10,40,70};
        solution.jobScheduling(startTime, endTime, profit);
    }
}
