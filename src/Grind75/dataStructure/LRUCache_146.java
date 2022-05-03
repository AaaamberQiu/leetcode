package Grind75.dataStructure;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class LRUCache_146 {

    class LRUCache {

        private int capacity;
        private Map<Integer, Node> cache;
        private NodeList queue;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            this.cache = new HashMap<>();
            this.queue = new NodeList();
        }

        public int get(int key) {
            if(cache.containsKey(key)) {
                Node node = cache.get(key);
                queue.remove(node);
                queue.addToTail(node);
                return node.value;
            }
            return -1;
        }

        public void put(int key, int value) {
            if(cache.containsKey(key)){
                Node node = cache.get(key);
                node.value = value;
                queue.remove(node);
                queue.addToTail(node);
                cache.put(key, node);
                return;
            }

            if(cache.size() >= capacity){
                Node first = queue.removeFromHead();
                cache.remove(first.key);
            }
            Node newNode = new Node(key, value);
            cache.put(key, newNode);
            queue.addToTail(newNode);
        }
    }


    class NodeList{
        Node dummyHead;
        Node dummyTail;

        NodeList(){
            this.dummyHead = new Node(-1, -1);
            this.dummyTail = new Node(-1, -1);
            dummyHead.next = dummyTail;
            dummyTail.prev = dummyHead;
        }

        void addToTail(Node node){
            node.prev = dummyTail.prev;
            dummyTail.prev.next = node;
            node.next = dummyTail;
            dummyTail.prev = node;
        }

        void remove(Node node){
            Node prev = node.prev, next = node.next;
            prev.next = next;
            next.prev = prev;
        }

        Node removeFromHead(){
            Node head = dummyHead.next;
            remove(head);
            return head;
        }

    }

    class Node{
        int key;
        int value;
        Node prev;
        Node next;
        Node(int key, int val){
            this.key = key;
            this.value = val;
        }
    }

}
