import java.util.Stack;

public class TrappingRainWater_42 {

    // pre-compute
    public int trap(int[] height) {
        int n = height.length;
        int[] leftMax = new int[n];
        int[] rightMax = new int[n];

        // leftMax[i]: the max height in [0, i)
        leftMax[0] = height[0];
        // rightMax[i]: the max height in (i, n-1]
        rightMax[n - 1] = height[n - 1];
        for (int i = 1; i < n - 1; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i - 1]);
        }
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i + 1]);
        }

        int water = 0;
        for (int i = 0; i < n; i++) {
            int temp = Math.min(leftMax[i], rightMax[i]) - height[i];
            water += temp > 0 ? temp : 0;
        }
        return water;
    }

    // divide and conquer
    public int trap2(int[] height) {
        int n = height.length;
        if (n <= 2) return 0;
        return helper(height, 1, n - 2, height[0], height[n - 1]);
    }

    private static int helper(int[] height, int left, int right, int leftMax, int rightMax) {
        if (left > right) {
            return 0;
        }

        if (left == right) {
            int temp = Math.min(leftMax, rightMax) - height[left];
            return temp > 0 ? temp : 0;
        }

        int maxIndex = findMax(height, left, right);
        int leftPart = helper(height, left, maxIndex - 1, leftMax, Math.max(height[maxIndex], rightMax));
        int rightPart = helper(height, maxIndex + 1, right, Math.max(height[maxIndex], leftMax), rightMax);
        int pos = Math.min(leftMax, rightMax) - height[maxIndex];
        pos = pos < 0 ? 0 : pos;
        return leftPart + pos + rightPart;

    }

    private static int findMax(int[] height, int left, int right) {
        if (height.length == 0) return 0;
        int temp = height[left];
        int ret = left;
        for (int i = left + 1; i <= right; i++) {
            if (height[i] > temp) {
                temp = height[i];
                ret = i;
            }
        }
        return ret;
    }

    // two pointer
    public int trap3(int[] height) {
        int n = height.length;
        if (n <= 2) return 0;

        int left = 1, right = n - 2;
        int leftMax = height[0], rightMax = height[n - 1];
        int water = 0;

        while (left <= right) {
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);

            if (leftMax < rightMax) {
                water += leftMax - height[left];
                left++;
            } else {
                water += rightMax - height[right];
                right--;
            }
        }
        return water;
    }

    // monotonic stack
    public int trap4(int[] height) {
        int water = 0;
        // stack stores index
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < height.length; i++) {
            int rightBound = height[i];
            while (!stack.isEmpty() && rightBound > height[stack.peek()]) {
                int currHeight = height[stack.pop()];
                if (!stack.isEmpty()) {
                    int leftBound = height[stack.peek()];
                    water += (Math.min(leftBound, rightBound) - currHeight) * (i - stack.peek() - 1);
                }
            }
            stack.push(i);
        }
        return water;
    }
}
