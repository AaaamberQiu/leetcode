package Grind75.dfs;

import java.util.*;

public class WordLadder_127 {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) return 0;

        Set<String> wordSet = new HashSet<>(wordList);

        Queue<String> queue = new ArrayDeque<>();
        queue.add(beginWord);

        int level = 0;
        while(!queue.isEmpty()){
            int size = queue.size();
            level += 1;
            for(int i = 0; i<size; i++){
                String curr = queue.poll();
                List<String> candidates = getCandidates(curr, wordSet);
                for(String candidate: candidates){
                    if(candidate.equals(endWord)) return level+1;
                    queue.offer(candidate);
                    wordSet.remove(candidate);
                }
            }
        }
        return 0;

    }


    public static List<String> getCandidates(String s, Set<String> wordSet){
        List<String> candidates = new ArrayList<>();
        for(String word: wordSet){
            int diff = 0;
            for(int i = 0; i<word.length(); i++){
                if(word.charAt(i) != s.charAt(i)) diff+=1;
                if(diff > 1) break;
            }
            if(diff == 1) candidates.add(word);
        }
        return candidates;
    }


    public static List<String> getCandidates2(String s, Set<String> wordSet){
        List<String> candidates = new ArrayList<>();
        char[] chars = s.toCharArray();
        for(int i = 0; i<chars.length; i++){
            for(char j = 'a'; j <= 'z'; j++){
                chars[i] = j;
                String temp = new String(chars);
                if(s.equals(temp)) continue;
                if(wordSet.contains(temp)){
                    candidates.add(temp);
                }
                chars[i] = s.charAt(i);
            }
        }
        return candidates;
    }



    public static void main(String[] args) {
        WordLadder_127 solution = new WordLadder_127();
        solution.ladderLength("leet", "code", Arrays.asList("lest","leet","lose","code","lode","robe","lost"));
    }
}
