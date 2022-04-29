import java.util.*;

public class FindAllAnagrams {

    /**
     * 438. Find All Anagrams in a String
     *
     * @param s
     * @param p
     * @return
     */
    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> ret = new ArrayList<>();
        int start = 0;
        char[] temp = new char[26];
        for (int i = 0; i < s.length(); i++) {
            temp[s.charAt(i) - 'a'] += 1;
            if (i - start + 1 == p.length()) {
                if (isAnagram(temp, p)) ret.add(start);
                temp[s.charAt(start) - 'a'] -=1;
                start += 1;
            }
        }
        return ret;
    }

    public static String getCode(String s, int start, int end){
        char[] count = new char[26];
        for(int i = start; i<=end; i++){
            char c = s.charAt(i);
            count[c - 'a'] += 1;
        }

        return String.valueOf(count);
    }

    public static boolean isAnagram(char[] temp, String p) {
        char[] copy = Arrays.copyOf(temp, temp.length);
        for(char c: p.toCharArray()){
            if(copy[c - 'a'] != 0) copy[c-'a']-=1;
            else return false;
        }
        return true;
    }


    // with template
    public static List<Integer> findAnagrams2(String s, String p) {
        List<Integer> ret = new ArrayList<>();
        // step1: store p into map
        Map<Character, Integer> map = new HashMap<>();
        for(char c : p.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0)+1);
        }

        int start = 0, matchSize = map.size();
        for(int i = 0; i<s.length(); i++){
            char c = s.charAt(i);
            // if exists in the pattern
            if(map.containsKey(c)){
                map.put(c, map.get(c)-1);
                if(map.get(c) == 0) matchSize -= 1;
            }

            // if meet the condition, add to the ret and move start pointer
            while(matchSize == 0){
                char startChar = s.charAt(start);
                if(map.containsKey(startChar)){
                    // add 1 to matchSize only when it first add back
                    if(map.get(startChar) == 0) matchSize+=1;
                    map.put(startChar, map.get(startChar) + 1);
                }
                if(i - start + 1 == p.length()) ret.add(start);
                start++;
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        List<Integer> ret = findAnagrams2("cbaebabacd", "abc");
    }
}
