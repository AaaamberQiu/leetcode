package Grind75.graph;

import java.util.*;

public class CourseSchedule_207 {

    public boolean canFinish_bfs(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        int[] inDegree = new int[numCourses];
        for(int[] pre: prerequisites){
            map.computeIfAbsent(pre[1], k -> new ArrayList<>());
            map.get(pre[1]).add(pre[0]);
            inDegree[pre[0]] += 1;
        }

        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i<inDegree.length; i++){
            if(inDegree[i] == 0) queue.offer(i);
        }

        int count = 0;
        while(!queue.isEmpty()){
            int course = queue.poll();
            count += 1;
            List<Integer> children = map.getOrDefault(course, new ArrayList<>());
            for(int child: children){
                inDegree[child]-=1;
                if(inDegree[child] == 0) queue.offer(child);
            }
        }
       return count == numCourses;
    }


    public boolean canFinish_dfs(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> map = buildMap(prerequisites);
        int[] visited = new int[numCourses];

        for(int i = 0; i < numCourses; i++){
            if(cycleDetect(map, visited, i)) return false;
        }
        return true;
    }


    public static Map<Integer, List<Integer>> buildMap(int[][] prerequisites){
        Map<Integer, List<Integer>> map = new HashMap<>();
        for(int[] pre: prerequisites){
            map.computeIfAbsent(pre[1], k -> new ArrayList<>());
            map.get(pre[1]).add(pre[0]);
        }
        return map;
    }

    public static boolean cycleDetect(Map<Integer, List<Integer>> map, int[] visited, int course){
        if(visited[course] != 0) return visited[course] == 1;

        visited[course] = 1;
        for(int child: map.getOrDefault(course, new ArrayList<>())){
            if(cycleDetect(map, visited, child)) return true;
        }
        visited[course] = 2;
        return false;
    }
}
