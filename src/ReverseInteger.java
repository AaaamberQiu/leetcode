public class ReverseInteger {

    /**
     * 7. Reverse Integer
     */
    public static int reverse(int x) {
        long ret = 0;
        while(x != 0){
            ret = ret*10 + x%10;
            x = x/10;
        }
        return ret > Integer.MAX_VALUE || ret < Integer.MIN_VALUE? 0 : (int)ret;
    }


    public static int reverse2(int x) {
        int ret = 0;
        int prev = 0;
        while(x != 0){
            ret = ret*10 + x%10;
            // check overflow instead of using larger type
            if((ret - x%10)/10 != prev){
                return 0;
            }
            prev = ret;
            x = x/10;
        }
        return ret;
    }

    public static void main(String[] args) {
        reverse(-2147483648);
    }
}
