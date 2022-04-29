public class CanPlaceFlowers {

    /**
     * 605. Can Place Flowers
     *
     * @param flowerbed
     * @param n
     * @return
     */
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        for(int i = 0; i<flowerbed.length; i++){
            if(flowerbed[i] == 1){
                continue;
            }
            int prev = i==0? 0:flowerbed[i-1];
            int next = i==flowerbed.length-1? 0: flowerbed[i+1];
            if(prev == 0 && next == 0){
                flowerbed[i] = 1;
                n--;
            }
        }
        return  n<=0;
    }

    // without modifying the original input
    // https://leetcode.com/problems/can-place-flowers/discuss/103883/Java-Very-easy-solution
    public boolean canPlaceFlowers2(int[] flowerbed, int n) {
        int zeroCount = 1;
        int res = 0;
        // count 0s to compute how many 1s can be inserted
        // In most cases: (count-1)/2
        // at the beginning/end: count/2
        for(int i = 0; i<flowerbed.length; i++){
            if(flowerbed[i] == 0){
                zeroCount += 1;
            }else{
                res += (zeroCount-1)/2;
                zeroCount = 0;
            }
        }
        if(zeroCount > 0) res += zeroCount/2;
        return res >= n;
    }
}
