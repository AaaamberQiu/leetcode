import java.util.Stack;

public class RemoveKdigits {

    /**
     * 402. Remove K Digits
     * <p>
     *     eliminate the peak element for k times by using stack
     *     similar to 316. Remove Duplicate Letters
     * </p>
     * @param num
     * @param k
     * @return
     */
    public String removeKdigits(String num, int k) {
        int n = num.length();
        if(n <= k) return "0";

        Stack<Integer> stack = new Stack<>();
        int i = 0;
        while(i < n){
            int val = Integer.parseInt("" + num.charAt(i));
            while(!stack.isEmpty() && val < stack.peek() && k > 0){
                stack.pop();
                k--;
            }
            stack.push(val);
            i++;
        }

        // handle the corner case that elements are mono increasing
        while(k > 0){
            stack.pop();
            k--;
        }

        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()){
            sb.append(stack.pop());
        }

        sb.reverse();

        //remove leading zeros
        while (sb.length() >= 1 && sb.charAt(0) == '0') {
            sb.deleteCharAt(0);

        }
        return sb.length() == 0? "0" : sb.toString();
    }
}
