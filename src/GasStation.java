public class GasStation {

    /**
     * 134. Gas Station
     *
     * <p>
     *     Key point: when the gas left in the tank is negative at ith station (cannot reach next station),
     *     we need to choose (i+1) as the next possible start
     * </p>
     * @param gas
     * @param cost
     * @return
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        int left = 0;
        int sum = 0;
        int start = 0;
        for(int i = 0; i<n; i++){
            left += gas[i] - cost[i];
            sum += gas[i] - cost[i];

            // if the gas left in tank < 0, it means current start is not available
            if(left < 0){
                start = i+1;
                left = 0;
            }
        }
        return sum < 0? -1:start;
    }
}
