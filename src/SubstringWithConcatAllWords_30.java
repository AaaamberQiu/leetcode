import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubstringWithConcatAllWords_30 {

    /**
     * 30. Substring with Concatenation of All Words
     * @param s
     * @param words
     * @return
     */
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> ret = new ArrayList<>();
        int wordLen = words[0].length();
        int matchSize = words.length * wordLen;

        Map<String, Integer> wordMap = new HashMap<>();
        for(String w: words) wordMap.put(w, wordMap.getOrDefault(w,0)+1);

        int start = 0;
        for(int i = 0; i<s.length(); i++){
            if(i - start + 1 == matchSize){
                if(isMatch(s.substring(start, i+1), wordMap, wordLen)) ret.add(start);
                start += 1;
            }
        }
        return ret;
    }

    public static boolean isMatch(String s, Map<String, Integer> wordMap, int wordLen){
        Map<String, Integer> seen = new HashMap<>();
        for(int i = 0; i<s.length(); i+= wordLen){
            String candidate = s.substring(i, i+wordLen);
            seen.put(candidate, seen.getOrDefault(candidate, 0)+1);
        }
        return seen.equals(wordMap);
    }
}
