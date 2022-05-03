package Grind75.tree;

import java.util.*;

public class MinimumHeightTrees_310 {

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if(n == 1) return Arrays.asList(0);
        // convert to graph
        Map<Integer, List<Integer>> graph = new HashMap<>();
        int[] degree = new int[n];
        for(int[] e: edges){
            graph.computeIfAbsent(e[0], k -> new ArrayList<>());
            graph.computeIfAbsent(e[1], k -> new ArrayList<>());

            graph.get(e[0]).add(e[1]);
            graph.get(e[1]).add(e[0]);

            degree[e[0]] += 1;
            degree[e[1]] += 1;
        }


        Queue<Integer> queue = new LinkedList<>();
        // find leaves
        for(int i = 0; i<degree.length; i++){
            if(degree[i] == 1) queue.offer(i);
        }

        while(n > 2){
            int size = queue.size();
            n -= size;
            for(int i = 0; i<size; i++){
                Integer curr = queue.poll();
                for(Integer neighbor: graph.getOrDefault(curr, new ArrayList<>())){
                    degree[neighbor] -= 1;
                    if(degree[neighbor] == 1) queue.offer(neighbor);
                }
            }
        }
        return new ArrayList<>(queue);
    }

}
