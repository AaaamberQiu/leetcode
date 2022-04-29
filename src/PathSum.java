public class PathSum {

    public int minPathSum(int[][] grid) {
        int height = grid.length;
        int width = grid[0].length;

        int[][] dp = new int[height][width];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < height; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for (int j = 1; j < width; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }

        for (int i = 1; i < height; i++) {
            for (int j = 1; j < width; j++) {
                dp[i][j] = grid[i][j] + Math.min(dp[i-1][j], dp[i][j-1]);

            }
        }
        return dp[height-1][width-1];
    }

    public int minPathSum2(int[][] grid) {
        int width = grid[0].length;
        int[] dp = new int[width];

        for(int r = 0; r<grid.length; r++){
            int[] row = grid[r];
            for(int i = 0; i<width; i++){
                if(r == 0){
                    dp[i] = row[i] + (i > 0? dp[i-1] : 0);
                }else{
                    dp[i] = row[i] + (i > 0? Math.min(dp[i], dp[i-1]) : dp[i]);
                }

            }
        }
        return dp[width-1];
    }
}
