// https://leetcode.com/problems/median-of-two-sorted-arrays/

package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.stream.IntStream;

import org.junit.Test;

public class No004_MedianOfTwoSortedArrays {

    @Test
    public void test() throws Exception {
        No004_MedianOfTwoSortedArrays instance = new No004_MedianOfTwoSortedArrays();
        assertEquals(2.0, instance.findMedianSortedArrays(new int[] { 1, 3 }, new int[] { 2 }),
                0.1);
        assertEquals(2.5, instance.findMedianSortedArrays(new int[] { 3, 2 }, new int[] { 4, 1 }),
                0.1);
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] nums = IntStream.concat(Arrays.stream(nums1), Arrays.stream(nums2))
                .sorted().toArray();
        int midIdx = nums.length / 2;
        if (nums.length % 2 == 0) {
            return (double)((nums[midIdx - 1] + nums[midIdx])) / 2;
        } else {
            return (double)nums[midIdx];
        }
    }

}
