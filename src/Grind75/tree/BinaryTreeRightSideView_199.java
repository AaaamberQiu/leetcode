package Grind75.tree;

import base.TreeNode;

import java.util.*;

public class BinaryTreeRightSideView_199 {

    public List<Integer> rightSideView(TreeNode root) {
        if(root == null) return Collections.emptyList();

        List<Integer> ret = new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i<size; i++){
                TreeNode curr = queue.poll();
                if(i == size-1) ret.add(curr.val);
                if(curr.left != null) queue.offer(curr.left);
                if(curr.right != null) queue.offer(curr.right);
            }
        }
        return ret;
    }
}
