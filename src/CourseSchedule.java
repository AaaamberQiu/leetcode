import java.util.*;

public class CourseSchedule {

    /**
     * 207. Course Schedule
     * <p>
     *     Topological sorting
     *     DFS: int[] visited array; 0-not visited; 1-being visited; 2-done; if meet visited[i] = 1, has a cycle
     *     BFS: start from zero in degree node
     * </p>
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if(numCourses == 0 || prerequisites.length == 0) return true;

        Map<Integer, List<Integer>> map = new HashMap<>();
        for(int[] pre: prerequisites){
            List<Integer> pres = map.getOrDefault(pre[0], new ArrayList<>());
            pres.add(pre[1]);
            map.put(pre[0],pres);
        }

        int[] visited = new int[numCourses];
        for(int i = 0; i<numCourses; i++){
            if(!dfs(map, visited, i)){
                return false;
            }
        }
        return true;
    }

    public static boolean dfs(Map<Integer, List<Integer>> map, int[] visited, int courseNum){
        if(visited[courseNum] == 1) return false; // has been visited while visiting its children - detect cycle
        if(visited[courseNum] == 2) return true;
        visited[courseNum] = 1; // mark it being visited
        List<Integer> pres = map.getOrDefault(courseNum, new ArrayList<>());
        for(int pre: pres){
            if(!dfs(map, visited, pre)) return false;
        }
        // mark it done
        visited[courseNum] = 2;
        return true;
    }


    // 1) add all zero in degree course into queue
    // 2) update the in degree of its children
    // 3) once its child in degree == 0, add into queue
    public boolean canFinishBFS(int numCourses, int[][] prerequisites) {
        if(numCourses == 0 || prerequisites.length == 0) return true;

        // key is pre-course
        Map<Integer, List<Integer>> map = new HashMap<>();
        int[] inDegree = new int[numCourses];
        for(int[] pre: prerequisites){
            List<Integer> pres = map.getOrDefault(pre[1], new ArrayList<>());
            pres.add(pre[0]);
            map.put(pre[1],pres);
            inDegree[pre[0]] += 1;
        }

        int count = 0;
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i<inDegree.length; i++){
            if(inDegree[i] == 0) queue.offer(i);
        }

        while(!queue.isEmpty()){
            int course = queue.poll();
            count++;
            for(int child: map.getOrDefault(course, new ArrayList<>())){
                inDegree[child]-=1;
                if(inDegree[child] == 0) queue.offer(child);
            }
        }
        return count == numCourses;
    }



    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        int[] inDegree = new int[numCourses];
        for(int[] pre: prerequisites){
            List<Integer> pres = map.getOrDefault(pre[1], new ArrayList<>());
            pres.add(pre[0]);
            map.put(pre[1],pres);
            inDegree[pre[0]] += 1;
        }

        List<Integer> orders = new ArrayList<>();

        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i<inDegree.length; i++){
            if(inDegree[i] == 0) queue.offer(i);
        }

        while(!queue.isEmpty()){
            int course = queue.poll();
            orders.add(course);
            for(int child: map.getOrDefault(course, new ArrayList<>())){
                inDegree[child]-=1;
                if(inDegree[child] == 0) queue.offer(child);
            }
        }

        if(orders.size() != numCourses) return new int[]{};
        int[] ret = new int[orders.size()];
        for(int i = 0; i<orders.size(); i++){
            ret[i] = orders.get(i);
        }
        return ret;
    }


    /**
     * 1462. Course Schedule IV
     * <p>
     *     DFS: TLE
     *     BFS:
     *     Floyd-Warshall: will output the min distance of any vertices, can modified it to output if any vertices is connected or not
     * </p>
     * @param numCourses
     * @param prerequisites
     * @param queries
     * @return
     */
    // DFS
    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        List<Boolean> ret = new ArrayList<>();

        Map<Integer, List<Integer>> outEdges = new HashMap<>();
        for(int[] pre: prerequisites){
            outEdges.computeIfAbsent(pre[0], key -> new ArrayList<>());
            outEdges.get(pre[0]).add(pre[1]);
        }

        for(int[] query: queries){
            int pre = query[0], curr = query[1];
            ret.add(isParent(pre, curr, outEdges));
        }
        return ret;
    }

    public static boolean isParent(int pre, int curr, Map<Integer, List<Integer>> outEdges){
        if(pre == curr) return true;
        for(int child : outEdges.getOrDefault(pre, new ArrayList<>())){
            if(isParent(child, curr, outEdges)) return true;
        }
        return false;
    }


    // BFS: pre-compute map keep course and all pres of it
    // in topological sorting: add curr as parent, and parents of curr are also parents
    public List<Boolean> checkIfPrerequisite2(int numCourses, int[][] prerequisites, int[][] queries) {
        Map<Integer, Set<Integer>> outEdges = new HashMap<>();
        // key = course, value: all prerequisites
        Map<Integer, Set<Integer>> preMap = new HashMap<>();
        int[] inDegree = new int[numCourses];
        for(int[] pre: prerequisites){
            outEdges.computeIfAbsent(pre[0], key -> new HashSet<>());
            outEdges.get(pre[0]).add(pre[1]);
            inDegree[pre[1]] += 1;
        }

        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i<numCourses; i++){
            if(inDegree[i] == 0) queue.add(i);
        }

        while(!queue.isEmpty()){
            int size = queue.size();
            for(int count = 0; count<size; count++){
                int curr = queue.poll();

                Set<Integer> children = outEdges.getOrDefault(curr, new HashSet<>());
                for(int child : children){
                    preMap.computeIfAbsent(child, key -> new HashSet<>());
                    // curr course is a pre
                    preMap.get(child).add(curr);
                    // pre of curr is also pres
                    preMap.get(child).addAll(preMap.getOrDefault(curr, new HashSet<>()));

                    inDegree[child] -= 1;
                    if(inDegree[child] == 0) queue.add(child);
                }
            }
        }

        List<Boolean> ret = new ArrayList<>();
        for(int[] query: queries){
            int pre = query[0], curr = query[1];
            Set<Integer> allPres = preMap.getOrDefault(curr, new HashSet<>());
            ret.add(allPres.contains(pre));
        }
        return ret;
    }



    // Floyd-Warshall
    public List<Boolean> checkIfPrerequisite3(int numCourses, int[][] prerequisites, int[][] queries) {
        boolean[][] connected = new boolean[numCourses][numCourses];
        for(int[] p: prerequisites){
            connected[p[0]][p[1]] = true; // p0 -> p1
        }

        for(int k = 0; k<numCourses; k++){
            for(int i = 0; i<numCourses; i++){
                for(int j = 0; j < numCourses; j++){
                    connected[i][j] = connected[i][j] || (connected[i][k] && connected[k][j]);
                }
            }
        }
        List<Boolean> ret = new ArrayList<>();
        for(int[] query: queries){
            ret.add(connected[query[0]][query[1]]);
        }
        return ret;
    }
}
