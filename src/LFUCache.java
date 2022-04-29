import java.util.*;

public class LFUCache {

    private int capacity;

    private long ts;

    // cache to hold kv pairs
    Map<Integer, Node> cache;

    // minHeap hold k-count
    PriorityQueue<Node> minHeap;


    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.ts = 0L;
        this.cache = new HashMap<>();
        this.minHeap = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                if(o1.count == o2.count){
                    return Long.compare(o1.timestamp, o2.timestamp);
                }
                return o1.count - o2.count;
            }
        });
    }

    public int get(int key) {
        if(capacity == 0) return -1;
        if(cache.containsKey(key)){
            Node node = cache.get(key);
            int ret = node.val;
            minHeap.remove(node);

            Node copy = new Node(node.key, node.val, node.count+1, ts++);
            cache.put(node.key, copy);
            minHeap.add(copy);
            return ret;
        }

        return -1;
    }

    public void put(int key, int value) {
        if(capacity == 0) return;
        if(cache.containsKey(key)){
            Node node = cache.get(key);
            minHeap.remove(node);

            Node copy = new Node(node.key, value, node.count+1, ts++);
            cache.put(node.key, copy);
            minHeap.add(copy);
            return;
        }

        Node newNode = new Node(key, value, 1, ts++);
        if(cache.size() == capacity){
            Node leastFreq = minHeap.poll();
            cache.remove(leastFreq.key);
        }
        cache.put(key, newNode);
        minHeap.add(newNode);
    }

    public static void main(String[] args) {
        LFUCache cache = new LFUCache(3);
        cache.put(1,1);
        cache.put(2,2);
        cache.put(3,3);
        cache.put(4,4);

        cache.get(4);
        cache.get(3);
        cache.get(2);
        cache.get(1);
        cache.put(5,5);

        cache.get(1);
        cache.get(2);
        cache.get(3);
        cache.get(4);
        cache.get(5);
    }
}

class Node{
    int key;
    int val;
    int count;
    long timestamp;

    Node(int key, int val, int count, long ts){
        this.key = key;
        this.val = val;
        this.count = count;
        this.timestamp = ts;
    }
}
