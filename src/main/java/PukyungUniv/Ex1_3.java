package PukyungUniv;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Ex1_3 {
    // n자리 2진수 중에서 0이 연속해서 나오지 않는 것의 개수는?
    // 단, 최상위 비트가 0일수는 없다.

    @Test
    public void test() {
        assertEquals(1, count(1)); // 0 1
        assertEquals(2, count(2)); // 00 01 10 11
        assertEquals(3, count(3)); // 000 001 010 011 100 101 110 111
        assertEquals(5, count(4)); // 0000 0001 0010 0011
                                   // 0100 0101 0110 0111
                                   // 1000 1001 1010 1011
                                   // 1100 1101 1110 1111
    }

    public int count(int n) {
        return _count(n - 1);
    }

    public int _count(int n) {
        if (n <= 2)
            return n + 1;
        else
            return _count(n - 1) + _count(n - 2);
    }
}