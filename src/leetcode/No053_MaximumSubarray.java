// https://leetcode.com/problems/maximum-subarray/

package leetcode;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class No053_MaximumSubarray {

    @Before
    public void setUp() {
    }

    @Test
    public void test() {
        assertEquals(6, maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
    }

    public int maxSubArray(int[] nums) {
        int N = nums.length;
        int[] cache = new int[N];
        cache[0] = nums[0];
        int max = nums[0];

        for (int i = 1; i < N; i++) {
            cache[i] = nums[i] + ((cache[i - 1] > 0) ? cache[i - 1] : 0);
            max = Math.max(max, cache[i]);
        }

        return max;
    }
}
