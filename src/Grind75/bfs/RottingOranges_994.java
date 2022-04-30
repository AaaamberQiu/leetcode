package Grind75.bfs;

import java.util.LinkedList;
import java.util.Queue;

public class RottingOranges_994 {

    public int orangesRotting(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int total = 0;
        // push rotten oranges into queue
        for(int i = 0; i<m; i++){
            for(int j = 0 ;j<n; j++){
                if(grid[i][j] == 2) queue.offer(new int[]{i,j});
                if(grid[i][j] != 0) total += 1;
            }
        }

        if(queue.isEmpty()) return total == 0 ? 0 : -1;

        int layer = 0, rottenCount = 0;
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        while(!queue.isEmpty()){
            int size = queue.size();
            rottenCount += size;
            layer += 1;
            for(int i = 0; i< size; i++){
                int[] rotten = queue.poll();
                for(int[] dir: directions){
                    int x = dir[0] + rotten[0];
                    int y = dir[1] + rotten[1];
                    if(x < 0 || x >= m || y < 0 || y >= n || grid[x][y] != 1) continue;
                    grid[x][y] = 2;
                    queue.offer(new int[]{x,y});
                }
            }
        }

        if(rottenCount < total) return -1;
        return layer;
    }

    public static void main(String[] args) {

    }
}
