import java.util.*;

public class FindEventualSafeStates {

    /**
     * 802. Find Eventual Safe States
     * <p>
     *     BFS: start from safe node, record nodes added into queue
     *     DFS: find nodes that not in a cycle
     * </p>
     * @param graph
     * @return
     */
    public List<Integer> eventualSafeNodes(int[][] graph) {
        // find 0-outDegree nodes
        Queue<Integer> queue = new LinkedList<>();

        // key = node index, val = list of in-edge
        Map<Integer, List<Integer>> map = new HashMap<>();
        for(int i = 0; i < graph.length; i++){
            int[] outEdges = graph[i];
            if(outEdges.length == 0) queue.offer(i);
            for(int out: outEdges){
                map.computeIfAbsent(out, key -> new ArrayList<>());
                map.get(out).add(i);
            }

        }

        List<Integer> ret = new ArrayList<>();
        // check its in edges
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i<size; i++){
                int top = queue.poll();
                ret.add(top);
                // update from node
                for(int fromNode: map.getOrDefault(top, new ArrayList<>())){
                    delete(graph[fromNode], top);
                    if(isEmpty(graph[fromNode])) queue.offer(fromNode);
                }
            }
        }
        Collections.sort(ret);
        return ret;
    }


    public static void delete(int[] outEdges, int nodeIndex){
        for(int i = 0; i<outEdges.length; i++){
            if(outEdges[i] == nodeIndex) outEdges[i] = -1;
        }
    }

    public static boolean isEmpty(int[] outEdges){
        for(int out : outEdges){
            if(out != -1) return false;
        }
        return true;
    }


    // DFS: if node is not in a cycle, it is safe
    public List<Integer> eventualSafeNodes2(int[][] graph) {
        List<Integer> ret = new ArrayList<>();
        int nodeNum = graph.length;
        if(nodeNum == 0) return ret;

        int[] nodes = new int[nodeNum];
        for(int i = 0; i<nodeNum; i++){
            // if node not in cycle, it is safe
            if(!hasCycle(nodes, graph, i)) ret.add(i);
        }
        return ret;
    }

    // 0 - not visited; 1 - visiting; 2 - done
    public static boolean hasCycle(int[] nodes, int[][] graph, int i){
        if(nodes[i] != 0) return nodes[i] == 1;
        nodes[i] = 1;
        for(int neighbor: graph[i]){
            if(hasCycle(nodes, graph, neighbor)) return true;
        }
        nodes[i] = 2;
        return false;
    }

}
