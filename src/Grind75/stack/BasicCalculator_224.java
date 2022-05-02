package Grind75.stack;

import java.util.ArrayDeque;
import java.util.Queue;

public class BasicCalculator_224 {

    public static int calculate(String s) {
        Queue<Character> queue = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (c != ' ') queue.offer(c);
        }
        queue.offer('+');
        return helper(queue);
    }

    public static int helper(Queue<Character> queue) {
        int sum = 0, prevNum = 0, num = 0;
        char prevOp = '+';
        while (!queue.isEmpty()) {
            char c = queue.poll();
            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
            } else if (c == '(') {
                num = helper(queue);
            } else {
                switch (prevOp) {
                    case '+':
                        sum += prevNum;
                        prevNum = num;
                        break;
                    case '-':
                        sum += prevNum;
                        prevNum = -num;
                        break;
                    default:
                        break;

                }
                prevOp = c;
                num = 0;
                if(c == ')') break;
            }
        }
        return sum + prevNum;
    }


    public static void main(String[] args) {
        int ret = calculate("(1+(4+5+2)-3)+(6+8)");
        System.out.println(ret);
    }

}
