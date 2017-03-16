// https://leetcode.com/problems/zigzag-conversion/

package leetcode;

import static org.junit.Assert.*;

import org.junit.Test;

public class No009_PalindromeNumber {

    @Test
    public void test() {
        assertTrue(isPalindrome(1));
        assertTrue(isPalindrome(121));
        assertTrue(isPalindrome(1234321));

        assertFalse(isPalindrome(-1));
        assertFalse(isPalindrome(Integer.MIN_VALUE));
    }

    public boolean isPalindrome(int x) {
        if (x < 0) return false;

        String s = Integer.toString(x);
        int center = s.length() / 2;
        for (int i = 0; i < center; i++) {
            int left;
            int right;
            if (s.length() % 2 == 0) {
                left = center - (i + 1);
                right = center + i;
            } else {
                left = center - (i + 1);
                right = center + (i + 1);
            }
            if (s.charAt(left) != s.charAt(right)) return false;
        }
        
        return true;
    }
}
