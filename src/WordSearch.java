import base.TrieNode;

import java.util.*;

public class WordSearch {


    /**
     * 79. Word Search
     * <p>
     *     1) each char can be used once => used 2D array
     *     2) if any neighbours could build the string, return true
     * </p>
     * @param board
     * @param word
     * @return
     */
    int rowNum = 0;
    int colNum = 0;
    public boolean exist(char[][] board, String word) {
        rowNum = board.length;
        colNum = board[0].length;
        // each char can only used once
        boolean[][] used = new boolean[rowNum][colNum];
        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < colNum; j++) {
                if (board[i][j] == word.charAt(0)) {
                    if (helper(board, word, i, j, 0, used)) return true;
                }
            }
        }
        return false;
    }

    public boolean helper(char[][] board, String word, int row, int col, int pos, boolean[][] used) {
        // check length first
        if (pos == word.length()) return true;
        if (row < 0 || row >= rowNum || col < 0 || col >= colNum
                || board[row][col] != word.charAt(pos) || used[row][col]) return false;
        used[row][col] = true;
        if (helper(board, word, row + 1, col, pos + 1, used)
                || helper(board, word, row - 1, col, pos + 1, used)
                || helper(board, word, row, col + 1, pos + 1, used)
                || helper(board, word, row, col - 1, pos + 1, used)) {
            return true;
        }
        used[row][col] = false;
        return false;
    }


    /**
     * 212. Word Search II
     * <p>
     *     1) check word one by one leads to TLE
     *     2) use TrieNode
     * </p>
     * @param board
     * @param words
     * @return
     */
    public List<String> findWords(char[][] board, String[] words) {
        int rowNum = board.length, colNum = board[0].length;
        Set<String> set = new HashSet<>();
        for(int i = 0; i<rowNum; i++){
            for(int j = 0; j<colNum; j++){
                char c = board[i][j];
                for(String word: words){
                    boolean[][] used = new boolean[rowNum][colNum];
                    if(word.charAt(0) == c){
                        if(findWordHelper(board, used, i, j, word, 0)){
                            set.add(word);
                        }
                    }
                }
            }
        }
        List<String> ret = new ArrayList<>();
        ret.addAll(set);
        return ret;
    }


    public static boolean findWordHelper(char[][] board, boolean[][] used, int row, int col, String word, int pos){
        int rowNum = board.length, colNum = board[0].length;
        if(pos == word.length()) return true;
        if(row < 0 || row >= rowNum || col < 0 || col >= colNum) return false;
        if(board[row][col] != word.charAt(pos)) return false;
        if(used[row][col]) return false;

        used[row][col] = true;
        if(findWordHelper(board, used, row-1, col, word, pos+1)
                ||findWordHelper(board, used, row+1, col, word, pos+1)
                ||findWordHelper(board, used, row, col-1, word, pos+1)
                ||findWordHelper(board, used, row, col+1, word, pos+1)){
            return true;
        }
        used[row][col] = false;
        return false;
    }



    public List<String> findWords2(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        TrieNode root = buildTrie(words);
        for(int i = 0; i<board.length; i++){
            for (int j = 0; j<board[0].length; j++){
                dfs(board, i, j, root, res);
            }
        }
        return res;
    }


    public static void dfs(char[][] board, int row, int col, TrieNode root, List<String> res){
        int rowNum = board.length, colNum = board[0].length;
        if(row < 0 || row >= rowNum || col < 0 || col >= colNum || board[row][col] == '*') return;
        char c = board[row][col];
        TrieNode next = root.map.get(c);
        if(next == null) return;
        if(next.isEnd){
            if(!next.word.isEmpty()) {
                res.add(next.word);
                next.word = "";
            }
            // if return here, words with same prefix will be only recorded once.
            //return;
        }

        board[row][col] = '*';
        dfs(board, row-1, col, next, res);
        dfs(board, row+1, col, next, res);
        dfs(board, row, col-1, next, res);
        dfs(board, row, col+1, next, res);
        board[row][col] = c;
    }


    public static TrieNode buildTrie(String[] words){
        TrieNode root = new TrieNode();
        for(String s: words){
            TrieNode prev = root;
            for(int i = 0; i<s.length(); i++){
                char c = s.charAt(i);
                if(!prev.map.containsKey(c)){
                    prev.map.put(c, new TrieNode());
                }
                prev = prev.map.get(c);
                prev.word = s.substring(0, i+1);
            }
            prev.isEnd = true;
        }
        return root;
    }


    public static void main(String[] args) {
        WordSearch ws = new WordSearch();
        char[][] board = new char[4][4];
        board[0] = new char[]{'o', 'a', 'a', 'n'};
        board[1] = new char[]{'e', 't', 'a', 'e'};
        board[2] = new char[]{'i', 'h', 'k', 'r'};
        board[3] = new char[]{'i', 'f', 'l', 'v'};
        ws.findWords2(board, new String[]{"eat", "oath"});
    }
}
