import java.util.*;

public class LoudAndRich {

    /**
     * 851. Loud and Rich
     *
     * @param richer
     * @param quiet
     * @return
     */
    public int[] loudAndRich(int[][] richer, int[] quiet) {
        int n = quiet.length;
        Map<Integer, Set<Integer>> richerMap = new HashMap<>();
        Map<Integer, Set<Integer>> outEdges = new HashMap<>();
        int[] inDegree = new int[n];

        for (int[] rich : richer) {
            outEdges.computeIfAbsent(rich[0], key -> new HashSet<>());
            outEdges.get(rich[0]).add(rich[1]);

            inDegree[rich[1]] += 1;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 0) queue.offer(i);
        }

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int t = 0; t < size; t++) {
                int person = queue.poll();
                for (int poorer : outEdges.getOrDefault(person, new HashSet<>())) {
                    richerMap.computeIfAbsent(poorer, key -> new HashSet<>());
                    richerMap.get(poorer).add(person);
                    richerMap.get(poorer).addAll(richerMap.getOrDefault(person, new HashSet<>()));

                    inDegree[poorer] -= 1;
                    if (inDegree[poorer] == 0) queue.offer(poorer);
                }
            }
        }

        int[] ret = new int[n];
        for (int i = 0; i < n; i++) {
            Set<Integer> richerPersons = richerMap.getOrDefault(i, new HashSet<>());
            ret[i] = findQuietest(quiet, richerPersons, i);
        }
        return ret;
    }

    public static int findQuietest(int[] quiet, Set<Integer> candidates, int self) {
        if (candidates.isEmpty()) return self;
        int ret = -1, min = quiet[self];
        for (int c : candidates) {
            if (quiet[c] <= min) {
                ret = c;
                min = quiet[c];
            }
        }
        return ret == -1 ? self : ret;
    }


    // DFS
    public int[] loudAndRich2(int[][] richer, int[] quiet) {
        int n = quiet.length;
        Map<Integer, Set<Integer>> richerMap = new HashMap<>();

        for (int[] rich : richer) {
            richerMap.computeIfAbsent(rich[1], key -> new HashSet<>());
            richerMap.get(rich[1]).add(rich[0]);
        }

        int[] ret = new int[n];
        Arrays.fill(ret, -1);
        for (int i = 0; i < n; i++) {
            findQuietestRicher(i, quiet, richerMap, ret);
        }
        return ret;
    }


    public static int findQuietestRicher(int i, int[] quiet, Map<Integer, Set<Integer>> richerMap, int[] ret) {
        if (ret[i] > 0) return ret[i];
        Set<Integer> richers = richerMap.getOrDefault(i, new HashSet<>());
        ret[i] = i;
        for (int richer : richers) {
            int parent = findQuietestRicher(richer, quiet, richerMap, ret);
            if (quiet[ret[i]] > quiet[parent]) {
                ret[i] = parent;
            }
        }
        return ret[i];
    }
}
