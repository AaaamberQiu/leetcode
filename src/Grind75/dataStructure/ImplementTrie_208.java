package Grind75.dataStructure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ImplementTrie_208 {

    class Trie {

        private TreeNode root;
        public Trie() {
            root = new TreeNode('*');
        }

        public void insert(String word) {
            TreeNode node = root;
            for(char c: word.toCharArray()){
                if(!node.children.containsKey(c)){
                    TreeNode newNode = new TreeNode(c);
                    node.children.put(c, newNode);
                }
                node = node.children.get(c);
            }
            node.isEnd = true;
        }

        public boolean search(String word) {
            TreeNode node = root;
            for(char c: word.toCharArray()){
                if(!node.children.containsKey(c)) return false;
                node = node.children.get(c);
            }
            return node.isEnd;
        }

        public boolean startsWith(String prefix) {
            TreeNode node = root;
            for(char c: prefix.toCharArray()){
                if(!node.children.containsKey(c)) return false;
                node = node.children.get(c);
            }
            return true;
        }
    }

    class TreeNode{
        char c;
        boolean isEnd;
        Map<Character, TreeNode> children;

        TreeNode(char c){
            this.c = c;
            this.isEnd = true;
            this.children = new HashMap<>();
        }
    }
}
