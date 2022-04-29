package Grind75.stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ValidParentheses_20 {

    public boolean isValid(String s) {
        if(s == null || s.length() == 0) return true;

        Map<Character, Character> map = init();
        Stack<Character> stack = new Stack<>();
        for(char c: s.toCharArray()){
            if(map.keySet().contains(c)) stack.push(c);
            else{
                if(stack.isEmpty()) return false;
                else{
                    Character open = stack.pop();
                    if(map.get(open) != c) return false;
                }
            }
        }

        return stack.isEmpty();

    }

    public static Map<Character, Character> init(){
        Map<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('{', '}');
        map.put('[', ']');
        return map;
    }
}
