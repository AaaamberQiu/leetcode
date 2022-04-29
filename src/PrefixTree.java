import java.util.ArrayList;
import java.util.List;

public class PrefixTree {

    /**
     * 208. Implement Trie (Prefix Tree)
     */
    class Trie {
        private TreeNode root;
        public Trie() {
            root = new TreeNode(' ');
        }

        public void insert(String word) {
            TreeNode pointer = root;
            for(char c : word.toCharArray()){
                if(pointer.children[c - 'a'] == null){
                    pointer.children[c - 'a'] = new TreeNode(c);
                }
                pointer = pointer.children[c - 'a'];
            }
            pointer.isEnd = true;
        }

        public boolean search(String word) {
            TreeNode pointer = root;
            for(char c : word.toCharArray()){
                if(pointer.children[c - 'a'] == null) return false;
                pointer = pointer.children[c - 'a'];
            }
            return pointer.isEnd;
        }

        public boolean startsWith(String prefix) {
            TreeNode pointer = root;
            for(char c : prefix.toCharArray()){
                if(pointer.children[c - 'a'] == null) return false;
                pointer = pointer.children[c - 'a'];
            }
            return true;
        }
    }

    class TreeNode{
        char c;
        TreeNode[] children = new TreeNode[26];
        boolean isEnd;

        TreeNode(char c){
            this.c = c;
        }
    }
}
