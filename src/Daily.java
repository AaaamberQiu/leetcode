import base.ListNode;

import java.util.*;

public class Daily {
    public static final int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int longestIncreasingPath(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;

        // dp[i][j] the len of the longest increasing path ending at (i,j)
        int[][] dp = new int[m][n];

        int max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int curr = dfs(matrix, i, j, dp);
                max = Math.max(max, curr);
            }
        }
        return max;
    }

    public static int dfs(int[][] matrix, int i, int j, int[][] dp) {
        if (dp[i][j] != 0) return dp[i][j];

        int value = matrix[i][j];
        dp[i][j] = 1;
        int m = matrix.length, n = matrix[0].length;
        for (int[] dir : directions) {
            int x = i + dir[0];
            int y = j + dir[1];
            if (x < 0 || x >= m || y < 0 || y >= n || matrix[i - 1][j] <= value) continue;
            dp[i][j] = Math.max(dp[i][j], 1 + dfs(matrix, x, y, dp));
        }
        return dp[i][j];
    }

}


