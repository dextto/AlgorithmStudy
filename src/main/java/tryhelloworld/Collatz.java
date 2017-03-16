package tryhelloworld;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class Collatz {
    @Test
    public void test() throws Exception {
        Collatz c = new Collatz();
        assertEquals(8, c.collatz(6));
        assertEquals(-1, c.collatz(939497));
        assertEquals(-1, c.collatz(837799));
    }

    public int collatz(int num) {
        int answer = 0;
        
        while (num > 1 && answer < 500) {
            if (num % 2 == 0) {
                num = num / 2;
            } else {
                num = num * 3 + 1;
            }
            answer++;
        }
        return (answer >= 500 || num < 0) ? -1 : answer;
    }
}
