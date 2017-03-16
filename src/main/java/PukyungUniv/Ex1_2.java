package PukyungUniv;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Ex1_2 {

    @Test
    public void test() {
        assertEquals(0, binomial(0, 2));
        assertEquals(1, binomial(1, 0));
        assertEquals(1, binomial(1, 1));
        assertEquals(2, binomial(2, 1));
        assertEquals(1, binomial(2, 2));
        assertEquals(3, binomial(3, 1));
        assertEquals(3, binomial(3, 2));
        assertEquals(1, binomial(3, 3));
        assertEquals(4, binomial(4, 1));
        assertEquals(6, binomial(4, 2));
        assertEquals(4, binomial(4, 3));
        assertEquals(1, binomial(4, 4));
    }

    public int binomial(int n, int k) {
        if (n == 0)
            return 0;
        if (k == 0 || n == k)
            return 1;
        else
            return binomial(n - 1, k) + binomial(n - 1, k - 1);
    }
}