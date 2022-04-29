package Grind75.slidingWindow;

import java.util.HashSet;
import java.util.Set;

public class LongestSubstringWithoutRepeatingChars_3 {

    public static int lengthOfLongestSubstring(String s) {
        if(s.length() <= 1) return s.length();
        int max = 0;
        int start = 0;
        Set<Character> set = new HashSet<>();
        for(int i = 0; i<s.length(); i++){
            char c = s.charAt(i);
            while(start < i && set.contains(c)){
                set.remove(s.charAt(start++));
            }
            set.add(c);
            max = Math.max(max, i - start + 1);
        }
        return max;
    }

    public static void main(String[] args) {
        int res = lengthOfLongestSubstring("bbbbb");
        System.out.println(res);
    }
}
