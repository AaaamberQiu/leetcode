import java.util.HashMap;
import java.util.Map;

public class LongestRepeatingCharReplacement {

    /**
     * @param s
     * @param k
     * @return
     */
    public static int characterReplacement(String s, int k) {
        int start = 0, maxLen = 0, maxCount = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
            maxCount = Math.max(maxCount, map.get(c));
            // maxCount may be invalid at some points,
            // but this doesn't matter, because it was valid earlier in the string,
            // and all that matters is finding the max window that occurred anywhere in the string.
            // 用while会比用if快，虽然time complexity 看起来一样
            while(i - start + 1 - maxCount > k){
                char startChar = s.charAt(start);
                map.put(startChar, map.get(startChar)-1);
                start++;
            }
            maxLen = Math.max(maxLen, i - start + 1);
        }
        return maxLen;
    }


    public static boolean canReplace(Map<Character, Integer> map, int k) {
        // find major
        Character major = null;
        int maxCount = 0;
        for (Character c : map.keySet()) {
            if (map.get(c) > maxCount) {
                maxCount = map.get(c);
                major = c;
            }
        }
        // check rest chars count
        for (Character c : map.keySet()) {
            if (!c.equals(major)) {
                k -= map.get(c);
                if (k < 0) return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int ret = characterReplacement("AABABBA", 1);
        System.out.println(ret);
    }
}
