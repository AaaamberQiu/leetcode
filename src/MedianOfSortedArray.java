public class MedianOfSortedArray {

    /**
     * 4. Median of Two Sorted Arrays
     * <p>binary search： https://www.youtube.com/watch?v=LPFhl65R7ww&t=152s
     * </p>
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // make the nums1 is the shorter one
        if(nums2.length < nums1.length) return findMedianSortedArrays(nums2, nums1);

        if(nums1.length == 0){
            int mid = (nums2.length-1)/2;
            if(nums2.length%2 == 0) return (nums2[mid] + nums2[mid+1])/2.0;
            else return nums2[mid];
        }

        // partX + partY = (nums1.length + nums2.length)/2
        // + 1 is to handle odd case
        int partSize = (nums1.length + nums2.length + 1)/2;

        // !! 计算partX的时候 endX是len，不是len-1；如果是len-1，在只有一个element的情况下，partX本来应该是1，但是被记成0
        int startX = 0, endX = nums1.length;
        while(startX <= endX){
            // found maxLeftX <= minRightY && maxLeftY <= minRightX
            int partX = (startX + endX)/2;
            int partY = partSize - partX;

            int maxLeftX = partX == 0? Integer.MIN_VALUE : nums1[partX-1];
            int minRightX = partX == nums1.length? Integer.MAX_VALUE: nums1[partX];
            int maxLeftY = partY == 0? Integer.MIN_VALUE : nums2[partY-1];
            int minRightY = partY == nums2.length? Integer.MAX_VALUE: nums2[partY];

            if(maxLeftX <= minRightY && maxLeftY <= minRightX){
                int totalSize = nums1.length + nums2.length;
                if(totalSize %2 == 0){
                    return (Math.max(maxLeftX, maxLeftY) + Math.min(minRightX, minRightY))/2.0;
                }else{
                    return Math.max(maxLeftX, maxLeftY);
                }
            }else if(maxLeftX > minRightY){
                // move partX to left
                endX = partX-1;
            }else{
                // move partX to right
                startX = partX+1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        MedianOfSortedArray solution = new MedianOfSortedArray();
        System.out.println(solution.findMedianSortedArrays(new int[]{2,3}, new int[]{}));
    }
}
