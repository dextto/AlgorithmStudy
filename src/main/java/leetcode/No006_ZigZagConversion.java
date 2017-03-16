// https://leetcode.com/problems/zigzag-conversion/

package leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class No006_ZigZagConversion {

    @Test
    public void testGetColumnCount() {
        assertEquals(1, getColumnCount("AB", 1));
        assertEquals(7, getColumnCount("PAYPALISHIRING", 3));
        assertEquals(7, getColumnCount("PAYPALISHIRING", 4));
    }
    
    @Test
    public void test() throws Exception {
        assertEquals("", convert("", 3));
        assertEquals("A", convert("A", 3));
        assertEquals("AB", convert("AB", 1));
        assertEquals("ABC", convert("ABC", 3));
        assertEquals("PAHNAPLSIIGYIR", convert("PAYPALISHIRING", 3));
    }

    private int getColumnCount(String s, int numRows) {
        if (numRows == 1) return 1;

        int l = s.length();
        int q = l / (2 * numRows - 2); // quotient
        int r = l % (2 * numRows - 2); // rest
        
        int ret = q * (1 + numRows - 2);
        if (r <= numRows) {
            ret++;
        } else {
            ret += 1 + (r - numRows);
        }
        return ret;
    }
    public String convert(String s, int numRows) {
        if (s.length() < 2 || numRows == 1) return s;
        
        StringBuilder builder = new StringBuilder();
        
        char arr[][] = new char[numRows][getColumnCount(s, numRows)];
        int row = -1;
        int col = -1;
        int dir = 1;
        for (char c : s.toCharArray()) {
            if (row == 0) dir = 1;
            else if (row == numRows - 1) dir = -1;
            row += dir;

            if (row == 0 || dir == -1) col++;

            arr[row][col] = c;
        }

        for (char ar[] : arr) {
            for (char c : ar) {
                if (c != 0) {
                    builder.append(c);
                }
            }
        }
        
        return builder.toString();
    }
}
