import java.util.LinkedList;
import java.util.Queue;

public class SurroundedRegions {

    /**
     *
     * @param board
     */
    public void solve(char[][] board) {
        int width = board[0].length;
        int height = board.length;

        for (int w = 0; w < width; w++) {
            if (board[0][w] == 'O') {
                mark(board, 0, w);
            }

            if(board[height-1][w] == 'O'){
                mark(board, height-1, w);
            }
        }

        for(int h = 0; h < height; h++){
            if(board[h][0] == 'O'){
                mark(board, h, 0);
            }
            if(board[h][width-1] == 'O'){
                mark(board, h, width-1);
            }
        }


        for(int h = 0; h < height; h++){
            for(int w = 0; w < width; w++){
                if(board[h][w] == 'X'){
                    continue;
                }else if(board[h][w] == 'O'){
                    board[h][w] = 'X';
                }else{
                    board[h][w] = 'O';
                }
            }
        }
    }


    public static void mark(char[][] board, int h, int w) {
        int width = board[0].length;
        int height = board.length;
        if (h < 0 || h >= height) return;
        if (w < 0 || w >= width) return;
        if (board[h][w] == 'O') {
            board[h][w] = '*';
        }else{
            return;
        }
        mark(board, h - 1, w);
        mark(board, h + 1, w);
        mark(board, h, w - 1);
        mark(board, h, w + 1);
    }

    // BFS: add the 'O' in edges into Queue;
    // then poll them from queue one by one to check its surrounding
    // once its surrounding is 'O', push into queue
    public void solve2(char[][] board) {
        int width = board[0].length;
        int height = board.length;

        Queue<int[]> queue = new LinkedList<>();
        for (int w = 0; w < width; w++) {
            if (board[0][w] == 'O') {
                board[0][w] = '*';
                queue.offer(new int[]{0, w});
            }

            if(board[height-1][w] == 'O'){
                board[height-1][w] = '*';
                queue.offer(new int[]{height-1, w});
            }
        }

        for(int h = 0; h < height; h++){
            if(board[h][0] == 'O'){
                board[h][0] = '*';
                queue.offer(new int[]{h, 0});
            }
            if(board[h][width-1] == 'O'){
                board[h][width-1] = '*';
                queue.offer(new int[]{h, width-1});
            }
        }

        while(!queue.isEmpty()){
            int[] top = queue.poll();
            int h = top[0], w = top[1];
            if(h-1 >= 0 && board[h-1][w] == 'O'){
                board[h-1][w] = '*';
                queue.offer(new int[]{h-1, w});
            }
            if(h+1 <= height-1 && board[h+1][w] == 'O'){
                board[h+1][w] = '*';
                queue.offer(new int[]{h+1, w});
            }

            if(w-1 >= 0 && board[h][w-1] == 'O'){
                board[h][w-1] = '*';
                queue.offer(new int[]{h, w-1});
            }

            if(w+1 <= width-1 && board[h][w+1] == 'O'){
                board[h][w+1] = '*';
                queue.offer(new int[]{h, w+1});
            }
        }

        for(int h = 0; h < height; h++){
            for(int w = 0; w < width; w++){
                if(board[h][w] == 'X'){
                    continue;
                }else if(board[h][w] == 'O'){
                    board[h][w] = 'X';
                }else{
                    board[h][w] = 'O';
                }
            }
        }
    }

}
