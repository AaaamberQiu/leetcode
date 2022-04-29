public class DecodeWay {

    public int numDecodings(String s) {
        int n = s.length();
        int[] dp = new int[n+1];

        dp[0] = 1;
        dp[1] = s.charAt(0) == '0' ? 0:1;
        for(int i = 2; i<=n; i++){
            Integer oneChar = Integer.valueOf(s.substring(i-1, i));
            Integer twoChar = Integer.valueOf(s.substring(i-2, i));
            if(oneChar>=1 && oneChar<=9){
                dp[i] += dp[i-1];
            }
            if(twoChar>=10 && twoChar<=26){
                dp[i] += dp[i-2];
            }
        }
        return dp[n];
    }
}
