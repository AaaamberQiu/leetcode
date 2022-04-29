import java.util.*;

/**
 * 380. Insert Delete GetRandom O(1)
 */
public class RandomizedSet_380 {

    private Map<Integer, Integer> map = new HashMap<>();
    private List<Integer> list = new LinkedList<>();

    public RandomizedSet_380() {

    }

    public boolean insert(int val) {
        if(map.containsKey(val)) return false;
        map.put(val, list.size());
        list.add(val);
        return true;
    }

    public boolean remove(int val) {
        if(!map.containsKey(val)) return false;
        // 跟最后一个swap，不能直接删，删了之后map里面的index不好更新
        int index = map.get(val);
        Collections.swap(list, index, list.size()-1);
        map.put(list.get(index), index);

        // remove last element
        list.remove(list.size()-1);
        map.remove(val);

        return true;
    }

    public int getRandom() {
        int min = 0, max = list.size();
        // Math.random gives a random double value [0.0, 1.0)
        int index = (int)(Math.random() * (max - min) + min);
        return list.get(index);
    }

    public static void main(String[] args) {
        RandomizedSet_380 set = new RandomizedSet_380();
        set.insert(0);
        set.insert(1);
        set.remove(0);
        set.insert(2);
        set.remove(1);
        set.getRandom();
    }
}
