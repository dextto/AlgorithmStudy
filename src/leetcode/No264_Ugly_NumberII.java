// https://leetcode.com/problems/ugly-number-ii/

package leetcode;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

public class No264_Ugly_NumberII {

    @Test
    public void testMin() throws Exception {
        TreeSet<Integer> set = new TreeSet<>();
        set.add(3);
        set.add(5);
        set.add(2);
        int min = set.higher(1);
        assertEquals(2, min);
        
        int max = Integer.MAX_VALUE;
        assertTrue(1399680000 < max);
    }

    @Test
    public void test() throws Exception {
        No264_Ugly_NumberII instance = new No264_Ugly_NumberII();
        assertEquals(1, instance.nthUglyNumber(1));
        assertEquals(2, instance.nthUglyNumber(2));
        assertEquals(3, instance.nthUglyNumber(3));
        assertEquals(12, instance.nthUglyNumber(10));
        assertEquals(536870912, instance.nthUglyNumber(1407));
        assertEquals(1399680000, instance.nthUglyNumber(1600));
    }

    public int nthUglyNumber(int n) {
        if (n < 0) return -1;
        if (n == 1) return 1;
        
        TreeSet<Integer> set = new TreeSet<>();
        set.add(1);

        int ans = 1;
        int cur = 1;
        while (cur < n) {
            set.remove(ans);
            add(set, ans, 2);
            add(set, ans, 3);
            add(set, ans, 5);
            ans = set.higher(ans);
            cur++;
        }
        return ans;
    }

    void add(Set<Integer> set, int ans, int v) {
        long val = ((long) ans) * v;
        if (val > 0 && val <= Integer.MAX_VALUE) {
            set.add((int)val);
        }
    }
}
