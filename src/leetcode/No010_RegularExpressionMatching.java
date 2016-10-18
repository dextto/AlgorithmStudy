// https://leetcode.com/problems/regular-expression-matching/

package leetcode;

import static org.junit.Assert.*;

import org.junit.Test;

public class No010_RegularExpressionMatching {

    @Test
    public void test() {
        assertFalse(isMatch("aa", "*"));
        assertFalse(isMatch("aa", "a"));
        assertFalse(isMatch("aaa", "aa"));
        assertFalse(isMatch("aa", "aaa"));
        assertFalse(isMatch("ab", ".*c"));
        assertFalse(isMatch("a", "ab*a"));
        assertFalse(isMatch("aaba", "ab*a*c*a"));

        assertTrue(isMatch("", ""));
        assertTrue(isMatch("aa", "aa"));
        assertTrue(isMatch("aa", "a*"));
        assertTrue(isMatch("aa", ".*"));
        assertTrue(isMatch("ab", ".*"));
        assertTrue(isMatch("ab", "a*bc*"));
        assertTrue(isMatch("aab", "c*a*b"));
        assertTrue(isMatch("aab", "a*bc*"));
        assertTrue(isMatch("aaa", "ab*a*c*a"));
        assertTrue(isMatch("aaa", "ab*a*.*a"));
    }

    @Test
    public void test1() {
        assertTrue("aab".matches("c*a*b"));
        assertTrue("aaaabk".matches("a*b."));
        assertTrue("aab".matches("a*bc*"));
        assertTrue("aaa".matches("ab*a*.*a"));
        assertFalse("aaba".matches("ab*a*c*a"));
    }

    public boolean isMatch(String s, String p) {
        if (p.length() == 0)
            return s.length() == 0;

        //p's length 1 is special case
        if (p.length() == 1 || p.charAt(1) != '*') {
            if (s.length() < 1 || (p.charAt(0) != '.' && s.charAt(0) != p.charAt(0)))
                return false;
            return isMatch(s.substring(1), p.substring(1));
        } else {
            int len = s.length();

            int i = -1;
            while (i < len && (i < 0 || p.charAt(0) == '.' || p.charAt(0) == s.charAt(i))) {
                if (isMatch(s.substring(i + 1), p.substring(2)))
                    return true;
                i++;
            }
            return false;
        }
    }
}
