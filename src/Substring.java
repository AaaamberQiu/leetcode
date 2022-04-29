import java.util.HashMap;
import java.util.Map;

public class Substring {

    /**
     * 3. Longest Substring Without Repeating Characters
     * <p>
     *     sliding window
     * </p>
     */
    public int lengthOfLongestSubstring(String s) {
        if(s.length() < 2){
            return s.length();
        }
        int maxLen = 0;
        int start = 0;
        Map<Character, Integer> map = new HashMap<>();
        for(int i = 0; i<s.length(); i++){
            char c = s.charAt(i);
            if(map.containsKey(c)){
                maxLen = Math.max(maxLen, i-start);
                start = Math.max(start, map.get(c) + 1);
            }
            map.put(c, i);
        }

        return maxLen;
    }


    /**
     * 395. Longest Substring with At Least K Repeating Characters
     * @param s
     * @param k
     * @return
     */
    public static int longestSubstring(String s, int k) {
        if(s == null || s.length() == 0) return 0;
        if(k == 1) return s.length();
        return helper(s, 0, s.length(), k);
    }


    public static int helper(String s, int left, int right, int k){
        // count frequencies
        int[] frequencies = new int[26];
        for(int i = left; i<right; i++){
            char c = s.charAt(i);
            frequencies[c - 'a'] += 1;
        }

        // validate the whole string
        boolean flag = true;
        for(int count: frequencies){
            if(count != 0 && count < k) flag = false;
        }
        if(flag) return right-left;

        // check each spilt
        int start = left, ret = 0;
        for(int i = left; i<right; i++){
            char c = s.charAt(i);
            if(frequencies[c - 'a'] < k){
                ret = Math.max(ret, helper(s, start, i, k));
                // already check s[start, i), move start to i+1
                start = i+1;
            }
        }
        return Math.max(ret, helper(s, start, right, k));
    }

    public static void main(String[] args) {
        System.out.println(longestSubstring("aaabb", 3));
    }

}
