import java.util.*;

public class Combination {

    /**
     * 17. Letter Combinations of a Phone Number
     * @param digits
     * @return
     */
    public List<String> letterCombinations(String digits) {
        List<String> ret = new ArrayList<>();
        if(digits == null || digits.length() == 0) return ret;

        Map<Character, List<String>> map = new HashMap<>();
        map.put('2', Arrays.asList("a", "b", "c"));
        map.put('3', Arrays.asList("d", "e", "f"));
        map.put('4', Arrays.asList("g", "h", "i"));
        map.put('5', Arrays.asList("j", "k", "l"));
        map.put('6', Arrays.asList("m", "n", "o"));
        map.put('7', Arrays.asList("p", "q", "r", "s"));
        map.put('8', Arrays.asList("t", "u", "v"));
        map.put('9', Arrays.asList("w", "x", "y", "z"));

        char[] chars = digits.toCharArray();
        helper(chars, 0, ret, map, new StringBuilder());
        return ret;

    }

    public static void helper(char[] chars, int index, List<String> ret, Map<Character, List<String>> map, StringBuilder sb){
        if(index == chars.length){
            ret.add(sb.toString());
            return;
        }
        char c = chars[index];
        List<String> choices = map.get(c);
        for(String choice: choices){
            sb.append(choice);
            helper(chars, index+1, ret, map, sb);
            sb.deleteCharAt(sb.length()-1);
        }
    }


    /**
     * 77. Combinations
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ret = new ArrayList<>();
        if(k == 0) return ret;
        combineHelper(1, n, k, new ArrayList<>(), ret);
        return ret;
    }

    public static void combineHelper(int start, int n, int count, List<Integer> temp, List<List<Integer>> ret){
        if(count == 0){
            ret.add(new ArrayList<>(temp));
            return;
        }
        for(int i = 1; i<=n; i++){
            temp.add(i);
            combineHelper(i+1, n, count-1, temp, ret);
            temp.remove(temp.size()-1);
        }
    }

}
