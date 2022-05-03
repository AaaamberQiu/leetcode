package Grind75.slidingWindow;

import java.util.ArrayList;
import java.util.List;

public class FindAllAnagramsInString {

    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> ret = new ArrayList<>();

        int[] pattern = new int[26];
        for(char c: p.toCharArray()) pattern[c - 'a'] += 1;
        int start = 0;
        int[] count = new int[26];
        for(int i = 0; i<s.length(); i++){
            char c = s.charAt(i);
            count[c-'a'] += 1;
            while(i - start + 1 >= p.length()){
                if(isAnagram(count, pattern)) ret.add(start);
                count[s.charAt(start)-'a'] -= 1;
                start++;
            }
        }
        return ret;
    }

    private boolean isAnagram(int[] count, int[] pattern){
        for(int i = 0; i<26; i++){
            if(count[i] != pattern[i]) return false;
        }
        return true;
    }
}
