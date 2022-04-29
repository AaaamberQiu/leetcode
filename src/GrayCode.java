import java.util.ArrayList;
import java.util.List;

public class GrayCode {

    /**
     * 89. Gray Code
     * <p>
     *     The key point: gray(n) can be built by backward traverse gray(n-1) and append 1 at the beginning
     *     n=1 (00,01)
     *     n=2 (00,01)(11,10)
     * </p>
     * @param n
     * @return
     */
    public List<Integer> grayCode(int n) {
        List<Integer> ret = new ArrayList<>();
        ret.add(0);
        for(int i = 0; i<n; i++){
            int prevSize = ret.size();
            int prepend = 1<<i;
            // backward traverse
            for(int j = prevSize-1; j>=0; j--){
                int num = ret.get(j) + prepend;
                ret.add(num);
            }
        }
        return ret;
    }


}
