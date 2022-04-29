import java.util.Arrays;

public class Hindex {

    /**
     * 274. H-Index
     * <p>
     *     sort
     *     counting sort
     * </p>
     * @param citations
     * @return
     */
    public static int hIndex(int[] citations) {
        Arrays.sort(citations);
        int len=citations.length;
        for(int i=0;i<len;i++){
            // (len-i) has at least citation[i]
            if(citations[i]>=len-i) return len-i;

        }
        return 0;
    }

    public static int hIndex2(int[] citations) {
        // h papers has h citation => at best case n paper has n citation => h<=n
        int n = citations.length;
        int[] count = new int[n+1];
        for(int c : citations){
            if(c >= n) count[n]++;
            else count[c]++;
        }

        // i is citation, count[i] is #papers
        int total = 0;
        for(int i = n; i>=0; i--){
            total += count[i];
            if(total >= i) return i;
        }
        return 0;
    }

    public static void main(String[] args) {
       int ret =  hIndex(new int[]{100});
        System.out.println(ret);
    }
}
