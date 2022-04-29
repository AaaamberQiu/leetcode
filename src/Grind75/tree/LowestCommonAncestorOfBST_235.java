package Grind75.tree;

import base.TreeNode;

public class LowestCommonAncestorOfBST_235 {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(p.val > q.val) return lowestCommonAncestor(root, q, p);
        if((p.val < root.val && root.val < q.val) || root == p || root == q) return root;
        if(q.val < root.val) return lowestCommonAncestor(root.left, p, q);
        if(p.val > root.val) return lowestCommonAncestor(root.right, p, q);
        return null;
    }

}
