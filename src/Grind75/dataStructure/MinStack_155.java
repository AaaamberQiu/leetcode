package Grind75.dataStructure;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class MinStack_155 {
    class MinStack {

        private LinkedList<Node> nodes;

        public MinStack() {
            nodes = new LinkedList<>();
        }

        public void push(int val) {
            int min = nodes.size() == 0 ? val: nodes.get(nodes.size()-1).min;
            Node node = new Node(val, Math.min(val, min));
            nodes.add(node);
        }

        public void pop() {
            nodes.removeLast();
        }

        public int top() {
            return nodes.get(nodes.size()-1).val;
        }

        public int getMin() {
            return nodes.get(nodes.size()-1).min;
        }
    }

    class Node{
        int val;
        int min;
        Node(int val, int min){
            this.val = val;
            this.min = min;
        }
    }

}
