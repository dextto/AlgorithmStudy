// https://leetcode.com/problems/longest-substring-without-repeating-characters/

package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class No003_LongestSubstringWithoutRepeatingCharacters {

    @Test
    public void test() throws Exception {
        No003_LongestSubstringWithoutRepeatingCharacters instance = new No003_LongestSubstringWithoutRepeatingCharacters();
        assertEquals(3, instance.lengthOfLongestSubstring("abcabcbb"));
        assertEquals(1, instance.lengthOfLongestSubstring("bbbbb"));
        assertEquals(3, instance.lengthOfLongestSubstring("pwwkew"));
        assertEquals(6, instance.lengthOfLongestSubstring("ohvhjdml"));
        assertEquals(4, instance.lengthOfLongestSubstring("vqblqcb"));
    }

    public int lengthOfLongestSubstring(String s) {
        return lengthOfLongestSubstring(s, 0);
    }

    private int lengthOfLongestSubstring(String s, int start) {
        if (s.length() == 0) return 0;

        boolean[] check = new boolean[200];
        List<Character> list = new ArrayList<>();
        
        char[] chars = s.toCharArray();
        for (int i = start; i < s.length(); i++) {
            char c = chars[i];
            int idx = (int)c;
            if (!check[idx]) {
                check[idx] = true;
                list.add(c);
            } else {
                if (c == list.get(0)) {
                    list.remove(0);
                    list.add(c);
                } else {
                    int perviousCharIdx = i - 1;
                    while (chars[perviousCharIdx] != c) {
                        perviousCharIdx--;
                    }

//                    return Math.max(list.size(), 
//                            (i - perviousCharIdx - 1) + lengthOfLongestSubstring(s, i));
                    return Math.max(list.size(), lengthOfLongestSubstring(s, perviousCharIdx + 1));
                }
            }
        }
        return list.size();
    }

}
