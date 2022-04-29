public class Sqrt {

    /**
     * 50. Pow(x, n)
     * @param x
     * @return
     */
    public static int mySqrt(int x) {
        if(x == 0) return x;
        int start = 1, end = x/2;
        while(start < end){
            int mid = (end - start)/2 + start;
            if(mid > x/mid) end = mid;
            else{
                // if mid * mid <=x
                if((mid+1) > x/(mid+1)) return mid;
                else start = mid+1;

            }
        }
        return start;
    }
}
