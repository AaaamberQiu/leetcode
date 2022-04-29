import java.util.Arrays;
import java.util.TreeSet;

public class UglyNumber {


    /**
     * 313. Super Ugly Number
     * @param n
     * @param primes
     * @return
     */
    public int nthSuperUglyNumber(int n, int[] primes) {
        int[] ugly = new int[n];
        int[] indices = new int[primes.length];
        ugly[0] = 1;

        for(int i = 1; i<n; i++){
            // find min
            int min = Integer.MAX_VALUE;
            for(int j = 0; j<indices.length; j++){
                int base = ugly[indices[j]];
                int prime = primes[j];
                min = Math.min(min, prime * base);
            }
            ugly[i] = min;
            for(int j = 0; j<indices.length; j++){
                int base = ugly[indices[j]];
                int prime = primes[j];
                if(base * prime == min) indices[j] += 1;
            }
        }
        return ugly[n-1];
    }


    // reduce redundant multiplication
    public int nthSuperUglyNumber2(int n, int[] primes) {
        int[] ugly = new int[n];
        // primes[i] = prime, indices[i] = index of ugly num to factor
        int[] indices = new int[primes.length];
        // val[i] = 用prime[i] 乘出来的ugly num
        int[] val = new int[primes.length];
        Arrays.fill(val, 1);

        int min = 1;
        for(int i = 0; i<n; i++){
            ugly[i] = min;

            min = Integer.MAX_VALUE;
            for(int j = 0; j<primes.length; j++){
                if(ugly[i] == val[j]){
                    val[j] = primes[j] * ugly[indices[j]];
                    indices[j] += 1;
                }
                min = Math.min(min, val[j]);
            }
        }
        return ugly[n-1];
    }


    /**
     * 1201. Ugly Number III
     * @param n
     * @param a
     * @param b
     * @param c
     * @return
     */
    public int nthUglyNumber(int n, int a, int b, int c) {
        int min = Math.min(a, Math.min(b, c));

        long start = min, end = 2*(long)Math.pow(10, 9);
        while(start < end){
            long mid = (end - start) / 2 + start;
            int count = count(mid, a, b, c);
            if(count >=n) end = mid;
            else start = mid+1;
        }
        return (int)start;
    }


    private static int count(long num, int a, int b, int c ){
        return (int) (num / a + num / b + num / c
                - num / lcm(a, b)
                - num / lcm(b, c)
                - num / lcm(a, c)
                + num / (lcm(a, lcm(b, c))));
    }

    // least common multiple
    private static long lcm(long a, long b){
        return a*b / gcd(a, b);
    }

    // greatest common division
    private static long gcd(long a, long b){
        if(a == 0) return b;
        return gcd(b%a, a);
    }


    public static void main(String[] args) {
        UglyNumber solution = new UglyNumber();
        int ret = solution.nthSuperUglyNumber2(12, new int[]{2,7,13,19});
        System.out.println(ret);
    }
}
