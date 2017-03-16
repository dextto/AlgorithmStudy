// https://leetcode.com/problems/container-with-most-water/

package leetcode;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class No011_ContainerWithMostWater {

    @Test
    public void test() {
        assertThat(maxArea(new int[]{1, 1}), is(1));
        assertThat(maxArea(new int[]{1, 2}), is(1));
        assertThat(maxArea(new int[]{1, 2, 3}), is(2));
        assertThat(maxArea(new int[]{2, 1, 3}), is(4));

        assertThat(maxArea(new int[]{1, 2, 3, 4}), is(4));
        assertThat(maxArea(new int[]{1, 2, 4, 3}), is(4));
        assertThat(maxArea(new int[]{2, 1, 3, 4}), is(6));
        assertThat(maxArea(new int[]{2, 4, 3, 1}), is(4));
    }
/*
    public int maxArea(int[] height) {
        int area = 0;
        for (int i = 0; i < height.length; i++) {
            for (int j = i + 1; j < height.length; j++) {
                int h = Math.min(height[i], height[j]);
                int w = j - i;
                area = Math.max(area, h * w);
            }
        }
        return area;
    }
*/

    public int maxArea(int[] height) {
        int maxArea = 0;
        int i = 0;
        int j = height.length - 1;

        while (i < j) {
            int area = (j - i) * (Math.min(height[i], height[j]));
            maxArea = Math.max(maxArea, area);
            if (height[i] < height[j]) i++;
            else j--;
        }

        return maxArea;
    }
}
