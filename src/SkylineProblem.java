import java.util.*;

public class SkylineProblem {

    /**
     * 218. The Skyline Problem
     * <p>
     * Data structure: TreeMap (records all points and sort by left index, nlogn) + max heap (store cluster of buildings, nlogn)
     * Key points:
     * 1) if meet left index, put it into heap; otherwise, remove it from heap;
     * 2) if heap size is empty, record the right index
     * 3) Compare the largest height with previous height
     *
     * divide and conquer solution: https://leetcode.com/problems/the-skyline-problem/discuss/61281/Java-divide-and-conquer-solution-beats-96
     * </p>
     *
     * @param buildings
     * @return
     */
    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> ret = new ArrayList<>();
        TreeMap<Integer, List<Building>> map = new TreeMap<>();
        for (int[] b : buildings) {
            Building building = new Building(b);
            map.computeIfAbsent(building.left, key -> new ArrayList<>());
            map.computeIfAbsent(building.right, key -> new ArrayList<>());
            map.get(building.left).add(building);
            map.get(building.right).add(building);
        }

        PriorityQueue<Building> maxHeap = new PriorityQueue<>(new Comparator<Building>() {
            @Override
            public int compare(Building o1, Building o2) {
                return o2.height - o1.height;
            }
        });

        for (Integer x : map.keySet()) {
            List<Building> bs = map.get(x);

            for (Building building : bs) {
                // start to handle
                if (building.left == x) {
                    maxHeap.add(building);
                } else {
                    maxHeap.remove(building);
                }
            }

            // reach the end of building cluster, record right point
            if (maxHeap.isEmpty()) {
                ret.add(Arrays.asList(x, 0));
            }
            // record left point
            else {
                int maxHeight = maxHeap.peek().height;
                // if current max height is at the same line of the previous result, just ignore
                // each line can only record one left point
                if (ret.size() == 0 || ret.get(ret.size() - 1).get(1) != maxHeight) {
                    ret.add(Arrays.asList(x, maxHeight));
                }
            }
        }
        return ret;

    }


    class Building {
        int left;
        int right;
        int height;

        Building(int[] building) {
            left = building[0];
            right = building[1];
            height = building[2];
        }
    }
}
