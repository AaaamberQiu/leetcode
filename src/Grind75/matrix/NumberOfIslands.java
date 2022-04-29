package Grind75.matrix;

public class NumberOfIslands {

    public int numIslands(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        int count = 0;
        for(int i = 0; i<m; i++){
            for(int j = 0; j<n; j++){
                if(grid[i][j] == '1' && !visited[i][j]){
                    dfsHelper(grid, visited, i, j);
                    count += 1;
                }
            }
        }
        return count;
    }

    public static void dfsHelper(char[][] grid, boolean[][] visited, int i, int j){
        int m = grid.length, n = grid[0].length;
        if(i < 0 || i >= m || j < 0 || j >= n || visited[i][j] || grid[i][j] != '1') return;
        visited[i][j] = true;

        int[][] directions = {{-1,0}, {1,0}, {0, -1}, {0, 1}};
        for(int[] dir: directions){
            int x = i + dir[0];
            int y = j + dir[1];
            dfsHelper(grid, visited, x, y);
        }
    }
}
