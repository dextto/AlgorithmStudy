// https://leetcode.com/problems/longest-palindromic-substring/

package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class No005_LongestPalindromicSubstring {

    @Test
    public void test() throws Exception {
        assertEquals("aaabaaa", longestPalindrome("aaabaaaa"));
        assertEquals("a", longestPalindrome("a"));
        assertEquals("ABBA", longestPalindrome("ABBA"));
        assertEquals("ABBA", longestPalindrome("12ABBA356"));
        assertEquals("ABBBA", longestPalindrome("12ABBBA356"));
        assertEquals("ABBBA", longestPalindrome("12ABBBA356"));
        assertEquals("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", 
                longestPalindrome("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabcaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
    }

    String str;
    int begin = 0;
    int maxLen = 0;

    public String longestPalindrome(String s) {
        int length = s.length();
        if (length < 2) return s;
        
        str = s;
        begin = 0;
        maxLen = 0;

        for (int i = 0; i < length - 1; i++) {
            expandPalindrome(i, i); // odd
            expandPalindrome(i, i + 1); // even
        }
        
        return s.substring(begin, begin + maxLen);
    }

    private void expandPalindrome(int j, int k) {
        int strLen = str.length();
        while(j >= 0 && k < strLen && str.charAt(j) == str.charAt(k)) {
            j--;
            k++;
        }
        int len = k - j - 1;
        if (maxLen < len) {
            maxLen = len;
            begin = j + 1;
        }
    }
}
