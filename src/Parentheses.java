import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Parentheses {
    /**
     * 22. Generate Parentheses
     *
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        List<String> ret = new ArrayList<>();
        helper(ret, n, n, n, "");
        return ret;
    }


    private static void helper(List<String> ret, int open, int close, int n, String s) {
        if (open == 0 && close == 0) {
            ret.add(s);
            return;
        }

        if (open > 0) {
            helper(ret, open - 1, close, n, s + "(");
        }

        if (open < close) {
            helper(ret, open, close - 1, n, s + ")");
        }
    }


    /**
     * 32. Longest Valid Parentheses
     *
     * @param s
     * @return
     */
    public static int longestValidParentheses(String s) {
        if (s == null || s.length() < 2) {
            return 0;
        }

        int ret = 0;
        int[] dp = new int[s.length() + 1];
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i + 1] = dp[i - 1] + 2;
                } else {
                    int x = i - dp[i] - 1;
                    if (dp[i] > 0 && x >= 0 && s.charAt(x) == '(') {
                        dp[i + 1] = dp[i] + dp[x] + 2;
                    }
                }
                ret = Math.max(ret, dp[i + 1]);
            }
        }
        return Math.max(ret, dp[s.length()]);
    }

    // DP + stack stores nearest open bracket index
    public static int longestValidParentheses2(String s) {
        if (s == null || s.length() < 2) return 0;
        int n = s.length();

        int ret = 0;
        int[] dp = new int[n];
        Stack<Integer> openIndices = new Stack<>();
        // dp[i]: the len of longest valid parentheses ending at i
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '(') {
                openIndices.push(i);
            } else {
                if (!openIndices.isEmpty()) {
                    int j = openIndices.pop();
                    int prevLen = j > 0 ? dp[j - 1] : 0;
                    // [i,j] 这段可以和 j-1 的连起来
                    dp[i] = prevLen + (i - j + 1);
                    ret = Math.max(dp[i], ret);
                }
            }
        }
        return ret;
    }


    // One stack: stores
    public static int longestValidParentheses3(String s) {
        Stack<Integer> stack = new Stack<>();
        int ret = 0;

        int left = -1;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') stack.push(i);
            else {
                if (stack.isEmpty()) left = i;
                else {
                    stack.pop();
                    if (stack.isEmpty()) ret = Math.max(ret, i - left);
                    else ret = Math.max(ret, i - stack.peek());
                }
            }
        }
        return ret;
    }


    /**
     * 20. Valid Parentheses
     *
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        if (s.length() == 0) return true;
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') stack.push(c);
            else {
                if (stack.isEmpty()) return false;
                if (!isMatch(stack.pop(), c)) return false;
            }
        }
        return stack.isEmpty();

    }

    public static boolean isMatch(char open, char close) {
        if (open == '{') {
            return close == '}';
        }
        if (open == '(') {
            return close == ')';
        }
        if (open == '[') {
            return close == ']';
        }
        return false;
    }


    /**
     * 301. Remove Invalid Parentheses
     *
     * @param s
     * @return
     */
    public List<String> removeInvalidParentheses(String s) {
        List<String> ret = new ArrayList<>();
        if (s.length() == 0) return ret;
        removeHelper(s, 0, 0, ret, new char[]{'(', ')'});
        return ret;
    }


    // s[left, right] is a piece of string with invalid parentheses
    public static void removeHelper(String s, int left, int right, List<String> ret, char[] parentheses) {
        int stack = 0;
        for (; right < s.length(); right++) {
            char c = s.charAt(right);
            if (c == parentheses[0]) stack++;
            else if (c == parentheses[1]) stack--;
            if (stack < 0) break;
        }

        // more ), need to remove
        if (stack < 0) {
            for (; left <= right; left++) {
                // if not ) continue
                if (s.charAt(left) != parentheses[1]) continue;
                // avoid duplicates
                if (left > 1 && s.charAt(left) == s.charAt(left - 1)) continue;
                // remove one ), and try to find other possible solutions
                // remove one ) => s.substring(0, left) + s.substring(left+1), 因为remove left，所以本来的left+1 变成了left
                removeHelper(s.substring(0, left) + s.substring(left + 1), left, right, ret, parentheses);
            }
        } else if (stack > 0) {
            removeHelper(new StringBuilder(s).reverse().toString(), 0, 0, ret, new char[]{')', '('});
        } else {
            ret.add(parentheses[0] == '(' ? s : new StringBuilder(s).reverse().toString());
        }

    }

    public static void main(String[] args) {
        int ret = longestValidParentheses2(")()())");
        System.out.println(ret);
    }

}
