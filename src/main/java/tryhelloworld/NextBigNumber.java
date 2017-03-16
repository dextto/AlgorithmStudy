package tryhelloworld;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class NextBigNumber {
    @Test
    public void test() throws Exception {
        NextBigNumber t = new NextBigNumber();
        assertEquals(1, t.getHex1Count(1));
        assertEquals(1, t.getHex1Count(2));
        assertEquals(2, t.getHex1Count(3));
        assertEquals(1, t.getHex1Count(4));
        assertEquals(4, t.getHex1Count(78));
        
        assertEquals(83, t.nextBigNumber(78));
    }

    public int nextBigNumber(int n) {
        int cnt = getHex1Count(n);
        
        int answer = n + 1;
        while(cnt != getHex1Count(answer)) {
            answer++;
        }
        return answer;
    }

    public int getHex1Count(int n) {
        return Integer.bitCount(n);
        
        //return (int) Integer.toBinaryString(n).chars().filter(c -> c == '1').count();
        
        /*int cnt = 0;
        while (n > 0) {
            if ((n & 1) == 1) { // n & 1 means n % 2 
                cnt++;
            }
            n = n >> 1; // n >> 1 means n / 2
        }
        return cnt;*/
    }
}
