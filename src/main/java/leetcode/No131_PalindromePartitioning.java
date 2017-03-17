// https://leetcode.com/problems/palindrome-partitioning/

package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class No131_PalindromePartitioning {

    @Test
    public void test() throws Exception {
        No131_PalindromePartitioning instance = new No131_PalindromePartitioning();
        assertEquals("[[]]", instance.partition("").toString());
        assertEquals("[[a]]", instance.partition("a").toString());
        assertEquals("[[a, b]]", instance.partition("ab").toString());
        assertEquals("[[a, a], [aa]]", instance.partition("aa").toString());
        assertEquals("[[a, a, b], [aa, b]]", instance.partition("aab").toString());
        assertEquals("[[a, b, c]]", instance.partition("abc").toString());
        assertEquals("[[a, b, b, a], [a, bb, a], [abba]]", instance.partition("abba").toString());
        assertEquals("[[a, a, a, b, c, c], [a, a, a, b, cc], [a, aa, b, c, c], [a, aa, b, cc], [aa, a, b, c, c], [aa, a, b, cc], [aaa, b, c, c], [aaa, b, cc]]",
                    instance.partition("aaabcc").toString());
    }

    // TODO: DP (HashSet 적용)

    public List<List<String>> partition(String s) {
        List<List<String>> ret = new ArrayList<>();
        List<String> list = new ArrayList<>();
        if (s.length() == 0) {
            ret.add(list);
        } else {
            backTrack(s, 0, list, ret);
        }

        return ret;
    }

    public void backTrack(String s, int start, List<String> list, List<List<String>> ret) {
        if (list.size() > 0 // first string
                && start >= s.length()) {
            List<String> r = new ArrayList<>(list);
            ret.add(r);
        }

        for (int i = start; i < s.length(); i++) {
            String left = (i == start) ? String.valueOf(s.charAt(i)) : s.substring(start, i + 1);
            if (isPalindrom(left)) {
                list.add(left);
                backTrack(s, i + 1, list, ret);
                list.remove(list.size() - 1);
            }
        }
    }

    public boolean isPalindrom(String s) {
        if (s.length() == 0) return false;
        StringBuffer buf = new StringBuffer(s);
        String p = buf.reverse().toString();
        return s.equals(p);
    }
}
