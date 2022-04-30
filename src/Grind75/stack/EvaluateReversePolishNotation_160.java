package Grind75.stack;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class EvaluateReversePolishNotation_160 {

    public int evalRPN(String[] tokens) {
        Stack<Integer> nums = new Stack<>();
        List<String> operators = Arrays.asList("+", "-", "*", "/");
        for(String token : tokens){
            if(operators.contains(token)){
                int second = nums.pop();
                int first = nums.pop();
                nums.push(compute(first, second, token));
            }else{
                nums.push(convert(token));
            }
        }
        return nums.pop();
    }

    private int compute(int a, int b, String op){
        switch (op){
            case "+":
                return a+b;
            case "-":
                return a-b;
            case "*":
                return a*b;
            case "/":
                return a/b;
            default:
                throw new UnsupportedOperationException();
        }
    }

    private int convert(String a){
        if(a.startsWith("-")){
            return -Integer.parseInt(a.substring(1));
        }
        return Integer.parseInt(a);
    }
}
