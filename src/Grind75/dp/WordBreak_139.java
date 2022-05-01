package Grind75.dp;

import java.util.List;

public class WordBreak_139 {

    public boolean wordBreak(String s, List<String> wordDict) {
        int n = s.length();
        boolean[] canBreak = new boolean[n+1];

        canBreak[0] = true;
        for(int i = 1; i<=n; i++){
            for(String word: wordDict){
                if(i < word.length()) continue;
                if(word.equals(s.substring(i-word.length(), i))){
                    canBreak[i] = canBreak[i] || canBreak[i-word.length()];
                }
            }
        }
        return canBreak[n];
    }
}
