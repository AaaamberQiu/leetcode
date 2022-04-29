public class CountPrime {

    /**
     * 204. Count Primes
     *
     * @param n
     * @return
     */
    public int countPrimes(int n) {
        boolean[] composite = new boolean[n + 1];
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (composite[i]) continue;
            count += 1;
            // if find a prime, then mark all composite nums based on prime
            for (int j = 2; i * j < n; j++) {
                composite[i * j] = true;
            }

        }
        return count;

    }
}
