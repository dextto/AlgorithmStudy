package tryhelloworld;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class TwoByN_Tiling {
    @Test
    public void test() throws Exception {
        TwoByN_Tiling instance = new TwoByN_Tiling();
//        assertEquals(2, instance.tiling(2));
//        assertEquals(3, instance.tiling(3));
//        assertEquals(5, instance.tiling(4));
//        assertEquals(8, instance.tiling(5));
//        assertEquals(13, instance.tiling(6));
//        assertEquals(21, instance.tiling(7));
//        assertEquals(95995, instance.tiling(159));
        assertEquals(31586, instance.tiling(938));
    }

    public int tiling(int n) {
        int answer = fibo(n);
        int a = Integer.MAX_VALUE;
        return (answer > 99999) ? answer % 100000 : answer;
    }

    private int fibo(int n) {
        /*if (n == 2) return 2;
        else if (n == 3) return 3;
        else {
            return fibo(n - 1) + fibo(n - 2);
        }*/

        if (n == 2) return 2;
        else if (n == 3) return 3;
        else {
            int sum = 0;
            int n1 = 2;
            int n2 = 3;
            for (int i = 4; i <= n; i++) {
                sum = n1 + n2;
                n1 = n2;
                n2 = sum;
            }
            return sum;
        }
    }
}
