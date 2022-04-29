import java.util.Arrays;

public class MaxGap {
    /**
     * 164. Maximum Gap
     * @param nums
     * @return
     */
    public int maximumGap(int[] nums) {
        if(nums.length < 2) return 0;

        //step1 : find min and max, then get bucket size
        int min = nums[0], max = nums[0];
        for(int n : nums){
            min = Math.min(min, n);
            max = Math.max(max, n);
        }
        // ensure index will not divide by 0
        if(min == max) return 0;

        // the min possible gap, CEILING!!
        int bucketSize = (int)Math.ceil((max - min)/(double)(nums.length-1));

        // step2: init max bucket and small bucket
        int[] minBucket = new int[nums.length];
        int[] maxBucket = new int[nums.length];
        Arrays.fill(minBucket, Integer.MAX_VALUE);
        Arrays.fill(maxBucket, Integer.MIN_VALUE);

        // step3: put num into buckets
        for(int n : nums){
            int index = (n - min)/bucketSize;
            minBucket[index] = Math.min(minBucket[index], n);
            maxBucket[index] = Math.max(maxBucket[index], n);
        }

        // step4: get the max gap
        // prevMax is the neighbor of currMin after sorted
        int maxGap = Integer.MIN_VALUE;
        int prevMax = maxBucket[0];
        for(int i = 1; i<minBucket.length; i++){
            if(minBucket[i] == Integer.MAX_VALUE){
                continue;
            }
            maxGap = Math.max(maxGap, minBucket[i] - prevMax);
            prevMax = maxBucket[i];
        }
        return maxGap;
    }

    public static void main(String[] args) {
        MaxGap solution = new MaxGap();
        int ret = solution.maximumGap(new int[]{1,3,100});
        System.out.println(ret);
    }
}
