package PukyungUniv;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Ex1_1 {

    @Test
    public void test() {
        assertEquals(1, sumOfOdds(1));
        assertEquals(1, sumOfOdds(2));
        assertEquals(4, sumOfOdds(3));
        assertEquals(4, sumOfOdds(4));
        assertEquals(9, sumOfOdds(5));
    }

    public int sumOfOdds(int n) {
        if (n < 1)
            return 0;
        else
            return (n % 2 == 0) ? sumOfOdds(n - 1) : n + sumOfOdds(n - 1);
    }
}