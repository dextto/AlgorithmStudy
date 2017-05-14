package acmicpc.prob1463_MakeOne;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;

import static java.lang.Math.min;
import static org.junit.Assert.assertEquals;

public class Main {

    @Test
    public void test() throws URISyntaxException, IOException {
        assertEquals(0, makeOne(1));
        assertEquals(1, makeOne(2));
        assertEquals(1, makeOne(3));
        assertEquals(2, makeOne(4));
        assertEquals(3, makeOne(5));
        assertEquals(2, makeOne(6));
        assertEquals(3, makeOne(7));
        assertEquals(3, makeOne(8));
        assertEquals(2, makeOne(9));
        assertEquals(3, makeOne(10));
        assertEquals(4, makeOne(11));
        assertEquals(3, makeOne(12));
        assertEquals(19, makeOne(1000000));
    }

    static int[] dp = new int[1_000_000 + 1];

    static int makeOne(int N) {
        dp[1] = 0;
        dp[2] = 1;
        dp[3] = 1;

        for (int i = 4; i <= N; i++) {
            int div3 = (i % 3 == 0) ? dp[i / 3] + 1 : Integer.MAX_VALUE;
            int div2 = (i % 2 == 0) ? dp[i / 2] + 1 : Integer.MAX_VALUE;
            int minusOne = dp[i - 1] + 1;
            dp[i] = min(min(div3, div2), minusOne);
        }

        return dp[N];
    }

    static BufferedReader reader;

    public static void main(String[] args) throws IOException {
        reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());
        System.out.println(makeOne(N));
    }

}
