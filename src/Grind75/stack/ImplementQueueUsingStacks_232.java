package Grind75.stack;

import java.util.Stack;

public class ImplementQueueUsingStacks_232 {

    class MyQueue {
        Stack<Integer> stack;
        public MyQueue() {
            stack = new Stack<>();
        }

        public void push(int x) {
            Stack<Integer> temp = new Stack<>();
            while(!stack.isEmpty()){
                temp.push(stack.pop());
            }
            stack.push(x);
            while(!temp.isEmpty()){
                stack.push(temp.pop());
            }
        }

        public int pop() {
            return stack.pop();
        }

        public int peek() {
            return stack.peek();
        }

        public boolean empty() {
            return stack.isEmpty();
        }
    }
}
