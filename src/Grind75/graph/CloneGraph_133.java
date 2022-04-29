package Grind75.graph;


import java.util.*;

public class CloneGraph_133 {

    public Node cloneGraph_bfs(Node node) {
        Map<Node, Node> map = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);

        while(!queue.isEmpty()){
            Node curr = queue.poll();
            Node newNode = map.containsKey(curr)? map.get(curr) : new Node(curr.val);
            List<Node> neighbors = curr.neighbors;
            for(Node n: neighbors){
                if(map.containsKey(n)){
                    newNode.neighbors.add(map.get(n));
                }else{
                    Node newNeighbor = new Node(n.val);
                    newNode.neighbors.add(newNeighbor);
                    map.put(n, newNeighbor);
                    queue.offer(n);
                }
            }
            map.put(curr, newNode);
        }
        return map.get(node);
    }


    public Node cloneGraph_dfs(Node node) {
        Map<Node, Node> map = new HashMap<>();
        return dfsHelper(node, map);
    }


    public Node dfsHelper(Node node, Map<Node, Node> map){
        if(map.containsKey(node)){
            return map.get(node);
        }

        Node newNode = new Node(node.val);
        map.put(node, newNode);
        for(Node n: node.neighbors){
            newNode.neighbors.add(dfsHelper(n, map));
        }
        return newNode;
    }

    class Node {
        public int val;
        public List<Node> neighbors;

        Node(int val){
            this.val = val;
            this.neighbors = new ArrayList<>();
        }

        Node(int val, List<Node> n){
            this.val = val;
            this.neighbors = n;
        }
    }
}

