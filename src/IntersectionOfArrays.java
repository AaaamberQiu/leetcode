import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IntersectionOfArrays {

    public int[] intersect(int[] nums1, int[] nums2) {
        // ensure nums1 is the shorter one
        if(nums1.length > nums2.length) return intersect(nums2, nums1);

        Map<Integer, Integer> map = new HashMap<>();
        for(int n : nums2){
            int count = map.getOrDefault(n, 0);
            map.put(n, count+1);
        }

        List<Integer> list = new ArrayList<>();
        for(int n: nums1){
            if(map.containsKey(n) && map.get(n) != 0){
                list.add(n);
                map.put(n, map.get(n)-1);
            }
        }

        int[] ret = new int[list.size()];
        for(int i = 0; i<list.size(); i++){
            ret[i] = list.get(i);
        }
        return ret;

    }
}
