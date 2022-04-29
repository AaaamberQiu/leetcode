public class WildcardMatching {

    /**
     * 44. Wildcard Matching
     * <p>
     *     dp[i][j]: whether s[0,i] matches p[0,j]
     *     if s[i] == p[j] || p[j] == ?: dp[i][j] = dp[i-1][j-1]
     *     else if p[j] == *: dp[i][j] = dp[i-1][j] (* acts like empty str)|| dp[i][j-1] (* acts like any str)
     * </p>
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();

        boolean[][] dp = new boolean[m+1][n+1];
        // if both are empty string, they match
        dp[0][0] = true;

        // if p is empty, cannot match
        for(int i = 1; i<=m; i++){
            dp[i][0] = false;
        }
        // if s is empty, p should be start with *, otherwise false
        for(int j = 1; j<=n; j++){
            if(p.charAt(j-1) == '*'){
                dp[0][j] = dp[0][j-1];
            }
        }

        for(int i = 1; i<=m; i++){
            for(int j = 1; j<=n; j++){
                if(s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '?'){
                    dp[i][j] = dp[i-1][j-1];
                }else if(p.charAt(j-1) == '*'){
                    // act as empty string OR act as any char
                    dp[i][j] = dp[i-1][j] || dp[i][j-1];
                }
            }
        }
        return dp[m][n];
    }
}
