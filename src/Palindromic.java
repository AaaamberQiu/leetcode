import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Palindromic {

    /**
     * 5. Longest Palindromic Substring
     * <p>
     * dynamic programming
     * dp[i][j]=true means string in [i,j] is palindromic
     * <p>
     * 递推：
     * if i == j: dp[i][j]=true
     * if s[i] == s[j] && (j-i<=2): dp[i][j]=true; char相同 而且是aa || aba
     * if s[i] == s[j] && (j-i>2) && dp[i+1][j-1]=true: dp[i][j]=true; char相同 而且小范围的str也是回文
     * <p>
     * 注意dp[i][j] 填充顺序，确保计算dp[i][j]前 dp[i+1][j-1]已经被计算
     * </p>
     */
    public static String longestPalindrome(String s) {
        if (s.length() == 1) {
            return s;
        }

        int n = s.length();
        int maxLen = 1;
        int start = 0;
        int end = 0;
        boolean[][] dp = new boolean[n][n];

        // i -> start; j -> end
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (s.charAt(i) == s.charAt(j) && ((j - i) <= 2 || dp[i + 1][j - 1])) {
                    dp[i][j] = true;
                    if (maxLen < (j - i + 1)) {
                        maxLen = j - i + 1;
                        start = i;
                        end = j;
                    }
                }
            }
        }
        return s.substring(start, end + 1);
    }


    public static String longestPalindrome2(String s) {
        if (s.length() == 1) {
            return s;
        }

        int n = s.length();
        int maxLen = 1;
        int start = 0;
        int end = 0;
        boolean[][] dp = new boolean[n][n];

        // i -> end; j -> start
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (s.charAt(i) == s.charAt(j) && ((i - j) <= 2 || dp[j + 1][i - 1])) {
                    dp[j][i] = true;
                    if (maxLen < (i - j + 1)) {
                        maxLen = i - j + 1;
                        start = j;
                        end = i;
                    }
                }
            }
        }
        return s.substring(start, end + 1);
    }

    /**
     * 分成odd,even 两种情况，分别向外扩散找回文串
     */
    private int start = 0;
    private int maxLen = 0;

    public String longestPalindrome3(String s) {
        if (s.length() < 2) {
            return s;
        }
        int n = s.length();

        for (int i = 0; i < n; i++) {
            search(s, i, i + 1); // for even
            search(s, i, i); // for odd
        }
        return s.substring(start, start + maxLen);

    }

    private void search(String s, int l, int r) {
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            l--;
            r++;
        }
        if (maxLen < r - l - 1) {
            maxLen = r - l - 1;
            start = l + 1;
        }
    }


    /**
     * 516. Longest Palindromic Subsequence
     * <p>
     * dynamic programming
     * if i == j: dp[i][j]=1
     * if s[i] == s[j]: dp[i][j] = dp[i+1][end-1] + 2
     * if s[i] != s[j]: find the max len between [i+1][j] 取后不取前 and [i][j-1] 取前不取后
     * <p>
     * 注意：因为计算dp[i][j]的时候要用到 dp[i+1][j] & dp[i][j-1], i必须从后往前，j必须从前往后
     * </p>
     */
    public static int longestPalindromeSubseq(String s) {
        if (s.length() < 2) {
            return s.length();
        }

        int n = s.length();
        // dp[i][j] means the longest palindromic string length in [i,j]
        int[][] dp = new int[n][n];

        for (int end = 0; end < n; end++) {
            dp[end][end] = 1;
            for (int start = end - 1; start >= 0; start--) {
                if (s.charAt(start) == s.charAt(end)) {
                    dp[start][end] = dp[start + 1][end - 1] + 2;
                } else {
                    dp[start][end] = Math.max(dp[start + 1][end], dp[start][end - 1]);
                }
            }
        }
        return dp[0][s.length() - 1];
    }


    /**
     * 647. Palindromic Substrings
     */
    public int countSubstrings(String s) {
        if (s.length() <= 1) {
            return s.length();
        }

        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int count = 0;
        for (int end = 0; end < n; end++) {
            for (int start = end; start >= 0; start--) {
                if (s.charAt(start) == s.charAt(end) && ((end - start) <= 1 || dp[start + 1][end - 1])) {
                    dp[start][end] = true;
                    count += 1;
                }
            }
        }
        return count;
    }


    /**
     * 409. Longest Palindrome
     *
     * @param s
     * @return
     */
    public static int buildLongestPalindrome(String s) {
        int n = s.length();
        if (n <= 1) return n;

        int[] count = new int[256];

        for (char c : s.toCharArray()) {
            count[c] += 1;
        }

        int total = 0;
        boolean hasOdd = false;
        for (int num : count) {
            if (num != 0) {
                if (num % 2 == 1) {
                    total += num - 1;
                    hasOdd = true;
                } else {
                    total += num;
                }
            }
        }
        return hasOdd ? total + 1 : total;
    }

    // use hashset
    public static int buildLongestPalindrome2(String s) {
        Set<Character> set = new HashSet<>();
        int count = 0;

        for (char c : s.toCharArray()) {
            if (set.contains(c)) {
                set.remove(c);
                count++;
            } else {
                set.add(c);
            }
        }

        return set.isEmpty() ? count * 2 : count * 2 + 1;
    }


    /**
     * 131. Palindrome Partitioning
     *
     * @param s
     * @return
     */
    public List<List<String>> partition(String s) {
        int len = s.length();
        List<List<String>> ret = new ArrayList<>();
        boolean[][] dp = new boolean[len][len];

        for(int i = 0; i<len; i++){
            for(int j = 0; j<=i; j++){
                if(s.charAt(i) == s.charAt(j) && (i-j<=2 || dp[j+1][i-1])){
                    dp[j][i] = true;
                }
            }
        }
        partitionHelper(s, ret, dp, 0, new ArrayList<>());
        return ret;

    }


    public static void partitionHelper(String s, List<List<String>> ret, boolean[][] dp, int pos, List<String> temp){
        if(pos == s.length()){
            ret.add(new ArrayList<>(temp));
            return;
        }

        for(int i = pos; i<s.length(); i++){
            if(dp[pos][i]){
                temp.add(s.substring(pos, i+1));
                partitionHelper(s, ret, dp, pos+1, temp);
                temp.remove(temp.size()-1);
            }
        }
    }


}
