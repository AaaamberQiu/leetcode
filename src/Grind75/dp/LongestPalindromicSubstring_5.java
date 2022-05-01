package Grind75.dp;

public class LongestPalindromicSubstring_5 {

    public static String longestPalindrome(String s) {
        int n = s.length();
        // dp[i][j]: s[i,j] is palindromic or not
        boolean[][] dp = new boolean[n][n];

        int max = 0;
        String ret = "";
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                dp[i][j] = s.charAt(i) == s.charAt(j);
                if (i - j > 2) {
                    dp[i][j] = dp[i][j] && dp[i - 1][j + 1];
                }

                if (dp[i][j] && (i - j + 1) > max) {
                    max = i - j + 1;
                    ret = s.substring(j, i + 1);
                }
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        longestPalindrome("a");
    }
}
