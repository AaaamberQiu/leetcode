public class Pow {

    /**
     * 50. Pow(x, n)
     * @param x
     * @param n
     * @return
     */
    public static double myPow(double x, int n) {
        if(n == 0) return 1.0;
        if(n < 0){
            // n 不可以直接反转，-Integer.MIN_VALUE会导致 overflow
            //n = -n; x = 1/x;
            return 1/x * myPow(1/x, -(n + 1));
        }
        // 用x*x 减少recursion深度，不然会TLE
        return n%2 == 0? myPow(x*x, n/2) : x*myPow(x*x, n/2);
    }

    public static void main(String[] args) {
        System.out.println(myPow(0.00001, 2147483647));
    }
}

