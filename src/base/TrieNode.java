package base;

import java.util.HashMap;
import java.util.Map;

public class TrieNode {
    public Map<Character, TrieNode> map = new HashMap<>();
    public String word = "";
    public boolean isEnd = false;
}
