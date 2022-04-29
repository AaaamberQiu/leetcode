import java.util.*;

public class MinimumWindowSubstr_76 {

    /**
     * 76. Minimum Window Substring
     * @param s
     * @param t
     * @return
     */
    public static String minWindow(String s, String t) {
        if(t.length() > s.length()) return "";
        Map<Character, Integer> pattern = new HashMap<>();
        for(char c: t.toCharArray()) pattern.put(c, pattern.getOrDefault(c,0)+1);

        String ret = s;
        int start = 0, matchSize = pattern.size();
        for(int i = 0; i<s.length(); i++){
            char c = s.charAt(i);
            if(pattern.containsKey(c)){
                pattern.put(c, pattern.get(c)-1);
                if(pattern.get(c) == 0) matchSize-=1;
            }
            // move start
            while(matchSize == 0){
                if(ret.length() > (i - start + 1)) ret = s.substring(start, i+1);
                char startChar = s.charAt(start);
                if(pattern.containsKey(startChar)){
                    if(pattern.get(startChar) == 0) matchSize += 1;
                    pattern.put(startChar, pattern.get(startChar)+1);
                }
                start += 1;
            }
        }
        return start == 0? "" : ret;
    }

    public static boolean contains(String t, String substring){
        if(substring.length() < t.length()) return false;

        int[] temp = new int[52];
        for(char c : substring.toCharArray()) temp[c - 'A'] += 1;
        for(int i = 0; i< t.length(); i++){
            char c = t.charAt(i);
            temp[c - 'A'] -= 1;
            if(temp[c - 'A'] < 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String ret = minWindow("bbaa", "aba");
        System.out.println(ret);
    }
}
