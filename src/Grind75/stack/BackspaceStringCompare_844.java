package Grind75.stack;

import java.util.Stack;

public class BackspaceStringCompare_844 {

    public boolean backspaceCompare(String s, String t) {
        Stack<Character> stack1 = getFinal(s);
        Stack<Character> stack2 = getFinal(t);
        return stack1.equals(stack2);
    }

    private Stack<Character> getFinal(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '#') {
                if (!stack.isEmpty()) stack.pop();
            } else {
                stack.push(c);
            }
        }
        return stack;
    }


    public boolean backspaceCompare_2(String s, String t) {
        int i = s.length() -1, j = t.length()-1;
        int count = 0;
        while(true){
            count = 0;
            // enter the while loop if count val need to change
            while(i >= 0 && (count > 0 || s.charAt(i) == '#')){
                if(s.charAt(i) == '#') count += 1;
                else count -= 1;
                i--;
            }
            // i stops at rightmost pos that count = 0 Or -1

            count = 0;
            while(j >= 0 && (count > 0 || t.charAt(j) == '#')){
                if(t.charAt(j) == '#') count += 1;
                else count -= 1;
                j--;
            }
            // j stops at rightmost pos that count = 0 Or -1

            if(i >= 0 && j >=0 && s.charAt(i) == t.charAt(j)){
                i--; j--;
            }else{
                break;
            }
        }
        return i ==-1 && j == -1;
    }
}
