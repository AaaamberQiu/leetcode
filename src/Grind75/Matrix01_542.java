package Grind75;

import java.util.LinkedList;
import java.util.Queue;

public class Matrix01_542 {

    public int[][] updateMatrix(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int[][] ret = new int[m][n];
        boolean[][] visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0) continue;
                ret[i][j] = dfs(mat, i, j, 0, visited);
            }
        }
        return ret;
    }

    public static int dfs(int[][] mat, int i, int j, int temp, boolean[][] visited) {
        int m = mat.length, n = mat[0].length;
        if (mat[i][j] == 0) {
            return temp;
        }

        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int min = Integer.MAX_VALUE;
        visited[i][j] = true;
        for (int[] dir : directions) {
            int x = i + dir[0];
            int y = j + dir[1];
            if (x < 0 || x >= m || y < 0 || y >= n || visited[x][y]) continue;
            min = Math.min(min, dfs(mat, x, y, temp + 1, visited));
        }
        visited[i][j] = false;
        return min;
    }

    // DP
    public int[][] updateMatrix_2(int[][] mat) {
        int m = mat.length, n = mat[0].length, INF = m + n;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0) continue;
                int top = i - 1 >= 0 ? mat[i - 1][j] : INF;
                int left = j - 1 >= 0 ? mat[i][j - 1] : INF;
                mat[i][j] = Math.min(top, left) + 1;
            }
        }

        for(int i = m-1; i>=0; i--){
            for(int j = n-1; j>=0; j--){
                if (mat[i][j] == 0) continue;
                int right = i+1 < m ? mat[i+1][j] : INF;
                int bottom = j+1 < n? mat[i][j+1] : INF;
                mat[i][j] = Math.min(mat[i][j], Math.min(right, bottom)+1);
            }
        }
        return mat;
    }

    // BFS
    public int[][] updateMatrix_3(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        Queue<int[]> queue = new LinkedList<>();
        for(int i = 0; i<m; i++){
            for(int j = 0; j<n; j++){
                if(mat[i][j] == 0) queue.offer(new int[]{i,j});
                else mat[i][j] = -1;
            }
        }
        while(!queue.isEmpty()){
            int[] point = queue.poll();
            int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
            for (int[] dir : directions) {
                int x = point[0] + dir[0];
                int y = point[1] + dir[1];
                if (x < 0 || x >= m || y < 0 || y >= n || mat[x][y] >= 0) continue;
                mat[x][y] = mat[point[0]][point[1]] + 1;
                queue.offer(new int[]{x,y});
            }
        }
        return mat;
    }



    public static void main(String[] args) {
        int[][] mat = {{0, 0, 0}, {0, 1, 0}, {1, 1, 1}};
        Matrix01_542 solution = new Matrix01_542();
        solution.updateMatrix(mat);
    }
}
