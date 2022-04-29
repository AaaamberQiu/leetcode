public class CountNumWithUniqDigits {

    /**
     * 357. Count Numbers with Unique Digits
     * @param n
     * @return
     */
    public static int countNumbersWithUniqueDigits(int n) {
        if(n == 0) return 1;
        int[] dp = new int[n+1];
        dp[1] = 10;
        for(int i = 2; i<=n; i++){
            dp[i] = dp[i-1] + count(i);
        }
        return dp[n];
    }

    public static int count(int n){
        int base = 9;
        int options = 9;
        for(int i = 1; i<n; i++){
            base = base * options;
            options--;
        }
        return base;
    }

    public static void main(String[] args) {
        countNumbersWithUniqueDigits(2);
    }
}
