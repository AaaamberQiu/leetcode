import base.TreeNode;

import java.util.*;

public class CloneGraph {
    /**
     * 133. Clone Graph
     * @param node
     * @return
     */
    public Node cloneGraph(Node node) {
        if (node == null) return null;
        // mapping between original node and copy one
        Map<Node, Node> map = new HashMap<>();
        // store original node needed to be copy
        Queue<Node> queue = new LinkedList<>();

        Node ret = new Node(node.val);
        map.put(node, ret);
        queue.offer(node);
        while (!queue.isEmpty()) {
            Node top = queue.poll();
            Node copy = map.getOrDefault(top, new Node(top.val));
            for (Node n : top.neighbors) {
                if (map.containsKey(n)) {
                    copy.neighbors.add(map.get(n));
                }else{
                    Node newNode = new Node(n.val);
                    map.put(n, newNode);
                    copy.neighbors.add(newNode);
                    queue.offer(n);
                }
            }
        }
        return ret;
    }


    public Node cloneGraphDFS(Node node) {
        Map<Node, Node> map = new HashMap<>();
        return cloneHelper(node, map);
    }

    public Node cloneHelper(Node node, Map<Node, Node> built) {
        if (node == null) return null;
        if (built.containsKey(node)) {
            return built.get(node);
        }
        Node newNode = new Node(node.val);
        built.put(node, newNode);

        List<Node> neighbors = node.neighbors;
        for (Node neighbor : neighbors) {
            newNode.neighbors.add(cloneHelper(neighbor, built));
        }
        return newNode;
    }


    /**
     * 199. Binary Tree Right Side View
     * @param root
     * @return
     */
    public List<Integer> rightSideViewDFS(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        rightSideViewHelper(root, ret, 0);
        return ret;
    }

    // given the fact that:
    // 1) result.size = height, grab the first node at each level
    // 2) recurse on right child to get right view
    public static void rightSideViewHelper(TreeNode curr, List<Integer> result, int level){
        if(curr == null) return;
        if(level == result.size()){
            result.add(curr.val);
        }
        rightSideViewHelper(curr.right, result, level+1);
        rightSideViewHelper(curr.left, result, level+1);
    }

    public List<Integer> rightSideViewBFS(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        if(root == null) return ret;
        Queue<TreeNode> queue = new LinkedList<>();

        queue.offer(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i<size; i++){
                TreeNode top = queue.poll();
                if(i == size-1) ret.add(top.val);
                if(top.left != null) queue.offer(top.left);
                if(top.right != null) queue.offer(top.right);
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        n1.neighbors = Arrays.asList(n2, n4);
        n2.neighbors = Arrays.asList(n1, n3);
        n3.neighbors = Arrays.asList(n2, n4);
        n4.neighbors = Arrays.asList(n1, n3);

        CloneGraph cloneGraph = new CloneGraph();
        cloneGraph.cloneGraph(n1);
    }


    static class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
}
