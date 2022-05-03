package Grind75.math;

import java.util.*;

public class RomanToInteger_13 {

    public int romanToInt(String s) {
        if (s.length() == 0) return 0;
        int ret = 0, prevInt = 0;
        Map<Character, Integer> map = getMap();

        int i = s.length() - 1;
        while (i >= 0) {
            char c = s.charAt(i);
            int curr = map.get(c);
            if(curr < prevInt) ret -= curr;
            else ret += curr;
            prevInt = curr;
            i--;
        }
        return ret;

    }

    private Map<Character, Integer> getMap() {
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        return map;
    }


}
