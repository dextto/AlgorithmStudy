// https://leetcode.com/problems/super-ugly-number/

package leetcode;


import static org.junit.Assert.assertEquals;

import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

public class No313_SuperUglyNumber {

    @Test
    public void test() throws Exception {
        No313_SuperUglyNumber instance = new No313_SuperUglyNumber();
        int[] primes = new int[] { 2, 3, 5 };
        assertEquals(1, instance.nthSuperUglyNumber(1, primes));
        assertEquals(2, instance.nthSuperUglyNumber(2, primes));
        assertEquals(3, instance.nthSuperUglyNumber(3, primes));
        assertEquals(12, instance.nthSuperUglyNumber(10, primes));
        assertEquals(536870912, instance.nthSuperUglyNumber(1407, primes));
        assertEquals(1399680000, instance.nthSuperUglyNumber(1600, primes));
    }

    public int nthSuperUglyNumber(int n, int[] primes) {
        if (n < 0) return -1;
        if (n == 1) return 1;
        
        TreeSet<Integer> set = new TreeSet<>();
        set.add(1);

        int ans = 1;
        int cur = 1;
        while (cur < n) {
            set.remove(ans);
            for (int v : primes) {
                add(set, ans, v);
            }
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
