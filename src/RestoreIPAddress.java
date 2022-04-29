import java.util.ArrayList;
import java.util.List;

public class RestoreIPAddress {

    /**
     * 93. Restore IP Addresses
     * @param s
     * @return
     */
    public List<String> restoreIpAddresses(String s) {
        List<String> ret = new ArrayList<>();
        if(s.length() < 4) return ret;
        helper(s, 0,  ret, new ArrayList<>(), 4);
        return ret;
    }

    public static void helper(String s, int startIndex, List<String> ret, List<Integer> temp, int count){
        if(count == 0){
            if(startIndex == s.length()) ret.add(convert(temp));
            return;
        }

        // at each position, there are three choices
        for(int i = 1; i<=3; i++){
            if(isValid(s, startIndex, startIndex + i)){
                temp.add(Integer.parseInt(s.substring(startIndex, startIndex+i)));
                helper(s, startIndex+i, ret, temp, count-1);
                temp.remove(temp.size()-1);
            }
        }
    }


    public static String convert(List<Integer> nums){
        StringBuilder sb = new StringBuilder();
        for(Integer num: nums){
            sb.append(num).append(".");
        }
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }

    public static boolean isValid(String s, int startIndex, int endIndex){
        if(startIndex >= s.length() || endIndex > s.length()) return false;
        if(s.charAt(startIndex) == '0') return s.substring(startIndex, endIndex).length() == 1;
        Integer num = Integer.parseInt(s.substring(startIndex, endIndex));
        return num > 0 && num <=255;
    }

    public static void main(String[] args) {
        RestoreIPAddress solution = new RestoreIPAddress();
        solution.restoreIpAddresses("101023");
    }
}
