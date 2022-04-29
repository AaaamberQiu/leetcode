import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class BasicCalculator {

    /**
     * 227. Basic Calculator II
     *
     * @param s
     * @return
     */
    public int calculate(String s) {
        Queue<Character> queue = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (c != ' ') queue.offer(c);
        }

        queue.offer(' ');
        return helper(queue);
    }


    private int helper(Queue<Character> queue) {
        int num = 0, prev = 0, sum = 0;
        char prevOp = '+';
        while(!queue.isEmpty()){
            char c = queue.poll();
            if(Character.isDigit(c)){
                num = num * 10 + (c - '0');
            }else if(c == '('){
                num = helper(queue);
            }else if (c == ')'){
                break;
            }else{
                switch (prevOp) {
                    case '+':
                        sum += prev;
                        prev = num;
                        break;
                    case '-':
                        sum += prev;
                        prev = -num;
                        break;
                    case '*':
                        prev *= num;
                        break;
                    case '/':
                        prev /= num;
                        break;
                    default:
                        return 0;
                }

                prevOp = c;
                num = 0;
            }
        }
        return sum + prev;
    }


    public static void main(String[] args) {
        BasicCalculator calculator = new BasicCalculator();
        int ret = calculator.calculate("(1+(4+5+2)-3)+(6+8)");
        System.out.println(ret);
    }
}
