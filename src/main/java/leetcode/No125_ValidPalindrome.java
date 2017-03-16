// https://leetcode.com/problems/valid-palindrome/#/description

package leetcode;


import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class No125_ValidPalindrome {

    @Test
    public void test() throws Exception {
        No125_ValidPalindrome instance = new No125_ValidPalindrome();
        assertTrue(instance.isPalindrome(""));
        assertTrue(instance.isPalindrome("aa"));
        assertTrue(instance.isPalindrome("aA"));
        assertTrue(instance.isPalindrome("`l;`` 1o1 ??;l`"));
        assertTrue(instance.isPalindrome("A man, a plan, a canal: Panama"));
        assertFalse(instance.isPalindrome("0P"));
        assertFalse(instance.isPalindrome("race a car"));
    }

    public boolean isPalindrome(String s) {
        if (s.length() == 0) return true;

        int start = 0;
        int end = s.length() - 1;

        char sc = ' ', ec = ' ';
        while (start <= end) {
            sc = s.charAt(start);
            ec = s.charAt(end);
            if (!isAlphaNumeric(sc)) {
                start++;
                continue;
            }
            if (!isAlphaNumeric(ec)) {
                end--;
                continue;
            }

            sc = (isAlphabet(sc) && sc < 'a') ? (char) ('a' + sc - 'A') : sc;
            ec = (isAlphabet(ec) && ec < 'a') ? (char) ('a' + ec - 'A') : ec;
            if (sc == ec) {
                start++;
                end--;
            } else {
                return false;
            }
        }

        return sc == ec;
    }

    private boolean isAlphaNumeric(char c) {
        return (c >= '0' && c <= '9') || isAlphabet(c);
    }

    private boolean isAlphabet(char c) {
        return (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z');
    }

}
