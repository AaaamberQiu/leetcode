package Grind75;

public class ValidAnagram_242 {

    public boolean isAnagram(String s, String t) {
        if(s.length() != t.length()) return false;

        // count chars in the s
        int[] count = countMap(s);
        // travers chars in t
        for(char c: t.toCharArray()){
            count[c - 'a'] -= 1;
            if(count[c - 'a'] < 0) return false;
        }

        // check if the count mapping is all 0
        for(int i = 0; i<count.length; i++){
            if(count[i] != 0) return false;
        }

        return true;
    }

    public int[] countMap(String s){
        int[] count = new int[26];
        for(char c : s.toCharArray()){
            count[c - 'a'] += 1;
        }
        return count;
    }
}
