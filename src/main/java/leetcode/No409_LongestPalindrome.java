// https://leetcode.com/problems/increasing-holders
// NOTE: the order of output is neglectable

package leetcode;


import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class No409_LongestPalindrome {

    @Test
    public void test() throws Exception {
        No409_LongestPalindrome instance = new No409_LongestPalindrome();
        assertEquals(0, instance.longestPalindrome(""));
        assertEquals(1, instance.longestPalindrome("a"));
        assertEquals(1, instance.longestPalindrome("aA"));
        assertEquals(1, instance.longestPalindrome("abc"));
        assertEquals(4, instance.longestPalindrome("aabb"));
        assertEquals(7, instance.longestPalindrome("aaaaabbb"));
        assertEquals(7, instance.longestPalindrome("abccccdd"));
        assertEquals(11, instance.longestPalindrome("eeabbbcccccdd"));
        assertEquals(983, instance.longestPalindrome("civilwartestingwhetherthatnaptionoranynartionsoconceivedandsodedicatedcanlongendureWeareqmetonagreatbattlefiemldoftzhatwarWehavecometodedicpateaportionofthatfieldasafinalrestingplaceforthosewhoheregavetheirlivesthatthatnationmightliveItisaltogetherfangandproperthatweshoulddothisButinalargersensewecannotdedicatewecannotconsecratewecannothallowthisgroundThebravelmenlivinganddeadwhostruggledherehaveconsecrateditfaraboveourpoorponwertoaddordetractTgheworldadswfilllittlenotlenorlongrememberwhatwesayherebutitcanneverforgetwhattheydidhereItisforusthelivingrathertobededicatedheretotheulnfinishedworkwhichtheywhofoughtherehavethusfarsonoblyadvancedItisratherforustobeherededicatedtothegreattdafskremainingbeforeusthatfromthesehonoreddeadwetakeincreaseddevotiontothatcauseforwhichtheygavethelastpfullmeasureofdevotionthatweherehighlyresolvethatthesedeadshallnothavediedinvainthatthisnationunsderGodshallhaveanewbirthoffreedomandthatgovernmentofthepeoplebythepeopleforthepeopleshallnotperishfromtheearth"));
    }

    public int longestPalindrome(String s) {
        if (s.length() == 0) return 0;

        int[] arr = new int['z' + 1];
        for (int i = 0; i < s.length(); i++) {
            arr[s.charAt(i)]++;
        }

        boolean foundOdd = false;
        int ret = 0;
        for (int i = 'A'; i <= 'z'; i++) {
            int n = arr[i];
            if (n % 2 == 0) {
                ret += n;
            } else {
                if (foundOdd) {
                    ret = ret - 1 + n;
                } else {
                    ret += n;
                    foundOdd = true;
                }
            }
        }
        return ret;
    }
}
