import base.ListNode;
import base.Node;
import base.TreeNode;

import java.util.*;

public class Tree {
    /**
     * 94. Binary Tree Inorder Traversal
     * 144. Binary Tree Preorder Traversal
     * 145. Binary Tree Postorder Traversal
     * <p>
     * pre-order: root-left-right
     * in-order: left-root-right
     * post-order: left-right-root
     * can be solved by iteration or recursion
     * </p>
     *
     * @param root
     * @return
     */
    // recursion
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        if (root == null) return ret;
        ret.addAll(inorderTraversal(root.left));
        ret.add(root.val);
        ret.addAll(inorderTraversal(root.right));
        return ret;
    }

    // iteration
    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        if (root == null) return ret;

        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        while (!stack.isEmpty() || curr != null) {
            // push all left children
            if (curr != null) {
                stack.push(curr);
                curr = curr.left;
            } else {
                TreeNode top = stack.pop();
                ret.add(top.val);
                curr = top.right;
            }
        }
        return ret;
    }

    // iteration
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        if (root == null) return ret;

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            if (curr != null) {
                ret.add(curr.val);
                // push right first to ensure that it will pop later
                stack.push(curr.right);
                stack.push(curr.left);
            }
        }
        return ret;
    }

    // iteration
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        if (root == null) return ret;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root, prev = null;
        while (curr != null && !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            TreeNode top = stack.peek();
            // if top has right child and it hasn't been visited
            if (top.right != null && top.right != prev) {
                curr = top.right;
            } else {
                prev = stack.pop();
                ret.add(prev.val);
            }
        }
        return ret;
    }


    /**
     * 101. Symmetric Tree
     *
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return helper(root.left, root.right);
    }

    public static boolean helper(TreeNode left, TreeNode right) {
        if (left == null || right == null) return left == null && right == null;
        if (left.val != right.val) return false;
        return helper(left.left, right.right) && helper(left.right, right.left);
    }

    // iteration
    public boolean isSymmetric2(TreeNode root) {
        if (root == null) return true;
        Stack<TreeNode> stack = new Stack<>();

        stack.push(root.left);
        stack.push(root.right);
        while (!stack.isEmpty()) {
            TreeNode m = stack.pop(), n = stack.pop();
            if (m == null && n == null) continue;
            if (m == null || n == null || m.val != n.val) return false;
            stack.push(m.left);
            stack.push(n.right);
            stack.push(m.right);
            stack.push(n.left);
        }
        return true;
    }


    /**
     * 104. Maximum Depth of Binary Tree
     *
     * @param root
     * @return
     */
    public static int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }


    // BFS
    public static int maxDepth2(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int height = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                TreeNode node = queue.poll();
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            height++;
        }
        return height;
    }

    /**
     * 110. Balanced Binary Tree
     *
     * @param root
     * @return
     */
    public static boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        int leftHeight = maxDepth(root.left);
        int rightHeight = maxDepth(root.right);
        return Math.abs(leftHeight - rightHeight) <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }


    /**
     * 111. Minimum Depth of Binary Tree
     * <p>
     * BFS is better than DFS since it avoid unnecessary accesses
     * </p>
     *
     * @param root
     * @return
     */
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int height = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            height++;
            while (size-- > 0) {
                TreeNode node = queue.poll();
                if (node.left == null && node.right == null) return height;
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }

        }
        return height;
    }


    /**
     * 112. Path Sum
     *
     * @param root
     * @param targetSum
     * @return
     */
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;
        if (root.left == null && root.right == null) return targetSum == root.val;
        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
    }


    /**
     * 113. Path Sum II
     * <p>
     * backtracking
     * remove the item from the list after traversing the whole subtree!!
     * </p>
     *
     * @param root
     * @param targetSum
     * @return
     */
    public static List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> ret = new ArrayList<>();
        if (root == null) return ret;
        pathSumHelper(root, targetSum, ret, new ArrayList<>());
        return ret;
    }

    public static void pathSumHelper(TreeNode root, int targetSum, List<List<Integer>> ret, List<Integer> temp) {
        if (root == null) return;
        temp.add(root.val);
        if (root.left == null && root.right == null && targetSum == root.val) {
            ret.add(new ArrayList<>(temp));
        } else {
            pathSumHelper(root.left, targetSum - root.val, ret, temp);
            pathSumHelper(root.right, targetSum - root.val, ret, temp);
        }
        temp.remove(temp.size() - 1);
    }


    /**
     * 114. Flatten Binary Tree to Linked List
     * <p>
     * 1) flatten right tree first, prev points to the root of flatten right subtree
     * 2) flatten left tree, prev is updated to the root of left subtree, and left subtree how has concatenate with flatten right tree
     * 3) change the root pointer
     *
     * </p>
     *
     * @param root
     */
    public void flatten(TreeNode root) {
        root = flattenHelper(root, null);
    }

    public static TreeNode flattenHelper(TreeNode root, TreeNode prev) {
        if (root == null) {
            return prev;
        }
        prev = flattenHelper(root.right, prev);
        prev = flattenHelper(root.left, prev);
        root.right = prev;
        root.left = null;
        return root;
    }


    /**
     * 116. Populating Next Right Pointers in Each Node
     *
     * @param root
     * @return
     */
    public static Node connect(Node root) {
        if (root == null) return root;

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node curr = queue.poll();
                if (i < size - 1) {
                    Node next = queue.peek();
                    curr.next = next;
                }
                if (curr.left != null) queue.offer(curr.left);
                if (curr.right != null) queue.offer(curr.right);
            }
        }
        return root;
    }


    // DFS
    public static Node connect2(Node root) {
        connectHelper(root, null);
        return root;
    }


    public static void connectHelper(Node curr, Node next) {
        if (curr == null) return;
        curr.next = next;
        connectHelper(curr.left, curr.right);
        connectHelper(curr.right, curr.next == null ? null : curr.next.left);
    }


    // DFS
    public static Node connect3(Node root) {
        if (root == null) return null;
        if (root.left != null) {
            root.left.next = root.right;
            if (root.next != null) {
                root.right.next = root.next.left;
            }
        }
        connect(root.left);
        connect(root.right);
        return root;
    }


    public static Node connectInConstantSpace(Node root) {
        Node dummyHead = new Node(-1);
        Node pre = dummyHead;
        Node ret = root;

        while (root != null) {
            if (root.left != null) {
                pre.next = root.left;
                pre = pre.next;
            }
            if (root.right != null) {
                pre.next = root.right;
                pre = pre.next;
            }

            root = root.next;
            if (root == null) {
                pre = dummyHead;
                root = dummyHead.next;
                dummyHead.next = null;
            }
        }
        return ret;
    }


    /**
     * 226. Invert Binary Tree
     *
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        root.left = right;
        root.right = left;
        return root;

    }


    /**
     * 129. Sum Root to Leaf Numbers
     *
     * @param root
     * @return
     */
    public int sumNumbers(TreeNode root) {
        return pathSumHelper(root, 0);
    }

    public static int pathSumHelper(TreeNode root, int currVal) {
        if (root == null) return 0;
        currVal = currVal * 10 + root.val;
        if (root.left == null && root.right == null) {
            return currVal;
        }
        return pathSumHelper(root.left, currVal) + pathSumHelper(root.right, currVal);
    }


    public int sumNumbersBFS(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> nodes = new LinkedList<>();
        // record parent values
        Queue<Integer> values = new LinkedList<>();

        int sum = 0;
        nodes.offer(root);
        values.offer(0);
        while (!nodes.isEmpty()) {
            int size = nodes.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = nodes.poll();
                Integer value = values.poll();
                value = value * 10 + node.val;
                if (node.left != null){
                    values.offer(value);
                    nodes.offer(node.left);
                }

                if(node.right != null){
                    values.offer(value);
                    nodes.offer(node.right);
                }

                if(node.left == null && node.right == null){
                    sum += value;
                }
            }
        }
        return sum;
    }


    /**
     * 297. Serialize and Deserialize Binary Tree
     * @param root
     * @return
     */
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(root == null) return "";
        StringBuilder sb = new StringBuilder();
        serializeHelper(root, sb);
        return sb.deleteCharAt(sb.length()-1).toString();
    }

    public static void serializeHelper(TreeNode root, StringBuilder sb){
        if(root == null){
            sb.append("#").append(",");
        }else{
            sb.append(root.val).append(",");
            serializeHelper(root.left, sb);
            serializeHelper(root.right, sb);
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data == null || data.trim().length() == 0) return null;
        Queue<String> queue = new LinkedList<>(Arrays.asList(data.split(",")));
        return deserializeHelper(queue);
    }

    public static TreeNode deserializeHelper(Queue<String> queue){
        String currVal = queue.poll();
        if(currVal.equals("#")) return null;
        TreeNode root = new TreeNode(Integer.parseInt(currVal));
        root.left = deserializeHelper(queue);
        root.right = deserializeHelper(queue);
        return root;
    }




    /**
     * 257. Binary Tree Paths
     * @param root
     * @return
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> ret = new ArrayList<>();
        pathHelper("", ret, root);
        return ret;
    }

    public static void pathHelper(String temp, List<String> ret, TreeNode root){
        if(root.left == null && root.right == null){
            ret.add(temp + root.val);
            return;
        }

        if(root.left != null){
            pathHelper(temp + root.val + "->", ret, root.left);
        }

        if(root.right != null){
            pathHelper(temp + root.val + "->", ret, root.right);
        }

    }


    /**
     * 310. Minimum Height Trees
     * @param n
     * @param edges
     * @return
     */
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> ret = new ArrayList<>();
        if(n == 0) return ret;
        if(n == 1){
            ret.add(0);
            return ret;
        }

        List<List<Integer>> graph = new ArrayList<>();
        for(int i = 0; i<n; i++){
            graph.add(new ArrayList<>());
        }

        for(int[] edge: edges){
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        int[] degree = new int[n];
        int count = n;
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i<n; i++){
            degree[i] = graph.get(i).size();
            if(degree[i] == 1){
                queue.add(i);
            }
        }

        while(count > 2){
            int size = queue.size();
            for(int i=0; i<size; i++){
                int vertex = queue.poll();
                count -= 1;
                for(int neighbor: graph.get(vertex)){
                    degree[neighbor] -= 1;
                    if(degree[neighbor] == 1) queue.offer(neighbor);
                }
            }
        }
        ret.addAll(queue);
        return ret;
    }


    /**
     * 105. Construct Binary Tree from Preorder and Inorder Traversal
     *
     * @param preorder
     * @param inorder
     * @return
     */
    int preorderIndex = 0;
    public TreeNode buildTreeByPreAndInOrder(int[] preorder, int[] inorder) {
        if(preorder.length == 1) return new TreeNode(preorder[0]);
        return helper(inorder, 0, inorder.length-1, preorder);

    }


    public TreeNode helper(int[] inorder, int start, int end, int[] preorder){
        if(start < 0 || end >= inorder.length || start > end) return null;
        TreeNode root = new TreeNode(preorder[preorderIndex]);
        int pos = -1;
        for(int i = start; i<=end; i++){
            if(inorder[i] == root.val){
                pos = i;
                break;
            }
        }

        preorderIndex += 1;
        TreeNode left = helper(inorder, start, pos-1, preorder);
        TreeNode right = helper(inorder, pos+1, end, preorder);
        root.left = left;
        root.right = right;
        return root;
    }

    int postorderIndex = 0;
    public TreeNode buildTreeByPostAndInorder(int[] inorder, int[] postorder) {
        if(postorder.length == 1) return new TreeNode(postorder[0]);
        postorderIndex = postorder.length-1;
        return helper2(inorder, 0, inorder.length-1, postorder);
    }


    public TreeNode helper2(int[] inorder, int start, int end, int[] postorder){
        if(start < 0 || end >= inorder.length || start > end) return null;
        TreeNode root = new TreeNode(postorder[postorderIndex]);

        int pos = -1;
        for(int i = start; i<=end; i++){
            if(inorder[i] == root.val){
                pos = i;
                break;
            }
        }
        postorderIndex -= 1;
        root.right = helper2(inorder, pos+1, end, postorder);
        root.left = helper2(inorder, start, pos-1, postorder);
        return root;
    }


    /**
     * 108. Convert Sorted Array to Binary Search Tree
     * @param nums
     * @return
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        if(nums.length == 1) return new TreeNode(nums[0]);
        return bstHelper(nums, 0, nums.length-1);
    }


    public static TreeNode bstHelper(int[] nums, int start, int end){
        if(start > end || start < 0 || end >= nums.length) return null;
        int mid = (end - start)/2 + start;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = bstHelper(nums, start, mid-1);
        root.right = bstHelper(nums, mid+1, end);
        return root;
    }


    /**
     * 109. Convert Sorted List to Binary Search Tree
     * @param head
     * @return
     */
    public TreeNode sortedListToBST(ListNode head) {
        if(head == null) return null;
        return linkedListBstHelper(head, null);
    }


    public static TreeNode linkedListBstHelper(ListNode start, ListNode end){
        if(start == end) return null;
        ListNode mid = findMid(start, end);
        TreeNode root = new TreeNode(mid.val);
        // end is exclusive
        root.left = linkedListBstHelper(start, mid);
        root.right = linkedListBstHelper(mid.next, end);
        return root;
    }

    public static ListNode findMid(ListNode start, ListNode end){
        ListNode fast = start, slow = start;
        while(fast != end && fast.next != end){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }


    /**
     * 654. Maximum Binary Tree
     * <p>
     *     divide and conquer: O(n*n) at worst case
     *     stack: O(n)
     * </p>
     * @param nums
     * @return
     */
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        if(nums.length == 0) return null;
        return maxBTHelper(nums, 0, nums.length-1);
    }

    public static TreeNode maxBTHelper(int[] nums, int start, int end){
        if(start > end) return null;
        int maxIndex = findMax(nums, start, end);
        TreeNode root = new TreeNode(nums[maxIndex]);
        root.left = maxBTHelper(nums, start, maxIndex-1);
        root.right = maxBTHelper(nums, maxIndex+1, end);
        return root;
    }


    public static int findMax(int[] nums, int start, int end){
        if(start == end) return start;
        int max = nums[start];
        int index = start;
        for(int i = start; i<=end; i++){
            if(max < nums[i]){
                max = nums[i];
                index = i;
            }
        }
        return index;
    }


    public TreeNode constructMaximumBinaryTree2(int[] nums) {
        if(nums.length == 0) return null;

        Deque<TreeNode> stack = new LinkedList<>();
        for(int n: nums){
            TreeNode curr = new TreeNode(n);
            // find first node whose value is less than curr
            while(!stack.isEmpty() && stack.peek().val < n){
                // curr should be its root
                // node in stack is in the left side of n (traverse from left to right)
                curr.left = stack.pop();
            }
            // peek now is larger than n
            if(!stack.isEmpty()){
                stack.peek().right = curr;
            }
            stack.push(curr);
        }
        return stack.isEmpty()? null: stack.removeLast();
    }


    /**
     * 889. Construct Binary Tree from Preorder and Postorder Traversal
     * @param preorder
     * @param postorder
     * @return
     */
    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        if(preorder.length == 0) return null;
        if(preorder.length == 1) return new TreeNode(preorder[0]);
        return constructFromPrePostHelper(preorder, 0, postorder.length-1,
                postorder, 0, postorder.length-1);

    }

    public static TreeNode constructFromPrePostHelper(int[] preorder, int preStart, int preEnd,
                                                      int[] postorder, int postStart, int postEnd){
        if(preStart > preEnd) return null;
        if(preStart == preEnd) return new TreeNode(preorder[preStart]);

        TreeNode root = new TreeNode(preorder[preStart]);
        int preLeftRoot = preStart + 1;
        int postLeftRoot = findRoot(preorder[preLeftRoot], postorder, postStart, postEnd);
        int preLeftEnd = preLeftRoot + (postLeftRoot - postStart);

        root.left = constructFromPrePostHelper(preorder, preLeftRoot, preLeftEnd,
                postorder, postStart, postLeftRoot);
        root.right = constructFromPrePostHelper(preorder, preLeftEnd+1, preEnd,
                postorder, postLeftRoot+1, postEnd-1);
        return root;

    }

    public static int findRoot(int val, int[] postorder, int start, int end){
        for(int i = start; i<=end; i++){
            if(postorder[i] == val) return i;
        }
        return -1;
    }


    public static void main(String[] args) {
        Tree solution = new Tree();
        ListNode head  = new ListNode(-10, new ListNode(-3));
        head.next.next = new ListNode(0, new ListNode(5));
        head.next.next.next.next = new ListNode(9);
        solution.sortedListToBST(head);
    }






}
