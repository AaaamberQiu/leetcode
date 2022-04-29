import java.util.ArrayDeque;
import java.util.Queue;

public class GraphBipartite {

    /**
     * 785. Is Graph Bipartite?
     * @param graph
     * @return
     */
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        int[] colors = new int[n];

        //This graph might be a disconnected graph. So check each unvisited node.
        for(int i = 0; i<n; i++){
            if(colors[i] == 0 && !bfs(i, colors, graph)) return false;
        }
        return true;
    }

    public static boolean bfs(int i, int[] colors, int[][] graph){
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(i);
        colors[i] = 1;
        while(!queue.isEmpty()){
            int node = queue.poll();
            int color = colors[node];

            for(int neighbor: graph[node]){
                if(colors[neighbor] == color){
                    return false;
                }
                if(colors[neighbor] == 0){
                    colors[neighbor] = -color;
                    queue.add(neighbor);
                }
            }
        }
        return true;
    }


    public static boolean dfs(int node, int color, int[] colors, int[][] graph){
        if(colors[node] != 0) {
            return colors[node] == color;
        }

        colors[node] = color;
        for(int next: graph[node]){
            if(!dfs(next, -color, colors, graph)) return false;
        }
        return true;
    }

    public boolean isBipartite2(int[][] graph) {
        int n = graph.length;
        int[] parent = new int[n];

        for(int i = 0; i<n; i++){
            parent[i] = i;
        }

        for(int i = 0; i<n; i++){
            for(int neighbor: graph[i]){
                if(find(i, parent) == find(neighbor, parent)){
                    return false;
                }
                union(neighbor, graph[i][0], parent);

            }
        }
        return true;
    }

    public static int find(int x, int[] parent){
        while(x != parent[x]){
            x = parent[x];
        }
        return x;
    }

    public static void union(int x, int y, int[] parent){
        int parentX = find(x, parent);
        int parentY = find(y, parent);
        parent[parentY] = parentX;
    }
}
