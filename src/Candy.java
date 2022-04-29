import java.util.Arrays;

public class Candy {

    public static int candy(int[] ratings) {
        int n = ratings.length;
        int[] candies = new int[n];

        Arrays.fill(candies, 1);
        // check right neighbors
        for (int i = 0; i < n - 1; i++) {
            if (ratings[i] < ratings[i + 1]) {
                candies[i + 1] = candies[i] + 1;
            }
        }

        for (int i = n - 1; i > 0; i--) {
            if (ratings[i] < ratings[i - 1] && candies[i] >= candies[i-1]) {
                candies[i - 1] = candies[i] + 1;
            }
        }

        int total = 0;
        for (int count : candies) {
            total += count;
        }
        return total;
    }


    public static void main(String[] args) {
        candy(new int[]{1, 3, 4,5,2});
    }

}
