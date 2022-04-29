import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Triangle {
    /**
     * 118. Pascal's Triangle
     * base case: first row and second row
     * @param numRows
     * @return
     */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        res.add(Arrays.asList(1));
        if(numRows == 1) return res;
        res.add(Arrays.asList(1, 1));

        for(int i = 2; i< numRows; i++){
            List<Integer> row = new ArrayList<>();
            row.add(1);
            List<Integer> prev = res.get(i-1);
            for(int k = 1; k<prev.size(); k++){
                row.add(prev.get(k-1) + prev.get(k));
            }
            row.add(1);
            res.add(row);
        }
        return res;
    }


    /**
     * 119. Pascal's Triangle II
     * @param rowIndex
     * @return
     */
    public List<Integer> getRow(int rowIndex) {
        if(rowIndex == 0) return Arrays.asList(1);
        List<Integer> res = Arrays.asList(1, 1);

        for(int i = 2; i<=rowIndex; i++){
            List<Integer> curr = new ArrayList<>();
            curr.add(1);
            for(int k = 1; k<res.size(); k++){
                curr.add(res.get(k-1) + res.get(k));
            }
            curr.add(1);
            res = curr;
        }
        return res;
    }


    /**
     * 120. Triangle
     *
     * if i == 0, dp[i] = dp[i] + row[i]
     * else if i == row.len-1, dp[i] = dp[i-1] + row[i]
     * else dp[i] = min(dp[i-1], dp[i]) + row[i]
     *
     * should update from len-1 to 1, since we need to keep i-1 unmodified when computing i
     * dp[i-1] is the prev row [i-1]
     * @param triangle
     * @return
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        int rowNum = triangle.size();
        int[] dp = new int[triangle.get(rowNum-1).size()];
        dp[0] = triangle.get(0).get(0);

        int minInRow = Integer.MAX_VALUE;
        for(int r = 1; r<rowNum; r++){
            List<Integer> row = triangle.get(r);
            int temp = Integer.MAX_VALUE;
            for(int i = row.size()-1; i>=0; i--){
                if(i == 0) dp[i] += row.get(i);
                else if(i == row.size()-1) dp[i] = dp[i-1] + row.get(i);
                else dp[i] = Math.min(dp[i-1] + row.get(i), dp[i] + row.get(i));

                temp = Math.min(temp, dp[i]);
            }
            minInRow = temp;

        }
        return minInRow;
    }
}
