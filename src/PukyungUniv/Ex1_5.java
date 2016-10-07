package PukyungUniv;

import static org.junit.Assert.*;

import org.junit.Test;

public class Ex1_5 {
    // Palindrome이란 바로 읽으나 거꾸로 읽으나 같은 단어를 말한다. 단어가 palindrome인지 아닌지 판단하는 함수를
    // recursion으로 작성하라. Hint: 첫 글짜와 마지막 글짜가 동일하고, 첫 글짜와 마지막 글짜를 제외한 나머지
    // 부분이 palindrome이어야 한다

    @Test
    public void test() {
        assertTrue(isPalindrome("a"));
        assertTrue(isPalindrome("aa"));
        assertTrue(isPalindrome("aba"));
        assertTrue(isPalindrome("abba"));

        assertFalse(isPalindrome("abc"));
    }

    public boolean isPalindrome(String s) {
        return isPalindrome(s, 0, s.length() - 1);
    }

    private boolean isPalindrome(String s, int start, int end) {
        if (start >= end)
            return true;
        else if (s.charAt(start) != s.charAt(end))
            return false;
        else
            return isPalindrome(s, start + 1, end - 1);
    }

}