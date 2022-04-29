import java.util.*;

public class RemoveDuplicateLetters {

    /**
     * 316. Remove Duplicate Letters
     * @param s
     * @return
     */
    public String removeDuplicateLetters(String s) {
        int[] count = new int[26];
        boolean[] visited = new boolean[26];
        for(int i = 0; i<s.length(); i++){
            count[s.charAt(i) - 'a'] += 1;
        }

        Stack<Character> stack = new Stack<>();
        for(int i = 0; i<s.length(); i++){
            char c = s.charAt(i);
            count[c - 'a'] -=1;
            // char already in the stack
            if(visited[c - 'a']){
                continue;
            }
            // stack.peek char can be removed if it is larger than c and it also appears later on
            while(!stack.isEmpty() && stack.peek() > c && count[stack.peek() - 'a'] != 0){
                visited[stack.pop()-'a'] = false;
            }
            stack.push(c);
            visited[c - 'a'] = true;
        }

        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()){
            sb.append(stack.pop());
        }
        return sb.reverse().toString();
    }
}
