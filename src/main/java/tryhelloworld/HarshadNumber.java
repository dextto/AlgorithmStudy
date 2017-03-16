package tryhelloworld;

import static org.junit.Assert.assertTrue;

import org.junit.Test;


public class HarshadNumber {
    @Test
    public void test() throws Exception {
        HarshadNumber h = new HarshadNumber();
        assertTrue(h.isHarshad(18));
    }

    private int sum = 0;
    public boolean isHarshad(int num){
        sum = 0;
        Integer.toString(num).chars().forEach(c -> sum += c - '0');
        return num % sum == 0;
    }
}
