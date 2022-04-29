import base.TreeNode;

import java.util.*;

public class BinarySearchTree {

    /**
     * 98. Validate Binary Search Tree
     * <p>
     * shrink the lower and upper bounds
     * </p>
     *
     * @param root
     * @return
     */
    // recursion
    public boolean isValidBST(TreeNode root) {
        return helper(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public static boolean helper(TreeNode root, Long lower, Long upper) {
        if (root == null) return true;
        if (root.val >= upper || root.val <= lower) return false;
        return helper(root.left, lower, Long.valueOf(root.val)) && helper(root.right, Long.valueOf(root.val), upper);
    }

    // iteration based on in-order traversal
    public boolean isValidBST2(TreeNode root) {
        if (root == null) return true;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root, prev = null;
        while (!stack.isEmpty() || curr != null) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }

            TreeNode top = stack.pop();
            if (prev != null && prev.val >= top.val) return false;
            prev = top;
            curr = top.right;
        }
        return true;
    }


    /**
     * 99. Recover Binary Search Tree
     *
     * @param root
     */
    public TreeNode prev = null, first = null, second = null;

    public void recoverTree(TreeNode root) {
        inOrderTraverse(root);
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }

    // Utilize the fact that prev.val is always larger than curr.val, if we meet the prev.val>=root.val, then the bad node occurs
    // the first bad node is prev, but second is curr
    // e.g. [3,1,4,null,null,2] -> 1,3,2,4
    public void inOrderTraverse(TreeNode root) {
        if (root == null) return;
        inOrderTraverse(root.left);
        if (first == null && (prev == null || prev.val >= root.val)) {
            first = prev;
        }
        if (first != null && prev.val >= root.val) {
            second = root;
        }

        prev = root;
        inOrderTraverse(root.right);
    }


    /**
     * 449. Serialize and Deserialize BST
     * <p>
     * pre-order + queue + BST property
     * </p>
     *
     * @param root
     * @return
     */
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) return "";
        StringBuilder sb = new StringBuilder();
        serializeHelper(root, sb);
        return sb.deleteCharAt(sb.length() - 1).toString();
    }

    public static void serializeHelper(TreeNode root, StringBuilder sb) {
        if (root == null) return;
        sb.append(root.val).append(",");
        serializeHelper(root.left, sb);
        serializeHelper(root.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.trim().length() == 0) return null;
        Queue<String> queue = new LinkedList<>(Arrays.asList(data.split(",")));
        return deserializeHelper(queue, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public static TreeNode deserializeHelper(Queue<String> queue, int lower, int upper) {
        if (queue.isEmpty()) return null;
        Integer currVal = Integer.valueOf(queue.peek());
        if (currVal < lower || currVal > upper) return null;
        queue.poll();
        TreeNode root = new TreeNode(currVal);
        root.left = deserializeHelper(queue, lower, currVal);
        root.right = deserializeHelper(queue, currVal, upper);
        return root;
    }


    /**
     * 95. Unique Binary Search Trees II
     * Both recursion and DP
     * @param n
     * @return
     */
    public List<TreeNode> generateTrees(int n) {
        if(n == 0) return new ArrayList<>();
        return generateTreeHelper(1, n);
    }


    public static List<TreeNode> generateTreeHelper(int lower, int upper) {
        List<TreeNode> ret = new ArrayList<>();
        int len = upper - lower + 1;
        if(len == 0){
            ret.add(null);
            return ret;
        }

        for(int i = lower; i<= upper; i++){
            List<TreeNode> left = generateTreeHelper(lower, i-1);
            List<TreeNode> right = generateTreeHelper(i+1, upper);
            for(TreeNode l: left){
                for(TreeNode r: right){
                    TreeNode root = new TreeNode(i);
                    root.left = l;
                    root.right = r;
                    ret.add(root);
                }
            }
        }
        return ret;
    }

    // not really understand the clone logic
    public List<TreeNode> generateTrees2(int n) {
        // dp[i] means trees with size of i
        List<TreeNode>[] dp = new ArrayList[n+1];
        dp[0] = new ArrayList<>(null);

        for(int len = 1; len <= n; len++){
            dp[len] = new ArrayList<>();

            for(int i = 1; i <= len; i++){
                int leftSize = i-1, rightSize = len - i;

                for(TreeNode left: dp[leftSize]){
                    for(TreeNode right: dp[rightSize]){
                        TreeNode root = new TreeNode(i);
                        root.left = left;
                        root.right = clone(right, i);
                        dp[len].add(root);
                    }
                }
            }
        }
        return dp[n];
    }

    private static TreeNode clone(TreeNode n, int offset) {
        if (n == null) {
            return null;
        }
        TreeNode node = new TreeNode(n.val + offset);
        node.left = clone(n.left, offset);
        node.right = clone(n.right, offset);
        return node;
    }


    /**
     * 96. Unique Binary Search Trees
     * @param n
     * @return
     */
    public int numTrees(int n) {
        int[] dp = new int[n+1];
        dp[0] = 1;

        for(int len = 1; len<=n; len++){
            for(int pos = 1; pos<=len; pos++){
                int leftSize = pos-1, rightSize = len-pos;
                dp[len] += dp[leftSize] * dp[rightSize];
            }
        }
        return dp[n];
    }


    /**
     * 1382. Balance a Binary Search Tree
     * <p>
     *     inorder traverse to sorted array -> to BST
     * </p>
     * @param root
     * @return
     */
    public TreeNode balanceBST(TreeNode root) {
        if(root == null) return null;
        List<Integer> sorted = new ArrayList<>();
        inorderTraverse(root, sorted);
        return convertToBst(sorted, 0, sorted.size()-1);
    }


    public static void inorderTraverse(TreeNode root, List<Integer> sorted){
        if(root == null) return;
        inorderTraverse(root.left, sorted);
        sorted.add(root.val);
        inorderTraverse(root.right, sorted);
    }

    public static TreeNode convertToBst(List<Integer> sorted, int start, int end){
        if(start > end) return null;
        int mid = (end - start)/2 + start;
        TreeNode root = new TreeNode(sorted.get(mid));
        root.left = convertToBst(sorted, start, mid-1);
        root.right = convertToBst(sorted, mid+1, end);
        return root;
    }


    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        bst.generateTrees(3);
    }

}
