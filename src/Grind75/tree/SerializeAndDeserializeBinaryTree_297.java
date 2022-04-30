package Grind75.tree;

import base.TreeNode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;


public class SerializeAndDeserializeBinaryTree_297 {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serializeHelper(sb, root);
        return sb.deleteCharAt(sb.length()-1).toString();
    }

    private void serializeHelper(StringBuilder sb, TreeNode node){
        if(node == null){
            sb.append("N").append(",");
            return;
        }
        sb.append(node.val).append(",");
        serializeHelper(sb, node.left);
        serializeHelper(sb, node.right);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data.length() == 0) return null;
        Queue<String> queue = new ArrayDeque<>();
        queue.addAll(Arrays.asList(data.split(",")));
        return deserializeHelper(queue);
    }

    private TreeNode deserializeHelper(Queue<String> queue){
        TreeNode root = convert(queue.poll());
        if(root == null) return null;
        root.left = deserializeHelper(queue);
        root.right = deserializeHelper(queue);
        return root;
    }

    private static TreeNode convert(String s){
        if("N".equals(s)) return null;
        return new TreeNode(Integer.parseInt(s));
    }

}
