package Grind75.tree;

import base.TreeNode;

public class ConstructBinaryTreeFromPreAndInorder_105 {

    private int index = 0;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return helper(preorder, inorder, 0, inorder.length-1);
    }

    public TreeNode helper(int[] preorder,int[] inorder, int inStart, int inEnd){
        if(inStart > inEnd) return null;

        int breakPoint = 0;
        for(int k = inStart; k<=inEnd; k++){
            if(inorder[k] == preorder[index]) {
                breakPoint = k;
                break;
            }
        }
        TreeNode root = new TreeNode(preorder[index++]);
        TreeNode left = helper(preorder, inorder, inStart, breakPoint-1);
        TreeNode right = helper(preorder, inorder, breakPoint+1, inEnd);

        root.left = left;
        root.right = right;
        return root;
    }

    public static void main(String[] args) {

    }
}
