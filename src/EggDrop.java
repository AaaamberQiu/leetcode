public class EggDrop {

    public static int twoEggDrop(int n) {
        if(n<=2) return n;
        int[][] dp = new int[n+1][3];
        return eggDrop(n, 2, dp);

    }

    private static int eggDrop(int n, int eggs, int[][] dp){
        if(eggs == 1 || n <= 2) return n;
        if(dp[n][eggs] > 0){
            return dp[n][eggs];
        }

        int min = n;
        for(int f = 1; f<n; f++){
            int broken = eggDrop(f-1, eggs-1, dp);
            int unbroken = eggDrop(n-f, eggs, dp);
            int drop = Math.max(broken, unbroken) + 1;
            min = Math.min(min, drop);
        }
        dp[n][eggs] = min;
        return dp[n][eggs];

    }


    private static int eggDrop2(int n, int eggs, int[][] dp){
        if(eggs == 1 || n <= 2) return n;
        if(dp[n][eggs] > 0){
            return dp[n][eggs];
        }

        int min = n;
        int low = 1;
        int high = n;
        while(low < high){
            int mid = low + (high - low)/2;
            int broken = eggDrop(mid-1, eggs-1, dp);
            int unbroken = eggDrop(n-mid, eggs, dp);
            int drop = Math.max(broken, unbroken) + 1;
            min = Math.min(min, drop);

            if(broken == unbroken){
                break;
            }else if(broken < unbroken){
                low = mid + 1;
            }else{
                high = mid;
            }
        }
        dp[n][eggs] = min;
        return dp[n][eggs];

    }

    public static void main(String[] args) {
        System.out.println(twoEggDrop(100));
    }
}
