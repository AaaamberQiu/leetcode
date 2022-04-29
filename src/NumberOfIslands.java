public class NumberOfIslands {

    /**
     * 200. Number of Islands
     * @param grid
     * @return
     */
    public int numIslands(char[][] grid) {
        int rowNum = grid.length;
        if(rowNum == 0) return 0;
        int colNum = grid[0].length;

        int count = 0;
        for(int i = 0; i<rowNum; i++){
            for(int j = 0; j<colNum; j++){
                // extend land until meet water
                if(grid[i][j] == '1'){
                    mark(grid, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    public static void mark(char[][] grid, int row, int col){
        int rowNum = grid.length;
        int colNum = grid[0].length;
        if(row < 0 || row >= rowNum || col < 0 || col >= colNum || grid[row][col] != '1') return;
        grid[row][col] = '*';
        mark(grid, row-1, col);
        mark(grid, row+1, col);
        mark(grid, row, col-1);
        mark(grid, row, col+1);
    }
}
