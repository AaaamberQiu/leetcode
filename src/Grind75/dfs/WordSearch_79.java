package Grind75.dfs;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WordSearch_79 {

    public boolean exist(char[][] board, String word) {
        char startChar = word.charAt(0);

        int m = board.length, n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        for(int i = 0; i<m; i++){
            for(int j = 0; j<n; j++){
                if(board[i][j] == startChar){
                    if(dfs(board, word, 0, i, j, visited)) return true;
                }
            }
        }
        return false;
    }


    public boolean dfs(char[][] board, String word, int pos, int i, int j, boolean[][] visited){
        if(pos == word.length()) return true;
        int m = board.length, n = board[0].length;
        if(i < 0 || i >= m || j < 0 || j >= n || visited[i][j] || board[i][j] != word.charAt(pos)) return false;

        visited[i][j] = true;
        int[][] directions = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
        for(int[] dir : directions){
            int x = i + dir[0];
            int y = j + dir[1];
            if(dfs(board, word, pos+1, x, y, visited)) return true;
        }
        visited[i][j] = false;
        return false;
    }


    public static void main(String[] args) {
        char[][] input = {{'A','B','C','E'}, {'S','F','C','S'}, {'A','D','E','E'}};
        WordSearch_79 solution = new WordSearch_79();
        boolean ret = solution.exist(input, "ABCB");
        System.out.println(ret);
    }



}
