import java.util.*;

public class WordBreak {

    /**
     * 139. Word Break
     * <p>
     * dp[i] means whether or not the substring ending at i could be segmented
     *
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        int n = s.length();

        boolean[] dp = new boolean[n + 1];
        // if the substring is empty, we could use none of words in dict to compose it
        dp[0] = true;
        for (int i = 1; i <= n; i++) {
            dp[i] = false;
            for (String word : wordDict) {
                if (i >= word.length()) {
                    String substr = s.substring(i - word.length(), i);
                    if (substr.equals(word)) {
                        // if any of combination works
                        dp[i] = dp[i] || dp[i - word.length()];
                    }
                }
            }
        }
        return dp[n];
    }


    /**
     * 140. Word Break II
     *
     * @param s
     * @param wordDict
     * @return
     */
    public List<String> wordBreakToSentences(String s, List<String> wordDict) {
        // dp.get(i): list of segmented words between [0,i]
        Map<Integer, List<String>> dp = new HashMap<>();

        dp.put(0, Arrays.asList(""));
        for (int i = 1; i <= s.length(); i++) {
            List<String> collection = new ArrayList<>();
            for (String word : wordDict) {
                if (i >= word.length() && word.equals(s.substring(i - word.length(), i))) {
                    List<String> choices = dp.get(i - word.length());
                    for(String c : choices){
                        String sentence = "".equals(c) ? word : c + " " + word;
                        collection.add(sentence);
                    }
                }
            }
            dp.put(i, collection);
        }
        return dp.get(s.length());
    }

}
