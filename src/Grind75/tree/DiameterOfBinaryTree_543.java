package Grind75.tree;

import base.TreeNode;

public class DiameterOfBinaryTree_543 {
    int max = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        getHeight(root);
        return max;
    }

    private int getHeight(TreeNode root){
        if(root == null) return 0;
        int left = getHeight(root.left);
        int right = getHeight(root.right);
        max = Math.max(max, left + right);
        return Math.max(left, right) + 1;
    }

}
