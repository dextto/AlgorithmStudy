// https://leetcode.com/problems/longest-increasing-subsequence
package leetcode;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class No300_LIS_LongestIncreasingSubsequence {

    @Test
    public void test() throws Exception {
        No300_LIS_LongestIncreasingSubsequence instance = new No300_LIS_LongestIncreasingSubsequence();
        assertEquals(4, instance.lengthOfLIS(new int[] { 10, 9, 2, 5, 3, 7, 101, 18 }));
    }

    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;

        int[] lis = new int[n];
        int ret = 1;
        for (int i = 0; i < n; i++) {
            ret = Math.max(ret, lengthOfLIS(lis, nums, i));
        }
        return ret;
    }

    private int lengthOfLIS(int[] lis, int[] nums, int start) {
        if (lis[start] != 0) return lis[start];

        int ret = 1;
        lis[start] = 1;
        for (int i = start + 1; i < lis.length; i++) {
            if (nums[start] < nums[i]) {
                ret = Math.max(ret, lengthOfLIS(lis, nums, i) + 1);
                lis[start] = ret;
            }
        }
        return ret;
    }
}
