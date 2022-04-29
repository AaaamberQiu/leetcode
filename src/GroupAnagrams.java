import java.util.*;

public class GroupAnagrams {

    /**
     * 49. Group Anagrams
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for(String s: strs){
            String key = getKey(s);
            map.computeIfAbsent(key, x -> new ArrayList<>());
            map.get(key).add(s);
        }
        return new ArrayList<>(map.values());
    }

    public static String getKey(String s){
        char[] count = new char[26];
        for(char c: s.toCharArray()){
            count[c - 'a'] += 1;
        }

        return String.valueOf(count);
    }

    public static void main(String[] args) {
        GroupAnagrams solution = new GroupAnagrams();
        solution.groupAnagrams(new String[]{"eat","tea","tan","ate","nat","bat"});
    }
}
