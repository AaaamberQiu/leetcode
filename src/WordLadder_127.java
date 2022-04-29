import java.util.*;

public class WordLadder_127 {

    /**
     * 127. Word Ladder
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if(!wordList.contains(endWord)) return 0;
        // dp[i]: the num of trans step to words[i]
        Map<String, Integer> dp = new HashMap<>();
        dp.put(beginWord, 1);
        helper(beginWord, dp, wordList);

        return dp.getOrDefault(endWord, 0);
    }


    public static void helper(String currWord, Map<String, Integer> dp, List<String> wordList){
        List<String> nextWords = getCandidates(wordList, currWord);
        for(String nextWord: nextWords){
            wordList.remove(currWord);
            if(!dp.containsKey(nextWord)){
               dp.put(nextWord, dp.get(currWord)+1);
            }else{
                int minVal = Math.min(dp.get(nextWord), dp.get(currWord)+1);
                dp.put(nextWord, minVal);
            }
            helper(nextWord, dp, wordList);
            wordList.add(currWord);
        }
    }

    public static List<String> getCandidates(List<String> wordList, String s){
        List<String> candidates = new ArrayList<>();
        for(String word: wordList){
            int diff = 0;
            for(int i = 0; i<word.length(); i++){
                if(word.charAt(i) != s.charAt(i)) diff+=1;
                if(diff > 1) break;
            }
            if(diff == 1) candidates.add(word);
        }
        return candidates;
    }


    // BFS solution
    public int ladderLength2(String beginWord, String endWord, List<String> wordList) {
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);

        Set<String> visited = new HashSet<>();
        visited.add(beginWord);

        int len = 1;
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i<size; i++){
                String curr = queue.poll();
                List<String> nextWords = getCandidates(wordList, curr);
                for(String next: nextWords){
                    if(next.equals(endWord)) return len+1;

                    if(visited.add(next)) queue.offer(next);
                }
            }
            len += 1;
        }
        return 0;
    }


    public static void main(String[] args) {
        WordLadder_127 solution = new WordLadder_127();
        List<String> wordList = new LinkedList<>();
        List<String> temp = Arrays.asList("hot","dot","dog","lot","log","cog");
        for(String s: temp) wordList.add(s);
        int ret = solution.ladderLength2("hit", "cog", wordList);
        System.out.println(ret);
    }
}
