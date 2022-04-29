public class NthDigits {

    /**
     * 400. Nth Digit
     * @param n
     * @return
     */
    public static int findNthDigit(int n) {
        int len = 1, start = 1;
        // 必须用long，不然会overflow
        long base = 9L;
        while(n > len * base){
            n -= len * base;
            len += 1;
            base *= 10;
            start *= 10;
        }
        // 因为从start开始，所以移除一个, e.g. 11-9=2, 从9后面开始数第二个，也是是start(10)的第二个数字
        n = n-1;
        start = start + n/len;
        int rest = n%len;
        String s = "" + start;
        return Character.getNumericValue(s.charAt(rest));
    }


    public static void main(String[] args) {
        System.out.println(findNthDigit(1000000000));
    }
}
