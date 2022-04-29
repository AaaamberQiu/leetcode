package Grind75.matrix;

public class FloodFill_733 {

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        boolean[][] visit = new boolean[image.length][image[0].length];
        dfs(image, visit, sr, sc, newColor, image[sr][sc]);
        return image;
    }


    public static void dfs(int[][] image, boolean[][] visit, int sr, int sc, int newColor, int prevColor){
        int m = image.length;
        int n = image[0].length;
        if(sr < 0 || sr >= m || sc < 0 || sc >= n || image[sr][sc] != prevColor || visit[sr][sc]) return;

        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        image[sr][sc] = newColor;
        visit[sr][sc] = true;
        for(int[] dir: directions){
            int x = sr + dir[0];
            int y = sc + dir[1];
            dfs(image, visit, x, y, newColor, prevColor);
        }
    }

    public static void main(String[] args) {
        FloodFill_733 solution = new FloodFill_733();
        int[][] image = {{0,0,0}, {0,1,1}};
        int[][] ret = solution.floodFill(image, 1, 1, 1);
        System.out.println();
    }

}
