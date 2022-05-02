package Grind75.dfs;

import java.util.*;

public class LetterCombinationsOfAPhoneNumber_17 {

    public List<String> letterCombinations(String digits) {
        if(digits.length() == 0) return Collections.emptyList();

        List<String> ret = new ArrayList<>();
        Map<Character, List<Character>> map = convert();
        dfs(new StringBuilder(), ret, map, 0, digits);
        return ret;
    }


    private static void dfs(StringBuilder temp, List<String> ret, Map<Character, List<Character>> map, int pos, String digits){
        if(temp.length() == digits.length()) {
            ret.add(new String(temp.toString()));
            return;
        }

        char curr = digits.charAt(pos);
        List<Character> candidates = map.getOrDefault(curr, new ArrayList<>());
        for(int i = 0; i < candidates.size(); i++){
            char c = candidates.get(i);
            temp.append(c);
            dfs(temp, ret, map, pos+1, digits);
            temp.deleteCharAt(temp.length()-1);
        }
    }

    private static Map<Character, List<Character>> convert(){
        Map<Character, List<Character>> map = new HashMap<>();
        map.put('2', Arrays.asList('a', 'b', 'c'));
        map.put('3', Arrays.asList('d', 'e', 'f'));
        map.put('4', Arrays.asList('g', 'h', 'i'));
        map.put('5', Arrays.asList('j', 'k', 'l'));
        map.put('6', Arrays.asList('m', 'n', 'o'));
        map.put('7', Arrays.asList('p', 'q', 'r', 's'));
        map.put('8', Arrays.asList('t', 'u', 'v'));
        map.put('9', Arrays.asList('w', 'x', 'y', 'z'));
        return map;
    }
}
