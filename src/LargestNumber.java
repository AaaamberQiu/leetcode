import java.util.Arrays;
import java.util.Comparator;

public class LargestNumber {

    /**
     * 179. Largest Number
     * <p>
     *     solution: rewrite comparator
     * </p>
     * @param nums
     * @return
     */
    public String largestNumber(int[] nums) {
        int n = nums.length;
        String[] strs = new String[n];

        for(int i = 0; i<n; i++){
            strs[i] = String.valueOf(nums[i]);
        }

        Arrays.sort(strs, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return (o1+o2).compareTo(o2+o1);
            }
        });

        // check if the largest num is 0
        if(strs[n-1].equals("0")){
            return "0";
        }

        StringBuffer sb = new StringBuffer();
        for(int i = n-1; i>=0; i--){
            sb.append(strs[i]);
        }
        return sb.toString();

    }


}
