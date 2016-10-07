// https://leetcode.com/problems/zigzag-conversion/

package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class No008_StringToInteger_atoi {

    @Test
    public void test() {
        assertEquals(123, myAtoi("123"));
        assertEquals(-123, myAtoi("-123"));
    }
    
    public int myAtoi(String str) {
        String s = str.trim();
        String ret = "";
        try {
            StringBuilder builder = new StringBuilder();
            boolean hasSymbol = false;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c == '+' || c == '-') {
                    if (hasSymbol) {
                        return 0;
                    }
                    hasSymbol = true;
                    builder.append(c);
                } else if (c >= '0' && c <= '9') {
                    builder.append(c);
                } else {
                    break;
                }
            }
            ret = builder.toString();
//            System.out.println(ret);
            return Integer.parseInt(ret);
        } catch (Exception e) {
            if (ret.length() == 0 || ret.equals("+") || ret.equals("-")) {
                return 0;
            } else {
                final String minValStr = Integer.toString(Integer.MIN_VALUE);
                final String maxValStr = Integer.toString(Integer.MAX_VALUE);
                if (ret.charAt(0) == '-' && (ret.length() > minValStr.length() ||ret.compareTo(minValStr) > 0)) {
                    return Integer.MIN_VALUE;
                } else if (ret.charAt(0) != '-' && (ret.length() > maxValStr.length() || ret.compareTo(maxValStr) > 0)) {
                    return Integer.MAX_VALUE;
                } else {
                    return 0;
                }
            }
        }
    }
}
