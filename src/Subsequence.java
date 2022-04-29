import java.util.*;

public class Subsequence {

    /**
     * 300. Longest Increasing Subsequence
     *
     * <p>
     * dp[i] means len of the longest subsequence ending at i
     * </p>
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 1) return 1;

        int[] dp = new int[nums.length];
        int ret = 0;
        for (int i = 0; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                // if nums[j] < nums[i], it could join the subsequence
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            ret = Math.max(ret, dp[i]);
        }
        return ret;
    }

    // O(nlogn)
    public int lengthOfLIS2(int[] nums) {
        if (nums.length == 1) return 1;

        // dp[i]: the smallest tail of LIS with len i
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int pos = 1;
        for(int i = 1; i<nums.length; i++){
            // cur num is less that start
            if(nums[i] < dp[0]) dp[0] = nums[i];
            // cur num append to LIS
            else if (nums[i] > dp[pos-1]){
                dp[pos] = nums[i];
                pos++;
            }else{
                // update a LIS before
                int left = 0, right = pos;
                while(left < right){
                    int mid = left + (right - left)/2;
                    if(dp[mid] < nums[i]) left = mid+1;
                    else right = mid;
                }
                // find the LIS whose end could be nums[i]
                dp[left] = nums[i];
            }
        }
        return pos;
    }


    /**
     * 115. Distinct Subsequences
     * <p>
     * if s[i] == t[j], dp[i][j] = dp[i-1][j] (the num we have before)+ dp[i-1][j-1] (the num for shorter t)
     * else, dp[i][j] = dp[i-1][j] (only inherit the prev num)
     * </p>
     *
     * @param s
     * @param t
     * @return
     */
    public int numDistinct(String s, String t) {
        int m = s.length();
        int n = t.length();

        // dp[i][j] the num of distinct subsequence for s[0, i] and t[0,j]
        int[][] dp = new int[m + 1][n + 1];
        // if t contains no char, subsequence for s must be ""
        for (int i = 0; i <= m; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // at least it has original num of distinct subseq
                dp[i][j] = dp[i - 1][j];
                // chars are equal, remove both chars to get num for shorter strings
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] += dp[i - 1][j - 1];
                }
            }
        }
        return dp[m][n];
    }


    public static int numDistinct2(String s, String t) {
        int m = s.length();
        int n = t.length();

        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= m; i++) {
            // since we keep prev iteration [j-1] (dp[i-1][j-1]), update backwards
            for (int j = n; j > 0; j--) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[j] += dp[j - 1];
                }
            }
        }
        return dp[n];
    }

    /**
     * 376. Wiggle Subsequence
     * <p>Greedy</p>
     *
     * @param nums
     * @return
     */
    public int wiggleMaxLength(int[] nums) {
        int n = nums.length;
        if (n < 2) return n;

        int prev = 0;
        int count = 1;
        for (int i = 1; i < n; i++) {
            int diff = nums[i] - nums[i - 1];
            if (diff != 0 && prev * diff <= 0) {
                count += 1;
                prev = diff;
            }
        }
        return count;
    }


    // Dynamic programming
    public int wiggleMaxLength2(int[] nums) {
        int n = nums.length;
        if (n < 2) return n;

        // up[i] & down[i]: the max wiggle sequence length in [0,i]
        int[] up = new int[n];
        int[] down = new int[n];

        up[0] = 1;
        down[0] = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i] > nums[i - 1]) {
                up[i] = down[i - 1] + 1;
                down[i] = down[i - 1];
            } else if (nums[i] < nums[i - 1]) {
                down[i] = up[i - 1] + 1;
                up[i] = up[i - 1];
            } else {
                up[i] = up[i - 1];
                down[i] = down[i - 1];
            }
        }
        return Math.max(up[n - 1], down[n - 1]);
    }


    /**
     * 491. Increasing Subsequences
     * @param nums
     * @return
     */
    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> ret = new ArrayList<>();
        if (nums.length <= 1) return ret;
        helper(nums, 0, new LinkedList<>(), ret);
        return ret;
    }

    public static void helper(int[] nums, int pos, LinkedList<Integer> temp, List<List<Integer>> ret) {
        if (temp.size() >= 2) {
            ret.add(new ArrayList<>(temp));
        }

        Set<Integer> used = new HashSet<>();
        for (int i = pos; i < nums.length; i++) {
            if(used.contains(nums[i])) continue;
            if(temp.size() == 0 || nums[i] >= temp.getLast()){
                temp.add(nums[i]);
                used.add(nums[i]);
                helper(nums, i+1, temp, ret);
                temp.removeLast();
            }
        }
    }


    /**
     * 521. Longest Uncommon Subsequence I
     * @param a
     * @param b
     * @return
     */
    public int findLUSlength(String a, String b) {
        if(a.length() != b.length()) return a.length() > b.length()? a.length() : b.length();
        return a.equals(b)? a.length() : -1;

    }

    /**
     * 522. Longest Uncommon Subsequence II
     * @param strs
     * @return
     */
    // the most intuitive idea is that we need to check whether s is the subsequence of rest strings
    public int findLUSlength(String[] strs) {
        int ret = -1;
        for(int i = 0; i<strs.length; i++){
            if(strs[i].length() < ret) continue;
            // check whether strs[i] is a subsequence of rest strings
            boolean isSubSequence = false;
            for(int j = 0; j<strs.length; j++){
                if(i == j) continue;
                if(isSubseq(strs[i], strs[j])) isSubSequence = true;
            }
            // if strs[i] is not subsequence of any string, it is uncommon subsequence
            if(!isSubSequence) ret = Math.max(ret, strs[i].length());
        }
        return ret;
    }


    // sort by length, a longer string cannot be subsequence of shorter strings
    public int findLUSlength2(String[] strs) {
        Arrays.sort(strs, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.length() - o1.length();
            }
        });

        int ret = -1;
        // check from the longest string
        Set<String> duplicates = getDuplicates(strs);
        for(int i = 0; i< strs.length; i++){
            // if it doesn't have duplicates
            if(!duplicates.contains(strs[i])){
                // check if it is the subseq of LONGER strings
                int j = 0;
                for(; j<i; j++){
                    if(isSubseq(strs[i], strs[j])) break;
                }
                if(j == i) ret = Math.max(ret, strs[i].length());
            }
        }
        return ret;
    }

    public static boolean isSubseq(String a, String b){
        if(a.equals(b)) return true;
        int i = 0;
        for(int j = 0; j<b.length(); j++){
            if(i< a.length() && a.charAt(i) == b.charAt(j)) i++;
        }
        return i == a.length();
    }


    public static Set<String> getDuplicates(String[] strs){
        Set<String> set = new HashSet<>();
        Set<String> duplicates = new HashSet<>();
        for(String s: strs){
            if(!set.add(s)) duplicates.add(s);
        }
        return duplicates;
    }




    public static void main(String[] args) {
        Subsequence solution = new Subsequence();
        solution.findSubsequences(new int[]{4, 6, 7, 7});
    }
}
