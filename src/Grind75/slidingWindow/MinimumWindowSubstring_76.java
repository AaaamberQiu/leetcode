package Grind75.slidingWindow;

import java.util.HashMap;
import java.util.Map;

public class MinimumWindowSubstring_76 {

    public String minWindow(String s, String t) {
        Map<Character, Integer> map = convertToMap(t);
        int distinctCount = map.keySet().size();

        int start = 0;
        String ret = null;
        for(int i = 0; i<s.length(); i++){
            char c = s.charAt(i);
            if(map.containsKey(c)){
                map.put(c, map.get(c) - 1);
                if(map.get(c) == 0) distinctCount -= 1;
            }
            while(distinctCount == 0){
                int len = i - start + 1;
                ret = ret == null ? s.substring(start, i+1) : (len < ret.length() ? s.substring(start, i+1): ret);

                char startChar = s.charAt(start);
                if(map.containsKey(startChar)){
                    if(map.get(startChar) == 0) distinctCount += 1;
                    map.put(startChar, map.get(startChar) + 1);
                }
                start++;
            }
        }
        return ret == null ? "" : ret;
    }

    private Map<Character, Integer> convertToMap(String t){
        Map<Character, Integer> map = new HashMap<>();
        for(char c: t.toCharArray()){
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        return map;
    }
}
