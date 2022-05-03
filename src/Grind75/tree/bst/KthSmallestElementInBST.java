package Grind75.tree.bst;

import base.TreeNode;

public class KthSmallestElementInBST {

    public int kthSmallest(TreeNode root, int k) {
        int leftCount = count(root.left);
        if(leftCount == k-1) return root.val;
        else if(k > leftCount) return kthSmallest(root.right, k - leftCount - 1);
        else return kthSmallest(root.left, k);
    }

    public int count(TreeNode root){
        if(root == null) return 0;
        return 1 + count(root.left) + count(root.right);
    }
}
