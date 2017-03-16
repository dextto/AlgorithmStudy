// https://leetcode.com/problems/missing-number/

package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class No268_MissingNumber {

    @Test
    public void test() throws Exception {
        No268_MissingNumber instance = new No268_MissingNumber();
        assertEquals(2, instance.missingNumber(new int[] { 0, 1, 3 } ));
    }

    public int missingNumber(int[] nums) {
        boolean[] check = new boolean[nums.length + 1];
        for (int i : nums) {
            check[i] = true;
        }

        for (int i = 0; i < check.length; i++) {
            if (!check[i]) return i; 
        }
        return -1;
    }
}
