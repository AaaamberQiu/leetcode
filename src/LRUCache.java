import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 146. LRU Cache
 */
public class LRUCache {
    private NodeList nodeList = new NodeList();
    private Map<Integer, CacheNode> cache = new HashMap<>();

    private int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        if(!cache.containsKey(key)) return -1;
        // move the node to the latest
        CacheNode node = cache.get(key);
        nodeList.moveToHead(node);
        return node.val;
    }

    public void put(int key, int value) {
        CacheNode node = cache.get(key);
        if(node != null) {
            node.val = value;
            nodeList.moveToHead(node);
        }else{
            CacheNode newNode = new CacheNode(key, value);
            if(cache.size() == capacity){
                // remove the tail
                CacheNode tail = nodeList.removeFromTail();
                // remove entry from cache
                cache.remove(tail.key);
            }
            cache.put(key, newNode);
            nodeList.addToHead(newNode);
        }
    }


    class NodeList{
        CacheNode dummyHead;
        CacheNode dummyTail;

        NodeList(){
            dummyHead = new CacheNode(0,0);
            dummyTail = new CacheNode(0,0);
            dummyHead.next = dummyTail;
            dummyTail.prev = dummyHead;
        }

        public void moveToHead(CacheNode node){
            node.prev.next = node.next;
            node.next.prev = node.prev;
            addToHead(node);
        }

        public void addToHead(CacheNode node){
            node.next = dummyHead.next;
            dummyHead.next.prev = node;

            dummyHead.next = node;
            node.prev = dummyHead;
        }

        public CacheNode removeFromTail(){
            CacheNode tail = dummyTail.prev;
            tail.prev.next = dummyTail;
            dummyTail.prev = tail.prev;

            tail.next = null;
            tail.prev = null;
            return tail;
        }
    }


    class CacheNode{
        int key;
        int val;
        CacheNode prev = null;
        CacheNode next = null;

        public CacheNode(int key, int val){
            this.key = key;
            this.val = val;
        }
    }

    public static void main(String[] args) {
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(1, 1); // cache is {1=1}
        lRUCache.put(2, 2); // cache is {1=1, 2=2}
        lRUCache.get(1);    // return 1
        lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
        lRUCache.get(2);    // returns -1 (not found)
        lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
        lRUCache.get(1);    // return -1 (not found)
        lRUCache.get(3);    // return 3
        lRUCache.get(4);    // return 4
    }
}
