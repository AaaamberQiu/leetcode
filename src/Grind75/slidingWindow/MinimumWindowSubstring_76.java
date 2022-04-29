package Grind75.slidingWindow;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MinimumWindowSubstring_76 {

    public String minWindow(String s, String t) {
        int start = 0;
        String ret = null;
        Map<Character, Integer> map = new HashMap<>();
        for(int i = 0; i<s.length(); i++){
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
            if(i - start + 1 < t.length()) continue;
            while(containsAll(map, t)){
                int len = i - start + 1;
                String temp = s.substring(start, i+1);
                ret = ret == null ? temp : (len < ret.length() ? temp: ret);

                map.put(s.charAt(start), map.get(s.charAt(start))-1);
                start++;
            }
        }
        return ret == null ? "" : ret;
    }

    public static boolean containsAll(Map<Character, Integer> map, String t){
        Map<Character, Integer> copy = new HashMap<>(map);
        for(char c: t.toCharArray()){
            copy.put(c, copy.getOrDefault(c, 0) - 1);
            if(copy.get(c) < 0) return false;
        }
        return true;
    }
}
