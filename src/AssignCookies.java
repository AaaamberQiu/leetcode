import java.util.Arrays;

public class AssignCookies {

    /**
     * 455. Assign Cookies
     * <p>
     *     sort two arrays
     *     give the smallest cookie to the least greedy child
     * </p>
     * @param g
     * @param s
     * @return
     */
    public static int findContentChildren(int[] g, int[] s) {
        if(s.length == 0) return 0;

        Arrays.sort(g);
        Arrays.sort(s);

        int i = 0;
        for(int j = 0; i<g.length && j<s.length; j++){
            if (g[i] <= s[j]) {
                i++;
            }
        }
        return i;
    }

}
