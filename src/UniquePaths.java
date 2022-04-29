public class UniquePaths {

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int y = obstacleGrid.length;
        int x = obstacleGrid[0].length;

        int[][] dp = new int[y][x];
        dp[0][0] = obstacleGrid[0][0] == 1? 0:1;
        for(int i = 1; i<y; i++){
            if(dp[i-1][0] == 0 || obstacleGrid[i][0] == 1){
                dp[i][0] = 0;
            }else{
                dp[i][0] = 1;
            }
        }

        for(int j = 1; j<x; j++){
            if(dp[0][j-1] == 0 || obstacleGrid[0][j] == 1){
                dp[0][j] = 0;
            }else{
                dp[0][j] = 1;
            }
        }

        for(int i = 1; i<y; i++){
            for(int j = 1; j<x; j++){
                if(obstacleGrid[i][j] == 1){
                    dp[i][j] = 0;
                }else{
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                }
            }
        }
        return dp[y-1][x-1];
    }

    /**
     * Since we only use value at dp[i-1][j] and dp[i][j-1] to update dp[i][j], we could use 1D array to store
     * prev dp[j] => last row dp[j] => dp[i-1][j]
     * dp[j-1] => have updated => current row dp[j-1] => dp[i][j-1]
     * @param obstacleGrid
     * @return
     */
    public int uniquePathsWithObstacles2(int[][] obstacleGrid) {
        int width = obstacleGrid[0].length;
        int[] dp = new int[width];

        dp[0] = obstacleGrid[0][0] == 1 ? 0: 1;
        for(int[] row: obstacleGrid){
            for(int i = 0; i<width; i++){
                if(row[i] == 1) dp[i] = 0;
                else if(i > 0){
                    dp[i] += dp[i-1];
                }
            }
        }
        return dp[width-1];
    }

}
