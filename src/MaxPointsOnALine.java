import java.nio.channels.Pipe;
import java.util.*;

public class MaxPointsOnALine {

    /**
     * 149. Max Points on a Line
     * @param points
     * @return
     */
    public int maxPoints(int[][] points) {
        // In general: 对于每一个点，找剩下和它在一条线上的点 => 在一条线意味着 斜率k相同, 一个点+k 确定一条直线
        // => map key = k, val = #points in the line
        // special case: duplicate points, k=0, k=infinite

        int len = points.length;
        if(len < 2) return 1;
        int max = 1;

        Set<Point> used = new HashSet<>();
        for(int i = 0; i<len; i++){
            Point curr = new Point(points[i]);
            if(used.contains(curr)) continue;

            int localMax = 1;
            int same = 0;
            Map<Double, Integer> ratioCountMap = new HashMap<>();
            // check the rest points
            for(int j = i+1; j<len; j++){
                Point next = new Point(points[j]);
                if(curr.equals(next)) {
                    same += 1;
                }else{
                    double ratio = getRatio(next, curr);
                    // default val = 1, for each k, it at least has one point
                    ratioCountMap.put(ratio, ratioCountMap.getOrDefault(ratio, 1)+1);
                    localMax = Math.max(localMax, ratioCountMap.get(ratio));
                }
            }
            max = Math.max(localMax + same, max);
            used.add(curr);
        }
        return max;

    }


    // 不用ratio做key，用dx dy一起做key，因为double也有可能精度不够
    public int maxPoints2(int[][] points) {
        int len = points.length;
        if(len < 2) return 1;
        int max = 1;

        Set<Point> used = new HashSet<>();
        for(int i = 0; i<len; i++){
            Point curr = new Point(points[i]);
            if(used.contains(curr)) continue;

            int localMax = 1;
            int same = 0;
            Map<Point, Integer> ratioCountMap = new HashMap<>();
            // check the rest points
            for(int j = i+1; j<len; j++){
                Point next = new Point(points[j]);
                if(curr.equals(next)) {
                    same += 1;
                }else{
                    int dx = next.x-curr.x, dy = next.y-curr.y;
                    int gcd = gcd(dx, dy);
                    Point base = new Point(dx/gcd, dy/gcd);
                    // default val = 1, for each k, it at least has one point
                    ratioCountMap.put(base, ratioCountMap.getOrDefault(base, 1)+1);
                    localMax = Math.max(localMax, ratioCountMap.get(base));
                }
            }
            max = Math.max(localMax + same, max);
            used.add(curr);
        }
        return max;

    }

    // gcd: greatest common divisor
    private static int gcd(int a,int b){
        if (b==0) return a;
        else return gcd(b,a%b);

    }


    private static double getRatio(Point a, Point b){
        // k = infinite
        if(a.x == b.x) return Double.MAX_VALUE;
        // special case，-0.0 和 0.0是一样的，但是-k 和k不一样
        else if(a.y == b.y) return 0.0;
        else return ((double) a.y - b.y)/((double) a.x - b.x);
    }

    class Point{
        int x;
        int y;
        Point(int[] array){
            this.x = array[0];
            this.y = array[1];
        }

        Point(int x, int y){
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    public static void main(String[] args) {
        int[][] testcase = new int[3][2];
        testcase[0] = new int[]{2,3};
        testcase[1] = new int[]{3,3};
        testcase[2] = new int[]{-5,3};
        MaxPointsOnALine solution = new MaxPointsOnALine();
        int ret = solution.maxPoints(testcase);
        System.out.println(ret);
    }
}
