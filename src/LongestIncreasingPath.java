import java.util.*;

public class LongestIncreasingPath {


    /**
     * 329. Longest Increasing Path in a Matrix
     * <p>
     *     dp: dfs + memory
     *     bfs
     * </p>
     * @param matrix
     * @return
     */
    public int longestIncreasingPath(int[][] matrix) {
        int rowNum = matrix.length, colNum = matrix[0].length;
        int[][] dp = new int[rowNum][colNum];

        int ret = 0;
        for(int i = 0; i<rowNum; i++){
            for(int j = 0; j<colNum; j++){
                ret = Math.max(ret, helper(matrix, dp, i, j));
            }
        }
        return ret;
    }

    public static int helper(int[][] matrix, int[][] dp, int row, int col){
        int rowNum = matrix.length, colNum = matrix[0].length;
        if(row < 0 || row >= rowNum || col < 0 || col >= colNum) return 0;
        if(dp[row][col] != 0) return dp[row][col];

        int res = 0, val = matrix[row][col];
        if(row-1 >= 0 && matrix[row-1][col] < val){
            res = Math.max(res, helper(matrix, dp, row-1, col));
        }
        if(row+1 < rowNum && matrix[row+1][col] < val){
            res = Math.max(res, helper(matrix, dp, row+1, col));
        }
        if(col-1 >= 0 && matrix[row][col-1] < val){
            res = Math.max(res, helper(matrix, dp, row, col-1));
        }
        if(col+1 < colNum && matrix[row][col+1] < val){
            res = Math.max(res, helper(matrix, dp, row, col+1));
        }

        dp[row][col] = res+1;
        return res+1;
    }


    // Topological sort
    // start from 0-inDegree node, check its out edges
    public int longestIncreasingPath2(int[][] matrix) {
        int rowNum = matrix.length, colNum = matrix[0].length;
        int[][] inDegree = new int[rowNum][colNum];

        for(int i = 0; i<rowNum; i++){
            for(int j = 0; j<colNum; j++){
                inDegree[i][j] = findNeighbors(matrix, i, j, false).size();
            }
        }

        Queue<int[]> queue = new LinkedList<>();
        for(int i = 0; i<rowNum; i++){
            for(int j = 0; j<colNum; j++){
                if(inDegree[i][j] == 0) queue.offer(new int[]{i,j});
            }
        }

        int ret = 0;
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int t = 0; t < size; t++){
                int[] point = queue.poll();
                List<int[]> neighbors = findNeighbors(matrix, point[0], point[1], true);
                for(int[] n: neighbors){
                    inDegree[n[0]][n[1]] -= 1;
                    if(inDegree[n[0]][n[1]] == 0){
                        queue.offer(new int[]{n[0], n[1]});
                    }
                }
            }
            ret += 1;
        }
        return ret;
    }


    public static List<int[]> findNeighbors(int[][] matrix, int row, int col, boolean largerNeighbors){
        int rowNum = matrix.length, colNum = matrix[0].length;
        List<int[]> ret = new ArrayList<>();
        int val = matrix[row][col];
        List<int[]> directions = Arrays.asList(new int[]{0,1}, new int[]{0,-1}, new int[]{-1,0}, new int[]{1,0});
        for(int[] dir : directions){
            int i = row + dir[0];
            int j = col + dir[1];
            if(i>= 0 && i < rowNum && j>=0 && j < colNum){
                if(largerNeighbors){
                    if(matrix[i][j] < val) ret.add(new int[]{i,j});
                }else{
                    if(matrix[i][j] > val) ret.add(new int[]{i,j});
                }

            }
        }
        return ret;
    }

    public static void main(String[] args) {
        LongestIncreasingPath solution = new LongestIncreasingPath();
        int[][] matrix = new int[3][3];
        matrix[0] = new int[]{9,9,4};
        matrix[1] = new int[]{6,6,8};
        matrix[2] = new int[]{2,1,1};
        int ret = solution.longestIncreasingPath2(matrix);
        System.out.println(ret);
    }
}
