import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class SimplifyPath_71 {

    /**
     * 71. Simplify Path
     * @param path
     * @return
     */
    public String simplifyPath(String path) {
        String[] tokens = path.split("/");

        Stack<String> stack = new Stack<>();
        for(String s : tokens){
            // handle double slash and single period
            if(s.trim().length() == 0 || s.equals(".")) continue;
            else if(s.equals("..")){
                if(!stack.isEmpty()) stack.pop();
            }else{
                stack.push(s);
            }
        }
        return "/" + String.join("/", stack);
    }
}
