package Grind75.dataStructure;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class TimeBasedKVStore_981 {
    class TimeMap {
        private Map<String, TreeMap<Integer, String>> cache;
        public TimeMap() {
            cache = new HashMap<>();
        }

        public void set(String key, String value, int timestamp) {
            cache.computeIfAbsent(key, k -> new TreeMap<>());
            cache.get(key).put(timestamp, value);
        }

        public String get(String key, int timestamp) {
            if(!cache.containsKey(key)) return "";
            TreeMap<Integer, String> values = cache.get(key);
            if(values.containsKey(timestamp)) return values.get(timestamp);
            Integer floor = values.floorKey(timestamp);
            return floor == null ? "" : values.get(floor);
        }
    }
}
