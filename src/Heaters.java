import java.util.Arrays;
import java.util.TreeSet;

public class Heaters {

    public static int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(heaters);
        int ret = Integer.MIN_VALUE;
        for(int i = 0; i<houses.length; i++){
            int distance = findDistance(heaters, houses[i]);
            ret = Math.max(ret, distance);
        }
        return ret;
    }


    public static int findDistance(int[] heaters, int target){
        int start = 0, end = heaters.length-1;
        while(start <= end){
            int mid = (end - start)/2 + start;
            if(heaters[mid] == target) return 0;
            else if(heaters[mid] < target) start = mid+1;
            else end = mid-1;
        }

        // heater[end] left heater, heater[start] right heater
        if(end == -1) return Math.abs(heaters[start]-target);
        if(start == heaters.length) return Math.abs(heaters[end]-target);

        return Math.min(heaters[start]-target, target-heaters[end]);
    }


    // TreeSet to get floor and ceiling of the target
    public static int findRadius2(int[] houses, int[] heaters) {
        TreeSet<Integer> set = new TreeSet<>();
        for(int h : heaters) set.add(h);
        int ret = 0;
        for(int h: houses){
            Integer left = set.floor(h);
            Integer right = set.ceiling(h);

            int required = Integer.MAX_VALUE;
            if(left != null) required = Math.min(required, h - left);
            if(right != null) required = Math.min(required, right - h);
            ret = Math.max(required, ret);
        }
        return ret;
    }

    public static void main(String[] args) {
        int ret = findRadius(new int[]{1,2,3,4}, new int[]{1,4});
        System.out.println(ret);
    }


}
